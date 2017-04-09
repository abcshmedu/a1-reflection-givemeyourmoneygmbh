package edu.hm;

import edu.hm.renderer.RenderMe;

import java.util.Date;

/**
 * Created by ivi on 4/8/2017.
 */
public class SomeClass {
    /**
     * MagicNumber1.
     */
    private final int magicNumber1 = 1;
    /**
     * MagicNumber2.
     */
    private final int magicNumber2 = 2;
    /**
     * MagicNumber3.
     */
    private final int magicNumber3 = 3;
    /**
     * Datumskonstante.
     */
    private final int magicNumber123456789 = 123456789;
    /**
     * TestIntegerVariable.
     */
    @RenderMe
    private int foo;
    /**
     * TestIntArray.
     */
    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    private int[] array = {magicNumber1, magicNumber2, magicNumber3};
    /**
     * TestDatum.
     */
    @RenderMe
    private Date date = new Date(magicNumber123456789);

    /**
     * Konstruktor, der die foo-Variable entgegen nimmt.
     * @param foo demoTestVariable
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
}
