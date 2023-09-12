
package universidadgrupo32.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES( ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            
            int exito = ps.executeUpdate();
            if (exito==1){
                JOptionPane.showMessageDialog(null, "La inscripción has sido registrada.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla inscripción"+ ex.getMessage());
        }
    
    }
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        AlumnoData alumno = new AlumnoData();
        MateriaData materia = new MateriaData();
        InscripcionData incs = new InscripcionData(materia, alumno);
        
        
        Alumno a1 = incs.aluData.buscarAlumno(3);
        Materia m1 = incs.matData.buscarMateria(3);
   
        Inscripcion i1 = new Inscripcion(a1, m1, 10.0);
        
        incs.guardarInscripcion(i1);
        
    }
}






