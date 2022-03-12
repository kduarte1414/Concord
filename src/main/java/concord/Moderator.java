package concord;


public class Moderator extends Role
{
	public Moderator(Server s) 
	{
	
	roleName="Moderator";
	canBlockChannel= true;
	canKickUser = true;
	canAssignModerator= true;
	canAssignAdmin=false;
	serverIn=s;
	canRespond = true;
	canCreateNewMessage = true;
	rolesBelow.add("Default");	
	rolesBelow.add("Moderator");
	
	}
	
}
