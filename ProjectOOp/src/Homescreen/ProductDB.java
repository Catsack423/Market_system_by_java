package Homescreen;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class ProductDB {
	private List<Product> dbProducts = new ArrayList<Product>();
	
	private static Connection DBconnection() {
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
	
	public static ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String query = "SELECT * FROM products";

        try {
	        	Connection connection = DBconnection();
	        	
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(query);
	             
	             while (resultSet.next()) {
	            	 
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                String username= resultSet.getString("username");
	                double price = resultSet.getDouble("price");
	                Date date = resultSet.getDate("date");
	                String category = resultSet.getString("category");
	                String description = resultSet.getString("description");
	                String fbid = resultSet.getString("fbid");
	                String tell = resultSet.getString("tell");
	                int amout = resultSet.getInt("amount");
	                
	                byte[] imageData = resultSet.getBytes("image_data");
	                 if (imageData != null) {
	                     ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
	                     products.add(new Product(id, name, username, amout, price, date, 
		                		 description, category, tell, fbid, new Image(inputStream)));
	                 }else {
	                	 System.out.println("cannot add product list");
	                	 return null;
					}
	                 
	         
	            	}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
	
}
