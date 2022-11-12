//package assignment2;

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
	
	public Group(String id)
	{
		if (Database.getGroup(id) == null)
		{
			ID = id;
			Database.addGroup(this);
			groupCount++;
		}		
		//root.addSubgroup(id);
	}
	
	public String getID()
	{
		return ID;		
	}
	
	public void addUser(String u)
	{
		//users.add(u);
		if(Database.getUser(u) == null)
		{
			users.add(new User(u));
		}	
		Database.getUser(u).setGroup(this);
	}
	
	public void addSubgroup(String g)
	{
		//subgroups.add(g);
		if(Database.getGroup(g) == null)
		{
			subgroups.add(new Group(g));
		}
		else
		{
			subgroups.add(Database.getGroup(g));
		}	
		subgroups.add(Database.getGroup(g));
		//Database.getGroup(g).setSupergroup(this);
	}
	/*
	public void setSupergroup(Group sg)
	{
		supergroup = sg;
	}
	*/
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public ArrayList<Group> getSubgroups()
	{
		return subgroups;
	}
	
	//public static Group getRoot()
	//{
		//return root;
	//}
	
	/*
	public Group getSupergroup()
	{
		return supergroup;
	}
	*/
	public static int getGroupCount()
	{
		return groupCount;
	}
	
	public String toString()
	{
		return ID;
	}
}
