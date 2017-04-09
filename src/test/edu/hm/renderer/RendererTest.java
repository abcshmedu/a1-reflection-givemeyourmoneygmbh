package edu.hm.renderer;

import edu.hm.AmazingClass;
import edu.hm.SomeClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ivi on 4/8/2017.
 */
public class RendererTest {

    private SomeClass toRender;
    private Renderer renderer;
    private AmazingClass amazingClass;

    @Before
    public void setUp() {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
        amazingClass = new AmazingClass();
    }

    @Test
    public void testRendering() throws Exception {
        assertEquals("Instance of edu.hm.SomeClass:\n" + "foo (Type int): 5\narray (Type int[]): [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n", renderer.render());
    }

    /**
     * Test f�r Methoden (private und public)
     *
     * @throws Exception
     */
    @Test
    public void renderMethods() throws Exception {

        String expected = "Instance of edu.hm.AmazingClass:\n" + "privateMethod (Type int): 0\nprivateMethodWith (Type int[]): [0, 0, ]\npublicMethod (Type int): 0\npublicMethodWith (Type int[]): [0, 0, ]\n";

        String result = new Renderer(amazingClass).render();
        //System.out.println(result);
        assertEquals(expected, result);
    }


}