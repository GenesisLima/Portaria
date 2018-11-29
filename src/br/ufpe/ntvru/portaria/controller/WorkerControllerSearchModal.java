package br.ufpe.ntvru.portaria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.helpers.Routes;
import br.ufpe.ntvru.portaria.model.Worker;
import br.ufpe.ntvru.portaria.repository.WorkerDAO;
import br.ufpe.ntvru.portaria.service.ServiceStrategy;
import br.ufpe.ntvru.portaria.service.ServiceStrategyImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class WorkerControllerSearchModal implements Initializable {

	
	
	  @FXML
	    private TextField textFieldSearchWorker;

	    @FXML
	    private Button buttonSearchWorker;

	    @FXML
	    private TableView<Worker> tableViewWorkersName;
	    
	    @FXML
	    private TableColumn<Worker, String> tableColumnWorkerName;
	    
	    
		private ServiceStrategy service ;
		

	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//tableViewWorkersName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.selectWorker(newValue));
		tableViewWorkersName.setOnMouseClicked(event -> {
			if(event.getClickCount() == 2) {
				
			    try {
			    	FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.VISITORSFORM));
					Parent modalDialogRoot = loader.load();
					 VisitorsFormController controller = loader.getController();
					 System.out.println("WORKER ON SEARCH "+ tableViewWorkersName.getSelectionModel().getSelectedItem());
					 controller.setWorker(tableViewWorkersName.getSelectionModel().getSelectedItem());
					 ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			}
		});
	}
	
	
	 @FXML
	    void searchWorkerByName(ActionEvent event) {
		// System.out.println("EVENT "+((Stage)((Node)event.getSource()).getScene().getWindow()).getTitle());

		service = new ServiceStrategyImpl<Worker>(new WorkerDAO());
		ObservableList<Worker> workersList = FXCollections.observableArrayList(service.getByNameLike(textFieldSearchWorker.getText()));
		tableColumnWorkerName.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
		tableViewWorkersName.setItems(workersList);
	    }
	 
	 
	 


	

	 
	 

}
