package vista;

import modelo.dto.Producto;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Contrato que debe cumplir cualquier implementación de la vista de lista.
 * Para implementar con Swing: crear ProductoListaVista implements IProductoListaVista,
 * añadir JTable + JButton, y conectar el controlador como ActionListener.
 */
public interface IProductoListaVista {
    void mostrarProductos(List<Producto> productos);
    Producto getProductoSeleccionado();
    void agregarListener(ActionListener listener);
    String getAccionEjecutada();
}
