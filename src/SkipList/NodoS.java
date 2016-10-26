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
 
}
