package patrones_creacionales.factory_method;

public class Camisa implements CrearProducto {
    @Override
    public void crear() {
        System.out.println("Se ha creado una camisa");
    }
}