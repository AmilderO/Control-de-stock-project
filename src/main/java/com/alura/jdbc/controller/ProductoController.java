package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.dao.ProductoDAO;

import java.sql.*;
import java.util.List;


public class ProductoController {

	private ProductoDAO productoDAO;

	public ProductoController() {
		this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(Producto producto) {

		return this.productoDAO.modificar(producto);

	}

	public int eliminar(Producto producto) {
		return productoDAO.eliminar(producto);
	}

	public List<Producto> listar() {
		return productoDAO.listar();
	}

    public void guardar(Producto producto) {
		productoDAO.guardar(producto);
	}

}
