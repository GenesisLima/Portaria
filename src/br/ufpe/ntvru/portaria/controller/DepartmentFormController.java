package br.ufpe.ntvru.portaria.controller;


import java.net.URL;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.model.Department;
import br.ufpe.ntvru.portaria.model.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepartmentFormController implements Initializable{

  
	  @FXML
	    private Label labelDeparmentName;

	    @FXML
	    private Label labelDepartmentAdditionalInfo;

	    @FXML
	    private TextArea textAreaDepartmentName;

	    @FXML
	    private TextField textFieldDepartmentAdditionalInfo;
	
	@FXML
    private Button buttonConfirm;

    @FXML
    private Button buttonCancelar;
    
    private Stage stage;

    
    
    
    private Department department;

    private Stage dialogStage;
    
    private boolean buttonConfirmClicked = false;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	
	
	 @FXML
	    public void handleButtonCancel() {
	        dialogStage.close();
	    }

	 @FXML
	    public void handleButtonConfirm() {
          System.out.println(department.getName()+" NAMED ==>"+textAreaDepartmentName.getText()+" FOR ==>"+department.toString());
	        System.out.println("FIELD "+textAreaDepartmentName.getText());

	        if (validate()) {
              
	        	department.setName(textAreaDepartmentName.getText().trim());
	        	//department.setPhone(textFieldWorkerPhone.getText().trim());
	            //worker.setDepartment(department);(textFieldWorkerName.getText().trim());
	        	department.setDescription(textFieldDepartmentAdditionalInfo.getText().trim());	            

	            buttonConfirmClicked = true;
	            dialogStage.close();
	       }

	    }
	 
	    private boolean validate() {
	        String errorMessage = "";

	        if (textAreaDepartmentName.getText() == null || textAreaDepartmentName.getText().trim().length() == 0) {
	            errorMessage += "Erro! Campo nome vazio!\n";
	        }
//	        if (textFieldVisitorCpf.getText() == null || textFieldVisitorCpf.getText().trim().length() == 0) {
//	            errorMessage += "CPF inválido!\n";
//	        }
//	        if (textFieldVisitorPhone.getText() == null || textFieldVisitorPhone.getText().trim().length() == 0) {
//	            errorMessage += "Telefone inválido!\n";
//	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	          
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Erro ao cadastrar Departamento!");
	            alert.setHeaderText("Campos inválidos, por favor, corrija...");
	            alert.setContentText(errorMessage);
	            alert.show();
	            return false;
	        }
	    }

	
	    
		public Department getDepartment() {
			return department;
		}

		public void setDepartment(Department department) {
			this.department = department;
		}

		public boolean isButtonConfirmClicked() {
			return buttonConfirmClicked;
		}
		
		
		public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
			this.buttonConfirmClicked = buttonConfirmarClicked;
		}

		public Stage getStage() {
			return stage;
		}

		public void setStage(Stage stage) {
			this.stage = stage;
		}

		
		


		
}

