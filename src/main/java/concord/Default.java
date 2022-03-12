package concord;

public class Default extends Role
{
	public Default(Server s) 
	{
	
	roleName="Default";
	canBlockChannel= false;
	canKickUser = false;
	canAssignModerator= false;
	canAssignAdmin=false;
	canRespond = true;
	canCreateNewMessage = true;
	serverIn =s;
	rolesBelow.add("default");

	}
}