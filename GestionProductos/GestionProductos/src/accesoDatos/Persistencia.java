package accesoDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia<T extends Serializable> {

    private List<T> lista;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String archivo;

    public Persistencia(String archivo) {
        this.archivo = archivo;
        this.lista = new ArrayList<>();
        cargar();
    }

    @SuppressWarnings("unchecked")
    private void cargar() {
        File f = new File(archivo);
        if (!f.exists()) return;
        try {
            entrada = new ObjectInputStream(new FileInputStream(archivo));
            lista = (List<T>) entrada.readObject();
            entrada.close();
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
    }

    public void guardar() {
        try {
            salida = new ObjectOutputStream(new FileOutputStream(archivo));
            salida.writeObject(lista);
            salida.flush();
            salida.close();
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public List<T> getLista()          { return lista; }
    public void setLista(List<T> lista){ this.lista = lista; }
    public String getArchivo()         { return archivo; }
}
