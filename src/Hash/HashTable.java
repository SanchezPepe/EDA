/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import EDA.Nodo;

/**
 *
 * @author hca
 */
public class HashTable<T> {
    
    public int cont;
    public Nodo<T> tabla[];
    public double factorDeCarga;
    
    public HashTable(){
        factorDeCarga = 0.65;
        this.tabla = (Nodo<T>[]) new Object[10];
        cont = 0;
    }
    
    public HashTable(double fc){
        this();
        this.factorDeCarga = fc;
    }
    
    public void expand(){
        Nodo<T> nuevo[] = (Nodo<T>[]) new Object[tabla.length*2];
        Nodo<T> actual, ant;
        for(int i = 0; i < tabla.length; i++){
            actual = tabla[i];
            while(actual != null){
                add(actual.getElem());
                actual = actual.getSig();
            }
        }
        tabla = nuevo;
    }
    
    public void add(T elem){
        if(cont >= this.factorDeCarga * tabla.length)
            expand();
        int index = hash(elem) % tabla.length;
        Nodo<T> nodo = new Nodo<T>(elem);
        nodo.setSig(nodo);
        cont++;
    }
    
}
