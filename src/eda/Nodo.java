/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDA;

/**
 *
 * @author hca
 */
public class Nodo <T> {
    
    private T elem;
    private Nodo<T> sig;
    
    public Nodo(){
        sig = null;
    }
    
    public Nodo(T elem){
        this.elem = elem;
        sig = null;
    }
    
    public T getElem(){
        return elem;
    }
    
    public Nodo<T> getSig(){
        return sig;
    }
    
    public void setSig(Nodo<T> nuevo){
        sig = nuevo;
    }    
    
    public void setElem(T elem){
        this.elem = elem;
    }
    
}
