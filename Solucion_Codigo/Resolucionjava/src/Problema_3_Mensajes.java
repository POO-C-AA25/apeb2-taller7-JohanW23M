/**
 * Problema 3 - Sistema de envío de mensajes a móviles
Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de 
* mensajes que se pueden enviar entre móviles, mensajes de texto (SMS) y 
* mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto 
* contienen un mensaje en caracteres que se desea enviar de un móvil a otro.
* Por otro lado, los mensajes que contienen imágenes almacenan información 
* sobre la imagen a enviar, la cual se representará por el nombre del fichero 
* que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá
* asociado un remitente de dicho mensaje y un destinatario. Ambos estarán 
* definidos obligatoriamente por un número de móvil, y opcionalmente se podrá 
* guardar información sobre su nombre. Además, los métodos enviarMensaje y 
* visualizarMensaje deben estar definidos.

Note
Para probar el diseño jerarquico de clases, instancia en el clase de prueba 
* Ejecutor, con datos ficticios.
 * @author Johan Wang
 */

import java.util.ArrayList;

public class Problema_3_Mensajes {
    public static void main(String[] args) {
        Persona remitente = new Persona("0992823444" + "Alexander");
        Persona destinatario = new Persona("0923455443");
        SMS mensaje1 = new SMS(remitente,destinatario, "Buenos dias estellitas");
        MMS mensaje2 = new MMS(remitente,destinatario, "gatito.jpg");
        System.out.println(mensaje1);
        System.out.println(mensaje2);
        mensaje1.visualizarMensaje();
        mensaje2.visualizarMensaje();
    }
}
class Mensaje{
    public Persona remitente;
    public Persona destinatario;

  
    public Mensaje(Persona remitente, Persona destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
    
    @Override
    public String toString() {
        return "Mensaje{" + " remitente =" + remitente.numero + ", destinatario =" + destinatario.numero + '}';
    }
}
class SMS extends Mensaje{
    public String mensaje;
    public SMS(Persona remitente, Persona destinatario, String mensaje){
        super(remitente, destinatario);
        this.mensaje = mensaje;
    }
    public void enviarMensaje(){
        remitente.enviado.add(mensaje);
        destinatario.recibido.add(mensaje);
    }
    public void visualizarMensaje(){
        System.out.println("mensaje: ");
        System.out.println(mensaje);
    }

    @Override
    public String toString() {
        return  super.toString() + " SMS{" + " mensaje= " + mensaje + '}';
    }
    
}
class MMS extends Mensaje{
    public String fichero;

    public MMS(Persona remitente, Persona destinatario,String fichero) {
        super(remitente, destinatario);
        this.fichero = fichero;
    }
    
    public void enviarMensaje(){
        remitente.enviado.add(fichero);
        destinatario.recibido.add(fichero);
    }
    public void visualizarMensaje(){
        System.out.println("mensaje: ");
        System.out.println(fichero);
    }

    @Override
    public String toString() {
        return super.toString() + " MMS{" + " fichero= " + fichero + '}';
    }
    
}
class Persona{
    public String numero;
    public String nombre;
    public ArrayList<String> recibido;
    public ArrayList<String> enviado;

    public Persona(String numero) {
        this.numero = numero;
    }

    public Persona(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }
    
}
/**
 * debug:
Mensaje{ remitente =0992823444Alexander, destinatario =0923455443} SMS{ mensaje= Buenos dias estellitas}
Mensaje{ remitente =0992823444Alexander, destinatario =0923455443} MMS{ fichero= gatito.jpg}
mensaje: 
Buenos dias estellitas
mensaje: 
gatito.jpg
BUILD SUCCESSFUL (total time: 1 second)

 */