package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    final private Connection con;

    public ProductoDAO(Connection con){
        this.con = con;
    }

    public List<Producto> listar() {

        try {
            Statement statement = con.createStatement();

            try(statement) {
                boolean result = statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
                final ResultSet resultset = statement.getResultSet();

                try(resultset) {
                    List<Producto> resultado = new ArrayList<>();

                    while(resultset.next()){
                        Producto fila = new Producto(
                                resultset.getInt("ID"),
                                resultset.getString("NOMBRE"),
                                resultset.getString("DESCRIPCION"),
                                resultset.getInt("CANTIDAD")
                        );


                        resultado.add(fila);
                    }
                    return resultado;
                }

            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void guardar(Producto producto) {

        String nombre = producto.getNombre();
        String descripcion = producto.getDescripcion();
        Integer cantidad = producto.getCantidad();

        try {
            final PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO" +
                            "(nombre, descripcion, cantidad, categoria_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try(statement){
                    ejecutaRegistro(producto, statement);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());
        statement.setInt(4, producto.getCategoriaId());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try(resultSet) {
            while(resultSet.next()){
                producto.setId(resultSet.getInt(1));
                System.out.println(producto.toString());
            }
        }

    }

    public int eliminar(Producto producto) {

        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = (?)");

            try(statement) {
                statement.setInt(1, producto.getId());
                statement.execute();
                int cantidadModificada = statement.getUpdateCount();
                return cantidadModificada;
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int modificar(Producto producto){

        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET " +
                    "NOMBRE = (?), DESCRIPCION = (?), CANTIDAD = (?) WHERE ID = (?)");

            try(statement){
                statement.setString(1, producto.getNombre());
                statement.setString(2, producto.getDescripcion());
                statement.setInt(3, producto.getCantidad());
                statement.setInt(4, producto.getId());

                statement.execute();

                int cantidadModificada = statement.getUpdateCount();

                return cantidadModificada;
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
