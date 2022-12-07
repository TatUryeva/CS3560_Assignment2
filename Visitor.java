
public class Visitor 
{
	public double visit(User u)
	{
		return User.getUserCount();		
	}

	public double visit(Group g)
	{
		return Group.getGroupCount();		
	}	
}
