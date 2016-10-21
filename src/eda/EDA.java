/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDA;

/**
 *
 * @author hca
 */
public class EDA {
    
    //Dada una cadena con n caracteres imprimir sus permutaciones
    public static void permuta(String s, String nueva){
        if(s.length() == 0){
            System.out.println(nueva);
            return;
        }
        for(int i = 0; i < s.length(); i++){
            String c = s.substring(i, i+1);
            String resto = s.substring(0,i) + s.substring(i+1);
            permuta(resto, nueva + c);
        }
    }
    
    //Dadas dos cadenas S1 y S2 encuentre cuál es el número mínimo de operaciones para convertir S1 en S2
    // Operaciones:
    // borrar char, agregar char y sustituir char
    public static int pasosMinimos(String s1, String s2){
        return pasosMinimos(s1, s2, 0, Math.max(s1.length(), s2.length()));
    }
        
    private static int pasosMinimos(String s1, String s2, int cont, int max){
        int val1, val2, val3;
        if(s1.length() == 0 || s2.length() == 0)
            return cont + s1.length() + s2.length();
        if(s1.compareTo(s2) == 0)
            return cont;
        if(s1.charAt(0) == s2.charAt(                     0))
            return pasosMinimos(s1.substring(1), s2.substring(1), cont, max);
        val1 = pasosMinimos(s1.substring(1), s2.substring(1), cont + 1, max); //SUSTITUIR
        val2 = pasosMinimos(s1.substring(1), s2, cont + 1, max); //BORRAR
        val3 = pasosMinimos(s1, s2.substring(1), cont + 1, max); ///AÑADIR
        return Math.min(val1, Math.min(val2, val3));
    }    
    

    public static void main(String[] args) {
        String s = "";
        EDA.permuta(s, "");
        String s1 = "botella";
        String s2 = "botela";
        System.out.println(EDA.pasosMinimos(s1, s2));
    }
    
}
