package concord;

import java.io.Serializable;

public class Admin extends Role implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6552795394721152705L;

	public Admin(Server s) 
	{
	
	roleName = "Admin";
	canBlockChannel= true;
	canKickUser = true;
	canAssignModerator= true;
	canAssignAdmin=true;
	canRespond = true;
	canCreateNewMessage = true;
	canChangeServerDescription= true;
	serverIn=s;
	rolesBelow.add("User");	
	rolesBelow.add("Moderator");
	rolesBelow.add("Admin");
	
	}
}
