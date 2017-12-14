package br.ufpe.ntvru.portaria.webcam;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import com.jfoenix.controls.JFXButton;

import br.ufpe.ntvru.portaria.helpers.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class WebCamController implements WebcamDiscoveryListener,ItemListener,WebcamListener,WindowListener{

	
	private static final long serialVersionUID = 1L;

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;
	
	@FXML
    private JFXButton visitorsSaveBtn;

    @FXML
    private ImageView visitorsCameraImgView;

    @FXML
    private AnchorPane visitorsAPanePicture;
    
    
    @Override
	public void webcamFound(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.addItem(event.getWebcam());
		}
	}

	@Override
	public void webcamGone(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.removeItem(event.getWebcam());
		}
	}
	
	
private void run() {
	
	
	Dimension[] nonStandardResolutions = new Dimension[] {
			WebcamResolution.PAL.getSize(),
			WebcamResolution.HD720.getSize(),
			new Dimension(2000, 1000),
			new Dimension(1000, 500),
		};
	
	
	Webcam.addDiscoveryListener(this);
	
	picker = new WebcamPicker();
	picker.addItemListener(this);

	webcam = picker.getSelectedWebcam();

	if (webcam == null) {
		System.out.println("No webcams found...");
		System.exit(1);
	}
	
	
}

@Override
public void itemStateChanged(ItemEvent e) {
	if (e.getItem() != webcam) {
		if (webcam != null) {

			panel.stop();

			
			
			//remove(panel);

			webcam.removeWebcamListener(this);
			webcam.close();

			webcam = (Webcam) e.getItem();
			webcam.setViewSize(WebcamResolution.HD720.getSize());
			webcam.addWebcamListener(this);

			System.out.println("selected " + webcam.getName());

			panel = new WebcamPanel(webcam, false);
			panel.setFPSDisplayed(true);

			//add(panel, BorderLayout.CENTER);
			//pack();

			Thread t = new Thread() {

				@Override
				public void run() {
					panel.start();
				}
			};
			t.setName("example-stoper");
			t.setDaemon(true);
		//	t.setUncaughtExceptionHandler(this);
			t.start();
		}
	}
	
}

@Override
public void webcamClosed(WebcamEvent arg0) {
	System.out.println("webcam closed");
	
}

@Override
public void webcamDisposed(WebcamEvent arg0) {
	System.out.println("webcam disposed");
	
}

@Override
public void webcamImageObtained(WebcamEvent arg0) {
	System.out.println("webcam got image");
	
}

@Override
public void webcamOpen(WebcamEvent arg0) {
	System.out.println("webcam open");
	
}

@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent e) {
	webcam.close();
	
}

@Override
public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent e) {
	System.out.println("webcam viewer resumed");
	panel.resume();
	
}

@Override
public void windowIconified(WindowEvent e) {
	System.out.println("webcam viewer paused");
	panel.pause();
	
}

@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
    
public void execute() throws IOException {
	run();
	System.out.println("RUN CAMERA ");
//	 final SwingNode swingNode = new SwingNode();
//	 Pane pane = new Pane();
//     pane.getChildren().add(swingNode); // Adding swing node
//	visitorsAPanePicture = new AnchorPane();
//	visitorsAPanePicture.getChildren().add(swingNode);
	 Parent root = FXMLLoader.load(getClass().getResource(Routes.CAMERAVIEW));
	
//	new WebcamViewerExample().run();
	
}
@FXML
void takeVIsitorPicture(ActionEvent event) {
   
}
}
