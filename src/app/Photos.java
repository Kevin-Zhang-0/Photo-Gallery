
package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.LoginController;
import view.MasterController;


/**
 * Photos project for software methodology.
 * <p>
 * Allows users to add photos, add tags and view them.
 *
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */
public class Photos extends Application{
	
	/** The second stage. */
	private static Stage secondStage;
	
	/** The orig. */
	public static Scene orig;
	
	/** The test controller. */
	public static LoginController testController;
	
	/** The p stage. */
	public static Stage p_stage;
	
	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		secondStage = primaryStage;
		p_stage = primaryStage;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Photos Login Screen.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		
		LoginController listController = loader.getController();
		listController.start(primaryStage);
		testController = listController;
		//System.out.println("test123");
		//
		
		//FXMLLoader loader2 = new FXMLLoader();
		//.setLocation(getClass().getResource("/view/addEdit.fxml"));
		//AnchorPane second = (AnchorPane)loader2.load();
		

		
		Scene scene = new Scene(root);
		orig = scene;
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photos");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public static Stage getStage() {
        return secondStage;
    }
	
	/**
	 * Switch scene.
	 *
	 * @param location name of the fxml that will be displayed on the stage
	 * @param runStart a boolean that determines if the start method in the controller of that fxml should be run
	 * @throws Exception the exception
	 */
	public void switchScene(String location, boolean runStart) throws Exception {
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource(location));
		AnchorPane second = (AnchorPane)loader2.load();
		Scene scene = new Scene(second);
		Photos.getStage().setScene(scene);
		if (runStart) {
			MasterController listController = loader2.getController();
			listController.start(p_stage);
		}
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		                             
		launch(args);
	}
}
