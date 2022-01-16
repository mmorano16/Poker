import java.io.*;


public class Save implements Serializable
{
	private Player[] players;
	private int dPos;
	
	public Save(Player[] players, int dPos)
	{
		this.players = players;
		this.dPos = dPos;
	}
	
	public Player[] getPlayers()
	{
		return players;
	}
	
	public int getDPos()
	{
		return dPos;
	}
}
