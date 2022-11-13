import java.util.ArrayList;

public class Database 
{
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Group> groups = new ArrayList<Group>();
	
	public static void addUser(User u)
	{
		users.add(u);
	}
	
	public static void addGroup(Group g)
	{
		groups.add(g);
	}
	
	public static User getUser(String id)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getID().equals(id))
			{
				return users.get(i);
			}
		}
		return null;
	}
	
	public static Group getGroup(String id)
	{
		for (int i = 0; i < groups.size(); i++)
		{
			if (groups.get(i).getID().equals(id))
			{
				return groups.get(i);
			}
		}
		return null;
	}
	
	
	public static ArrayList<User> getAllUsers()
	{
		return users;
	}
	
}
