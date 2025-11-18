/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;


// crear una lista en memoria para guardar los pedidos
import java.util.List;
import java.util.ArrayList;

// crear una lista en memoria para guardar los pedidos
public class PedidoRepository {
  private List<Pedido> pedidos = new ArrayList<>();

  
  //funcion para agragar a la lista 
  // Sincronizado para seguridad en hilos
    public synchronized void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }
//Buscar el pedido por nombre
//  Sincronizado para seguridad en hilos
    public synchronized Pedido buscarPorCliente(String nombre) {
        for (Pedido p : pedidos) {
            if (p.getCliente().getNombre().equalsIgnoreCase(nombre)) { /*Usa equalsIgnoreCase() para que la comparación no 
                                                                        distinga mayúsculas y minúsculas*/
                return p;
            }
        }
        return null; // retorna nada si no encuentra el nombre
    }
    
}