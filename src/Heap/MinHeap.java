package Heap;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author JSANCHEZAGU
 */
public class MinHeap<T extends Comparable <T>>{
    
    private T tree[];
    private int heapSize;
    
    
    public MinHeap(){
        tree = (T[]) new Comparable[20];
        heapSize = 0;
    }
    
    public T HeapMinimum(){
        return tree[1];
    }
    
    public void BuildMinHeap(T elem){
        this.heapSize++;
        if(heapSize == tree.length)
            this.expandCapacity();
        tree[heapSize] = elem;
        int act = heapSize;
        while(act/2 >= 1){
            if(elem.compareTo(tree[act/2]) < 0)
                swap(act, act/2);
            else
                break;
            act = act/2;
        }
    }
    
    public void MinHeapify(int index){
        int izq = index*2;
        int der = (index*2)+1;
        int chico;
        if(izq <= heapSize && der <= heapSize && tree[izq].compareTo(tree[der]) < 0)
            chico = izq;
        else
            chico = index;
        if(der <= heapSize && tree[der].compareTo(tree[chico]) < 0)
            chico = der;
        if(chico != index && tree[chico].compareTo(tree[index]) < 0){
            swap(index, chico);
            MinHeapify(chico);
        }else{
            if(izq <= heapSize && tree[izq].compareTo(tree[index]) < 0)
                swap(izq, index);
        }
    }
    
    public void HeapSort(){
        T sort[] = (T[]) new Comparable[heapSize+1];
        int num = heapSize;
        for(int i = 1; i <= num; i++){
            sort[i] = tree[1];      //SE ELIMINA LA RAÍZ Y SE PASA AL SORTED
            swap(1, heapSize);
            tree[heapSize] = null;
            heapSize--;
            MinHeapify(1);
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
        MinHeap<Integer> h = new MinHeap();
        int tam = 25;
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
                    h.BuildMinHeap(aux);
                    l.add(aux);
                }
                i++;
            }
            System.out.println("A insertar:\n" + l.toString() + "\n");
        }else{
            int i = 1;
            while(i < arre.length){
                h.BuildMinHeap(arre[i]);
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
        h.HeapSort();
        
    }
}
