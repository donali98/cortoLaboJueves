package com.dona.operaciones;

import com.dona.Logica.Persona;
import com.dona.conexion.Conexion;
import com.dona.interfaces.Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operaciones implements Metodos<Persona> {

    private Conexion con = Conexion.conectar();

    private static final String INSERT_QUERY = "insert into personas(numAfiliacion,nombres,apellidos,edad,profesion,estado) values (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "update personas set numAfiliacion = ?, nombres = ?, apellidos = ?, edad = ?, profesion = ?, estado = ? where id = ?";
    private static final String DELETE_QUERY = "delete from personas where id = ?";
    private static final String READ_QUERY = "select * from personas where id = ?";
    private static final String READ_ALL_QUERY = "SELECT * FROM personas";


    @Override
    public boolean create(Persona g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(INSERT_QUERY);
            ps.setString(1,g.getNumAfiliacion());
            ps.setString(2,g.getNombres());
            ps.setString(3,g.getApellidos());
            ps.setInt(4,g.getEdad());
            ps.setString(5,g.getProfesion());
            ps.setBoolean(6,g.getEstado());
            if(ps.executeUpdate()>0){
                return true;
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(DELETE_QUERY);
            ps.setInt(1,Integer.parseInt( key.toString()));
            if(ps.executeUpdate()>0){
                return true;
            }


        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            con.cerrarConexion();
        }

        return false;
    }

    @Override
    public boolean update(Persona c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getId());
            ps = con.getCnx().prepareStatement(UPDATE_QUERY);
            ps.setString(1,c.getNumAfiliacion());
            ps.setString(2,c.getNombres());
            ps.setString(3,c.getApellidos());
            ps.setInt(4,c.getEdad());
            ps.setString(5,c.getProfesion());
            ps.setBoolean(6,c.getEstado());
            if(ps.executeUpdate()>0){
                return true;
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Persona read(Object key) {
        Persona persona = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(READ_QUERY);
            ps.setInt(1,Integer.parseInt(key.toString()));

            rs = ps.executeQuery();

            while (rs.next()){
                persona = new Persona(
                        rs.getInt(1),
                        rs.getInt(5),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(6),
                        rs.getBoolean(7)

                        );

            }
            rs.close();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            con.cerrarConexion();
        }
        return persona;
    }

    @Override
    public ArrayList<Persona> readAll() {
        ArrayList<Persona> personas = new ArrayList<>();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(READ_ALL_QUERY);
            rs = s.executeQuery(READ_ALL_QUERY);
            while (rs.next()){
                personas.add(new Persona(rs.getInt(1),
                        rs.getInt(5),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(6),
                        rs.getBoolean(7)));
            }
            rs.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            con.cerrarConexion();
        }
        return personas;
    }
}
