package br.ufpe.ntvru.portaria.controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.ufpe.ntvru.portaria.helpers.Routes;
import br.ufpe.ntvru.portaria.model.Vehicle;
import br.ufpe.ntvru.portaria.model.Visitor;
import br.ufpe.ntvru.portaria.repository.VisitorDAO;
import br.ufpe.ntvru.portaria.service.ServiceStrategy;
import br.ufpe.ntvru.portaria.service.ServiceStrategyImpl;
import br.ufpe.ntvru.portaria.webcam.WebcamViewerExample;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VisitorsController implements Initializable {

	  @FXML
	    private JFXComboBox<String> comboSpecialization;
	    @FXML
	    private JFXComboBox<String> comboDepartment;
	    @FXML
	    private ToggleGroup q;
	    
	    @FXML
	    private JFXTextField fieldVisitorsName;

	    @FXML
	    private JFXTextField fieldVisitorsCPF;

	    @FXML
	    private JFXTextField fieldVisitorsProduct;

	    @FXML
	    private JFXTextField fieldVisitorsBranchLine;

	    @FXML
	    private JFXTextField fieldVisitorsAccountable;

	    @FXML
	    private JFXTextArea fieldVisitorsInfo;

	    

	    @FXML
	    private JFXComboBox<String> comboVehicleBrand;

	    @FXML
	    private JFXComboBox<String> comboVehicleModel;

	    @FXML
	    private TableView<Visitor> tableViewVisitors;
	   

	    @FXML
	    private JFXTextField fieldVehiclePlate;

	   
	    

	    @FXML
	    private TableColumn<Visitor, String> tableColumnVisitorName;

	    @FXML
	    private TableColumn<Visitor, String> tableColumnVisitorPhone;
	    
	    private Stage stage;
	   
	    
	    private Visitor visitor;
	    
	    private Vehicle vehicle;
	    
	    private ServiceStrategy<Visitor> visitorsStrategy = new ServiceStrategyImpl<Visitor>(new VisitorDAO());
	    
	    private ServiceStrategy<Vehicle> vehicleStrategy;

	
	   
	    
	    /**
	     * Initializes the controller class.
	     * @param url
	     * @param rb
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        // Populate Combobox with static options,
//	    	comboVehicleBrand.getItems().addAll("Pychologist","Psychiatric","Gynaecologist","Pathologist","Cardiologist","Orginologist","unspecified");
//	    	comboVehicleBrand.getSelectionModel().selectLast();
//	        
//	    	comboVehicleModel.getItems().addAll("Jornalismo","Produção","Rádio","Compras","Departamento Técnico");
//	    	comboVehicleModel.getSelectionModel().selectLast();
	        
//	        visitorsPicture = new ImageView(); //createImageView(visitorsPicture.getParent().getBoundsInParent().getWidth());
//	        visitorsPicture.setPreserveRatio(true);
//	       
//	        visitorsPicture.setImage(takePicture());	
	    	loadTableViewVisitor();
	        
	    }    
	    
	    

	    
	    


	  
	  
	  
	public void saveVisitor() {
		visitor = new Visitor();
		//visitorsStrategy = new ServiceStrategyImpl<Visitor>(new VisitorDAO());
		visitor.setName(this.fieldVisitorsName.getText());
		visitor.setCpf(this.fieldVisitorsCPF.getText());
		visitor.setAccountable(this.fieldVisitorsAccountable.getText());
		visitor.setAdditionalInfo(this.fieldVisitorsInfo.getText());
		visitor.setProduct(this.fieldVisitorsProduct.getText());
		visitorsStrategy.add(visitor);
		//visitor.set
		
	}  
	
	
	public void searchVisitor() {
	
		};
		
	

@FXML
public void searchVisitorOnEnter(ActionEvent e){
         
	System.out.println("SEARCHING VISITOR...");
	List<Visitor> visitorsList = visitorsStrategy.getAll();
	ObservableList<Visitor> visitors = FXCollections.observableArrayList(visitorsList);
	System.out.println("NAME"+visitorsList.size());
	System.out.println("NAME"+visitors.size());
	tableColumnVisitorName.setCellValueFactory(new PropertyValueFactory<>("name"));
	//visitorsAccountableColumn.setCellValueFactory(new PropertyValueFactory<>("accountable"));
	tableColumnVisitorPhone.setCellValueFactory(new PropertyValueFactory<>("branchLine"));
	//visitorsVehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
	tableViewVisitors.setItems(visitors);
	//visitorsTableView.getColumns().addAll(visitorsNameColumn,visitorsAccountableColumn, visitorsBranchLinaColumn, visitorsVehicleColumn);
	 
	
	 
}

public void loadTableViewVisitor() {
	
	List<Visitor> visitorsList = visitorsStrategy.getAll();
	ObservableList<Visitor> visitors = FXCollections.observableArrayList(visitorsList);
	tableColumnVisitorName.setCellValueFactory(new PropertyValueFactory<>("name"));
	tableColumnVisitorPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));  
	//visitorsCpfColumn.cellValueFactoryProperty().setValue(new PropertyValueFactory<>("cpf"));
	//visitorsAccountableColumn.setCellValueFactory(new PropertyValueFactory<>("accountable"));
	
	tableViewVisitors.setItems(visitors);
   
}

@FXML
public void handleButtonInsert() throws IOException {
	 Visitor visitor = new Visitor();
     boolean buttonConfirmarClicked = showFXMLAnchorPaneRegisterVisitorForm(visitor);
     if (buttonConfirmarClicked) {
    	 visitorsStrategy.add(visitor);
        loadTableViewVisitor();
     }
}


public boolean showFXMLAnchorPaneRegisterVisitorForm(Visitor visitor) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(VisitorsController.class.getResource("/br/ufpe/ntvru/portaria/view/VisitorForm.fxml"));
    AnchorPane page = (AnchorPane) loader.load();

    
    Stage dialogStage = new Stage();
    dialogStage.setTitle("Cadastro de Visitantes");
    Scene scene = new Scene(page);
    dialogStage.setScene(scene);

    
    VisitorsFormController controller = loader.getController();
    controller.setDialogStage(dialogStage);
    controller.setVisitor(visitor);

    
    dialogStage.showAndWait();

    return controller.isButtonConfirmClicked();

}
	    


@FXML
public void handleButtonRemove() {

}

@FXML
public void handleButtonUpdate() {

}
}    
	    

