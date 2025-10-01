package Patrones_estructurales.adapter;

public class EmailAdapter implements Notificador {
    private ServicioEmail servicioEmail;

    public EmailAdapter(ServicioEmail servicioEmail) {
        this.servicioEmail = servicioEmail;
    }

    @Override
    public void enviar(String mensaje) {
        servicioEmail.enviarEmail(mensaje); // Traducción de interfaz
    }
}
