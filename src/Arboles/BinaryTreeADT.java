package Arboles;
import java.util.Iterator;
/**
 *
 * @author FOF
 */
public interface BinaryTreeADT<T extends Comparable<T>> {
    public boolean isEmpty();
    public int size();
    public boolean contains(T dato);
    public T find(T dato);
    public String toString();
    public Iterator <T> inOrder();
    public Iterator <T> preOrder();
    public Iterator <T> postOrder();
    public Iterator <T> levelOrder();
}
