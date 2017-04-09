package edu.hm.renderer;

import edu.hm.AmazingClass;
import edu.hm.SomeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by ivi on 4/8/2017.
 */
@RunWith(Parameterized.class)
public class RendererTest {

    private int intGiven;
    private String expectedReturnValue;

    private SomeClass toRender;
    private Renderer renderer;
    private AmazingClass amazingClass;

    /**
     * Konstruktor, der die verschiedenen ParameterPaare entgegen nimmt.
     * @param intGivenIn der vorgegebene Wert
     * @param expectedReturnValueIn der erwartete String
     */
    public RendererTest(int intGivenIn, String expectedReturnValueIn){
        this.intGiven = intGivenIn;
        this.expectedReturnValue = expectedReturnValueIn;
    }

    /**
     * Erstellen der ParameterListe.
     * @return collection DatenArray mit Parametern fuer den Konstruktor
     */
    @Parameterized.Parameters
    public static Collection data() {
        final int magicNumber1 = 17;
        final int magicNumber2 = 1;
        final int magicNumber3 = 99;
        Collection dataArray = Arrays.asList(new Object[][]{
                {magicNumber1, "Instance of edu.hm.SomeClass:\nfoo (Type int): " + magicNumber1 + "\narray (Type int[]): [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},
                {magicNumber2, "Instance of edu.hm.SomeClass:\nfoo (Type int): " + magicNumber2 + "\narray (Type int[]): [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},
                {magicNumber3, "Instance of edu.hm.SomeClass:\nfoo (Type int): " + magicNumber3 + "\narray (Type int[]): [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},
        });
        return dataArray;
    }

    /**
     * Initialisierung von drei TestObjects.
     */
    @Before
    public void setUp() {
        final int magicNumber5 = 5;
        toRender = new SomeClass(magicNumber5);
        renderer = new Renderer(toRender);
        amazingClass = new AmazingClass();
    }

    /**
     * Test fuer Felder.
     *
     * @throws IllegalAccessException    -
     * @throws ClassNotFoundException    -
     * @throws NoSuchMethodException     -
     * @throws InvocationTargetException -
     * @throws InstantiationException    -
     */
    @Test
    public void testRendering() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        assertEquals("Instance of edu.hm.SomeClass:\n" + "foo (Type int): 5\narray (Type int[]): [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n", renderer.render());
        assertEquals(new Renderer(new SomeClass(intGiven)).render(),expectedReturnValue);
    }

    /**
     * Test fuer Methoden (private und public).
     *
     * @throws IllegalAccessException    -
     * @throws ClassNotFoundException    -
     * @throws NoSuchMethodException     -
     * @throws InvocationTargetException -
     * @throws InstantiationException    -
     */
    @Test
    public void renderMethods() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        String expected = "Instance of edu.hm.AmazingClass:\n" + "privateMethod (Type int): 0\nprivateMethodWith (Type int[]): [0, 0, ]\npublicMethod (Type int): 0\npublicMethodWith (Type int[]): [0, 0, ]\n";

        String result = new Renderer(amazingClass).render();
        //System.out.println(result);
        assertEquals(expected, result);
    }
}