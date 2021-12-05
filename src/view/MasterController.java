package view;

import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterController. The class is the superclass of all controllers and them to call the start method.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public abstract class MasterController {
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @throws Exception the exception
	 */
	public abstract void start(Stage mainStage) throws Exception;
	
	/**
	 * Shortens the tag, removing the tag names type.
	 *
	 * @param x the String of the tags full name
	 * @return the tag name
	 */
	public String shorten(String x){
		return x.substring(0,x.length()-1);
	}
	
	/**
	 * Checks if type of tag can have a single value or multiple.
	 *
	 * @param x the full tag name x
	 * @return true, if it is a single value
	 */
	public boolean isSingle(String x) {
		if (x.charAt(x.length()-1) == '1'){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Search.
	 *
	 * @param x the shortened tag name x
	 * @return the tag name including its type
	 */
	public String search(String x){
		String x1 = x + "1";
		String x0 = x + "0";
		
		if(LoginController.curr_user.tags.contains(x1)){
			return x1;
		}
		else{
			if(LoginController.curr_user.tags.contains(x0)){
				return x0;
			}
		}
		return x1;
		
	}
}
