/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmosOrdTarea2;

import AlgoritmosOrdTarea2.AlgoritmosDeOrdenamiento;
import java.io.File;
import java.util.*;

/**
 *
 * @author jsanchezagu
 */
public class ColeccionPeliculas  {
    
    private Pelicula coleccion[];
    private int numeroP;
    private int n = 0;
    private  AlgoritmosDeOrdenamiento<Pelicula> ord;
    
    public ColeccionPeliculas(){
        ord = new AlgoritmosDeOrdenamiento<Pelicula>();
    }
    
    
    public void setN(int n){
        this.n = n;
    }
    
    /**
     * Método que cuenta las líneas en el TXT para crear el arreglo donde se almacenarán las películas
     * Si se ocupa un cierto número de películas se le asigna un valor a N
     * Si se quiere leer todo el TXT se define n = -1
     */
    public int cuentaP(){
        int cont = 0;
        try{
            String nomArchivo = "movie_titles2.txt";
            File archivo = new File(nomArchivo);
            Scanner in = new Scanner(archivo);
            while(in.hasNextLine()){
                cont++;
                in.nextLine();
            }
        }catch(Exception e){
            System.out.print("\nNo se pudo abrir el archivo");
            System.exit(-1);
        }
        return cont;
    }    
    
    public void lecturaTXT(){
        if(n != 0)
            coleccion = new Pelicula[n];
        else
            coleccion = new Pelicula[cuentaP()];
        numeroP = 0;
        try{
            String nomArchivo = "movie_titles1.txt";
            
            File archivo = new File(nomArchivo);
            Scanner in = new Scanner(archivo);
            while(numeroP < coleccion.length && in.hasNextLine()){
                String pel = in.nextLine();
                int i = 0;
                while(pel.charAt(i) != ',')
                    i++;
                int id = Integer.parseInt(pel.substring(0, i));
                int año = Integer.parseInt(pel.substring(i+1, i+5));
                String titulo = pel.substring(i+6, pel.length());
                Pelicula p = new Pelicula(id, año, titulo);
                coleccion[numeroP] = p;
                numeroP++;
            }
        }catch(Exception e){
            System.out.print("\nNo se pudo abrir el archivo");
            System.exit(-1);
        }
    }
 
    public Pelicula[] getColeccion() {
        return coleccion;
    }

    public int getNumeroP() {
        return numeroP;
    }

    public void setColeccion(Pelicula[] coleccion) {
        this.coleccion = coleccion;
    }
    
    public void imprimeCat(){
        int i = 0;
        while(i < coleccion.length){
            System.out.println(this.coleccion[i].toString());
            i++;
        }
    }
    
    public void ordenamientos(int alg, int n){
        this.setN(n);
        lecturaTXT();
        ord.quickSort(coleccion);
        String s = null;
        long t0 = System.nanoTime();
        switch(alg){
            case 1:
                s = "Inserción Directa";
                ord.insercionDirecta(coleccion);
                break; 
            case 2:
                s = "Bubble sort";
                ord.bubbleSort(coleccion);
                break;
            case 3:
                s = "Quick sort";
                ord.quickSort(coleccion);
                break;
            case 4:
                s = "Merge sort";
                ord.mergeSort(coleccion);
                break;
            case 5:
                s = "Shell sort";
                ord.shellSort(coleccion);
                break;
            default:
                System.out.println("No se seleccionó el método");
        }
        long t = System.nanoTime();
        long tiempoEjec = t - t0;
        System.out.println(s);
        System.out.println("Peliculas almacenadas: " + coleccion.length);
        System.out.println("Tiempo de ejecución: " + tiempoEjec + " nanosegundos");
        System.out.println("Comparaciones hechas: " + ord.numComp);
        
    }
    
    public static void main(String[] args) {
        /** Algoritmos
         * 1.- Inserción Directa
         * 2.- Bubble Sort
         * 3.- Quick Sort
         * 4.- Merge Sort
         * 5.- Shell Sort */
        System.out.println("INVERTIDOS");
        System.out.println("ID");
        int alg = 1;
        System.out.println("\n");
        ColeccionPeliculas c = new ColeccionPeliculas();
        c.ordenamientos(alg, 100);
        c.ordenamientos(alg, 1000);
        c.ordenamientos(alg, 10000);
        c.ordenamientos(alg, 0);
        System.out.println("\n");
        alg = 2;
        System.out.println("BS");
        c.ordenamientos(alg, 100);
        c.ordenamientos(alg, 1000);
        c.ordenamientos(alg, 10000);
        c.ordenamientos(alg, 0);
        System.out.println("\n");
        alg = 4;
        System.out.println("MS");
        c.ordenamientos(alg, 100);
        c.ordenamientos(alg, 1000);
        c.ordenamientos(alg, 10000);
        c.ordenamientos(alg, 0);
        System.out.println("\n");
        alg = 5;
        System.out.println("SS");
        c.ordenamientos(alg, 100);
        c.ordenamientos(alg, 1000);
        c.ordenamientos(alg, 10000);
        c.ordenamientos(alg, 0);
        alg = 3;
        System.out.println("QS");
        c.ordenamientos(alg, 100);
        c.ordenamientos(alg, 1000);
        c.ordenamientos(alg, 10000);
        c.ordenamientos(alg, 0);
        System.out.println("\n");
        
    }
}
