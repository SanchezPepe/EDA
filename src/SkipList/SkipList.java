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
        cont = 0;
        altura = 0;
        cabeza.setRigth(cola);
        cola.setLeft(cabeza);
        r = new Random();
    }

    public SkipList(T elem){
        this();
        if(elem != null){
            cont = 1;
            NodoS<T> nuevo = new NodoS(elem);
            nuevo.setRigth(cola);
            nuevo.setLeft(cabeza);
            cabeza.setRigth(nuevo);
            cola.setLeft(nuevo);
        }
    }

    public boolean coinFlip(){
        return r.nextBoolean();
    }

    public NodoS<T> find(T elem){
        NodoS aux = cabeza.getRigth();
        return find(aux, elem);
    }

    private NodoS<T> find(NodoS<T> aux, T elem){
        if(aux.getElem().equals(elem)){ //SI LO ENCONTRÉ
            if(aux.getDown() == null) //SI NO TIENE ABAJO
                return aux;
            else{
                while(aux.getDown() != null) //SI TIENE ABAJO
                    aux = aux.getDown();
                return aux;
            }
        }
        else{
            if(elem.compareTo(aux.getElem()) > 0 && aux.getRigth() != null) //SI ELEMENTO ES MAYOR AL NODO Y TIENE DERECHA - SE RECORRE
                return find(aux.getRigth(), elem);
            if(aux.getDown() != null) //SI TIENE ABAJO - SE RECORRE ABAJO
                return find(aux.getDown(), elem);
            else //SI ELEMENTO ES MENOR AL NODO Y NO TIENE ABAJO = NO LO ENCONTRÓ - REGRESA DONDE DEBERÍA IR
                return aux.getLeft();

        }
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

    //CREACION DE UN NUEVO NIVEL SI ARRIBA ES NULO y FLIP COIN == TRUE
    private void nuevoNivel(NodoS<T> nodo){
    }


    public void insert(T elem){
        
    }

    public static void main(String[] args) {
        Random r = new Random();
        System.out.println(r.nextBoolean());
    }
}
