package Repositorio;
/**
 *
 * @author Fernando Calmet <github.com/fernandocalmet>
 */
import java.util.ArrayList;

public interface IBaseRepositorio 
{
    public boolean Crear(Object obj);
    public boolean Eliminar(Object obj);
    public Object[] ListarDetalles(Object obj);
    public boolean Modificar(Object obj);
    public ArrayList<Object[]> ListarTodos();
}