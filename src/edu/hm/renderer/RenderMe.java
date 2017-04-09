package edu.hm.renderer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ifw14087 on 27.03.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {
    /**
     * Methode zum auslesen etwaiger spezieller Renderklassen.
     * @return String spezielle Renderklassen
     */
    String with() default "";
}

