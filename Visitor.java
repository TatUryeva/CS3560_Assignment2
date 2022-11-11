package assignment2;

import java.util.ArrayList;

public class Visitor 
{
	public double visit(User u)
	{
		ArrayList<User> users = Database.getAllUsers();
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
