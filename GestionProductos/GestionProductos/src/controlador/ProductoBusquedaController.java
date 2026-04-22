package controlador;

import modelo.dao.ProductoDAO;
import modelo.dto.Producto;
import vista.IProductoBusquedaVista;
import vista.IProductoFormVista;
import vista.ProductoModificarDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoBusquedaController implements ActionListener {

    private ProductoDAO dao;
    private IProductoBusquedaVista vista;
    private IProductoFormVista vistaForm;

    public ProductoBusquedaController(IProductoBusquedaVista vista, IProductoFormVista vistaForm, ProductoDAO dao) {
        this.vista     = vista;
        this.vistaForm = vistaForm;
        this.dao       = dao;
        vista.agregarListener(this);
        vista.mostrarResultados(dao.LeerTodos());
    }

    public void refrescar() {
        vista.mostrarResultados(dao.LeerTodos());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Leemos el comando del botón que disparó el evento — sin variables de estado
        switch (e.getActionCommand()) {
            case "NOMBRE":
                vista.mostrarResultados(dao.buscarPorNombre(vista.getNombreBusqueda()));
                break;
            case "TIPO":
                vista.mostrarResultados(dao.buscarPorTipo(vista.getTipoBusqueda()));
                break;
            case "PROVEEDOR":
                vista.mostrarResultados(dao.buscarPorProveedor(vista.getProveedorBusqueda()));
                break;
            case "TODOS":
                vista.mostrarResultados(dao.LeerTodos());
                break;
            case "MODIFICAR":
                Producto aModificar = vista.getProductoSeleccionado();
                if (aModificar != null) {
                    Producto completo = (Producto) dao.Leer(aModificar);
                    if (completo == null) break;
                    ProductoModificarDialog dialog = new ProductoModificarDialog(null, completo);
                    dialog.setVisible(true);
                    if (dialog.isConfirmado()) {
                        int    nuevaCantidad = dialog.getNuevaCantidad();
                        double nuevoPrecio   = dialog.getNuevoPrecio();
                        if (nuevaCantidad < 0 || nuevoPrecio < 0) {
                            JOptionPane.showMessageDialog(null, "Valores inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        completo.setCantidad(nuevaCantidad);
                        completo.setPrecio(nuevoPrecio);
                        dao.Actualizar(dao.BuscarIndex(completo), completo);
                        vista.mostrarResultados(dao.LeerTodos());
                    }
                }
                break;
            case "ELIMINAR":
                Producto sel = vista.getProductoSeleccionado();
                if (sel != null) {
                    dao.Eliminar(sel);
                    vista.mostrarResultados(dao.LeerTodos());
                }
                break;
            case "CREAR":
                vistaForm.limpiarCampos();
                ((JFrame) vistaForm).setVisible(true);
                ((JFrame) vistaForm).toFront();
                break;
        }
    }

    public List<Producto> buscarPorNombre(String nombre)       { return dao.buscarPorNombre(nombre); }
    public List<Producto> buscarPorTipo(String tipo)           { return dao.buscarPorTipo(tipo); }
    public List<Producto> buscarPorProveedor(String proveedor) { return dao.buscarPorProveedor(proveedor); }
}
