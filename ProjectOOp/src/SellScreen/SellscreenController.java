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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import ClassHelper.Alertmeassage;
import ClassHelper.ConnecttionDVBproducts;
import Homescreen.HomescreenController;
import Homescreen.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

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
	    public void buybuttonclicked(MouseEvent e) {
	    	navigateToHome(e);
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
	    
	    private void navigateToHome(Event e) {
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
	    public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@FXML
	   public void logouticongetClicked(ActionEvent e) {
	    	System.out.println("HEllo world");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/LoginScene.fxml"));
				root = loader.load();
				stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				Stage stage2 = new Stage();
				stage.close();

				stage2.setScene(scene);
				stage2.setMaximized(false);
				stage2.setResizable(false);
				stage2.centerOnScreen();
				stage2.show();


			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }

	    @FXML
	    public void sellbuttonclicked(ActionEvent e) {
	    	System.out.println("sell");
	    }
	
	    public void setandisplay_username(String username) {
	    	this.username=username;
	    	usernamelabel.setText(this.username);
		
	    }

	    public void handlesubmitbutton(ActionEvent e){
	    	if(check_information()) {
	    		System.out.println("please enter all information at sell screen");
	    		return;
	    	}

	    	Alertmeassage alertmeassage = new Alertmeassage();
	    	String productname = productnameTextFeild.getText();	    	
	    	Double price = Double.parseDouble(priceTextfeild.getText());
	    	String category = menucategorybutton.getText();
	    	int amount = Integer.parseInt(amoutTextfeild.getText());
	    	String fbid = fbidTextfeild.getText();
	    	String tell = tellTextfeild.getText();
	    	String description= descripTextarea.getText();
	    	Date date = new Date();
	    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());//ใช้ตัวนี้
	    	Product testProduct=new Product(productname,getUsername(), amount, price, sqlDate, 
	    			description, category, tell, fbid,image);
	    	System.out.println(testProduct.toString());
	    	
	    	
	    	//insert data to database
			System.out.println("Inesert product to database");
			ConnecttionDVBproducts.InssertProductTODB(testProduct);
			System.out.println("imback");
			submitbuttion.setDisable(true);
			alertmeassage.succesMessage("ลงขายสำเร็จ");
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


	    public boolean check_information() {
	    	Alertmeassage alertmeassage = new Alertmeassage();
	    	//inspect amuntTextfeild
	    	String input = amoutTextfeild.getText().trim();
	        if (input.isEmpty()) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            amoutTextfeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
	        	amoutTextfeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        //inspect priceTextfeild
	        input = priceTextfeild.getText().trim();
	        System.out.println(input);
	        if ( input.isEmpty() || input.equals("0") ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            priceTextfeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
				priceTextfeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        
	        if ( menucategorybutton.getText().isEmpty() ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            menucategorybutton.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
	        	menucategorybutton.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        
	        if ( productnameTextFeild.getText().isEmpty() ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            productnameTextFeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
	        	productnameTextFeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        
	        
	        if ( tellTextfeild.getText().isEmpty() ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            tellTextfeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
	        	tellTextfeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        if ( fbidTextfeild.getText().isEmpty() ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            fbidTextfeild.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
	        	fbidTextfeild.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        
	        if ( descripTextarea.getText().isEmpty() ) {
	            alertmeassage.errorMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
	            descripTextarea.setStyle("-fx-border-color: red;"); // เปลี่ยนขอบ TextField เป็นสีแดง
	            return true;
	        }else {
	        	descripTextarea.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
			}
	        
	        
	        
	        if(image == null) {
	    		alertmeassage.errorMessage("กรุณาใส่รูปภาพสินค้า");
	    		selectpicturebutton.setStyle("-fx-border-color: red;");
	    		return true;
	    	}else {
	    		menucategorybutton.setStyle("-fx-border-color: lightgray; ");
	    	}
	      
	        return false;
			
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
			
			
			tellTextfeild.textProperty().addListener((observable, oldValue, newValue) -> {
			    if (!newValue.matches("\\d*(\\.\\d*)?")) {
			        tellTextfeild.setText(oldValue);
			    }
			});	
			setMenuinitialize();
			
		}


		public void setMenuinitialize() {
			ObservableList<MenuItem> listofmenubutton = FXCollections.observableArrayList();
			List<String> listcategory = Arrays.asList("ผัก/ผลไม้","ขนมปัง","อาหาร","เครื่องใช้ไฟฟ้า","เครื่องดื่ม","เนื้อสัตว์/อาหารทะเล","ของใช้ภายในบ้าน","อื่นๆ");
			
			listcategory.forEach((e)->{
				listofmenubutton.add(new MenuItem(e));
			});

			menucategorybutton.getItems().setAll(listofmenubutton);
			for (MenuItem item: menucategorybutton.getItems()) {
				 item.setOnAction(e -> menucategorybutton.setText(item.getText()));
			        
			}
		}
		
		
		public void homebuttonHandle(ActionEvent e) {
			navigateToHome(e);
		}

}




