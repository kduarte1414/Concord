package concord;

import java.io.Serializable;

public class Moderator extends Role implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8569168885624066986L;

	public Moderator(Server s) 
	{
	
	roleName="Moderator";
	canBlockChannel= true;
	canKickUser = true;
	canAssignModerator= true;
	canAssignAdmin=false;
	canChangeServerDescription= true;
	serverIn=s;
	canRespond = true;
	canCreateNewMessage = true;
	rolesBelow.add("Default");	
	rolesBelow.add("Moderator");
	
	}
	
}
