/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SkipList;

/**
 *
 * @author hca
 */
public class NodoS<T extends Comparable <T>>{
    
    private NodoS<T> left, rigth, up, down;
    private T elem;
    
    public NodoS(){
        this.elem = null;
        this.left = null;
        this.rigth = null;
        this.up = null;
        this.down = null;
    }
    
    public NodoS(T dato){
        this();
        this.elem = dato;
    }

    public NodoS<T> getLeft() {
        return left;
    }

    public void setLeft(NodoS<T> left) {
        this.left = left;
    }

    public NodoS<T> getRigth() {
        return rigth;
    }

    public void setRigth(NodoS<T> rigth) {
        this.rigth = rigth;
    }

    public NodoS<T> getUp() {
        return up;
    }

    public void setUp(NodoS<T> up) {
        this.up = up;
    }

    public NodoS<T> getDown() {
        return down;
    }

    public void setDown(NodoS<T> down) {
        this.down = down;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }
    
    public NodoS<T> cuelgaEnNivelSup(T elem){
        if(elem!=null){
            NodoS<T>nuevo = new NodoS(elem);
            
            if(this.getUp() == null){
                this.setUp(nuevo);
                nuevo.setDown(this);
                //casos
                    //no tengo hijo izquierdo ni derecho
                    if(this.getRigth()==null&&this.getLeft()==null){
                        //this.setArri(nuevo);
                        return nuevo;
                    }else{
                        if(this.getRigth()!=null){
                            if(this.getLeft()!=null){//si el derecho e izquierdo no son nulos
                                //preguntar si tienen arriba
                                NodoS<T> aux=this.getRigth();
                                while(aux!=null && aux.getUp()==null)
                                    aux=aux.getRigth();
                                if(aux!=null){
                                    nuevo.cueldaDerecha(aux.getUp());
                                }
                                aux=this.getLeft();
                                while(aux!=null && aux.getUp()==null)
                                    aux=aux.getLeft();
                                if(aux!=null){
                                    nuevo.cuelgaIzquierda(aux.getUp());
                                }
                                return nuevo;
                                //this.setArri(nuevo);
                            }else{
                                //si el derecho no es nulo y el izquierdo si
                                //preguntar si tienen arriba
                                NodoS<T> aux=this.getRigth();
                                while(aux!=null && aux.getUp()==null)
                                    aux=aux.getRigth();
                                if(aux!=null){
                                    nuevo.cueldaDerecha(aux.getUp());
                                }
                                return nuevo;
                                //this.setArri(nuevo);
                            }
                        }//si el derecho es nulo
                        else{
                            
                            NodoS<T> aux=this.getLeft();
                            while(aux!=null && aux.getUp()==null)
                                aux=aux.getLeft();
                            if(aux!=null){
                                nuevo.cuelgaIzquierda(aux.getUp());
                            }
                            return nuevo;
                            //this.setArri(nuevo);
                        }
                    } 
            }else{
                System.out.println("No se colgó arriba pues ya hay un nodo arriba");
                return null;
            }      
        }
        else{
            System.out.println("No se puede colgar porque el elemento proporcionado es nulo");
            return null;
        }
    }
    
    public void cueldaDerecha(NodoS<T> nuevo){
        if(nuevo!=null){
            if(this.getRigth()==null){
                nuevo.setLeft(this);
                this.setRigth(nuevo);
            }else{
                // Si el nodo de la derecha no es nulo, es necesario ligar referencias de manera adecuada
                    //asigno ligas al nuevo nodo
                nuevo.setLeft(this);
                nuevo.setRigth(this.getRigth());
                    //reasigno ligas mia y del de mi derecha
                this.getRigth().setLeft(nuevo);
                this.setRigth(nuevo);
            }
        }else{
            System.out.println("No se ligo el nodo por ser nulo");
        }
    }
    
    public void cuelgaIzquierda(NodoS<T> nuevo){
        if(nuevo!=null){
            if(this.getLeft()==null){
                //caso donde el izq sea nulo
                nuevo.setRigth(this);
                this.setLeft(nuevo);
            }else{
                //Si el nodo de la izquierda no es nulo, es necesario ligar referencias de manera adecuada
                    //asigno referencias al nuevo
                nuevo.setRigth(this);
                nuevo.setLeft(this.getLeft());
                    //reasigno ligas mia y del de mi izquierda
                this.getLeft().setRigth(nuevo);
                this.setLeft(nuevo);
            }    
        }else{
            System.out.println("No se ligo el nodo por ser nulo");
        }
    }
    
    
}
