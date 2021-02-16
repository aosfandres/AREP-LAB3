package edu.escuelaing.arep.persistencia;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Coneccion a BD
 * 
 */
public class DataBase {
	private static String url = "jdbc:postgresql://ec2-52-7-168-69.compute-1.amazonaws.com:5432/d1686vrarh42vi";
	private static String user = "sfxgjpvnwvgyyr";
	private static String passwd = "49c201f3253ad0c628d15566d3e7e319b3895838108f5261bdb444ad34e7a029";
	private static Connection connect = null;

	/**
	 * Constructor encargado de hacer la coneccion
	 * @throws ClassNotFoundException t
	 */
	public DataBase() throws ClassNotFoundException {
		
		try {
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(url, user, passwd);
			System.out.println("funcion bien");
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	/**
	 * Extraccion de datos de BD
	 * 
	 * @return data list
	 */
	public ArrayList<String[]> getTable() {
		ArrayList<String[]> data = new ArrayList<>();
		try {
			Statement state = connect.createStatement();
			ResultSet res = state.executeQuery("SELECT * FROM todo");
			while (res.next()) {
				String id = res.getString("id");
				String name = res.getString("elemento");
				String[] inf = { id, name };
				data.add(inf);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return data;
	}
}