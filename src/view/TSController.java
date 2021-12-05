package view;

import java.util.ArrayList;

import app.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class TSController. The class responsible for searching by tag values.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class TSController extends MasterController {
	
	/** The search. */
	@FXML Button search;
	
	/** The tag 1. */
	@FXML ChoiceBox<String> tag1;
	
	/** The tag val 1. */
	@FXML TextField tagVal1;
	
	/** The and or. */
	@FXML ChoiceBox<String> andOr;
	
	/** The tag 2. */
	@FXML ChoiceBox<String> tag2;
	
	/** The tag val 2. */
	@FXML TextField tagVal2;
	
	/** The back. */
	@FXML Button back;
	
	
	/** The tag 1 list. */
	private static ObservableList<String> tag1List;
	
	/** The tag 2 list. */
	private static ObservableList<String> tag2List;
	
	/** The obs. */
	private static ObservableList<String> obs;
	
	/** The temp. */
	public static Album temp = new Album("");
	
	/** The from search. */
	public static boolean from_search = false;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public void start (Stage mainStage) throws Exception{
		//dates
		
		
		
		
		//tags
		tag1List = FXCollections.observableArrayList();
		for(String x: LoginController.curr_user.tags){
			
			tag1List.add(shorten(x));
			
			
		}
		tag1.setItems(tag1List);
		tag1.getSelectionModel().selectFirst();
		
		tagVal1.setPromptText("Tag 1 Value");
		tagVal1.setFocusTraversable(false);
		
		
		
		tag2List = FXCollections.observableArrayList();
		for(String x: LoginController.curr_user.tags){
			
			tag2List.add(shorten(x));
			
			
		}
		tag2.setItems(tag2List);
		tag2.getSelectionModel().selectFirst();
		
		tagVal2.setPromptText("Optional Tag 2 Value");
		tagVal2.setFocusTraversable(false);
		
		obs = FXCollections.observableArrayList();
		obs.add("AND");
		obs.add("OR");
		andOr.setItems(obs);
		andOr.getSelectionModel().selectFirst();
		
		
		
		
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
			temp = new Album("temp");
			ArrayList<Photo> searched_photos = new ArrayList<Photo>();
			temp.album_photos = searched_photos;
			boolean and = true;
			if(andOr.getValue().equals("OR")) {
				and = false;
			}
			if(b == search) {
				if(( tagVal1.getText().trim().equals("") && tagVal2.getText().trim().equals("") ) || (tagVal1.getText().trim().equals("") && !tagVal2.getText().trim().equals("") ) ) {
					//alert
					
				}
				else {
					//single search
					//System.out.println("single");
					//System.out.println("checkpoitn 1");
					if(tagVal2.getText().trim().equals("")) {
						//System.out.println("checkpoitn 2");
						ArrayList<Album> album_list = LoginController.curr_user.albums;
						for(Album a: album_list) {
							for(Photo p : a.album_photos) {
								if(p.tags.containsKey(search(tag1.getValue()))) {
									
									
									//System.out.println("checkpoitn 3");
									//for(int i = 0; i < p.tags.get(search(tag1.getValue())).size();i++) {
										//System.out.println("tag val: " + p.tags.get(search(tag1.getValue())).get(i));
									//}
									if(p.tags.get(search(tag1.getValue())).contains(tagVal1.getText().trim())) {
										//System.out.println("checkpoitn 4");
										if(!searched_photos.contains(p)) {
											searched_photos.add(p);
										}
										
									}
								}
							}
						}
						
						
					}
					else {
						//double search
						ArrayList<Album> album_list = LoginController.curr_user.albums;
						for(Album a: album_list) {
							for(Photo p : a.album_photos) {
								if(p.tags.containsKey(search(tag1.getValue())) && p.tags.containsKey(search(tag2.getValue()))   ) {
									//System.out.println("checkpoitn 3");
									if(and) {
										if(p.tags.get(search(tag1.getValue())).contains(tagVal1.getText().trim())  && p.tags.get(search(tag2.getValue())).contains(tagVal2.getText().trim())) {
											//System.out.println("checkpoitn 4");
											if(!searched_photos.contains(p)) {
												searched_photos.add(p);
											}
											
										}
									}
									else {
										if(p.tags.get(search(tag1.getValue())).contains(tagVal1.getText().trim())  || p.tags.get(search(tag2.getValue())).contains(tagVal2.getText().trim())) {
											//System.out.println("checkpoitn 4");
											if(!searched_photos.contains(p)) {
												searched_photos.add(p);
											}
											
										}
									}
								}
							}
						}
						
						
						
						
						
						
					}
				    if(temp.album_photos.size()>0){
						AOController.curr_album = temp;
						from_search = true;
						helper.switchScene("/view/Temp Album.fxml",true);
					}
					
					else{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.initOwner(Photos.getStage());
						alert.setTitle("Alert");
						alert.setHeaderText("No search results");
						alert.showAndWait();
					}
					
					
					
				}
			}
			
			else if (b == back) {
				helper.switchScene("/view/Album Overview Screen.fxml",true);
			}
	}

	
	
	
}
