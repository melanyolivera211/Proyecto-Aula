/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoaula.objects;

import java.util.HashMap;

/**
 *
 * @author MELANY
 */
public class Electrodomesticos {
    public String nombreE; 
    public String nroserie;
    public String marca;
    public Usuario usuario; 
    public Gastos gasto;
    public static HashMap<String, Usuario> electrodomesticosBD = new HashMap<>();
}
