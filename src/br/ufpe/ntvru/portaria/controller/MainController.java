package br.ufpe.ntvru.portaria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.helpers.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable{

	
	 @FXML
	    private MenuItem menuItemCadastrosClientes;

	    @FXML
	    private MenuItem menuItemProcessosVendas;

	    @FXML
	    private MenuItem menuItemGraficosVendasPorMes;

	    @FXML
	    private MenuItem menuItemRelatoriosQuantidadeProdutosEstoque;

	    @FXML
	    private AnchorPane anchorPane;

	    	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    public void handleMenuItemRegisterVisitor() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource(Routes.VISITORSVIEW));
        anchorPane.getChildren().setAll(a);
    }

    

}
