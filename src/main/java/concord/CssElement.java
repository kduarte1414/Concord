package concord;

import java.util.ArrayList;

public abstract class CssElement
{
	ArrayList <CssProperty> properties;
	String elementType;
	String selector;
	String filePath;
	String themeName;
	public CssElement(String name, String fp)
	{
		filePath=fp;
		themeName =name;
		properties = new ArrayList<CssProperty>();
	}
	
	public void replace() {
		for(CssProperty cp: properties)
		{
			cp.replace(filePath+themeName+".css");
		}
	}
	public void changeProperty(CssProperty cp, String newValue)
	{
		//Add the property if it isn't already in the arrayList
		if(properties.isEmpty()||!propertyExist(cp.getPropertyName())) {
			String old=cp.getOldValue();
			cp.setOldValue(selector+old);
			cp.setNewValue(newValue);
			properties.add(cp);	
		//modify if it is in the arrayList
		}else {
			CssProperty property = findProperty(cp.getPropertyName());
			String old= property.getOldValue();
			property.setOldValue(selector+old);
			property.setNewValue(newValue);
		}
		
	}
	public boolean propertyExist(String pname)
	{
		if(findProperty(pname).getPropertyName().equals(pname))
		{
			return true;
		}else {
			return false;
		}
	}
	public CssProperty findProperty(String pname){
		
		CssProperty cpFound = properties.get(0);
		
		for(CssProperty cp: properties)
		{
			if(cp.getPropertyName().equals(pname))
			{
				cpFound = cp;
			}
			
		}
		
		return cpFound;
	}

	
	public ArrayList<CssProperty> getProperties()
	{
		return properties;
	}
	
	public void setProperties(ArrayList<CssProperty> properties)
	{
		this.properties = properties;
	}
	
	public String getElementType()
	{
		return elementType;
	}
	
	public void setElementType(String elementType)
	{
		this.elementType = elementType;
	}
	
	public String getSelector()
	{
		return selector;
	}
	
	public void setSelector(String selector)
	{
		this.selector = selector;
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
	
	
}

