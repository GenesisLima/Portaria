package br.ufpe.ntvru.portaria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.model.Department;
import br.ufpe.ntvru.portaria.model.Visitor;
import br.ufpe.ntvru.portaria.model.Department;
import br.ufpe.ntvru.portaria.repository.DepartmentDAO;
import br.ufpe.ntvru.portaria.repository.DepartmentDAO;
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

public class DepartmentController implements Initializable{

	
	 @FXML
	    private TableView<Department> tableViewDepartments;

	    @FXML
	    private TableColumn<Department, String> tableColumnDepartmentName;

	    @FXML
	    private TableColumn<Department, String> tableColumnDepartmentAdditionalInfo;

	    @FXML
	    private Label labelDepartmentId;

	    @FXML
	    private Label labelDepartmentName;

	    @FXML
	    private Label labelDepartmentAdditionalInfo;

	    @FXML
	    private Button buttonInsert;

	    @FXML
	    private Button buttonUpdate;

	    @FXML
	    private Button buttonRemove;
	    
	    
	    private ServiceStrategy<Department> departmentsService= new ServiceStrategyImpl<Department>(new DepartmentDAO());

	
	    @FXML
	    public void searchVisitorOnEnter(ActionEvent e){
	             
	    	List<Department> DepartmentsList = departmentsService.getAll();
	    	ObservableList<Department> Departments = FXCollections.observableArrayList(DepartmentsList);    	
	    	tableColumnDepartmentName.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	tableColumnDepartmentAdditionalInfo.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	tableViewDepartments.setItems(Departments);
	    	 
	    	
	    	 
	    }



	    @FXML
	    public void handleButtonInsert() throws IOException {
	    	 Department Department= new Department();
	         boolean buttonConfirmClicked = showFXMLAnchorPaneRegisterDepartmentForm(Department);
	         if (buttonConfirmClicked) {
	        	 departmentsService.add(Department);
	            loadTableViewDepartment();
	         }
	    }


	    public boolean showFXMLAnchorPaneRegisterDepartmentForm(Department Department) throws IOException {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(DepartmentController.class.getResource("/br/ufpe/ntvru/portaria/view/department/DepartmentForm.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Cadastro de Departamentos");
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        DepartmentFormController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setDepartment(Department);

	        
	        dialogStage.showAndWait();

	        return controller.isButtonConfirmClicked();

	    }
	    	    


	    @FXML
	    public void handleButtonRemove() {
	        Department Department= tableViewDepartments.getSelectionModel().getSelectedItem();
	       
	        if (Department!= null) {
	        	int id = Department.getId();
	        	departmentsService.drop(id);
	            loadTableViewDepartment();
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
	            alert.show();
	        }
	    }

	    @FXML
	    public void handleButtonUpdate() throws IOException {
	        Department Department= tableViewDepartments.getSelectionModel().getSelectedItem();
	        if (Department!= null) {
	            boolean buttonConfirmarClicked = showFXMLAnchorPaneRegisterDepartmentForm(Department);
	            if (buttonConfirmarClicked) {
	            	departmentsService.add(Department);
	                loadTableViewDepartment();
	            }
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
	            alert.show();
	        }
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			tableViewDepartments.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> selectItemTableViewDepartments(newValue));
			loadTableViewDepartment();
			
		}
		
	    public void selectItemTableViewDepartments(Department department){
	        if (department != null) {
	            labelDepartmentId.setText(String.valueOf(department.getId()));
	            labelDepartmentName.setText(department.getName());
	          //  labelDepartmentDepartment.setText(Department.getDepartment().getName());
	            labelDepartmentAdditionalInfo.setText(department.getDescription());
	        } else {       	
	            labelDepartmentName.setText("");
	          //  labelDepartmentDepartment.setText(Department.getDepartment().getName());
	            labelDepartmentAdditionalInfo.setText("");
	            
	        }

	    }
	    
	    
	    public void loadTableViewDepartment() {
	    	
	    	List<Department> departmentsList = departmentsService.getAll();
	    	//DepartmentsList.stream().map(w -> w.getPhone()).forEach(System.out::println);    	
	    	ObservableList<Department> departments = FXCollections.observableArrayList(departmentsList);
	    	tableColumnDepartmentName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
	    	tableColumnDepartmentAdditionalInfo.setCellValueFactory(new PropertyValueFactory<Department, String>("description"));  
	    	
	    	
	    	tableViewDepartments.setItems(departments);
	       
	    }
	    

}
