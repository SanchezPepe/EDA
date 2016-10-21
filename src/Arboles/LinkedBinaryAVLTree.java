package Arboles;
import Arboles.LinkedBinarySearchTree;
import EDA.NodoAVL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author JJSA
 */
public class LinkedBinaryAVLTree<T extends Comparable<T>> extends LinkedBinarySearchTree<T> {
    
    private NodoAVL<T> raiz;

    public LinkedBinaryAVLTree(T elem) {
        super(elem);
        super.raiz = new NodoAVL<T>(elem);
        this.raiz = (NodoAVL<T>)super.raiz;
    }
    
    public NodoAVL<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAVL<T> raiz) {
        this.raiz = raiz;
    }

    public int getCont() {
        return cont;
    }
    
    public void rota(NodoAVL<T> nodo){
       NodoAVL<T> alfa, beta, gamma, A, B, C, D, papa;
       //CASO IZQUIERDA-IZQUIERDA
        if(nodo.getFe() == -2 && nodo.getIzq().getFe() <= 0){
            System.out.println("IZQUIERDA-IZQUIERDA");
            alfa = nodo;
            beta = alfa.getIzq();
            gamma = beta.getIzq();
            A = gamma.getIzq();
            B = gamma.getDer();
            C = beta.getDer();
            D = alfa.getDer();
            //ROTACIÓN
            papa = nodo.getPapa();
            alfa.cuelga(C);
            beta.cuelga(alfa);
            beta.cuelga(gamma);           
            if(papa != null)
               papa.cuelga(beta);
            else
               raiz = beta;
            int DH = 0;
            if(D!=null)
                DH = D.getAltura();
            int CH = 0;
            if(C != null)
                CH =C.getAltura();
            alfa.setFe(DH - CH);
            beta.setFe(alfa.getAltura() - gamma.getAltura());
            return; 
        }
        //CASO IZQUIERDA-DERECHA
        if(nodo.getFe() == -2 && nodo.getIzq().getFe() > 0){
            System.out.println("IZQUIERDA-DERECHA");
            alfa = nodo;
            beta = alfa.getIzq();
            gamma = beta.getDer();
            A = beta.getIzq();
            B = gamma.getIzq();
            C = gamma.getDer();
            D = alfa.getDer();
            //ROTACIÓN
            papa = nodo.getPapa();
            alfa.cuelga(C);
            beta.cuelga(B);
            gamma.cuelga(beta);
            gamma.cuelga(alfa);
            if(papa != null){
                papa.cuelga(gamma);
            }
            else
                raiz = gamma;
            int BH = 0, AH = 0, CH = 0, DH = 0;
            if(B != null)
                BH = 1 + B.getAltura();
            if(A != null)
                AH = 1 + A.getAltura();
            if(C != null)
                CH = 1 + C.getAltura();
            if(D != null)
                DH = 1 + D.getAltura();
            alfa.setFe(DH - CH);
            beta.setFe(BH - AH);
            gamma.setFe(alfa.getAltura() - beta.getAltura()); 
            return;
        }
        //CASO DERECHA-DERECHA
        if(nodo.getFe() == 2 && nodo.getDer().getFe() > 0){
            System.out.println("DERECHA-DERECHA");
            alfa = nodo;
            beta = alfa.getDer();
            gamma = beta.getDer();
            A = alfa.getIzq();
            B = beta.getDer();
            C = gamma.getIzq();
            D = gamma.getDer();   
            //ROTACIÓN
            papa = nodo.getPapa();
            alfa.cuelga(B);
            beta.cuelga(alfa);
            beta.cuelga(gamma);
            if(papa != null){
                papa.cuelga(beta);
            }
            else
                raiz = beta;
            int AH = 0;
            if(A != null)
                AH = A.getAltura();
            int BH = 0;
            if(B != null)
                BH = B.getAltura();            
            alfa.setFe(BH - AH);
            beta.setFe(gamma.getAltura() - alfa.getAltura());
            return;
       }
       //CASO DERECHA-IZQUIERDA
        if(nodo.getFe() == 2 && nodo.getDer().getFe() <= 0){
            System.out.println("DERECHA-IZQUIERDA");
            alfa = nodo;
            beta = alfa.getDer();
            gamma = beta.getIzq();
            A = alfa.getIzq();
            B = gamma.getIzq();
            C = gamma.getDer();
            D = beta.getDer();
            //ROTACIÓN
            papa = nodo.getPapa();
            alfa.cuelga(B);
            beta.cuelga(C);
            gamma.cuelga(alfa);
            gamma.cuelga(beta);
            if(papa!=null){
                papa.cuelga(gamma);
            }
            else
                raiz = gamma;
            int BH = 0, AH = 0, CH = 0, DH = 0;
            if(B!=null)
                BH = 1+B.getAltura();
            if(A!=null)
                AH = 1+A.getAltura();
            if(C!=null)
                CH = 1+C.getAltura();
            if(D!=null)
                DH = 1+D.getAltura();
            alfa.setFe(BH - AH);
            beta.setFe(DH - CH);
            gamma.setFe(beta.getAltura()- alfa.getAltura());
            return;
       }
    }
    
    public void inserta(T elem){
        NodoAVL<T> actual, papa, nodo = new NodoAVL<T>(elem);
        actual = this.raiz;
        papa = actual;
        cont++;
        if(actual == null){
            raiz = nodo;
            return;
        }
        while(actual != null){
            papa = actual;
            if(elem.compareTo(actual.getElem()) <= 0)
                actual = (NodoAVL<T>)actual.getIzq();
            else
                actual = (NodoAVL<T>)actual.getDer();
        }
        papa.cuelga(nodo);
        boolean termine = false;
        while(nodo.getPapa() != null && !termine){
            
            papa = nodo.getPapa();
            if(nodo.getElem().compareTo(papa.getElem()) <= 0)
                papa.setFe(papa.getFe()-1);
            else
                papa.setFe(papa.getFe()+1);
            this.imprimeArbol();
            if(Math.abs(papa.getFe()) > 1){
                rota(papa);
                termine = true;
            }
            if(papa.getFe() == 0)
                termine = true;
            nodo = papa;
            this.imprimeArbol();
        }
            
    }
    
    public NodoAVL busca(T elem){
        if(elem == null)
            return null;
        NodoAVL<T> act, papa;
        act = raiz;
        papa = act;
        while(act != null && act.getElem().compareTo(elem) != 0){
            if(elem.compareTo(act.getElem()) < 0){
                papa = act;
                act = act.getIzq();
            }
            else{
                papa = act;
                act = act.getDer();
            }
        }
        if(act == null)
            return null;
        else
            return act;
    }
    
    public void imprimeArbol() {
        StringBuilder cad = new StringBuilder();
        Queue<NodoAVL<T>> nivelActual = new LinkedList<NodoAVL<T>>();
        Queue<NodoAVL<T>> nivelSiguiente = new LinkedList<NodoAVL<T>>();
        int contImp = 0;
        nivelActual.add(this.raiz);
        while (!nivelActual.isEmpty() && contImp < cont) {
            Iterator<NodoAVL<T>> iter = nivelActual.iterator();
            while (iter.hasNext() && contImp < cont) {
                NodoAVL<T> nodoActual = iter.next();
                if (nodoActual.getIzq() != null) 
                    nivelSiguiente.add(nodoActual.getIzq());
                if (nodoActual.getDer() != null) 
                    nivelSiguiente.add(nodoActual.getDer());
                cad.append(nodoActual.getElem() + "(Fe:" + nodoActual.getFe() + ")");
                contImp++;
            }
            cad.append("\n");
            nivelActual = nivelSiguiente;
            nivelSiguiente = new LinkedList<NodoAVL<T>>();
        }
        System.out.println(cad);

    }    
    
    public static void main(String[] args) {
        
        //PRUEBA-NEGATIVOS Y POSITIVOS
        System.out.println("PRUEBA");
        LinkedBinaryAVLTree av = new LinkedBinaryAVLTree(100);
        av.inserta(50);
        av.inserta(75);
        //av.inserta(40);
        av.imprimeArbol();
        av.inserta(40);
        av.imprimeArbol();
        //av.imprimeArbol();
        
    }

}