package accesoDatos;

import java.util.List;

public interface ICrud {
    boolean Insertar(Object object);
    Object Leer(Object object);
    boolean Actualizar(int index, Object object);
    boolean Eliminar(Object object);
    int BuscarIndex(Object object);
    List<?> LeerTodos();
}
