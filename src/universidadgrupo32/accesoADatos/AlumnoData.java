
package universidadgrupo32.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadgrupo32.entidades.Alumno;

/* @author PedroxVA */
public class AlumnoData {
    private Connection con=null;
    
    public AlumnoData(){
        con = Conexion.getConexion();
    }
    
    
    public void guardarAlumno(Alumno alumno){
        String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado)"+
                "VALUES(? ,? ,? ,? ,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5, alumno.getActivo());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                
                alumno.setIdAlumno(rs.getInt(1));
                 JOptionPane.showMessageDialog(null, "Alumno Guardado!");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno.");
        }      
    }
    
    public void modificarAlumno(Alumno alumno){
        String sql = "UPDATE alumno SET dni= ?, apellido = ?, nombre = ?, fechaNacimiento = ?"
                + "WHERE idAlumno = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setInt(5, alumno.getIdAlumno());
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Alumno modificado con exito!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno.");
        }
    }
    
    public void eliminarAlumno(int id){
        String sql = "UPDATE alumno set estado= 0 WHERE idAlumno = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Alumno Eliminado.");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno.");
        }
        
    }
    
 public Alumno buscarAlumno(int id){
        String sql="SELECT dni,apellido,nombre, fechaNacimiento FROM alumno WHERE idAlumno =? AND estado =1";
        Alumno alumno=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                
                alumno=new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni "));
                alumno.setApellido(rs.getString("apellido "));
                alumno.setNombre(rs.getString("nombre "));
                alumno.setFechaNac(rs.getDate("fechaNacimiento ").toLocalDate());
                alumno.setActivo(true);
                
            } else {
                JOptionPane.showMessageDialog(null,"No existe ese alumno");
            }
            ps.close();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
            
        }
        return alumno;
   
    }
    
    
    public static void main(String[] args) {
        Alumno juan = new Alumno(1, 12312312, "Luna", "Juan Pedro", LocalDate.of(1980, 4, 25), true);
        AlumnoData alu = new AlumnoData();
        //alu.guardarAlumno(juan);
        //alu.modificarAlumno(juan);
        alu.eliminarAlumno(1);
        
    }
}
















