/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import EDA.NodoBinario;

/**
 *
 * @author FOF
 */
public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree(T elem) {
        super(elem);
    }

    public void add(T dato) {
        if(this.cont == 0)
            this.raiz = new NodoBinario(dato);
        else
            add(this.raiz, dato);
        this.cont++;
    }
    
    private NodoBinario <T> add(NodoBinario <T> actual, T dato){
        if(actual == null)
            return new NodoBinario <>(dato);
        if(dato.compareTo(actual.getElem()) < 0)
            actual.setIzq(add(actual.getIzq(), dato));
        else
            actual.setDer(add(actual.getDer(), dato));
        return actual;
    }
    
    public T remove(T dato) {
        T res = null;
        if(cont>0){
            if(raiz.getElem().equals(dato)){
               T x = sucesorInOrden(raiz);
                if(x!=null)
                   raiz.setElem(x);
                else
                    raiz = raiz.getIzq();
                cont--;
            }else{
                //hay que recorrer hasta encontrarlo y sustituir, por sucesor en orden si lo hay
                //en caso de que no lo haya... sustituir por el izquierdo
                NodoBinario<T> act,ant;
                act=raiz;
                ant=act;
                while(act!=null && !act.getElem().equals(dato)){
                    if(dato.compareTo(act.getElem())<0){
                       ant = act;
                       act = act.getIzq();
                    }else
                       if(dato.compareTo(act.getElem())>0){
                           ant = act;
                           act = act.getDer();
                       }    
                }//la recorrida a encontrar el elemento ya está
                if(act!=null && act.getElem().equals(dato)){
                    T x = sucesorInOrden(act);
                    if(x!=null)
                        act.setElem(x);
                    else{
                        if(act == ant.getIzq())
                            ant.setIzq(null);
                        else
                            ant.setDer(null);
                    }
                    cont--;
                }
           }
        }
        return res;
    }
    
    private T sucesorInOrden(NodoBinario<T> n){
        T res = null; 
        /**
         * Este método te regresa el sucesor en orden y al mismo tiempo lo elimina del arbol
         * si regresa null, es que no hay nada a su derecha
         */
        if(n.getDer()!=null){
            NodoBinario act = n.getDer(); 
            NodoBinario ant = n;
            while(act.getIzq()!=null){
                ant = act;
                act = act.getIzq();
            }
            if(act.getDer()!=null){
                if(act==ant){//caso de que solo hay uno der y no izq
                    res = (T)act.getElem();
                    ant.setDer(act.getDer());
                }else{
                    res = (T)act.getElem();
                    if(ant!=n)
                    ant.setIzq(act.getDer());
                    else
                        ant.setDer(act.getDer());
                }
            }else{
                res = (T)act.getElem();
                if(ant!=n)
                ant.setIzq(act.getDer());
                else
                    ant.setDer(act.getDer());
            }     
        }
        return res;
    }

    public T removeMin() {
        T temp = null;
        if(!isEmpty()){
            NodoBinario <T> actual = this.raiz;
            NodoBinario <T> papa = actual;
            while(actual.getIzq() != null){
                papa = actual;
                actual = actual.getIzq();
            }
            temp = actual.getElem();
            if(actual == this.raiz){
                this.raiz = this.raiz.getDer();
            }
            else
                papa.setIzq(actual.getDer());
            this.cont--;
        }
        return temp;
    }

    public T removeMax() {
        T res = null;
        if(!isEmpty()){
            NodoBinario <T> actual = this.raiz;
            NodoBinario <T> papa = actual;
            while(actual.getDer() != null){
                papa = actual;
                actual = actual.getDer();
            }
            res = actual.getElem();
            if(actual == this.raiz){
                this.raiz = this.raiz.getIzq();
            }
            else
                papa.setDer(actual.getIzq());
            this.cont--;
        }
        return res;
    }

    public T findMin() {
        if(raiz==null)
            return null;
        NodoBinario<T> actual = raiz;
        while(actual!=null)
            actual = actual.getIzq();
        return actual.getElem();
    }

    public T findMax() {
        if(raiz==null)
            return null;
        NodoBinario<T> actual = raiz;
        while(actual.getDer()!=null)
            actual = actual.getDer();
        return actual.getElem();
    }
 
    
    //T O D O    L O    D E   A B A J O    S O N    P R U E B A S    C O M E N T A D A S
    
//    public static void main(String[] args) {
//        LinkedBinarySearchTree arb = new LinkedBinarySearchTree();
//        Integer arr[] = {10,-65,42,27,-6,-100,89,70,63,41,3,0,1,10000,-50};
//        for(int i = 0; i < arr.length; i++) {
//           arb.add(arr[i]); 
//          // arb.imprimeArbol(arb.inOrder());
//        }
//  
//        System.out.println("Ahora a borrar");
////        for (int i = 0; i < 14+1; i++) {
////            System.out.println("num elem antes borrar: "+arb.cont);
////            arb.remove(arb.raiz.getElem());
////            System.out.println("num elem despues borrar: "+arb.cont);
////            arb.imprimeArbol(arb.inOrder());
////            
////        }
////        System.out.println("Voy a borrar el 27");
////        System.out.println("Antes de borrar:");arb.imprimeArbol(arb.inOrder());
////        arb.remove(27);
////        System.out.println("Despues de borrar:");arb.imprimeArbol(arb.inOrder());
////        
////        System.out.println("Voy a borrar el -65");
////        System.out.println("Antes de borrar:");arb.imprimeArbol(arb.inOrder());
////        arb.remove(-65);
////        System.out.println("Despues de borrar:");arb.imprimeArbol(arb.inOrder());
////        
////        System.out.println("Voy a borrar el -1");
////        System.out.println("Antes de borrar:");arb.imprimeArbol(arb.inOrder());
////        arb.remove(-1);
////        System.out.println("Despues de borrar:");arb.imprimeArbol(arb.inOrder());
////        
////        System.out.println("Voy a borrar el 89");
////        System.out.println("Antes de borrar:");arb.imprimeArbol(arb.inOrder());
////        arb.remove(89);
////        System.out.println("Despues de borrar:");arb.imprimeArbol(arb.inOrder());
////        
//        System.out.println("Voy a borrar el 1");
//        System.out.println("Antes de borrar:");arb.imprimeArbol(arb.inOrder());
//        arb.remove(1);
//        System.out.println("Despues de borrar:");arb.imprimeArbol(arb.inOrder());
//        
//        
//        System.out.println("Voy a borrar el -6");
//        System.out.println("Antes de borrar:");arb.imprimeArbol(arb.inOrder());
//        arb.remove(-6);
//        System.out.println("Despues de borrar:");arb.imprimeArbol(arb.inOrder());
//        
//    }  
}