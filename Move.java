//will need to add condition for when player money is less than current bet
//create sidepot
//add instance for if players are out of money

//determines order for player moves and amounts bet/won
import java.util.*;

public class Move
{
	public int move(Player[] table, int dPos, int round, Card[] com)
	{//pre:recieves tables of players and decides whose turn it is
	//post: returns the value of the pot after the round of betting, sets status, bet and money appropriately 	
		int count=0, bPos=-1, highBet=0, pot=0, bet=0, sideAmount = 0, sideCount = 0;
		boolean sideCreated = false;
		ArrayList<Integer> sideIndex = new ArrayList<Integer>();
		Stack<Side> sidePots = new Stack<Side>();
		
		for(int i=0;i<9;i++)//sets status folded for players who are bankrupt 
			if(table[i].getOut()==true)
				table[i].setStatus(false);
			
		while(count!=table.length)
		{
			bet=0;
			if((dPos+count)%9==0 && table[0].getStatus()==true)//if player is dealer
			{
				bet=playerMove(table[0], highBet);
				if(bet>highBet)
				{
					highBet=bet;
					bPos=0;
					count=0;
				}
			}
			else if(bPos==-1)//if no one has bet
			{
				if(table[(dPos+count)%9].getStatus()==true && table[(dPos+count)%9].getOut()==false)//if player is still in
				{
					bet=opponentMove(table[(dPos+count)%9], round, com, highBet);
					if(bet>0)
					{
						bPos=(dPos+count)%9;
						count=0;
						highBet=bet;
					}
				}
			}
			else//someone has bet
			{
				if((bPos+count)%9==0 && table[0].getStatus()==true)//players turn
				{
					bet=playerMove(table[0], highBet);
					if(bet>highBet)
					{
						highBet=bet;
						bPos=0;
						count=0;
					}
				}
				else if(table[(bPos+count)%9].getStatus()==true && table[(bPos+count)%9].getOut()==false)//opponents turn
				{				
					bet=opponentMove(table[(bPos+count)%9], round, com, highBet);
					if(bet>highBet)
					{
						bPos=(bPos+count)%9;
						count=0;
						highBet=bet;
					}
				}
			}
			count++;
		}
		
		int index = 0;
		for(Player player : table)
		{
			if(player.getAllIn())
			{
				sideCount++;
				sideIndex.add(index);
			}
			index++;
		}

		if(sideCount == 1)
		{
			ArrayList<Player> players = new ArrayList<Player>();
			int sidePot = 0;
			
			table[sideIndex.get(0)].setInSidePot(true);
			sideAmount = table[sideIndex.get(0)].getBet();
			players.add(table[sideIndex.get(0)]);
			for(Player player : table)
			{
				if(player.getStatus() && !player.getInSidePot())
				{
					players.add(player);
				}
				
				if(player.getBet() >= sideAmount)
				{
					sidePot += sideAmount;
					player.setBet(player.getBet() - sideAmount);
				}
				else
				{
					sidePot += sideAmount;
					player.setBet(0);
				}
			}
			sidePots.add(new Side(players, sidePot));
		}
		highBet=0;
		for(int i=0;i<table.length;i++)//subtracts bets from player money
			table[i].setMoney(table[i].getMoney()-table[i].getBet());
		for(int i=0;i<table.length;i++)//adds bets to pot and resets bets
		{
			pot+=table[i].getBet();
			table[i].setBet(0);
		}
		sideIndex.clear();
		sideCount = 0;
		return pot;
	}
	public int opponentMove(Player p, int round, Card[] com, int currentBet)
	{//pre: recieves player information to decide what move to make
	//post: returns 0 if the player folds sets status to fold, 0 if the player checks, value>0 is the amount bet
		Card c1=p.getC1(), c2=p.getC2();
		int money=p.getMoney(), result;
		if(round==0)//pre flop
		{
			if(p.getAllIn() == true)//if player is all in
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=handBet(c1, c2, money, currentBet, p);
		}
			
		else if(round==1)//flop
		{
			if(p.getAllIn() == true)//if player is all in
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=flopBet(c1, c2, com, money, currentBet, p);
		}
		else if(round==2)//turn
		{
			if(p.getAllIn() == true)//if player is all in
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=turnBet(c1, c2, com, money, currentBet, p);
		}
		else //river
		{
			if(p.getAllIn() == true)//if player is all in
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=riverBet(c1, c2, com, money, currentBet, p);
		}
		//result is the players move
		//following if/else are to print player move
		if(result==-1)//sets player folds
			p.setStatus(false);
		if(result==0)//player checks
			System.out.println("Player " + p.getName() + " Checks");
		else if(result>0)//player bets/raises
			System.out.println("Player " + p.getName() + " Bets: " + result);
		else if(result==-1)//prints player folds
			System.out.println("Player " + p.getName() + " folds");
		else//this should not print
			System.out.println("Something went wrong with Player " + p.getName());
		//following are to set variables based on player move
		if(result>=0)//player bets
		{//corrects player money, returns result
			p.setBet(result);
			if(p.getMoney()==result)//if player went all in
				p.setAllIn(true);
			return result;
		}
		return 0;//player checks
	}
	public int playerMove(Player p, int currentBet)//user move
	{
		Scanner in=new Scanner(System.in);
		
		String move="", sBet="";
		boolean validMove=false, validBet=false;
		int bet=0;
		
		System.out.println(currentBet);
		if(p.getAllIn() == true)//if user is all in
		{
			System.out.println("You are all in! You can't do anything.");
			return 0;
		}
		if(currentBet==0)//no one has bet
		{
			System.out.println("What is your move? Enter:" + "\n" + "Check, Bet");
			
			while(validMove==false)
			{
				move=in.next();
				if(move.equals("check") || move.equals("Check") || move.equals("Bet") || move.equals("bet"))
					validMove=true;
				else
					System.out.println("Please enter valid move");
			}
			if(move.equals("check") || move.equals("Check"))
			{
				return 0;
			}
			else if(move.equals("bet") || move.equals("Bet"))
			{
				while(validBet==false)
				{
					System.out.println("You have $" + p.getMoney());
					System.out.println("How much would you like to bet?");
					try
					{
						sBet=in.next();
						bet=Integer.parseInt(sBet);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Please enter a numeric value");
					}
					if(bet>0 && bet<=p.getMoney())
						validBet=true;
					else
						System.out.println("Invalid Bet");
				}
				p.setBet(bet);
				if(p.getMoney()==bet)//sets player all in if bet all money
					p.setAllIn(true);
				return bet;
			}
		}
		else//someone has bet
		{
			System.out.println("Current Bet is: " + currentBet);
			System.out.println("What is your move? Enter:" + "\n" + "Call, Raise, Fold");
			if(currentBet != 0 && currentBet == p.getBet())
				return currentBet;
				
			while(validMove==false)
			{
				move=in.next();
				if(move.equals("Call") || move.equals("call") || move.equals("Raise") || move.equals("raise") || move.equals("Fold") || move.equals("fold"))
					validMove=true;
				else
					System.out.println("Please enter valid move");
			}
			if(move.equals("fold") || move.equals("Fold"))
			{
				p.setStatus(false);
				return 0;
			}
			else if(move.equals("Raise") || move.equals("raise"))
			{
				if(p.getMoney()<=currentBet)
					System.out.println("You cannot raise you do not have enough money.");
				else
				{
					while(validBet==false)
					{
						System.out.println("Current bet is " + currentBet + " How much would you like to bet?");
						try
						{
							sBet=in.next();
							bet=Integer.parseInt(sBet);
						}
						catch(NumberFormatException e)
						{
							System.out.println("Please enter a numeric value");
						}
						if(bet>0 && bet<=p.getMoney() && bet>currentBet)
							validBet=true;
						else
							System.out.println("Invalid Bet");
					}
					p.setBet(bet);
					if(p.getMoney()==bet)
						p.setAllIn(true);//sets player all in if bet all money
					return bet;
				}
			}
			else if(move.equals("Call") || move.equals("call"))
			{
				//need to make instance if players money is less than bet
				if(p.getMoney()<=currentBet)
				{
					p.setBet(p.getMoney());//sets player all in
					//create side pot?
					p.setAllIn(true);
					return p.getMoney();
				}
				p.setBet(currentBet);
				return currentBet;
			}
		}
		return 0;
	}
	public int handBet(Card c1, Card c2, int money, int currentBet, Player p)
	{//pre: takes player cards, money, current high bet and player
	 //uses percentages to determine what move the opponent makes based on random values
	 //percentages of outcomes are determined by the current hand strength the player has.
		int chance=0;
		int bet=0;
		Random r=new Random();
		
		if(currentBet==0)//no bet
		{
			if(c1.getValue()==c2.getValue())//pocket pair
			{
				chance=r.nextInt(100)+1;
				if(chance<=30)//player checks
					return 0;
				else//player bets
				{
					chance=r.nextInt(100)+1;
					if(chance<=80)//bet 1-2
						bet=r.nextInt(2)+1;
					else if(chance>=81 && chance<=99)//bet 3-5
						bet=r.nextInt(5-3)+2;
					else//bet random
						bet=r.nextInt(money)+1;
				}
			}				
			else if((c1.getValue()>=c2.getValue()-4 && c1.getValue()<=c2.getValue()+4) || (c1.getValue()==12 && c2.getValue()<=4) || (c2.getValue()==12 && c1.getValue()<=4) || (c1.getSuit().equals(c2.getSuit())))//if c1 +-5 c2
			{
				chance=r.nextInt(100)+1;
				if(chance<=80)//player checks
					return 0;
				else//player bets
				{
					chance=r.nextInt(100)+1;
					if(chance<=94)//bet 1-2
						bet=r.nextInt(2)+1;
					else if(chance>=95 && chance<=99)//bet 3-5
						bet=r.nextInt(5-3)+2;
					else//bet random
						bet=r.nextInt(money)+1;
				}
			}
			else//!suit!+-5
			{
				chance=r.nextInt(100)+1;
				if(chance<=95)//player checks
					return 0;
				else//player bets
				{
					chance=r.nextInt(100)+1;
					if(chance<=97)//bet 1-2
						bet=r.nextInt(2)+1;
					else if(chance>=98 && chance<=99)//bet 3-5
						bet=r.nextInt(5-3)+2;
					else// bet random
						bet=r.nextInt(money)+1;
				}
			}
		}
		else//someone has bet
		{
			if(c1.getValue()==c2.getValue())//pocket pair
			{
				chance=r.nextInt(100)+1;
				if(chance<=45)//player calls
					bet = currentBet;
				else if(chance>=46 && chance<=95)//player raises
				{
					if(currentBet>=p.getMoney())
						bet=currentBet;
					else
					{
						chance=r.nextInt(100)+1;
						if(chance<=90)//raise 1
							bet=currentBet+1;
						else if(chance>=91 && chance<=99)//raise 2
							bet=currentBet+2;
						else//raise random
						{
							if(currentBet>=money)//if bet is higher than current money
								bet=currentBet;
							else
								bet=currentBet+(r.nextInt(money-currentBet)+1);
						}
					}
				}
				else//player folds
					return -1;
			}
			else if((c1.getValue()>=c2.getValue()-4 && c1.getValue()<=c2.getValue()+4) //+-5
			|| (c1.getValue()==12 && c2.getValue()<=4) || (c2.getValue()==12 && c1.getValue()<=4)//ace & card 2-4
			|| (c1.getSuit().equals(c2.getSuit())))//suits the same //all one if statement
			{
				chance=r.nextInt(100)+1;
				if(chance<=60)//player calls
					bet = currentBet;
				else if(chance>=61 && chance<=65)//player raises
				{
					if(currentBet>=p.getMoney())
						bet=currentBet;
					else
					{
						chance=r.nextInt(100)+1;
						if(chance>=90)//raise 1
							bet=currentBet+1;
						else if(chance>=91 && chance<=99)//raise 2
							bet=currentBet+2;
						else//raise random
						{	
							if(currentBet>=money)//if bet is higher than current money
								bet=currentBet;//player goes all in
							else
								bet=currentBet+(r.nextInt(money-currentBet)+1);
						}
					}
				}
				else//player folds
					return -1;
			}
			else//!+-5 !suit
			{
				chance=r.nextInt(100)+1;
				if(chance<=40)//player calls
					bet = currentBet;
				else if(chance>=41 && chance<=45)//player raises
				{
					if(currentBet>=p.getMoney())
						bet=currentBet;
					else
					{
						chance=r.nextInt(100)+1;
						if(chance<=90)//raise 1
							bet=currentBet+1;
						else if(chance>=91 && chance<=99)//raise 2
							bet=currentBet+2;
						else//raise random
						{
							if(currentBet>=money)//if bet is higher than current money
								bet=currentBet;//player goes all in
							else
								bet=currentBet+(r.nextInt(money-currentBet)+1);
						}
					}
				}
				else//player folds
					return -1;
			}
		}
		System.out.println("bet: " + bet);
		if(bet > p.getMoney())//sets bet to player money if bet is greater
			bet = p.getMoney();
		return bet;
	}
	public int flopBet(Card c1, Card c2, Card[] com, int money, int currentBet, Player p)
	{
		int chance=0, bet=0, hs=0, move=0;
		String result;
		
		Random r=new Random();	
		Search s=new Search();
		
		result=s.searchFlop(c1,c2,com);//gets handStrength(hs)
		result=result.substring(0,1);
		hs=Integer.parseInt(result);//this all works
		
		if(currentBet==0)//no one has bet
		{
			chance=r.nextInt(100)+1;
			switch(hs)
			{
				case 0://high card
					if(chance<=90)//check
						bet=0;
					else//bet
					{
						chance=r.nextInt(100)+1;
						if(chance<=90)//bet 1-3
							bet=r.nextInt(3)+1;
						else if(chance>=91 && chance<=99)//bet 3-5
							bet=r.nextInt(5-2)+3;
						else//bet 5+
							bet=r.nextInt(95)+6;
					}
					break;
				case 1://pair
					if(chance<=70)//check
						bet = 0;
					else//bet
					{
						chance=r.nextInt(100)+1;
						if(chance <= 83)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >=84 && chance <= 93)//bet 3-5
							bet = r.nextInt(5-2)+3;
						else if(chance >=94 && chance <= 98)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 2://two pair
					if(chance<=50)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 80)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 81 && chance <= 90)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 91 && chance <= 97)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 3://three of a kind
					if(chance<=35)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 70)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 71 && chance <= 85)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 86 && chance <= 96)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=97 && chance <= 99)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 4://straight
					if(chance<=30)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 70)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 71 && chance <= 85)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 86 && chance <= 96)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=97 && chance <= 99)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 5://flush
					if(chance<=30)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 45)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 46 && chance <= 70)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 71 && chance <= 85)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=86 && chance <= 95)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 6://four of a kind
					if(chance<=15)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 30)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 31 && chance <= 60)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 61 && chance <= 80)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=81 && chance <= 94)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 7://full house
					if(chance<=10)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 15)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 16 && chance <= 40)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 41 && chance <= 70)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=71 && chance <= 90)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//bet 15+
							bet = r.nextInt(85)+16;
					}
					break;		
				case 8://straight flush
					if(chance<=5)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 5)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 6 && chance <= 20)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 21 && chance <= 45)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=46 && chance <= 75)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//bet 15+
							bet = r.nextInt(85)+16;
					}
					break;
				case 9://royal flush
					if(chance<=5)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 2)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 3 && chance <= 13)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 14 && chance <= 34)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >=35 && chance <= 61)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else if(chance >=62 && chance <= 85)//bet 15-20
							bet = r.nextInt(20-14)+15;
						else//bet 15+
							bet = r.nextInt(85)+16;
					}
					break;
			}
		}
		else//someone has bet
		{
			chance=r.nextInt(100)+1;
			switch(hs)
			{
				case 0://high card
					if(chance<=20)//player calls
						bet = currentBet;
					else if(chance>=21 && chance<=25)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 90)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 91 && chance <= 99)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 1://pair
					if(chance<=70)//player calls
						bet = currentBet;
					else if(chance>=71 && chance<=75)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 83)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 84 && chance <= 93)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 94 && chance <= 98)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}							
						}
					}
					else//player folds
						return -1;
					break;
				case 2://two pair
					if(chance<=80)//player calls
						bet = currentBet;
					else if(chance>=81 && chance<=90)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 80)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 81 && chance <= 90)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 91 && chance <= 97)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}							
						}
					}
					else//player folds
						return -1;
					break;
				case 3://three of a kind
					if(chance<=85)//player calls
						bet = currentBet;
					else if(chance>=86 && chance<=95)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 70)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 71 && chance <= 85)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 86 && chance <= 96)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 97 && chance <= 99)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}							
						}
					}
					else//player folds
						return -1;
					break;
				case 4://straight
					if(chance<=80)//player calls
						bet = currentBet;
					else if(chance>=81 && chance<=97)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 60)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 61 && chance <= 80)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 81 && chance <= 90)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 91 && chance <= 97)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 5://flush
					if(chance<=60)//player calls
						bet = currentBet;
					else if(chance>=61 && chance<=98)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 45)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 46 && chance <= 70)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 71 && chance <= 85)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 86 && chance <= 95)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 6://four of a kind
					if(chance<=45)//player calls
						bet = currentBet;
					else if(chance>=46 && chance<=99)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 30)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 31 && chance <= 60)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 61 && chance <= 80)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 81 && chance <= 94)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 7://full house
					if(chance<=40)//player calls
						bet = currentBet;
					else if(chance>=41 && chance<=99)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 15)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 16 && chance <= 40)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 41 && chance <= 70)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 71 && chance <= 90)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					else//player folds
						return -1;
					break;		
				case 8://straight flush, player will not fold
					if(chance<=20)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 5)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 6 && chance <= 20)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 21 && chance <= 45)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 46 && chance <= 75)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;
				case 9://royal flush
					if(chance<=20)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 2)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 3 && chance <= 13)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 14 && chance <= 34)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 35 && chance <= 61)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else if(chance >= 62 && chance <= 85)//raises 15-20
								bet = currentBet + (r.nextInt(20-14)+15);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;
			}
		}  
		System.out.println("bet: " + bet);
		if(bet > p.getMoney())
			bet = p.getMoney();
		return bet;

	}
	public int turnBet(Card c1, Card c2, Card[] com, int money, int currentBet, Player p)
	{
		int chance=0, bet=0, hs=0;
		String result;
		
		Random r=new Random();	
		Search s=new Search();
		
		result=s.searchTurn(c1,c2,com);//gets handStrength(hs)
		result=result.substring(0,1);
		hs=Integer.parseInt(result);//this all works
		
		if(currentBet==0)//no one has bet
		{
			chance=r.nextInt(100)+1;
			switch(hs)
			{
				case 0://high card
					if(chance <=90)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 90)
							bet = r.nextInt(3)+1;
						else if(chance >= 91 && chance <=99)
							bet = r.nextInt(5-2)+3;
						else//bet 5+
							bet = r.nextInt(95)+6;
					}
					break;
				case 1://pair
					if(chance <= 70)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 83)
							bet = r.nextInt(3)+1;
						else if(chance >= 84 && chance <=93)
							bet = r.nextInt(5-2)+3;
						else if(chance >= 94 && chance <= 98)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 2://two pair
					if(chance <= 50)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 80)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 81 && chance <= 90)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 91 && chance <= 97)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 3://three of a kind
					if(chance <= 35)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 70)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 71 && chance <= 85)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 86 && chance <= 95)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 96 && chance <= 99)//bet 10-15
							bet = r.nextInt(15-9)+10;	
						else//10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 4://straight
					if(chance <= 30)//bet
						bet = 0;
					else//bet
					{
					chance = r.nextInt(100)+1;
						if(chance <= 50)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 51 && chance <= 75)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 76 && chance <= 90)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 91 && chance <= 97)//bet 10-15
							bet = r.nextInt(15-9)+10;	
						else//+10
							bet = r.nextInt(90)+11;
					}
					break;
				case 5://flush
					if(chance <= 30)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 45)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 46 && chance <= 70)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 71 && chance <= 85)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 86 && chance <= 95)//bet 10-15
							bet = r.nextInt(15-9)+10;	
						else//10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 6://four of a kind
					if(chance <= 15)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 30)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 31 && chance <= 60)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 61 && chance <= 80)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 81 && chance <= 94)//bet 10-15
							bet = r.nextInt(15-9)+10;	
						else//+10
							bet = r.nextInt(90)+11;
					}
					break;
				case 7://full house
					if(chance <= 10)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 15)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 16 && chance <= 40)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 41 && chance <= 70)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 71 && chance <= 90)//bet 10-15
							bet = r.nextInt(15-9)+10;	
						else//+15
							bet = r.nextInt(85)+16;
					}
					break;		
				case 8://straight flush
					if(chance <= 5)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 5)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 6 && chance <= 20)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 21 && chance <= 45)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 46 && chance <= 75)//bet 10-15
							bet = r.nextInt(15-9)+10;	
						else//+15
							bet = r.nextInt(85)+16;
					}
					break;
				case 9://royal flush
					if(chance <= 5)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 2)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 3 && chance <= 13)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 14 && chance <= 34)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 35 && chance <= 61)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else if(chance >= 62 && chance <= 85)//bet 15-20
							bet = r.nextInt(20-14)+15;	
						else//+15
							bet = r.nextInt(85)+16;
					}
					break;
			}
		}
		else//someone has bet
		{
			switch(hs)
			{
				case 0://high card
					if(chance<= 20)//player calls
						bet = currentBet;
					else if(chance>= 21 && chance<= 25)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 90)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 91 && chance <= 99)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 1://pair
					if(chance<= 70)//player calls
						bet = currentBet;
					else if(chance>= 71 && chance<= 75)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							if(currentBet>=p.getMoney())//player goes all in
								bet=currentBet;
							else
							{
								chance = r.nextInt(100)+1;
								if(chance <= 83)//raise 1-3
									bet = currentBet +(r.nextInt(3)+1);
								else if(chance >= 84 && chance <= 93)//raise 3-5
									bet = currentBet + (r.nextInt(5-2)+3);
								else if(chance >= 94 && chance <= 98)//raise 5-10
									bet = currentBet + (r.nextInt(10-4)+5);
								else//raise 10+
								{
									if(currentBet>=money)
										bet=currentBet;//player goes all in 
									else	
										bet=currentBet + r.nextInt(90)+11;
								}
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 2://two pair
					if(chance<= 80)//player calls
						bet = currentBet;
					else if(chance>= 81 && chance<= 90)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 80)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 81 && chance <= 90)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 91 && chance <= 97)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 3://three of a kind
					if(chance<= 85)//player calls
						bet = currentBet;
					else if(chance>= 86 && chance<= 95)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 70)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 71 && chance <= 85)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 86 && chance <= 95)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 96 && chance <= 99)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 4://straight
					if(chance<= 80)//player calls
						bet = currentBet;
					else if(chance>= 81 && chance<= 97)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 50)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 51 && chance <= 70)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 71 && chance <= 90)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 91 && chance <= 97)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 5://flush
					if(chance<= 60)//player calls
						bet = currentBet;
					else if(chance>= 61 && chance<= 98)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 45)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 46 && chance <= 70)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 71 && chance <= 85)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 86 && chance <= 95)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 6://four of a kind
					if(chance<= 45)//player calls
						bet = currentBet;
					else if(chance>= 46 && chance<= 99)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 30)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 31 && chance <= 60)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 61 && chance <= 80)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 81 && chance <= 96)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					else//player folds
						return -1;
					break;
				case 7://full house
					if(chance<= 40)//player calls
						bet = currentBet;
					else if(chance>= 41 && chance<= 99)//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 15)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 16 && chance <= 40)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 41 && chance <= 70)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 71 && chance <= 90)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					else//player folds
						return -1;
					break;		
				case 8://straight flush
					if(chance<= 20)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 5)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 6 && chance <= 20)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 21 && chance <= 45)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 46 && chance <= 75)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;
				case 9://royal flush
					if(chance<= 20)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet>=p.getMoney())//player goes all in
							bet=currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 2)//raise 1-3
								bet = currentBet +(r.nextInt(3)+1);
							else if(chance >= 3 && chance <= 13)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 14 && chance <= 34)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 35 && chance <= 61)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else if(chance >=62 && chance <= 85)//bet 15-20
								bet = r.nextInt(20-14)+15;
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;
			}
		}  
		System.out.println("bet: " + bet);
		if(bet > p.getMoney())
			bet = p.getMoney();
		return bet;
	}
	public int riverBet(Card c1, Card c2, Card[] com, int money, int currentBet, Player p)
	{
		int chance=0, bet=0, hs=0;
		
		Random r=new Random();	
		Rank rk=new Rank();
		
		hs=rk.rankHand(c1,c2,com);//gets handStrength(hs)
		chance = r.nextInt(100)+1;
		if(currentBet==0)//no one has bet
		{
			switch(hs)
			{
				case 0://high card
					if(chance <= 90)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 95)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 96 && chance <= 99)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else//bet 5+
							bet = r.nextInt(95)+6;
					}
					break;
				case 1://pair
					if(chance <= 80)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 90)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 91 && chance <= 99)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else//bet 5+
							bet = r.nextInt(95)+6;
					}
					break;
				case 2://two pair
					if(chance <= 60)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 75)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 76 && chance <= 99)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else//bet 5+
							bet = r.nextInt(95)+6;
					}
					break;
				case 3://three of a kind
					if(chance <= 40)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 65)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 66 && chance <= 98)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else//bet 5+
							bet = r.nextInt(95)+6;
					}
					break;
				case 4://straight
					if(chance <= 20)//bet
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 55)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 56 && chance <= 98)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else//bet 5+
							bet = r.nextInt(95)+6;
					}
					break;
				case 5://flush
					if(chance <= 15)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 40)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 41 && chance <= 85)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else if(chance >= 86 && chance <= 98)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 6://four of a kind
					if(chance <= 5)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 20)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 21 && chance <= 80)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else if(chance >= 81 && chance <= 98)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;
				case 7://full house
					if(chance <= 5)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 10)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 11 && chance <= 80)//bet 3-5						
							bet = r.nextInt(5-2)+3;	
						else if(chance >= 81 && chance <= 97)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else//bet 10+
							bet = r.nextInt(90)+11;
					}
					break;		
				case 8://straight flush
					if(chance <= 5)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 2)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 3 && chance <= 27)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 28 && chance <= 47)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 48 && chance <= 97)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//+15
							bet = r.nextInt(85)+16;
					}
					break;
				case 9://royal flush
					if(chance <= 5)//check
						bet = 0;
					else//bet
					{
						chance = r.nextInt(100)+1;
						if(chance <= 1)//bet 1-3
							bet = r.nextInt(3)+1;
						else if(chance >= 2 && chance <= 16)//bet 3-5						
							bet = r.nextInt(5-2)+3;
						else if(chance >= 17 && chance <= 31)//bet 5-10
							bet = r.nextInt(10-4)+5;
						else if(chance >= 32 && chance <= 91)//bet 10-15
							bet = r.nextInt(15-9)+10;
						else//+15
							bet = r.nextInt(85)+16;
					}
					break;
			}
		}
		else//someone has bet
		{
			switch(hs)
			{
				case 0://high card
					if(chance <= 5)//player calls
						bet = currentBet;
					else if(chance >= 6 && chance <= 7)//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 98)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 99 && chance <=99)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;					
					break;
				case 1://pair
					if(chance <= 7)//player calls
						bet = currentBet;
					else if(chance >= 8 && chance <= 10)//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 95)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 96 && chance <=99)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;					
					break;
				case 2://two pair
					if(chance <= 35)//player calls
						bet = currentBet;
					else if(chance >= 36 && chance <= 40)//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 92)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 93 && chance <=99)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;					
					break;
				case 3://three of a kind
					if(chance <= 60)//player calls
						bet = currentBet;
					else if(chance >= 61 && chance <= 75)//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 85)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 86 && chance <=98)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;					
					break;
				case 4://straight
					if(chance <= 55)//player calls
						bet = currentBet;
					else if(chance >= 56 && chance <= 90)//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 70)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 71 && chance <=97)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else//raise 5+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(95)+6;
							}
						}
					}
					else//player folds
						return -1;					
					break;
				case 5://flush
					if(chance <= 40)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 40)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 41 && chance <=74)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 75 && chance <= 99)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					break;
				case 6://four of a kind
					if(chance <= 35)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 30)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 31 && chance <=75)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 76 && chance <= 95)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else//raise 10+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(90)+11;
							}
						}
					}
					break;
				case 7://full house
					if(chance <= 30)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 15)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 16 && chance <=50)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 51 && chance <= 90)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 91 && chance <= 98)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;		
				case 8://straight flush
					if(chance <= 20)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 5)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 6 && chance <=20)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 21 && chance <= 65)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 66 && chance <= 95)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;
				case 9://royal flush
					if(chance <= 15)//player calls
						bet = currentBet;
					else//player raises
					{
						if(currentBet >= p.getMoney())//player goes all in
							bet = currentBet;
						else
						{
							chance = r.nextInt(100)+1;
							if(chance <= 1)//raise 1-3
								bet = currentBet + (r.nextInt(3)+1);
							else if(chance >= 2 && chance <=5)//raise 3-5
								bet = currentBet + (r.nextInt(5-2)+3);
							else if(chance >= 6 && chance <= 35)//raise 5-10
								bet = currentBet + (r.nextInt(10-4)+5);
							else if(chance >= 36 && chance <= 85)//raises 10-15
								bet = currentBet + (r.nextInt(15-9)+10);
							else//raise 15+
							{
								if(currentBet>=money)
									bet=currentBet;//player goes all in 
								else	
									bet=currentBet + r.nextInt(85)+16;
							}
						}
					}
					break;
			}
		}  
		System.out.println("bet: " + bet);
		if(bet > p.getMoney())
			bet = p.getMoney();
		return bet;
	}
	
	//Method userOpponentMove is for testing purposes
	//used to control opponent moves
	//same as playerMove
	public int userOpponentMove(Player p, int currentBet)
	{
		Scanner in=new Scanner(System.in);
		
		String move="", sBet="";
		boolean validMove=false, validBet=false;
		int bet=0;
		
		System.out.println(currentBet);
		if(currentBet==0)
		{
			System.out.println("What is your move? Enter:" + "\n" + "Check, Fold, Bet");
			
			while(validMove==false)
			{
				move=in.next();
				if(move.equals("check") || move.equals("Check") || move.equals("fold") || move.equals("Fold") || move.equals("Bet") || move.equals("bet"))
					validMove=true;
				else
					System.out.println("Please enter valid move");
			}
			if(move.equals("fold") || move.equals("Fold"))
			{
				p.setStatus(false);
				return 0;
			}
			else if(move.equals("bet") || move.equals("Bet"))
			{
				while(validBet==false)
				{
					System.out.println("How much would you like to bet?");
					try
					{
						sBet=in.next();
						bet=Integer.parseInt(sBet);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Please enter a numeric value");
					}
					if(bet>0 && bet<p.getMoney())
						validBet=true;
					else
						System.out.println("Invalid Bet");
				}
				p.setBet(bet);
				return bet;
			}
		}
		else
		{
			System.out.println("Current Bet is: " + currentBet);
			System.out.println("What is your move? Enter:" + "\n" + "Call, Raise, Fold");
			
			while(validMove==false)
			{
				move=in.next();
				if(move.equals("Call") || move.equals("call") || move.equals("Raise") || move.equals("raise") || move.equals("Fold") || move.equals("fold"))
					validMove=true;
				else
					System.out.println("Please enter valid move");
			}
			if(move.equals("fold") || move.equals("Fold"))
			{
				p.setStatus(false);
				return 0;
			}
			else if(move.equals("Raise") || move.equals("raise"))
			{
				while(validBet==false)
				{
					System.out.println("How much would you like to bet?");
					try
					{
						sBet=in.next();
						bet=Integer.parseInt(sBet);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Please enter a numeric value");
					}
					if(bet>0 && bet<p.getMoney() && bet>currentBet)
						validBet=true;
					else
						System.out.println("Invalid Bet");
				}
				p.setBet(bet);
				return bet;
			}
			else if(move.equals("Call") || move.equals("call"))
			{
				p.setBet(currentBet);
				return currentBet;
			}
		}
		return 0;
	}
}