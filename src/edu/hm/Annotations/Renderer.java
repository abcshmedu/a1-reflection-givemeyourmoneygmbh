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


    public String render(){
        String result = "";
        //TODO: access annotated elements via Java Reflection

        //final RenderMe[] declaredAnnotationsByType =
        //        object.getClass().getDeclaredAnnotationsByType(RenderMe.class);

        final Field[] declaredFields = object.getClass().getDeclaredFields();

        for (Field field :declaredFields) {
            field.setAccessible(true);

            if(field.isAnnotationPresent(RenderMe.class)){
                final String name = field.getName();
                final Class<?> type = field.getType();

                //TODO: get Value

            }
        }


        return result;
    }

}
