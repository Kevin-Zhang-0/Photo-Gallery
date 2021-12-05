
package view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import app.Photos;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController. Displays a list of users, admin can add or delete users.
 * 
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
 
public class AdminController extends MasterController{
	
	/** The list of users. */
	@FXML ListView<String> users_list;
	
	/** The add button. */
	@FXML Button add;
	
	/** The delete user button. */
	@FXML Button delete_user;
	
	/** The logout button. */
	@FXML Button logout;
	
	/** The new id button. */
	@FXML TextField new_id;
	
	
	/** The obs list for the ListView. */
	private static ObservableList<String> obsList;
	
	
	//private static User selected_user;
	
	/** The selected user index. */
	private static int selected_user_index;
	
	
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
		ArrayList<User> users = LoginController.user_list; 
		obsList = FXCollections.observableArrayList();
		for(int i =0;i<users.size();i++){
			if(!users.get(i).username.equals("admin")){
				obsList.add(users.get(i).username );
			}
			
		}
		users_list.setItems(obsList);
		
		users_list
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(
				(obs, oldVal, newVal) -> 
				selectUser(mainStage));
		
		
		
		//
	
	
	}
	
	/**
	 * Select user.
	 *
	 * @param mainStage the main stage
	 */
	public void selectUser(Stage mainStage) {
		//String item = listView.getSelectionModel().getSelectedItem();
		//System.out.println("listview index: " + AlbumList.getSelectionModel().getSelectedIndex());
		
		int index = users_list.getSelectionModel().getSelectedIndex();
		selected_user_index = index;
		//selected_user = LoginController.user_list.get(index);
		
        
		
		
	}
	
	/**
	 * Convert.
	 *
	 * @param e the ActionEvent e
	 * @throws Exception the exception
	 */
	public void convert(ActionEvent e) throws Exception {
		
		Button b = (Button)e.getSource();
		
		if (b == add) {
			//add exception handling
			
			String id = new_id.getText();
			if (!id.trim().equals("")) {
				
				if (id.trim().toLowerCase().equals("admin")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(Photos.getStage());
					alert.setTitle("Error");
					alert.setHeaderText("Cannot create another admin account");
					alert.showAndWait();
				}
				
				else if(LoginController.user_list.stream().filter(o -> o.get_username().equals(id.trim())).findFirst().isPresent()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(Photos.getStage());
					alert.setTitle("Error");
					alert.setHeaderText("User already exists");
					alert.showAndWait();
				}
				else {
					LoginController.user_list.add(new User(id.trim()));
					
					Photos helper = new Photos();
					helper.switchScene("/view/Admin.fxml", true);
				}
				
				
				
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("User name cannot be empty");
				alert.showAndWait();
			}
			
			
			
		}
		
		else if (b == delete_user) {
			if (LoginController.user_list.size() > 0) {
				LoginController.user_list.remove(selected_user_index);
				Photos helper = new Photos();
				helper.switchScene("/view/Admin.fxml", true);
			}
			
			
		}
		
		else if (b == logout) {
			Photos helper = new Photos();
			helper.switchScene("/view/Photos Login Screen.fxml", false);
		}
		
		ArrayList<User> serialize_user_list = LoginController.user_list;
		LoginController o = new LoginController();
		o.serialize(serialize_user_list);
		
		
		
	
	}
							
	
}
