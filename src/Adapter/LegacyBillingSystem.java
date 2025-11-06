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
    public void createInvoice(String customer, String item, double totalAmount) {
        System.out.println("🧾 [LegacyBillingSystem] Factura creada para " + customer +
                " - Producto: " + item + " - Total: S/." + totalAmount);
    }
}