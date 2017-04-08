package edu.hm;

import edu.hm.renderer.RenderMe;

/**
 * Created by ivi on 4/8/2017.
 */
public class AmazingClass {

    @RenderMe
    private int privateMethod(){

        return 1;
    }

    @RenderMe
    public int publicMethod(){

        return 0;
    }


    public AmazingClass() {

    }
}
