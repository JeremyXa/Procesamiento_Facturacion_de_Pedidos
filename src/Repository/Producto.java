/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

/**
 *
 * @author USUARIO
 */
public class Producto {
    private String nombrep;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombrep = nombrep;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombrep;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }
}
