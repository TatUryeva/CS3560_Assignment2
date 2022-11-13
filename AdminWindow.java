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
			Database.getGroup(currentNode.toString()).addUser(userID.getText());
			currentNode.insert(new DefaultMutableTreeNode(Database.getUser(userID.getText())), 0);
            SwingUtilities.updateComponentTreeUI(frame);
        }
        if (s.equals("add group")) 
        {
            g.setText(groupID.getText());
			Database.getGroup(currentNode.toString()).addSubgroup(groupID.getText());
            currentNode.insert(new DefaultMutableTreeNode(Database.getGroup(groupID.getText())), 0);
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
        		//System.out.println(tree.getLastSelectedPathComponent().toString() + "'s user window is opened");
				new UserWindow(tree.getLastSelectedPathComponent().toString());
        	}        	
        }
        
        
        if (s.equals("show positive")) 
        {
            p.setText(Double.toString(Database.getAllUsers().get(0).accept(new Visitor())));
        }
        
        
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
					  DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
					  //r.setLeafIcon(r.getClosedIcon());
					  //System.out.println(this.getClass().toString());
					  this.setIcon(render.getClosedIcon());
				  }
			  }
			  return this;
		  }
	}
	
    private static AdminWindow admin;
	public static AdminWindow instantiateAdminWindow()
	{
		if (admin == null)
		{
			admin = new AdminWindow();
		}		
		return admin;
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
    
	//public static void main(String[] args)
    public AdminWindow()
	{		
		Group r = new Group("root");
		
        frame = new JFrame("Admin View");
        frame.setLayout(new GridLayout());
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        tree.setCellRenderer(new CustomTreeCellRenderer());
        
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
        //layout = new GridLayout(3, 2);
        layout = new GridLayout(4, 2);
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
        
        addUser.addActionListener(this);
        addGroup.addActionListener(this);
        userView.addActionListener(this);
        
        showUserTotal.addActionListener(this);
        showGroupTotal.addActionListener(this);
        showMessageTotal.addActionListener(this);
        
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
        
        
        sp = new JButton("show positive");
        sp.addActionListener(this);
        p = new JLabel("nothing displayed");
        stats.add(sp);
        stats.add(p);
        
        
        frame.add(controls);
        frame.setSize(800, 600);
 
        frame.setVisible(true);
	}

}
