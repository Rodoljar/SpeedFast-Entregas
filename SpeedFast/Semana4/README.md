#  - SpeedFast-semana 4_Ejecutando tareas en paralelo con hilos en Java

Proyecto desarrollado en **Java** para la simulación de procesamiento concurrente de entregas y pedidos. El sistema implementa programación orientada a objetos avanzada, jerarquías de clases, interfaces, polimorfismo y concurrencia mediante `ExecutorService`.

---

##  Estructura del Proyecto

El proyecto está organizado en paquetes siguiendo buenas prácticas de arquitectura de software y separación de responsabilidades:

```text
src/
└── com/
    └── speedfast/
        ├── concurrencia/
        │   └── Repartidor.java         # Hilo encargado de procesar los pedidos asignados de forma concurrente
        ├── interfaces/
        │   ├── Cancelable.java         # Contrato para operaciones cancelables
        │   ├── Despachable.java        # Contrato para el despacho de pedidos
        │   └── Rastreable.java         # Contrato para el seguimiento de ubicación/estado
        ├── main/
        │   └── Main.java               # Clase principal de ejecución y orquestación del ExecutorService
        └── modelo/
            ├── Pedido.java             # Clase abstracta base para todo tipo de pedidos
            ├── PedidoComida.java       # Subclase para pedidos de comida caliente
            ├── PedidoEncomienda.java   # Subclase para encomiendas con peso y dimensiones
            └── PedidoExpress.java      # Subclase para entregas prioritarias/express