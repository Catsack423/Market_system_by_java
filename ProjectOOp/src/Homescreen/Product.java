package Homescreen;

import java.awt.Image;
import java.sql.Date;

public class Product {
	private String nameString;
	private String username;
	private double price;
	private Date date;
	private String description;
	private String category;
	private String tell;
	private String fbid;
	private Image image;
	
	public Product() {}
	
	public Product(String nameString, String username, double price, Date date, String description, String category, String tell, String fbid) {
        this.nameString = nameString;
        this.username = username;
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.tell = tell;
        this.fbid = fbid;
        this.image = image;
    }

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getNameString() {
		return nameString;
	}


	public void setNameString(String nameString) {
		this.nameString = nameString;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getTell() {
		return tell;
	}


	public void setTell(String tell) {
		this.tell = tell;
	}


	public String getFbid() {
		return fbid;
	}


	public void setFbid(String fbid) {
		this.fbid = fbid;
	}
	
	
	
	
	
	
	
	
}
