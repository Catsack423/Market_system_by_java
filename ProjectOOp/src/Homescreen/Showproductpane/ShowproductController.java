package Homescreen.Showproductpane;

import java.net.URL;
import java.util.ResourceBundle;

import Homescreen.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowproductController implements Initializable{
	  	@FXML
	    private Text CategoryText;

	    @FXML
	    private Button Showdetailbutton;

	    @FXML
	    private Text UsernameText;

	    @FXML
	    private Text amountText;

	    @FXML
	    private Text priceText;

	    @FXML
	    private Label productnameText;

	    @FXML
	    private ImageView showpicimageview;
	    
	    
	    private Product product;
	    
	    
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	
		}
	    
	    
	    
	    public void setanddisplayproduct(Product p) {
			setProduct(p);
			CategoryText.setText("ประเภทสินค้า: "+product.getCategory());
			amountText.setText("จำนวนสินค้า: " + product.getAmount() );
			priceText.setText("ราคา: "+product.getPrice()+ " บาท");
			UsernameText.setText("ผู้ขาย: "+product.getUsername());
			productnameText.setText("ชื่อสินค้า: "+product.getNameString());
			showpicimageview.setImage(product.getImage());
			System.out.println("seting diplay for "+ product.getNameString() );
		}
	    
	    
	    
	    public void showdetailbuttonhandle(ActionEvent e) {
	    	
	    	try {
	    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Showdetailscene.fxml"));
		    	Parent root = fxmlLoader.load();
		    	ShowdetailController showdetailController = fxmlLoader.getController();
		    	showdetailController.setanddiplaydetailproduct(getProduct());
		    	
		    	
		    	Stage secondStage = new Stage();
		    	Scene scene = new Scene(root);
		    	secondStage.setScene(scene);
		    	secondStage.centerOnScreen();
		    	secondStage.setResizable(false);
		    	secondStage.setTitle("รายละเอียดสินค้า");
		    	secondStage.show();
		    	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    	
			
		}
	    
	    
	    
	    
	    
	    
	    public Product getProduct() {
			return product;
		}
	    
	    public void setProduct(Product product) {
			this.product = product;
		}

		
}
