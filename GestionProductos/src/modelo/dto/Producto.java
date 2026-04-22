package modelo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private TiposProducto tipo;
    private String nombre;
    private int cantidad;
    private double precio;
    private LocalDate fechaCaducidad;
    private String proveedor;

    public Producto() {}

    public Producto(int id, TiposProducto tipo, String nombre, int cantidad,
                    double precio, LocalDate fechaCaducidad, String proveedor) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
               "id=" + id +
               ", tipo=" + tipo +
               ", nombre='" + nombre + '\'' +
               ", cantidad=" + cantidad +
               ", precio=" + precio +
               ", fechaCaducidad=" + fechaCaducidad +
               ", proveedor='" + proveedor + '\'' +
               '}';
    }

    public int getId()                        { return id; }
    public void setId(int id)                 { this.id = id; }

    public TiposProducto getTipo()            { return tipo; }
    public void setTipo(TiposProducto tipo)   { this.tipo = tipo; }

    public String getNombre()                 { return nombre; }
    public void setNombre(String nombre)      { this.nombre = nombre; }

    public int getCantidad()                  { return cantidad; }
    public void setCantidad(int cantidad)     { this.cantidad = cantidad; }

    public double getPrecio()                 { return precio; }
    public void setPrecio(double precio)      { this.precio = precio; }

    public LocalDate getFechaCaducidad()                     { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad)  { this.fechaCaducidad = fechaCaducidad; }

    public String getProveedor()              { return proveedor; }
    public void setProveedor(String proveedor){ this.proveedor = proveedor; }
}
