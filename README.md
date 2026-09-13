# SpeedFast - Sistema de Gestión de Entregas

**SpeedFast** es una solución integral orientada a la optimización, control y automatización de procesos logísticos y de despacho. 

##  Arquitectura y Módulos del Sistema

El proyecto está diseñado bajo una arquitectura modular para asegurar la escalabilidad y el rendimiento de las operaciones:

* **Gestión de Entidades y Datos:** Estructuras robustas para el manejo y validación de la información de pedidos, clientes y zonas operativas.
* **Módulo de Concurrencia y Despacho:** Implementación de procesamiento concurrente mediante hilos (`Thread`) en Java, permitiendo la sincronización en tiempo real de múltiples repartidores sobre zonas de carga compartidas, asegurando la consistencia de los datos y evitando duplicidad de entregas.
* **Lógica de Negocio:** Reglas de validación y control de tiempos de despacho adaptadas a los requerimientos de la operación logística.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 17+)
* **Entorno de Desarrollo:** IntelliJ IDEA
* **Control de Versiones:** Git / GitHub
