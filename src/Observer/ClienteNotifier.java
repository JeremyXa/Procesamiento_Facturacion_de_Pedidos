/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Repository.Pedido;

/**
 * Observador Concreto 1: Notifica al cliente.
 */
public class ClienteNotifier implements PedidoObserver {
    
    @Override
    public void onPedidoCreado(Pedido pedido) {
        System.out.println("\n[Observer Cliente - Hilo: " + Thread.currentThread().getName() + "]");
        System.out.println("   ===> Notificando al cliente: " + pedido.getCliente().getNombre());
        
        // Simular trabajo (ej. enviar email)
        try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        System.out.println("   ===> Correo enviado a " + pedido.getCliente().getNombre());
    }
}