package view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import app.Photos;


// TODO: Auto-generated Javadoc
/**
 * The Class IAController. This class allows the user to view all of the photos in an album. The photos are displayed in a list with a thumbnail and caption for each photo. The photo's details are displayed on the right side. There is also a manual slideshow, which allows the user to view the photos in the album one by one.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class IAController extends MasterController{
	
	/** The photos list. */
	@FXML ListView<Photo> photos_list;
	
	/** The current photo. */
	@FXML ImageView current_photo;
	
	/** The add photo. */
	@FXML Button addPhoto;
	
	/** The edit photo. */
	@FXML Button editPhoto;
	
	/** The logout. */
	@FXML Button logout;
	
	/** The back. */
	@FXML Button back;
	
	/** The cp photo. */
	@FXML Button cpPhoto;
	
	/** The delete photo. */
	@FXML Button deletePhoto;
	
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
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage mainStage) throws Exception{
		
		ArrayList<User> serialize_user_list = LoginController.user_list;
		LoginController o = new LoginController();
		o.serialize(serialize_user_list);
		
		//System.out.println("sdasdasdsadasdasdasdasdasdasda");
		
		/**
        ObservableList<String> items = FXCollections.observableArrayList();
                
        for(int i =0;i<AOController.curr_album.album_photos.size();i++){
        	System.out.println(AOController.curr_album.album_photos.get(i).location);
			items.add(AOController.curr_album.album_photos.get(i).caption);
		}      
		**/
		ObservableList<Photo> items = FXCollections.observableArrayList();
		for(int i =0;i<AOController.curr_album.album_photos.size();i++){
        	//System.out.println(AOController.curr_album.album_photos.get(i).location);
			items.add(AOController.curr_album.album_photos.get(i));
		}      
       
        //photos_list.setItems(items); 
        
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
	 * Select photo.
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
	 * Left right.
	 *
	 * @param left the left
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
	 * Convert.
	 *
	 * @param e the ActionEvent e
	 * @throws Exception the exception
	 */
	public void convert(ActionEvent e) throws Exception {
		
		Button b = (Button)e.getSource();
		Photos helper = new Photos();
			
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
		else if(b == addPhoto) {
			helper.switchScene("/view/Add Photo.fxml", true);
		}
		else if(b == editPhoto) {
			helper.switchScene("/view/Edit Photo.fxml", true);
		}
		else if(b == deletePhoto){
			if(AOController.curr_album.album_photos.size() != 0) {
				AOController.curr_album.album_photos.remove(curr_photo_index);
				if(curr_photo_index == AOController.curr_album.album_photos.size()){
					curr_photo_index = AOController.curr_album.album_photos.size()-1;
				}
				if(AOController.curr_album.album_photos.size() != 0) {
					AOController.curr_album.setRange();
				}
			}
			
			
			
			
			
			helper.switchScene("/view/In Album Screen.fxml", true);
		}
		else if (b == logout) {
			helper.switchScene("/view/Photos Login Screen.fxml",false);
		}
		else if (b == cpPhoto){
			helper.switchScene("/view/CopyMove Album.fxml",true);
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
			if(values!=null){
				keys_values += shorten(s)+ " - ";
				keys_values +=  values.get(0);
				for(int i = 1;i<values.size();i++){
					keys_values +=  ", " + values.get(i);
				}
				
				
				keys_values += "\n";
			}
			
		}
		x+= keys_values;
		return x;
	}
	
}
