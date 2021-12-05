package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import app.Photos;

// TODO: Auto-generated Javadoc
/**
 * The Class RAController. This class allows the user to rename an album.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class RAController extends MasterController{
	
	/** The album name. */
	@FXML TextField album_name;
	
	/** The confirm. */
	@FXML Button confirm;
	
	/** The cancel. */
	@FXML Button cancel;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public void start(Stage mainStage) throws Exception{
		album_name.setText(AOController.curr_album.name);
	}
	
	/**
	 * Convert.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void convert(ActionEvent e) throws Exception {
		
		Button b = (Button)e.getSource();
		
		if (b == confirm) {
			String id = album_name.getText();
			if (!id.trim().equals("")) {
				
				if(LoginController.curr_user.albums.stream().filter(o -> o.getName().equals(id.trim())).findFirst().isPresent()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(Photos.getStage());
					alert.setTitle("Error");
					alert.setHeaderText("Album already exists");
					alert.showAndWait();
				}
				else{
					AOController.curr_albums.get(AOController.curr_album_index).name = id.trim();
					Photos helper = new Photos();
					helper.switchScene("/view/Album Overview Screen.fxml", true);
				}
				
				
				
				
				
				
				
				
				
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(Photos.getStage());
					alert.setTitle("Error");
					alert.setHeaderText("Album name cannot be empty");
					alert.showAndWait();
			}
			
		}
		
		else if (b == cancel) {
			Photos helper = new Photos();
			helper.switchScene("/view/Album Overview Screen.fxml", true);
		}
		
	
	}
}
