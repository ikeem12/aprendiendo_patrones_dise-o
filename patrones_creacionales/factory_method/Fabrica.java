package patrones_creacionales.factory_method;

public abstract class Fabrica {
    public abstract CrearProducto crearProducto();

    public void fabricar(){
        CrearProducto producto = crearProducto();
        producto.crear();
    }
}
