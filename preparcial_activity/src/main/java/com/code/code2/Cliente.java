package com.code.code2;

import java.io.*;
import java.net.*;


/**
 * Clase Cliente que establece una conexión de socket TCP con un servidor
 * en localhost:5000, recibe un mensaje de texto y luego cierra la conexión.
 * Implementación de cliente muy básica, sin manejo robusto de excepciones.
 */
class Cliente {
    
    // **Constantes de Conexión:**
    // Define el nombre de host o la dirección IP del servidor al que se intentará conectar.
    // 'localhost' se resuelve a la IP de bucle invertido (loopback) 127.0.0.1.
    final String HOST = "localhost";
    
    // Define el puerto TCP/IP que el servidor debe estar escuchando.
    final int PUERTO = 5000;

    /**
     * Constructor de la clase Cliente. Contiene la lógica principal para 
     * iniciar la conexión y recibir datos.
     */
    public Cliente() {
        // El bloque try-catch es esencial para manejar fallos de red (conexión rechazada, timeout, etc.).
        try {
            // 1. ESTABLECIMIENTO DE LA CONEXIÓN (Handshake TCP)
            
            // Se crea un nuevo objeto Socket, lo que inicia automáticamente el 
            // intento de conexión TCP/IP al servidor especificado (HOST:PUERTO).
            // Si el servidor no está escuchando, se lanzará una excepción (ConnectException).
            Socket sCliente = new Socket(HOST, PUERTO);
            
            // 2. OBTENER FLUJO DE ENTRADA DE DATOS
            
            // Obtiene el InputStream básico del socket. Este es el conducto binario 
            // crudo por donde el cliente recibirá los bytes enviados por el servidor.
            InputStream aux = sCliente.getInputStream();
            
            // Se envuelve el InputStream crudo (`aux`) en un DataInputStream.
            // Esto permite leer tipos de datos Java primitivos (como String con readUTF()).
            // Es crucial que el servidor use DataOutputStream para que el flujo sea compatible.
            DataInputStream flujo = new DataInputStream(aux);
            
            // 3. RECEPCIÓN DE DATOS
            
            // Llama a readUTF() para leer una cadena de texto codificada en formato UTF-8 modificado
            // que fue enviada previamente por el DataOutputStream del servidor.
            // La ejecución se bloquea aquí hasta que el servidor envíe los datos.
            System.out.println(flujo.readUTF());
            
            // 4. CIERRE DE RECURSOS
            
            // Cierra el socket. Esto libera el puerto local y notifica al servidor 
            // que la conexión se ha terminado correctamente (FIN/ACK).
            // NOTA: Cerrar el socket también cierra automáticamente los streams asociados (aux y flujo).
            sCliente.close();
            
        } catch (Exception e) {
            // Manejo genérico de excepciones.
            // En un código de producción, se capturarían excepciones más específicas 
            // (e.g., IOException, ConnectException) para dar un diagnóstico más preciso.
            System.out.println(e.getMessage());
            // **NOTA SENIOR:** Idealmente se usaría un bloque 'finally' o try-with-resources
            // para asegurar que sCliente.close() se llame incluso si ocurre una excepción
            // antes del cierre normal.
        }
    }

    /**
     * Punto de entrada de la aplicación.
     */
    public static void main(String[] arg) {
        // Al instanciar la clase Cliente, se ejecuta el constructor, que a su vez
        // inicia la lógica de conexión y recepción de datos.
        new Cliente();
    }
}