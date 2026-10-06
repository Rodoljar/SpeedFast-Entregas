package com.speedfast;

import com.speedfast.dao.*;
import com.speedfast.modelo.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class PruebaEntrega {
    public static void main(String[] args) {
        RepartidorDAO repartidorDAO = new RepartidorDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        EntregaDAO entregaDAO = new EntregaDAO();

        try {

            repartidorDAO.create(new Repartidor("Carlos Perez"));
            pedidoDAO.create(new Pedido("Av. Las Condes 1000", TipoPedido.COMIDA, EstadoPedido.PENDIENTE));

            Repartidor repartidor = repartidorDAO.readAll().get(0);
            Pedido pedido = pedidoDAO.readAll().get(0);


            Entrega nuevaEntrega = new Entrega(pedido, repartidor, LocalDate.now(), LocalTime.now());
            entregaDAO.create(nuevaEntrega);
            System.out.println("Después de crear entrega: " + entregaDAO.readAll());


            Entrega entregaRegistrada = entregaDAO.readAll().get(0);
            entregaRegistrada.setFecha(LocalDate.now().plusDays(1));
            entregaDAO.update(entregaRegistrada);
            System.out.println("Después de actualizar fecha: " + entregaDAO.readAll());


            entregaDAO.delete(entregaRegistrada.getId());
            pedidoDAO.delete(pedido.getId());
            repartidorDAO.delete(repartidor.getId());

            System.out.println("Después de eliminar registros: " + entregaDAO.readAll());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

