/**
 * Problema 5 - Venta de entradas al teatro
 * Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro.
* El patio de butacas del teatro se divide en varias zonas, cada una identificada
* por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	25$	17.5$
PalcoB	40	70$	40$
Central	400	20$	14$
Lateral	100	15.5$	10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que
* desea y presentar al vendedor el documento que justifique que tiene algún tipo 
* de descuento (estudiante, abonado o pensionista). El vendedor sacará la 
* entrada del tipo apropiado y de la zona indicada, en el momento de la compra 
* se asignará a la entrada un identificador (un número entero) que permitirá la 
* identificación de la entrada en todas las operaciones que posteriormente se 
* desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que
* pertenece y el nombre del comprador.

Los precios de las entradas dependen de la zona y del tipo de entrada según
* lo explicado a continuación:

Entradas normales: su precio es el precio normal de la zona elegida sin ningún
* tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una 
* rebaja del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los
* siguientes casos de usos.

Note

Caso de uso “Vende entrada”:

El vendedor elige la opción “vende entrada” e introduce la zona deseada, 
* el nombre del espectador y el tipo (normal, abonado o beneficiario de 
* entrada reducida).
Si la zona elegida no está completa, la aplicación genera una nueva entrada
* con los datos facilitados.
Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de
* uso sin generar la entrada.
Si la zona elegida está completa lo notifica y finaliza el caso de uno sin
* generar la entrada.
La aplicación muestra el identificador y el precio de la entrada.
Caso de uso “Consulta entrada”:

El vendedor elige la opción “consulta entrada” e introduce el identificador
* de la entrada.
La aplicación muestra los datos de la entrada: nombre del espectador, 
* precio y nombre de la zona. Si no existe ninguna entrada con ese 
* identificador, lo notifica y finaliza el caso de uso
 * @author Johan Wang
 */

import java.util.ArrayList;

public class Problema_5_Teatro {
    public static void main(String[] args) {
        Teatro teatro = new Teatro();

        System.out.println("=== VENTA DE ENTRADAS ===");

        teatro.venderEntrada("Principal", "Luis Perez", "normal");
        teatro.venderEntrada("PalcoB", "Ana Ruiz", "abonado");
        teatro.venderEntrada("Central", "Carlos Mora", "reducida");
        teatro.venderEntrada("Lateral", "Maria Vega", "abonado");
        teatro.venderEntrada("ZonaVip", "Pedro Error", "normal");
        teatro.venderEntrada("PalcoB", "Juan Perez", "reducida");

        System.out.println("\n=== CONSULTA DE ENTRADAS ===");

        teatro.consultarEntrada(1);
        teatro.consultarEntrada(3);
        teatro.consultarEntrada(99); 
    }
}

class Teatro {
    public ArrayList<Zona> zonas;
    public ArrayList<Entrada> entradas;
    public int contadorEntradas;

    public Teatro() {
        zonas = new ArrayList<>();
        entradas = new ArrayList<>();
        contadorEntradas = 1;

        zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        zonas.add(new Zona("Central", 400, 20.0, 14.0));
        zonas.add(new Zona("Lateral", 100, 15.5, 10.0));
    }

    public void venderEntrada(String nombreZona, String comprador, String tipo) {
        Zona zonaSeleccionada = null;

        for (Zona z : zonas) {
            if (z.nombre.equalsIgnoreCase(nombreZona)) {
                zonaSeleccionada = z;
                break;
            }
        }

        if (zonaSeleccionada == null) {
            System.out.println("Zona '" + nombreZona + "' no encontrada. No se puede vender la entrada.");
            return;
        }

        if (zonaSeleccionada.localidadesDisponibles <= 0) {
            System.out.println("La zona '" + nombreZona + "' está completa. No se puede vender la entrada.");
            return;
        }

        double precio = 0;
        if (tipo.equalsIgnoreCase("normal")) {
            precio = zonaSeleccionada.precioNormal;
        } else if (tipo.equalsIgnoreCase("abonado")) {
            precio = zonaSeleccionada.precioAbonado;
        } else if (tipo.equalsIgnoreCase("reducida")) {
            precio = zonaSeleccionada.precioNormal * 0.85;
        } else {
            System.out.println("Tipo de entrada no válido. No se puede vender la entrada.");
            return;
        }

        Entrada nuevaEntrada = new Entrada(contadorEntradas, zonaSeleccionada.nombre, comprador, precio);
        entradas.add(nuevaEntrada);
        zonaSeleccionada.localidadesDisponibles--;
        System.out.println("Entrada vendida - ID: " + contadorEntradas + ", Precio: $" + precio);
        contadorEntradas++;
    }

    public void consultarEntrada(int idEntrada) {
        for (Entrada e : entradas) {
            if (e.id == idEntrada) {
                System.out.println("Entrada encontrada:");
                System.out.println("ID: " + e.id);
                System.out.println("Comprador: " + e.comprador);
                System.out.println("Zona: " + e.zona);
                System.out.println("Precio: $" + e.precio);
                return;
            }
        }
        System.out.println("No existe ninguna entrada con ID: " + idEntrada);
    }
}

class Zona {
    public String nombre;
    public int localidadesDisponibles;
    public double precioNormal;
    public double precioAbonado;

    public Zona(String nombre, int localidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.localidadesDisponibles = localidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }
}

class Entrada {
    public int id;
    public String zona;
    public String comprador;
    public double precio;

    public Entrada(int id, String zona, String comprador, double precio) {
        this.id = id;
        this.zona = zona;
        this.comprador = comprador;
        this.precio = precio;
    }
}
/**
 * debug:
=== VENTA DE ENTRADAS ===
Entrada vendida - ID: 1, Precio: $25.0
Entrada vendida - ID: 2, Precio: $40.0
Entrada vendida - ID: 3, Precio: $17.0
Entrada vendida - ID: 4, Precio: $10.0
Zona 'ZonaVip' no encontrada. No se puede vender la entrada.
Entrada vendida - ID: 5, Precio: $59.5

=== CONSULTA DE ENTRADAS ===
Entrada encontrada:
ID: 1
Comprador: Luis Perez
Zona: Principal
Precio: $25.0
Entrada encontrada:
ID: 3
Comprador: Carlos Mora
Zona: Central
Precio: $17.0
No existe ninguna entrada con ID: 99
BUILD SUCCESSFUL (total time: 1 second)

 */