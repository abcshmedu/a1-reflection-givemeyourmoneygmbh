package edu.hm.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ifw14087 on 27.03.2017.
 * http://tutorials.jenkov.com/java/annotations.html
 * http://www.vogella.com/tutorials/JavaAnnotations/article.html
 * http://www.torsten-horn.de/techdocs/java-annotations.htm
 */
public class Renderer {

    private Object object;

    /**
     * Konstruktor, der das zu untersuchende Object entgegen nimmt.
     * @param object das zu untersuchende Object.s
     */
    public Renderer(Object object) {
        this.object = object;
    }

    /**
     * @return String result - Der konkatinierte String, der die Object-Informationen enthaelt
     * @throws IllegalAccessException    -
     * @throws ClassNotFoundException    -
     * @throws NoSuchMethodException     -
     * @throws InvocationTargetException -
     * @throws InstantiationException    -
     */
    public String render() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        final StringBuilder result = new StringBuilder();

        final String className = object.getClass().getCanonicalName();
        result.append(String.format("Instance of %s:\n", className));

        final Field[] declaredFields = object.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(RenderMe.class)) {
                final RenderMe annotation = field.getAnnotation(RenderMe.class);

                result.append(String.format("%s (Type %s): ", field.getName(), field.getType().getCanonicalName()));

                if (annotation.with().isEmpty()) {
                    result.append(field.get(object));
                    result.append("\n");
                } else {

                    final String renderClassName = field.getAnnotation(RenderMe.class).with();
                    final Object value = field.get(object);

                    final Class< ? > renderClass = Class.forName(renderClassName);

                    final Object renderObj = renderClass.getConstructor().newInstance();
                    final Method method = renderClass.getMethod("render", value.getClass());
                    final Object resultObj = method.invoke(renderObj, value);
                    result.append(resultObj.toString());
                    result.append("\n");

                }
            }
        }


        Method[] methods = object.getClass().getDeclaredMethods();

        List< Method > methodList = Arrays.asList(methods);
        methodList.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));

        for (Method method : methods) {

            method.setAccessible(true);

            if (method.isAnnotationPresent(RenderMe.class)) {
                final RenderMe annotation = method.getAnnotation(RenderMe.class);

                try {
                    result.append(String.format("%s (Type %s): ", method.getName(), method.getReturnType().getCanonicalName()));
                    if (annotation.with().isEmpty()) {
                        Class< ? > c = Class.forName(className);
                        //Works only for constructors without parameters
                        Object instance = c.newInstance();
                        result.append(method.invoke(instance).toString());
                        result.append("\n");
                    } else {

                        final String renderClassName = method.getAnnotation(RenderMe.class).with();

                        Class< ? > c = Class.forName(className);
                        //Works only for constructors without parameters
                        Object instance = c.newInstance();
                        final Object value = method.invoke(instance);

                        final Class< ? > renderClass = Class.forName(renderClassName);

                        final Object renderObj = renderClass.getConstructor().newInstance();
                        final Method methodRender = renderClass.getMethod("render", value.getClass());
                        final Object resultObj = methodRender.invoke(renderObj, value);
                        result.append(resultObj.toString());
                        result.append("\n");
                    }
                } catch (InvocationTargetException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return result.toString();
    }
}