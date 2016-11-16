/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import EDA.Nodo;
import java.util.ArrayList;

/**
 *
 * @author hca
 */
public class GrafoLA<T> {
    
    private Nodo<T> vertices[];
    private Nodo<T> grafo[];
    private int numV;
    private int numE;
    private boolean visitado[];
    
    public void DFS(){
        ArrayList<T> lista = new ArrayList<T>();
        iniciaVisitados();
        for(int i = 0; i < numV; i++)
            DFS(i, lista);
    }
    
    public void DFS(int i, ArrayList<T> lista){
        Nodo<T> actual;
        if(visitado[i])
            return;
        lista.add(vertices[i].getElem());
        visitado[i] = true;
        actual = grafo[i];
        while(actual != null){
            DFS(actual.getId(), lista);
            actual = actual.getSig();
        }
    }
    
    private void iniciaVisitados(){
        for(int i = 0; i < numV; i++)
            visitado[i] = false;
    }
    
    
}
