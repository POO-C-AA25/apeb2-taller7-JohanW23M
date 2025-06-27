/**
 * Problema 1 - Jerarquía de clases para el capítulo de libro
Dibujad un diagrama de clases que muestre la estructura de un capítulo de
* libro; un capítulo está compuesto por varias secciones, cada una de las 
* cuales comprende varios párrafos y figuras. Un párrafo incluye varias
* sentencias, cada una de las cuales contiene varias palabras.

Note

Suponga que en un futuro se prevé que el sistema gestione además de párrafos 
* y figuras otros componentes, como tablas, listas, viñetas, etc.
Suponga además que una palabra puede aparecer en varias sentencias.
 */
import java.util.ArrayList;



public class Problema_1_Libro {
    public static void main(String[] args) {
        Capitulo capitulo1 = new Capitulo("Capitulo 1 - Introduccion");

        Seccion seccion1 = new Seccion();

        Palabra p1 = new Palabra("hola");
        Palabra p2 = new Palabra("amigos");
        Palabra p3 = new Palabra("mundo");

        Sentencia s1 = new Sentencia();
        s1.palabras.add(p1);
        s1.palabras.add(p2);

        Sentencia s2 = new Sentencia();
        s2.palabras.add(p1);
        s2.palabras.add(p3);

        Parrafo parrafo1 = new Parrafo("Parrafo");
        parrafo1.agregarSentencia(s1);
        parrafo1.agregarSentencia(s2);

        Figura figura1 = new Figura("Figura", "Diagrama de flujo", "https://fuente.com/figura1.png");

        seccion1.componentes.add(parrafo1);
        seccion1.componentes.add(figura1);

        capitulo1.secciones.add(seccion1);

        capitulo1.mostrarContenido();
    }
}

class Capitulo {
    public String titulo;
    public ArrayList<Seccion> secciones;

    public Capitulo(String titulo) {
        this.titulo = titulo;
        this.secciones = new ArrayList<>();
    }

    public void mostrarContenido() {
        System.out.println("Capitulo: " + titulo);
        for (Seccion s : secciones) {
            s.mostrarComponentes();
        }
    }
}

class Seccion {
    public ArrayList<Componente> componentes;

    public Seccion() {
        this.componentes = new ArrayList<>();
    }

    public void mostrarComponentes() {
        System.out.println(" Seccion:");
        for (Componente c : componentes) {
            System.out.println("  " + c.toString());
        }
    }
}

class Componente {
    public String tipo;

    public Componente(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Componente de tipo: " + tipo;
    }
}

class Parrafo extends Componente {
    public ArrayList<Sentencia> sentencias;

    public Parrafo(String tipo) {
        super(tipo);
        this.sentencias = new ArrayList<>();
    }

    public void agregarSentencia(Sentencia s) {
        sentencias.add(s);
    }

    @Override
    public String toString() {
        String texto = "Parrafo:";
        for (Sentencia s : sentencias) {
            texto += "\n   - " + s;
        }
        return texto;
    }
}

class Figura extends Componente {
    public String descripcion;
    public String fuente;

    public Figura(String tipo, String descripcion, String fuente) {
        super(tipo);
        this.descripcion = descripcion;
        this.fuente = fuente;
    }

    @Override
    public String toString() {
        return "Figura: " + descripcion + " (Fuente: " + fuente + ")";
    }
}

class Sentencia {
    public ArrayList<Palabra> palabras;

    public Sentencia() {
        this.palabras = new ArrayList<>();
    }

    @Override
    public String toString() {
        String linea = "";
        for (Palabra p : palabras) {
            linea += p.texto + " ";
        }
        return linea.trim();
    }
}

class Palabra {
    public String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }
}
