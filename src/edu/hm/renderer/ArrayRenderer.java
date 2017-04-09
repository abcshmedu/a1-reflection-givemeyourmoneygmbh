package edu.hm.renderer;

/**
 * Created by ivi on 4/7/2017.
 */
public class ArrayRenderer {


    /**
     * @param value IntegerArray.
     * @return String -
     */
    public String render(int[] value) {
        StringBuilder result = new StringBuilder();

        result.append("[");
        for (int i = 0; i < value.length; i++) {
            result.append(String.format("%d, ", value[i]));
        }

        result.append("]");
        return result.toString();
    }

}
