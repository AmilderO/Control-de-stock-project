package com.alura.jdbc.persistencia;

import java.sql.Connection;

public class PersistenciaProducto {

    private Connection conn;

    public PersistenciaProducto(Connection conn){
        this.conn = conn;
    }

}
