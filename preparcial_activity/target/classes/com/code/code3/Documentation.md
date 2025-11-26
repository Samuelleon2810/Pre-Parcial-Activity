# 💬 Cliente-Servidor Interactivo (Verificador de Palíndromos)

Este proyecto implementa un sistema de sockets TCP/IP con un **protocolo de diálogo bidireccional** continuo. El **Cliente** permite al usuario enviar repetidamente palabras al **Servidor**, y el Servidor responde inmediatamente indicando si la palabra es un palíndromo (se lee igual al derecho y al revés).

---

## 🎯 Objetivo del Sistema

El objetivo principal es demostrar:

1.  **Comunicación Interactiva:** Un bucle `do-while` que permite múltiples envíos y respuestas en una única conexión TCP.
2.  **Lógica de Negocio en Servidor:** El servidor ejecuta una tarea específica (verificación de palíndromos) con la entrada del cliente.
3.  **Protocolo de Terminación:** Uso de la cadena **"1"** como señal de finalización de la conversación.

---

## ⚙️ Protocolo de Comunicación y Streams

Este sistema utiliza una mezcla de Streams de **caracteres** y **binarios**, lo cual requiere una configuración precisa en ambos extremos:

| Flujo | Dirección | Clase Usada | Propósito |
| :--- | :--- | :--- | :--- |
| **Petición** | Cliente $\to$ Servidor | `PrintWriter` | Envía el texto de la palabra (caracteres). |
| **Recepción (Servidor)** | Servidor $\leftarrow$ Cliente | `BufferedReader` | Lee el texto enviado línea por línea. |
| **Respuesta** | Servidor $\to$ Cliente | `DataOutputStream` | Envía el resultado (`Es palindromo.`) en formato UTF binario. |
| **Recepción (Cliente)** | Cliente $\leftarrow$ Servidor | `DataInputStream` | Lee la respuesta en formato UTF binario. |

### Lógica de Terminación

El ciclo de diálogo persiste hasta que el **Cliente** ingresa `"1"`. El Cliente envía `"1"` al servidor, y ambos programas proceden a cerrar los recursos de red de forma ordenada.

---

## 💻 Proceso Interno del Socket

### Lado del Servidor (Monoconexión)

1.  **Inicialización:** Crea un **`ServerSocket`** en el puerto 5000.
2.  **Aceptación (`yo.accept()`):** El servidor se **bloquea** hasta que se conecta el primer cliente. Una vez conectado, el servidor **solo atiende a ese cliente** y no acepta a ningún otro. 
3.  **Bucle de Recepción:** Entra en el bucle `do-while`. En cada iteración:
    * Se **bloquea** en `entrada.readLine()` esperando la palabra del cliente.
    * Ejecuta la **lógica de palíndromo** (comparación de punteros).
    * Envía la respuesta (`salida.writeUTF(rta)`).
4.  **Cierre:** Al recibir `"1"`, cierra el `Socket` del cliente y el `ServerSocket` principal.

### Lado del Cliente (Interactivo)

1.  **Conexión:** Crea un **`Socket`** que se conecta a `localhost:5000`.
2.  **Bucle de Envío/Espera:** Entra en el bucle `do-while`. En cada iteración:
    * Lee la palabra del teclado (`delTeclado.readLine()`).
    * Envía la palabra al servidor (`alServidor.println(palabra)`).
    * Se **bloquea** en `delServidor.readUTF()` esperando la respuesta del servidor antes de pedir la siguiente palabra.
3.  **Cierre:** Envía el `"1"` final al servidor y cierra todos los flujos y el `Socket`. 

---

## 🚀 Instrucciones de Uso

### Paso 1: Iniciar el Servidor

1.  Compila las clases.
2.  Inicia el servidor en una terminal:
    ```bash
    java com.code.code3.Servidor
    ```

### Paso 2: Iniciar el Cliente

1.  En una terminal separada, inicia el cliente:
    ```bash
    java com.code.code3.Cliente
    ```

### Ejemplo de Interacción (Cliente)

El cliente guiará la interacción: