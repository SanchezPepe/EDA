/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SkipList;

import java.lang.reflect.Array;
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
    
    public NodoS<T> findj(NodoS<T> aux, T elem){
        if(elem.compareTo(aux.getRigth().getElem()) > 0){
            return find(aux.getRigth(), elem);
        }
        if(elem.compareTo(aux.getRigth().getElem()) < 0){
            if(aux.getDown() != null)
                return find(aux.getDown(), elem);
            else
                return aux;
        }
        if(elem.equals(aux.getElem()))
            while(aux.getDown() != null)
                aux = aux.getDown();
        return aux;
        
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
        if(esCola(aux.getRigth())){ //SI EL DERECHO ES COLA
            if(aux.getDown() == null)
                return aux;
            else
                return find(aux.getDown(), elem);
        }else{
            if(elem.compareTo(aux.getRigth().getElem()) < 0){ //SI ES MENOR, SE BAJA
                if(aux.getDown() == null)
                    return aux;
                else
                    return find(aux.getDown(), elem);
            }else //SI ES MAYOR SE RECORRE
                return find(aux.getRigth(), elem);
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
    
    public boolean esCabeza(NodoS<T> nodo){
        return nodo.getLeft() == null && nodo.getElem() == null;
    }
    
    public boolean esCola(NodoS<T> nodo){
        return nodo.getRigth() == null && nodo.getElem() == null;
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
        while(aux.getDown() != null)
            aux = aux.getDown(); //ENCONTRAR LA CABEZA DE LA LISTA CON ALTURA 0
        int ancho = this.getAncho();
        System.out.println(ancho);
        if(altura == 0){
            aux = cabeza;
            T[] lista = (T[]) new Object();
            for(int i = 0; i < lista.length; i++){
                lista[i] = aux.getElem();
                System.out.println(lista[i]);
                aux = aux.getRigth();
                
            }
        }
        else{
            T lamina[][] = (T[][]) new Object[ancho][altura];
            for(int i = 0; i <= ancho; i++){
                for(int j = 0; j <= altura; j ++){
                    lamina[i][j] = aux.getElem();
                    if(aux.getUp() == null){
                        while(j <= altura){
                            lamina[i][j] = null;
                            j++;
                        }
                    }else
                        aux = aux.getUp();
                }
            }
            for(int k = altura; k > 0; k--){
                for(int m = 0; m <= ancho; m++){
                    cad.append(lamina[m][k]).append("-");
                }
                cad.append("\n");
            }
        }
        return cad.toString();
    }
    
    public static void main(String[] args) {
        SkipList<Integer> l = new SkipList(49);
        System.out.println(l.find(50).getElem());
        System.out.println(l.esCabeza(l.find(50)));
        System.out.println(l.cont);
        
        
        System.out.println(l.imprime());
    }
}
