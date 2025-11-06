/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

/**
 *
 * @author USUARIO
 */
public class FacturaAdapter implements FacturaService {
    private LegacyBillingSystem legacySystem;

    public FacturaAdapter(LegacyBillingSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void generarFactura(String cliente, String producto, double total) {
        legacySystem.createInvoice(cliente, producto, total);
    }
}