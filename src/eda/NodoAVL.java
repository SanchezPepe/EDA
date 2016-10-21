/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDA;

import EDA.NodoBinario;

/**
 *
 * @author hca
 */
public class NodoAVL<T extends Comparable <T>> extends NodoBinario<T> {
    
    private NodoAVL<T> izq, der, papa;
    private int fe;


    public NodoAVL(T dato){
        super(dato);
        fe = 0;
        this.izq = (NodoAVL<T>) super.izq;
        this.der = (NodoAVL<T>) super.der;
    }
    
    public void cuelga(NodoAVL<T> nodo){
        if(nodo == null)
            return;
        if(nodo.getElem().compareTo(elem) <= 0)
            izq = nodo;
        else
            der = nodo;
        nodo.setPapa(this);
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }
    
    public void setIzq(NodoAVL<T> nodo){
        this.izq = nodo;
    }
    
    public void setDer(NodoAVL<T> nodo){
        this.der = nodo;
    }

    public NodoAVL getPapa() {
        return papa;
    }

    public int getFe() {
        return fe;
    }
    
    public void setPapa(NodoAVL papa) {
        this.papa = papa;
    }

    public void setFe(int nuevo){
        this.fe = nuevo;
    }
    
    public int actFe(){
        if(this.izq != null && this.der != null)
            return 0;
        if(this.izq != null && this.der == null)
            return -1;
        if(this.der != null && this.izq == null)
            return 1;
        else
            return 0;
    }
    
    public static void main(String[] args) {
        NodoAVL<Integer> n = new NodoAVL(4);
        n.setDer(new NodoAVL(5));
        System.out.println(n.getDer().elem);
                
      
        
    }
    
    
}
