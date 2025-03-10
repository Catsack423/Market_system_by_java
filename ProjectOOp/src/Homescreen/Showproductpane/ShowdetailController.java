package Homescreen.Showproductpane;

import java.net.URL;
import java.util.ResourceBundle;

import Homescreen.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ShowdetailController implements Initializable{
	

	@FXML
    private Label amountlabel;

    @FXML
    private Label categorylabel;

    @FXML
    private Button closebutton;

    @FXML
    private TextArea detailproducttextArea;

    @FXML
    private Label fbidlabel;

    @FXML
    private Label nameproductlable;

    @FXML
    private Label pricelabel;

    @FXML
    private ImageView productImageview;

    @FXML
    private Label telllabel;
    
    private Product product;
    
    public void handleclosebutton(ActionEvent e) {
    	Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	stage.close();
	}

    
    public void setanddiplaydetailproduct(Product p) {
    	if(p==null) {
    		System.out.println("Error at setproduct in showdetailControler");
    		return ;
    	}
		this.product=p;
		productImageview.setImage(p.getImage());
		telllabel.setText("เบอร์โทร: " +p.getTell());
		fbidlabel.setText("Facebook: "+p.getFbid());
		pricelabel.setText("ราคา: "+ p.getPrice() +" บาท" );
		nameproductlable.setText("ชื่อสินค้า: "+p.getNameString());
		detailproducttextArea.setText(p.getDescription());
		amountlabel.setText("จำนวนสินค้า: "+p.getAmount());
		categorylabel.setText("ประเภท: "+p.getCategory());
	
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		closebutton.requestFocus();
		detailproducttextArea.setDisable(true);
		detailproducttextArea.setStyle("-fx-border-color: transparent; -fx-background-color: #f0f0f0; -fx-opacity: 1.0;");
	}

    
    
    
    
}
