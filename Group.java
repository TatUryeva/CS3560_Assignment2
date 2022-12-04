import java.util.ArrayList;

public class Group 
{
	//public static Group root = new Group("Root");
	
	private String ID;
	//private ArrayList<String> users = new ArrayList<String>();
	//private ArrayList<String> subgroups = new ArrayList<String>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Group> subgroups = new ArrayList<Group>();
	
	//private Group supergroup;
	
	private static int groupCount = 0;

	private long creationTime;
	
	public Group(String id)
	{
		ID = id;
		Database.addGroup(this);
		groupCount++;	
		//root.addSubgroup(id);
		creationTime = System.currentTimeMillis();
	}
	
	public String getID()
	{
		return ID;		
	}
	
	public void addUser(String u)
	{
		users.add(new User(u));	
		Database.getUser(u).setGroup(this);
	}
	
	public void addSubgroup(String g)
	{
		subgroups.add(new Group(g));
	}

	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public ArrayList<Group> getSubgroups()
	{
		return subgroups;
	}

	public static int getGroupCount()
	{
		return groupCount;
	}

	public long getCreationTime()
	{
		return creationTime;
	}
	
	public String toString()
	{
		return ID;
	}
}
