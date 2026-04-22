package controlador;

import modelo.dao.ProductoDAO;
import modelo.dto.Producto;
import vista.IProductoListaVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoListaController implements ActionListener {

    private ProductoDAO dao;
    private IProductoListaVista vista;

    public ProductoListaController(IProductoListaVista vista) {
        this.vista = vista;
        this.dao = new ProductoDAO();
        vista.agregarListener(this);
        vista.mostrarProductos(listarTodos());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = vista.getAccionEjecutada();
        switch (accion) {
            case "ELIMINAR":
                Producto seleccionado = vista.getProductoSeleccionado();
                if (seleccionado != null) {
                    eliminar(seleccionado);
                    vista.mostrarProductos(listarTodos());
                }
                break;
            case "ACTUALIZAR_LISTA":
                vista.mostrarProductos(listarTodos());
                break;
        }
    }

    public void eliminar(Producto p) {
        dao.Eliminar(p);
    }

    public List<Producto> listarTodos() {
        return dao.LeerTodos();
    }
}
