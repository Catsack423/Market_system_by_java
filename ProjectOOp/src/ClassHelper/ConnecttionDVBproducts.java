package ClassHelper;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Homescreen.Product;


public class ConnecttionDVBproducts {
	public static Connection conect_productDB() {
		try {
            // แสดง message เพื่อตรวจสอบว่าเข้าถึงเมธอดนี้หรือไม่
            System.out.println("กำลังเชื่อมต่อกับฐานข้อมูล...");
            
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("โหลด Driver สำเร็จ");
            
            // สร้าง connection string ที่มีพารามิเตอร์เพิ่มเติม
            String url = "jdbc:mysql://10.62.58.97:3306/marketapp";
            String user = "root";
            String password = "kimbap001";
            
            System.out.println("กำลังพยายามเชื่อมต่อกับ URL: " + url);
            
            // ทดลองเชื่อมต่อ
            Connection connect = DriverManager.getConnection(url, user, password);
            System.out.println("เชื่อมต่อกับฐานข้อมูลสำเร็จ");
            
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println("ไม่พบ JDBC Driver: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("เกิดข้อผิดพลาดในการเชื่อมต่อฐานข้อมูล: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("ข้อผิดพลาดที่ไม่รู้จัก: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
	}



	public static  void InssertProductTODB(Product product) {
		try {
	    	Connection connection = conect_productDB();
	    	Statement statement = connection.createStatement();
	    	String insertProductString= "INSERT INTO products" + " (price,date,name,category,description,"
	    			+ "username,fbid,tell,image_data,amount) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)";//10 prepre
	    	PreparedStatement prepar = connection.prepareStatement(insertProductString);
	    	prepar.setDouble(1, product.getPrice());
	    	prepar.setDate(2, product.getDate()); 
	    	prepar.setString(3, product.getNameString());
	    	prepar.setString(4, product.getCategory());
	    	prepar.setString(5, product.getDescription());
	    	prepar.setString(6, product.getUsername());
	    	prepar.setString(7, product.getFbid());
	    	prepar.setString(8, product.getTell());
	    	prepar.setBytes(9, product.getImagebyte());
	    	prepar.setInt(10, product.getAmount());
	    	prepar.executeUpdate();
	    	
	        System.out.println("Insert success!");

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
