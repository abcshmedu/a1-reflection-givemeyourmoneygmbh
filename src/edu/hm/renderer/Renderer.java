package edu.hm.renderer;

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


    public String render() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        StringBuilder result = new StringBuilder();
        //TODO: access annotated elements via Java Reflection

        //final RenderMe[] declaredAnnotationsByType =
        //        object.getClass().getDeclaredAnnotationsByType(RenderMe.class);

        result.append(String.format("Instance of %s:\n",object.getClass().getCanonicalName()));

        final Field[] declaredFields = object.getClass().getDeclaredFields();

        for (Field field :declaredFields) {
            field.setAccessible(true);

            if(field.isAnnotationPresent(RenderMe.class)){
                final RenderMe annotation = field.getAnnotation(RenderMe.class);


                if(annotation.with().isEmpty()) {
                    result.append(String.format("%s (Type %s): ", field.getName(), field.getType().getCanonicalName()));

                    //final String name = field.getName();
                    //final Class<?> type = field.getType();
                    result.append(field.get(object));
                    result.append("\n");
                }
                else{
                    result.append(String.format("%s (Type %s) ", field.getName(), field.getType().getCanonicalName()));


                    final String renderClassName = field.getAnnotation(RenderMe.class).with();
                    Object value = field.get(object);

                    Class< ? > renderClass = Class.forName(renderClassName);

                    Object renderObj = renderClass.getConstructor().newInstance();
                    Method method = renderClass.getMethod("render", value.getClass());
                    Object resultObj = method.invoke(renderObj, value);
                    result.append((String) resultObj);
                    result.append("\n");

                }


                //TODO: get Value

            }
        }


        return result.toString();
    }

}
