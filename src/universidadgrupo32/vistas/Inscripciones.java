/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo32.vistas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import universidadgrupo32.accesoADatos.AlumnoData;
import universidadgrupo32.accesoADatos.InscripcionData;
import universidadgrupo32.accesoADatos.MateriaData;
import universidadgrupo32.entidades.Alumno;
import universidadgrupo32.entidades.Inscripcion;
import universidadgrupo32.entidades.Materia;

/**
 *
 * @author jfneg
 */
public class Inscripciones extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo=new DefaultTableModel();
    private AlumnoData aluData = new AlumnoData();
    private MateriaData matData = new MateriaData();
    private InscripcionData insData = new InscripcionData(matData, aluData);
    
    /**
     * Creates new form Inscripciones
     */
    public Inscripciones() {
        initComponents(); 
        armarTabla();
        cargarCombo();
        jRMatInscriptas.setSelected(true);
        jBInscribir.setEnabled(false);
        jBAnular.setEnabled(true);
        borrarFilas();
        cargarDatos();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLListadoMaterias = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jRMatInscriptas = new javax.swing.JRadioButton();
        jRMatNoInscriptas = new javax.swing.JRadioButton();
        jLMateriasInscriptas = new javax.swing.JLabel();
        jLMateriasNoInsriptas = new javax.swing.JLabel();
        jBInscribir = new javax.swing.JButton();
        jBAnular = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jLFormularioInscrip = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTTabla = new javax.swing.JTable();
        jLSeleccionAlumno = new javax.swing.JLabel();

        setBackground(new java.awt.Color(68, 164, 132));

        jPanel1.setBackground(new java.awt.Color(68, 164, 132));

        jLListadoMaterias.setText("Listado de Materias");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jRMatInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRMatInscriptasActionPerformed(evt);
            }
        });

        jRMatNoInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRMatNoInscriptasActionPerformed(evt);
            }
        });

        jLMateriasInscriptas.setText("Materias Inscriptas");

        jLMateriasNoInsriptas.setText("Materias no Inscriptas");

        jBInscribir.setText("Inscribir");
        jBInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInscribirActionPerformed(evt);
            }
        });

        jBAnular.setText("Anular Inscripción");
        jBAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAnularActionPerformed(evt);
            }
        });

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jLFormularioInscrip.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLFormularioInscrip.setText("Formulario de Inscripción");

        jTTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTTabla);

        jLSeleccionAlumno.setText("Seleccione un Alumno: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBInscribir)
                        .addGap(66, 66, 66)
                        .addComponent(jBAnular)
                        .addGap(53, 53, 53)
                        .addComponent(jBSalir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLFormularioInscrip))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLSeleccionAlumno)
                                .addGap(38, 38, 38)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLListadoMaterias)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jRMatInscriptas)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLMateriasInscriptas)
                                        .addGap(59, 59, 59)
                                        .addComponent(jRMatNoInscriptas)))
                                .addGap(18, 18, 18)
                                .addComponent(jLMateriasNoInsriptas)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLFormularioInscrip)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSeleccionAlumno)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLListadoMaterias)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRMatInscriptas)
                    .addComponent(jRMatNoInscriptas)
                    .addComponent(jLMateriasInscriptas)
                    .addComponent(jLMateriasNoInsriptas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInscribir)
                    .addComponent(jBAnular)
                    .addComponent(jBSalir))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        borrarFilas();
        cargarDatos();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jRMatInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRMatInscriptasActionPerformed
        // TODO add your handling code here:
        jRMatNoInscriptas.setSelected(false);
        borrarFilas();
        cargarDatos();;
        jBInscribir.setEnabled(false);
        jBAnular.setEnabled(true);
    }//GEN-LAST:event_jRMatInscriptasActionPerformed

    private void jRMatNoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRMatNoInscriptasActionPerformed
        // TODO add your handling code here:
        jRMatInscriptas.setSelected(false);
        borrarFilas();
        cargarDatos();
        jBInscribir.setEnabled(true);
        jBAnular.setEnabled(false);
    }//GEN-LAST:event_jRMatNoInscriptasActionPerformed

    private void jBInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInscribirActionPerformed
        // TODO add your handling code here:
        int id = (int)(jTTabla.getValueAt(jTTabla.getSelectedRow(), 0));
        String nombre =(String)(jTTabla.getValueAt(jTTabla.getSelectedRow(), 1));
        int anio = (int)(jTTabla.getValueAt(jTTabla.getSelectedRow(), 2));
        
        Materia mat = new Materia(id , nombre, anio, true);    
        Alumno alu = (Alumno) jComboBox1.getSelectedItem();
        
        Inscripcion insc = new Inscripcion(alu, mat, 0);
        insData.guardarInscripcion(insc);
        borrarFilas();
        cargarDatos();

        
        
        
    }//GEN-LAST:event_jBInscribirActionPerformed

    private void jBAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnularActionPerformed
        // TODO add your handling code here:
        Alumno alu = (Alumno)jComboBox1.getSelectedItem();
        int idAlu = alu.getIdAlumno();
        
        int idMat =(int) jTTabla.getValueAt(jTTabla.getSelectedRow(), 0);
        
        insData.borrarInscripcionMateriaAlumno(idAlu, idMat);
        borrarFilas();
        cargarDatos();
    }//GEN-LAST:event_jBAnularActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAnular;
    private javax.swing.JButton jBInscribir;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<Alumno> jComboBox1;
    private javax.swing.JLabel jLFormularioInscrip;
    private javax.swing.JLabel jLListadoMaterias;
    private javax.swing.JLabel jLMateriasInscriptas;
    private javax.swing.JLabel jLMateriasNoInsriptas;
    private javax.swing.JLabel jLSeleccionAlumno;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRMatInscriptas;
    private javax.swing.JRadioButton jRMatNoInscriptas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTTabla;
    // End of variables declaration//GEN-END:variables

    private void armarTabla(){
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Año");
        jTTabla.setModel(modelo);
    }
    
    private void cargarCombo(){
        List<Alumno> listaAlumnos = aluData.listarAlumnos();
        for (Alumno alumno : listaAlumnos) {
            jComboBox1.addItem(alumno);
        }
    }
    private void borrarFilas(){
        int filas = jTTabla.getRowCount()-1;
        for (int f = filas; f>=0; f--){
            modelo.removeRow(f);
        }
    }
    
    private void cargarDatos(){
        //Primero, obtener el alumno
        Alumno alumno = (Alumno)jComboBox1.getSelectedItem();
        //Luego su id;
        int id = alumno.getIdAlumno();
        
        //Segundo, crear una lista de materias usando el id;
        List<Materia> listaMaterias = new ArrayList();
        if (jRMatInscriptas.isSelected()){
        listaMaterias = insData.obtenerMateriasCursadas(id);}
        else if(jRMatNoInscriptas.isSelected()){
        listaMaterias = insData.obtenerMateriasNOCursadas(id);
        }
        
        //Tercero, cargar -----------cada materia------------- a la tabla
        //A traves de un bucle;
        for (Materia mat : listaMaterias) {
            int idMat = mat.getIdMateria();
            String nombreMateria = mat.getNombre();
            int anio = mat.getAnioMateria();
            modelo.addRow(new Object[]{idMat,nombreMateria, anio});
            
        }
        
    }
}

