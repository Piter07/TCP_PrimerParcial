package py.una.bd;

import py.una.entidad.MetereoDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetereoDAO {

    /**
     *
     * @return
     */
    public List<MetereoDatos> seleccionar() {
        String query = "SELECT id_estacion, ciudad, porcentaje_humedad, temperatura,velocidad_viento,fecha,hora FROM metereodatos";

        List<MetereoDatos> lista = new ArrayList<MetereoDatos>();

        Connection conn = null;
        try
        {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                MetereoDatos m = new MetereoDatos();
                m.setId_estacion(rs.getLong(1));
                m.setCiudad(rs.getString(2));
                m.setPorcentaje_humedad(rs.getDouble(3));
                m.setTemperatura(rs.getInt(4));
                m.setVelocidad_viento(rs.getDouble(5));
                m.setFecha(rs.getString(6));
                m.setHora(rs.getString(7));

                lista.add(m);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return lista;

    }

    public List<MetereoDatos> seleccionarPorEstacion(long estacion) {
        String SQL = "SELECT id_estacion, ciudad, porcentaje_humedad, temperatura,velocidad_viento FROM metereodatos WHERE id_estacion = ? ";

        List<MetereoDatos> lista = new ArrayList<MetereoDatos>();

        Connection conn = null;
        try
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, estacion);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                MetereoDatos m = new MetereoDatos();
                m.setId_estacion(rs.getLong(1));
                m.setCiudad(rs.getString(2));
                m.setPorcentaje_humedad(rs.getDouble(3));
                m.setTemperatura(rs.getInt(4));
                m.setVelocidad_viento(rs.getDouble(5));
                m.setFecha(rs.getString(6));
                m.setHora(rs.getString(7));

                lista.add(m);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return lista;

    }
    public ArrayList<Integer> seleccionarPorCiudad(String ciudad) {
        String SQL = "SELECT temperatura FROM metereodatos WHERE ciudad = ? ";

        List<Integer> lista = new ArrayList<Integer>();

        Connection conn = null;
        try
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ciudad);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                 int m;
                m = rs.getInt(1);

                lista.add(m);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return (ArrayList<Integer>) lista;

    }

    public long insertar(MetereoDatos m) throws SQLException {

        String SQL = "INSERT INTO metereodatos(id_estacion,ciudad,porcentaje_humedad,temperatura,velocidad_viento,fecha,hora) "
                + "VALUES(?,?,?,?,?,?,?)";

        long id = 0;
        Connection conn = null;

        try
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, m.getId_estacion());
            pstmt.setString(2, m.getCiudad());
            pstmt.setDouble(3, m.getPorcentaje_humedad());
            pstmt.setInt(4,m.getTemperatura());
            pstmt.setDouble(5, m.getVelocidad_viento());
            pstmt.setString(6, m.getFecha());
            pstmt.setString(7, m.getHora());



            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la insercion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }

        return id;


    }


    public long actualizar(MetereoDatos m) throws SQLException {

        String SQL = "UPDATE metereodatos SET ciudad = ? , porcentaje_humedad = ? , temperatura = ? , velocidad_viento = ? , fecha = ? , hora = ? WHERE id_estacion = ? ";

        long id = 0;
        Connection conn = null;

        try
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, m.getCiudad());
            pstmt.setDouble(2, m.getPorcentaje_humedad());
            pstmt.setInt(3,m.getTemperatura());
            pstmt.setDouble(4, m.getVelocidad_viento());
            pstmt.setString(5, m.getFecha());
            pstmt.setString(6, m.getHora());
            pstmt.setLong(7, m.getId_estacion());


            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return id;
    }

    public long borrar(long estacion) throws SQLException {

        String SQL = "DELETE FROM metereodatos WHERE id_estacion = ? ";

        long id = 0;
        Connection conn = null;

        try
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, estacion);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminación: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return id;
    }


}
