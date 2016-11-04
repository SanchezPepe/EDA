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
 * @param <T>
 */
public class SkipList<T extends Comparable<T>>{
    
    private int size;
    private NodoS<T> cabeza, cola;
    private Random rand;
    private int altura;
    
    public SkipList(){
        cabeza = new NodoS();
        cola = new NodoS();
        size = 0;
        altura = 0;
        cabeza.setRigth(cola);
        cola.setLeft(cabeza);
        rand = new Random();
    }

    public SkipList(T elem){
        altura = 0;
        rand = new Random();
        if(elem != null){
            cabeza = new NodoS();
            cola = new NodoS();
            size = 1;
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
    
    public void lista(){
       this.altura = 0;
        while(cabeza.getDown() != null){
            cabeza = cabeza.getDown();
            cola = cola.getDown();
        }
        cabeza.setUp(null);
        cola.setUp(null); 
    }
    
    public void restruc(){
        NodoS<T> aux;
        this.lista();
        aux = cabeza;
        while(aux.getRigth() != null){
            aux.setUp(null);
            aux = aux.getRigth();
        }
        aux = cabeza.getRigth();
        boolean resp = false;
        int cont = 0;
        while(altura < Math.log(size) && cont < (size/2)){
            while(aux != null && aux.getRigth() != null){
                if(resp){
                    this.ligaH(this.findUpL(aux), this.cloneUp(aux), this.findUpR(aux));
                    resp = false;
                }
                else
                    resp = true;
                aux = aux.getRigth();
            }
            int k = 0;
            aux = cabeza;
            while(k < cont){
                aux = aux.getRigth();
                k++;
            }
            resp = false;
            cont++;
        }
        System.out.println("RESTRUCTURACIÓN: ");
        this.impH();
    }
    
    public void insertN(T elem){
        NodoS<T> ant, nuevo, ai, ad;
        if(elem != null){
            nuevo = new NodoS(elem);
            ant = find(elem);
            this.ligaH(ant, nuevo , ant.getRigth());
            while(flipCoin() && altura < Math.log(size)){
                ai = findUpL(nuevo);
                ad = findUpR(nuevo);
                this.ligaH(ai, cloneUp(nuevo), ad);
                nuevo = nuevo.getUp();
            }
            size++;
        }
    }
    
    public void delete(T elem){
        NodoS<T> busq = this.find(elem);
        if(busq.getElem() != null && busq.getElem().equals(elem)){
            if(busq.getUp() == null)
                desliga(busq);
            while(busq.getUp() != null){
                desliga(busq);
                busq = busq.getUp();
            }
            size--;
            System.out.println("El elemento: '" + elem + "' se eliminó correctamente\n");
        }else
            System.out.println("El elemento: '" + elem + "' no se eliminó porque no está en la estrucura\n");
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
        return rand.nextBoolean();
    }
    
    public boolean esCola(NodoS<T> nodo){
        if(nodo == null)
            return false;
        else
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
    
    public void impV(){
        int ancho = this.getAncho();
        StringBuilder cad = new StringBuilder();
        NodoS<T> aux = cabeza, aux2;
        while(aux.getDown() != null)
            aux = aux.getDown();
        aux2 = aux;
        for(int i = 0; i < ancho; i++){
            for(int j = 0; j <= altura; j++){
                cad.append("  ").append(aux.getElem());
                if(aux.getElem() != null && aux.getElem().toString().length() < 4){
                    int k = aux.getElem().toString().length();
                    while(k < 4){
                        cad.append(" ");
                        k++;
                    }
                }
                if(aux.getUp() == null){
                    while(j < altura){
                        cad.append("  ").append("↓").append("   ");
                        j++;
                    }
                    break;
                }
                aux = aux.getUp();
            }
            cad.append("\n");
            if(aux2.getRigth() != null){
                aux = aux2.getRigth();
                aux2 = aux2.getRigth();
            }
        }
        System.out.println("Número de elementos: " + size + "\nAltura máxima: " + (int)(Math.log(size)+1) + "\nAltura actual: " + altura + "\nImpresión:");
        System.out.println(cad.toString());
    }
    
    public void impH(){
        int ancho = this.getAncho();
        StringBuilder cad = new StringBuilder();
        NodoS<T> aux = cabeza, aux2;
        while(aux.getDown() != null)
            aux = aux.getDown();
        aux2 = aux;
        Object [][]lamina = new Object[ancho][altura+1];
        for(int i = 0; i < ancho; i++){
            for(int j = 0; j <= altura; j++){
                lamina[i][j] = aux.getElem();
                if(aux.getUp() != null){
                    lamina[i][j] = aux.getElem();
                    aux = aux.getUp();
                }
                else{
                    while(j < altura){
                        j++;
                        lamina[i][j] = "-";
                    }
                    break;
                }
            }
            if(aux2.getRigth() != null){
                aux = aux2.getRigth();
                aux2 = aux2.getRigth();
            }
        }
        for(int i = altura; i >= 0; i--){
            for(int j = 0; j < ancho; j++){
                cad.append("  ").append(lamina[j][i]);
                if(lamina[j][i] != null && lamina[j][i].toString().length() < 4){
                    int k = lamina[j][i].toString().length();
                    while(k < 4){
                        cad.append(" ");
                        k++;
                    }
                }
                    
            }
            cad.append("\n");
        }
        System.out.println("Número de elementos: " + size + "\nAltura máxima: " + (int)(Math.log(size)+1) + "\nAltura actual: " + altura + "\nImpresión:");
        System.out.println(cad.toString());
    }
    
    public static void main(String[] args) {
        SkipList<Integer> l = new SkipList();
        //INSERCIÓN
        for(int i = 1; i <= 10 ; i++)
            l.insertN(i);
        //l.impV();
        l.impH();
        
        //BORRADO
        l.delete(5);
        l.delete(4);
        l.restruc();
        //l.impH();
        
    }
}
