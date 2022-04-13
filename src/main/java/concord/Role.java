package concord;
import java.util.ArrayList;

public abstract class Role
{

	String roleName;
	boolean canBlockChannel;
	boolean canKickUser;
	boolean canAssignModerator;
	boolean canAssignAdmin;
	boolean canRespond;
	boolean canCreateNewMessage;
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
	 * @param canCreateNewMessage the canCreateNewMessage to set
	 */
	public void setCanCreateNewMessage(boolean canCreateNewMessage)
	{
		this.canCreateNewMessage = canCreateNewMessage;
	}
	
	public void createNewMessage(Channel c, Message m) {
		if(canCreateNewMessage) {
			//can only send messages in a channel that this server has
			if(serverIn.getChannels().contains(c)) {
				c.newMessage(m);
			}
		}
	}
	
	public void respond(Channel c, Message m1, Message m2) {
		if(canRespond){
			if(serverIn.getChannels().contains(c)) {
				c.replyMessage(m1, m2);
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
		m.pinMessage();
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

