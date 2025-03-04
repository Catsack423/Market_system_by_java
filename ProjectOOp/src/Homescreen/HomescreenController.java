package Homescreen;




import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HomescreenController implements Initializable {

    @FXML
    private Button buybutton;

    @FXML
    private GridPane gridproduct;

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

    
    


	
	
	public void logouticongetClicked(ActionEvent e) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		 
	}
	
	
	public void showandsetUsername(String username) {
		this.username=username;
		usernamelabel.setText(this.username);
		
	}
	
	
	public void buybuttonclicked(ActionEvent e) {
		System.out.println("buy");
		
	}
	
	public void sellbuttonclicked(MouseEvent e) {
		try {
            
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/SellScreen/Sellscreen.fxml"));
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
	
	
	public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMaximized(boolean isMaximized) {
        if (stage != null) {
            stage.setMaximized(isMaximized);
        }
    }
	
    
}
