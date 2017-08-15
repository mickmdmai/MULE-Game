package games.FallDown;

import javax.swing.*;
import java.awt.*;

public class PlayFallDown extends JFrame
{
	public PlayFallDown()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new FallDownComponent());
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayFallDown();
	}
}