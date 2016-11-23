/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author sdist
 */
public class NodoG<T> {
    
    private T elem;
    private int index;
    private double ponderancia;
    private NodoG<T> sig;
            
    public NodoG(T elem, double pond, int i){
        this.elem = elem;
        this.ponderancia = pond;
        sig = null;
        index = i;
    }
    
    public T getElem(){
        return this.elem;
    }

    public NodoG getSig(){
        return this.sig;
    }
    
    public void setSig(NodoG<T> nvo){
        this.sig = nvo;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public double getPond(){
        return this.ponderancia;
    }
}
