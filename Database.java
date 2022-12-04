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
	
	public static String[] getAllIDs()
	{
		String[] ids = new String[users.size() + groups.size()];
		for (int i = 0; i < users.size(); i++)
		{
			System.out.println(users.get(i).getID());
			ids[i] = users.get(i).getID();
		}
		for (int i = 0; i < groups.size(); i++)
		{
			ids[i + users.size()] = groups.get(i).getID();
		}
		return ids;
	}

	public static String getLastUpdatedUser()
	{
		String luu = users.get(0).getID();
		long temp = users.get(0).getLastUpdateTime();
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getLastUpdateTime() >= temp)
			{
				temp = users.get(i).getLastUpdateTime();
				luu = users.get(i).getID();
			}
		}
		return luu;
	}
	
	public static boolean isEmpty()
	{
		if (users.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static ArrayList<User> getAllUsers()
	{
		return users;
	}
	
}
