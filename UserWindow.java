//package assignment2;

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
        	if (Database.getUser(userID.getText()) != null)
        	{
	        	user.follow(userID.getText());
	        	flwgs.addElement(userID.getText());
	        	followings.setModel(flwgs);
	        	userID.setText("");
        	}
        }
        if (s.equals("post message")) 
        {
        	//Database.getUser("bob").post(messageText.getText());
        	user.post(messageText.getText());
        	fd.addElement("me: " + messageText.getText());
        	feed.setModel(fd);
        	messageText.setText("");
        }
    }
	
	public void recieveFeedUpdate(String userName, String message)
	{
		//fd.addElement(user.getFeed().get(user.getFeed().size()-1).toString());
		fd.addElement(userName + ": " + message);
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
    
    private JList<String> followings;
    private JList<String> feed;
    
    private DefaultListModel<String> flwgs;
    private DefaultListModel<String> fd;
    
    private User user;
    
	//public static void main(String[] args) throws Exception
    public UserWindow(String id)
	{
    	openedUserWindows.add(this);
    	
    	user = Database.getUser(id);
				
		frame = new JFrame(user.getID() + " User View");
		GridBagLayout layout = new GridBagLayout();
		JPanel controls = new JPanel(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		followUser = new JButton("follow user");
		postMessage = new JButton("post message");
		
		followUser.addActionListener(this);
		postMessage.addActionListener(this);
		
		userID = new JTextArea(1, 20);
		messageText = new JTextArea(5, 20);
		messageText.setLineWrap(true);
		
		//JScrollPane messageScroll = new JScrollPane(messageText);
        //frame.add(messageScroll);		
		
		flwgs = new DefaultListModel<String>();
		//flwgs.addElement(Database.getUser("john").getID());
		fd = new DefaultListModel<String>();
		//modelFollowings.addElement("john");
		//modelFeed = new DefaultListModel();
		
		followings = new JList<String>(flwgs);
		feed = new JList<String>(fd);
		//followings = new JList(Database.getUser("bob").followingsToArray());
		//feed = new JList(Database.getUser("bob").getFeed().toArray());
		//followings = new JList(user.followingsToArray());
		//feed = new JList(user.getFeed().toArray());
		
		JScrollPane followingsScroll = new JScrollPane(followings);
        frame.add(followingsScroll);
        JScrollPane feedScroll = new JScrollPane(feed);
        frame.add(feedScroll);			 	        
		
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
		frame.setSize(400, 400);
		 
		frame.setVisible(true);
	}

}
