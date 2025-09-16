package patrones_creacionales.builder;

import java.util.List;

public class Pizza {
    private String tamano;
    private String masa;
    private Boolean quesoExtra;
    private List<String> ingredientes;

    private Pizza(Builder builder){
        this.tamano = builder.tamano;
        this.masa = builder.masa;
        this.quesoExtra = builder.quesoExtra;
        this.ingredientes = builder.ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza [tamaño=" + tamano + ", masa=" + masa + ", quesoExtra=" + quesoExtra + ", ingredientes=" + ingredientes + "]";
    }

    public static class Builder{
        private String tamano;
        private String masa;
        private Boolean quesoExtra;
        private List<String> ingredientes;

        public Builder setTamano(String tamano){
            this.tamano = tamano;
            return this;
        }

        public Builder setMasa(String masa){
            this.masa = masa;
            return this;
        }

        public Builder setQuesoExtra(Boolean quesoExtra){
            this.quesoExtra = quesoExtra;
            return this;
        }

        public Builder setIngredientes(List<String> ingredientes){
            this.ingredientes = ingredientes;
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }
    }
}