package view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import app.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class EPController. This class allows users to edit the details of a photo.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class EPController extends MasterController{
	
	/** The add tag. */
	@FXML Button addTag;
	
	/** The confirm. */
	@FXML Button confirm;
	
	/** The cancel. */
	@FXML Button cancel;
	
	/** The save tag vals. */
	@FXML Button saveTagVals;
	
	/** The add exist tag. */
	@FXML Button addExistTag;
	
	/** The remove tag. */
	@FXML Button removeTag;
	
	/** The tag values. */
	@FXML TextField tagValues;
	
	/** The caption. */
	@FXML TextField caption;
	
	/** The new tag. */
	@FXML TextField newTag;
	
	/** The tag list. */
	@FXML ListView<String> tagList;
	
	/** The select tag. */
	@FXML ChoiceBox<String> selectTag;
	
	/** The s O rm. */
	@FXML ChoiceBox<String> sORm;
	
	/** The abs path. */
	static String absPath;
	
	/** The obs list. */
	private static ObservableList<String> obsList;
	
	/** The obs drop down. */
	private static ObservableList<String> obsDropDown;
	
	/** The o 10. */
	private static ObservableList<String> o10 = FXCollections.observableArrayList("Single", "Multiple");
	
	/** The user tags. */
	static ArrayList<String> user_tags;
	
	/** The curr tag index. */
	static int curr_tag_index = 0;
	
	/** The photo tags. */
	static HashMap<String,ArrayList<String>> photo_tags;
	
	/** The new p. */
	static Photo new_p;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public void start(Stage mainStage) throws Exception{
		new_p = IAController.curr_photo;
		caption.setText(new_p.caption);
		obsList = FXCollections.observableArrayList();
		user_tags = LoginController.curr_user.tags;
		photo_tags = new_p.tags;
		
		for(String x: photo_tags.keySet()){
			
			obsList.add(shorten(x));
			
		}
		tagList.setItems(obsList);
		tagList.getSelectionModel().select(curr_tag_index);
		
		tagList
			.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
					(obs, oldVal, newVal) -> 
					select(mainStage));
		
		String tag_name = tagList.getFocusModel().getFocusedItem();
		ArrayList<String> tag_values = photo_tags.get(search(tag_name));
		String tag_values_String = "";
			
		if(tag_values != null && tag_values.size()!=0){
			//System.out.println("shouldve printed new tag values");
			tag_values_String += tag_values.get(0).trim();
				
			for(int i = 1;i<tag_values.size();i++){
				tag_values_String += ", " + tag_values.get(i).trim();
			}
				
				
	
			tagValues.setText(tag_values_String);
			}
		else{
			tagValues.setText("");
		}
		
		
		
		
				
		obsDropDown = FXCollections.observableArrayList();
		for(String x: user_tags){
			if(!photo_tags.containsKey(x)){
				obsDropDown.add(shorten(x));
			}
			
		}
		selectTag.setItems(obsDropDown);
		selectTag.getSelectionModel().selectFirst();
		
		sORm.setItems(o10);
		sORm.getSelectionModel().selectFirst();
	
		
	}
	
	/**
	 * Select.
	 *
	 * @param mainStage the main stage
	 */
	public void select(Stage mainStage) {
		//String item = listView.getSelectionModel().getSelectedItem();
		//System.out.println("listview index: " + tagList.getSelectionModel().getSelectedIndex());
		int index = tagList.getSelectionModel().getSelectedIndex();	
		curr_tag_index = index;
		//System.out.println("taglist.getItems() size is: " + tagList.getItems().size());
		if(tagList.getItems().size() > 0){
			//System.out.println("greater than 0");
			String tag_name = tagList.getFocusModel().getFocusedItem();
			ArrayList<String> tag_values = photo_tags.get(search(tag_name));
			String tag_values_String = "";
			
			if(tag_values != null && tag_values.size()!=0){
				//System.out.println("shouldve printed new tag values");
				tag_values_String += tag_values.get(0).trim();
				
				for(int i = 1;i<tag_values.size();i++){
					tag_values_String += ", " + tag_values.get(i).trim();
				}
				
				
	
				tagValues.setText(tag_values_String);
			}
			else{
				tagValues.setText("");
			}
		}
		
		
		
        
		
		
	}
	
	/**
	 * Choice.
	 *
	 * @param x the x
	 * @return true, if successful
	 */
	public boolean choice(String x){
		if(x.equals("Single")){
			return true;
		}
		return false;
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
		
		if (b == addTag) {
			String nTag = newTag.getText();
			if(!nTag.trim().equals("")) {
				
				String l = "0";
				if(choice(sORm.getValue())){
					l = "1";
				}
				
				user_tags.add(nTag.trim() + l);
				obsDropDown.add(nTag.trim());
				selectTag.setItems(obsDropDown);
				selectTag.getSelectionModel().selectFirst();
				newTag.setText("");
			}
			//helper.switchScene("/view/Add Photo.fxml", true);
			
		}
		else if (b == saveTagVals) {
			if(curr_tag_index>=0){
				String tagVals = tagValues.getText().trim();
				
				String[] values = tagVals.split(",");
				//String tag_name = user_tags.get(curr_tag_index);
				String tag_name = tagList.getFocusModel().getFocusedItem();
				tag_name = search(tag_name);
				//System.out.println(tag_name);
				if ((isSingle(tag_name) && values.length > 1)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(Photos.getStage());
					alert.setTitle("Error");
					alert.setHeaderText("Tag: " + shorten(tag_name) + " must have only 1 value");
					alert.showAndWait();
				}
				else {
					photo_tags.remove(tag_name);
					ArrayList<String> tag_values_toAdd = new ArrayList<String>();
					for(String x : values){
						//System.out.println(x);
						if( (x.trim().length() > 0) ){
							tag_values_toAdd.add(x.trim());
						}
						
					}
					if(tag_values_toAdd.size()==0){
						photo_tags.put(tag_name, null);
					}
					else{
						photo_tags.put(tag_name, tag_values_toAdd);
					}
				}
				
				
				
			}
			
			
		}
		else if (b == addExistTag) {
			String existTag = selectTag.getValue();
			if(existTag!=null){
				photo_tags.put(search(existTag), null);
				obsList.add(existTag);
				tagList.setItems(obsList);
				tagList.getSelectionModel().select(obsList.size() - 1);
				curr_tag_index = obsList.size() - 1;
				//System.out.println("current tag index is: " + curr_tag_index);
				
				obsDropDown.remove(existTag);
				selectTag.setItems(obsDropDown);
				selectTag.getSelectionModel().selectFirst();
				
			}
			
			
		}
		else if (b == removeTag){
			
			if(tagList.getItems().size() > 0){
				String removed_tag = tagList.getFocusModel().getFocusedItem();
				//ArrayList<String> xd = new ArrayList();
				//xd.add("xd testr");
				//new_p.tags.put(removed_tag,xd);	
				new_p.tags.remove(search(removed_tag));
				obsDropDown.add(removed_tag);	
				
				selectTag.setItems(obsDropDown);
				selectTag.getSelectionModel().selectFirst();
				
				obsList.remove(curr_tag_index);
				tagList.setItems(obsList);
				
				if(tagList.getItems().size() > 0){
					//System.out.println("> 0");
					tagList.getSelectionModel().selectFirst();
					String f_tag = tagList.getItems().get(0);
					
					String tag_values_String = "";
					ArrayList<String> tag_values = new_p.tags.get(f_tag);
					if(tag_values!=null){
						tag_values_String += tag_values.get(0).trim();	
						for(int i = 1;i<tag_values.size();i++){
							tag_values_String += ", " + tag_values.get(i).trim();
						}
						
			
					tagValues.setText(tag_values_String);
					
					}
					else{
						tagValues.setText("");
					}
					
					
				
				}
			}
			if (tagList.getItems().size() == 0){
				
				tagValues.setText("");
			}
			
			
		}
		
		if (b == confirm){
			if (!caption.getText().trim().equals("")) {
				new_p.caption = caption.getText().trim();
			}
			new_p.tags.values().removeAll(Collections.singleton(null));
			//new_p.setTime();
			helper.switchScene("/view/In Album Screen.fxml", true);
			
			
		}
		else if (b == cancel) {
			helper.switchScene("/view/In Album Screen.fxml",true);
		}
		
		
	
	}
}
