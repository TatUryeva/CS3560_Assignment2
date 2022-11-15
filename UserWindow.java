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
        	user.post(messageText.getText());
        	fd.addElement("me: " + messageText.getText());
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

	private void resumeList(DefaultListModel<String> model, ArrayList<String> ids)
	{
		for (int i = 0; i < ids.size(); i++)
		{
			model.addElement(ids.get(i).toString());
		}
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
		
		
		flwgs = new DefaultListModel<String>();
		resumeList(flwgs, user.followingsToStringArrayList());
		fd = new DefaultListModel<String>();
		resumeList(fd, user.getFeed());
		
		followings = new JList<String>(flwgs);
		feed = new JList<String>(fd);
		
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
