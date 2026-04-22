package controlador;

import modelo.dao.ProductoDAO;
import modelo.dto.Producto;
import vista.IProductoFormVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoFormController implements ActionListener {

    private ProductoDAO dao;
    private IProductoFormVista vista;
    private Runnable onCambio; // callback para notificar a la vista de búsqueda

    public ProductoFormController(IProductoFormVista vista, ProductoDAO dao, Runnable onCambio) {
        this.vista    = vista;
        this.dao      = dao;
        this.onCambio = onCambio;
        vista.agregarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = vista.getAccionEjecutada();
        Producto p = construirProductoDesdeVista();
        if (p == null || p.getFechaCaducidad() == null) return;

        switch (accion) {
            case "CREAR":
                crear(p);
                vista.limpiarCampos();
                if (onCambio != null) onCambio.run();
                break;
            case "ACTUALIZAR":
                int index = dao.BuscarIndex(p);
                if (index >= 0) {
                    actualizar(index, p);
                    vista.limpiarCampos();
                    if (onCambio != null) onCambio.run();
                }
                break;
        }
    }

    private Producto construirProductoDesdeVista() {
        return new Producto(
            vista.getId(), vista.getTipo(), vista.getNombre(),
            vista.getCantidad(), vista.getPrecio(),
            vista.getFechaCaducidad(), vista.getProveedor()
        );
    }

    public void crear(Producto p)              { dao.Insertar(p); }
    public Producto leer(Producto p)           { return (Producto) dao.Leer(p); }
    public void actualizar(int index, Producto p) { dao.Actualizar(index, p); }
}
