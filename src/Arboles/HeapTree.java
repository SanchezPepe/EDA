package Arboles;

/**
 *
 * @author JSANCHEZAGU
 */
public class HeapTree<T> implements HeapTreeADT {
    
    private T tree[];
    private int heapSize;
    
    
    public HeapTree(){
        tree = (T[]) new Object[20];
    }
    
    public void expandCapacity(){
        T nuevo[] = (T[]) new Object[heapSize*2];
        for(int i = 0; i < heapSize; i++)
            nuevo[i] = tree[i];
        tree = nuevo;
    }

    @Override
    public Object removeMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addElement(Object elem) {
        if(heapSize == tree.length)
            this.expandCapacity();
        tree[heapSize] = (T)elem;
        
    }
    
}
