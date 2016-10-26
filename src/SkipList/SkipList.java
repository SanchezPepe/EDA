/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SkipList;

import java.util.Random;

/**
 *
 * @author hca
 */
public class SkipList<T extends Comparable<T>>{
    
    private int cont;
    private NodoS<T> cabeza, cola;
    private Random r;
    private int altura;
    
    public SkipList(){
        cabeza = new NodoS();
        cola = new NodoS();
        cont = 0;
        altura = 0;
        cabeza.setRigth(cola);
        cola.setLeft(cabeza);
        r = new Random();
    }

    public SkipList(T elem){
        altura = 0;
        r = new Random();
        if(elem != null){
            cabeza = new NodoS();
            cola = new NodoS();
            cont = 1;
            NodoS<T> nuevo = new NodoS(elem);
            this.ligaH(cabeza, nuevo, cola);
        }
    }

    public NodoS<T> find(T elem){
        return find(cabeza, elem);
    }
    
    private NodoS<T> find(NodoS<T> aux, T elem){
        if(aux.getElem() != null && aux.getElem().equals(elem)){ //SI LO ENCONTRÉ
            if(aux.getDown() == null) //SI NO TIENE ABAJO
                return aux;
            else{
                while(aux.getDown() != null) //SI TIENE ABAJO
                    aux = aux.getDown();
                return aux;
            }
        }
        else{
            if(elem.compareTo(aux.getRigth().getElem()) < 0){ //SI ES MENOR, SE BAJA
                if(aux.getDown() == null)
                    return aux;
                else
                    return find(aux.getDown(), elem);
            }else //SI ES MAYOR SE RECORRE
                if(aux.getRigth() != null && aux.getRigth().getElem() != null) //SI TIENE SIGUIENTE Y NO ES LA COLA
                    return find(aux.getRigth(), elem);
                else
                    if(aux.getDown() == null)
                        return aux;
                    else
                        return find(aux.getDown(), elem);
        }
    }
    
    public void insert(T elem){
        NodoS<T> nuevo = new NodoS(elem);
        if(cont == 0){
            ligaH(cabeza, nuevo, cola);
            return;
        }
        NodoS<T> ant = find(elem);
        ligaH(ant, nuevo, ant.getRigth());
        int nivelA = altura;
        while(flipCoin() && altura < Math.log(cont)){
            
        }
    }
   
        
    //MÉTODOS AUXILIARES
    //VOLADO
    public boolean flipCoin(){
        return r.nextBoolean();
    }
    
    //ACOMODAR APUNTADORES EN HORIZONAL
    private void ligaH(NodoS<T> izq, NodoS<T> nuevo, NodoS<T> der){
        izq.setRigth(nuevo);
        nuevo.setLeft(izq);
        nuevo.setRigth(der);
        der.setLeft(nuevo);
    }

    //ACOMODAR APUNTADORES EN VERTICAL
    private void ligaV(NodoS<T> arriba, NodoS<T> nuevo, NodoS<T> abajo){
        arriba.setDown(nuevo);
        nuevo.setUp(arriba);
        nuevo.setDown(abajo);
        abajo.setUp(nuevo);
    }

    //CREACION DE UN NUEVO NIVEL Y REASIGNA LA CABEZA Y LA COLA
    private void nuevoNivel(NodoS<T> nodo){
        NodoS<T> arriba = clona(nodo);
        arriba.setLeft(clona(cabeza));
        arriba.setRigth(clona(cola));
        cabeza = arriba.getLeft();
        cola = arriba.getRigth();
        altura++;
    }

    //CLONA EL NODO DADO Y ACOMODA APUNTADORES
    public NodoS<T> clona(NodoS<T> nodo){
        NodoS<T> nuevo = new NodoS<T>(nodo.getElem());
        nodo.setUp(nuevo);
        nuevo.setDown(nodo);
        return nuevo;
    }
    
    public NodoS<T> getNodoLista(int nivel, T elem){
        NodoS<T> aux = cabeza;
        int i = altura;
        while(i > nivel){
            aux = aux.getDown();
            i--;
        }
        aux = aux.getRigth();
        while(aux.getRigth() != null && elem.compareTo(aux.getElem()) > 0)
            aux = aux.getRigth();
        return aux.getLeft();
    }
    
    public int getAncho(){
        int res = 1;
        NodoS<T> aux = cabeza;
        while(aux.getDown() != null)
            aux = aux.getDown();
        while(aux.getRigth() != null){
            res++;
            aux = aux.getRigth();
        }
        return res;
    }
    
    public String imprime(){
        StringBuilder cad = new StringBuilder();
        NodoS<T> aux = cabeza;
        while(aux != null){
            cad.append(aux.getElem());
        }
        T lamina[][] = (T[][]) new Object[this.getAncho()][altura];
        
        
        return cad.toString();
    }
    
    public static void main(String[] args) {
        SkipList<Integer> l = new SkipList(3);
        System.out.println(l.getAncho());
    }
}
