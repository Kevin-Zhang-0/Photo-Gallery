package view;
import java.util.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Photo. Each photo is stored in an instance of this class.
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225 
 */
public class Photo implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;
	
	/** The location. */
	public String location;
	
	/** The date stored in Calendar form. */
	public Calendar date;
	
	/** The date stored in String form. */
	public String f_date;
	
	/** The millisseconds of the photo date. */
	long millis;
	
	/** The caption. */
	public String caption = "";
	
	
	/** The tags of the photo. */
	HashMap<String, ArrayList<String>> tags = new HashMap<String, ArrayList<String>>();
	
	/**
	 * Instantiates a new photo.
	 *
	 * @param location the location of the file (aka filepath)
	 */
	public Photo(String location) {
		this.location = location;
		tags = new HashMap<String,ArrayList<String>>();
		
		
		File file1 = new File(location);
		long time1 = file1.lastModified();
		millis = time1;
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = new GregorianCalendar();	
		c.setTimeInMillis(time1);
		date = c;
		f_date = sdf.format(c.getTime());
		
		//picture = new FileInputStream(location);
	}
	
	/**
	 * Instantiates a new photo.
	 *
	 * @param location location of the file (aka filepath)
	 * @param cap the caption of the photo
	 */
	public Photo(String location, String cap){
		this.location = location;
		this.caption = cap;
		tags = new HashMap<String,ArrayList<String>>();
		
		
		File file1 = new File(location);
		long time1 = file1.lastModified();
		millis = time1;
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Calendar c = new GregorianCalendar();	
		c.setTimeInMillis(time1);
		c.set(Calendar.MILLISECOND, 0);
		date = c;
		f_date = sdf.format(c.getTime());
	}
	
	/**
	 * Gets the millis.
	 *
	 * @return the milliseconds of the photo
	 */
	public long getMillis(){
		return millis;
	}
	
	/**
	 * Sets the time of the photo.
	 */
	public void setTime(){
		File file1 = new File(location);
		long time1 = file1.lastModified();
		millis = time1;
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = new GregorianCalendar();	
		c.setTimeInMillis(time1);
		c.set(Calendar.MILLISECOND, 0);
		date = c;
		f_date = sdf.format(c.getTime());
	}
	
	/**
	 * Gets the location of the photo.
	 *
	 * @return the location of the photo
	 */
	public String getLocation(){
		return this.location;
	}
	
}
