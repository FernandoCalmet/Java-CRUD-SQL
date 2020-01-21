package Clases.OperacionesBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author github.com/fernandocalmet
 */
public class Operacion implements Interfaces.IOperacionesBD 
{
    private BaseDatos.MySql baseDatos = new BaseDatos.MySql();
    private Clases.Entidades.Operacion operacion = new Clases.Entidades.Operacion();
    
    //TODO: Recibe como parametro un objeto, verifica si el objeto existe en la base de datos para crear el registro
    @Override
    public boolean Crear(Object obj) 
    {
        operacion = (Clases.Entidades.Operacion) obj;
        String consultaSql = "INSERT INTO operaciones (nombre, id_modulo) VALUES (?, ?)";      
        PreparedStatement declaracionSql;        
        try
        {
            declaracionSql = baseDatos.conectado().prepareStatement(consultaSql);
            declaracionSql.setString(1, operacion.getNombre());
            declaracionSql.setInt(2, operacion.getId_modulo());
            int filas = declaracionSql.executeUpdate();
            if(filas > 0){return true;}
            else{return false;}
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex.getMessage());
            return false;
        }
    }

    //TODO: Recibe como parametro un objeto, verifica si el objeto existe en la base de datos para eliminar el registro
    @Override
    public boolean Eliminar(Object obj) 
    {
        operacion = (Clases.Entidades.Operacion) obj;
        String consultaSql = "DELETE FROM operaciones WHERE id=?";      
        PreparedStatement declaracionSql;        
        try
        {
            declaracionSql = baseDatos.conectado().prepareStatement(consultaSql);
            declaracionSql.setInt(1, operacion.getId());
            int filas = declaracionSql.executeUpdate();
            if(filas > 0){return true;}
            else{return false;}
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex.getMessage());
            return false;
        }
    }

    //TODO: Recibe como parametro un objeto, verifica si el objeto existe en la base de datos para consultar el registro
    @Override
    public Object[] ListarDetalles(Object obj) 
    {
        String consultaSql = "SELECT * FROM operaciones WHERE id=?";      
        PreparedStatement declaracionSql; 
        ResultSet resultadoSql;
        ResultSetMetaData metaSql;
        Object[] listaDatos = null;
        try{
            declaracionSql = baseDatos.conectado().prepareStatement(consultaSql);  
            declaracionSql.setInt(1, operacion.getId());
            int filas = declaracionSql.executeUpdate();
            if(filas > 0)
            {
                resultadoSql = declaracionSql.executeQuery();
                metaSql = resultadoSql.getMetaData();
                while(resultadoSql.next())
                {
                    Object[] fila = new Object[metaSql.getColumnCount()];
                    for(int i=0; i<fila.length; i++){fila[i] = resultadoSql.getObject(i + 1);}                 
                }                
            }
            else{JOptionPane.showMessageDialog(null, "El registro no existe en la base de datos");}             
        }
        catch(SQLException ex){JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex.getMessage());}
        return listaDatos;
    }

    //TODO: Recibe como parametro un objeto, verifica si el objeto existe en la base de datos para modificar el registro
    @Override
    public boolean Modificar(Object obj) 
    {
        operacion = (Clases.Entidades.Operacion) obj;
        String consultaSql = "UPDATE operaciones SET nombre=?, id_modulo=? WHERE id=?";      
        PreparedStatement declaracionSql;        
        try{
            declaracionSql = baseDatos.conectado().prepareStatement(consultaSql);
            declaracionSql.setString(1, operacion.getNombre());
            declaracionSql.setInt(2, operacion.getId_modulo());
            declaracionSql.setInt(3, operacion.getId());
            int filas = declaracionSql.executeUpdate();
            if(filas > 0){return true;}
            else{ return false;}
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex.getMessage());
            return false;
        }
    }

    //TODO: Verifica los registros en la base de datos y devuelve una lista de los objetos encontrados
    @Override
    public ArrayList<Object[]> ListarTodos() 
    {   
        String consultaSql = "SELECT * FROM operaciones";      
        PreparedStatement declaracionSql; 
        ResultSet resultadoSql;
        ResultSetMetaData metaSql;
        ArrayList<Object[]> listaDatos = new ArrayList<>();
        try
        {
            declaracionSql = baseDatos.conectado().prepareStatement(consultaSql);      
            resultadoSql = declaracionSql.executeQuery();
            metaSql = resultadoSql.getMetaData();
            while(resultadoSql.next())
            {
                Object[] fila = new Object[metaSql.getColumnCount()];
                for(int i=0; i<fila.length; i++){fila[i] = resultadoSql.getObject(i + 1);}
                listaDatos.add(fila);
            }
        }
        catch(SQLException ex){JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex.getMessage());}
        return listaDatos;
    }
}