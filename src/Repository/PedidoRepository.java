/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.List;
import java.util.ArrayList;


public class PedidoRepository {
  private List<Pedido> pedidos = new ArrayList<>();

    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido buscarPorCliente(String nombre) {
        for (Pedido p : pedidos) {
            if (p.getCliente().getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
    
}


