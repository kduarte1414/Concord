package concord;
import java.util.Date;


public class Message
{

	protected String text;
	protected int userID;
	protected Message linkMessage;
	protected boolean pinned;
	protected Date timeStamp;
	protected Channel channelIn;
	//protected Date timeStamp;
	
	public Message(String t, int id, Channel c) {
		text = t;
		userID = id;
		timeStamp= new Date();
		channelIn = c;
	}
	//Messages that are in direct messages dont have channels
	public Message(String t, int id) {
		text = t;
		userID = id;
		timeStamp= new Date();
	}
	
	//respond message 
	public Message(String t, int id, Message m2) {
		text = t;
		userID = id;
		linkMessage = m2;
		timeStamp= new Date();
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
	
	
	
}
