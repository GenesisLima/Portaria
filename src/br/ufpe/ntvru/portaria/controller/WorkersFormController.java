package br.ufpe.ntvru.portaria.controller;


import java.net.URL;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.model.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WorkersFormController implements Initializable{

    @FXML
    private Label labelWorkerName;

    @FXML
    private Label labelWorkerDepartment;

    @FXML
    private Label labelWorkerPhone;

    @FXML
    private TextField textFieldWorkerDepartment;

    @FXML
    private TextField textFieldWorkerPhone;

    @FXML
    private Label labelWorkerAdditionalInfo;

    @FXML
    private TextArea textAreaWorkerAdditionalInfo;

    @FXML
    private TextField textFieldWorkerName;

    @FXML
    private Button buttonConfirm;

    @FXML
    private Button buttonCancelar;
    
    private Stage stage;

    
    
    private Worker worker;

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
          System.out.println(worker.getName()+" NAMED ==>"+textFieldWorkerName.getText()+" FOR ==>"+worker.toString());
	        System.out.println("FIELD "+textFieldWorkerName.getText());

	        if (validate()) {
              
	            worker.setName(textFieldWorkerName.getText().trim());
	            worker.setPhone(textFieldWorkerPhone.getText().trim());
	            //worker.setDepartment(department);(textFieldWorkerName.getText().trim());
	            worker.setAdditionalInfo(textAreaWorkerAdditionalInfo.getText().trim());	            

	            buttonConfirmClicked = true;
	            dialogStage.close();
	       }

	    }
	 
	    private boolean validate() {
	        String errorMessage = "";

	        if (textFieldWorkerName.getText() == null || textFieldWorkerName.getText().trim().length() == 0) {
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
	            alert.setTitle("Erro ao cadastrar Funcionário");
	            alert.setHeaderText("Campos inválidos, por favor, corrija...");
	            alert.setContentText(errorMessage);
	            alert.show();
	            return false;
	        }
	    }

		public Worker getWorker() {
			return worker;
		}

		public void setWorker(Worker worker) {
			this.worker = worker;
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

