package patrones_creacionales.factory_method;

public class Zapatillas implements CrearProducto {
    @Override
    public void crear() {
        System.out.println("Se ha creado un par de zapatillas");
    }
}