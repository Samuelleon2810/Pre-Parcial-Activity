package com.code.code4;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado. Esperando conexiones en el puerto " + PUERTO + "...");
            
            // 1. Esperar y aceptar la conexión del cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());
            
            // 2. Configurar streams de entrada/salida
            // DataInputStream para leer los números (int) enviados por el cliente
            DataInputStream entrada = new DataInputStream(clientSocket.getInputStream());
            // PrintWriter para enviar el resultado (String) al cliente
            PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // 3. Recibir los tres números
            int num1 = entrada.readInt();
            int num2 = entrada.readInt();
            int num3 = entrada.readInt();
            System.out.println("Números recibidos: " + num1 + ", " + num2 + ", " + num3);
            
            // 4. Comparar los números y obtener el resultado
            String resultado = comparar(num1, num2, num3);
            
            // 5. Enviar el resultado al cliente
            salida.println(resultado);
            System.out.println("Resultado enviado al cliente.");

            // 6. Cerrar la conexión con el cliente
            clientSocket.close();
            System.out.println("Conexión con el cliente cerrada.");
            
        } catch (IOException e) {
            System.err.println("Error del servidor: " + e.getMessage());
        }
    }

    /**
     * Función que compara los tres números y devuelve una cadena con el resultado.
     * @param a Primer número.
     * @param b Segundo número.
     * @param c Tercer número.
     * @return Cadena que indica cuál es el mayor.
     */
    public static String comparar(int a, int b, int c) {
        int mayor = a;
        if (b > mayor) {
            mayor = b;
        }
        if (c > mayor) {
            mayor = c;
        }
        return "El número mayor entre " + a + ", " + b + " y " + c + " es: " + mayor;
    }
}