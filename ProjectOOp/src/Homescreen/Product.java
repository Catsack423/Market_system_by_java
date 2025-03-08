package Homescreen;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Product {
	private int id;
	private String nameString;
	private String username;
	private int amount;
	private double price;
	private Date date;
	private String description;
	private String category;
	private String tell;
	private String fbid;
	private Image image;
	private byte[] imagebyte;
	
	public Product() {}
	
	public Product(String nameString, String username,int amount ,double price, Date date, String description, String category, String tell, String fbid,Image image) {
        this.nameString = nameString;
        this.username = username;
        this.amount=amount;
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.tell = tell;
        this.fbid = fbid;
        this.image = image;
        this.imagebyte = imageToBytes(this.image);
    }
	
	
	
	public Product(int id,String nameString, String username,int amount ,double price, Date date, String description, String category, String tell, String fbid,Image image) {
        this.id=id;
		this.nameString = nameString;
        this.username = username;
        this.amount=amount;
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.tell = tell;
        this.fbid = fbid;
        this.image = image;
        this.imagebyte = imageToBytes(this.image);
    }

	
	public byte[] getImagebyte() {
		return imagebyte;
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
	
	public int getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
	    return "Product{" +
	            "name='" + nameString + '\'' +
	            ", username='" + username + '\'' +
	            ", amount=" + amount +
	            ", price=" + price +
	            ", date=" + date +
	            ", description='" + description + '\'' +
	            ", category='" + category + '\'' +
	            ", tell='" + tell + '\'' +
	            ", fbid='" + fbid + '\'' +
	            ", image=" + image +
	            '}';
	}
	
    public static byte[] imageToBytes(Image image)  {
    	if (image == null) {
            System.out.println("Error: Image is null!");
            return null;
        }
        
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return outputStream.toByteArray();
        
    }

	
	
	
	
	
	
	
}
