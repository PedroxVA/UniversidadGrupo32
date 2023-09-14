
package universidadgrupo32.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import universidadgrupo32.entidades.Alumno;
import universidadgrupo32.entidades.Inscripcion;
import universidadgrupo32.entidades.Materia;

/* @author PedroxVA */
public class InscripcionData {
    Connection con = null;
    MateriaData matData = null;
    AlumnoData aluData = null;

    public InscripcionData(MateriaData matData, AlumnoData aluData) {
        con = Conexion.getConexion();
        this.matData = matData;
        this.aluData = aluData;
    }

    public void guardarInscripcion(Inscripcion insc){
        String sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES ( ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()){
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "La inscripción has sido registrada.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
    
        
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int exito = ps.executeUpdate();
            
            if (exito>0){
                JOptionPane.showMessageDialog(null, "La inscripción ha sido eliminada.");
            } else{
                JOptionPane.showMessageDialog(null, "Inscripción no encontrada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
        }

    public void actualizarNota(int idAlumno, int idMateria, double nota){
        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int exito = ps.executeUpdate();
            
            if (exito>0){
                JOptionPane.showMessageDialog(null, "La nota se ha actualizado exitosamente!.");
            } else{
                JOptionPane.showMessageDialog(null, "Inscripción no encontrada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
    }

    public List<Inscripcion> obtenerInscripciones(){
        String sql = "SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion";
        ArrayList<Inscripcion> listaInscripciones = new ArrayList();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Inscripcion insc = new Inscripcion();
                insc.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                insc.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                insc.setNota(rs.getDouble("nota"));
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                
                listaInscripciones.add(insc);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
        return listaInscripciones;
}
 
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        String sql = "SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion WHERE idAlumno = ?";
        ArrayList<Inscripcion> listaInscripciones = new ArrayList();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Inscripcion insc = new Inscripcion();
                insc.setAlumno(aluData.buscarAlumno(id));
                insc.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                insc.setNota(rs.getDouble("nota"));
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                
                listaInscripciones.add(insc);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
        return listaInscripciones;
}
                 
    public List<Materia> obtenerMateriasCursadas(int id){
        String sql = "SELECT inscripcion.idMateria, nombre, anio FROM inscripcion, materia "
                + "WHERE inscripcion.idMateria = materia.idMateria "
                + "AND inscripcion.idAlumno = ?";
        ArrayList<Materia> listaMateriasCursadas = new ArrayList();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia materia = matData.buscarMateria(rs.getInt("idMateria"));
                listaMateriasCursadas.add(materia); //diff
            } 
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
        return listaMateriasCursadas;
    }

    public List<Materia> obtenerMateriasNOCursadas(int id){ //diff
        List<Materia> listaMateriasCursadas = obtenerMateriasCursadas(id);
        List<Materia> listaMaterias = matData.listarMaterias();
        listaMaterias.removeAll(listaMateriasCursadas);

        return listaMaterias;
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        String sql = "SELECT alumno.idAlumno, alumno.dni, alumno.apellido, alumno.nombre, alumno.fechaNacimiento "
                   + "FROM alumno " 
                   + "JOIN inscripcion ON alumno.idAlumno = inscripcion.idAlumno " 
                   + "WHERE inscripcion.idMateria = ? AND alumno.estado = 1";
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(rs.getBoolean("estado"));
                listaAlumnos.add(alumno);
            }
            
            ps.close();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción "+ ex.getMessage());
        }
        return listaAlumnos;
    }
    
    public static void main(String[] args) {
        AlumnoData alumno = new AlumnoData();
        MateriaData materia = new MateriaData();
        InscripcionData insc = new InscripcionData(materia, alumno);
        
        
        //Alumno a1 = incs.aluData.buscarAlumno(4);
        //System.out.println(a1);
        //Materia m1 = incs.matData.buscarMateria(6);
        //System.out.println(m1);
   
        //Inscripcion i1 = new Inscripcion(a1, m1, 10.0);
        
        //insc.guardarInscripcion(i1);
        //insc.borrarInscripcionMateriaAlumno(4, 6);
        //insc.actualizarNota(5, 6, 5);
        
        //List<Inscripcion> listaInscripciones = insc.obtenerInscripciones();
        //for (Inscripcion x : listaInscripciones) {
        //    System.out.println("idInscripto: "+x.getIdInscripcion());
        //    System.out.println("---");
        //}
        
        //List<Inscripcion> listaInscripciones = insc.obtenerInscripcionesPorAlumno(3);
        //for (Inscripcion x : listaInscripciones) {
        //System.out.println("idInscripto: "+x.getIdInscripcion());
        //System.out.println("---");
        //}
        
        //List<Materia> listaMaterias = insc.obtenerMateriasCursadas(3);
        //for (Materia x : listaMaterias) {
        //System.out.println("Materia: "+x.getNombre());
        //System.out.println("---");
        //}
        
        //List<Materia> listaMaterias = insc.obtenerMateriasNOCursadas(3);
        //for (Materia x : listaMaterias) {
        //System.out.println("Materia: "+x.getNombre());
        //System.out.println("---");
        //}
        
        //List<Alumno> listaAlumnos = insc.obtenerAlumnosXMateria(6);
        //for (Alumno x : listaAlumnos) {
        //    System.out.println("Alumno: "+x.getNombre());
        //    System.out.println("---");
        //}
    }
}






