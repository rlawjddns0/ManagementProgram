package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ScheduleSet {

	static final String id = "root";
	static final String pass = "dhwldud7133";
	String url = "jdbc:mysql://localhost:3306/java?serverTimezone=UTC&SSL=false&useSSL=false";

	public ScheduleSet() {
		System.out.println("MySQL JDBC 드라이버 로딩중...");
		try {
			////////////////////////// a/////////////
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("MySQL JDBC 드라이버 로딩 성공...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public Scheduleinfo select() { Scheduleinfo info = new Scheduleinfo();
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * try { conn = DriverManager.getConnection(url,id,pass); String query =
	 * "select * from scheduler"; pstmt = conn.prepareStatement(query); rs =
	 * pstmt.executeQuery(); while(rs.next()) {
	 * info.setPlace(rs.getString("place")); info.setTitle(rs.getString("title"));
	 * info.setInformation(rs.getString("information")); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { try { if(rs != null)
	 * rs.close(); if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); }
	 * catch(Exception e) { e.printStackTrace(); } } return info; }
	 */
	public void select(TableView<Scheduleinfo> mytable) {
		Scheduleinfo info = new Scheduleinfo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from scheduler";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				info.setPlace(rs.getString("place"));
				info.setTitle(rs.getString("title"));
				info.setInformation(rs.getString("information"));
				info.setYear(rs.getString("year"));
				info.setD_day(rs.getString("d_day"));
		/*		info.setMonth(rs.getString("month"));
				info.setDay(rs.getString("day"));*/
				mytable.getItems().add(new Scheduleinfo(new SimpleStringProperty(info.gettitle()),
						new SimpleStringProperty(info.getPlace()),new SimpleStringProperty(info.getInformation()),
						new SimpleStringProperty(info.getYear()),new SimpleStringProperty(info.getD_day())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void insert(String information, String title, String place, String year, String d_day) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into scheduler(information,title,place,year,d_day) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, information);
			pstmt.setString(2, title);
			pstmt.setString(3, place);
			pstmt.setString(4, year);
			pstmt.setString(5, d_day);
	//		pstmt.setString(5, month);
	//		pstmt.setString(6, day);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(String information) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         conn = DriverManager.getConnection(url,id,pass);
	         String query = "delete from scheduler where information =? ";
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, information);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         } catch(Exception e) {
	            e.printStackTrace();
	         }
	      }
	   }
}
