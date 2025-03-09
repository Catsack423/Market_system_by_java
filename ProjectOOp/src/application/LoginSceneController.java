package application;

import java.net.URL;
import java.security.AccessControlContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import ClassHelper.Alertmeassage;
import ClassHelper.ConnecttionDVBproducts;
import Homescreen.HomescreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginSceneController implements Initializable{
    @FXML
    private AnchorPane AnchorLogin;

    @FXML
    private Button CreateAccountButton;

    @FXML
    private PasswordField PassTextFiled;

    @FXML
    private TextField UsernameTextFiled;

    @FXML
    private Button loginButton;
    
    @FXML
    private Label ErorLabel;
    
    
    private PreparedStatement prepar;
    private ResultSet result;
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    
    

    
    
   
    
    
    public void login(ActionEvent e) {
    	Alertmeassage alertmeassage = new Alertmeassage();
    	String username = UsernameTextFiled.getText();
    	String password = PassTextFiled.getText();
    	
    	if(username.isEmpty() && password.isEmpty()) {
    		alertmeassage.errorMessage("Please Enter username and password");
    		
    	}else if(username.isEmpty() || password.isEmpty()) {
    		alertmeassage.errorMessage("username or password cannot be emthy");
    		
    	}else {
    		String selectData = "SELECT username,password FROM useraccount WHERE "+"username = ? and password = ?";
    		Connection connect = ConnecttionDVBproducts.conect_productDB();
    		try {
    			//เชื่อมdatabaseเพื่อหาusername
    			statement = connect.createStatement();
        		prepar=connect.prepareStatement(selectData);
        		prepar.setString(1, username);
        		prepar.setString(2, password);
        		result = prepar.executeQuery();  
        		if(result.next()) {
        			//แสดงว่ามีข้อมูล
     
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Homescreen/homescreen.fxml"));
        			System.out.println("Founded");
        			alertmeassage.succesMessage("Welcome");
        			try {
        	    	    root = loader.load();
        	    	    HomescreenController home = loader.getController();
            			home.showandsetUsername(UsernameTextFiled.getText());
            			System.out.println(username);
        	    	    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        	    	    scene = new Scene(root);
        	    	    stage.setResizable(true);
        	    	    stage.setScene(scene);
        	    	    stage.centerOnScreen();
        	    	    
        	    	    stage.show();
        			} catch (Exception e2) {
        				e2.printStackTrace();
        			}

        			///
        		}else {
        			alertmeassage.errorMessage("Please enter correct username and password");
        		}
        		
        	
			} catch (Exception e3) {
					e3.printStackTrace();
			}
    		
    	}
    	
    }
    
    
    
  
    public void creataccountclicked(ActionEvent e) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SinginScene/SinginScene.fxml"));
    	    root = loader.load();
    	    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	    scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.setResizable(false);
    	    stage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
    	
    }
    
    
  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UsernameTextFiled.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				login(new ActionEvent());
			}
		});

		PassTextFiled.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				login(new ActionEvent());
			}
		});		
	}
    
    
    
    
}