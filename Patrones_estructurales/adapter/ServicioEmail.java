package Patrones_estructurales.adapter;

// Librería externa (no modificable)
public class ServicioEmail {
    public void enviarEmail(String contenido) {
        System.out.println("Enviando email: " + contenido);
    }
}
