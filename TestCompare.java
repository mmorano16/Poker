import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestCompare
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
	public void highCard_P1_True()
	{
		p1.setC1(DA);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				p1.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_P1_False()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 110);
			}
		}
	}
	
	@Test
	public void highCard_P2_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 110);
				p2.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_P2_False()
	{
		p1.setC1(D10);p1.setC2(CK);
		p2.setC1(D4);p2.setC2(H10);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p2.getMoney() == 110);
			}
		}
	}
	
	@Test
	public void highCard_P3_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p3.getMoney() == 110);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_P3_False()
	{
		p1.setC1(D10);p1.setC2(CK);
		p2.setC1(D4);p2.setC2(H10);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p3.getMoney() == 110);
			}
		}
	}
	
	@Test
	public void highCard_Tie12_True()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D3);p2.setC2(HA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_Tie12_False()
	{
		p1.setC1(D3);p1.setC2(CK);
		p2.setC1(D3);p2.setC2(HA);
		p3.setC1(C4);p3.setC2(SA);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_Tie13_True()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D3);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_Tie13_False()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D3);p2.setC2(HA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_Tie23_True()
	{
		p1.setC1(D3);p1.setC2(CK);
		p2.setC1(D3);p2.setC2(HA);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_Tie23_False()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D3);p2.setC2(HA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void highCard_3Tie_True()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D3);p2.setC2(HA);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 9);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}

	@Test
	public void pair_P1_True()
	{
		p1.setC1(DA);p1.setC2(C7);
		p2.setC1(D4);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S7;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_P1_False()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_P2_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_P2_False()
	{
		p1.setC1(D10);p1.setC2(CK);
		p2.setC1(D4);p2.setC2(H10);
		p3.setC1(C4);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_P3_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_P3_False()
	{
		p1.setC1(D10);p1.setC2(C6);
		p2.setC1(D4);p2.setC2(H10);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_Tie12_True()
	{
		p1.setC1(D3);p1.setC2(C6);
		p2.setC1(D3);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_Tie12_False()
	{
		p1.setC1(D3);p1.setC2(CK);
		p2.setC1(D3);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(S6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_Tie13_True()
	{
		p1.setC1(D3);p1.setC2(C6);
		p2.setC1(D3);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_Tie13_False()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D3);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_Tie23_True()
	{
		p1.setC1(D3);p1.setC2(CK);
		p2.setC1(D3);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_Tie23_False()
	{
		p1.setC1(D3);p1.setC2(C6);
		p2.setC1(D3);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void pair_3Tie_True()
	{
		p1.setC1(D3);p1.setC2(C6);
		p2.setC1(D3);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 9);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}

	@Test
	public void twoPair_P1_True()
	{
		p1.setC1(D5);p1.setC2(C7);
		p2.setC1(D5);p2.setC2(H2);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S7;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_P1_False()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(CQ);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_P2_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C2);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_P2_False()
	{
		p1.setC1(D10);p1.setC2(CK);
		p2.setC1(D4);p2.setC2(H10);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_P3_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D5);p2.setC2(H2);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_P3_False()
	{
		p1.setC1(D10);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H10);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D2;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_Tie12_True()
	{
		p1.setC1(D5);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C2);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = H2;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_Tie12_False()
	{
		p1.setC1(D3);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(S6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_Tie13_True()
	{
		p1.setC1(D5);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H2);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = H2;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_Tie13_False()
	{
		p1.setC1(D3);p1.setC2(CA);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_Tie23_True()
	{
		p1.setC1(D5);p1.setC2(C2);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = H2;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_Tie23_False()
	{
		p1.setC1(D5);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void twoPair_3Tie_True()
	{
		p1.setC1(D5);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 9);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}	

	@Test
	public void threePair_P1_True()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(H2);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S7;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_P1_False()
	{
		p1.setC1(D5);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_P2_True()
	{
		p1.setC1(D5);p1.setC2(C3);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_P2_False()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D4);p2.setC2(H5);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_P3_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(H5);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_P3_False()
	{
		p1.setC1(D10);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S6;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_Tie12_True()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(C2);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_Tie12_False()
	{
		p1.setC1(D5);p1.setC2(C2);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(SK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_Tie13_True()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D3);p2.setC2(H5);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_Tie13_False()
	{
		p1.setC1(D3);p1.setC2(C5);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_Tie23_True()
	{
		p1.setC1(D3);p1.setC2(C5);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_Tie23_False()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C5);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void threePair_3Tie_True()
	{
		p1.setC1(D5);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 9);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}	

	@Test
	public void fourPair_P1_True()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(H2);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void FourPair_P1_False()
	{
		p1.setC1(D5);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_P2_True()
	{
		p1.setC1(D5);p1.setC2(C3);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_P2_False()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D4);p2.setC2(H5);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p2.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_P3_True()
	{
		p1.setC1(D10);p1.setC2(C3);
		p2.setC1(D4);p2.setC2(H5);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_P3_False()
	{
		p1.setC1(D10);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(C8);
		com[0] = S5;
		com[1] = D9;
		com[2] = D5;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_Tie12_True()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(C2);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_Tie12_False()
	{
		p1.setC1(D5);p1.setC2(C2);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(SK);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertFalse(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_Tie13_True()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D3);p2.setC2(H5);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_Tie13_False()
	{
		p1.setC1(D3);p1.setC2(C5);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_Tie23_True()
	{
		p1.setC1(D3);p1.setC2(C5);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C5);p3.setC2(CK);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_Tie23_False()
	{
		p1.setC1(D5);p1.setC2(CK);
		p2.setC1(D5);p2.setC2(HK);
		p3.setC1(C4);p3.setC2(C5);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fourPair_3Tie_True()
	{
		p1.setC1(D5);p1.setC2(C6);
		p2.setC1(D5);p2.setC2(H6);
		p3.setC1(C5);p3.setC2(C6);
		com[0] = S5;
		com[1] = D5;
		com[2] = D10;
		com[3] = S5;
		com[4] = HQ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 9);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_P1_True()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C9);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_P1_False()
	{
		p1.setC1(C4);p1.setC2(C9);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_P2_True()
	{
		p1.setC1(C4);p1.setC2(C9);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_P2_False()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C9);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_P3_True()
	{
		p1.setC1(C4);p1.setC2(C9);
		p2.setC1(C4);p2.setC2(C9);
		p3.setC1(C4);p3.setC2(C7);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_P3_False()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C9);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_Tie12_True()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_Tie12_False()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C9);
		p3.setC1(C4);p3.setC2(C7);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_Tie13_True()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C9);
		p3.setC1(C4);p3.setC2(C7);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_Tie13_False()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_Tie23_True()
	{
		p1.setC1(C4);p1.setC2(C9);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C7);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_Tie23_False()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C9);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_3Tie_True()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C7);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void straight_3Tie_False()
	{
		p1.setC1(C4);p1.setC2(C7);
		p2.setC1(C4);p2.setC2(C7);
		p3.setC1(C4);p3.setC2(C7);
		com[0] = S2;
		com[1] = C3;
		com[2] = HK;
		com[3] = D5;
		com[4] = S6;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_P1_True()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CK);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_P1_False()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_P2_True()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_P2_False()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CK);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_P3_True()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CK);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_P3_False()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_Tie12_True()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_Tie12_False()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CK);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_Tie13_True()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CK);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_Tie13_False()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_Tie23_True()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_Tie23_False()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CK);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 105);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_3Tie_True()
	{
		p1.setC1(C4);p1.setC2(CA);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 103);
				assertTrue(p2.getMoney() == 103);
				assertTrue(p3.getMoney() == 103);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void flush_3Tie_False()
	{
		p1.setC1(C4);p1.setC2(CK);
		p2.setC1(C4);p2.setC2(CA);
		p3.setC1(C4);p3.setC2(CA);
		com[0] = C2;
		com[1] = C6;
		com[2] = C4;
		com[3] = S9;
		com[4] = SJ;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 105);
				assertTrue(p3.getMoney() == 105);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P1_Com_True()
	{
		p1.setC1(SA);p1.setC2(S2);
		p2.setC1(S2);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = HK;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P1_Hand_True()
	{
		p1.setC1(SA);p1.setC2(S2);
		p2.setC1(SK);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = H2;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 110);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P1_Com_False()
	{
		p1.setC1(S2);p1.setC2(S2);
		p2.setC1(SA);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = HK;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P1_Hand_False()
	{
		p1.setC1(SK);p1.setC2(S2);
		p2.setC1(SA);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = H2;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P2_Com_True()
	{
		p1.setC1(S2);p1.setC2(S2);
		p2.setC1(SA);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = HK;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P2_Hand_True()
	{
		p1.setC1(SK);p1.setC2(S2);
		p2.setC1(SA);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = H2;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P2_Com_False()
	{
		p1.setC1(S2);p1.setC2(S2);
		p2.setC1(S2);p2.setC2(S2);
		p3.setC1(SA);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = HK;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P2_Hand_False()
	{
		p1.setC1(SK);p1.setC2(S2);
		p2.setC1(S2);p2.setC2(S2);
		p3.setC1(SA);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = H2;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P3_Com_True()
	{
		p1.setC1(S2);p1.setC2(S2);
		p2.setC1(S2);p2.setC2(S2);
		p3.setC1(SA);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = HK;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P3_Hand_True()
	{
		p1.setC1(SK);p1.setC2(S2);
		p2.setC1(S2);p2.setC2(S2);
		p3.setC1(SA);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = H2;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 100);
				assertTrue(p3.getMoney() == 110);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	

	@Test
	public void fullHouse_P3_Com_False()
	{
		p1.setC1(S2);p1.setC2(S2);
		p2.setC1(SA);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = HK;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
	
	@Test
	public void fullHouse_P3_Hand_False()
	{
		p1.setC1(SK);p1.setC2(S2);
		p2.setC1(SA);p2.setC2(S2);
		p3.setC1(S2);p3.setC2(S2);
		com[0] = HK;
		com[1] = HK;
		com[2] = H2;
		com[3] = CA;
		com[4] = CA;
		Player table[] = {p1, p2, p3};
		for(int j=0;j<5;j++)
		{
			for(int k=0;k<=5;k++)
			{
				Card temp = com[j];
				com[j] = com[(k+1)%5];
				com[(k+1)%5] = temp;
				for(int i=0;i<table.length;i++)
					table[i].setHandStrength(r1.rankHand(table[i].getC1(), table[i].getC2(), com));
				c1.findWinner(table, com, 10);
				assertTrue(p1.getMoney() == 100);
				assertTrue(p2.getMoney() == 110);
				assertTrue(p3.getMoney() == 100);
				p1.setMoney(100);
				p2.setMoney(100);
				p3.setMoney(100);
			}
		}
	}
}
