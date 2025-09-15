package patrones_creacionales.factory_method;

public class FabricaCamisa extends Fabrica {
    @Override
    public CrearProducto crearProducto() {
        return new Camisa();
    }
}
