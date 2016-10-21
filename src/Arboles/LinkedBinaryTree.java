package Arboles;

import EDA.NodoBinario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author FOF
 */
public class LinkedBinaryTree<T extends Comparable<T>> implements BinaryTreeADT{
    protected int cont;
    protected NodoBinario<T> raiz;

    public LinkedBinaryTree() {
        this.raiz = null;
        this.cont = 0;
    }

    public LinkedBinaryTree(T elem) {
        this.raiz = new NodoBinario(elem);
        this.cont = 1;
    }
    
    public boolean isEmpty() {
       return cont == 0;
    }

    public int size() {
        return cont;
    }

    public boolean contains(Comparable dato) {
        return contains(dato,raiz);
    }
    
    private boolean contains(Comparable dato, NodoBinario raiz){
        if(raiz.getElem().equals(dato))
            return true;
        if(raiz.equals(null))
            return false;
        else
            if(dato.compareTo(raiz.getElem())<0)
                return contains(dato,raiz.getIzq());
            else
                return contains(dato,raiz.getDer());
    }

    public Comparable find(Comparable dato) {
        return (Comparable)find(dato,raiz);
    }
    
    private NodoBinario<T> find(Comparable dato, NodoBinario raiz){
        if(raiz.getElem().equals(dato))
            return raiz;
        if(raiz.equals(null))
            return null;
        else
            if(dato.compareTo(raiz.getElem())<0)
                return find(dato,raiz.getIzq());
            else
                return find(dato,raiz.getDer());
    }

    public Iterator<T> inOrder() {
        ArrayList <T> lista = new ArrayList <>();
        inOrder(raiz, lista);
        return lista.iterator();
    }

    private void inOrder(NodoBinario <T> nodo, ArrayList <T> lista){
        if(nodo == null)
            return;
        inOrder(nodo.getIzq(), lista);
        lista.add(nodo.getElem());
        inOrder(nodo.getDer(), lista);
    }
    
    public Iterator<T> preOrder() {
        ArrayList <T> lista = new ArrayList <>();
        preOrder(raiz, lista);
        return lista.iterator();
    }
    
    private void preOrder(NodoBinario <T> nodo, ArrayList <T> lista){
        if(nodo == null)
            return;
        lista.add(nodo.getElem());
        preOrder(nodo.getIzq(), lista);
        preOrder(nodo.getDer(), lista);
    }

    public Iterator<T> postOrder() {
        ArrayList <T> lista = new ArrayList <>();
        postOrder(raiz, lista);
        return lista.iterator();
    }
    
    private void postOrder(NodoBinario <T> nodo, ArrayList <T> lista){
        if(nodo == null)
            return;
        postOrder(nodo.getIzq(), lista);
        postOrder(nodo.getDer(), lista);
        lista.add(nodo.getElem());
    }

    public Iterator<T> levelOrder() {
        ArrayList <T> lista = new ArrayList <>();
        levelOrder(raiz, lista);
        return lista.iterator();
    }
    
    private void levelOrder(NodoBinario<T> actual, ArrayList<T> lista) {
        Queue <NodoBinario <T>> cola = new LinkedList();
        cola.add(actual);
        
        while(!cola.isEmpty()){
            NodoBinario <T> temp = cola.remove();
            lista.add(temp.getElem());
            if(temp.getIzq() != null)
                cola.add(temp.getIzq());
            if(temp.getDer() != null)
                cola.add(temp.getDer());
        }
    }
    
    public void imprimeArbol(Iterator <T> it){
        if(it != null){
            String resp = "";
            
            while(it.hasNext())
                resp += it.next() + ", ";
            if(resp.length() != 0)
                resp = resp.substring(0, resp.length() - 2);
            System.out.println(resp);
        }
    }
}