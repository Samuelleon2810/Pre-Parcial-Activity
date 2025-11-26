package com.code.code4;



import java.io.*;
import java.net.*;
import java.util.Scanner; // Importamos la clase Scanner

public class Cliente {
    private static final String DIRECCION_SERVIDOR = "127.0.0.1"; // IP local
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        
        // Usamos Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        int n1;
        int  n2;
        int  n3;
        
        System.out.println("--- Aplicación Cliente de Comparación de Números ---");
        
        // 1. Pedir los tres números al usuario
        try {
            System.out.print("Introduce el primer número (n1): ");
            n1 = scanner.nextInt();
            
            System.out.print("Introduce el segundo número (n2): ");
            n2 = scanner.nextInt();
            
            System.out.print("Introduce el tercer número (n3): ");
            n3 = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Error de entrada: Por favor, introduce solo números enteros.");
            scanner.close();
            return; // Termina el programa si la entrada es incorrecta
        }

        System.out.println("\nCliente intentando conectar con " + DIRECCION_SERVIDOR + ":" + PUERTO + "...");

        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO)) {
            System.out.println("Conexión establecida con el servidor.");
            
            // 2. Configurar streams de entrada/salida
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // 3. Enviar los tres números al servidor
            System.out.println("Enviando números al servidor: " + n1 + ", " + n2 + ", " + n3);
            salida.writeInt(n1);
            salida.writeInt(n2);
            salida.writeInt(n3);
            
            // 4. Recibir la respuesta del servidor
            String respuesta = entrada.readLine();
            
            // 5. Mostrar el resultado
            System.out.println("\n--- Respuesta del Servidor ---");
            System.out.println(respuesta);
            System.out.println("------------------------------");
            
        } catch (ConnectException e) {
             System.err.println("Error: No se pudo establecer la conexión. Asegúrate de que el **Servidor** esté ejecutándose en " + DIRECCION_SERVIDOR + ":" + PUERTO);
        } catch (IOException e) {
            System.err.println("Error de comunicación del cliente: " + e.getMessage());
        } finally {
            // Cerramos el scanner al finalizar todo el proceso del cliente
            scanner.close();
        }
    }
}