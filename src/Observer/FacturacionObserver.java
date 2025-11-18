/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Adapter.FacturaService;
import Repository.Pedido;

/**
 * Observador Concreto 3: Genera la factura.
 * Este observador usa el Adapter para conectarse al sistema legacy.
 */
public class FacturacionObserver implements PedidoObserver {
    
    private FacturaService facturaService;

    public FacturacionObserver(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @Override
    public void onPedidoCreado(Pedido pedido) {
        System.out.println("\n[Observer Facturación - Hilo: " + Thread.currentThread().getName() + "]");
        System.out.println("   ===> Iniciando generación de factura para " + pedido.getCliente().getNombre() + "...");
        
        // Simular trabajo pesado de facturación
        try { Thread.sleep(250); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Llama al adapter (que llama al sistema legacy)
        // Esto ahora ocurre en un hilo separado.
        facturaService.GenerarFacturaNueva(pedido);
        
        System.out.println("   ===> Factura generada.");
    }
}