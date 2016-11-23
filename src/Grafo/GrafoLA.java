/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;

/**
 *
 * @author hca
 */
public class GrafoLA<T> {
    
    private NodoG<T> grafo[];
    private int numV;
    private boolean visitado[];
    
    public GrafoLA(NodoG g[]){
        this.grafo = g;
        this.numV = g.length;
        iniciaVisitados();
    }
    
    public void DFS(){
        ArrayList<T> lista = new ArrayList<T>();
        for(int i = 0; i < numV; i++)
            DFS(i, lista);
    }
    
    public void DFS(int i, ArrayList<T> lista){
        NodoG<T> hermano = this.grafo[i];
        if(visitado[i])
            return;
        lista.add(grafo[i].getElem());
        visitado[i] = true;
        if(lista.size() == this.numV) //CAMINO HAMILTONIANO
            System.out.println(lista.toString());
        while(hermano != null){
            DFS(hermano.getIndex(), lista);
            hermano = hermano.getSig();
        }
        visitado[i] = false;
    }
    
    private void iniciaVisitados(){
        for(int i = 0; i < numV; i++)
            visitado[i] = false;
    }
    
    public static void main(String[] args) {
        NodoG<Character> grafo[] = new NodoG[5];
        grafo[0] = new NodoG('A', 0, 0);
        
        
        
    }
    
}
