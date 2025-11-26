# 🤝 Comunicación Unidireccional Cliente-Servidor (TCP Básico)

Este proyecto consta de dos componentes (`Cliente` y `Servidor`) que demuestran una comunicación simple basada en sockets TCP/IP. El servidor es un punto de escucha limitado que acepta **tres clientes secuenciales** y les envía un mensaje de bienvenida único.

---

## 🎯 Objetivo del Sistema

El objetivo de este sistema es ilustrar los conceptos fundamentales de los sockets Java:

1.  **Enlace (Bind) y Escucha (Listen):** Uso de `ServerSocket` en el servidor.
2.  **Conexión (Connect) y Handshake:** Uso de `Socket` en el cliente.
3.  **Comunicación Unidireccional:** El flujo de datos es **del Servidor al Cliente** (el cliente solo lee).
4.  **Flujos de Datos Primitivos:** Uso de `DataInputStream` y `DataOutputStream` para la compatibilidad de mensajes.

---

## ⚙️ Configuración y Requisitos

| Componente | Rol | Dirección | Puerto |
| :--- | :--- | :--- | :--- |
| **Servidor** | Oyente | `localhost` | `5000` |
| **Cliente** | Conector | `localhost` | `5000` |

### Flujo de Datos

| Dirección | Flujo Usado | Propósito |
| :--- | :--- | :--- |
| Servidor $\to$ Cliente | `DataOutputStream` | Escribe una cadena (`writeUTF`). |
| Cliente $\leftarrow$ Servidor | `DataInputStream` | Lee la cadena (`readUTF`). |

---

## 💻 Proceso Interno de la Comunicación

El sistema opera en un flujo secuencial estricto:

### 1. Inicialización del Servidor

El servidor se inicia creando un **`ServerSocket`** en el puerto 5000 y entra en un bucle `for` limitado (máximo 3 veces).

### 2. Conexión y Bloqueo

* El servidor ejecuta **`sServidor.accept()`**, bloqueando el hilo principal.
* Cuando un cliente se inicia (`new Socket("localhost", 5000)`), inicia el Handshake TCP.
* Si es exitoso, **`accept()` devuelve un nuevo `Socket`** (`sCliente`) para la comunicación.

### 3. Transferencia de Mensaje

1.  **Servidor:** Utiliza `DataOutputStream` para enviar la cadena de bienvenida (incluyendo el número de cliente y la IP).
2.  **Cliente:** Utiliza `DataInputStream` y se **bloquea** en `readUTF()` hasta recibir el mensaje completo.

### 4. Cierre y Límite

* El servidor cierra el socket del cliente (`sCliente.close()`) inmediatamente después de enviar el mensaje.
* El cliente cierra su socket después de leer.
* El servidor repite este proceso hasta atender al **tercer cliente**, tras lo cual, el programa `Servidor` finaliza. 

---

## 🚀 Instrucciones de Uso

Para probar este sistema, debes compilar y ejecutar las clases en el orden correcto:

### Paso 1: Iniciar el Servidor

1.  Compila las clases (`Cliente.java` y `Servidor.java`).
2.  Inicia el servidor en una terminal:
    ```bash
    java com.code.code2.Servidor
    ```
    El servidor imprimirá: `Servidor --> Puerto de Escucha: 5000`.

### Paso 2: Conectar los Clientes

1.  En una o varias terminales separadas, ejecuta el cliente **hasta tres veces**:
    ```bash
    java com.code.code2.Cliente
    ```

### Comportamiento Esperado

| Programa | Después de 3 Clientes |
| :--- | :--- |
| **Cliente** | Se conecta, imprime el saludo y **termina** inmediatamente. |
| **Servidor** | Después de atender al tercer cliente, imprime `Demasiados clientes por hoy` y **termina** el programa. |

---

## 🖼️ Muestra de Ejecución