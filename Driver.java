import java.util.ArrayList;

public class Driver 
{
	public static void printMessages(ArrayList<String> m)
	{
		for (int i = 0; i < m.size(); i++)
		{
			System.out.println(m.get(i));
		}
		System.out.println();
	}
	
	public static void printUsers(ArrayList<User> u)
	{
		for (int i = 0; i < u.size(); i++)
		{
			System.out.println(u.get(i).getID());
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{	
		AdminWindow.instantiateAdminWindow();
	}

}
