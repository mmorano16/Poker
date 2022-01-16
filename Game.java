//main class, creates card, deck, players, table and runs methods in other classes
import java.util.*;
import java.io.*;

public class Game
{
    public static void main(String[] args)
    {
		Scanner in=new Scanner(System.in);
		
        String ranks[]={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        String suits[]={"Clubs","Spades","Diamonds","Hearts"};
		Card hand[]=new Card[2];
        int values[]=new int[13];
		Card com[]=new Card[5];//community cards
		Player table[]=new Player[9];//table of players
		Player  winner=table[0];
		int pos=0, dPos=0, pot=0, round=0, turn=0, bet=0, highBet=0, playerCount=9;
		Card temp=new Card("temp", "temp", -1);
		ArrayList<Integer> sideIndex;
		Stack<Side> sidePots;
		
		for(int i=0;i<values.length;i++)
            values[i]=i;
		
        Deck d1=new Deck(ranks, suits, values);
		Rank r1=new Rank();
		Compare c1=new Compare();
		Move m1=new Move();
        d1.shuffle();
		//		      card, card, money, notFold, name, handStrenght, betValue, allIn, bankrupt
		table[0]=new Player(temp, temp, 100, true, "Me", 0, 0, false, false);//sets players hand
		table[1]=new Player(temp, temp, 100, true, "Player 2", 0, 0, false, false);//sets players hand
		table[2]=new Player(temp, temp, 100, true, "Player 3", 0, 0, false, false);//sets players hand
		table[3]=new Player(temp, temp, 100, true, "Player 4", 0, 0, false, false);//sets players hand
		table[4]=new Player(temp, temp, 100, true, "Player 5", 0, 0, false, false);//sets players hand
		table[5]=new Player(temp, temp, 100, true, "Player 6", 0, 0, false, false);//sets players hand
		table[6]=new Player(temp, temp, 100, true, "Player 7", 0, 0, false, false);//sets players hand
		table[7]=new Player(temp, temp, 100, true, "Player 8", 0, 0, false, false);//sets players hand
		table[8]=new Player(temp, temp, 100, true, "Player 9", 0, 0, false, false);//sets players hand
		
		Simple.print("Welcome to CLI Poker V1" + "\n" + "Press Enter to Begin or Type 'Load' to Load a Previously Saved Game");
		if(in.nextLine().equals("Load"))
		{
			Save save = loadGame();
			table = save.getPlayers();
			dPos = save.getDPos();
			for(Player player : table)
				System.out.println(player.getName() + player.getMoney());
			Simple.print("Dealer Position: " + dPos);
		}
		Simple.print("Would you like to rename players? Y/N");
		if(in.next().equals("Y"))
		{
			renamePlayers(table);
		}
		//add while loop for continuous game here
		while(playerCount > 1)
		{
			//reset side pot list and stack for new hand
			sideIndex = new ArrayList<Integer>();
			sidePots = new Stack<Side>();
			
			pos = 0; pot = 0;
			for(int i=0;i<table.length;i++)//deals first cards
				table[i].setC1(d1.deal());
			for(int i=0;i<table.length;i++)//deals second cards 
				table[i].setC2(d1.deal());		
			
//			for(int i=0;i<table.length;i++)
//				System.out.println(table[i].toString());
			Simple.print(table[0].show());
			//round of betting
			pot=m1.move(table, dPos, round, pot, com, sideIndex, sidePots);
			sideIndex.clear();
			System.out.println("Pot: " + pot);
//			for(int i=0;i<table.length;i++)
//				System.out.println(table[i].toString());
			System.out.println();
			//adds flop cards
			com[pos]=d1.deal();
			pos++;
			com[pos]=d1.deal();
			pos++;
			com[pos]=d1.deal();
			pos++;
			System.out.println("\n\n" + "The Flop: ");
			for(int i=0;i<pos;i++)
				System.out.print(com[i] + ", ");
			System.out.println("\n");	
			//round of betting
			round++;
			Simple.print(table[0].show());
			pot=m1.move(table, dPos, round, pot, com, sideIndex, sidePots);
			sideIndex.clear();
			System.out.println("Pot: " + pot);
			//adds turn card		
			com[pos]=d1.deal();
			pos++;
			System.out.println("\n\n" + "The Turn:");
			for(int i=0;i<pos;i++)
				System.out.print(com[i] + ", "); 
			System.out.println("\n");
			//round of betting
			round++;
			Simple.print(table[0].show());
			pot=m1.move(table, dPos, round, pot, com, sideIndex, sidePots);
			sideIndex.clear();
			System.out.println("Pot: " + pot);
			//adds river card
			com[pos]=d1.deal();
			pos++;
			System.out.println("\n\n"+"The River:"); 
			for(int i=0;i<pos;i++)
				System.out.print(com[i] + ", ");
			System.out.println("\n");
			//round of betting
			round++;
			Simple.print(table[0].show());
			pot=m1.move(table, dPos, round, pot, com, sideIndex, sidePots);
			sideIndex.clear();
			System.out.println("Pot: " + pot);
			
//			for(int i=0;i<table.length;i++)
//				System.out.println(table[i].toString());
			System.out.println();
			for(int i=0;i<table.length;i++)//finds hand strength for each player
			{
				if(table[i].getStatus()==true)
				{
					switch(r1.rankHand(table[i].getC1(), table[i].getC2(), com))//finds each players hand strength
					{
						case 9:
							table[i].setHandStrength(9);//royal flush
							break;
						case 8:
							table[i].setHandStrength(8);//straight flush
							break;
						case 7:
							table[i].setHandStrength(7);//four of a kind
							break;
						case 6:
							table[i].setHandStrength(6);//full house
							break;
						case 5:
							table[i].setHandStrength(5);//flush
							break;
						case 4:
							table[i].setHandStrength(4);//straight
							break;
						case 3:
							table[i].setHandStrength(3);//three of a kind
							break;
						case 2:
							table[i].setHandStrength(2);//two pair
							break;
						case 1:
							table[i].setHandStrength(1);//pair
							break;
						case 0:
							table[i].setHandStrength(0);//high card
							break;
					}
				}
			}
			if(sidePots.size() < 1)
			{
				c1.findWinner(table, com, pot, false);
			}
			else //there are side pots
			{
				ArrayList<Player> tempList = new ArrayList<Player>();
				//find players that are not in any side pots and add to array list
				for(Player player : table)
				{
					if(!player.getInSidePot())
						tempList.add(player);
				}
				//transfer players from arraylist into new array
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
				if(mainPlayers.length > 1)
				{
					c1.findWinner(mainPlayers, com, pot, false);
				}
				else //if there is only one player not in a side pot
				{	 //might not be possible but just in case
//					System.out.print(winner.getName() + " wins.");
//					System.out.print(".");
//					Simple.print("\n");
					winner.setMoney(winner.getMoney()+pot);
				}
			}
			Simple.print("Community Cards:");
			for(Card card : com)
			{
				System.out.print(card.toString() + ", ");
			}
			Simple.print("\n");
			for(int i=0;i<table.length;i++)//sets player status and all in to false and sets players with 0 money to out
			{
				if(table[i].getStatus())
				{
					Simple.print(table[i].getName() + ", " + table[i].show() + ", " + table[i].showHandStrength());
				}
				else
				{
					Simple.print(table[i].getName() + ", Folded, Money:" + table[i].getMoney());
				}
				table[i].setAllIn(false);
				table[i].setStatus(true);
				table[i].setInSidePot(false);
				if(table[i].getMoney() == 0)
					table[i].setOut(true);
			}
			d1=new Deck(ranks, suits, values);//resets deck
			d1.shuffle();
			round=0;
			dPos++;
			dPos = dPos % 9;
			while(table[dPos].getOut())
			{
				dPos++;
				dPos %= 9;
			}
			//checks to see if game has a winner
			playerCount = 9;
			for(int i=0;i<table.length;i++)
				if(table[i].getOut())
					playerCount--;
			if(!table[0].getOut())
			{
				Simple.print("Press Enter to Continue or Type 'Save' to Save Your Game");
				if(in.next().equals("Save"))
				{
					saveGame(table, dPos);
				}
			}
		}
		//Player winner = new Player();
		for(Player player : table)
		{
			if(player.getOut() == false)
				winner = player;
		}
		System.out.println("\n" + winner.getName() + " Is the winner!");
    }

	private static void saveGame(Player[] table, int dPos) 
	{
		Scanner in = new Scanner(System.in);
		Save save = new Save(table, dPos);
		String fileName = "file.ser";
		
		//Serialization
		try
		{
			//Saving of object in a file
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			//Method for serialization of object
			out.writeObject(save);
			
			out.close();
			file.close();
			Simple.print("Game Saved" + "\n" + "Press Enter to Continue");
			in.nextLine();
		}
		catch(IOException e)
		{
			System.out.println("IOException is caught" + "\n" + e);
			Simple.print("Something went wrong. Game not saved");
			Simple.print("Press Enter to Continue");
			in.nextLine();
		}
	}

	private static Save loadGame() 
	{
		Scanner in = new Scanner(System.in);
		Save save = null;
		String fileName = "file.ser";
		
		try
		{
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream input = new ObjectInputStream(file);
			
			save = (Save)input.readObject();
			
			input.close();
			file.close();

			Simple.print("Game Loaded" + "\n" + "Press Enter to Continue");
			in.nextLine();
		}
		catch(IOException | ClassNotFoundException e)
		{
			Simple.print("Something went wrong. Game not loaded" + "\n" + e);
			Simple.print("Press Enter to Continue");
			in.nextLine();
		}
		
		
		
		return save;
	}

	private static void renamePlayers(Player[] table) 
	{
		Scanner in = new Scanner(System.in);
		for(Player player : table)
		{
			Simple.print("Current Name: " + player.getName());
			Simple.print("Enter New Name:");
			player.setName(in.next());
		}
		
	}
}
