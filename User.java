import java.util.ArrayList;

public class User 
{
	private String ID;
	//private ArrayList<String> followers = new ArrayList<String>();
	//private ArrayList<String> followings = new ArrayList<String>();
	private ArrayList<User> followers = new ArrayList<User>();
	private ArrayList<User> followings = new ArrayList<User>();
	private ArrayList<String> feed = new ArrayList<String>();
	
	private Group userGroup;
	
	private static int userCount = 0;
	private static int messageCount = 0;
	
	private Observer observer;
	
	public User(String id)
	{
		ID = id;
		Database.addUser(this);
		userCount++;
		setObserver(new Observer(this));
		//setObserver(uo);
		//Group.getRoot().addUser(id);
	}
	
	public void follow(String id)
	{
		//followings.add(id);
		boolean follows = false;
		for (int i = 0; i< followings.size(); i++)
		{
			if (followings.get(i).getID().equals(id))
			{
				follows = true;
			}
		}
		
		if (!follows)
		{
			followings.add(Database.getUser(id));
			observer.updateFollowing(id);
			//Database.getUser(id).getFollowers().add(this);
		}		
	}
	
	public void addFeed(String message)
	{
		feed.add(message);
	}
	
	public String getID()
	{
		return ID;
	}
	
	public ArrayList<User> getFollowers()
	{
		return followers;
	}
	
	public ArrayList<User> getFollowings()
	{
		return followings;
	}
	
	public ArrayList<String> getFeed()
	{
		return feed;
	}
	
	public void post(String message)
	{
		feed.add("me: " + message);
		messageCount++;
		observer.updateFeed(message);
	}
	
	public void setGroup(Group ug)
	{
		userGroup = ug;
	}
	
	public void setObserver(Observer uo)
	{
		observer = uo;
	}
	
	public Group getUserGroup()
	{
		return userGroup;
	}
	
	public static int getUserCount()
	{
		return userCount;
	}
	
	public static int getMessageCount()
	{
		return messageCount;
	}
	
	public ArrayList<String> followingsToStringArrayList()
	{
		ArrayList<String> flwgs = new ArrayList<String>();
		for (int i = 0; i < followings.size(); i++)
		{
			flwgs.add(followings.get(i).getID());
		}
		return flwgs;
	}	
	
	public Double accept(Visitor visitor) 
	{
	      return visitor.visit(this);
	}
	
	
	public String toString()
	{
		return ID;
	}
}
