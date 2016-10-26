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
    
    public void insertN(T elem){
        NodoS<T> ant, nuevo, ai, ad;
        if(elem != null){
            nuevo = new NodoS(elem);
            ant = find(elem);
            //System.out.println("IZQUIERDA: " + ant.getElem() + "\nCENTRO: " + nuevo.getElem() + "\nDERECHA: " + ant.getRigth().getElem());
            this.ligaH(ant, nuevo , ant.getRigth());
            cont++;
            boolean res = flipCoin();
            //System.out.println(res);
            while(res && altura < Math.log(cont)){
                ai = findUpL(nuevo);
                ad = findUpR(nuevo);
                //System.out.println("ENTRO NUEVA LINEA CON: " + ai.getElem()+ " " + ad.getElem());
                this.ligaH(ai, cloneUp(nuevo), ad);
                //System.out.println("NUEVA LIGA: " + ai.getElem()+ " " + ai.getRigth().getElem()+ " " + ad.getElem());
                nuevo = nuevo.getUp();
                res = flipCoin();
            }
        }
    }
    
    public void borra(T elem){
        NodoS<T> busq = this.find(elem);
        if(busq.getElem().equals(elem)){
            if(busq.getUp() == null)
                desliga(busq);
            while(busq.getUp() != null){
                desliga(busq);
                busq = busq.getUp();
            }
        }else
            System.out.println("El elemento: '" + elem + "' no está en la estrucura");
    }
    
    //MÉTODOS AUXILIARES
    public void desliga(NodoS<T> nodo){
        NodoS<T> izq = nodo.getLeft();
        NodoS<T> der = nodo.getRigth();
        izq.setRigth(der);
        der.setLeft(izq);
        nodo.setLeft(null);
        nodo.setRigth(null);
    }
    
    public NodoS<T> findUpR(NodoS<T> nodo){
        NodoS<T> aux = nodo;
        while(aux.getRigth() != null){
            aux = aux.getRigth();
            if(aux.getUp() != null)
                return aux.getUp();
        }
        return cloneUp(aux);
    }
    
    public NodoS<T> findUpL(NodoS<T> nodo){
        NodoS<T> aux = nodo;
        while(aux.getLeft() != null){
            aux = aux.getLeft();
            if(aux.getUp() != null)
                return aux.getUp();
        }
        return cloneUp(aux);
    }
    
    public NodoS<T> cloneUp(NodoS<T> nodo){
        NodoS<T> up = new NodoS(nodo.getElem());
        nodo.setUp(up);
        up.setDown(nodo);
        if(nodo == cabeza){
            cabeza = up;
            altura++;
        }
            
        if(nodo == cola)
            cola = up;
        return up;
    }
    
    public boolean flipCoin(){
        return r.nextBoolean();
    }
    
    public boolean esCola(NodoS<T> nodo){
        return nodo.getRigth() == null && nodo.getElem() == null;
    }
    
//ACOMODAR APUNTADORES EN HORIZONAL
    private void ligaH(NodoS<T> izq, NodoS<T> nuevo, NodoS<T> der){
        izq.setRigth(nuevo);
        nuevo.setLeft(izq);
        nuevo.setRigth(der);
        der.setLeft(nuevo);
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
    
    public String imp(){
        int ancho = this.getAncho();
        StringBuilder cad = new StringBuilder();
        NodoS<T> aux = cabeza;
        NodoS<T> aux2 = cabeza;
        while(aux.getDown() != null){
            aux = aux.getDown();
            aux2 = aux2.getDown();
        }
        for(int i = 0; i < ancho; i++){
            for(int j = 0; j <= altura; j++){
                if(aux.getElem() == null)
                    cad.append("(" + aux.getElem() + ")").append("---->");
                else
                    cad.append("( " + aux.getElem() + "  )").append("--->");
                if(aux.getUp() == null)
                    break;
                else
                    aux = aux.getUp();
            }
            cad.append("\n");
            if(aux2.getRigth() != null){
                aux = aux2.getRigth();
                aux2 = aux2.getRigth();
            }
        }
        return cad.toString();
    }
    
    public static void main(String[] args) {
        SkipList<Integer> l = new SkipList();
        //INSERCIÓN
        for(int i = 0; i < 10; i++)
            l.insertN(i);
        System.out.println("Número de elementos: " + l.cont + "\nAltura: " + l.altura + "\nImpresión:\n"+ l.imp());
        
        //BORRADO
        l.borra(8);
        l.borra(1);
        
        System.out.println("Número de elementos: " + l.cont + "\nAltura: " + l.altura + "\nImpresión:\n"+ l.imp());
        
    }
}
