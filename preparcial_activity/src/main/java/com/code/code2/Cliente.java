package com.code.code2;

import java.io.*;
import java.net.*;


class Cliente {
    String HOST = "localhost";
    int PUERTO = 5000;

    public Cliente() {
        try {
            Socket sCliente = new Socket(HOST, PUERTO);
            InputStream aux = sCliente.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);
            System.out.println(flujo.readUTF());
            sCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] arg) {
        new Cliente();
    }
}
