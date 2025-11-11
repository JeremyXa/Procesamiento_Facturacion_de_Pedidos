/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

/**
 *
 * @author USUARIO
 */
public class LegacyBillingSystem {
       public void GenerarFacturaAntigua(String cliente, String producto, double subtotal, double impuesto, double total) {
        System.out.println("\n--- FACTURA (Sistema Antiguo) ---");
        System.out.println("Cliente: " + cliente);
        System.out.println("Producto: " + producto);
        System.out.printf("Subtotal: %.2f\nImpuesto: %.2f\nTotal: %.2f\n", subtotal, impuesto, total);
        System.out.println("---------------------------------");
    }
}