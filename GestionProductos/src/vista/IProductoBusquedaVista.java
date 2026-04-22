package vista;

import modelo.dto.Producto;
import java.awt.event.ActionListener;
import java.util.List;

public interface IProductoBusquedaVista {
    String getNombreBusqueda();
    String getTipoBusqueda();
    String getProveedorBusqueda();
    String getCriterioBusqueda();
    Producto getProductoSeleccionado();
    void mostrarResultados(List<Producto> resultados);
    void agregarListener(ActionListener listener);
}
