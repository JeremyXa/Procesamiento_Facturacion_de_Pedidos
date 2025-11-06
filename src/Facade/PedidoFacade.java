/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;
import Adapter.FacturaService;
/**
 *
 * @author USUARIO
 */
public class  PedidoFacade {
    private Stock validador;
    private CalculoP calculadora;
    private RegistroDePedido registro;
    private FacturaService facturaService;

    public PedidoFacade(FacturaService facturaService) {
        this.validador = new Stock();
        this.calculadora = new CalculoP();
        this.registro = new RegistroDePedido();
        this.facturaService = facturaService;
    }

    public void procesarPedido(String cliente, String producto, int cantidad) {
        System.out.println("\n==== Procesando pedido ====");
        if (!validador.validarStock(producto, cantidad)) {
            System.out.println("Error: cantidad inválida o sin stock disponible.");
            return;
        }

        double precio = validador.obtenerPrecio(producto);
        double subtotal = calculadora.calcularSubtotal(precio, cantidad);
        double igv = calculadora.calcularIGV(subtotal);
        double total = calculadora.calcularTotal(subtotal, igv);

        registro.registrar(cliente, producto, cantidad);
        facturaService.generarFactura(cliente, producto, total);

        System.out.println("\n=== COMPROBANTE ===");
        System.out.println("Cliente: " + cliente);
        System.out.println("Producto: " + producto);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Subtotal: S/." + subtotal);
        System.out.println("IGV (18%): S/." + igv);
        System.out.println("Total: S/." + total);
        System.out.println("====================");
    }

    public void mostrarProductosDisponibles() {
        System.out.println("Productos disponibles:");
        System.out.println("1. Laptop Lenovo - S/.2500.00 (Stock: 5)");
        System.out.println("2. Mouse Logitech - S/.120.00 (Stock: 10)");
        System.out.println("3. Teclado Redragon - S/.180.00 (Stock: 8)");
    }

    public String obtenerProductoPorOpcion(int opcion) {
        switch (opcion) {
            case 1: return "Laptop Lenovo";
            case 2: return "Mouse Logitech";
            case 3: return "Teclado Redragon";
            default: return null;
        }
    }
}