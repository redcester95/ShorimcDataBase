package fr.shorimcdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.md_5.bungee.api.plugin.Plugin;

public class DataBaseAPI extends Plugin{
	
	private static DataBaseAPI instance;
	private Connection connection;
	
	@Override
	public void onEnable() {
		
		instance = this;
		System.out.println("[ShorimcDataBase] Initialisation du plugin");
	}
	
	@Override
	public void onDisable() {
	
		System.out.println("[ShorimcDataBase] Desactivation du plugin");
	
	}
	
	
	public void connect(String host, String database, int port, String user, String password) {
			
			try {
				System.out.println("[Shorimc-DataBase] Connection a la DataBase " + database);
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
				System.out.println("[Shorimc-DataBase] Connection a la DataBase " + database + " reussi");
				
			} catch (SQLException e) {
				
				System.out.println("[Shorimc-DataBase] Connection a la DataBase " + database + " fail");
				e.printStackTrace();
			}
		}
	
	public void disconnect() {
		
		if(isConnected()) {
			try {
				System.out.println("[Shorimc-DataBase] Deconnection de la DataBase");
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected() {
		
		try {
			if(connection == null || (connection.isClosed()) || (connection.isValid(5))) {
				
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void CreateTable(String name, String table) {
		
		try {
			PreparedStatement ps = this.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table);
			ps.execute();
			ps.close();
			System.out.println("[Shorimc-DataBase] Creation de la table " + name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public static DataBaseAPI getInstance() {
		return instance;
	}

}
