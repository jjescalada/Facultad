package TP1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SeSoMeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
    //Test de metodos genericos
        MySeSoMe<Integer> numberTester = new MySeSoMe<>();
        Integer arr1[] = numberTester.makeRandomIntArray(10);
        Integer arr2[] = numberTester.makeRandomIntArray(100);
        Integer arr3[] = numberTester.makeRandomIntArray(1000);
        Integer arr4[] = numberTester.makeRandomIntArray(10000);

        numberTester.recursiveSelectSort(arr1,0);
        for (int i = 0; i<arr1.length; i++) {
            System.out.print(arr1[i]+"; ");
        }
        System.out.print("\n");

        numberTester.bubbleSort(arr2);
        numberTester.insertionSort(arr3);
        numberTester.selectionSort(arr4);

        String str1[] = new String[3];
        String str2[] = new String[3];

        str1[0] = "abc";
        str1[1] = "ghi";
        str1[2] = "mno";
        //str1: {"abc";"ghi";"mno"}

        str2[0] = "def";
        str2[1] = "jkl";
        str2[2] = "pqr";
        //str2: {"def";"jkl";"pqr"}

        MySeSoMe<String> stringTester = new MySeSoMe<>();
        String newStr[] = stringTester.merge(str1,str2,String.class); //paso la clase porque hice un workaround con Array.newInstance que la requiere
        for (int i = 0; i<newStr.length; i++) {
            System.out.print(newStr[i]+"; ");
        }

    }



}