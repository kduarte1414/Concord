package concord;

import java.util.ArrayList;

public class Button extends CssElement
{

	
	public Button(String themeName, String filePath) {
		super(themeName,filePath);
		elementType = "Button";
		selector = "Button";
		
	}
	
	//if there is a special selector
	public Button (String filePath, String themeName, String id)
	{
		super(themeName,filePath);
		elementType = "Button";
		selector = id;
	}
	
}
