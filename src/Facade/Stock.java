/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author USUARIO
 */
public class Stock {
  private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Double> precios = new HashMap<>();

    public Stock() {
        stock.put("Laptop", 5);
        stock.put("Mouse", 10);
        stock.put("Teclado", 8);

        precios.put("Laptop", 2500.0);
        precios.put("Mouse", 80.0);
        precios.put("Teclado", 150.0);
    }

    public boolean validarStock(String producto, int cantidad) {
        return stock.containsKey(producto) && cantidad > 0 && cantidad <= stock.get(producto);
    }

    public double obtenerPrecio(String producto) {
        return precios.getOrDefault(producto, 0.0);
    }

    public void reducirStock(String producto, int cantidad) {
        stock.put(producto, stock.get(producto) - cantidad);
    }
}