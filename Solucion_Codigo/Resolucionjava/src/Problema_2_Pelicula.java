

/**
 * Problema 2 - Alquiler de peliculas
 *Un videoclub dispone de una serie de películas que pueden estar en DVD
 (con capacidad en Gb.) o en VHS (una sola cinta por película y con cierto 
 tipo de cinta magnetica). De las películas interesa guardar el título,
 el autor, el año de edición y el idioma (o los idiomas, en caso de DVD).
 El precio de alquiler de las películas varía en función del tipo de película.
 Las DVD siempre son 10% mas caras que las de VHS.

Note

*Analice los tipos de relación de las siguientes posibles clases: Pelicula,
Dvd, Vhs, Soporte, etc, y justifique su diseño.
Para probar el diseño jerarquico de clases, instancia en el clase de prueba 
Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
Los escenarios de prueba pueden darse para el alquiler de una o varias 
peliculas según la preferencia del usuario.
 * @author Johan Wang
 */
import java.util.ArrayList;

public class Problema_2_Pelicula {
    public static void main(String[] args) {
       
        Pelicula peli1 = new Pelicula("Matrix", "Wachowski", 1999);
        Pelicula peli2 = new Pelicula("John Wick", "Chad Stahelski", 2014);
        Pelicula peli3 = new Pelicula("Interstellar", "Christopher Nolan", 2014);
        Pelicula peli4 = new Pelicula("El laberinto del fauno", "Guillermo del Toro", 2006);

        VHS vhs1 = new VHS("ESP", peli1, 2.0);
        VHS vhs2 = new VHS("ENG", peli2, 2.5);
     
        ArrayList<Pelicula> peliculasDvd1 = new ArrayList<>();
        peliculasDvd1.add(peli3);
        peliculasDvd1.add(peli4);

        ArrayList<Pelicula> peliculasDvd2 = new ArrayList<>();
        peliculasDvd2.add(peli1); 

        DVD dvd1 = new DVD(new String[]{"ESP", "ENG", "FRA"}, peliculasDvd1, 3.0);
        DVD dvd2 = new DVD(new String[]{"ENG"}, peliculasDvd2, 2.5);

        System.out.println("Catalogo de Alquiler:");
        System.out.println(vhs1);
        System.out.println(vhs2);
        System.out.println(dvd1);
        System.out.println(dvd2);

        System.out.println("Precios de alquiler:");
        System.out.println("Precio VHS 1: $" + vhs1.precioAlq);
        System.out.println("Precio VHS 2: $" + vhs2.precioAlq);
        System.out.println("Precio DVD 1 (con 10% extra): $" + dvd1.precioAlq);
        System.out.println("Precio DVD 2 (con 10% extra): $" + dvd2.precioAlq);

    }
}

class SoportePelicula{
    public double precioAlq;

    public SoportePelicula(double precioAlq) {
        this.precioAlq = precioAlq;
    }
    
}
class DVD extends SoportePelicula{
    public String idioma[];
    public ArrayList<Pelicula> pelicula;

    public DVD(String[] idioma, ArrayList<Pelicula> pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
        calcularPrecioAlq();
    }

    
    
    public void calcularPrecioAlq(){
       this.precioAlq +=(this.precioAlq * 0.1);
    }
    @Override
    public String toString() {
        return "DVD{" +
                "idiomas=" + String.join(", ", idioma) +
                ", peliculas=" + pelicula +
                ", precio=" + precioAlq +
                '}';
    }

    
}
class VHS extends SoportePelicula{
    public String idioma;
    public Pelicula pelicula;

    public VHS(String idioma, Pelicula pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "VHS{" +
                "idioma='" + idioma + '\'' +
                ", pelicula=" + pelicula +
                ", precio=" + precioAlq +
                '}';
    }
}
class Pelicula{
    public String titulo;
    public String autor;
    public int anio;

    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }
    
    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anio=" + anio +
                '}';
    }


}
/**
 * Salida:
 * debug:
Catalogo de Alquiler:
VHS{idioma='ESP', pelicula=Pelicula{titulo='Matrix', autor='Wachowski', anio=1999}, precio=2.0}
VHS{idioma='ENG', pelicula=Pelicula{titulo='John Wick', autor='Chad Stahelski', anio=2014}, precio=2.5}
DVD{idiomas=ESP, ENG, FRA, peliculas=[Pelicula{titulo='Interstellar', autor='Christopher Nolan', anio=2014}, 
Pelicula{titulo='El laberinto del fauno', autor='Guillermo del Toro', anio=2006}], precio=3.3}
DVD{idiomas=ENG, peliculas=[Pelicula{titulo='Matrix', autor='Wachowski', anio=1999}], precio=2.75}
Precios de alquiler:
Precio VHS 1: $2.0
Precio VHS 2: $2.5
Precio DVD 1 (con 10% extra): $3.3
Precio DVD 2 (con 10% extra): $2.75
BUILD SUCCESSFUL (total time: 1 second)

 */