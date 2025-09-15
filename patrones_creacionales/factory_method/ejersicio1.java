package patrones_creacionales.factory_method;

public class ejersicio1 {
    public static void main(String[] args) {
        Fabrica fabrica;

        String tipo = "zapatillas";

        switch (tipo) {
            case "zapatos":
                fabrica = new FabricaZapatos("zapatos");
                break;
            case "zapatillas":
                fabrica = new FabricaZapatos("zapatillas");
                break;
            default:
                fabrica = new FabricaCamisa();
                break;
        }

        fabrica.fabricar();
    }
}
