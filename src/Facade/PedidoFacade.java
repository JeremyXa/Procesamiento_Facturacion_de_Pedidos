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
import Repository.PedidoRepository;
import Repository.Cliente;
/**
 *
 * @author USUARIO
 */
public class  PedidoFacade {
    private List<Producto> productos;
    private PedidoRepository pedidoguarda;
    private FacturaService facturaService;

    public PedidoFacade(List<Producto> productos, PedidoRepository repo) {
        this.productos = productos;
        this.pedidoguarda = repo;
        this.facturaService = new FacturaAdapter();
    }

    public void procesarPedido(String nombreCliente, String nombreProducto, int cantidad, ImpuestoStrategy impuestoStrategy) {
        Cliente cliente = new Cliente(nombreCliente);
        Producto producto = buscarProducto(nombreProducto);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser positiva.");
            return;
        }

        if (cantidad > producto.getStock()) {
            System.out.println("No hay suficiente stock disponible.");
            return;
        }

        double subtotal = producto.getPrecio() * cantidad;
        double impuesto = impuestoStrategy.calcular(subtotal);
        double total = subtotal + impuesto;

        producto.reducirStock(cantidad);

        Pedido pedido = new Pedido(cliente, producto, cantidad, subtotal, impuesto, total);
        pedidoguarda.guardar(pedido);

        facturaService.GenerarFacturaNueva(pedido);
    }

    
    
    
    public void mostrarPedido(String nombreCliente) {
        Pedido pedido = pedidoguarda.buscarPorCliente(nombreCliente);
        if (pedido == null) {
            System.out.println("No se encontró un pedido para el cliente ingresado.");
        } else {
            System.out.println("\n--- PEDIDO REGISTRADO ---");
            System.out.println("Cliente: " + pedido.getCliente().getNombre());
            System.out.println("Producto: " + pedido.getProducto().getNombre());
            System.out.println("Cantidad: " + pedido.getCantidad());
            System.out.printf("Subtotal: %.2f | IGV: %.2f | Total: %.2f\n",
                    pedido.getSubtotal(), pedido.getImpuesto(), pedido.getTotal());
        }
          }

    private Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }
}