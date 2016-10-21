package Arboles;
/**
 *
 * @author FOF
 */
public interface BinarySearchTreeADT<T extends Comparable<T>>{
    public void add(T dato);
    public T remove(T dato);
    public T removeMin();
    public T removeMax();
    public T findMin();
    public T findMax();
}
