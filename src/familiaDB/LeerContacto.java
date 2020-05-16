package familiaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LeerContacto {
	
	private String url;
	private String usr;
	private String pwd;
	private String sql;
	
	Connection conn;
	Statement st;
	PreparedStatement ps;
	
	public LeerContacto() {
		url = "jdbc:mysql://localhost:3306/familia";
		usr = "root";
		pwd = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void leer() {
		sql = "select nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, id_domicilio from persona";
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print(" ");
				System.out.print(rs.getString(2));
				System.out.print(" ");
				System.out.print(rs.getString(3));
				System.out.print(" ");
				System.out.print(rs.getDate(4));
				System.out.print(" ");
				System.out.print(rs.getInt(5));
				System.out.println();
			}
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void introducir() {
		sql = "insert into persona (nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, id_domicilio)" + "values ('Josuke', 'joestar', 'higashikata', '2003-02-11', 2)";
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			st = conn.createStatement();
			int res = st.executeUpdate(sql);
			if (res == 1) {
				System.out.println("Se inserto registro");
			}else {
				System.out.println("No se pudo insertar el registro");
			}
			st.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void actualizar() {
		sql = "update persona set apellidoPaterno='Lopez' where id_persona = 3";
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			st = conn.createStatement();
			int res = st.executeUpdate(sql);
			if (res == 1) {
				System.out.println("Se actualizo registro");
			}else {
				System.out.println("No se pudo actualizar el registro");
			}
			st.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void eliminar() {
		sql = "delete from persona where id_persona=5";
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			st = conn.createStatement();
			int res = st.executeUpdate(sql);
			if (res == 1) {
				System.out.println("Se elimino registro");
			}else {
				System.out.println("No se pudo eliminar el registro");
			}
			st.close();
			conn.close();
		}catch (Exception e) {
		e.printStackTrace();
	}
		
}
	public void leero() {
		sql = "select id_persona, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, id_domicilio from persona where id_persona = ?";
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			int parametro = 6;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, parametro);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt(1));
				System.out.print(" ");
				System.out.print(rs.getString(2));
				System.out.print(" ");
				System.out.print(rs.getString(3));
				System.out.print(" ");
				System.out.print(rs.getString(4));
				System.out.print(" ");
				System.out.print(rs.getDate(5));
				System.out.print(" ");
				System.out.print(rs.getInt(6));
				System.out.println();
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
