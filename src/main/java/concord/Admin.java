package concord;

public class Admin extends Role
{
	public Admin(Server s) 
	{
	
	roleName = "Admin";
	canBlockChannel= true;
	canKickUser = true;
	canAssignModerator= true;
	canAssignAdmin=true;
	canRespond = true;
	canCreateNewMessage = true;
	serverIn=s;
	rolesBelow.add("User");	
	rolesBelow.add("Moderator");
	rolesBelow.add("Admin");
	
	}
}
