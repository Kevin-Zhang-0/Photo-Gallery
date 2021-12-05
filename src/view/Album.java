package view;
import java.util.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// TODO: Auto-generated Javadoc

/**
 * The Class Album. Each album is represented by an instance of this class.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class Album implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;
	
	/** The album photos. */
	ArrayList<Photo> album_photos;
	
	/** The name. */
	String name;
	
	/** The first date. */
	Calendar first_date;
	
	/** The last date. */
	Calendar last_date;
	
	/** The f first date. */
	String f_first_date;
	
	/** The f last date. */
	String f_last_date;
	
	/**
	 * Instantiates a new album.
	 *
	 * @param a_name the album name
	 */
	public Album(String a_name){
		name = a_name;
		album_photos = new ArrayList<Photo>();
	}
	
	/**
	 * Sets the range of the Album.
	 */
	public void setRange(){
		
		
		
		
		Photo last =  Collections.max(this.album_photos, Comparator.comparing(s -> s.getMillis()));
		Photo first = Collections.min(this.album_photos, Comparator.comparing(s -> s.getMillis()));
		
		first_date = first.date;
		last_date = last.date;
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		f_first_date = sdf.format(first_date.getTime());
		f_last_date = sdf.format(last_date.getTime());
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the of the Album name
	 */
	public String getName(){
		return this.name;
	}
}
