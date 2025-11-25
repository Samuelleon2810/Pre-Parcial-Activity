package com.code.code1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PruebaSockets {

/**
 * Imprime la direccion IP de una URL y la direccion IP actual del
 * LocalHost. Ademas muestra la direccion IP de la URL y la
 * direccion IP actual del LocalHost. Tambien muestra el nombre
 * actual del LocalHost y su direccion IP actual.
 * 
 * @param args Los argumentos de la linea de comandos.
 * 
 * @throws UnknownHostException Si no se puede obtener la direccion
 * IP de la URL o del LocalHost.
 * 
 * @see java.net.InetAddress#getByName(String)
 * @see java.net.InetAddress#getLocalHost()
 * @see java.net.InetAddress#getHostName()
 * @see java.net.InetAddress#getAddress()
 */
public static void main(String[] args) {
    try {
        // 1. OBTENER INFORMACIÓN DE UNA URL ESPECÍFICA (www.udistrital.edu.co)
        
        System.out.println("URL & Direccioon IP:");
        
        // Llama al servicio DNS para resolver el nombre de host a una dirección IP.
        // Esto crea un objeto InetAddress que contiene tanto el hostname como su IP.
        InetAddress direccion = InetAddress.getByName("www.udistrital.edu.co");
        
        // Imprime el formato estándar de InetAddress: "nombre_host/ip_address"
        System.out.println(direccion);
        
        // Bloque para extraer y reimprimir SOLO la dirección IP.
        System.out.println("Direccion IP: " );
        
        // Busca la posición del carácter separador '/' en el String (e.g., 200.7.10.155)
        int temp = direccion.toString().indexOf('/');
        
        // Extrae la subcadena que representa SOLAMENTE la IP (lo que está después de '/')
        String ipAddressString = direccion.toString().substring(temp + 1);
        
        // Vuelve a llamar a getByName, pero ahora usando la IP como argumento.
        // Esto resuelve la IP de vuelta a un nombre (si existe), o simplemente
        // retorna un InetAddress conteniendo solo la IP.
        // También sobrescribe la variable 'direccion'.
        direccion = InetAddress.getByName(ipAddressString);
        
        // Imprime el nuevo objeto InetAddress (que probablemente ahora sea solo la IP).
        System.out.println(direccion);
        
        // 2. OBTENER INFORMACIÓN DEL LOCALHOST (Esta Máquina)
        
        System.out.println("Nombre & Direccion IP actual de LocalHost" );
        
        // Obtiene la instancia de InetAddress que representa la máquina local.
        // Es crucial para aplicaciones de servidor o para conocer la identidad de red.
        // Sobrescribe nuevamente la variable 'direccion'.
        direccion = InetAddress.getLocalHost();
        
        // Imprime el formato estándar del LocalHost: "nombre_local/ip_local"
        System.out.println(direccion);
        
        // Bloque para extraer y reimprimir SOLO la dirección IP del LocalHost.
        System.out.println("Direccion IP del LocalHost: " );
        temp = direccion.toString().indexOf('/');
        ipAddressString = direccion.toString().substring(temp + 1);
        direccion = InetAddress.getByName(ipAddressString); // Re-resuelve la IP.
        System.out.println(direccion);
        
        // 3. OBTENER EL NOMBRE DE HOST DE LA INSTANCIA ACTUAL
        
        System.out.println("Nombre actual de LocalHost" );
        // Usa el método canónico y eficiente para obtener el nombre de host.
        System.out.println(direccion.getHostName());
        
        // 4. OBTENER LA DIRECCIÓN IP COMO ARREGLO DE BYTES
        
        System.out.println("Direccion IP actual de LocalHost" );
        // Usa el método estándar para obtener la IP como un arreglo de 4 bytes (IPv4).
        byte[] bytes = direccion.getAddress();
        
        // Itera sobre el arreglo de bytes para imprimir la IP en formato decimal (dot-decimal).
        for (int cnt = 0; cnt < bytes.length; cnt++) {
            // Conversión de 'byte' a 'int' sin signo (unsigned).
            // En Java, 'byte' es firmado (signed, de -128 a 127).
            // Si el byte es negativo (e.g., 192 que es -64 en byte), se le suma 256
            // para obtener su valor sin signo correcto (0 a 255).
            int uByte = bytes[cnt] < 0 ? bytes[cnt] + 256 : bytes[cnt];
            System.out.print(uByte + " ");
        }
        System.out.println(); // Salto de línea para finalizar la impresión de la IP.
        
    } catch (UnknownHostException e) {
        // Manejo de la excepción crítica que ocurre si el DNS falla o
        // si el nombre de host no puede ser resuelto (ni la URL ni el LocalHost).
        System.out.println(e);
        System.out.println("Debes estar conectado para que esto funcione bien.");
    }
}
}
