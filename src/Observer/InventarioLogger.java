/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Repository.Pedido;

/**
 * Observador Concreto 2: Registra el movimiento de inventario.
 */
public class InventarioLogger implements PedidoObserver {
    
    @Override
    public void onPedidoCreado(Pedido pedido) {
        System.out.println("\n[Observer Inventario - Hilo: " + Thread.currentThread().getName() + "]");
        System.out.println("   ===> Registrando en log: " + pedido.getCantidad() + " unidades de " + pedido.getProducto().getNombreP() + " vendidas.");
        
        // Simular escritura en log
        try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        System.out.println("   ===> Log de inventario actualizado.");
    }
}