package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.dao.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

	public int modificar(Producto producto) throws SQLException {

		final Connection con = new ConnectionFactory().recuperaConexion();

		try(con) {

			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET " +
					"NOMBRE = (?), DESCRIPCION = (?), CANTIDAD = (?) WHERE ID = (?)");

			try(statement){
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setInt(3, producto.getCantidad());
				statement.setInt(4, producto.getId());

				statement.execute();

				int cantidadModificada = statement.getUpdateCount();

				con.close();

				return cantidadModificada;
			}

		}

	}

	public int eliminar(Producto producto) throws SQLException {

		final Connection con = new ConnectionFactory().recuperaConexion();

		try(con) {

			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = (?)");

			try(statement) {

				statement.setInt(1, producto.getId());

				statement.execute();

				int cantidadModificada = statement.getUpdateCount();

				return cantidadModificada;
			}

		}

	}

	public List<Map<String, String>> listar() throws SQLException {

		final Connection con = new ConnectionFactory().recuperaConexion();

		try(con) {

			final Statement statement = con.createStatement();

			try(statement) {

				boolean result = statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");

				final ResultSet resultset = statement.getResultSet();

				try(resultset) {

					List<Map<String, String>> resultado = new ArrayList<>();

					while(resultset.next()){
						Map<String, String> fila = new HashMap<>();
						fila.put("ID", String.valueOf(resultset.getInt("ID")));
						fila.put("NOMBRE", resultset.getString("NOMBRE"));
						fila.put("DESCRIPCION", resultset.getString("DESCRIPCION"));
						fila.put("CANTIDAD", String.valueOf(resultset.getInt("CANTIDAD")));

						resultado.add(fila);


					}

					return resultado;
				}

			}

		}

	}

    public void guardar(Producto producto) throws SQLException {
		ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
		productoDAO.guardar(producto);
	}

}
