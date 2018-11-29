package br.ufpe.ntvru.portaria.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.model.Worker;
import br.ufpe.ntvru.portaria.repository.WorkerDAO;
import br.ufpe.ntvru.portaria.service.ServiceStrategy;
import br.ufpe.ntvru.portaria.service.ServiceStrategyImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkersController implements Initializable{

    @FXML
    private TableView<Worker> tableViewWorkers;

    @FXML
    private TableColumn<Worker, String> tableColumnWorkerName;

    @FXML
    private TableColumn<Worker, String> tableColumnWorkerPhone;

    @FXML
    private Label labelWorkerId;

    @FXML
    private Label labelWorkerName;

    @FXML
    private Label labelWorkerDepartment;

    @FXML
    private Label labelWorkerPhone;

    @FXML
    private Label vehicleRegistrationPlate;

    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonRemove;
    
    
    private ServiceStrategy<Worker> workersService= new ServiceStrategyImpl<Worker>(new WorkerDAO());

    
    

   



    @FXML
    public void handleButtonInsert() throws IOException {
    	 Worker worker= new Worker();
         boolean buttonConfirmClicked = showFXMLAnchorPaneRegisterWorkerForm(worker);
         if (buttonConfirmClicked) {
        	 workersService.add(worker);
            loadTableViewWorker();
         }
    }


    public boolean showFXMLAnchorPaneRegisterWorkerForm(Worker worker) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VisitorsController.class.getResource("/br/ufpe/ntvru/portaria/view/worker/WorkerForm.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        
        WorkersFormController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setWorker(worker);

        
        dialogStage.showAndWait();

        return controller.isButtonConfirmClicked();

    }
    	    


    @FXML
    public void handleButtonRemove() {
        Worker worker= tableViewWorkers.getSelectionModel().getSelectedItem();
       
        if (worker!= null) {
        	int id = worker.getId();
            workersService.drop(id);
            loadTableViewWorker();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonUpdate() throws IOException {
        Worker worker= tableViewWorkers.getSelectionModel().getSelectedItem();
        if (worker!= null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneRegisterWorkerForm(worker);
            if (buttonConfirmarClicked) {
                workersService.add(worker);
                loadTableViewWorker();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
            alert.show();
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableViewWorkers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableViewWorkers(newValue));
		loadTableViewWorker();
		
	}
	
    public void selectItemTableViewWorkers(Worker worker){
        if (worker != null) {
            labelWorkerId.setText(String.valueOf(worker.getId()));
            labelWorkerName.setText(worker.getName());
          //  labelWorkerDepartment.setText(worker.getDepartment().getName());
            labelWorkerPhone.setText(worker.getPhone());
        } else {       	
            labelWorkerName.setText("");
          //  labelWorkerDepartment.setText(worker.getDepartment().getName());
            labelWorkerPhone.setText("");
            
        }

    }
    
    
    public void loadTableViewWorker() {
    	
    	List<Worker> workersList = workersService.getAll();
    	//workersList.stream().map(w -> w.getPhone()).forEach(System.out::println);    	
    	ObservableList<Worker> workers = FXCollections.observableArrayList(workersList);
    	tableColumnWorkerName.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
    	tableColumnWorkerPhone.setCellValueFactory(new PropertyValueFactory<Worker, String>("phone"));
    
    	tableViewWorkers.setItems(workers);
       
    }
    
    
    
    
    
    
}

