import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

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
    private Player p1, p2, p3;
	
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
	}
    
    @Test
    public void test()
    {
    	
    }
}