import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

//javac.exe Concentration.java Deck.java
//java.exe Concentration

public class Concentration extends JFrame implements ActionListener {  

	private boolean[] flipped;
	private JButton[][] mybuttons;
	private Deck mydeck;
	
	private JLabel infolabel; 
	private int   paircount;
	private int   trycount;
	private int   clickcount;
	private int[] clicksave;
	
	Concentration() {
		
		super("CONCENTRATION CARD GAME");  
		
		paircount = 0;
		trycount = 0;
		clickcount = 0;
		clicksave = new int[2];
		
		mydeck = new Deck();
		mydeck.Shuffle();
		
		flipped = new boolean[52];
		mybuttons = new JButton[4][13];
		int xx;
		int yy = 30;
		int buttonindex = 0;
		
		infolabel = new JLabel("Tries:0");
		add(infolabel);
		infolabel.setBounds(10, 0, 200, 30);
		for (int row=0; row < 4; row++)
		{
			xx = 10;
			for (int col=0; col < 13; col++)
			{
				mybuttons[row][col] = new JButton();
				mybuttons[row][col].setActionCommand(Integer.toString(buttonindex));
				buttonindex++;
				mybuttons[row][col].setBounds(xx, yy, 60, 60);
				mybuttons[row][col].addActionListener(this);
				add(mybuttons[row][col]);
				xx += 70;
			}
			yy += 70;
		}
		
		for (int ii=0; ii < 52; ii++)
			flipped[ii] = false;
    
		setSize(940,350);  
		setLayout(null);  
		setVisible(true);  
	}
  
	public void actionPerformed(ActionEvent e) {  
		
		Object myobj = e.getSource();
		JButton mybutton = (JButton)myobj;
		String actstr = mybutton.getActionCommand();
		int ii = Integer.parseInt(actstr);
		
		clickcount++;
		
		if (clickcount == 1) {
			if (flipped[ii] == false) {
				clicksave[0] = ii;
				flipped[ii] = true;
				String cardstr = mydeck.GetCardString(ii);
				mybutton.setText(cardstr);
			}
			else //that means flipped[ii] is true, this should be an impossible state in this state machine, 1st clicks mean no cards are showing
				clickcount = 0;
		}
		else if (clickcount == 2) {
			if (flipped[ii] == false) {
				clicksave[1] = ii;
				flipped[ii] = true;
				String cardstr = mydeck.GetCardString(ii);
				mybutton.setText(cardstr);
			}
			else //clicked on 1st already flipped card so just do nothing but reset clickcount back to 1
				clickcount = 1;
		}
		else { //clickcount==3 only can click on already flipped over card
		
			if (flipped[ii]) {
				boolean pairresult = mydeck.IsPair(clicksave[0], clicksave[1]);
				if (pairresult == false) //2 cards already flipped and clickcount is 3 and not matching, then we flip both cards over
				{
					for (int cc=0; cc < 2; cc++) {
						int target = clicksave[cc];
						flipped[target] = false;
						int row = target / 13;
						int col = target % 13;
						mybuttons[row][col].setText("");
					}
				}
				else {
					for (int cc=0; cc < 2; cc++) {
						int target = clicksave[cc];
						int row = target / 13;
						int col = target % 13;
						remove(mybuttons[row][col]);
						paircount++;
						repaint();
					}
				}
				clickcount = 0;
				trycount++;
			}
			else //3rd click is on non-flipped card which is not allowed
				clickcount = 2;
		}
		
		String infostr = String.format("Tries:%d  click:%d", trycount, clickcount);
		infolabel.setText(infostr);


		//congrats code  
		if(paircount == 52)
			JOptionPane.showMessageDialog(this,"Congratulations! You won.");
	}  
	
	public static void main(String[] args) {  
		new Concentration();  
	}  
}  