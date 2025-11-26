package com.code.code2;

import java.io.*;
import java.net.*;

/**
 * Clase Servidor que implementa un servidor de sockets TCP básico.
 * Escucha en un puerto fijo, acepta un máximo de 3 clientes de forma secuencial,
 * les envía un mensaje de saludo y luego cierra la conexión.
 */
public class Servidor {
    
    // **Constante de Conexión:**
    // Define el puerto TCP/IP donde el servidor estará esperando las conexiones entrantes.
    final int PUERTO = 5000;
    
    /**
     * Constructor de la clase Servidor. Contiene la lógica principal para 
     * inicializar el servidor y gestionar la aceptación de clientes.
     */
    public Servidor() {
        // Envolver toda la lógica de sockets en un try-catch es crucial 
        // para manejar errores como la imposibilidad de enlazar el puerto (ej., ya está en uso).
        try {
            // 1. INICIALIZACIÓN DEL SERVIDOR
            
            // Crea un ServerSocket, que es el "listener" (oyente) del servidor.
            // Esto enlaza el servidor a la dirección IP local (0.0.0.0 o similar) y al puerto especificado.
            ServerSocket sServidor = new ServerSocket(PUERTO);
            
            System.out.println("Servidor --> Puerto de Escucha: " + PUERTO);
            
            // 2. BUCLE DE ESCUCHA Y ACEPTACIÓN (Limitado)
            
            // El servidor está configurado para aceptar exactamente 3 conexiones de clientes.
            // Un servidor típico usaría un bucle infinito (`while(true)`) para escucha continua.
            for (int numCli = 0; numCli < 3; numCli++) {
                
                // *** Operación Bloqueante: sServidor.accept() ***
                // El hilo de ejecución se detiene aquí, esperando que un cliente 
                // inicie una conexión (Handshake TCP).
                // Cuando un cliente se conecta, este método devuelve un nuevo objeto Socket (`sCliente`).
                // Este nuevo Socket se utiliza para la comunicación con el cliente específico.
                Socket sCliente = sServidor.accept(); // Crea el objeto de comunicación individual
                
                System.out.println("Sirvo al cliente " + numCli);
                
                // 3. OBTENER FLUJO DE SALIDA DE DATOS
                
                // Obtiene el OutputStream básico del socket. Este es el conducto binario
                // crudo por donde el servidor enviará los bytes al cliente.
                OutputStream aux = sCliente.getOutputStream();
                
                // Envuelve el OutputStream crudo en un DataOutputStream.
                // Esto permite enviar tipos de datos Java primitivos de manera estructurada (ej., writeUTF()).
                DataOutputStream flujo = new DataOutputStream(aux);
                
                // 4. ENVÍO DE DATOS
                
                // Envía una cadena de texto al cliente, incluyendo su número y la IP que el servidor 
                // ve del cliente (`sCliente.getInetAddress()`).
                // El formato de escritura debe coincidir con la lectura del cliente (`flujo.readUTF()`).
                flujo.writeUTF("Cliente No.: " + numCli +" Direccion IP Cliente: "+sCliente.getInetAddress());
                
                // 5. CIERRE DE RECURSOS DEL CLIENTE
                
                // Cierra el socket específico que se usó para la comunicación con este cliente.
                // Esto termina la conexión TCP con ese cliente y libera recursos.
                // El ServerSocket (`sServidor`) sigue abierto y listo para aceptar el próximo cliente.
                sCliente.close();
            }
            
            // 6. CIERRE DEL SERVIDOR
            
            System.out.println("Demasiados clientes por hoy");
            
        } catch (Exception e) {
            // Manejo de excepciones (ej., puerto en uso, error de red).
            System.out.println(e.getMessage());
        }
    }

    /**
     * Punto de entrada de la aplicación.
     */
    public static void main(String[] arg) {
        // Instancia la clase Servidor, lo que inicia la lógica de escucha.
        new Servidor();
    }
}