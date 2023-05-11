package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Producto;

import java.sql.*;

public class ProductoDAO {

    final private Connection con;

    public ProductoDAO(Connection con){
        this.con = con;
    }

    public void guardar(Producto producto) throws SQLException {

        String nombre = producto.getNombre();
        String descripcion = producto.getDescripcion();
        Integer cantidad = producto.getCantidad();

        try(con) {

            con.setAutoCommit(false);

            final PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO" +
                            "(nombre, descripcion, cantidad) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            try(statement){
                try {

                    ejecutaRegistro(producto, statement);

                    con.commit();
                    System.out.println("COMMIT");

                } catch (Exception e){

                    con.rollback();
                    System.out.println("ROLLBACK");

                }

            }

        }
    }

    private static void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try(resultSet) {
            while(resultSet.next()){
                producto.setId(resultSet.getInt(1));
                System.out.println(producto.toString());
            }
        }

    }

}
