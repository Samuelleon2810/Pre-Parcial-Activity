package com.code.code3;

import java.io.*;
import java.net.*;
/**
 * Cliente interactivo de sockets TCP/IP.
 * Permite al usuario enviar múltiples cadenas de texto al servidor (localhost:5000)
 * y recibir una respuesta inmediata para cada una, hasta que se ingresa el comando "1".
 */
public class Cliente {
    /**
     * Punto de entrada de la aplicación.
     * Permite al usuario enviar múltiples cadenas de texto al servidor (localhost:5000)
     * y recibir una respuesta inmediata para cada una, hasta que se ingresa el comando "1".
     * La conexión se establece en un socket TCP/IP y se utiliza un flujo de lectura
     * y otro de escritura para manejar la comunicación con el servidor.
     * La lectura y escritura se realizan en formato UTF, lo que garantiza la compatibilidad
     * entre sistemas operativos y lenguajes de programación.
     * La aplicación se encarga de manejar las excepciones de I/O que pueden ocurrir durante la
     * conexión o lectura/escritura.
     */
    public static void main(String[] comandos) {
        // Declaración de variables clave para la conexión y los flujos de I/O.
        Socket yo = null;               // El socket que representa la conexión al servidor.
        PrintWriter alServidor;         // Stream de caracteres para enviar datos al servidor (fácil de usar).
        BufferedReader delTeclado;      // Stream para leer la entrada de texto desde la consola (System.in).
        DataInputStream delServidor;    // Stream binario para recibir datos del servidor.
        String palabra;                 // Variable para almacenar la entrada del usuario.

        // Bloque try externo: Manejo general de excepciones de I/O.
        try {
            // Bloque try anidado: Específicamente para manejar la UnknownHostException 
            // durante el intento de conexión del socket.
            try {
                // 1. ESTABLECIMIENTO DE LA CONEXIÓN
                
                // Intenta establecer la conexión TCP con el servidor en localhost:5000.
                // Si la conexión falla (servidor no está o puerto incorrecto), lanza una excepción.
                yo = new Socket("localhost", 5000);
            } catch (UnknownHostException e) {
                // Captura si el nombre "localhost" no se pudo resolver (un caso raro, pero posible).
                System.out.println(e.getMessage());
                System.exit(1); // Terminación forzada si el host no se encuentra.
            }
            
            System.out.println("Ya se conecto al Servidor");
            
            // 2. INICIALIZACIÓN DE LOS STREAMS DE I/O

            // **Flujo de Entrada del Usuario (Teclado):**
            // 2.1. System.in (bytes) -> InputStreamReader (bytes a caracteres) -> BufferedReader (lectura eficiente de líneas).
            delTeclado = new BufferedReader(new InputStreamReader(System.in));
            
            // **Flujo de Salida al Servidor (Escritura):**
            // Crea un flujo de salida de caracteres sobre el OutputStream del socket.
            // El parámetro 'true' habilita el **auto-flush**, asegurando que los datos se envíen
            // inmediatamente al servidor después de cada llamada a `println()`.
            alServidor = new PrintWriter(yo.getOutputStream(), true);
            
            // **Flujo de Entrada desde el Servidor (Lectura):**
            // Se utiliza DataInputStream para leer datos binarios o tipos primitivos (como UTF).
            // Esto implica que el servidor debe usar DataOutputStream o DataOutputStream.writeUTF().
            delServidor = new DataInputStream(yo.getInputStream());
            
            // 3. BUCLE DE INTERACCIÓN (Protocolo de Diálogo)
            
            do {
                System.out.print("Digite la palabra a comparar (1 para terminar): ");
                
                // 3.1. Lectura del usuario (bloqueante)
                palabra = delTeclado.readLine();
                
                // 3.2. Procesamiento y envío
                if (!palabra.equals("1")) {
                    // Envía la cadena de texto al servidor (el auto-flush la envía de inmediato).
                    alServidor.println(palabra);
                    
                    // **Bloqueo:** Espera la respuesta del servidor en formato UTF.
                    // Si el servidor no envía nada o envía datos en otro formato, 
                    // el cliente se bloqueará o fallará.
                    System.out.println("Rta del Servidor = " + delServidor.readUTF());
                }
            // 3.3. Condición de salida del bucle
            } while (!palabra.equals("1"));

            // 4. CIERRE DE LA SESIÓN Y RECURSOS
            
            // Envía el comando final ("1") al servidor para que el servidor sepa que el diálogo terminó.
            alServidor.println("1"); 
            System.out.println("Ya termine de enviar");
            
            // Cierre de flujos y socket.
            delServidor.close();
            delTeclado.close();
            alServidor.close();
            yo.close();
            
        } catch (IOException e) {
            // Captura errores de I/O que pueden ocurrir en la conexión o durante la lectura/escritura.
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}