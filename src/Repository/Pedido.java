/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

/**
 *
 * @author USUARIO
 */
public class  Pedido {
    private Cliente cliente;
    private Producto producto;
    private int cantidad;
    private double subtotal;
    private double impuesto;
    private double total;

    public Pedido(Cliente cliente, Producto producto, int cantidad, double subtotal, double impuesto, double total) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

}
