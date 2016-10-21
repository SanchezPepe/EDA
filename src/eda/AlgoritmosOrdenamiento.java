/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDA;

/**
 *
 * @author jsanchezagu
 */
public class AlgoritmosOrdenamiento <T extends Comparable <T>>{
    
    public void selectionSort(T arre[]){
        int min;
        for(int i = 0; i < arre.length; i++){
            min = i;
            for(int j = i+1; j < arre.length; j++){
                if(arre[min].compareTo(arre[j]) > 0)
                    min = j;
            }
            swap(arre, min, i);
        }   
    }
    
    //Insertion sort toma O(n^2) en el peor escenario
    public void insertionSort(T arre[]){
        int i, j;
        T aux;
        for(i = 1; i < arre.length; i++){
            aux = arre[i];
            j = i-1;
            while(j >= 0 && aux.compareTo(arre[j]) < 0){
                arre[j+1] = arre[j];
                j--;
            }
            arre[j+1] = aux;
        }
    }
    
    public void bubbleSort(T arre[]){
        int i, j;
        for(i = arre.length-1; i >= 0; i--){
            for(j = 0; j < i-1; j++){
                if(arre[j].compareTo(arre[j+1]) > 0)
                    swap(arre, j, j+1);
            }
        }
        
    }
    
    public void quickSort(T arre[]){
        quickSort(arre, 0, arre.length-1);
    }
    
    private void quickSort(T arre[], int min, int max){
        int pivote;
        if(min >= max)
            return;
        pivote = encuentraParticion(arre, min, max);
        quickSort(arre, min, pivote-1); //Izquierda del pivote
        quickSort(arre, pivote+1, max); //Derecha del pivote
    }
    
    public void mergeSort(T[] arre){
        mergeSort(arre, 0, arre.length-1);
    }
    
    private void mergeSort(T[] datos,int ini,int fin){
        T[] temp;
        int indice,izq,der;
        if(ini == fin)
            return;
        int tam = fin-ini+1;
        int mitad = (ini+fin)/2;
        temp=(T[])(new Comparable[tam]);
        mergeSort(datos, ini, mitad);
        mergeSort(datos, mitad+1, fin);
        izq = ini;
        der = mitad+1;
        int n = 0;
        while(izq <= mitad && der <= fin){
            if(datos[izq].compareTo(datos[der]) > 0){
                temp[n] = datos[der];
                der++;
            }
            else{
                if(datos[izq].compareTo(datos[der]) < 0){
                    temp[n] = datos[izq];
                    izq++;
                }
                else{
                    temp[n] = datos[izq];
                    izq++;
                    der++;
                }
            }
            n++;
        }
        if(izq >= mitad && der <= fin){
            for (int i = der; i <= fin; i++) {
                temp[n] = datos[i];
                n++;
            }
        }
        if(der >= fin && izq <= mitad){
            for (int i = izq; i <= mitad; i++) {
                temp[n] = datos[i];
                n++;
            }
        }
        n=0;
        for (int i = ini; i <= fin; i++) {
            datos[i] = temp[n];
            n++;
        }
    }
    
    public void shellSort(T arre[]){
        T aux;
        int salto, i;
        boolean cambios;
        for(salto = arre.length/2; salto != 0; salto/=2){
            cambios = true;
            while(cambios){ // Mientras se intercambie algún elemento
                cambios = false;
                for(i=salto; i< arre.length; i++)
                    if(arre[i-salto].compareTo(arre[i]) > 0){
                        aux = arre[i];
                        arre[i] = arre[i-salto];
                        arre[i-salto] = aux;
                        cambios = true;
                    }
            }
        }    
    }
    
    
    //Métodos Auxiliares
    private void swap(T arre[], int i, int j){
        T aux = arre[i];
        arre[i] = arre[j];
        arre[j] = aux;
    }
    
    private int encuentraParticion(T arre[], int min, int max){
        int lugar = min;
        int i = min;
        while (i <= max){
            if(i > lugar && arre[i].compareTo(arre[lugar]) <= 0){
                swap(arre, lugar, i);
                int nuevoI = lugar;
                lugar = i;
                i = nuevoI;
            }
            else{
                if(i < lugar && arre[i].compareTo(arre[lugar]) > 0){
                    swap(arre, lugar, i);
                    lugar = i;
                }
            }
            i++;
        }
        return lugar;
    }
    
    public void imprime(T arre[]){
        int i = 0;
        StringBuilder cad = new StringBuilder();
        while(i < arre.length){
            cad.append(arre[i]).append(" ");
            i++;
        }
        System.out.println(cad);
    }
    
    public static void main(String[] args) {
        AlgoritmosOrdenamiento<Integer> ao = new AlgoritmosOrdenamiento<Integer>();
        //Algoritmos
        // 1.- selectionSort
        // 2.- insertionSort
        // 3.- bubbleSort
        // 4.- quickSort
        // 5.- mergeSort
        // 6.- shellSort
        
        int algoritmo = 4;
        
        Integer arre[] = {7,1,5,3,8,2,4,6,9};
        ao.imprime(arre);
        switch(algoritmo){
            case 1:
                ao.selectionSort(arre);
                break;
            case 2:
                ao.insertionSort(arre);
                break;
            case 3:
                ao.bubbleSort(arre);
                break;
            case 4:
                ao.quickSort(arre);
                break;
            case 5:
                ao.mergeSort(arre);
                break;
            case 6:
                ao.shellSort(arre);
                break;
            default:
                System.out.println("No se seleccionó algoritmo");
        }
        ao.imprime(arre);
    }
    
}
