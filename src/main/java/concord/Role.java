package concord;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Role implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1919316376357218358L;
	String roleName;
	boolean canBlockChannel;
	boolean canKickUser;
	boolean canAssignModerator;
	boolean canAssignAdmin;
	boolean canRespond;
	boolean canCreateNewMessage;
	boolean canChangeServerDescription;
	Server serverIn; //
	ArrayList <String> rolesBelow = new ArrayList<String>();
	
	public Role()
	{
		
	}
	/**
	 * @return the canRespond
	 */
	public boolean isCanRespond()
	{
		return canRespond;
	}

	/**
	 * @param canRespond the canRespond to set
	 */
	public void setCanRespond(boolean canRespond)
	{
		this.canRespond = canRespond;
	}

	/**
	 * @return the canCreateNewMessage
	 */
	public boolean isCanCreateNewMessage()
	{
		return canCreateNewMessage;
	}
	/**
	 * @return the canChangeServerDescription
	 */
	public boolean isCanChangeServerDescription()
	{
		return canChangeServerDescription;
	}
	/**
	 * @param canChangeServerDescription the canChangeServerDescription to set
	 */
	public void setCanChangeServerDescription(boolean canChangeServerDescription)
	{
		this.canChangeServerDescription = canChangeServerDescription;
	}

	public void ChangeServerDescription(String text)
	{
		if(canChangeServerDescription) {
			serverIn.setDescription(text);
		}
	}
	public void setCanCreateNewMessage(boolean canCreateNewMessage)
	{
		this.canCreateNewMessage = canCreateNewMessage;
	}
	
	public void createNewMessage(Channel c, Message m) {
		if(canCreateNewMessage) {
			//can only send messages in a channel that this server has
			if(serverIn.getChannels().contains(c)) {
				c.newMessage(m);
				serverIn.update();
				
			}
		}
	}
	public void createNewMessage(Channel c, String m, int userId) {
		if(canCreateNewMessage) {
			//can only send messages in a channel that this server has
			if(serverIn.getChannels().contains(c)){
				c.newMessage(m,userId);
				serverIn.update();
			}
		}
	}
	public void respond(Channel c, Message m1, Message m2) {
		if(canRespond){
			if(serverIn.getChannels().contains(c)) {
				c.replyMessage(m1, m2);
				serverIn.update();
			}
		}
		
	}
	public void respond(Channel c, Message m1, String m2, int userID) {
		if(canRespond){
			if(serverIn.getChannels().contains(c)) {
				c.replyMessage(m1, m2,userID);
				serverIn.update();
			}
		}
		
	}
	public void setRoleName(String role)
	{
		roleName = role;
	}
	
	public void blockChannel(Channel c)
	{
		if(canBlockChannel){
			c.lockChannel();
		}
	}
	public void KickUser(User u )
	{
		if(canKickUser){
			serverIn.kickUser(u);
		}
	}
	public void AssignModerator(User u) 
	{
		if(canAssignModerator){
			serverIn.assignModerator(u);
		}
	}
	public void AssignAdmin(User u) 
	{
			if(canAssignAdmin){
				serverIn.assignAdmin(u);
			}
	}
	
	public void pinMessage(Channel c, Message m)
	{
		c.pinMessage(m);
		serverIn.update();
	}
	
	//setting booleans 
	/**
	 * @return the canKickUser
	 */
	public boolean isCanKickUser()
	{
		return canKickUser;
	}

	/**
	 * @param canKickUser the canKickUser to set
	 */
	public void setCanKickUser(boolean canKickUser)
	{
		this.canKickUser = canKickUser;
	}


	/**
	 * @return the canAssignModerator
	 */
	public boolean isCanAssignModerator()
	{
		return canAssignModerator;
	}

	/**
	 * @param canAssignModerator the canAssignModerator to set
	 */
	public void setCanAssignModerator(boolean canAssignModerator)
	{
		this.canAssignModerator = canAssignModerator;
	}

	/**
	 * @return the canAssignAdmin
	 */
	public boolean isCanAssignAdmin()
	{
		return canAssignAdmin;
	}

	/**
	 * @param canAssignAdmin the canAssignAdmin to set
	 */
	public void setCanAssignAdmin(boolean canAssignAdmin)
	{
		this.canAssignAdmin = canAssignAdmin;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @return the canBlockChannel
	 */
	public boolean isCanBlockChannel()
	{
		return canBlockChannel;
	}
	
	/**
	 * @return the serverIn
	 */
	public Server getServerIn()
	{
		return serverIn;
	}
}

