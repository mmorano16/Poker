//main class, creates card, deck, players, table and runs methods in other classes
import java.util.*;

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
		table[1]=new Player(temp, temp, 100, true, "Player 1", 0, 0, false, false);//sets players hand
		table[2]=new Player(temp, temp, 100, true, "Player 2", 0, 0, false, false);//sets players hand
		table[3]=new Player(temp, temp, 100, true, "Player 3", 0, 0, false, false);//sets players hand
		table[4]=new Player(temp, temp, 100, true, "Player 4", 0, 0, false, false);//sets players hand
		table[5]=new Player(temp, temp, 100, true, "Player 5", 0, 0, false, false);//sets players hand
		table[6]=new Player(temp, temp, 100, true, "Player 6", 0, 0, false, false);//sets players hand
		table[7]=new Player(temp, temp, 100, true, "Player 7", 0, 0, false, false);//sets players hand
		table[8]=new Player(temp, temp, 100, true, "Player 8", 0, 0, false, false);//sets players hand
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
			pot+=m1.move(table, dPos, round, com, sideIndex, sidePots);
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
			System.out.println("The Flop: ");
			for(int i=0;i<pos;i++)
				System.out.print(com[i] + ", ");
			System.out.println();	
			//round of betting
			round++;
			Simple.print(table[0].show());
			pot+=m1.move(table, dPos, round, com, sideIndex, sidePots);
			System.out.println("Pot: " + pot);
			//adds turn card		
			com[pos]=d1.deal();
			pos++;
			System.out.println("\n\n" + "The Turn:");
			for(int i=0;i<pos;i++)
				System.out.print(com[i] + ", "); 
			System.out.println();
			//round of betting
			round++;
			Simple.print(table[0].show());
			pot+=m1.move(table, dPos, round, com, sideIndex, sidePots);
			System.out.println("Pot: " + pot);
			//adds river card
			com[pos]=d1.deal();
			pos++;
			System.out.println("\n\n"+"The River:"); 
			for(int i=0;i<pos;i++)
				System.out.print(com[i] + ", ");
			System.out.println();
			//round of betting
			round++;
			Simple.print(table[0].show());
			pot+=m1.move(table, dPos, round, com, sideIndex, sidePots);
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
							table[i].setHandStrength(9);
							System.out.println("Royal Flush");
							break;
						case 8:
							table[i].setHandStrength(8);
							System.out.println("Striaght Flush " + i);
							break;
						case 7:
							table[i].setHandStrength(7);
							System.out.println("Four of a Kind " + i);
							break;
						case 6:
							table[i].setHandStrength(6);
							System.out.println("Full House " + i);
							break;
						case 5:
							table[i].setHandStrength(5);
							System.out.println("Flush " + i);
							break;
						case 4:
							table[i].setHandStrength(4);
							System.out.println("Straight " + i);
							break;
						case 3:
							table[i].setHandStrength(3);
							System.out.println("Three of a Kind " + i);
							break;
						case 2:
							table[i].setHandStrength(2);
							System.out.println("Two Pair " + i);
							break;
						case 1:
							table[i].setHandStrength(1);
							System.out.println("Pair " + i);
							break;
						case 0:
							table[i].setHandStrength(0);
							System.out.println("High Card " + i);
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
				int sidePlayerCount = 0;
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
				c1.findWinner(mainPlayers, com, pot, false);
			}
			
			for(int i=0;i<table.length;i++)//sets player status and all in to false and sets players with 0 money to out
			{
				table[i].setAllIn(false);
				table[i].setStatus(true);
				table[i].setInSidePot(false);
				if(table[i].getMoney() == 0)
					table[i].setOut(true);
				System.out.println(table[i].toString());
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
			sideIndex.clear();
			//checks to see if game has a winner
			playerCount = 9;
			for(int i=0;i<table.length;i++)
				if(table[i].getOut())
					playerCount--;

			Simple.print("Press Enter to Continue");
			in.nextLine();
		}
		//Player winner = new Player();
		for(Player player : table)
		{
			if(player.getOut() == false)
				winner = player;
		}
		System.out.println(winner.getName() + " Is the winner!");
    }
}
