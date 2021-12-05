package view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import app.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class TAController. Responsible for displaying the results of a search in the same format as viewing an album.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class TAController extends MasterController{
	
	/** The photos list. */
	@FXML ListView<Photo> photos_list;
	
	/** The current photo. */
	@FXML ImageView current_photo;
	
	/** The logout. */
	@FXML Button logout;
	
	/** The back. */
	@FXML Button back;
	
	/** The new album. */
	@FXML Button newAlbum;
	
	/** The left. */
	@FXML Button left;
	
	/** The right. */
	@FXML Button right;
	
	/** The details. */
	@FXML Label details;
	
	/** The curr photo index. */
	public static int curr_photo_index = 0;
	
	/** The counter. */
	public static int counter = 0;
	
	/** The curr photo. */
	public static Photo curr_photo;
	
	/** The boolean to determine if a search was made. */
	public static boolean from_search = false;
	
	/**
	 * Start.
	 *
	 * @param mainStage the Stage mainStage
	 * @throws Exception the exception
	 */
	public void start(Stage mainStage) throws Exception{
		
		ObservableList<Photo> items = FXCollections.observableArrayList();
		for(int i =0;i<AOController.curr_album.album_photos.size();i++){
        	//System.out.println(AOController.curr_album.album_photos.get(i).location);
			items.add(AOController.curr_album.album_photos.get(i));
		}  
		
		if(AOController.curr_album.album_photos.size()>0){
	    	photos_list.setCellFactory(listView -> new ListCell<Photo>() {
	        	
	            //private ImageView imageView = new ImageView();
	            @Override
	            public void updateItem(Photo c_photo, boolean empty) {
	            	//System.out.println("in1");
	                super.updateItem(c_photo, empty);
	                if (empty) {
	                    //setText(null);
	                	//System.out.println("empty");
	                	setGraphic(null);
	                } else {
					
	                 	//InputStream stream;
						HBox hBox = new HBox(5);
						hBox.setAlignment(Pos.CENTER_LEFT);
						//System.out.println("counter: " + counter);
							
						//System.out.println("test: " + AOController.curr_album.album_photos.get(counter).location);
								//stream = new FileInputStream(AOController.curr_album.album_photos.get(counter).location);
								//Image x = new Image(stream);
						
						//imageView.setImage(x);
						Image x = new Image(c_photo.location);		
						ImageView imageView = new ImageView();
					      //Setting image to the image view
					    imageView.setImage(x);
					    //imageView.setX(10);
				        //imageView.setY(10);
					    imageView.fitHeightProperty();
					    imageView.fitWidthProperty();
				        imageView.setFitWidth(50);
				        imageView.setFitHeight(50);
				        imageView.setSmooth(true);
				        imageView.setPreserveRatio(true);
					      
						hBox.getChildren().addAll(   imageView,   new Label(c_photo.caption)    );
	                  
								
						//setText(c_photo.caption);
		                setGraphic(hBox);
	                    
	                    
	                   
	                    
	                }
	            }
	        });
	    
	        photos_list.setItems(items);
			Image x = new Image(AOController.curr_album.album_photos.get(curr_photo_index).location);
			curr_photo = AOController.curr_album.album_photos.get(curr_photo_index);
			
			
			
			details.setText(getInfo(curr_photo));
			
			
			
	        current_photo.setImage(x);
	        current_photo.fitHeightProperty();
	        current_photo.fitWidthProperty();
	        current_photo.setFitWidth(294);
	        current_photo.setFitHeight(294);
	        current_photo.setSmooth(true);
	        current_photo.setPreserveRatio(true);
	        //centerImage(current_photo);
	        if(AOController.curr_album.album_photos.size()!=0){
	        	photos_list.getSelectionModel().select(curr_photo_index);
	        }
	        photos_list
			.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
					(obs, oldVal, newVal) -> 
					selectPhoto(mainStage));
		}
		
		
		
		
	}
	
	/**
	 * Select a photo from the listView.
	 *
	 * @param mainStage the main stage
	 */
	public void selectPhoto(Stage mainStage) {
		//String item = listView.getSelectionModel().getSelectedItem();
		//System.out.println("listview index: " + photos_list.getSelectionModel().getSelectedIndex());
		
		int index = photos_list.getSelectionModel().getSelectedIndex();
		curr_photo_index = index;
		curr_photo = AOController.curr_album.album_photos.get(index);
		//Photos helper = new Photos();
		Image x = new Image(curr_photo.location);
        current_photo.setImage(x);
        current_photo.fitHeightProperty();
        current_photo.fitWidthProperty();
        current_photo.setFitWidth(294);
        current_photo.setFitHeight(294);
        current_photo.setSmooth(true);
        current_photo.setPreserveRatio(true);
		details.setText(getInfo(curr_photo));
		
	}
	
	/**
	 * For cycling through photos with left and right arrows.
	 *
	 * @param left boolean for if previous photo should be displayed
	 */
	public void left_right(boolean left){
		if(AOController.curr_album.album_photos.size()>0){
			int index = photos_list.getSelectionModel().getSelectedIndex();
			int future_index;
			if(left){
				future_index = index -1;
				if(future_index<0){
					future_index = AOController.curr_album.album_photos.size()-1;
				}
			}
			else{
				future_index = index+1;
				if(future_index==AOController.curr_album.album_photos.size()){
					future_index = 0;
				}
			}
			curr_photo = AOController.curr_album.album_photos.get(future_index);
			//Photos helper = new Photos();
			Image x = new Image(curr_photo.location);
	        current_photo.setImage(x);
	        photos_list.getSelectionModel().select(future_index);
	    }
		
	}
	
	/**
	 * Gets the info.
	 *
	 * @param p A photo
	 * @return Returns a String containing the details of the photo
	 */
	public String getInfo(Photo p){
		String x = "";
		x+= p.caption;
		x+= "\n";
		String keys_values = "";
		ArrayList<String> values;
		for(String s: p.tags.keySet()){
			values = p.tags.get(s);
			keys_values += shorten(s)+ " - ";
			keys_values +=  values.get(0);
			for(int i = 1;i<values.size();i++){
				keys_values +=  ", " + values.get(i);
			}
			
			
			keys_values += "\n";
			
		}
		x+= keys_values;
		return x;
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
		if (b == newAlbum) {
			from_search = true;
			helper.switchScene("/view/Create Album.fxml", true);
			
		}
		if(b == left){
			left_right(true);
		}
		else if(b == right){
			left_right(false);
		}
		else if(b == back){
			
			
			curr_photo_index = 0;
			helper.switchScene("/view/Album Overview Screen.fxml", true);
		}
		else if (b == logout) {
			helper.switchScene("/view/Photos Login Screen.fxml",false);
		}
		
	}
	
}
