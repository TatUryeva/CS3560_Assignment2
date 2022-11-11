package assignment2;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;   

public class AdminWindow extends JFrame implements ActionListener
{
	public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("add user")) 
        {
            u.setText(userID.getText());
            //try {
				Database.getGroup(currentNode.toString()).addUser(userID.getText());
			//} catch (Exception e1) {}
            //currentNode.insert(new DefaultMutableTreeNode(userID.getText()), 0);
			currentNode.insert(new DefaultMutableTreeNode(new User(userID.getText())), 0);
            SwingUtilities.updateComponentTreeUI(frame);
        }
        if (s.equals("add group")) 
        {
            g.setText(groupID.getText());
            //try {
				Database.getGroup(currentNode.toString()).addSubgroup(groupID.getText());
			//} catch (Exception e1) {}
            //currentNode.insert(new DefaultMutableTreeNode(groupID.getText()), 0);
            currentNode.insert(new DefaultMutableTreeNode(new Group(groupID.getText())), 0);
                
            SwingUtilities.updateComponentTreeUI(frame);
        }
        
        if (s.equals("show user total")) 
        {
            ut.setText(User.getUserCount()+"");
        }
        if (s.equals("show group total")) 
        {
            gt.setText(Group.getGroupCount()+"");
        }
        if (s.equals("show message total")) 
        {
            mt.setText(User.getMessageCount()+"");
        }
        
        if (s.equals("open user view"))
        {        	
        	if (Database.getUser(tree.getLastSelectedPathComponent().toString()) != null)
        	{
        		System.out.println(tree.getLastSelectedPathComponent().toString() + "'s user window is opened");
        		try {
					new UserWindow(tree.getLastSelectedPathComponent().toString());
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}        	
        }
        
        /*
        if (s.equals("show positive")) 
        {
            p.setText(Double.toString(Database.getAllUsers().get(0).accept(new Visitor())));
        }
        */
        
    }
	
	private class CustomTreeCellRenderer extends DefaultTreeCellRenderer 
	{
		  @Override
		  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) 
		  {
			  super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			  
			  if (value instanceof DefaultMutableTreeNode) 
			  {
				  DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
				  //if (Database.getGroup(node.getPath()[node.getPath().length-1].toString()) != null) 
				  if (node.getUserObject() instanceof Group)
				  {
					  DefaultTreeCellRenderer r = new DefaultTreeCellRenderer();
					  //r.setLeafIcon(r.getClosedIcon());
					  System.out.println(this.getClass().toString());
					  setIcon(r.getClosedIcon());
				  }
			  }
			  return this;
		  }
	}
	
    static JFrame frame;
    
    static JButton addUser;
    static JButton addGroup;
    static JButton userView;
    
    static JButton showUserTotal;
    static JButton showGroupTotal;
    static JButton showMessageTotal;   
    
    static JLabel u;
    static JLabel g;
    
    static JLabel ut;
    static JLabel gt;
    static JLabel mt;
    
    static JTextArea userID;
    static JTextArea groupID;
    
    static JTree tree;
    static DefaultMutableTreeNode currentNode;
    static DefaultTreeCellRenderer renderer;
    //static int nodePosition;
    
    static JButton sp;
    static JLabel p;
    
	public static void main(String[] args)
    //public Window()
	{		
		Group r = new Group("root");
		
		User j = new User("john");
		User b = new User("bob");
		User s = new User("steve");
		
		r.addUser("john");
		r.addUser("bob");
		r.addUser("steve");
		
		Group cs = new Group("CS356");		
		r.addSubgroup("CS356");
		
		User s1 = new User("stu1");
		User s2 = new User("stu2");
		User s3 = new User("stu3");
		
		cs.addUser("stu1");
		cs.addUser("stu2");
		cs.addUser("stu3");
				
		Group cs01 = new Group("CS356Session01");		
		cs.addSubgroup("CS356Session01");

		User s7 = new User("stu7");
		User s8 = new User("stu8");
		User s9 = new User("stu9");
		
		cs01.addUser("stu7");
		cs01.addUser("stu8");
		cs01.addUser("stu9");
		
		
		
				
		//Group Root = new Group("root");
		
		//Group CS356 = new Group("CS356");
		//Root.addSubgroup("CS356");
		//Group CS356session01 = new Group("CS356Session01");		
		//CS356.addSubgroup("CS356Session01");
		
        frame = new JFrame("Admin View");
        frame.setLayout(new GridLayout());
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        
        DefaultMutableTreeNode john = new DefaultMutableTreeNode("john");
        DefaultMutableTreeNode bob = new DefaultMutableTreeNode("bob");
        DefaultMutableTreeNode steve = new DefaultMutableTreeNode("steve");
        
        root.add(john);
        root.add(bob);
        root.add(steve);
        
        DefaultMutableTreeNode cs356 = new DefaultMutableTreeNode("CS356");
        
        root.add(cs356);
        
        DefaultMutableTreeNode stu1 = new DefaultMutableTreeNode("stu1");
        DefaultMutableTreeNode stu2 = new DefaultMutableTreeNode("stu2");
        DefaultMutableTreeNode stu3 = new DefaultMutableTreeNode("stu3");
        
        cs356.add(stu1);
        cs356.add(stu2);
        cs356.add(stu3);
        
        DefaultMutableTreeNode cs356session01 = new DefaultMutableTreeNode("CS356Session01");
        
        cs356.add(cs356session01);
        
        DefaultMutableTreeNode stu7 = new DefaultMutableTreeNode("stu7");
        DefaultMutableTreeNode stu8 = new DefaultMutableTreeNode("stu8");
        DefaultMutableTreeNode stu9 = new DefaultMutableTreeNode("stu9");
        
        cs356session01.add(stu7);
        cs356session01.add(stu8);
        cs356session01.add(stu9);
        
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        //renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        //renderer.setLeafIcon(renderer.getClosedIcon());        
        //tree.setCellRenderer(renderer);
        
        tree.setCellRenderer(new AdminWindow().new CustomTreeCellRenderer());
        
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            
            public void valueChanged(TreeSelectionEvent e) {
            	if (Database.getGroup(tree.getLastSelectedPathComponent().toString()) == null)
            	{            		
            		currentNode = (DefaultMutableTreeNode) tree.getSelectionPath().getPathComponent(tree.getSelectionPath().getPathCount()-2);
            	}
            	else
            	{
            		currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            	}            
                u.setText(tree.getLastSelectedPathComponent().toString());
                g.setText(tree.getSelectionPath().toString());
            }
        });
        
        //tree.addTreeSelectionListener(this);
        JScrollPane treeView = new JScrollPane(tree);
        frame.add(treeView);
        
        
        JPanel entries = new JPanel();
        GridLayout layout = new GridLayout(2, 3);
        layout.setHgap(10);
        layout.setVgap(10);
        entries.setLayout(layout);
        
        JPanel stats = new JPanel();
        layout = new GridLayout(3, 2);
        //layout = new GridLayout(4, 2);
        layout.setHgap(10);
        layout.setVgap(10);
        stats.setLayout(layout);
        
        u = new JLabel("selected user");
        g = new JLabel("selected group");
        
        ut = new JLabel("nothing displayed");
        gt = new JLabel("nothing displayed");
        mt = new JLabel("nothing displayed");
        
        addUser = new JButton("add user");
        addGroup = new JButton("add group");
        userView = new JButton("open user view");
        
        showUserTotal = new JButton("show user total");
        showGroupTotal = new JButton("show group total");
        showMessageTotal = new JButton("show message total");
        
        addUser.addActionListener(new AdminWindow());
        addGroup.addActionListener(new AdminWindow());
        userView.addActionListener(new AdminWindow());
        
        showUserTotal.addActionListener(new AdminWindow());
        showGroupTotal.addActionListener(new AdminWindow());
        showMessageTotal.addActionListener(new AdminWindow());
        
        userID = new JTextArea(1, 20);
        groupID = new JTextArea(1, 20);   
        
        entries.add(userID);
        entries.add(addUser);
        entries.add(u);
        
        entries.add(groupID);
        entries.add(addGroup);
        entries.add(g);
        
        stats.add(showUserTotal);
        stats.add(ut);
        
        stats.add(showGroupTotal);
        stats.add(gt);
        
        stats.add(showMessageTotal);
        stats.add(mt);
 
        JPanel controls = new JPanel(new BorderLayout(10, 10));
        controls.add(entries, BorderLayout.NORTH);
        controls.add(stats, BorderLayout.SOUTH);
        controls.add(userView, BorderLayout.CENTER);
        
        
        //sp = new JButton("show positive");
        //sp.addActionListener(new AdminWindow());
        //p = new JLabel("nothing displayed");
        //stats.add(sp);
        //stats.add(p);
        
        
        frame.add(controls);
        frame.setSize(800, 600);
 
        frame.setVisible(true);
	}

}
