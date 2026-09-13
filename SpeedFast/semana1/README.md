# Sistema de Asignación de Repartos - SpeedFast

Actividad formativa desarrollada en Java utilizando **Programación Orientada a Objetos (POO)** para la empresa de reparto a domicilio **SpeedFast**.

##  Descripción del Proyecto
El sistema implementa una jerarquía de clases aplicando los siguientes conceptos clave de POO:
* **Herencia:** Una clase base `Semana1_SpeedFast_SobreEscritura_SobreCarga.Pedido` que es extendida por tres tipos de servicios específicos.
* **Polimorfismo y Sobrescritura (`@Override`):** El método `asignarRepartidor()` se comporta de manera diferenciada según el tipo de pedido (Comida, Encomienda o Compra Express).
* **Sobrecarga de Métodos (`Overloading`):** Versiones del método `asignarRepartidor(String nombreRepartidor)` que reciben datos específicos del repartidor y validaciones particulares.

##  Estructura de Clases
* `Semana1_SpeedFast_SobreEscritura_SobreCarga.Pedido.java`: Clase base con atributos genéricos (`idPedido`, `direccionEntrega`, `tipoPedido`).
* `Semana1_SpeedFast_SobreEscritura_SobreCarga.PedidoComida.java`: Subclase que valida el uso de mochila térmica.
* `Semana1_SpeedFast_SobreEscritura_SobreCarga.PedidoEncomienda.java`: Subclase que verifica el peso y tipo de embalaje.
* `Semana1_SpeedFast_SobreEscritura_SobreCarga.PedidoExpress.java`: Subclase enfocada en la cercanía y disponibilidad inmediata.
* `Semana1_SpeedFast_SobreEscritura_SobreCarga.Main.java`: Clase principal para la ejecución y prueba de los casos de uso.

##  Requisitos de Ejecución
* Entorno de desarrollo: **IntelliJ IDEA**
* Kit de desarrollo: **Java JDK 26** (o superior)