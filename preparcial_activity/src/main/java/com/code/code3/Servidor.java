package com.code.code3;

import java.io.*;
import java.net.*;
/**
 * Servidor de sockets TCP que acepta una sola conexión de cliente y 
 * entra en un bucle interactivo para recibir cadenas de texto.
 * La lógica de negocio principal es determinar si la cadena recibida es un palíndromo.
 * Utiliza el string "1" como comando de terminación.
 */
class Servidor {
    
    // La variable PUERTO está definida pero no se usa en la inicialización (ver línea 30).
    final int PUERTO = 5000;
    
    /**
     * Constructor que contiene toda la lógica de inicialización, escucha,
     * comunicación interactiva y cierre.
     */
    public Servidor() {
        // Declaración de variables clave para la conexión y los flujos de I/O, inicializadas a null.
        ServerSocket yo = null;     // El socket oyente (listener) del servidor.
        Socket cliente = null;      // El socket individual para la comunicación con el cliente.
        BufferedReader entrada;     // Stream de caracteres para leer del cliente (debe coincidir con PrintWriter del cliente).
        DataOutputStream salida;    // Stream binario para escribir la respuesta al cliente.
        String llego;               // Almacena el mensaje (palabra) recibido del cliente.
        
        // Bloque try principal para manejar errores críticos de Sockets y I/O.
        try {
            // 1. INICIALIZACIÓN DEL SERVIDOR
            
            // Crea e inicializa el ServerSocket, uniéndolo al puerto 5000.
            yo = new ServerSocket(5000); 
            System.out.println("Socket escuchando en puerto 5000");
            
            // 2. ACEPTACIÓN DE LA CONEXIÓN (Bloqueante y Monohilo)
            
            // El hilo se bloquea aquí hasta que un cliente inicie una conexión.
            // Una vez conectado, ServerSocket retorna un nuevo Socket para la comunicación.
            // **LÍMITE**: Este servidor solo acepta UN cliente y luego se dedica a él.
            cliente = yo.accept();
            System.out.println("Ya se conecto el cliente");
            
            // 3. INICIALIZACIÓN DE LOS STREAMS DE I/O

            // **Flujo de Entrada (desde el Cliente):**
            // Se utiliza BufferedReader para leer líneas de texto del socket, 
            // lo que es compatible con el PrintWriter.println() del Cliente.
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            // **Flujo de Salida (al Cliente):**
            // Se utiliza DataOutputStream para enviar tipos primitivos (writeUTF).
            // Esto es compatible con el DataInputStream.readUTF() del Cliente.
            salida = new DataOutputStream(cliente.getOutputStream());
            
            // 4. BUCLE DE INTERACCIÓN Y LÓGICA DE NEGOCIO
            
            do {
                // **Bloqueo:** El servidor espera aquí a que el cliente envíe una línea de texto.
                // Si el cliente cierra la conexión, esta línea devolverá null o lanzará una excepción.
                llego = entrada.readLine();
                
                // 4.1. Verificación del comando de terminación
                if (!llego.equals("1")) {
                    System.out.println("Palabra a comparar: " + llego);
                    
                    // Lógica para verificar si la palabra es un palíndromo (ej. 'reconocer', 'ana').
                    int fin = llego.length() - 1; // Índice del último carácter.
                    int ini = 0;                  // Índice del primer carácter.
                    boolean espalin = true;
                    
                    // Bucle que compara los caracteres de forma simétrica desde los extremos hacia el centro.
                    while (ini < fin) {
                        if (llego.charAt(ini) != llego.charAt(fin)) {
                            espalin = false; // Se encuentra una discrepancia, no es palíndromo.
                        }
                        ini++; // Avanza el índice inicial.
                        fin--; // Retrocede el índice final.
                    }
                    
                    // 4.2. Preparación y envío de la respuesta
                    String rta;
                    if (espalin) {
                        rta="Es palindromo.";
                    } else {
                        rta="No es palindromo.";
                    }
                    
                    // Envía la respuesta al cliente usando el formato UTF (para DataInputStream).
                    salida.writeUTF(rta);
                }
            // 4.3. Condición de salida: Continúa hasta que el cliente envíe el comando "1".
            } while (!llego.equals("1"));
            
            // 5. CIERRE DE RECURSOS
            
            System.out.println("Ya termine de recibir");
            // Se cierran los flujos y luego los sockets para liberar recursos del sistema.
            entrada.close();
            cliente.close(); // Cierra el socket de comunicación con el cliente.
            yo.close();      // Cierra el socket de escucha del servidor, liberando el puerto 5000.
            
        } catch (IOException e) {
            // Captura errores de I/O (fallos de conexión, lectura, escritura).
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Punto de entrada de la aplicación.
     */
    public static void main(String[] arg) {
        // Instancia la clase Servidor, lo que inicia toda la lógica de sockets.
        new Servidor();
    }
}