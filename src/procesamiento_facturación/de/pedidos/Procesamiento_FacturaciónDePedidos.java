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

        // Permitir repetir si elige opción incorrecta
        String producto = null;
        int opcion = 0;
        do {
            facade.mostrarProductosDisponibles();
            System.out.print("Seleccione el número del producto: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                producto = facade.obtenerProductoPorOpcion(opcion);
                if (producto == null) {
                    System.out.println("⚠️ Opción inválida. Intente nuevamente.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Por favor ingrese un número.\n");
            }
        } while (producto == null);

        // Validar cantidad
        int cantidad = 0;
        do {
            System.out.print("Ingrese la cantidad: ");
            try {
                cantidad = Integer.parseInt(sc.nextLine());
                if (cantidad <= 0) {
                    System.out.println("⚠️ La cantidad debe ser mayor a 0. Intente nuevamente.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Ingrese un número entero.\n");
                cantidad = 0;
            }
        } while (cantidad <= 0);

        // Procesar el pedido
        facade.procesarPedido(cliente, producto, cantidad);
        sc.close();
    }
}