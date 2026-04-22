package vista;

import modelo.dto.Producto;
import modelo.dto.TiposProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoBusquedaVista extends JFrame implements IProductoBusquedaVista {

    private JPanel pnlFiltros;
    private JTable tblResultados;
    private DefaultTableModel modeloTabla;

    private JTextField txtNombre;
    private JComboBox<TiposProducto> cmbTipo;
    private JTextField txtProveedor;

    private JButton btnBuscarNombre;
    private JButton btnBuscarTipo;
    private JButton btnBuscarProveedor;
    private JButton btnVerTodos;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCrear;

    public ProductoBusquedaVista() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Productos — Búsqueda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 540);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        // Panel de filtros
        pnlFiltros = new JPanel(new GridBagLayout());
        pnlFiltros.setBorder(BorderFactory.createTitledBorder("Filtros"));
        pnlFiltros.setPreferredSize(new Dimension(230, 0));

        GridBagConstraints g = new GridBagConstraints();
        g.insets  = new Insets(5, 8, 5, 8);
        g.fill    = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        g.gridx   = 0;

        txtNombre       = new JTextField();
        btnBuscarNombre = boton("Buscar por nombre",    new Color(60, 130, 200), "NOMBRE");
        g.gridy = 0; pnlFiltros.add(new JLabel("Nombre:"), g);
        g.gridy = 1; pnlFiltros.add(txtNombre, g);
        g.gridy = 2; pnlFiltros.add(btnBuscarNombre, g);
        g.gridy = 3; pnlFiltros.add(new JSeparator(), g);

        cmbTipo       = new JComboBox<>(TiposProducto.values());
        btnBuscarTipo = boton("Buscar por tipo",        new Color(60, 130, 200), "TIPO");
        g.gridy = 4; pnlFiltros.add(new JLabel("Tipo:"), g);
        g.gridy = 5; pnlFiltros.add(cmbTipo, g);
        g.gridy = 6; pnlFiltros.add(btnBuscarTipo, g);
        g.gridy = 7; pnlFiltros.add(new JSeparator(), g);

        txtProveedor       = new JTextField();
        btnBuscarProveedor = boton("Buscar por proveedor", new Color(60, 130, 200), "PROVEEDOR");
        g.gridy = 8;  pnlFiltros.add(new JLabel("Proveedor:"), g);
        g.gridy = 9;  pnlFiltros.add(txtProveedor, g);
        g.gridy = 10; pnlFiltros.add(btnBuscarProveedor, g);

        g.gridy = 11; g.weighty = 1.0;
        pnlFiltros.add(new JLabel(), g);
        add(pnlFiltros, BorderLayout.WEST);

        // Tabla
        String[] cols = {"ID", "Nombre", "Tipo", "Cantidad", "Precio", "Caducidad", "Proveedor"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblResultados = new JTable(modeloTabla);
        tblResultados.setRowHeight(24);
        tblResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblResultados.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tblResultados);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));
        add(scroll, BorderLayout.CENTER);

        // Barra de acciones — cada botón lleva su ActionCommand
        btnVerTodos  = boton("Ver todos",      new Color(100, 100, 100), "TODOS");
        btnModificar = boton("Modificar",      new Color(200, 140,   0), "MODIFICAR");
        btnEliminar  = boton("Eliminar",       new Color(200,  60,  60), "ELIMINAR");
        btnCrear     = boton("Nuevo producto", new Color( 60, 160,  80), "CREAR");

        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        pnlAcciones.add(btnVerTodos);
        pnlAcciones.add(btnCrear);
        pnlAcciones.add(btnModificar);
        pnlAcciones.add(btnEliminar);
        add(pnlAcciones, BorderLayout.SOUTH);
    }

    private JButton boton(String texto, Color color, String command) {
        JButton b = new JButton(texto);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setActionCommand(command);   // <-- clave: identifica el botón en el evento
        return b;
    }

    // IProductoBusquedaVista
    @Override public String getNombreBusqueda()    { return txtNombre.getText().trim(); }
    @Override public String getTipoBusqueda()      { return cmbTipo.getSelectedItem().toString(); }
    @Override public String getProveedorBusqueda() { return txtProveedor.getText().trim(); }
    @Override public String getCriterioBusqueda()  { return ""; } // ya no se usa

    @Override
    public Producto getProductoSeleccionado() {
        int fila = tblResultados.getSelectedRow();
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
    public void mostrarResultados(List<Producto> resultados) {
        modeloTabla.setRowCount(0);
        for (Producto p : resultados) {
            modeloTabla.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getTipo(),
                p.getCantidad(), p.getPrecio(),
                p.getFechaCaducidad(), p.getProveedor()
            });
        }
    }

    @Override
    public void agregarListener(ActionListener listener) {
        btnBuscarNombre.addActionListener(listener);
        btnBuscarTipo.addActionListener(listener);
        btnBuscarProveedor.addActionListener(listener);
        btnVerTodos.addActionListener(listener);
        btnModificar.addActionListener(listener);
        btnEliminar.addActionListener(listener);
        btnCrear.addActionListener(listener);
    }
}
