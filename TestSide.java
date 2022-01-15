import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

public class TestSide 
{
	private String ranks[]={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    private String suits[]={"Clubs","Spades","Diamonds","Hearts"};
    private int values[]=new int[13];
    private Deck d1;
    
    private Card temp;    
    private Card com[]=new Card[5];
    private Card C2, C3, C4, C5, C6, C7, C8, C9, C10, CJ, CQ, CK, CA;
    private Card S2, S3, S4, S5, S6, S7, S8, S9, S10, SJ, SQ, SK, SA;
    private Card D2, D3, D4, D5, D6, D7, D8, D9, D10, DJ, DQ, DK, DA;
    private Card H2, H3, H4, H5, H6, H7, H8, H9, H10, HJ, HQ, HK, HA;
    private Rank r1=new Rank();
    private Compare c1=new Compare();
    private Move m1=new Move();
    private Player p1, p2, p3, p4, exp;
    private Stack<Side> sidePots = new Stack<Side>();
    private ArrayList<Integer> sideIndex = new ArrayList<Integer>();
	
    @Before
    public void testInitialize() 
	{
		for(int i=0;i<values.length;i++)
	        values[i]=i;
		temp = new Card("temp", "temp", -1);
		d1 = new Deck(ranks, suits, values);
		//deal cards
		C2 = d1.deal(); C3 = d1.deal(); C4 = d1.deal(); C5 = d1.deal(); C6 = d1.deal(); C7 = d1.deal();
		C8 = d1.deal(); C9 = d1.deal(); C10 = d1.deal(); CJ = d1.deal(); CQ = d1.deal(); CK = d1.deal(); CA = d1.deal();
		S2 = d1.deal(); S3 = d1.deal(); S4 = d1.deal(); S5 = d1.deal(); S6 = d1.deal(); S7 = d1.deal();
		S8 = d1.deal(); S9 = d1.deal(); S10 = d1.deal(); SJ = d1.deal(); SQ = d1.deal(); SK = d1.deal(); SA = d1.deal();
		D2 = d1.deal(); D3 = d1.deal(); D4 = d1.deal(); D5 = d1.deal(); D6 = d1.deal(); D7 = d1.deal();
		D8 = d1.deal(); D9 = d1.deal(); D10 = d1.deal(); DJ = d1.deal(); DQ = d1.deal(); DK = d1.deal(); DA = d1.deal();
		H2 = d1.deal(); H3 = d1.deal(); H4 = d1.deal(); H5 = d1.deal(); H6 = d1.deal(); H7 = d1.deal();
		H8 = d1.deal(); H9 = d1.deal(); H10 = d1.deal(); HJ = d1.deal(); HQ = d1.deal(); HK = d1.deal(); HA = d1.deal();
		p1 = new Player(temp, temp, 10, true, "Player 1", 0, 0, false, false);
		p2 = new Player(temp, temp, 20, true, "Player 2", 0, 0, false, false);
		p3 = new Player(temp, temp, 20, true, "Player 3", 0, 0, false, false);
		p4 = new Player(temp, temp, 20, true, "Player 4", 0, 0, false, false);
		exp = new Player(temp, temp, 0, false, "extra player", 0, 0, false, true);
	}
    
    //@Test
    public void test1()
    {
		p1.setC1(temp);p1.setC2(temp);
		p2.setC1(temp);p2.setC2(temp);
		p3.setC1(temp);p3.setC2(temp);
		p4.setC1(temp);p4.setC2(temp);
		com[0] = temp;
		com[1] = temp;
		com[2] = temp;
		com[3] = temp;
		com[4] = temp;
		Player table[] = {p1, p2, p3, p4, exp, exp, exp, exp, exp};
    	int pot = m1.move(table, 0, 0, com, sideIndex, sidePots);
    	ArrayList<Player> expResult = new ArrayList<Player>();
    	expResult.add(p1);
    	expResult.add(p3);
    	expResult.add(p4);
    	Simple.print("Main Pot:" + pot);
    	Simple.print("Side Pot: " + sidePots.get(0).getPot());
    	
    	assertTrue(pot == 10);
    	assertTrue(sidePots.get(0).getPot() == 35);
    	assertArrayEquals(expResult.toArray(), sidePots.get(0).getPlayers().toArray());
    }
    
    @Test
    public void test2()
    {
    	p1.setC1(D9);p1.setC2(temp);
		p2.setC1(temp);p2.setC2(temp);
		p3.setC1(C7);p3.setC2(temp);
		p4.setC1(temp);p4.setC2(temp);
		com[0] = C2;
		com[1] = S3;
		com[2] = H5;
		com[3] = D7;
		com[4] = C9;
		Player table[] = {p1, p2, p3, p4, exp, exp, exp, exp, exp};
    	int pot = m1.move(table, 0, 0, com, sideIndex, sidePots);
    	
    	if(sidePots.size() < 1)
		{
			c1.findWinner(table, com, pot, false);
		}
		else //there are side pots
		{
			int sidePlayerCount = 0;
			ArrayList<Player> tempList = new ArrayList<Player>();
			//find players that are not in any side pots and add to array list
			for(Player player : table)
			{
				if(!player.getInSidePot())
					tempList.add(player);
			}
			//transfer players from array list into new array
			Player mainPlayers[] = new Player[tempList.size()];
			for(int i = 0;i<mainPlayers.length;i++)
			{
				mainPlayers[i] = tempList.get(i);
			}
			//for each side pot transfer players into new array and find a winner for each side pot
			for(Side side : sidePots)
			{
				Player sidePlayers[] = new Player[side.getPlayers().size()];
				for(int i = 0; i < sidePlayers.length; i++)
					sidePlayers[i] = side.getPlayers().get(i);
				
				c1.findWinner(sidePlayers, com, side.getPot(), true);
			}
			//find the winner for the main pot with players not in any side pots
			c1.findWinner(mainPlayers, com, pot, false);
		}
    	assertTrue(p1.getMoney() == 35);
    	assertTrue(p3.getMoney() == 15);
    }
    
    @Test
    public void sortArrayList()
    {
    	p1.setBet(20);
    	p2.setBet(10);
    	p3.setBet(30);
    	p4.setBet(10);
    	exp.setBet(5);
    	ArrayList<Player> players = new ArrayList<Player>();
    	players.add(p1);
    	players.add(p2);
    	players.add(p3);
    	players.add(p4);
    	players.add(exp);
    	ArrayList<Player> results = new ArrayList<Player>();
    	results.add(exp);
    	results.add(p2);
    	results.add(p4);
    	results.add(p1);
    	results.add(p3);
    	m1.sortSidePlayers(players);
    	assertArrayEquals(players.toArray(), results.toArray());
    }
}
