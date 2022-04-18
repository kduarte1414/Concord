package concord;

import java.io.Serializable;

public class Default extends Role implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6044557346083739919L;

	public Default(Server s) 
	{
	
	roleName="Default";
	canBlockChannel= false;
	canKickUser = false;
	canAssignModerator= false;
	canAssignAdmin=false;
	canRespond = true;
	canCreateNewMessage = true;
	canChangeServerDescription= false;
	serverIn =s;
	rolesBelow.add("default");

	}
}