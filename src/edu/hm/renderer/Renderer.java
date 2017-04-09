package edu.hm.renderer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ifw14087 on 27.03.2017.
 * http://tutorials.jenkov.com/java/annotations.html
 * http://www.vogella.com/tutorials/JavaAnnotations/article.html
 * http://www.torsten-horn.de/techdocs/java-annotations.htm
 */
public class Renderer {


    private Object object;

    public Renderer(Object object) {
        this.object = object;

    }

    /**
     *
     * @return
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public String render() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        StringBuilder result = new StringBuilder();

        final String className = object.getClass().getCanonicalName();
        result.append(String.format("Instance of %s:\n",className));

        final Field[] declaredFields = object.getClass().getDeclaredFields();

        for (Field field :declaredFields) {
            field.setAccessible(true);

            if(field.isAnnotationPresent(RenderMe.class)){
                final RenderMe annotation = field.getAnnotation(RenderMe.class);


                if(annotation.with().isEmpty()) {
                    result.append(String.format("%s (Type %s): ", field.getName(), field.getType().getCanonicalName()));

                    result.append(field.get(object));
                    result.append("\n");
                }
                else{
                    result.append(String.format("%s (Type %s) ", field.getName(), field.getType().getCanonicalName()));


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

        for (Method method : methods) {


            method.setAccessible(true);

            if(method.isAnnotationPresent(RenderMe.class)){
                final RenderMe annotation = method.getAnnotation(RenderMe.class);

                try {
                    if(annotation.with().isEmpty()) {
                        Class<?> c = Class.forName(className);
                        //Works only for constructors without parameters
                        Object instance = c.newInstance();
                        result.append(method.invoke(instance).toString());
                        result.append("\n");
                    }
                    else{
                        result.append(String.format("%s (Type %s) ", method.getName(), method.getReturnType().getCanonicalName()));

                        final String renderClassName = method.getAnnotation(RenderMe.class).with();

                        Class<?> c = Class.forName(className);
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
                }
                catch (InvocationTargetException exception){

                }

            }

        }


        return result.toString();
    }

}
