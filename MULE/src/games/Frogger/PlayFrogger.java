package games.Frogger;

import javax.swing.*;
import java.awt.*;

public class PlayFrogger extends JFrame
{
	public PlayFrogger()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new FroggerComponent());
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayFrogger();
	}
}