package patrones_creacionales.factory_method;

public class Zapatos implements CrearProducto {
    @Override
    public void crear() {
        System.out.println("Se ha creado un par de zapatos");
    }
}