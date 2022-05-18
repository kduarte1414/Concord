package concord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class CssProperty
{

	String oldValue;
	String newValue;
	String propertyName;
	
	public String getPropertyName()
	{
		return propertyName;
	}
	public String getNewValue()
	{
		return newValue;
	}
	public void setNewValue(String value) {
			newValue = value;
	}
	
	public String getOldValue()
	{
		return oldValue;
	}

	public void setOldValue(String oldValue)
	{
		this.oldValue = oldValue;
	}
	
	
	/* Code reference for edit theme function:
	 * https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java
	 */
	
	public void replace(String path)
	{
		//Get String 
		try
		{
			//String path ="/Users/katherineduarte/eclipse-workspace/Concord/src/main/java/Main/"+ThemeName+".css";
			
			Scanner sc = new Scanner(new File(path));
			StringBuffer buffer = new StringBuffer();
		      //Reading lines of the file and appending them to StringBuffer
		      while (sc.hasNextLine()) {
		         buffer.append(sc.nextLine()+System.lineSeparator());
		      }
		      String fileContents = buffer.toString();
		      //System.out.println("Contents of the file: "+fileContents);
		      sc.close();
		      //Replacing the old line with new line
		      fileContents = fileContents.replaceAll(oldValue, newValue);
		      //instantiating the FileWriter class
		      FileWriter writer = new FileWriter(path);
		      writer.append(fileContents);
		      writer.flush();
		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

	
	
}
