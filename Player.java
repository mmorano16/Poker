import java.io.*;

//class to create player object
public class Player implements Serializable
{
	private Card c1, c2;
	private int money, handStrength, bet;
	private boolean status;
	private boolean allIn;
	private String name;
	private boolean out;
	private boolean inSidePot;
	
	public Player(Card newC1, Card newC2, int newMoney, boolean newStatus, String newName, int newHandStrength, 
			int newBet, boolean newAllIn, boolean newOut)
	{
		c1=newC1;//card 1
		c2=newC2;//card 2
		money=newMoney;//players money
		status=newStatus;//player still in hand (true = Not folded)
		name=newName;//player name
		newHandStrength=handStrength;//player handstrength
		bet=newBet;//player bet
		allIn=newAllIn;//is player all in
		out=newOut;//player is bankrupt
		this.inSidePot = false;
	}
	
	public void setMoney(int amount)
	{
		money=amount;
	}
	public int getMoney()
	{
		return money;
	}
	public void setBet(int newBet)
	{
		bet=newBet;
	}
	public int getBet()
	{
		return bet;
	}
	public void setStatus(boolean newStatus)
	{
		status=newStatus;
	}
	public boolean getStatus()
	{
		return status;
	}
	public void setC1(Card newC1)
	{
		c1=newC1;
	}
	public Card getC1()
	{
		return c1;
	}
	public void setC2(Card newC2)
	{
		c2=newC2;
	}
	public Card getC2()
	{
		return c2;
	}
	public void setHandStrength(int newHandStrength)
	{
		handStrength=newHandStrength;
	}
	public int getHandStrength()
	{
		return handStrength;
	}
	public void setHand(Card newC1, Card newC2)
	{
		c1=newC1;
		c2=newC2;
	}
	public void sortHand()//makes card with higher value C1
	{
		Card temp;
		if(c1.getValue()<c2.getValue())
		{
			temp=c1;
			c1=c2;
			c2=temp;
		}
	}
	public String showHand()//concats toString of each card
	{
		String s="";
		s+=c1.toString()+", ";
		s+=c2.toString();
		return s;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public void setAllIn(boolean tf)
	{
		allIn=tf;
	}
	public boolean getAllIn()
	{
		return allIn;
	}
	public boolean getOut()
	{
		return out;
	}
	public void setOut(boolean tf)
	{
		out=tf;
	}
	public void setInSidePot(boolean tf)
	{
		inSidePot = tf;
	}
	public boolean getInSidePot()
	{
		return inSidePot;
	}
	public String show()//returns necessary stats to show player
	{
		return "Hand: " + showHand() + ", Money: " + money;
	}
	public String showHandStrength()//returns hand strength name
	{
		switch(handStrength)
		{
		case 9:
			return "Royal Flush";
		case 8:
			return "Straight Flush";
		case 7:
			return "Four of a Kind";
		case 6:
			return "Full House";
		case 5:
			return "Flush";
		case 4:
			return "Straight";
		case 3:
			return "Three of a Kind";
		case 2:
			return "Two Pair";
		case 1:
			return "Pair";
		default:
			return "High Card";
		}
	}
	public String toString()
	{
		return name + ": Money: " + money + ", Bet: " + bet + ", " + c1 + ", " + c2  + ", Not Fold: " + status + ", Hand: " + handStrength + ", All In: " + allIn + ", Out: " + out;
	}
}