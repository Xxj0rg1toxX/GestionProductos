package vista;

import modelo.dto.TiposProducto;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Contrato que debe cumplir cualquier implementación del formulario.
 * Para implementar con Swing: crear ProductoFormVista implements IProductoFormVista,
 * añadir los campos (JTextField, JComboBox, etc.) y conectar el controlador.
 */
public interface IProductoFormVista {
    int getId();
    String getNombre();
    TiposProducto getTipo();
    int getCantidad();
    double getPrecio();
    LocalDate getFechaCaducidad();
    String getProveedor();
    void limpiarCampos();
    void agregarListener(ActionListener listener);
    String getAccionEjecutada();
}
