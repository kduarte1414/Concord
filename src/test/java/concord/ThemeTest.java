package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThemeTest
{
	Theme t1;
	Theme t2;
	Theme t3;
	
	CssElement button;
	CssElement button2;
	CssElement label;
	CssProperty bgcolor;
	CssProperty txtColor;
	
	File themeFile;
	File theme2file;
	File templateFile;
	String path;
	
	@BeforeEach
	void setUp() throws Exception
	{
		path ="/Users/katherineduarte/eclipse-workspace/Concord/src/main/java/Main/";
		
		t1 = new Theme("BlueOcean", path);
		themeFile = new File("/Users/katherineduarte/eclipse-workspace/Concord/src/main/java/Main/BlueOcean.css");
		templateFile = new File(path+"template.css");
		bgcolor = new BackgroundColor();
		txtColor = new TextFillColor();
		
		button = new Button("BlueOcean",path);
		button2 = new Button("redSea",path);
		
	}

	@Test
	void testConstructor()
	{
		assertEquals("/Users/katherineduarte/eclipse-workspace/Concord/src/main/java/Main/",t1.getFilePath());
		assertEquals("BlueOcean",t1.getThemeName());
		assertEquals(true, t1.getElements().isEmpty());
		assertEquals(true,themeFile.exists());
	}
	@Test 
	void changePropertyTest()
	{
		assertEquals(true,t1.getElements().isEmpty());
		t1.changeProperty(button, bgcolor, "black");

		assertEquals(1,t1.getElements().size());
		assertEquals("Button",t1.getElements().get(0).getSelector());
		assertEquals(1,t1.getElements().get(0).getProperties().size());
		
		assertEquals("black",t1.getElements().get(0).getProperties().get(0).getNewValue());
		assertEquals(true,t1.elementExists(button.getSelector()));
		
		
	}
	@Test
	void editThemeTest() throws IOException
	{
		t2 = new Theme("redSea",path);
		theme2file = new File(path+"redSea.css");
		/*compareTextFiles compares Template and new theme file 
		 * returns 1 if same 0 if not
		 */
		assertEquals(1, t2.compareTextfiles());
		t2.changeProperty(button2, bgcolor, "black");
		t2.editTheme();
		assertEquals(0, t2.compareTextfiles());
		t2.deleteTheme();
	}
	@Test
	void addElement()
	{
		t3 = new Theme("DesertBrown",path);
		assertEquals(true,t3.getElements().isEmpty());
		
		CssElement label = new Label("DesertBrown",path);
		t3.addElement(label);
		assertEquals(1,t3.getElements().size());
		assertEquals("Label",t3.getElements().get(0).getSelector());
		assertEquals("Label",t3.getElements().get(0).getElementType());
		assertEquals(path,t3.getElements().get(0).getFilePath());
		assertEquals("DesertBrown",t3.getElements().get(0).getThemeName());
		label = new Label("oceanBlue",path);
		
		//wrong file shouldn't add it to this theme
		t3.addElement(label);
		assertEquals(1,t3.getElements().size());

		
	}
	@Test
	void deleteTest()
	{
		t1.deleteTheme();
		assertEquals(false,themeFile.exists());
		assertEquals(null,t1.getFilePath());
		assertEquals(null,t1.getThemeName());
		assertEquals(null, t1.getElements());
	}
	

}
