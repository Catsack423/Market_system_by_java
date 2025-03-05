package SellScreen;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import ClassHelper.Alertmeassage;
import ClassHelper.ConnecttionDVBproducts;
import Homescreen.HomescreenController;
import Homescreen.Product;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SellscreenController implements Initializable{
	
	 	@FXML
	    private TextField amoutTextfeild;

	    @FXML
	    private Button buybutton;

	    @FXML
	    private TextArea descripTextarea;

	    @FXML
	    private TextField fbidTextfeild;

	    @FXML
	    private ImageView logoutImage;

	    @FXML
	    private Button logoutbutton;

	    @FXML
	    private MenuButton menucategorybutton;

	    @FXML
	    private TextField priceTextfeild;

	    @FXML
	    private TextField productnameTextFeild;

	    @FXML
	    private Button selectpicturebutton;

	    @FXML
	    private Label selllabel;

	    @FXML
	    private ImageView showpicproduct;

	    @FXML
	    private Button submitbuttion;

	    @FXML
	    private TextField tellTextfeild;

	    @FXML
	    private Label usernamelabel;

	    
	    private String username;
	    private Stage stage;
		private Scene scene;
		private Parent root;
		private Image image;

	    @FXML
	    void buybuttonclicked(MouseEvent e) {
	    	try {
	    		final Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	            final boolean wasMaximized = stage.isMaximized();
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Homescreen/homescreen.fxml"));
				root = loader.load();
				HomescreenController homescreenController = loader.getController();
				homescreenController.showandsetUsername(getUsername());			
				
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(true);
				
				stage.show();
				 Platform.runLater(() -> {
			            if(wasMaximized) {
			            	Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			                stage.setX(bounds.getMinX());
			                stage.setY(bounds.getMinY());
			                stage.setWidth(bounds.getWidth());
			                stage.setHeight(bounds.getHeight());
			                // ลองเรียก maximize อีกครั้ง
			                stage.setMaximized(true);
			            } else {
			                stage.centerOnScreen();
			            }
			        });
				 } catch (Exception e2) {
				e2.printStackTrace();
			}
	    }	
	    
	    public void selectImagebutton(ActionEvent e) {
	    	System.out.println("Selecting image");
	    	// สร้าง FileChooser
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("เลือกไฟล์รูปภาพ");
	        
	        // กำหนดให้เลือกเฉพาะไฟล์ภาพ
	        fileChooser.getExtensionFilters().add(
	            new FileChooser.ExtensionFilter("รูปภาพ (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg")
	        );

	        // แสดง FileChooser และรับไฟล์ที่เลือก
	        File file = fileChooser.showOpenDialog(new Stage());
	        if (file != null) {
	            image = new Image(file.toURI().toString()); // โหลดภาพ
	            showpicproduct.setImage(image); // แสดงภาพ
	        }
	    }
	    
	    
	    public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@FXML
	    void logouticongetClicked(ActionEvent e) {
	    	System.out.println("HEllo world");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/LoginScene.fxml"));
				root = loader.load();
				stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.centerOnScreen();
				stage.show();
				
				
				

			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }

	    @FXML
	    void sellbuttonclicked(ActionEvent e) {
	    	System.out.println("sell");
	    }
	
	    public void setandisplay_username(String username) {
	    	this.username=username;
	    	usernamelabel.setText(this.username);
		
	    }

	    public void handlesubmitbutton(){
	    	Alertmeassage alertmeassage = new Alertmeassage();
	    	String productname = productnameTextFeild.getText();
	    	
	    	//inspect amuntTextfeild
	    	String input = amoutTextfeild.getText().trim();
	        if (input.isEmpty()) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            amoutTextfeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return;
	        }else {
	        	amoutTextfeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        //inspect priceTextfeild
	        input = priceTextfeild.getText().trim();
	        System.out.println(input);
	        if ( input.isEmpty() || input.equals("0") ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            priceTextfeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return;
	        }else {
				priceTextfeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	    	Double price = Double.parseDouble(priceTextfeild.getText());
	    	
	    	String category = menucategorybutton.getText();
	    	if ( category.isEmpty()  ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            menucategorybutton.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return;
	        }else {
	        	menucategorybutton.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	    	
	    	int amount = Integer.parseInt(amoutTextfeild.getText());
	    	String fbid = fbidTextfeild.getText();
	    	String tell = tellTextfeild.getText();
	    	String description= descripTextarea.getText();
	    	Date date = new Date();
	    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());//ใช้ตัวนี้
	    	Product testProduct=new Product(productname,getUsername(), amount, price, sqlDate, 
	    			description, category, tell, fbid,image);
	    	System.out.println(testProduct.toString());
	    	
	    	
	    	if(image == null) {
	    		alertmeassage.errorMessage("please insert image");
	    	}else if (productname.isEmpty()|| tell.isEmpty()|| fbid.isEmpty() || productname.isEmpty() ) {
				alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
			}else {
				System.out.println("Inesert product to database");
				ConnecttionDVBproducts.InssertProductTODB(testProduct);
				System.out.println("imback");
			}
	    	
	    }


	    
	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			priceTextfeild.textProperty().addListener((observable, oldValue, newValue) -> {
			    if (!newValue.matches("\\d*(\\.\\d*)?")) {
			        priceTextfeild.setText(oldValue);
			    }
			});		
			
			amoutTextfeild.textProperty().addListener((observable, oldValue, newValue) -> {
			    if (!newValue.matches("\\d*(\\.\\d*)?")) {
			        amoutTextfeild.setText(oldValue);
			    }
			});	
			
			setMenuinitialize();
			
		}


		public void setMenuinitialize() {
			
			menucategorybutton.getItems().setAll(new MenuItem("ผลไม้"),new MenuItem("เครื่องใช้ไฟฟ้า"));
			for (MenuItem item: menucategorybutton.getItems()) {
				 item.setOnAction(e -> menucategorybutton.setText(item.getText()));
			        
			}
		}


}




