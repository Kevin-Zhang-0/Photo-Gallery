package view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import app.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class AOController. The album overview screen. Shows a list of all albums for the current user with each album's amount of photos and range of dates. Users can open, create, rename, or delete albums here. Users can also search by a date range or by tag values here.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class AOController extends MasterController{
	
	/** The Album list. */
	@FXML ListView<String> AlbumList;
	
	/** The create album. */
	@FXML Button createAlbum;
	
	/** The edit album. */
	@FXML Button editAlbum;
	
	/** The delete album. */
	@FXML Button deleteAlbum;
	
	/** The logout. */
	@FXML Button logout;
	
	/** The open album. */
	@FXML Button openAlbum;
	
	/** The date search. */
	@FXML Button dateSearch;
	
	/** The tag search. */
	@FXML Button tagSearch;
	
	/** The curr albums. */
	static ArrayList<Album> curr_albums;
	
	/** The curr album. */
	static Album curr_album;
	
	/** The curr album index. */
	static int curr_album_index;
	
	/** The obs list. */
	private static ObservableList<String> obsList;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public void start(Stage mainStage) throws Exception{
		
		ArrayList<User> serialize_user_list = LoginController.user_list;
		LoginController o = new LoginController();
		o.serialize(serialize_user_list);
		
		
		//populate listview here
		curr_albums = LoginController.curr_user.albums; 
		if(curr_albums.size()>0){
			obsList = FXCollections.observableArrayList();
			for(int i =0;i<curr_albums.size();i++){
				if(curr_albums.get(i).album_photos.size()==0){
					obsList.add(curr_albums.get(i).name + ", " + curr_albums.get(i).album_photos.size() + " photos");
				}
				else{
					obsList.add(curr_albums.get(i).name + ", " + curr_albums.get(i).album_photos.size() + " photos, " + curr_albums.get(i).f_first_date + " - " + curr_albums.get(i).f_last_date);
				}
				
			}
			AlbumList.setItems(obsList);
			
			AlbumList
			.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
					(obs, oldVal, newVal) -> 
					selectAlbum(mainStage));
					
			AlbumList.getSelectionModel().select(0);
			curr_album_index = 0;
			curr_album = curr_albums.get(0);
					
		
		}
		
		//
	}
	
	/**
	 * Select album from listView.
	 *
	 * @param mainStage the main stage
	 */
	public void selectAlbum(Stage mainStage) {
		//String item = listView.getSelectionModel().getSelectedItem();
		//System.out.println("listview index: " + AlbumList.getSelectionModel().getSelectedIndex());
		if(curr_albums.size()>0){
			int index = AlbumList.getSelectionModel().getSelectedIndex();
			
			curr_album = curr_albums.get(index);
			curr_album_index = index;
		}
		
        
		
		
	}
	
	/**
	 * Convert.
	 *
	 * @param e the ActionEvent e
	 * @throws Exception the exception
	 */
	public void convert(ActionEvent e) throws Exception {
		
		Button b = (Button)e.getSource();
		Photos helper = new Photos();
		if (b == createAlbum) {
			
			helper.switchScene("/view/Create Album.fxml", true);
		}
		else if(b == openAlbum){
			try {
				if(curr_albums.size()!=0) {					
					helper.switchScene("/view/In Album Screen.fxml",true);
				}
									
			} catch (Exception e1) {
									// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}      
		else if (b == editAlbum) {
			if(curr_albums.size()>0) {					
				helper.switchScene("/view/Rename Album.fxml",true);
			}
			
		}
		else if (b == deleteAlbum) {
			//get sleected album
			//get rid of that shit
			if (curr_albums.size() > 0) {
				curr_albums.remove(curr_album_index);
				obsList.remove(curr_album_index);
				
				if(curr_albums.size()>0){
					curr_album = curr_albums.get(0);
					curr_album_index = 0;
					AlbumList.getSelectionModel().select(0);
				}
				helper.switchScene("/view/Album Overview Screen.fxml",true);
			}
			
			
		}
		
		else if (b == dateSearch ) {
			helper.switchScene("/view/Date Search.fxml",true);
		}
		else if (b == tagSearch ) {
			helper.switchScene("/view/Tag Search.fxml",true);
		}
		else if (b == logout) {
			helper.switchScene("/view/Photos Login Screen.fxml",false);
		}
		
		
	
	}

	
	
}
