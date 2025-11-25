package com.code.code2;

import java.io.*;
import java.net.*;

public class Servidor {
    int PUERTO = 5000;
    public Servidor() {
        try {
            ServerSocket sServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor --> Puerto de Escucha: " + PUERTO);
        for (int numCli = 0; numCli < 3; numCli++) {
            Socket sCliente = sServidor.accept(); // Crea objeto
            System.out.println("Sirvo al cliente " + numCli);
            OutputStream aux = sCliente.getOutputStream();
            DataOutputStream flujo = new DataOutputStream(aux);
            flujo.writeUTF("Cliente No.: " + numCli +" Direccion IP Cliente: "+sCliente.getInetAddress());
            sCliente.close();
        }
        System.out.println("Demasiados clientes por hoy");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }
    public static void main(String[] arg) {
        new Servidor();
    }
}
