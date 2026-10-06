package com.speedfast;

import com.speedfast.dao.RepartidorDAO;
import com.speedfast.modelo.Repartidor;

public class PruebaRepartidor {
    public static void main(String[] args) {
        RepartidorDAO dao = new RepartidorDAO();
        try {
            dao.create(new Repartidor("Juan Perez"));
            System.out.println("Después de crear: " + dao.readAll());

            Repartidor primero = dao.readAll().get(0);
            primero.setNombre("Juan Pablo Perez");
            dao.update(primero);
            System.out.println("Después de editar: " + dao.readAll());

            dao.delete(primero.getId());
            System.out.println("Después de eliminar: " + dao.readAll());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
