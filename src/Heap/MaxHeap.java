package Heap;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author JSANCHEZAGU
 */
public class MaxHeap<T extends Comparable <T>>{
    
    private T tree[];
    private int heapSize;
    
    
    public MaxHeap(){
        tree = (T[]) new Comparable[20];
        heapSize = 0;
    }

    public void addElement(T elem){
        this.heapSize++;
        if(heapSize == tree.length)
            this.expandCapacity();
        tree[heapSize] = elem;
        int act = heapSize;
        while(act/2 >= 1){
            if(elem.compareTo(tree[act/2]) > 0)
                swap(act, act/2);
            else
                break;
            act = act/2;
        }
    }
    
    //MAX-HEAPIFY
    public void adjust(int index){
        int izq = index*2;
        int der = (index*2)+1;
        int grande;
        if(izq <= heapSize && der <= heapSize && tree[izq].compareTo(tree[der]) > 0)
            grande = izq;
        else
            grande = index;
        if(der <= heapSize && tree[der].compareTo(tree[grande]) > 0)
            grande = der;
        if(grande != index && tree[grande].compareTo(tree[index]) > 0){
            swap(index, grande);
            adjust(grande);
        }else{
            if(izq <= heapSize && tree[izq].compareTo(tree[index]) > 0)
                swap(izq, index);
        }
    
    }
    
    public void sort(){
        T sort[] = (T[]) new Comparable[heapSize+1];
        for(int i = heapSize; i >= 1; i--){
            sort[i] = tree[1];      //SE ELIMINA LA RAÍZ Y SE PASA AL SORTED
            swap(1, heapSize);
            tree[heapSize] = null;
            heapSize--;
            adjust(1);
        }
        this.printArray(sort);
    }
    
    //MÉTODOS AUXILIARES
    public void expandCapacity(){
        T nuevo[] = (T[]) new Comparable[heapSize*2];
        for(int i = 0; i < heapSize; i++)
            nuevo[i] = tree[i];
        tree = nuevo;
    }
    
    public void swap(int i, int j){
        T aux = tree[i];
        tree[i] = tree[j];
        tree[j] = aux;
    }
    
    public void printTree(){
        StringBuilder cad = new StringBuilder();
        cad.append("[");
        int i = 1;
        while(i <= heapSize){
            cad.append(tree[i]).append(", ");
            i++;
        }
        cad.delete(cad.length()-2, cad.length()).append("]\n");
        
        System.out.println("Heap-Build (Size: " + (this.heapSize)  + "):\n" + cad.toString());
    }
    
    public void printArray(T arre[]){
        StringBuilder cad = new StringBuilder();
        cad.append("[");
        int i = 1;
        while(i < arre.length){
            cad.append(arre[i]).append(", ");
            i++;
        }
        cad.delete(cad.length()-2, cad.length()).append("]\n");
        
        System.out.println("Arreglo (Size: " + (arre.length-1)  + "):\n" + cad.toString());
    }
    
    public static void main(String[] args) {
        MaxHeap<Integer> h = new MaxHeap();
        int tam = 55;
        boolean select = true; //ALEATORIA(TRUE) || DEFINIDA(FALSE)
        Integer arre[] = {null,5,0,8,10,2,3,11,1,9,7};
        
        
        if(select){
            Random r = new Random();
            ArrayList<Integer> l = new ArrayList();
            arre = new Integer[tam];          
            int i = 0, aux;
            while(i < arre.length){
                aux = r.nextInt(tam*2);
                if(!l.contains(aux)){
                    h.addElement(aux);
                    l.add(aux);
                }
                i++;
            }
            System.out.println("A insertar:\n" + l.toString() + "\n");
        }else{
            int i = 1;
            while(i < arre.length){
                h.addElement(arre[i]);
                i++;
            }
            //INICIAL
            System.out.println("A insertar");
            h.printArray(arre);
        }
        //IMPRESIÓN
        h.printTree();
        
        //HEAPSORT
        System.out.println("Sorted:");
        h.sort();
        
    }
}
