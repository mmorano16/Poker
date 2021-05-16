//class for sidepot
import java.util.ArrayList;

public class Side
{
	private int pot;
	private int count;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public Side(Player p)
	{
		players.add(p);
	}
	public int getPot()
	{
		return pot;
	}
}