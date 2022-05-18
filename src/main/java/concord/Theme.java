package concord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Theme
{
	public ArrayList<CssElement> elements;
	public String filePath;
	public String themeName;
	public boolean isSetTheme;

	
	public Theme(String name, String filePath){
		elements = new ArrayList<CssElement>();
		themeName = name;
		this.filePath =filePath;
		createTheme();
		isSetTheme =false;
	}
	
	
	public ArrayList<CssElement> getElements()
	{
		return elements;
	}


	public void setElements(ArrayList<CssElement> elements)
	{
		this.elements = elements;
	}

	public String getFilePath()
	{
		return filePath;
	}
	
	public void setFilePath(String filePath)
		{
		this.filePath = filePath;
		}
	
	public String getThemeName()
	{
		return themeName;
	}
	
	public void setThemeName(String themeName)
	{
		this.themeName = themeName;
	}
	
	public CssElement findElement(String id)
	{
		CssElement object = elements.get(0);
		for(CssElement ce: elements)
		{
			if(ce.getSelector().equals(id))
			{
				object=ce;
			}
			
		}
		return object;
	}
	public int findElementIndex(String id)
	{
		int found = 0;
		for(int i =0; i<elements.size();i++)
		{
			if(elements.get(i).getSelector().equals(id))
			{
				found =i;
			}
			
		}
		return found;
	}
	public boolean elementExists(String select)
	{
		if(findElement(select).getSelector().equals(select))
		{
			return true;
		}else {
			return false;
		}
	}
	
	public void changeProperty(CssElement object, CssProperty property,String newValue)
	{
		if(elements.isEmpty()|| !elementExists(object.getSelector()))
			{
				object.changeProperty(property, newValue);
				elements.add(object);
			}
		else {
				CssElement element = findElement(object.getSelector());
				element.changeProperty(property, newValue);
		}
			
	}
	//Creates a new file with themeName.css and copies Template.css into it 
	public void createTheme()
	{
		InputStream template = null;
	    OutputStream newTheme = null;
	    try {
	    	
	        template = new FileInputStream(filePath+"template.css");
	        newTheme = new FileOutputStream(filePath+themeName+".css");
	        
	        int i;
	        while ((i = template.read()) != -1) {  
	        	newTheme.write(i);
	        	}//end while
	        
	        }catch(Exception e) {  
	            System.out.println("Error Found: "+e.getMessage());  
	        } 
	    finally {  
            		try
					{
            		   if (template != null) {  
            			template.close();
            		   }
						// use close() method of FileOutputStream class to close the stream  
	                    if (newTheme != null) {  
	                        newTheme.close();  
	                    }  
					
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
                    }  
                    
                }      

	public void editTheme()
	{
		
		if(elements.isEmpty()) {
			System.out.println("nothing to change");
			
		}else {
			for (CssElement element: elements)
			{
				element.replace();
		
			}
		}
		
		
	}
	public void deleteTheme()
	{
		 	
		File theme = new File(filePath+themeName+".css"); 
		    if (theme.delete()) { 
		      System.out.println("Deleted the file: " + theme.getName());
		    } else {
		      System.out.println("Failed to delete the file.");
		    } 
		    themeName = null;
		    filePath = null;
		    elements = null;
		    
	}
	public void addElement(CssElement object)
	{
		
	Boolean sameTheme = (object.getThemeName()+object.getFilePath()).equals(themeName+filePath);
	
	if(elements.isEmpty()&& sameTheme)
	{
		elements.add(object);

	}
	else if(!elementExists(object.getSelector())&& sameTheme)
		{
			elements.add(object);
		}
	}
	public void isSetTheme(Boolean prefer)
	{
		isSetTheme = prefer;
	}
	public boolean getisSetTheme()
	{
		return isSetTheme;
	}
	/*
	 * More for Testing
	 * https://www.devglan.com/corejava/comparing-files-in-java
	 */
	public int compareTextfiles() throws IOException
	{

        BufferedReader reader1 = new BufferedReader(new FileReader(filePath+"template.css"));
         
        BufferedReader reader2 = new BufferedReader(new FileReader(filePath+themeName+".css"));
         
        String line1 = reader1.readLine();
         
        String line2 = reader2.readLine();
         
        boolean areEqual = true;
         
        int lineNum = 1;
         
        while (line1 != null || line2 != null)
        {
            if(line1 == null || line2 == null)
            {
                areEqual = false;
                 
                break;
            }
            else if(! line1.equalsIgnoreCase(line2))
            {
                areEqual = false;
                 
                break;
            }
             
            line1 = reader1.readLine();
             
            line2 = reader2.readLine();
             
            lineNum++;
        }
        if(areEqual)
        {
            System.out.println("Two files have same content."); 
        }
        else
        {
            System.out.println("Two files have different content. They differ at line "+lineNum);
             
            System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
        }
        try
		{
			reader1.close();
		    
	        reader2.close();
		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(areEqual)
        {
        	return 1;
        }else {
        	return 0;
        }
      
         
     
    }

	
}
	


	

