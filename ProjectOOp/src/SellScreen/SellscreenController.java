package SellScreen;


import Homescreen.HomescreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SellscreenController {
	
	@FXML
    private TextField amoutTextfeild;

    @FXML
    private Button buybutton;

    @FXML
    private TextArea descripTextarea;

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
    private Label usernamelabel;

	    
	    private String username;
	    private Stage stage;
		private Scene scene;
		private Parent root;

	    @FXML
	    void buybuttonclicked(MouseEvent e) {
	    	try {
	    		final Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	            final boolean wasMaximized = stage.isMaximized();
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Homescreen/homescreen.fxml"));
				root = loader.load();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(true);
				stage.setMaximized(true);
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
}