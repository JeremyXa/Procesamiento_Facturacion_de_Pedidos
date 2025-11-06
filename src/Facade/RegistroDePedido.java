/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

/**
 *
 * @author USUARIO
 */
public class  RegistroDePedido {
    public void registrar(String cliente, String producto, int cantidad) {
        System.out.println("📦 Pedido registrado: " + cantidad + " unidad(es) de " + producto + " para " + cliente);
    }
}