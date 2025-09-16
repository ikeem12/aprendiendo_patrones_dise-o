# builder

El patron builder es un patron creacional que se usa cuando se quiere construir objetos complejos paso a paso, sin necesidad de tener un constructor gigante con mil parametros

La idea es separar la construcción de un objeto de su representación, para que el mismo proceso pueda crear diferentes representaciones.


## Problema que resuelve

pude haber un clase usuario con muchos atributos

````java
public class Usuario {
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String telefono;
    private boolean activo;

    public Usuario(String nombre, String apellido, int edad, String direccion, String telefono, boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
    }
}
````

El constructor con tantos parámetros es difícil de leer y propenso a errores:

````java
Usuario u = new Usuario("Johan", "García", 25, "Calle 123", "123456789", true);
````

## Solución con builder

se crea un constructor paso a paso

````java
public class Usuario {
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String telefono;
    private boolean activo;

    // Constructor privado, solo se usa desde el Builder
    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.edad = builder.edad;
        this.direccion = builder.direccion;
        this.telefono = builder.telefono;
        this.activo = builder.activo;
    }

    // Clase estática interna Builder
    public static class Builder {
        private String nombre;
        private String apellido;
        private int edad;
        private String direccion;
        private String telefono;
        private boolean activo;

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder edad(int edad) {
            this.edad = edad;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }
}
````

**Uso**:

````java
Usuario usuario = new Usuario.Builder()
    .nombre("Johan")
    .apellido("García")
    .edad(25)
    .direccion("Calle 123")
    .telefono("123456789")
    .activo(true)
    .build();
````

## ¿Por qué los métodos devuelven this?

Se devuelve this para implementar **fluent interface** (interfaz fluida).
Esto permite **encadenar métodos** (method chaining) de forma clara:

````java
new Usuario.Builder()
    .nombre("Johan")
    .apellido("García")
    .build();

````

sin **fluent interface**:

````java
Usuario.Builder builder = new Usuario.Builder();

builder.nombre("Johan");
builder.apellido("García");

Usuario usuario = builder.build();
````

## Formas comunes de implementar un builder

1. **Builder Interno** (inner static class): El builder vive dentro de la misma clase.
El constructor de la clase es private y solo se accede a través del builder.

2. **Builder externo**: El builder es una clase independiente que crea otro objeto.
Muy útil cuando el objeto a construir viene de una librería o framework que no se puede 
modificar.

3. **Builder con Director** (GoF clásico): Es la forma más “académica” del patrón (la del libro GoF).

Tiene tres actores:

- Product → el objeto que se construye.

- Builder → interfaz que define los pasos de construcción.

- ConcreteBuilder → implementación específica de los pasos.

- Director → orquesta el proceso de construcción paso a paso.

````java
// Product
class Casa {
    String paredes;
    String techo;
}

// Builder
interface CasaBuilder {
    void construirParedes();
    void construirTecho();
    Casa getResultado();
}

// ConcreteBuilder
class CasaDeMaderaBuilder implements CasaBuilder {
    private Casa casa = new Casa();
 
    public void construirParedes() { 
        casa.paredes = "Paredes de madera"; 
    }

    public void construirTecho() { 
        casa.techo = "Techo de madera"; 
    }

    public Casa getResultado() { return casa; }
}

// Director
class Director {
    private CasaBuilder builder;

    public Director(CasaBuilder builder) { 
        this.builder = builder; 
    }

    public void construir() {
        builder.construirParedes();
        builder.construirTecho();
    }
}
````

**Uso**:

````java
CasaBuilder builder = new CasaDeMaderaBuilder();
Director director = new Director(builder);
director.construir();
Casa casa = builder.getResultado();
````

---

**En conclusión**:

- Lo más usado hoy en día en Java empresarial → builder interno + fluent API (como Lombok @Builder).

- Lo que se usa en frameworks o objetos de terceros → builder externo como ServerBuilder.

- Lo más didáctico/GoF → builder con Director, pero casi no se ve en código real.