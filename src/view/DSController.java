package view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import app.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class DSController. This class allows the user to search through all of their photos by a date range.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class DSController extends MasterController{
	
	/** The date 1. */
	@FXML TextField date1;
	
	/** The date 2. */
	@FXML TextField date2;
	
	/** The search. */
	@FXML Button search;
	
	/** The back. */
	@FXML Button back;
	
	/** The temp album. */
	static Album tempAlbum = new Album("");
	
	/** The obs list. */
	private static ObservableList<String> obsList;
	
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
		date1.setPromptText("MM/DD/YYYY");
		date1.setFocusTraversable(false);
		date2.setPromptText("MM/DD/YYYY");
		date2.setFocusTraversable(false);
		
		
		
		
		//tags
		obsList = FXCollections.observableArrayList();
		for(String x: LoginController.curr_user.tags){
			
			obsList.add(x);
			
			
		}
	
		
		
	}
	
	/**
	 * Checks if is valid date.
	 *
	 * @param input the input
	 * @return true, if is valid date
	 */
	public boolean isValidDate(String input) {
     SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
     format.setLenient(false);
     try {
          format.parse(input);
          return true;
     }
     catch(ParseException e){
          return false;
     }
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
		if(b == search) {
			String d1 = date1.getText();
			String d2 = date2.getText();
			tempAlbum = new Album("");

			if(isValidDate(d1) && isValidDate(d2)){
				try {
		            Date start = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(d1);
		            Date end = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(d2);
					for (int i=0; i<LoginController.curr_user.albums.size(); i++){
						int aLength = LoginController.curr_user.albums.get(i).album_photos.size();
						Album a = LoginController.curr_user.albums.get(i);
						for (int j=0; j<aLength; j++){
							Photo p = a.album_photos.get(j);
							String cD = p.f_date;
							Date cDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
		                    .parse(cD);
							if (cDate.compareTo(start) >= 0 && cDate.compareTo(end) <= 0){
								if(!tempAlbum.album_photos.contains(p)){
									tempAlbum.album_photos.add(p);
								}	
							}
							
						}
						
					}
		        } catch (ParseException p) {
		            p.printStackTrace();
		        }
		        if(tempAlbum.album_photos.size()>0) {
			        AOController.curr_album = tempAlbum;
			        from_search = true;
			        helper.switchScene("/view/Temp Album.fxml",true);
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(Photos.getStage());
					alert.setTitle("Alert");
					alert.setHeaderText("No search results");
					alert.showAndWait();
				}
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("Invalid Date(s)");
				alert.showAndWait();
			}
			
		}
		else if (b == back) {
			helper.switchScene("/view/Album Overview Screen.fxml",true);
		}
	}


}
