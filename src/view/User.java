package view;
import java.io.Serializable;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class User. Each user is represented by an instance this class.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class User implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;
	
	/** The albums. */
	ArrayList<Album> albums;
	
	/** The username. */
	public String username;
	
	/** The tags. */
	ArrayList<String> tags = new ArrayList<String>();
	
	/**
	 * Instantiates a new user.
	 *
	 * @param name the name of the user
	 */
	public User(String name){
		username = name;
		albums = new ArrayList<Album>();
		tags.add("Location1");
		tags.add("People0");
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the user's username
	 */
	public String get_username(){
		return this.username;
	}
}
