package br.ufpe.ntvru.portaria.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufpe.ntvru.portaria.model.Visitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VisitorsFormController implements Initializable{

	
    @FXML
    private Label labelVisitorName;

    @FXML
    private Label labelVisitorCpf;

    @FXML
    private Label labelVisitorPhone;

    @FXML
    private TextField textFieldVisitorCpf;

    @FXML
    private TextField textFieldVisitorPhone;

    @FXML
    private Label labelVisitorAccountable;

    @FXML
    private TextField textFieldVisitorAccountable;

    @FXML
    private Label labelVisitorAdditionalInfo;

    @FXML
    private TextArea textAreaVisitorAdditionalInfo;

    @FXML
    private TextField textFieldVisitorName;

	    

	    @FXML
	    private Button buttonConfirmar;

	    @FXML
	    private Button buttonCancelar;
	    
	    private Stage dialogStage;
	    private boolean buttonConfirmClicked = false;
	    
	    private Visitor visitor;

	    @FXML
	    void handleButtonInsert(ActionEvent event) {

	    }

	   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isButtonConfirmClicked() {
		return buttonConfirmClicked;
	}

	public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
		this.buttonConfirmClicked = buttonConfirmarClicked;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	
	 @FXML
	    public void handleButtonCancel() {
	        dialogStage.close();
	    }

	 @FXML
	    public void handleButtonConfirm() {
             System.out.println(visitor.getName()+" NAMED ==>"+textFieldVisitorName.getText()+" FOR ==>"+visitor.toString());
 	        System.out.println("FIELD "+textFieldVisitorName.getText());

	        if (validate()) {
                 
	            visitor.setName(textFieldVisitorName.getText().trim());
	            visitor.setCpf(textFieldVisitorCpf.getText().trim());
	            visitor.setPhone(textFieldVisitorPhone.getText().trim());
	            visitor.setProduct("TVU");
	            visitor.setAccountable(textFieldVisitorAccountable.getText().trim());
	            visitor.setAdditionalInfo(textAreaVisitorAdditionalInfo.getText().trim());
	            

	            buttonConfirmClicked = true;
	            dialogStage.close();
	       }

	    }
	 
	    private boolean validate() {
	        String errorMessage = "";

	        if (textFieldVisitorName.getText() == null || textFieldVisitorName.getText().trim().length() == 0) {
	            errorMessage += "Nome inválido!\n";
	        }
	        if (textFieldVisitorCpf.getText() == null || textFieldVisitorCpf.getText().trim().length() == 0) {
	            errorMessage += "CPF inválido!\n";
	        }
	        if (textFieldVisitorPhone.getText() == null || textFieldVisitorPhone.getText().trim().length() == 0) {
	            errorMessage += "Telefone inválido!\n";
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	          
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Erro ao cadastrar Visitante");
	            alert.setHeaderText("Campos inválidos, por favor, corrija...");
	            alert.setContentText(errorMessage);
	            alert.show();
	            return false;
	        }
	    }
	    
	 
}


