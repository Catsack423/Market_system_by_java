package Homescreen;




import java.net.URL;
import java.util.ResourceBundle;

import Homescreen.Showproductpane.ShowproductController;
import SellScreen.SellscreenController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomescreenController implements Initializable {

	@FXML
    private Button buybutton;

    @FXML
    private GridPane gridproduct;

    @FXML
    private ImageView loginimageview;

    @FXML
    private ImageView logoutImage;

    @FXML
    private Button logoutbutton;

    @FXML
    private Label selllabel;

    @FXML
    private Label usernamelabel;
    
   
    
    
    
    private String username= "Username";
    private Stage stage;
	private Scene scene;
	private Parent root;
	ObservableList<Product> products = FXCollections.observableArrayList();
	ObservableList<Product> show_product = FXCollections.observableArrayList();

    
    


	
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		products = ProductDB.getProducts();
		
		if(products==null || products.isEmpty()) {
			System.out.println("Product list in initialize is null");
			return ;
		}
		for (Product product : products) {
			System.out.println(product.toString());
		}
		
		int row=0,col=3;
		try {	
		for (int i = 0; i < products.size(); i++) {
			
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Homescreen/Showproductpane/Showproductpane.fxml"));
				AnchorPane anchorPane = loader.load();
				
				ShowproductController showproductController = loader.getController();
				showproductController.setanddisplayproduct(products.get(i));

				if(col==3) {
					col=0;
					row++;
				}
				gridproduct.add(anchorPane, col++, row);
				GridPane.setMargin(anchorPane, new Insets(10));				
				}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		
	}
	
	
	public void showandsetUsername(String username) {
		this.username=username;
		usernamelabel.setText(this.username);
		
	}
	
	
	public void buybuttonclicked(ActionEvent e) {
		System.out.println("buy button got clicked");
		
	}
	
	public void sellbuttonclicked(MouseEvent e) {
		try {
			final Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            final boolean wasMaximized = stage.isMaximized();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/SellScreen/Sellscreen.fxml"));
			root = loader.load();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(true);

			stage.centerOnScreen();

			SellscreenController sellscreenController = loader.getController();
			sellscreenController.setandisplay_username(getUsername());
			
			
			

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
			
			
			
			stage.show();
            
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	

	public  void addtogridpane(ObservableList<Product> p) {
		
	}
	
	
}
