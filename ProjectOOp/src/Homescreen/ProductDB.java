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

import ClassHelper.ConnecttionDVBproducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class ProductDB {
	
	static ObservableList<Product> products = FXCollections.observableArrayList();
	
	public static ObservableList<Product> getProducts() {
        
        String query = "SELECT * FROM products";

        try {
	        	Connection connection = ConnecttionDVBproducts.conect_productDB();
	        	
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
