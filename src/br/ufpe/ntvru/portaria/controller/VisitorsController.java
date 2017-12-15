
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	    private Label labelVisitorId;

	    @FXML
	    private Label labelVisitorName;

	    @FXML
	    private Label labelVisitorCPF;

	    @FXML
	    private Label labelVisitorPhone;

	    @FXML
	    private Label labelVIsitorAccountable;

	    @FXML
	    private Label labelVisitorAdditionalInfo;

	    @FXML
	    private Label vehicleRegistrationPlate;
	    

	    @FXML
	    private TableColumn<Visitor, String> tableColumnVisitorName;

	    @FXML
	    private TableColumn<Visitor, String> tableColumnVisitorPhone;
	    
	    private Stage stage;
	    private Parent root;
	    
	    private Visitor visitor;
	    
	    private Vehicle vehicle;
	    
	    private ServiceStrategy<Visitor> visitorsService= new ServiceStrategyImpl<Visitor>(new VisitorDAO());
	    
	    private ServiceStrategy<Vehicle> vehicleStrategy;

	    //JDBC Postgres Configuration
//	    private final Database database = DatabaseFactory.getDatabase("postgresql");
//	    private final Connection connection = database.connect();
//	    private final VisitorJDBCDAO visitorDAO = new VisitorJDBCDAO();
//	   
	    
	    /**
	     * Initializes the controller class.
	     * @param url
	     * @param rb
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	//visitorService.setConnection(connection);
	    	tableViewVisitors.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> selectItemTableViewVisitors(newValue));
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
	    
	    
	    private ImageView createImageView(ReadOnlyDoubleProperty widthProperty) {
	    	
	    	ImageView imageView = new ImageView();
	    	
	    	imageView.setPreserveRatio(true);
	    	
	    	imageView.fitWidthProperty().bind(widthProperty);
	    	return imageView;
	    }
	    
	    
	  public WritableImage takePicture() {
		  
		  Webcam webcam = Webcam.getDefault();
		  
		  
		  Dimension[] nonStandardResolutions = new Dimension[] {
					WebcamResolution.PAL.getSize(),
					WebcamResolution.HD720.getSize(),
					new Dimension(2000, 1000),
					new Dimension(1000, 500),
				};
		  
		    webcam.setCustomViewSizes(nonStandardResolutions);
			WebcamUtils.capture(webcam, fieldVisitorsName.getText().replaceAll("\\s+","").trim(), ImageUtils.FORMAT_PNG);

			 BufferedImage image = webcam.getImage();
		  return SwingFXUtils.toFXImage(image, null);
	  } 
	  
	  public void preparePicture() {
		  System.out.println("CAMERA READY");
		  new WebcamViewerExample().run();
		  
	  }
	  
	  public void drawWebCamView() {
		  
		  System.out.println("DRAWING WEB CAM");
//		  AnchorPane anchorPane = new AnchorPane();
//		  anchorPane.setPrefHeight(459.0);
//		  anchorPane.setPrefWidth(529.0);
//		  
//		  
//		  JFXTabPane tabPane = new JFXTabPane();
//		  tabPane.setPrefHeight(51.0);
//		  tabPane.setPrefWidth(529.0);
//		  
//		  
//		  Tab tab = new Tab();
//		  tab.setText("Capturar Foto");
//		  
//		  tabPane.getTabs().add(tab);
//		  
//		  anchorPane.getChildren().add(tabPane);
//		  
		  Stage stage = new Stage(); 
		  
		  try {
			  root =FXMLLoader.load(getClass().getResource(Routes.CAMERAVIEW));
			  
			   stage.setScene(new Scene(root));
			   stage.setTitle("Tela de Captura.");
			   stage.initModality(Modality.APPLICATION_MODAL);
			  // System.out.println("SCENE "+visitorsPicture.getScene().getWindow());
			   //stage.initOwner(visitorsPicture.getScene().getWindow());
			   
			   stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
	  }
	  
	  
	  
	public void saveVisitor() {
		visitor = new Visitor();
		//visitorsStrategy = new ServiceStrategyImpl<Visitor>(new VisitorDAO());
		visitor.setName(this.fieldVisitorsName.getText());
		visitor.setCpf(this.fieldVisitorsCPF.getText());
		visitor.setAccountable(this.fieldVisitorsAccountable.getText());
		visitor.setAdditionalInfo(this.fieldVisitorsInfo.getText());
		visitor.setProduct(this.fieldVisitorsProduct.getText());
		visitorsService.add(visitor);
		//visitor.set
		
	}  
	
	
	public void searchVisitor() {
	
		};
		
	    public void selectItemTableViewVisitors(Visitor visitor){
	        if (visitor != null) {
	            labelVisitorId.setText(String.valueOf(visitor.getId()));
	            labelVisitorName.setText(visitor.getName());
	            labelVisitorCPF.setText(visitor.getCpf());
	            labelVisitorPhone.setText(visitor.getPhone());
	        } else {
	            labelVisitorId.setText("");
	            labelVisitorName.setText("");
	            labelVisitorCPF.setText("");
	            labelVisitorPhone.setText("");
	        }

	    }

@FXML
public void searchVisitorOnEnter(ActionEvent e){
         
	System.out.println("SEARCHING VISITOR...");
	List<Visitor> visitorsList = visitorsService.getAll();
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
	
	//List<Visitor> visitorsList = visitorsService.getAll();
	List<Visitor> visitorsList = visitorsService.getAll();
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
     boolean buttonConfirmClicked = showFXMLAnchorPaneRegisterVisitorForm(visitor);
     if (buttonConfirmClicked) {
    	// visitorsService.add(visitor);
    	 visitorsService.add(visitor);
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
    Visitor visitor = tableViewVisitors.getSelectionModel().getSelectedItem();
    if (visitor!= null) {
        visitorsService.drop(visitor);
        loadTableViewVisitor();
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Por favor, escolha um visitante na Tabela!");
        alert.show();
    }
}

@FXML
public void handleButtonUpdate() throws IOException {
    Visitor visitor= tableViewVisitors.getSelectionModel().getSelectedItem();
    if (visitor!= null) {
        boolean buttonConfirmarClicked = showFXMLAnchorPaneRegisterVisitorForm(visitor);
        if (buttonConfirmarClicked) {
            visitorsService.add(visitor);
            loadTableViewVisitor();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Por favor, escolha um visitante na Tabela!");
        alert.show();
    }
}
}    
	    


