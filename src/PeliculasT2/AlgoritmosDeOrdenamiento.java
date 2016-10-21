package PeliculasT2;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author jsanchezagu
 */
public class AlgoritmosDeOrdenamiento <T extends Comparable <T>> {
    
    public int numComp = 0;
    
    public AlgoritmosDeOrdenamiento(){    
    }
    
    //Algoritmos de Ordenamiento
    public void insercionDirecta(T arre[]){
        numComp = 0;
        int p, j;
        T aux;
        for (p = 1; p < arre.length; p++){
            aux = arre[p];
            j = p - 1;
            numComp++;
            while (j >= 0 && arre[j].compareTo(aux) > 0){
                
                arre[j + 1] = arre[j];
                j--;
            }
            arre[j+1] = aux;
        }
    }
    
    public void bubbleSort(T arre[]){
        numComp = 0;
        int pos, j;
        for(pos = arre.length-1; pos >= 0; pos--)
            for(j = 0; j <= pos-1 ; j++){
                numComp++;
                if(arre[j].compareTo(arre[j+1]) > 0){
                    
                    swap(arre, j, j+1);
                }    
            }
    }
    
    public void quickSort(T[] datos){
        quickSort(datos, 0, datos.length-1); 
    }
    
    private void quickSort(T[] datos, int min, int max){
        int particion;
        if(min >= max){
            return;
        }
        particion = encuentraParticion(datos, min, max);
        quickSort(datos, min, particion-1);
        quickSort(datos, particion+1, max);
        
    }
    
    public void mergeSort(T[] datos){
        mergeSort(datos, 0, datos.length-1); 
    }
    
    public void mergeSort(T[] datos,int ini,int fin){
        numComp = 0;
        T[] temp;
        int indice,izq,der;
        if(ini == fin)
            return;
        int tam = fin-ini+1;
        int mitad = (ini+fin)/2;
        temp =(T[])(new Comparable[tam]);
        mergeSort(datos, ini, mitad);
        mergeSort(datos, mitad+1, fin);
        izq = ini;
        der = mitad+1;
        int n = 0;
        while(izq <= mitad && der <= fin){
            numComp++;
            if(datos[izq].compareTo(datos[der]) > 0){
                temp[n] = datos[der];
                der++; 
            }
            else{
                numComp++;
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
        //http://puntocomnoesunlenguaje.blogspot.mx/2014/09/metodo-shell-de-ordenacion.html
        numComp = 0;
        T aux;
        int salto, i;
        boolean cambios;
        for(salto = arre.length/2; salto != 0; salto/=2){
            cambios = true;
            while(cambios){ // Mientras se intercambie algún elemento
                cambios = false;
                for(i=salto; i< arre.length; i++){
                    numComp++;
                    if(arre[i-salto].compareTo(arre[i]) > 0){
                        aux = arre[i];
                        arre[i] = arre[i-salto];
                        arre[i-salto] = aux;
                        cambios = true;
                    }
                }    
            }
        }    
    }
    
    //Métodos Auxiliares
    private void swap(T[] arre, int i, int j) {
        T temp = arre[i];
        arre[i] = arre[j];
        arre[j] = temp;
    }
    
    private int encuentraParticion(T[] datos, int min, int max){
        int lugar = min;
        int i = min;
        while (i <= max){
            this.numComp++;
            if(datos[i].compareTo(datos[lugar])<=0&&i>lugar){
                swap(datos, lugar, i);
                int nuevoI = lugar;
                lugar = i;
                i = nuevoI;
            }
            else{
                this.numComp++;
                if(datos[i].compareTo(datos[lugar])>0&&i<lugar){
                    swap(datos,lugar,i);
                    lugar=i;
                }
                i++;
            }
        }
        return lugar;
    }
    
    public void impA(T arre[]){
        int i = 0;
        StringBuilder cad = new StringBuilder();
        while(i < arre.length){
            cad.append(arre[i]).append(" ");
            i++;
        }    
        System.out.println(cad);
    }
    
    public void random(T arre[]){
        Random r = new Random();
        int n = 0, j = 0;
        T aux;
        for(int i = 0; i < arre.length; i++){
            n = r.nextInt(arre.length);
            aux = arre[i];
            arre[i] = arre[n];
            arre[n] = aux;
        }
    }
    
    public T[] invert(T[] n) {
        T aux;
        for (int i = 0; i < n.length / 2; i++) {
            aux = n[i];
            n[i] = n[n.length - 1 - i];
            n[n.length - 1 - i] = aux;
        }
 
    return n;
}
    
    public static void main(String[] args) {
        AlgoritmosDeOrdenamiento<Integer> o = new AlgoritmosDeOrdenamiento<Integer>();
        Integer arre[] = {1,2,3,4,5,6};
        o.impA(arre);
        o.shellSort(arre);
        o.impA(arre);
        System.out.println("Comparaciones: " + o.numComp);
        o.impA(o.invert(arre));
        
    }
}
