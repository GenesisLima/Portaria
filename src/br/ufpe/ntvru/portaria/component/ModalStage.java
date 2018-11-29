package br.ufpe.ntvru.portaria.component;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ModalStage {

	private String fxml;
	
	private int width;
	
	private int height;

	public ModalStage(String fxml, int width, int height) {
		super();
		this.fxml = fxml;
		this.width = width;
		this.height = height;
	}


	public <T> T showModal(Button button) throws IOException {
		Stage modalState = new Stage();
	    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
	    Parent modalDialogRoot = loader.load();
	    T controller = loader.getController(); // Retrieve the controller
	    Scene modalScene = new Scene(modalDialogRoot, width, height);	  
	    modalState.initOwner(button.getScene().getWindow());
	    modalState.setScene(modalScene);
	    modalState.setResizable(false);
	    
	    Platform.runLater(() -> modalState.showAndWait());
	    
	    return controller;
	}
      
}
