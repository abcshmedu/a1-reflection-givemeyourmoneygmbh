package edu.hm;

import edu.hm.renderer.RenderMe;

/**
 * Created by ivi on 4/8/2017.
 */
public class AmazingClass {

    /**
     * private idempotente Methode, die eine Konstante zurueckliefert.
     * @return int Null
     */
    @RenderMe
    private int privateMethod() {
        return 0;
    }

    /**
     * oeffentliche idempotente Methode, die eine Konstante zurueckliefert.
     * @return int Null
     */
    @RenderMe
    public int publicMethod() {
        return 0;
    }

    /**
     * oeffentliche idempotente Methode, die ein Array zurueckliefert.
     * @return int[] Null, Null
     */
    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    public int[] publicMethodWith() {
        int[] result = {0, 0};
        return result;
    }

    /**
     * private idempotente Methode, die ein Array zurueckliefert.
     * @return int[] Null, Null
     */
    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    private int[] privateMethodWith() {
        int[] result = {0, 0};
        return result;
    }

    /**
     * Default-Konstruktor.
     */
    public AmazingClass() {

    }
}
