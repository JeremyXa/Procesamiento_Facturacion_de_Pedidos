/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;
import Repository.Pedido;

/**
 *
 * @author USUARIO
 */
public class FacturaAdapter implements FacturaService {
    private LegacyBillingSystem legacySystem = new LegacyBillingSystem();

    public void generarFactura(Pedido pedido) {
        legacySystem.generarFacturaAntigua(
                pedido.getCliente().getNombre(),
                pedido.getProducto().getNombre(),
                pedido.getSubtotal(),
                pedido.getImpuesto(),
                pedido.getTotal()
        );
    }

}