import javax.swing.JFrame;
import javax.swing.JLabel;

public class ValidationDialog extends JFrame
{
	static JFrame frame;
	static JLabel notification;
	
	//public static void main(String[] args)
	public ValidationDialog(String message)
	{
		frame = new JFrame("Validation Dialog");
		
		//notification = new JLabel("some of the entries contain spaces and/or are identical to some other entry/entries.");
		notification = new JLabel(message);
		notification.setHorizontalAlignment(JLabel.CENTER);
		
		frame.add(notification);
		
		frame.setSize(600, 200);		 
        frame.setVisible(true);
	}

}