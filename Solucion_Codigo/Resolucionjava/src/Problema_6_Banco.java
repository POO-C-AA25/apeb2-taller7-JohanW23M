/**
 * Problema 6 - Sistema Un Banco
El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que 
* describen a cada una de las cuentas consisten en el número de cuenta, el 
* nombre del cliente y el balance actual. Escriba una clase para implementar
* dicha cuenta bancaria. El método constructor debe aceptar como parámetros el 
* número de cuenta y el nombre. Debe proporcionarse métodos para depositar o 
* retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de 
* AHORROS. Una cuenta de cheques puede sobregirarse 
* (el balance puede ser menor que cero), pero una cuenta de ahorros no.
* Al final de cada mes, se calcula el interés sobre la cantidad que tenga la 
* cuenta de ahorros. Este interés se suma al balance. Escriba clases para 
* describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la 
* herencia. La clase de la cuenta de ahorros debe proporcionar un método que 
* sea invocado para calcular el interés. Además, el banco está pensando en 
* implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos 
* anteriores de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni 
* castigos por sobregiro.

Note

Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual
* se pueda evidenciar el correcto funcionamiento de cada clase con n clientes, 
* y que además se pueda mostrar el balance (estado de cuenta) para cada cliente.
 * @author Johan Wang
 */
public class Problema_6_Banco {
    public static void main(String[] args) {
        Cheques cuenta1 = new Cheques("12345", "Juan Perez");
        Ahorros cuenta2 = new Ahorros("67890", "Maria Lopez", 0.05);
        Platino cuenta3 = new Platino("11223", "Carlos Gomez");

        cuenta1.depositar(1000);
        cuenta1.retirar(1200); 

        cuenta2.depositar(2000);
        cuenta2.retirar(500);
        cuenta2.calcularInteres();

        cuenta3.depositar(5000);
        cuenta3.retirar(3000);
        cuenta3.calcularInteres();

        System.out.println("Estados de cuenta ");
        System.out.println(cuenta1);
        System.out.println("Balance: $" + cuenta1.getBalance());

        System.out.println(cuenta2);
        System.out.println("Balance: $" + cuenta2.getBalance());

        System.out.println(cuenta3);
        System.out.println("Balance: $" + cuenta3.getBalance());
    }
}

class Cuenta {
    protected String numeroCuenta;
    protected String nombreCliente;
    protected double balance;

    public Cuenta(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            balance += cantidad;
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && balance >= cantidad) {
            balance -= cantidad;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                '}';
    }
}

class Cheques extends Cuenta {
    public Cheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;  
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cuenta de Cheques{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                '}';
    }
}

class Ahorros extends Cuenta {
    public double tasaInteres;

    public Ahorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && balance >= cantidad) {
            balance -= cantidad;
            return true;
        }
        return false;
    }

    public void calcularInteres() {
        if (balance > 0) {
            balance += balance * tasaInteres;
        }
    }

    @Override
    public String toString() {
        return "Cuenta de Ahorros{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", tasaInteres=" + (tasaInteres * 100) + "%" +
                '}';
    }
}

class Platino extends Cuenta {
    public double tasaInteres = 0.10;

    public Platino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad; 
            return true;
        }
        return false;
    }

    public void calcularInteres() {
        if (balance != 0) {  
            balance += balance * tasaInteres;
        }
    }

    @Override
    public String toString() {
        return "Cuenta Platino{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", tasaInteres=" + (tasaInteres * 100) + "%" +
                '}';
    }
}
/**
 * debug:
=== Estados de cuenta ===
Cuenta de Cheques{numeroCuenta='12345', nombreCliente='Juan Perez'}
Balance: $-200.0
Cuenta de Ahorros{numeroCuenta='67890', nombreCliente='Maria Lopez', tasaInteres=5.0%}
Balance: $1575.0
Cuenta Platino{numeroCuenta='11223', nombreCliente='Carlos Gomez', tasaInteres=10.0%}
Balance: $2200.0
BUILD SUCCESSFUL (total time: 1 second)

 */