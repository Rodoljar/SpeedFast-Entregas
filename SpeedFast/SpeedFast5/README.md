# SpeedFast - Módulo Semana 5: Concurrencia y Sincronización

Este módulo implementa el sistema de control de concurrencia y gestión de despacho para **SpeedFast**, asegurando la sincronización de hilos al procesar pedidos en zonas de carga compartidas.

##  Características Principales
* **Procesamiento Concurrente:** Simulación de múltiples repartidores operando en paralelo mediante la interfaz `Runnable` y la clase `Thread`.
* **Control de Regiones Críticas:** Uso de bloques sincronizados (`synchronized`) para evitar condiciones de carrera, prevenir duplicidad en la asignación de pedidos y garantizar la consistencia en el recurso compartido (`ZonaDeCarga`).
* **Modelado de Estados:** Gestión de flujos y estados de los pedidos mediante enumeradores (`EstadoPedido`).

##  Estructura del Código Fuente
* **`main/`**: Clase principal de ejecución y orquestación de hilos.
* **`modelo/`**: Clases de entidad (`Pedido`, `EstadoPedido`).
* **`servicios/`**: Lógica de gestión de recursos y concurrencia (`ZonaDeCarga`, `Repartidor`).

##  Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 17+)
* **Conceptos Aplicados:** Multihilos, Sincronización, Programación Orientada a Objetos (POO).
