package concord;
import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable 
{

	
	private static final long serialVersionUID = 7959383570910422524L;
	
	protected String text;
	protected int userID;
	protected Message linkMessage;
	protected boolean pinned;
	protected Date timeStamp;
	protected Channel channelIn;
	protected int id;
	//protected Date timeStamp;
	
	public Message(String t, int id, int messageID) {
		text = t;
		userID = id;
		timeStamp= new Date();
		id = messageID;
	}
	
	//respond message 
	public Message(String t, int id, Message m2) {
		text = t;
		userID = id;
		linkMessage = m2;
		timeStamp = new Date();
	}
	public void linkMessage(Message M) {
		linkMessage = M;
	}
	
	
	public void pinMessage() {
		pinned=true;
		//pinMessage
	}
	public void unPin() {
		if(pinned) {
			pinned=false;
		}
	}
	public Message getLinkMessage() {
		return linkMessage;
	}
	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}
	/**
	 * @return the userID
	 */
	public int getUserID()
	{
		return userID;
	}
	/**
	 * @return the pinned
	 */
	public boolean isPinned()
	{
		return pinned;
	}
	public int getId()
	{
		return id;
	}
	
	
	
}
