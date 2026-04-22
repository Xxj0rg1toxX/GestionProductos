package vista;

import modelo.dto.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoListaVista extends JFrame implements IProductoListaVista {

    private JTable tablaProductos;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private DefaultTableModel modeloTabla;
    private String accionEjecutada = "";

    public ProductoListaVista() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Productos — Lista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        // ── Tabla ──────────────────────────────────────────────
        String[] columnas = {"ID", "Nombre", "Tipo", "Cantidad", "Precio", "Caducidad", "Proveedor"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tablaProductos);
        add(scroll, BorderLayout.CENTER);

        // ── Panel de botones ───────────────────────────────────
        btnEliminar  = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar lista");

        btnEliminar.setBackground(new Color(200, 60, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        pnlBotones.add(btnActualizar);
        pnlBotones.add(btnEliminar);
        add(pnlBotones, BorderLayout.SOUTH);

        // ── Acciones internas ──────────────────────────────────
        btnEliminar.addActionListener(e -> accionEjecutada = "ELIMINAR");
        btnActualizar.addActionListener(e -> accionEjecutada = "ACTUALIZAR_LISTA");
    }

    // ── IProductoListaVista ────────────────────────────────────

    @Override
    public void mostrarProductos(List<Producto> productos) {
        modeloTabla.setRowCount(0);
        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getTipo(),
                p.getCantidad(),
                p.getPrecio(),
                p.getFechaCaducidad(),
                p.getProveedor()
            });
        }
    }

    @Override
    public Producto getProductoSeleccionado() {
        int fila = tablaProductos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Producto p = new Producto();
        p.setId((int) modeloTabla.getValueAt(fila, 0));
        p.setNombre((String) modeloTabla.getValueAt(fila, 1));
        return p;
    }

    @Override
    public void agregarListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
        btnActualizar.addActionListener(listener);
    }

    @Override
    public String getAccionEjecutada() { return accionEjecutada; }
}
