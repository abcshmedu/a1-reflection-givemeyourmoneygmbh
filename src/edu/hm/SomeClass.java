package edu.hm;

import edu.hm.renderer.RenderMe;

import java.util.Date;

/**
 * Created by ivi on 4/8/2017.
 */
public class SomeClass {
    @RenderMe
    private int foo;
    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    protected int[] array = {1, 2, 3};
    @RenderMe
    public Date date = new Date(123456789);

    public SomeClass(int foo) {
        this.foo = foo;
    }

    public int getFoo() {
        return foo;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
