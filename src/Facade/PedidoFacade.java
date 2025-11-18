/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;
import Adapter.FacturaService;
import Adapter.FacturaAdapter;
import Repository.Pedido;
import Strategy.ImpuestoStrategy;
import Repository.Producto;
import java.util.*;
import java.util.concurrent.Executors; // 
import java.util.concurrent.ExecutorService; // 
import Repository.PedidoRepository;
import Repository.Cliente;
import Observer.*; 

/**
 *
 * @author USUARIO
 */
public class PedidoFacade {
    private List<Producto> productos;  
    private PedidoRepository pedidoguarda;
    
 
    
    //  Pool de hilos para procesar pedidos y notificaciones
    private ExecutorService executor;
    
    // El "Sujeto" del patrón Observer
    private PedidoEventManager eventManager;

    public PedidoFacade(List<Producto> productos, PedidoRepository pedidoguarda) {
        this.productos = productos;
        this.pedidoguarda = pedidoguarda;
        
        // Creamos un pool de 5 hilos
        this.executor = Executors.newFixedThreadPool(5);
        
        // Configuramos el sistema de eventos (Observer)
        this.eventManager = new PedidoEventManager(this.executor);
        
        //  Suscribimos a nuestros observadores
        FacturaService facturaService = new FacturaAdapter(); // El adapter
        this.eventManager.subscribe(new ClienteNotifier());
        this.eventManager.subscribe(new InventarioLogger());
        this.eventManager.subscribe(new FacturacionObserver(facturaService));
    }

    
    public void procesarPedido(String nombreCliente, Producto producto, int cantidad, ImpuestoStrategy impuestoStrategy) {
        
        System.out.println("\n[Facade] Recibido pedido para " + nombreCliente + ". Enviando a la cola de procesamiento...");
        
        // Creamos una "tarea" (Runnable) y la enviamos al pool de hilos
        // Todo el procesamiento ocurre ahora en un hilo separado.
        executor.submit(() -> {
            System.out.println("[Procesador - Hilo: " + Thread.currentThread().getName() + "] ===> Procesando pedido de " + nombreCliente);
            
            // Crear el cliente
            Cliente cliente = new Cliente(nombreCliente);

            // Validaciones básicas
            if (producto == null) {
                System.out.println("[Procesador] Error: Producto no encontrado.");
                return; // Termina la tarea
            }

            if (cantidad <= 0) {
                System.out.println("[Procesador] Error: La cantidad debe ser positiva.");
                return; // Termina la tarea
            }

            double subtotal;
            double impuesto;
            double total;

          
            // Sincronizamos sobre el "producto" para evitar que dos hilos
            // lean el stock y lo reduzcan al mismo tiempo (Condición de Carrera).
            synchronized (producto) {
                if (cantidad > producto.getStock()) {
                    System.out.println("[Procesador] Error: No hay suficiente stock para " + producto.getNombreP() + ". Pedido cancelado.");
                    return; // Termina la tarea
                }
                
                System.out.println("[Procesador] Stock OK. Calculando y reduciendo stock de " + producto.getNombreP());

                // Cálculos
                subtotal = producto.getPrecio() * cantidad;
                impuesto = impuestoStrategy.calcular(subtotal);
                total = subtotal + impuesto;

                // Actualizar stock (dentro del bloque sincronizado)
                producto.reducirStock(cantidad);
            } // Fin de la sección crítica

            // Crear y guardar pedido
            // El repositorio ya es thread-safe (métodos synchronized)
            Pedido pedido = new Pedido(cliente, producto, cantidad, subtotal, impuesto, total);
            pedidoguarda.guardar(pedido);
            
            System.out.println("[Procesador - Hilo: " + Thread.currentThread().getName() + "] ===> Pedido guardado. Disparando eventos...");

            // Generar factura (ahora se hace vía Observer)
            // facturaService.GenerarFacturaNueva(pedido); -> ELIMINADO
            
            // Notificar a los observadores (que correrán en sus propios hilos)
            eventManager.notify(pedido);
        });
    }

    //  Mostrar pedido por cliente
    public void mostrarPedido(String nombreCliente) {
        // La búsqueda ya es thread-safe gracias al PedidoRepository modificado
        Pedido pedido = pedidoguarda.buscarPorCliente(nombreCliente);
        if (pedido == null) {
            System.out.println("No se encontró un pedido para el cliente ingresado.");
        } else {
            System.out.println("\n--- PEDIDO REGISTRADO ---");
            System.out.println("Cliente: " + pedido.getCliente().getNombre());
            System.out.println("Producto: " + pedido.getProducto().getNombreP());
            System.out.println("Cantidad: " + pedido.getCantidad());
            System.out.printf("Subtotal: %.2f | IGV: %.2f | Total: %.2f\n",
                    pedido.getSubtotal(), pedido.getImpuesto(), pedido.getTotal());
        }
    }
    
    // Método para apagar el pool de hilos
    public void shutdown() {
        System.out.println("[Facade] Apagando el procesador de pedidos...");
        executor.shutdown();
    }
}
