package com.miweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/notas";
	private static final String USUARIO = "notasAppUser";
	private static final String CLAVE = "notasAppPass";

	public int modificarRegistro(String consultaSQL) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		Statement sentencia = null;
		int filasAfectadas = 0;
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			sentencia = conexion.createStatement();
			filasAfectadas = sentencia.executeUpdate(consultaSQL);
		} catch (ClassNotFoundException e) {

			System.out.println("Error driver" + e.getMessage());
			throw e;
		} catch (SQLException e) {
			System.out.println("Error de SQL" + e.getMessage());
			throw e;
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e) {
					throw e;
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw e;
				}
			}
		}
		return filasAfectadas;
	}

	public ResultSet seleccionarRegistros(String consultaSQL) {
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet filas = null;
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			sentencia = conexion.createStatement();
			filas = sentencia.executeQuery(consultaSQL);
		} catch (ClassNotFoundException e) {
			System.out.println("Error Driver" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error de SQL " + e.getMessage());
		}
		return filas;
	}
}
