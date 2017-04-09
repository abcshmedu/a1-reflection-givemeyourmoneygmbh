package edu.hm.renderer;

import edu.hm.AmazingClass;
import edu.hm.SomeClass;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
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
        assertEquals("Instance of edu.hm.SomeClass:\n" + "foo (Type int): 5\narray (Type int[]) [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n", renderer.render());
    }

    /**
     * Test f�r Methoden (private und public)
     * @throws Exception
     */
    @Test
    public void renderMethods() throws Exception {

        String result1 = "Instance of edu.hm.AmazingClass:\n" + "publicMethodWith (Type int[]) [0, 0, ]\nprivateMethodWith (Type int[]) [0, 0, ]\n0\n0\n";
        String result2 = "Instance of edu.hm.AmazingClass:\n" + "0\n0\npublicMethodWith (Type int[]) [0, 0, ]\nprivateMethodWith (Type int[]) [0, 0, ]\n";

        String result3 = "Instance of edu.hm.AmazingClass:\n" + "privateMethodWith (Type int[]) [0, 0, ]\npublicMethodWith (Type int[]) [0, 0, ]\n0\n0\n";
        String result4 = "Instance of edu.hm.AmazingClass:\n" + "0\n0\nprivateMethodWith (Type int[]) [0, 0, ]\npublicMethodWith (Type int[]) [0, 0, ]\n";


        String result = new Renderer(amazingClass).render();
        assertTrue(result1.equals(result)|| result2.equals(result)||result3.equals(result)||result4.equals(result));
        //assertEquals("Instance of edu.hm.AmazingClass:\n" + "0\n0\n", result);
    }


}