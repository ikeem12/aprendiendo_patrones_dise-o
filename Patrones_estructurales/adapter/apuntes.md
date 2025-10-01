# Adapter

El patrón Adapter es un patrón estructural del catálogo GoF que permite que dos clases con 
interfaces incompatibles trabajen juntas. Actúa como un **traductor** entre sistemas que no se 
entienden directamente.

    “El patrón Adapter traduce interfaces incompatibles para que puedan trabajar juntas, ideal 
    cuando se integra librerías externas o se refactoriza código sin romper dependencias.”

---

## Formas de implementación

### 1. Adapter por clase (herencia)

Usa herencia para adaptar una clase existente. Solo viable en lenguajes con herencia múltiple 
(como C++). En Java, es poco común.

````java
public class AdaptadorPorClase extends ServicioExistente implements ClienteEsperado {
    @Override
    public void metodoEsperado() {
        metodoExistente(); // Traducción
    }
}
````

---

### 2. Adapter por objeto (composición) 

La forma más común en Java. El Adapter contiene una instancia del objeto a adaptar y delegas las llamadas.

````java
public class AdaptadorPorObjeto implements ClienteEsperado {
    private ServicioExistente servicio;

    public AdaptadorPorObjeto(ServicioExistente servicio) {
        this.servicio = servicio;
    }

    @Override
    public void metodoEsperado() {
        servicio.metodoExistente(); // Traducción
    }
}
````

## Aplicaciones reales de adapter

- Integración con APIs o SDKs externas que no la interfaz del proyecto.

- Refactorización de código legado sin romper dependencias.

- Compatibilidad entre múltiples proveedores (ej. pagos, notificaciones).

- Separación de lógica de infraestructura en arquitectura limpia.