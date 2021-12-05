package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import app.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class CMController. Users can move or copy photos to other albums.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class CMController extends MasterController{
	
	/** The copy. */
	@FXML Button copy;
	
	/** The cancel. */
	@FXML Button cancel;
	
	/** The move. */
	@FXML Button move;
	
	/** The albums. */
	@FXML ChoiceBox<String> albums;
	
	/** The obs drop down. */
	private static ObservableList<String> obsDropDown;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public void start(Stage mainStage) throws Exception{
		obsDropDown = FXCollections.observableArrayList();
		for(Album x: AOController.curr_albums){
			if(!x.name.equals(AOController.curr_album.name)){
				obsDropDown.add(x.name);
			}
			
			
		}
		albums.setItems(obsDropDown);
		albums.getSelectionModel().selectFirst();
	}
	
	/**
	 * Convert.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void convert(ActionEvent e) throws Exception {
		
		Button b = (Button)e.getSource();
		
		
		Photos helper = new Photos();
		String chosen_album = albums.getValue();
		int chosen_album_index = 0;
		Album a = null;
		if(chosen_album!=null){
			
			
			//Album a = AOController.curr_albums.stream().filter(o -> o.getName().equals(chose_album)).findFirst();
			for(int i = 0;i<AOController.curr_albums.size();i++){
				if (chosen_album.equals(AOController.curr_albums.get(i).name)) {
					chosen_album_index = i;	
					a = AOController.curr_albums.get(i);
				}
					
					
			}
		}
		
		
		
		
		if (b == copy) {
			if(chosen_album==null){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("No other albums available, please create new album before copying");
				alert.showAndWait();
			}
			else if(!a.album_photos.contains(IAController.curr_photo)){
				a.album_photos.add(IAController.curr_photo);
				IAController.curr_photo = a.album_photos.get(a.album_photos.size()-1);
				IAController.curr_photo_index = a.album_photos.size()-1;
				AOController.curr_album_index = chosen_album_index;
				AOController.curr_album = a;
				helper.switchScene("/view/In Album Screen.fxml",true);
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("Photo already exists in Album");
				alert.showAndWait();
			}
		}
		else if (b == move) {
			if(chosen_album==null){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("No other albums available, please create new album before moving");
				alert.showAndWait();
			}
			else if(!a.album_photos.contains(IAController.curr_photo)){
				a.album_photos.add(IAController.curr_photo);
				
				AOController.curr_album.album_photos.remove(IAController.curr_photo_index);
				
				IAController.curr_photo = a.album_photos.get(a.album_photos.size()-1);
				IAController.curr_photo_index = a.album_photos.size()-1;
				AOController.curr_album_index = chosen_album_index;
				AOController.curr_album = a;
				
				
				helper.switchScene("/view/In Album Screen.fxml",true);
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("Photo already exists in Album");
				alert.showAndWait();
			}
			
		}
		else if (b == cancel) {
			helper.switchScene("/view/In Album Screen.fxml",true);
		}
		
	}
	
}
