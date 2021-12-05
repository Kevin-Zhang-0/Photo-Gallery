package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import app.Photos;
import java.nio.file.Path;
import java.nio.file.Paths;


// TODO: Auto-generated Javadoc
/**
 * The Class LoginController. Responsible for the login page
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class LoginController extends MasterController {
	
	/** The username. */
	@FXML TextField username;
	
	/** The confirm. */
	@FXML Button confirm;
	
	/** The is first. */
	boolean isFirst = true;
	
	/** The user list. */
	static ArrayList<User> user_list = new ArrayList<User>();
	
	/** The curr user. */
	static User curr_user;
	
	/** The user exist. */
	static boolean user_exist = false;;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 */
	public void start(Stage mainStage) {
		
		
		isFirst = d();
		if(isFirst) {
			Path path1 = Paths.get("src");
			Path path2 = path1.toAbsolutePath();
			Path f_path = Paths.get(path2.getParent() +"/data");
			User stock =  new User("stock");
			Album stockAlbum1 = new Album("stock");
			stock.albums.add(stockAlbum1);
			stockAlbum1.album_photos.add(new Photo(f_path.toString() + "/cat_caviar.jpg"));
			stockAlbum1.album_photos.add(new Photo(f_path.toString() + "/donk.jpg"));
			stockAlbum1.album_photos.add(new Photo(f_path.toString() + "/umbreon.png"));
			stockAlbum1.album_photos.add(new Photo(f_path.toString() + "/camel.jpg"));
			stockAlbum1.album_photos.add(new Photo(f_path.toString() + "/bitcoin.jpg"));
			stockAlbum1.album_photos.add(new Photo(f_path.toString() + "/tablet.jpg"));
			stockAlbum1.setRange();
			user_list.add(stock);
			//System.out.println("SHOULD ONLY HAPPEN ONCE");
			s(!isFirst);
			
			ArrayList<User> serialize_user_list = LoginController.user_list;
			LoginController o = new LoginController();
			o.serialize(serialize_user_list);
		}
		else {
			user_list = deserialize();
		}
		
	
	}
	
	/**
	 * Convert.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void convert(ActionEvent e) throws Exception{
		Photos helper = new Photos();
		Button b = (Button)e.getSource();
		if (b == confirm) {
			String inputted_username = username.getText();
			
			if(inputted_username.equals("admin")){
				helper.switchScene("/view/Admin.fxml", true);
				user_exist = true;
			}
			user_list.stream().filter(o -> o.get_username().equals(inputted_username)).forEach(
            	o -> {
                	//System.out.println("confirm");
                
                	
                	try {
						//AOController ob = new AOController();
						curr_user = o;
						reverse();
						helper.switchScene("/view/Album Overview Screen.fxml", true);
				
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                	
            	}
    		);
			if(!user_exist) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(Photos.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("User does not exist");
				alert.showAndWait();
			}
			user_exist = false;
			
			//System.out.println("confirm button worked");
			
			
		} 
		
	}
	
	/**
	 * Reverse.
	 */
	public void reverse() {
		user_exist =  !user_exist;
	}
	
	/**
	 * Serializing our boolean if it is the applications first startup.
	 *
	 * @param isF the boolean
	 */
	public void s(boolean isF) {
		Path path1 = Paths.get("src");
		Path f_path = Paths.get(path1.toAbsolutePath().getParent() + "/data" + "/isTrue.dat");
		
		
		try {
         FileOutputStream fileOut =
         new FileOutputStream(f_path.toString());
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         
         out.writeObject(isF);
         out.flush();
         out.close();
         fileOut.close();
         
      } catch (IOException i) {
         i.printStackTrace();
      }
	}
	
	/**
	 * Deserializing our boolean if it is the applications first startup.
	 *
	 * @return the boolean
	 */
	public boolean d() {
		boolean e = true;
		Path path1 = Paths.get("src");
		Path f_path = Paths.get(path1.toAbsolutePath().getParent() + "/data" + "/isTrue.dat");
		try {
         FileInputStream fileIn = new FileInputStream(f_path.toString());
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e = (boolean) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         
      } catch (ClassNotFoundException c) {
         c.printStackTrace();
         
      }
      return e;
	}
	
	/**
	 * Serialize our list of users containing their information.
	 *
	 * @param list ArrayList of users
	 */
	public void serialize(ArrayList<User> list){
		Path path1 = Paths.get("src");
		Path f_path = Paths.get(path1.toAbsolutePath().getParent() + "/data" + "/data.dat");
		
		
		try {
         FileOutputStream fileOut =
         new FileOutputStream(f_path.toString());
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         
         out.writeObject(list);
         out.flush();
         out.close();
         fileOut.close();
         
      } catch (IOException i) {
         i.printStackTrace();
      }
	}
	
	/**
	 * Deserialize our list of users containing their information.
	 *
	 * @return the ArrayList of users
	 */
	public ArrayList<User> deserialize(){
		ArrayList<User> e = new ArrayList<User>();
		Path path1 = Paths.get("src");
		Path f_path = Paths.get(path1.toAbsolutePath().getParent() + "/data" + "/data.dat");
		try {
         FileInputStream fileIn = new FileInputStream(f_path.toString());
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e = (ArrayList<User>) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         
      } catch (ClassNotFoundException c) {
         c.printStackTrace();
         
      }
      return e;
	}
	
	
}
