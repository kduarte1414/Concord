package concord;

import java.util.ArrayList;

public class Label extends CssElement
{

	public Label(String name, String fp)
	{
		super(name, fp);
		elementType ="Label";
		selector = "Label";
		// TODO Auto-generated constructor stub
	}
	public Label(String filePath, String themeName, String id)
	{
		super(themeName,filePath);
		elementType = "Label";
		selector = id;
	}

}
