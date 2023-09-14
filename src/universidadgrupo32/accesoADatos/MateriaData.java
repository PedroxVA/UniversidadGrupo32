
package universidadgrupo32.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadgrupo32.entidades.Materia;

/* @author PedroxVA */
public class MateriaData {
    private Connection con = null;

    public MateriaData() {
        con = Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        String sql = "INSERT INTO materia(nombre, anio, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia Guardada!");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla materia");
        }
    }
    
    public void modificarMateria(Materia materia){
        String sql = "UPDATE materia SET nombre = ?, anio = ? WHERE idMateria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setInt(3, materia.getIdMateria());
            
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia modificada con exito!");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla materia");
        }
        
    }
    
    public void eliminarMateria(int id){
        String sql = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia eliminada logicamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla materia");
        }
    }

    public Materia buscarMateria(int id){
        String sql = "SELECT nombre, anio, estado FROM materia WHERE idMateria = ? AND estado = 1";
        Materia materia=null;
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                materia = new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("anio"));
                materia.setActivo(true);
            } else{
                JOptionPane.showMessageDialog(null, "Materia no encontrada");
            }
            ps.close(); //duda  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla materia");
        }
        return materia;
    }
    
    public List<Materia> listarMaterias(){
        String sql = "SELECT idMateria, nombre, anio FROM materia WHERE estado = 1";
        ArrayList<Materia> listaMaterias = new ArrayList();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("anio"));
                
                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla materia"+ex.getMessage());//getmessage es muy útil
        }
        
        return listaMaterias;
    }
    
    public static void main(String[] args) {
        Materia materia = new Materia("Ética y Ciudadanía", 1, true);
        MateriaData mate = new MateriaData();
        
        //------------------------------------------------------------------------
        //mate.guardarMateria(materia);
        //mate.modificarMateria(materia);
        //mate.eliminarMateria(5);
        
        //Materia mateEncontrada = mate.buscarMateria(3);
        //if (mateEncontrada!=null){
        //    System.out.println("Nombre: "+mateEncontrada.getNombre());
        //}
        
        //List<Materia> listaMates = mate.listarMaterias();
        //for (Materia m : listaMates) {
        //    System.out.println("Nombre: "+m.getNombre());
        //}
    }
}
