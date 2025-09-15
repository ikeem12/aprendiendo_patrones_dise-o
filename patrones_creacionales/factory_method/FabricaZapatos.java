package patrones_creacionales.factory_method;

public class FabricaZapatos extends Fabrica {
    private String tipo;

    public FabricaZapatos(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public CrearProducto crearProducto() {
        switch (tipo.toLowerCase()) {
            case "zapatillas":
                return new Zapatillas();
        
            case "zapatos":
                return new Zapatos();

            default:
                return null;
        }
    }
}