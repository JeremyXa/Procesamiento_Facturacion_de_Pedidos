/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

/**
 *
 * @author USUARIO
 */
public class CalculoP {
    public double calcularSubtotal(double precio, int cantidad) {
        return precio * cantidad;
    }

    public double calcularIGV(double subtotal) {
        return subtotal * 0.18;
    }

    public double calcularTotal(double subtotal, double igv) {
        return subtotal + igv;
    }
}