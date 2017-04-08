package edu.hm.Annotations;
import java.lang.reflect.Field;

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


    public String render() throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        //TODO: access annotated elements via Java Reflection

        //final RenderMe[] declaredAnnotationsByType =
        //        object.getClass().getDeclaredAnnotationsByType(RenderMe.class);

        result.append(String.format("Instance of %s:\n",object.getClass().getCanonicalName()));

        final Field[] declaredFields = object.getClass().getDeclaredFields();

        for (Field field :declaredFields) {
            field.setAccessible(true);

            if(field.isAnnotationPresent(RenderMe.class)){
                //final String name = field.getName();
                //final Class<?> type = field.getType();
                result.append(String.format("%s (Type %s): ",field.getName(),field.getType().getCanonicalName()));
                result.append(field.get(object));
                result.append("\n");
                //TODO: get Value

            }
        }


        return result.toString();
    }

}
