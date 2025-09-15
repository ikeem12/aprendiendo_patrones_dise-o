# Factory Method

Es un patrón creacional que propone que las subclases decidan qué objeto crear, en vez de que la clase principal lo haga 
directamente con **new**.

la idea es: 

Tener una **interfaz** o **clase abstracta** que declara un metodo de fabrica (CrearProducto())

Las subclases concretas implementan ese método y deciden qué objeto instanciar.

Esto:

- Evita meter lógica de creación en todas partes.
- Facilita agregar nuevos productos sin modificar el código existente (Principio Open/Closed).
- Desacopla el código cliente de las clases concretas.

---

## Forma clásica (GoF)

Cuando cada fábrica concreta devuelve un único tipo de producto.
Se usa cuando hay productos muy distintos (ej: distintos pools de conexión, distintas familias de UI).

````java
public interface CrearProducto {
    void crear();
}

public class Zapatos implements CrearProducto {
    @Override
    public void crear() {
        System.out.println("Creando zapatos");
    }
}

public class Camisa implements CrearProducto {
    @Override
    public void crear() {
        System.out.println("Creando camisa");
    }
}

// Fabrica base
public abstract class Fabrica {
    public abstract CrearProducto crearProducto();

    public void fabricar() {
        CrearProducto producto = crearProducto();
        producto.crear();
    }
}

// Fábricas concretas
public class FabricaZapatos extends Fabrica {
    @Override
    public CrearProducto crearProducto() {
        return new Zapatos();
    }
}

public class FabricaCamisas extends Fabrica {
    @Override
    public CrearProducto crearProducto() {
        return new Camisa();
    }
}
````

**Aquí cada fábrica concreta es responsable de un solo producto.**

---

## Forma simplificada (Factory Method parametrizado)

Cuando se quiere que una sola fábrica maneje varias variantes del mismo producto.
Se usa cuando los productos son de la misma categoría, pero con variaciones (ej: zapatos → deportivos, formales, botas).

````java
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
                throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
    }
}
````

**Aquí una sola fábrica concreta decide qué variante devolver.**
Esto evita crear demasiadas clases, pero mete la lógica de selección en un switch.

---

## Forma simplificada (sin clase abstracta)

Puede tener una sola clase Factory concreta con un método que decida qué objeto instanciar en base a un parámetro.

````java
public class ProductoFactory {
    public static CrearProducto crearProducto(String tipo) {
        switch (tipo) {
            case "zapato":
                return new Zapatos();
            case "camisa":
                return new Camisa();
            default:
                throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
    }
}
````
**Aquí no hay jerarquía de fábricas. Todo se resuelve en un único punto.**


---

### Conclusión

**Factory Method clásico**: más limpio, más extensible, pero crea muchas clases.

**Factory Method simplificado**: más práctico en proyectos pequeños o cuando hay muchas variantes de un mismo producto.

**Simplificado con switch**: cuando el caso es pequeño y concreto.

todos siguen la misma idea: delegar la creación del objeto a un método especializado en lugar de usar new en el código cliente.