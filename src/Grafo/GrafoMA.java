/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author hca
 */
public class GrafoMA<T> {
    
    private NodoG<T> vertices[];
    private NodoG<T> grafo[][];
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
        if(visitado[i])
            return;
        lista.add(vertices[i].getElem());
        visitado[i] = true;
        for(int j = 0; j < numV; j++)
            if(grafo[i][j] != null)
                DFS(j, lista);
    }
    
    public void BFS(int i, ArrayList<T> lista){
        Queue<NodoG<T>> q = new ArrayDeque<NodoG<T>>();
        NodoG<T> actual;
        q.add(vertices[i]);
        visitado[i] = true;
        while(!q.isEmpty()){
            actual = q.remove();
            i = actual.getIndex();
            for(int j = 0; j < numV; j++)
                if(grafo[i][j] != null && !visitado[j]){
                    visitado[j] = true;
                    q.add(vertices[j]);
                }
        }
    }
    
    
    private void iniciaVisitados(){
        for(int i = 0; i < numV; i++)
            visitado[i] = false;
    }
}
