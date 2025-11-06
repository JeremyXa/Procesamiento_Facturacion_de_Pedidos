/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author USUARIO
 */
public class Stock {
    private Map<String, Integer> stockProductos = new HashMap<>();

    public Stock() {
        // Carga de productos con su stock disponible
        stockProductos.put("Laptop Lenovo", 5);
        stockProductos.put("Mouse Logitech", 10);
        stockProductos.put("Teclado Redragon", 8);
    }

    public boolean validarStock(String producto, int cantidad) {
        if (!stockProductos.containsKey(producto)) return false;
        int stockDisponible = stockProductos.get(producto);
        return cantidad > 0 && cantidad <= stockDisponible;
    }

    public double obtenerPrecio(String producto) {
        switch (producto) {
            case "Laptop Lenovo": return 2500.00;
            case "Mouse Logitech": return 120.00;
            case "Teclado Redragon": return 180.00;
            default: return 0.0;
        }
    }
}