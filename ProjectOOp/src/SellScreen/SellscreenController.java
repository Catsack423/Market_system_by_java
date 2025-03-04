package SellScreen;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SellscreenController {
	
	  	@FXML
	    private Button buybutton;

	    @FXML
	    private ImageView logoutImage;

	    @FXML
	    private Button logoutbutton;

	    @FXML
	    private Label selllabel;

	    @FXML
	    private Label usernamelabel;
	    
	    private String username;
	    private Stage stage;
		private Scene scene;
		private Parent root;

	    @FXML
	    void buybuttonclicked(MouseEvent e) {
	    	try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Homescreen/homescreen.fxml"));
				root = loader.load();
				stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(true);
				stage.centerOnScreen();
				stage.show();
				
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