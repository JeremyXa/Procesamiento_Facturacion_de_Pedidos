/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package procesamiento_facturación.de.pedidos;
import java.util.Scanner;
import java.util.*;
import Strategy.ExoneradoStrategy;
import Strategy.ImpuestoStrategy;
import Strategy.IGV18Strategy;
import Facade.PedidoFacade;
import Repository.Producto;
import Repository.PedidoRepository;




public class Procesamiento_FacturaciónDePedidos {

  
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lista de productos con stock inicial
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Laptop", 2500, 10));
        productos.add(new Producto("Mouse", 60, 30));
        productos.add(new Producto("Teclado", 120, 20));

        PedidoRepository repo = new PedidoRepository();
        PedidoFacade facade = new PedidoFacade(productos, repo);

        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Buscar pedido por cliente");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            // Validar entrada numérica
            while (!sc.hasNextInt()) {
                System.out.print("Opción inválida. Intenta nuevamente: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = sc.nextLine();

                    // Mostrar lista numerada de productos
                    System.out.println("Productos disponibles:");
                    for (int i = 0; i < productos.size(); i++) {
                        Producto p = productos.get(i);
                        System.out.println((i + 1) + ". " + p.getNombreP() +
                                " (S/ " + p.getPrecio() + ", Stock: " + p.getStock() + ")");
                    }

                    // Seleccionar producto por número
                    int indice = -1;
                    do {
                        System.out.print("Elige el número del producto: ");
                        while (!sc.hasNextInt()) {
                            System.out.print("Opción inválida. Intenta nuevamente: ");
                            sc.next();
                        }
                        indice = sc.nextInt();
                        sc.nextLine();
                        if (indice < 1 || indice > productos.size()) {
                            System.out.println("Número fuera de rango. Intenta de nuevo.");
                        }
                    } while (indice < 1 || indice > productos.size());

                    // seleccionamos directamente el producto
                    Producto productoSeleccionado = productos.get(indice - 1);

                    // Ingresar cantidad
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    // Elegir tipo de impuesto
                    int tipo;
                    do {
                        System.out.print("Tipo de impuesto (1 = 18%, 2 = 0%): ");
                        while (!sc.hasNextInt()) {
                            System.out.print("Opción inválida. Intenta nuevamente: ");
                            sc.next();
                        }
                        tipo = sc.nextInt();
                        sc.nextLine();
                        if (tipo != 1 && tipo != 2) {
                            System.out.println("Debe elegir 1 o 2.");
                        }
                    } while (tipo != 1 && tipo != 2);

                    // Elegir estrategia de impuesto
                    ImpuestoStrategy estrategia = (tipo == 1)
                            ? new IGV18Strategy()
                            : new ExoneradoStrategy();

                    // Procesar pedido con el Facade (ahora es asíncrono)
                    facade.procesarPedido(nombre, productoSeleccionado, cantidad, estrategia);
                    break;

                case 2:
                    System.out.print("Nombre del cliente: ");
                    String buscar = sc.nextLine();
                    facade.mostrarPedido(buscar);
                    break;

                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    break;
            }

        } while (opcion != 3);

        // Apagar el pool de hilos antes de salir
        facade.shutdown();
        sc.close();
    }
}
