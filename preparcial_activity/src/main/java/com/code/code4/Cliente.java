package com.code.code4;

import java.io.*;
import java.net.*;

public class Cliente {
    private static final String DIRECCION_SERVIDOR = "127.0.0.1"; // IP local
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        
        // Números a enviar para la comparación
        int n1 = 45;
        int n2 = 120;
        int n3 = 78;
        
        System.out.println("Cliente intentando conectar con " + DIRECCION_SERVIDOR + ":" + PUERTO + "...");

        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO)) {
            System.out.println("Conexión establecida con el servidor.");
            
            // 1. Configurar streams de entrada/salida
            // DataOutputStream para enviar los números (int) al servidor
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // BufferedReader para leer el resultado (String) del servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // 2. Enviar los tres números al servidor
            System.out.println("Enviando números: " + n1 + ", " + n2 + ", " + n3);
            salida.writeInt(n1);
            salida.writeInt(n2);
            salida.writeInt(n3);
            
            // 3. Recibir la respuesta del servidor
            String respuesta = entrada.readLine();
            
            // 4. Mostrar el resultado
            System.out.println("\n--- Resultado del Servidor ---");
            System.out.println(respuesta);
            System.out.println("-----------------------------\n");
            
        } catch (ConnectException e) {
             System.err.println("Error: No se pudo establecer la conexión. Asegúrate de que el servidor esté ejecutándose en " + DIRECCION_SERVIDOR + ":" + PUERTO);
        } catch (IOException e) {
            System.err.println("Error del cliente: " + e.getMessage());
        }
    }
}