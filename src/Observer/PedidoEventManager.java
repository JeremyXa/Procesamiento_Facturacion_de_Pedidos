/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Repository.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * El "Sujeto" (Subject) del patrón Observer.
 * Gestiona la lista de observadores y les notifica de forma asíncrona.
 */
public class PedidoEventManager {
    private List<PedidoObserver> observers = new ArrayList<>();
    private ExecutorService notificationExecutor; // Pool de hilos para las notificaciones

    public PedidoEventManager(ExecutorService notificationExecutor) {
        this.notificationExecutor = notificationExecutor;
    }

    public void subscribe(PedidoObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(PedidoObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifica a todos los observadores en paralelo.
     * Cada observador se ejecuta en un hilo del pool.
     */
    public void notify(Pedido pedido) {
        System.out.println("[Evento] Notificando a " + observers.size() + " observadores...");
        for (PedidoObserver observer : observers) {
            // Cada observador obtiene su propia tarea en el pool de hilos
            notificationExecutor.submit(() -> {
                observer.onPedidoCreado(pedido);
            });
        }
    }
}