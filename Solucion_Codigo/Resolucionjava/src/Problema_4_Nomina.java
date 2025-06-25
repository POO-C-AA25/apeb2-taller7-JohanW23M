/**
 * Problema 4 - Sistema de nómina para trabajadores
Se desea desarrollar un sistema de nómina para los trabajadores de una empresa.
* Los datos personales de los trabajadores son nombre y apellidos, dirección y 
* DNI. Además, existen diferentes tipos de trabajadores:

Fijos Mensuales: que cobran una cantidad fija al mes.
Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
Por Horas: cobran un precio por cada una de las horas que han realizado durante
* el mes. El precio es fijo para las primeras 40 horas y es otro para las horas
* realizadas a partir de la 40 hora mensual.
Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene 
* obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno). 
* El programa debe permitir dar de alta a trabajadores, así como fijar horas
* o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 * @author Johan Wang
Note

Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor 
* forma el escenario planteado.
Para probar el diseño de clases, instancia en el clase de prueba Ejecutor 
* (la-s clase-s respectiva-s), con datos aleatorios.
En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores.
 */
import java.util.ArrayList;

public class Problema_4_Nomina {
    public static void main(String[] args) {
        Jefe jefe1 = new Jefe("Ana", "Perez", "Calle Sol 123", 12345678, 3000);
        Jefe jefe2 = new Jefe("Carlos", "Gomez", "Av. Luna 456", 23456789, 3200);

        FijoMensual emp1 = new FijoMensual("Luis", "Martinez", "Calle Norte 5", 34567890, 1200, jefe1);
        Comisionista emp2 = new Comisionista("Maria", "Ruiz", "Calle Este 7", 45678901, 0.10, jefe2);
        PorHoras emp3 = new PorHoras("Pedro", "Sanchez", "Calle Sur 8", 56789012, 15, 20, jefe1);

        emp2.ventas = 8000;
        emp3.horasTrabajadas = 45;

        System.out.println(" NOMINAS ");
        System.out.println(emp1);
        System.out.println("Sueldo: $" + emp1.calcularSueldo());

        System.out.println(emp2);
        System.out.println("Sueldo: $" + emp2.calcularSueldo());

        System.out.println(emp3);
        System.out.println("Sueldo: $" + emp3.calcularSueldo());

        System.out.println(jefe1);
        System.out.println("Sueldo: $" + jefe1.sueldoFijo);

        System.out.println(jefe2);
        System.out.println("Sueldo: $" + jefe2.sueldoFijo);
    }
}

class Trabajador {
    public String nombre;
    public String apellidos;
    public String direccion;
    public int dni;
    public Jefe jefe;

    public Trabajador(String nombre, String apellidos, String direccion, int dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni=" + dni +
                ", jefe=" + jefe.nombre + " " + jefe.apellidos +
                '}';
    }
}

class FijoMensual extends Trabajador {
    public double sueldoMensual;

    public FijoMensual(String nombre, String apellidos, String direccion, int dni, double sueldoMensual, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.sueldoMensual = sueldoMensual;
    }

    public double calcularSueldo() {
        return sueldoMensual;
    }
}

class Comisionista extends Trabajador {
    public double porcentajeComision;
    public double ventas;

    public Comisionista(String nombre, String apellidos, String direccion, int dni, double porcentajeComision, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
        this.ventas = 0;
    }

    public double calcularSueldo() {
        return ventas * porcentajeComision;
    }
}

class PorHoras extends Trabajador {
    public double pagoHoraBase;
    public double pagoHoraExtra;
    public int horasTrabajadas;

    public PorHoras(String nombre, String apellidos, String direccion, int dni,
                    double pagoHoraBase, double pagoHoraExtra, Jefe jefe) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.pagoHoraBase = pagoHoraBase;
        this.pagoHoraExtra = pagoHoraExtra;
        this.horasTrabajadas = 0;
    }

    public double calcularSueldo() {
        int horasBase = 0;
        int horasExtra = 0;

        if (horasTrabajadas <= 40) {
            horasBase = horasTrabajadas;
        } else {
            horasBase = 40;
            horasExtra = horasTrabajadas - 40;
        }

        return (horasBase * pagoHoraBase) + (horasExtra * pagoHoraExtra);
    }
}

class Jefe extends Trabajador {
    public double sueldoFijo;

    public Jefe(String nombre, String apellidos, String direccion, int dni, double sueldoFijo) {
        super(nombre, apellidos, direccion, dni, null);
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public String toString() {
        return "Jefe{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni=" + dni +
                ", sueldoFijo=" + sueldoFijo +
                '}';
    }
}
/**
 * debug:
 NOMINAS 
Trabajador{nombre='Luis', apellidos='Martinez', direccion='Calle Norte 5', dni=34567890, jefe=Ana Perez}
Sueldo: $1200.0
Trabajador{nombre='Maria', apellidos='Ruiz', direccion='Calle Este 7', dni=45678901, jefe=Carlos Gomez}
Sueldo: $800.0
Trabajador{nombre='Pedro', apellidos='Sanchez', direccion='Calle Sur 8', dni=56789012, jefe=Ana Perez}
Sueldo: $700.0
Jefe{nombre='Ana', apellidos='Perez', direccion='Calle Sol 123', dni=12345678, sueldoFijo=3000.0}
Sueldo: $3000.0
Jefe{nombre='Carlos', apellidos='Gomez', direccion='Av. Luna 456', dni=23456789, sueldoFijo=3200.0}
Sueldo: $3200.0
BUILD SUCCESSFUL (total time: 1 second)

 */