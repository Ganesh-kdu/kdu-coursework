package question3;

import java.util.ArrayList;
import java.util.Arrays;
import com.ganesh.LogMaster;
import java.util.List;

public class question3{

    public static <T> void swap(T[] array, int index1, int index2) throws IllegalArgumentException{
        if (array == null || index1 < 0 || index1 >= array.length || index2 < 0 || index2 >= array.length || index1 == index2) {

            throw new IllegalArgumentException("Invalid indices provided");
        }

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {


        Integer[] intArray = {1, 2, 3, 4, 5};
        LogMaster.print("Int Array:");
        LogMaster.print("Original array: {}",Arrays.toString(intArray));
        swap(intArray, 2, 3);
        LogMaster.print("Array after swapping: {}", Arrays.toString(intArray));

        LogMaster.print("String Array:");
        String[] stringArray = {"a", "b", "c", "d"};
        LogMaster.print("Original array: {}", Arrays.toString(stringArray));
        swap(stringArray, 3, 4);
        LogMaster.print("Array after swapping: {}", Arrays.toString(stringArray));
    }
}