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

	public static User getUser(int index)
	{
		return users.get(index);
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

	public static Group getGroup(int index)
	{
		return groups.get(index);
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

	public static double getPositive()
	{
		int numPositive = 0;
		for (int i = 0; i < User.getUserCount(); i++)
		{
			for (int j = 0; j < users.get(i).getFeed().size(); i++)
			{
				if (users.get(i).getFeed().get(j).toString().indexOf("cool") > 0
						||users.get(i).getFeed().get(j).toString().indexOf("great") > 0
						||users.get(i).getFeed().get(j).toString().indexOf("awesome") > 0
						||users.get(i).getFeed().get(j).toString().indexOf("amazing") > 0
						||users.get(i).getFeed().get(j).toString().indexOf("nice") > 0)
				{
					numPositive++;
				}
				
			}
		}
		if (User.getMessageCount() == 0)
		{
			return 0;
		}
		else
		{
			return numPositive/User.getMessageCount();
		}		
	}
	
}
