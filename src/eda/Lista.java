/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDA;

import java.util.Stack;


/**
 *
 * @author hca
 */
public class Lista <T>{
    
    private Nodo<T> cabeza;
    private int cont;
    
    public Lista(){
        cabeza = new Nodo<T>();
        cont = 0;
    }
    
    public void imprime(){
        Nodo<T> actual = cabeza.getSig();
        while(actual != null){
            System.out.println(actual.getElem());
            actual = actual.getSig();
        }
    }
    
    public void inserta(T elem){
        Nodo<T> nuevo = new Nodo<T>(elem);
        nuevo.setSig(cabeza.getSig());
        cabeza.setSig(nuevo);
        cont++;
    }
    
    public void invierte(){
        Nodo<T> aux = cabeza.getSig();
        Stack<Nodo> p1 = new Stack();
        while(aux != null){
            p1.push(aux);
            aux = aux.getSig();
        }
        aux = p1.pop();
        cabeza.setSig(aux);
        while(!p1.isEmpty()){
            aux.setSig(p1.pop());
            aux = aux.getSig();
        }
        aux.setSig(null);
    }
    
    public void invierteR(){
        invierteR(cabeza.getSig());
    }
    
    private Nodo<T> invierteR(Nodo<T> act){
        if(act == null)
            return cabeza.getSig();
        else{
            T var = act.getElem();
            Nodo<T> temp = invierteR(act.getSig());
            temp.setElem(var);
            return temp.getSig();
        }
    }
    
    public boolean esPal(){
        return esPal(cabeza.getSig()) == null;
    }
    
    private Nodo<T> esPal(Nodo<T> act){
        if(act == null)
            return cabeza.getSig();
        else{
            Nodo<T> temp = esPal(act.getSig());
            if(temp.getElem().equals(act.getElem()))
                return temp.getSig();
            return temp;
        }
    }
    
    public static void main(String[] args) {
        Lista<Integer> l1 = new Lista();
        int i = 0;
        while(i != 11){
            l1.inserta(i);
            i++;
        }
        l1.imprime();
        System.out.println("\n");
        l1.invierteR();
        l1.imprime();
    }
    
    
    
}
