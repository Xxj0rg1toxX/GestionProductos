package modelo.dao;

import accesoDatos.ICrud;
import accesoDatos.Persistencia;
import modelo.dto.Producto;
import modelo.dto.TiposProducto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoDAO implements ICrud {

    private List<Producto> listaProductos;
    private Persistencia<Producto> persistencia;

    public ProductoDAO() {
        persistencia = new Persistencia<>("productos.dat");
        listaProductos = persistencia.getLista();
    }

    @Override
    public boolean Insertar(Object object) {
        Producto p = (Producto) object;
        listaProductos.add(p);
        persistencia.guardar();
        return true;
    }

    @Override
    public Object Leer(Object object) {
        Producto p = (Producto) object;
        for (Producto prod : listaProductos) {
            if (prod.getId() == p.getId()) return prod;
        }
        return null;
    }

    @Override
    public boolean Actualizar(int index, Object object) {
        if (index < 0 || index >= listaProductos.size()) return false;
        listaProductos.set(index, (Producto) object);
        persistencia.guardar();
        return true;
    }

    @Override
    public boolean Eliminar(Object object) {
        Producto p = (Producto) object;
        boolean removed = listaProductos.removeIf(prod -> prod.getId() == p.getId());
        if (removed) persistencia.guardar();
        return removed;
    }

    @Override
    public int BuscarIndex(Object object) {
        Producto p = (Producto) object;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == p.getId()) return i;
        }
        return -1;
    }

    @Override
    public List<Producto> LeerTodos() {
        return new ArrayList<>(listaProductos);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return listaProductos.stream()
            .filter(p -> p.getNombre() != null &&
                         p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Producto> buscarPorTipo(String tipo) {
        try {
            TiposProducto tipoEnum = TiposProducto.valueOf(tipo.toUpperCase());
            return listaProductos.stream()
                .filter(p -> p.getTipo() == tipoEnum)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

    public List<Producto> buscarPorPrecio(double precio) {
        return listaProductos.stream()
            .filter(p -> Double.compare(p.getPrecio(), precio) == 0)
            .collect(Collectors.toList());
    }

    public List<Producto> buscarPorProveedor(String proveedor) {
        return listaProductos.stream()
            .filter(p -> p.getProveedor() != null &&
                         p.getProveedor().equalsIgnoreCase(proveedor))
            .collect(Collectors.toList());
    }

    public List<Producto> buscarPorFecha(LocalDate fecha) {
        return listaProductos.stream()
            .filter(p -> p.getFechaCaducidad() != null &&
                         p.getFechaCaducidad().equals(fecha))
            .collect(Collectors.toList());
    }
}
