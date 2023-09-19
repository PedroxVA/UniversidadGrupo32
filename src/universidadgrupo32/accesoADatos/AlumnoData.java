
package universidadgrupo32.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            ps.close();
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
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno.");
        }
    }
    
    public void eliminarAlumno(int dni){
        String sql = "UPDATE alumno set estado= 0 WHERE dni = ?"; //se cambio el paramentro de id a dni
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Alumno Eliminado.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno.");
        }
        
    }
    
 public Alumno buscarAlumno(int id){
        String sql="SELECT dni,apellido,nombre, fechaNacimiento FROM alumno WHERE idAlumno = ? AND estado = 1";
        Alumno alumno=null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                
                alumno=new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);
                
            } else {
                JOptionPane.showMessageDialog(null,"No existe ese alumno");
            }
            ps.close();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno"+ex.getMessage());
            
        }
        return alumno;
   
    }
 
 public Alumno buscarAlumnoPorDni(int dni){
        String sql="SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE dni = ? AND estado = 1";
        Alumno alumno=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                
                alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(dni);
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
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
 
 public List<Alumno> listarAlumnos(){
        String sql="SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE estado = 1";
        ArrayList<Alumno> alumnos=new ArrayList();
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                Alumno alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);
                
                alumnos.add(alumno);
        }
            ps.close();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
            
        }
        return alumnos;
   
    }
    
    
    public static void main(String[] args) {
        //Alumno juan = new Alumno(333, "Armando", "Mesas", LocalDate.of(2003, 10, 19), true);
        //AlumnoData alu = new AlumnoData();
        
        //-----------------------------------------------------------------------------//
        
        //alu.guardarAlumno(juan);
        //alu.modificarAlumno(juan);
        //alu.eliminarAlumno(1);
        
        //Alumno aluEncontrado = alu.buscarAlumnoPorDni(111);
        //if (aluEncontrado != null){
        //    System.out.println("Dni: "+aluEncontrado.getDni());
        //    System.out.println("Apellido: "+aluEncontrado.getApellido());
        //}

        //List<Alumno> listaAlumnos = alu.listarAlumnos();
        //for (Alumno alumno : listaAlumnos) {
        //    System.out.println("Apellido: "+alumno.getApellido());
        //    System.out.println("-------");
        //}
    }
}
















