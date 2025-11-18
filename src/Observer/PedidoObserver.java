/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Repository.Pedido;

/**
 * Interfaz para el patrón Observer.
 * Cualquier clase que quiera ser notificada de un nuevo pedido debe implementarla.
 */
public interface PedidoObserver {
    void onPedidoCreado(Pedido pedido);
}