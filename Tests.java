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
    private Rank r1=new Rank();
    private Compare c1=new Compare();
    private Move m1=new Move();
    private Player p1;
    private Player p2;
    
	@Before
	public void testInitialize() 
	{
		for(int i=0;i<values.length;i++)
	        values[i]=i;
		temp = new Card("temp", "temp", -1);
		d1 = new Deck(ranks, suits, values);
		p1 = new Player(temp, temp, 100, true, "Player 1", 0, 0, false, false);
		p2 = new Player(temp, temp, 100, true, "Player 2", 0, 0, false, false);
	}
	
	@Test
	public void test()
	{
		assertEquals(2, 1+1);
	}
}