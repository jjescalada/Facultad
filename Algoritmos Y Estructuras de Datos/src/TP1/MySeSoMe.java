package TP1;

import java.lang.reflect.Array;

public class MySeSoMe<T extends Comparable<? super T>> {
    //1- modificar busquedaBin
    public int busquedaBin(T a[], T k){
        int min = 0;
        int max = a.length-1;
        int medio = (min+max)/2;
        while(min <= max) {

            if (k.compareTo(a[medio])== 0)
                return medio;
            else if (k.compareTo(a[medio]) < 0)
                max = medio-1;
            else
                min = medio +1;
            medio = (min+max)/2;
        }
        return -1;
    }

    //1- busquedaSecuencial
    public int busquedaSec(T a[], T k) {
        int i=0;
        for (T t: a) {
            if (a[i].equals(k)) {
                return i;
            }
            i++;
        }
        return -1;
    }
//metodos de ordenamiento de Strings (exactamente igual a los genericos, pero con String en lugar de T
    public void selectionSortString(String a[]) {
        int myCtr = 0;
        String min;
        int imin;
        for(int i =0; i < a.length-1; i++) {
            min = a[i];
            imin = i;
            myCtr+=2;
            for(int j = i+1 ; j<a.length; j++) {
                if (a[j].compareTo(min)<0) {
                    min = a[j];
                    imin = j;
                    myCtr+=2;
                }
            }
            a[imin] = a[i];
            a[i] = min;
            myCtr+=2;
        }
        System.out.println("Selection sort realizo "+myCtr+" operaciones para ordenar "+a.length+" elementos.");
    }

    public void bubbleSortString(String a[]){
        int k;
        int myCtr = 0;
        String temp;
        for(int i = 0; i < a.length-1 ; i++) {
            for(int j = 0; j < a.length-1; j++) {
                k = j+1;
                if (a[j].compareTo(a[k]) > 0) {
                    temp = a[j];
                    a[j] = a[k];
                    a[k] = temp;
                    myCtr+=4;
                }
            }
        }
        System.out.println("Bubble sort realizo "+myCtr+" operaciones para ordenar "+a.length+" elementos.");
    }

    public void insertionSortString(String a[]){
        int myCtr = 0;
        for (int i = 1; i<a.length; i++) {
            String temp = a[i];
            myCtr++;
            int j = i-1;
            myCtr++;
            while (j >= 0 && a[j].compareTo(temp) > 0) {
                a[j+1] = a[j];
                j = j-1;
                myCtr=myCtr+4;
            }
            a[j+1] = temp;
            myCtr++;
        }
        System.out.println("Insertion sort realizo "+myCtr+" operaciones para ordenar "+a.length+" elementos.");
    }

    // metodos genericos
    // por algun motivo, la complejidad promedio de este selectSort me da O(n*(log n)), es posible que falten contar operaciones porque deberia ser O(n*n)
    public void selectionSort(T a[]) {
        int myCtr = 0;
        T min;
        int imin;
        for(int i =0; i < a.length-1; i++) {
            min = a[i];
            imin = i;
            myCtr+=2;
            for(int j = i+1 ; j<a.length; j++) {
                if (a[j].compareTo(min)<0) {
                    min = a[j];
                    imin = j;
                    myCtr+=2;
                }
            }
            a[imin] = a[i];
            a[i] = min;
            myCtr+=2;
        }
        System.out.println("Selection sort realizo "+myCtr+" operaciones para ordenar "+a.length+" elementos.");
    }

    public void bubbleSort(T a[]){
        int k;
        int myCtr = 0;
        T temp;
        for(int i = 0; i < a.length-1 ; i++) {
            for(int j = 0; j < a.length-1; j++) {
                k = j+1;
                if (a[j].compareTo(a[k]) > 0) {
                    temp = a[j];
                    a[j] = a[k];
                    a[k] = temp;
                    myCtr+=4;
                }
            }
        }
        System.out.println("Bubble sort realizo "+myCtr+" operaciones para ordenar "+a.length+" elementos.");
    }

    public void insertionSort(T a[]){
        int myCtr = 0;
        for (int i = 1; i<a.length; i++) {
            T temp = a[i];
            myCtr++;
            int j = i-1;
            myCtr++;
            while (j >= 0 && a[j].compareTo(temp) > 0) {
                a[j+1] = a[j];
                j = j-1;
                myCtr=myCtr+4;
            }
            a[j+1] = temp;
            myCtr++;
        }
        System.out.println("Insertion sort realizo "+myCtr+" operaciones para ordenar "+a.length+" elementos.");
    }

//reemplazo un for con recursion, por eso debe conocer el indice de comienzo, que sera proporcionado al llamar el metodo
    public T[] recursiveSelectSort(T a[],int startIndex){
        if (startIndex == a.length) return a;
        T min;
        int imin;
        min = a[startIndex];
        imin = startIndex;
        for(int j = startIndex+1; j<a.length; j++) {
            if (a[j].compareTo(min) < 0) {
                min = a[j];
                imin = j;
            }
        }
        if (imin != startIndex) {
            a[imin] = a[startIndex];
            a[startIndex] = min;
        }
        return recursiveSelectSort(a,startIndex+1);
    }

    public T[] merge(T a[], T b[], Class<T> myClass) {
        //workaround para poder asignar el T[] retornado sin recibir ClassCastException al correr los tests
        //se podria hacer lo mismo mediante casteo, pero no se puede asignar el valor retornado a otra variable (si se puede operar directamente, pero el algoritmo estaria incompleto si funcionara condicionalmente)
       @SuppressWarnings("unchecked")
        T[] newArray = (T[])Array.newInstance(myClass,a.length + b.length);
       int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) < 0)  {
                newArray[k] = a[i];
                i++;
            }
            else {
                newArray[k] = b[j];
                j++;
            }
            k++;
        }
        while (i < a.length) {
            newArray[k] = a[i];
            i++;
            k++;
        }
        while (j < b.length) {
            newArray[k] = b[j];
            j++;
            k++;
        }
        for (int n = 0; n<newArray.length ; n++) {
            newArray[n] = (T)newArray[n];
        }
        return newArray;
    }

//metodo de ejemplo del campus
    public void seleccion(int a[]){
        int min;
        int imin;
        for(int i =0; i < a.length-1; i ++){
            min =a[i];
            imin = i;
            for (int j = i+1; j < a.length; j++){
                if (a[j] < min){
                    min = a[j];
                    imin = j;
                }
            }
            a[imin]=a[i];
            a[i]= min;
        }
    }
//metodo de ejemplo del campus
    public void seleccion(String a[]){
        String min;
        int imin;
        for(int i =0; i < a.length-1; i ++){
            min =a[i];
            imin = i;
            for (int j = i+1; j < a.length; j++){
                if (a[j].compareTo(min) < 0){
                    min = a[j];
                    imin = j;
                }
            }
            a[imin]=a[i];
            a[i]= min;
        }
    }
//metodo de ejemplo del campus
    public void seleccion(Object a[]){
        Object min;
        int imin;
        for(int i =0; i < a.length-1; i ++){
            min =a[i];
            imin = i;
            for (int j = i+1; j < a.length; j++){
                if (((Comparable) a[j]).compareTo(min) < 0){
                    min = a[j];
                    imin = j;
                }
            }
            a[imin]=a[i];
            a[i]= min;
        }
    }

    //genera un array de enteros aleatorios entre 0 y 1000
    public Integer[] makeRandomIntArray(int size) {
        Integer a[] = new Integer[size];
        for(int i = 0; i<size ; i++){
            a[i] = (int)(Math.random()*1000);
        }
        return a;
    }
}
