import controlador.ProductoBusquedaController;
import controlador.ProductoFormController;
import modelo.dao.ProductoDAO;
import modelo.dto.Producto;
import modelo.dto.TiposProducto;
import vista.ProductoBusquedaVista;
import vista.ProductoFormVista;

import javax.swing.*;
import java.awt.Color;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


        ProductoDAO dao = new ProductoDAO();
        cargarDatosEjemplo(dao);

        SwingUtilities.invokeLater(() -> {
            ProductoFormVista     vistaForm     = new ProductoFormVista();
            ProductoBusquedaVista vistaBusqueda = new ProductoBusquedaVista();

            // El controlador de búsqueda se crea primero para tener la referencia de refrescar
            ProductoBusquedaController ctrlBusqueda = new ProductoBusquedaController(vistaBusqueda, vistaForm, dao);

            // El formulario notifica al de búsqueda cuando hay cambios
            new ProductoFormController(vistaForm, dao, ctrlBusqueda::refrescar);

            vistaBusqueda.setVisible(true);
        });
    }

    private static void cargarDatosEjemplo(ProductoDAO dao) {
        if (!dao.LeerTodos().isEmpty()) return;

        dao.Insertar(new Producto(1,  TiposProducto.REFRIGERADO, "Leche entera",     50,  2800.0, LocalDate.of(2025, 6, 20), "LacteosS.A"));
        dao.Insertar(new Producto(2,  TiposProducto.REFRIGERADO, "Yogurt fresa",     30,  3500.0, LocalDate.of(2025, 5, 15), "LacteosS.A"));
        dao.Insertar(new Producto(3,  TiposProducto.REFRIGERADO, "Queso campesino",  20,  7200.0, LocalDate.of(2025, 7, 10), "LacteosS.A"));
        dao.Insertar(new Producto(4,  TiposProducto.CONGELADO,   "Pechuga de pollo", 80, 14000.0, LocalDate.of(2025, 9,  1), "CarnesDelValle"));
        dao.Insertar(new Producto(5,  TiposProducto.CONGELADO,   "Carne molida",     60, 18000.0, LocalDate.of(2025, 8, 25), "CarnesDelValle"));
        dao.Insertar(new Producto(6,  TiposProducto.CONGELADO,   "Camarones",        25, 32000.0, LocalDate.of(2025,10,  5), "MariscosFresh"));
        dao.Insertar(new Producto(7,  TiposProducto.SECO,        "Arroz Diana 500g",200,  3200.0, LocalDate.of(2026,12,  1), "GranosCol"));
        dao.Insertar(new Producto(8,  TiposProducto.SECO,        "Frijol rojo 500g",150,  4100.0, LocalDate.of(2026,11, 20), "GranosCol"));
        dao.Insertar(new Producto(9,  TiposProducto.SECO,        "Pasta espagueti", 180,  2600.0, LocalDate.of(2027, 3, 15), "AlimentosSA"));
        dao.Insertar(new Producto(10, TiposProducto.SECO,        "Aceite de cocina", 90,  8900.0, LocalDate.of(2026, 6, 30), "AlimentosSA"));
    }
}
