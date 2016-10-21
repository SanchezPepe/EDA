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
public class NodoBinario <T extends Comparable>{
    
    protected T elem;
    protected NodoBinario<T> izq;
    protected NodoBinario<T> der;
    
    public NodoBinario(T obj){
        this.elem = obj;
        izq = null;
        der = null;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoBinario<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoBinario<T> izq) {
        this.izq = izq;
    }

    public NodoBinario<T> getDer() {
        return der;
    }

    public void setDer(NodoBinario<T> der) {
        this.der = der;
    }
    
    //NÚMERO DE NODOS BAJO ÉL. NO SOLO LOS HIJOS DIRECTOS
    public int numDescendientes(){
        int cont = 0;
        if(izq != null){
            cont++;
            cont = 1 + numDescendientes();
        }
        if(der != null){
            cont = 1 + der.numDescendientes();
        }
        return cont;              
    }
    
    public void cuelga(NodoBinario<T> nodo){
        if(nodo == null)
            return;
        if(nodo.getElem().compareTo(elem) <= 0)
            izq = nodo;
        else
            der = nodo;
    }
    
    public int getAltura(){
        if(izq == null && der == null)
            return 0;
        int altIzq = 0;
        int altDer = 0;
        if(izq != null)
            altIzq = izq.getAltura() + 1;
        if(der != null)
            altDer = der.getAltura() + 1;
        return Math.max(altIzq, altDer);
    }
    
    public static void main(String[] args) {
        NodoBinario<Integer> n = new NodoBinario(8);
        n.cuelga(new NodoBinario(4));
        System.out.println(n.getAltura());
        
        
    }
}
