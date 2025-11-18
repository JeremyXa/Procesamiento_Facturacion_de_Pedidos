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

    
    
    
    public void GenerarFacturaNueva(Pedido pedido) {
        legacySystem.GenerarFacturaAntigua(
                pedido.getCliente().getNombre(),
                pedido.getProducto().getNombreP(),
                pedido.getSubtotal(),
                pedido.getImpuesto(),
                pedido.getTotal()
        );
    }

}