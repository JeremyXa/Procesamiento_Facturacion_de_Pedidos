/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package procesamiento_facturación.de.pedidos;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import Adapter.LegacyBillingSystem;
import Adapter.FacturaService;
import Facade.PedidoFacade;
import Adapter.FacturaAdapter;
/**
 *
 * @author USUARIO
 */
public class Procesamiento_FacturaciónDePedidos {

  
        
          public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LegacyBillingSystem legacy = new LegacyBillingSystem();
        FacturaService adaptador = new FacturaAdapter(legacy);
        PedidoFacade facade = new PedidoFacade(adaptador);

        System.out.print("Ingrese su nombre: ");
        String cliente = sc.nextLine();

        facade.mostrarProductosDisponibles();
        System.out.print("Seleccione el número del producto: ");
        int opcion = sc.nextInt();

        String producto = facade.obtenerProductoPorOpcion(opcion);
        if (producto == null) {
            System.out.println("❌ Opción inválida. Saliendo del sistema.");
            return;
        }

        System.out.print("Ingrese la cantidad: ");
        int cantidad = sc.nextInt();

        facade.procesarPedido(cliente, producto, cantidad);
        sc.close();
    }
}