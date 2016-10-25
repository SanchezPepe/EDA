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
            this.ligaH(cabeza, nuevo, cola);
        }
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
            if(aux.getElem() == null) //SI ES LA COLA
                return aux.getLeft();
            if(elem.compareTo(aux.getElem()) > 0 && aux.getRigth() != null) //SI ELEMENTO ES MAYOR AL NODO Y TIENE DERECHA - SE RECORRE
                return find(aux.getRigth(), elem);
            if(aux.getDown() != null && aux.getDown().getRigth() != null) //SI TIENE ABAJO - SE RECORRE ABAJO-AL LADO
                return find(aux.getDown().getRigth(), elem);
            else //SI ELEMENTO ES MENOR AL NODO Y NO TIENE ABAJO = NO LO ENCONTRÓ - REGRESA DONDE DEBERÍA IR
                return aux.getLeft();

        }
    }
    
    public NodoS<T> find2(T elem){
        return find2(cabeza, elem);
    }
    //http://segweb.blogspot.mx/2012/04/skiplist.html
    
    private NodoS<T> find2(NodoS<T> aux, T elem){
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
            if(elem.compareTo(aux.getRigth().getElem()) < 0){
                if(aux.getDown() == null)
                    return aux;
                else
                    return find2(aux.getDown(), elem);
            }else
                if(aux.getRigth() != null){
                    if(aux.getRigth().getElem() == null)    //LLEGÓ A LA COLA
                        return aux;
                    else
                        return find2(aux.getRigth(), elem);
                }
        }
    }
    
        
    //MÉTODOS AUXILIARES
    //VOLADO
    public boolean coinFlip(){
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
    
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println(Math.log(1));
    }
}
