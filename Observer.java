//package assignment2;

import java.util.ArrayList;

public class Observer 
{
	private User user;
	
	public Observer(User u)
	{
		user = u;
		user.setObserver(this);
	}
	
	public void updateFeed(String message)
	{
		ArrayList<UserWindow> ouw = UserWindow.getOpenedUserWindows();
		for (int i = 0; i < user.getFollowers().size(); i++)
		{
			Database.getUser(user.getFollowers().get(i).getID()).getFeed().add(message);
			//System.out.println(user.getFollowers().get(i).getID()+" got feed from " + user.getID());
			for (int j = 0; j < UserWindow.getOpenedUserWindows().size(); j++)
			{
				if (ouw.get(j).getUserID().equals(user.getFollowers().get(i).getID()))
				{
					//System.out.println(ouw.get(j).getUserID() + " updated interface with " + user.getID());
					ouw.get(j).recieveFeedUpdate(user.getID(), message);
				}
			}			
		}				
		
	}
	
	public void updateFollowing(String id)
	{
		Database.getUser(id).getFollowers().add(user);
	}
}
