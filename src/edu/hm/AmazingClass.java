package edu.hm;

import edu.hm.renderer.RenderMe;

/**
 * Created by ivi on 4/8/2017.
 */
public class AmazingClass {

    @RenderMe
    private int privateMethod() {
        return 0;
    }

    @RenderMe
    public int publicMethod() {
        return 0;
    }

    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    public int[] publicMethodWith() {
        int[] result = {0, 0};
        return result;
    }

    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    private int[] privateMethodWith() {
        int[] result = {0, 0};
        return result;
    }

    public AmazingClass() {

    }
}
