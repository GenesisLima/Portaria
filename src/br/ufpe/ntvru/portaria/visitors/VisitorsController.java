package br.ufpe.ntvru.portaria.visitors;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
	    private ImageView visitorsPicture;

	    @FXML
	    private JFXTextField visitorsName;
	    
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
	    private JFXTextField fieldVehiclePlate;

	    @FXML
	    private TableColumn<Visitor, String> nameColumn;

	    @FXML
	    private TableColumn<String, String> accountableColumn;

	    @FXML
	    private TableColumn<String, String> branchLinaColumn;

	    @FXML
	    private TableColumn<Vehicle, String> vehicleColumn;
	    
	    
	    
	    private Stage stage;
	    private Parent root;
	    
	    private Visitor visitor;
	    
	    private Vehicle vehicle;
	    
	    private ServiceStrategy<Visitor> visitorsStrategy;
	    
	    private ServiceStrategy<Vehicle> vehicleStrategy;

	    
	    /**
	     * Initializes the controller class.
	     * @param url
	     * @param rb
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        // Populate Combobox with static options,
	    	comboVehicleBrand.getItems().addAll("Pychologist","Psychiatric","Gynaecologist","Pathologist","Cardiologist","Orginologist","unspecified");
	    	comboVehicleBrand.getSelectionModel().selectLast();
	        
	    	comboVehicleModel.getItems().addAll("Jornalismo","Produção","Rádio","Compras","Departamento Técnico");
	    	comboVehicleModel.getSelectionModel().selectLast();
	        
//	        visitorsPicture = new ImageView(); //createImageView(visitorsPicture.getParent().getBoundsInParent().getWidth());
//	        visitorsPicture.setPreserveRatio(true);
//	       
//	        visitorsPicture.setImage(takePicture());	
	        		
	        
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
			WebcamUtils.capture(webcam, visitorsName.getText().replaceAll("\\s+","").trim(), ImageUtils.FORMAT_PNG);

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
		visitorsStrategy = new ServiceStrategyImpl<Visitor>(new VisitorDAO());
		visitor.setName(this.fieldVisitorsName.getText());
		visitor.setCpf(this.fieldVisitorsCPF.getText());
		visitor.setAccountable(this.fieldVisitorsAccountable.getText());
		visitor.setAdditionalInfo(this.fieldVisitorsInfo.getText());
		visitor.setProduct(this.fieldVisitorsProduct.getText());
		visitorsStrategy.add(visitor);
		//visitor.set
		
	}  
	    
	    
	    
}
