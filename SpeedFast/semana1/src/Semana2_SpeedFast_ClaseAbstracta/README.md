## Semana 2: Definición de Clases Abstractas y Jerarquía

En esta segunda actividad del proyecto **SpeedFast**, se profundizó en los principios de la Programación Orientada a Objetos (POO) mediante el uso de **clases abstractas, herencia y polimorfismo**.

###  Objetivos de la Actividad
* Modelar una clase abstracta base (`Pedido`) que centraliza atributos comunes y define un contrato obligatorio (`calcularTiempoEntrega()`).
* Implementar clases derivadas con lógica de negocio diferenciada para el cálculo de tiempos.
* Aplicar polimorfismo en la clase `Main` para procesar múltiples tipos de pedidos de forma genérica y comparativa.

###  Estructura de Clases Implementadas
1. **`Pedido` (Clase Abstracta):**
    * **Atributos:** `idPedido`, `direccionEntrega`, `distanciaKm`.
    * **Método Concreto:** `mostrarResumen()` para la visualización de datos básicos.
    * **Método Abstracto:** `calcularTiempoEntrega()` (obligatorio para las subclases).
2. **`PedidoComida`:** Calcula el tiempo sumando 15 min base + 2 min por cada kilómetro.
3. **`PedidoEncomienda`:** Calcula el tiempo sumando 20 min base + 1.5 min por kilómetro (ajustado a valor entero).
4. **`PedidoExpress`:** Establece 10 min base, agregando 5 min extra si la distancia supera los 5 km.
5. **`Main`:** Instancia objetos de cada subclase utilizando referencias de tipo `Pedido` (polimorfismo) y despliega resultados comparativos en consola.