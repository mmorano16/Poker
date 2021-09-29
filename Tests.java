import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class Tests 
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
    private Player p1, p2, p3, p4, p5, p6, p7, p8, p9;
    
    
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
		p1 = new Player(temp, temp, 100, true, "Player 1", 0, 0, false, false);
		p2 = new Player(temp, temp, 100, true, "Player 2", 0, 0, false, false);
		p3 = new Player(temp, temp, 100, true, "Player 3", 0, 0, false, false);
		p4 = new Player(temp, temp, 100, true, "Player 4", 0, 0, false, false);
		p5 = new Player(temp, temp, 100, true, "Player 5", 0, 0, false, false);
		p6 = new Player(temp, temp, 100, true, "Player 6", 0, 0, false, false);
		p7 = new Player(temp, temp, 100, true, "Player 7", 0, 0, false, false);
		p8 = new Player(temp, temp, 100, true, "Player 8", 0, 0, false, false);
		p9 = new Player(temp, temp, 100, true, "Player 8", 0, 0, false, false);
	}

	@Test
	public void highCard_Hand_True()
	{
		p1.setC1(CA);
		p1.setC2(S7);
		com[0] = C3;
		com[1] = S5;
		com[2] = H9;
		com[3] = HJ;
		com[4] = SK;
		assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 0);
	}
	
	@Test
	public void highCard_Hand_False()
	{
		p1.setC1(CA);
		p1.setC2(S7);
		com[0] = C3;
		com[1] = S5;
		com[2] = H9;
		com[3] = HJ;
		com[4] = SA;
		assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 0);
	}
	
	@Test
	public void highCard_Com_True()
	{
		p1.setC1(C4);
		p1.setC2(S7);
		com[0] = CK;
		com[1] = S5;
		com[2] = H9;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			Card temp = com[i];
			com[i] = com[(i+1)%5];
			com[(i+1)%5] = temp;
			assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 0);
		}
	}
	
	@Test
	public void highCard_Com_False()
	{
		p1.setC1(C4);
		p1.setC2(S9);
		com[0] = CK;
		com[1] = S5;
		com[2] = H9;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			Card temp = com[i];
			com[i] = com[(i+1)%5];
			com[(i+1)%5] = temp;
			assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 0);
		}
	}
	
	@Test
	public void pair_Pocket_True()
	{
		p1.setC1(C4);
		p1.setC2(S4);
		com[0] = CK;
		com[1] = S5;
		com[2] = H9;
		com[3] = HJ;
		com[4] = S3;
		assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 1);
	}
	
	@Test
	public void pair_Pocket_False()
	{
		p1.setC1(C4);
		p1.setC2(S7);
		com[0] = CK;
		com[1] = S5;
		com[2] = H9;
		com[3] = HJ;
		com[4] = S3;
		assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 1);
	}
	
	@Test
	public void pair_Hand_True()
	{
		p1.setC1(C4);
		p1.setC2(S7);
		com[0] = CK;
		com[1] = S5;
		com[2] = H7;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			Card temp = com[i];
			com[i] = com[(i+1)%5];
			com[(i+1)%5] = temp;
			assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 1);
		}
	}
	
	@Test
	public void pair_Hand_False()
	{
		p1.setC1(C4);
		p1.setC2(S6);
		com[0] = CK;
		com[1] = S5;
		com[2] = H7;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			Card temp = com[i];
			com[i] = com[(i+1)%5];
			com[(i+1)%5] = temp;
			assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 1);
		}
	}
	
	@Test
	public void pair_Com_True()
	{
		p1.setC1(C4);
		p1.setC2(S8);
		com[0] = CK;
		com[1] = S5;
		com[2] = H3;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 1);
			}
		}
	}
	
	@Test
	public void pair_Com_False()
	{
		p1.setC1(C4);
		p1.setC2(S8);
		com[0] = CK;
		com[1] = S5;
		com[2] = H3;
		com[3] = HJ;
		com[4] = SQ;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 1);
			}
		}
	}
	
	@Test
	public void twoPair_Pocket_True()
	{
		p1.setC1(C4);
		p1.setC2(S4);
		com[0] = CK;
		com[1] = S5;
		com[2] = H3;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}	
	}
	
	@Test
	public void twoPair_Pocket_False()
	{
		p1.setC1(C4);
		p1.setC2(S9);
		com[0] = CK;
		com[1] = S5;
		com[2] = H3;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}	
	}

	@Test
	public void twoPair_2HandCom_True()
	{
		p1.setC1(C4);
		p1.setC2(S5);
		com[0] = CK;
		com[1] = S5;
		com[2] = H4;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}	
	}	
	
	@Test
	public void twoPair_2HandCom_False()
	{
		p1.setC1(C4);
		p1.setC2(S2);
		com[0] = CK;
		com[1] = S5;
		com[2] = H4;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}	
	}
	
	@Test
	public void twoPair_1HandCom_True()
	{
		p1.setC1(C4);
		p1.setC2(S2);
		com[0] = CK;
		com[1] = S3;
		com[2] = H4;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}	
	}
	
	@Test
	public void twoPair_1HandCom_False()
	{
		p1.setC1(C4);
		p1.setC2(S2);
		com[0] = CK;
		com[1] = S7;
		com[2] = H4;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}	
	}
		
	@Test
	public void twoPair_Com_True()
	{
		p1.setC1(C4);
		p1.setC2(S2);
		com[0] = C5;
		com[1] = S5;
		com[2] = H3;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertTrue(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}
	}
	
	@Test
	public void twoPair_Com_False()
	{
		p1.setC1(C4);
		p1.setC2(S2);
		com[0] = C5;
		com[1] = S5;
		com[2] = H8;
		com[3] = HJ;
		com[4] = S3;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[i];
				com[i] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				assertFalse(r1.rankHand(p1.getC1(), p1.getC2(), com) == 2);
			}
		}
	}
	
	@Test
	public void findWinnerCustomTest()
	{
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3, p4, p5, p6, p7, p8, p9};
		p1.setC1(DJ);p1.setC2(CK);p1.setOut(true);
		p2.setC1(DK);p2.setC2(H3);
		p3.setC1(C4);p3.setC2(C8);p3.setOut(true);
		p4.setC1(HJ);p4.setC2(H5);
		p5.setC1(D10);p5.setC2(D6);
		p6.setC1(C3);p6.setC2(SA);p6.setOut(true);
		p7.setC1(H4);p7.setC2(S10);p7.setOut(true);
		p8.setC1(C7);p8.setC2(D4);p8.setOut(true);
		p9.setC1(SK);p9.setC2(S8);
		for(int i=0;i<9;i++)
			table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
		c1.findWinner(table, com, 10);
		assertTrue(p5.getMoney() == 110);
	}
	
	
	
	
}	
	
	









