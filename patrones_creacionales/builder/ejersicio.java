package patrones_creacionales.builder;

import java.util.ArrayList;
import java.util.List;

public class ejersicio {
    public static void main(String[] args) {

        List<String> ingredientes = new ArrayList<>();

        ingredientes.add("pepperoni");

        Pizza pizza = new Pizza.Builder()
                .setTamano("grande")
                .setMasa("fina")
                .setQuesoExtra(true)
                .setIngredientes(ingredientes)
                .build();

        System.out.println(pizza.toString());
    }
}
