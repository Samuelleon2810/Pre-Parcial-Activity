package com.code.code3;

import java.io.*;
import java.net.*;
class Servidor {
    int PUERTO = 5000;
    public Servidor() {
    ServerSocket yo = null;
    Socket cliente = null;
    BufferedReader entrada;
    DataOutputStream salida;
    String llego;
    try {
        yo = new ServerSocket(5000);
        System.out.println("Socket escuchando en puerto 5000");
        cliente = yo.accept();
        System.out.println("Ya se conecto el cliente");
        entrada = new BufferedReader(new
        InputStreamReader(cliente.getInputStream()));
        salida = new DataOutputStream(cliente.getOutputStream());
        do {
            llego = entrada.readLine();
            if (!llego.equals("1")) {
                System.out.println("Palabra a comparar: " + llego);
                int fin = llego.length() - 1;
                int ini = 0;
                boolean espalin = true;
                while (ini < fin) {
                    if (llego.charAt(ini) != llego.charAt(fin)) {
                        espalin = false;
                    }
                    ini++;
                    fin--;
                }
                String rta;
                if (espalin) {
                    rta="Es palindromo.";
                } else {
                    rta="No es palindromo.";
                }
                salida.writeUTF(rta);
            }
        } while (!llego.equals("1"));
            System.out.println("Ya termine de recibir");
            entrada.close();
            cliente.close();
            yo.close();
    } catch (IOException e) {
        System.err.println(e.getMessage());
        System.exit(1);
    }
}
    public static void main(String[] arg) {
        new Servidor();
    }
}