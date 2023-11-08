/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoaula.objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MELANY
 */
public class Usuario {
  public String nombre; 
  public String apellido; 
  public String nroDocumento;
  public String contraseña;
  public String email;   
  public String telefono; 
  public ArrayList<Gastos> listaGasto;
  public static HashMap<String, Usuario> usuariosBD = new HashMap<>();
}
