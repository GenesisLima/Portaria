package br.ufpe.ntvru.portaria.webcam;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import br.ufpe.ntvru.portaria.controller.VisitorsController;
import br.ufpe.ntvru.portaria.controller.VisitorsFormController;
import br.ufpe.ntvru.portaria.model.WebCamInfo;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class WebCamController implements Initializable, WebcamDiscoveryListener,ItemListener,WebcamListener,WindowListener,Runnable{

	
	private static final long serialVersionUID = 1L;

	@FXML
	Button btnStartCamera;

    @FXML
    private Button btnTakePicture;

	@FXML
	Button btnDisposeCamera;

	@FXML
	ComboBox<WebCamInfo> cbCameraOptions;

	@FXML
	BorderPane bpWebCamPaneHolder;

	@FXML
	FlowPane fpBottomPane;

	@FXML
	ImageView imgWebCamCapturedImage;



	private BufferedImage grabbedImage;
	private Webcam selWebCam = null;
	private boolean stopCamera = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

	private String cameraListPromptText = "Choose Camera";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		fpBottomPane.setDisable(true);
		ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
		int webCamCounter = 0;
		for (Webcam webcam : Webcam.getWebcams()) {
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter++;
		}
		cbCameraOptions.setItems(options);
		cbCameraOptions.setPromptText(cameraListPromptText);
		cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {

			@Override
			public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
				if (arg2 != null) {
					System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
					initializeWebCam(arg2.getWebCamIndex());
				}
			}
		});
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				setImageViewSize();
			}
		});

	}

	protected void setImageViewSize() {

		double height = bpWebCamPaneHolder.getHeight();
		double width = bpWebCamPaneHolder.getWidth();
		imgWebCamCapturedImage.setFitHeight(height);
		imgWebCamCapturedImage.setFitWidth(width);
		imgWebCamCapturedImage.prefHeight(height);
		imgWebCamCapturedImage.prefWidth(width);
		imgWebCamCapturedImage.setPreserveRatio(true);

	}

	protected void initializeWebCam(final int webCamIndex) {

		Task<Void> webCamIntilizer = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if (selWebCam == null) {
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				} else {
					closeCamera();
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				}
				startWebCamStream();
				return null;
			}

		};

		new Thread(webCamIntilizer).start();
		fpBottomPane.setDisable(false);
		btnStartCamera.setDisable(true);
	}

	protected void startWebCamStream() {

		stopCamera = false;
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				while (!stopCamera) {
					try {
						if ((grabbedImage = selWebCam.getImage()) != null) {

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils
										.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return null;
			}

		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgWebCamCapturedImage.imageProperty().bind(imageProperty);

	}

	private void closeCamera() {
		if (selWebCam != null) {
			selWebCam.close();
		}
	}

	public void stopCamera(ActionEvent event) {
		stopCamera = true;
		btnStartCamera.setDisable(false);
		btnTakePicture.setDisable(true);
	}

	public void startCamera(ActionEvent event) {
		stopCamera = false;
		startWebCamStream();
		btnStartCamera.setDisable(true);
		btnTakePicture.setDisable(false);
	}

	public void disposeCamera(ActionEvent event) {
		stopCamera = true;
		closeCamera();
		btnTakePicture.setDisable(true);
		btnStartCamera.setDisable(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webcamOpen(WebcamEvent we) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webcamClosed(WebcamEvent we) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webcamDisposed(WebcamEvent we) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webcamImageObtained(WebcamEvent we) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webcamFound(WebcamDiscoveryEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webcamGone(WebcamDiscoveryEvent event) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private WritableImage takePicture() {
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(VisitorsController.class.getResource("/br/ufpe/ntvru/portaria/view/VisitorsForm.fxml"));
	    VisitorsFormController visitorFormController = loader.getController();
		
		Webcam webcam = Webcam.getDefault();
		  
		  
		  Dimension[] nonStandardResolutions = new Dimension[] {
					WebcamResolution.PAL.getSize(),
					WebcamResolution.HD720.getSize(),
					new Dimension(2000, 1000),
					new Dimension(1000, 500),
				};
		
		    webcam.setCustomViewSizes(nonStandardResolutions);
			WebcamUtils.capture(webcam, "TESTE".replaceAll("\\s+","").trim(), ImageUtils.FORMAT_PNG);

			 BufferedImage image = webcam.getImage();
		  return SwingFXUtils.toFXImage(image, null);
		
	}
}
