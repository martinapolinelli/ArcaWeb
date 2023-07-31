package presentation.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import integration.AnimaleDto;
import integration.ConnectionManager;


public class ArcaDao {
	public static Boolean insert(AnimaleDto animale) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("INSERT INTO arcaweb.arca (id, specie, peso) VALUES (?,?,?)");
			stmt.setInt(1, animale.getId());
			stmt.setString(2, animale.getSpecie());
			stmt.setInt(3, animale.getPeso());

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
				return false;
			}

		}
	}

	public static Boolean delete(int id) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("delete from arcaweb.arca where id = ?");
			stmt.setInt(1, id);

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {

			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
	}

	public static Map<Integer, AnimaleDto> select() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		AnimaleDto animale;
		Map<Integer, AnimaleDto> animaliMapDto = new HashMap<>();

		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery("select * from arca");
			while (rs.next()) {
				animale = new AnimaleDto();
				animale.setId(rs.getInt("id"));
				animale.setPeso(rs.getInt("peso"));
				animale.setSpecie(rs.getString("specie"));

				animaliMapDto.put(animale.getId(), animale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
		return animaliMapDto;
	}
	
	public static Boolean update(Integer id, Integer peso) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("UPDATE Arca set peso = ? WHERE id = ?");
			stmt.setInt(1, peso);
			stmt.setInt(2, id);

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
				return false;
			}

		}
	}

	public static AnimaleDto selectOne(Integer id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AnimaleDto animale = null;

		try {
			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("SELECT * FROM arca WHERE id = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				animale = new AnimaleDto();
				animale.setId(rs.getInt("id"));
				animale.setPeso(rs.getInt("peso"));
				animale.setSpecie(rs.getString("specie"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
		return animale;
	}
	
}
