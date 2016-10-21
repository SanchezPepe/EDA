/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Netflix;

/**
 *
 * @author jsanchezagu
 */
public class Pelicula implements Comparable<Pelicula>{
    
    private int idPelicula;
    private int año;
    private String nombre;
    
    public Pelicula(){       
    }
    
    public Pelicula(int id, int año, String nom){
        this.nombre = nom;
        this.año = año;
        this.idPelicula = id;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int compareTo(Pelicula p){
            if(this.idPelicula > p.idPelicula)
                return 1;
            if(this.idPelicula < p.idPelicula)
                return -1;
            else
                return 0;        
    }
    
    public String toString() {
        return idPelicula + ".- Pelicula: " + this.nombre + ", Año: " + año;
    }
    
    
    
}
