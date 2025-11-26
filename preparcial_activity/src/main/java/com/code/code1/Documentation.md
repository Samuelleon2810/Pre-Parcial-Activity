# 🌐 PruebaSockets: Exploración de Direcciones IP

Este programa Java utiliza la clase `java.net.InetAddress` para obtener y mostrar información básica de la red, incluyendo la dirección IP de una URL remota (`www.udistrital.edu.co`) y los detalles de la máquina local (LocalHost).

---

## 🎯 ¿Qué Hace?

El objetivo principal es demostrar la funcionalidad de las utilidades de red de Java para:

* **Resolver el nombre de host** de una URL específica a su dirección IP.
* **Obtener la identidad de la máquina local** (su nombre y dirección IP).
* **Mostrar la dirección IP en diferentes formatos** (como cadena y como arreglo de bytes).

---

## 💻 Proceso Interno (Mecanismos de Red)

El código interactúa con los servicios de resolución de nombres de red de bajo nivel del sistema operativo.

1.  ### 1. Resolución de DNS Remoto (`getByName`)
    * La llamada a `InetAddress.getByName("www.udistrital.edu.co")` delega la tarea al **Servicio de Nombres de Dominio (DNS)**.
    * El sistema envía una consulta a un servidor DNS para que traduzca el nombre legible por humanos a la **dirección IP numérica**. 
    * Si la resolución es exitosa, se devuelve un objeto **`InetAddress`** que encapsula ambos datos.

2.  ### 2. Identificación del LocalHost (`getLocalHost`)
    * La llamada a `InetAddress.getLocalHost()` consulta la configuración de red de la propia máquina.
    * Devuelve un objeto `InetAddress` con el **nombre de la máquina** y su **dirección IP local**.

3.  ### 3. Representación de la IP (`getAddress`)
    * El método `getAddress()` obtiene la dirección IP como un **arreglo de 4 bytes** (para IPv4). El programa itera para mostrar estos bytes en el formato decimal con puntos.

---

## 🚀 Uso y Ejecución

1.  Compila la clase `PruebaSockets.java`.
2.  Ejecuta la clase:
    ```bash
    java com.code.code1.PruebaSockets
    ```
    **Nota:** Es necesario estar conectado a Internet para la resolución de la URL remota.

---

## 🖼️ Muestra de Ejecución
```java
run:
URL & Direccioon IP:
www.udistrital.edu.co/200.69.103.83
Direccion IP: 
/200.69.103.83
Nombre & Direccion IP actual de LocalHost
nikkaoyy/192.168.1.8
Direccion IP del LocalHost: 
/192.168.1.8
Nombre actual de LocalHost
nikkaoyy
Direccion IP actual de LocalHost
192 168 1 8 
BUILD SUCCESSFUL (total time: 1 second)
```
