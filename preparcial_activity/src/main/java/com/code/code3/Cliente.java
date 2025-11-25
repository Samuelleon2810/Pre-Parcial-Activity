package com.code.code3;

import java.io.*;
import java.net.*;
public class Cliente {
 public static void main(String[] comandos) {
 Socket yo = null;
 PrintWriter alServidor;
 BufferedReader delTeclado;
 DataInputStream delServidor;
 String palabra;
    try {
        try {
            yo = new Socket("localhost", 5000);
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Ya se conecto al Servidor");
        delTeclado = new BufferedReader(new InputStreamReader(System.in));
        alServidor = new PrintWriter(yo.getOutputStream(), true);
        delServidor = new DataInputStream(yo.getInputStream());
        do {
            System.out.print("Digite la palabra a comparar (1 para terminar): ");
            palabra = delTeclado.readLine();
            if (!palabra.equals("1")) {
                alServidor.println(palabra);
                System.out.println("Rta del Servidor = " + delServidor.readUTF());
            }
        } while (!palabra.equals("1"));
        alServidor.println("1");
        System.out.println("Ya termine de enviar");
        delServidor.close();
        delTeclado.close();
        alServidor.close();
        yo.close();
    } catch (IOException e) {
        System.err.println(e.getMessage());
        System.exit(1);
    }
    }
}