package vista;

import modelo.dto.Producto;

import javax.swing.*;
import java.awt.*;

public class ProductoModificarDialog extends JDialog {

    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private boolean confirmado = false;

    public ProductoModificarDialog(JFrame parent, Producto p) {
        super(parent, "Modificar producto — " + p.getNombre(), true);
        setSize(320, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout(8, 8));

        // Info del producto (solo lectura)
        JPanel pnlInfo = new JPanel(new GridLayout(4, 2, 6, 6));
        pnlInfo.setBorder(BorderFactory.createEmptyBorder(12, 12, 4, 12));

        pnlInfo.add(new JLabel("Producto:"));
        pnlInfo.add(new JLabel(p.getNombre()));

        pnlInfo.add(new JLabel("Tipo:"));
        pnlInfo.add(new JLabel(p.getTipo().toString()));

        txtCantidad = new JTextField(String.valueOf(p.getCantidad()));
        pnlInfo.add(new JLabel("Nueva cantidad:"));
        pnlInfo.add(txtCantidad);

        txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
        pnlInfo.add(new JLabel("Nuevo precio:"));
        pnlInfo.add(txtPrecio);

        add(pnlInfo, BorderLayout.CENTER);

        // Botones
        JButton btnGuardar  = boton("Guardar", new Color(80, 170, 100));
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            confirmado = true;
            dispose();
        });
        btnCancelar.addActionListener(e -> dispose());

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnCancelar);
        add(pnlBotones, BorderLayout.SOUTH);
    }

    private JButton boton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    public boolean isConfirmado() { return confirmado; }

    public int getNuevaCantidad() {
        try { return Integer.parseInt(txtCantidad.getText().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    public double getNuevoPrecio() {
        try { return Double.parseDouble(txtPrecio.getText().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
