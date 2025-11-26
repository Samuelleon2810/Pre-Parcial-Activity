# 🔢 Cliente-Servidor de Comparación de Números (Solicitud-Respuesta)

Este proyecto implementa una aplicación de sockets TCP/IP donde el **Cliente** solicita al usuario tres números enteros, los envía al **Servidor** y espera una respuesta que indica cuál de los tres números es el mayor.

El servidor procesa la solicitud y devuelve el resultado, cerrando la conexión inmediatamente después.

---

## 🎯 Objetivo del Sistema

El objetivo principal es demostrar:

1.  **Transferencia de Datos Tipados:** Uso de **`DataOutputStream.writeInt()`** y **`DataInputStream.readInt()`** para enviar y recibir datos primitivos (enteros) de forma fiable.
2.  **Patrón Solicitud-Respuesta:** El cliente se conecta, envía todos los datos de entrada, recibe una única respuesta del servidor, y luego la conexión termina.
3.  **Lógica de Negocio Remota:** El cliente delega la tarea de **comparación de números** al servidor.

---

## ⚙️ Protocolo de Comunicación y Flujos

El protocolo de este sistema es **estrictamente secuencial y tipado**:

| Flujo | Dirección | Stream Usado | Tipo de Dato | Propósito |
| :--- | :--- | :--- | :--- | :--- |
| **Solicitud (Envío)** | Cliente $\to$ Servidor | `DataOutputStream` | Enteros (`int`) | Envía los 3 números (`n1`, `n2`, `n3`). |
| **Respuesta (Envío)** | Servidor $\to$ Cliente | `PrintWriter` | Cadena (`String`) | Envía el resultado de la comparación. |

### Configuración

| Parámetro | Valor | Descripción |
| :--- | :--- | :--- |
| `DIRECCION_SERVIDOR` | `127.0.0.1` | Dirección de bucle invertido (localhost). |
| `PUERTO` | `5000` | Puerto de escucha y conexión. |

---

## 💻 Proceso Interno de la Comunicación

El proceso se divide claramente en la fase de **Envío de Petición** y la fase de **Recepción de Respuesta**, todas dentro de una única conexión TCP:

### 1. Conexión y Envío de Petición (Cliente)

1.  El **Cliente** lee los tres enteros (`n1`, `n2`, `n3`) del teclado.
2.  Abre un `Socket` con el Servidor (`127.0.0.1:5000`).
3.  Utiliza **`salida.writeInt()`** tres veces consecutivas para enviar los tres números como datos binarios.

### 2. Procesamiento de Petición (Servidor)

1.  El **Servidor** se **bloquea** en `serverSocket.accept()` esperando la conexión.
2.  Una vez conectado el cliente, utiliza **`entrada.readInt()`** tres veces consecutivas para extraer los tres números binarios en el orden correcto.
3.  Ejecuta la lógica de **`comparar(n1, n2, n3)`** para encontrar el mayor.
4.  Envía el **resultado como una cadena** (`salida.println(resultado)`) al cliente.

### 3. Cierre (Unidireccional)

1.  El **Cliente** lee la respuesta del servidor con **`entrada.readLine()`**.
2.  El servidor y el cliente cierran sus respectivos sockets, finalizando la comunicación. 

---

## 🚀 Instrucciones de Uso

### Paso 1: Iniciar el Servidor

1.  Compila las clases.
2.  Inicia el servidor en una terminal:
    ```bash
    java com.code.code4.Servidor
    ```
    El servidor imprimirá: `Servidor iniciado. Esperando conexiones en el puerto 5000...`.

### Paso 2: Iniciar el Cliente

1.  En una terminal separada, inicia el cliente:
    ```bash
    java com.code.code4.Cliente
    ```
2.  El cliente solicitará los tres números por consola.

### Ejemplo de Interacción (Cliente)