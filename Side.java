//class for sidepot
import java.util.ArrayList;
import java.util.Stack;

public class Side
{
	private int pot;
	private ArrayList<Player> players;
	
	public Side(ArrayList<Player> players, int pot)
	{
		this.players = players;
		this.pot = pot;
	}
	public int getPot()
	{
		return pot;
	}
	
	public void setPot(int pot)
	{
		this.pot = pot;
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
}