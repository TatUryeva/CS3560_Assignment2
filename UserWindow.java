package assignment2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class UserWindow implements ActionListener
{	
	public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("follow user")) 
        {
        	//Database.getUser("bob").follow(userID.getText());
        	user.follow(userID.getText());
        	flwgs.addElement(userID.getText());
        	followings.setModel(flwgs);
        	userID.setText("");
        }
        if (s.equals("post message")) 
        {
        	//Database.getUser("bob").post(messageText.getText());
        	user.post(messageText.getText());
        	fd.addElement(messageText.getText());
        	feed.setModel(fd);
        	messageText.setText("");
        }
    }
	
	public void recieveFeedUpdate(String message)
	{
		//fd.addElement(user.getFeed().get(user.getFeed().size()-1).toString());
		fd.addElement(message);
    	feed.setModel(fd);
	}
	
	public String getUserID()
	{
		return user.getID();
	}
	
	public static ArrayList<UserWindow> getOpenedUserWindows()
	{
		return openedUserWindows;
	}
	
	private static ArrayList<UserWindow> openedUserWindows = new ArrayList<UserWindow>();
	
	private JFrame frame;
    
    private JButton followUser;
    private JButton postMessage;
    
    private JTextArea userID;
    private JTextArea messageText;
    
    private JList followings;
    private JList feed;
    
    private DefaultListModel flwgs;
    private DefaultListModel fd;
    
    private User user;
    
	//public static void main(String[] args) throws Exception
    public UserWindow(String id) throws InterruptedException
	{
    	openedUserWindows.add(this);
    	/*
		Group root = new Group("Root");
		
		User john = new User("john");
		User bob = new User("bob");
		User steve = new User("steve");
		
		root.addUser("john");
		root.addUser("bob");
		root.addUser("steve");
		*/
    	user = Database.getUser(id);
		//user.follow("john");
		//bob.follow("steve");
		
		//Database.getUser("john").post("lol");
		//Database.getUser("steve").post("kek");
				
		
		
		
		frame = new JFrame(user.getID() + " User View");
		GridBagLayout layout = new GridBagLayout();
		JPanel controls = new JPanel(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		followUser = new JButton("follow user");
		postMessage = new JButton("post message");
		
		followUser.addActionListener(this);//(new UserWindow(id));
		postMessage.addActionListener(this);//(new UserWindow(id));
		
		userID = new JTextArea(1, 20);
		messageText = new JTextArea(5, 20);
		
		
		flwgs = new DefaultListModel();
		//flwgs.addElement(Database.getUser("john").getID());
		fd = new DefaultListModel();
		//modelFollowings.addElement("john");
		//modelFeed = new DefaultListModel();
		
		followings = new JList(flwgs);
		feed = new JList(fd);
		//followings = new JList(Database.getUser("bob").followingsToArray());
		//feed = new JList(Database.getUser("bob").getFeed().toArray());
		//followings = new JList(user.followingsToArray());
		//feed = new JList(user.getFeed().toArray());
		
		JScrollPane followingsView = new JScrollPane(followings);
        frame.add(followingsView);
        JScrollPane feedView = new JScrollPane(feed);
        frame.add(feedView);			 	        
		
        gbc.insets = new Insets(5,5,5,5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
		controls.add(userID, gbc);
		
		gbc.gridx = 1;
	    gbc.gridy = 0;
		controls.add(followUser, gbc);
		
		gbc.gridx = 0;
	    gbc.gridy = 1;      
	    gbc.gridwidth = 2;
		controls.add(followings, gbc);
		
		     
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
		controls.add(messageText, gbc);
		
		gbc.gridx = 1;
	    gbc.gridy = 2;
		controls.add(postMessage, gbc);
		
		gbc.gridx = 0;
	    gbc.gridy = 3;      
	    gbc.gridwidth = 2;
		controls.add(feed, gbc);
		
		
		frame.add(controls);
		frame.setSize(400, 300);
		 
		frame.setVisible(true);
		
		//while (true)
		//{
			//Thread.sleep(2000);
		//}
	}

}
