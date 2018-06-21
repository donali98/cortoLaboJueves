package com.dona.frontend;

import com.dona.Logica.Persona;
import com.dona.operaciones.Operaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Personas extends JFrame {
    public JLabel lblNumAfiliacion,lblNombres,lblApellidos,lblEdad,lblProfesion,lblEstado;
    public JTextField txtNumAfiliacion,txtNombres,txtApellidos,txtEdad;
    public JComboBox cmbProfesion;

    public ButtonGroup siNo;
    public JRadioButton si;
    public JRadioButton no;

    public JTable resultados;
    public JPanel table;

    public JButton btnBuscar,btnInsertar,btnActualizar,btnEliminar,btnLimpiar;

    public static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Personas()  {
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addLabels();
        formulario();
    }

    private void addLabels(){
        lblNumAfiliacion = new JLabel("N Afiliacion");
        lblNombres = new JLabel("Nombre");
        lblApellidos = new JLabel("Apellidos");
        lblEdad = new JLabel("Edad");
        lblProfesion = new JLabel("Profesion");
        lblEstado = new JLabel("Estado");

        lblNumAfiliacion.setBounds(10,10,ANCHOC,ALTOC);
        lblNombres.setBounds(10,60,ANCHOC,ALTOC);
        lblApellidos.setBounds(40,60,ANCHOC,ALTOC);
        lblEdad.setBounds(10,90,ANCHOC,ALTOC);
        lblProfesion.setBounds(40,120,ANCHOC,ALTOC);
        lblEstado.setBounds(40,120,ANCHOC,ALTOC);


    }
    private void formulario(){
        txtNumAfiliacion = new JTextField();
        txtNombres = new JTextField();
        cmbProfesion = new JComboBox();
        txtApellidos = new JTextField();
        txtEdad = new JTextField();

        si = new JRadioButton("si",true);
        no = new JRadioButton("no");

        resultados = new JTable();

        btnBuscar = new JButton("Buscar");
        btnInsertar = new JButton("Insertar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        table = new JPanel();

        cmbProfesion.addItem("Ingeniero");
        cmbProfesion.addItem("Abogado");
        cmbProfesion.addItem("Doctor");

        siNo = new ButtonGroup();
        siNo.add(si);
        siNo.add(no);

        txtNumAfiliacion.setBounds(50,10,ANCHOC,ALTOC);
        btnBuscar.setBounds(150,10,ANCHOC,ALTOC);
        txtNombres.setBounds(50,60,ANCHOC,ALTOC);
        txtApellidos.setBounds(140,60,ANCHOC,ALTOC);
        txtEdad.setBounds(60,180,ANCHOC,ALTOC);
        cmbProfesion.setBounds(80,240,ANCHOC,ALTOC);

        table.setBounds(10,300,500,200);
        table.add(new JScrollPane(resultados));
    }

    private void llenarTabla(){
        tm= new DefaultTableModel(){
            public Class<?> getColumnClass(int col){
                switch (col){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Num. Afiliacion");
        tm.addColumn("Nombre");
        tm.addColumn("Apellido");
        tm.addColumn("Edad");
        tm.addColumn("Profesion");
        tm.addColumn("Estado");

        Operaciones operaciones = new Operaciones();
        ArrayList<Persona> personas = operaciones.readAll();
        for (Persona p: personas){
            tm.addRow(new Object[]{p.getNumAfiliacion(),p.getNombres(),p.getApellidos(),p.getEdad(),p.getProfesion(),p.getEstado()});
        }
    }
}
