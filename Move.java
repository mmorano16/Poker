//will need to add condition for when player money is less than current bet
/**Create a object called sidepot that has the variables amount/players inside/pot#**\
if sidepot.player.gethand > winner.getHand
	sidepot.player wins sidepot.amount
	winner.player wins pot
else
	winner.player wins pot + sidepot.amount
**if there are multiple sidepots the winner takes the pot of the side pot they are in + lower side pots**
**will need to start from the bottom and work way up**
**have: if sidepot = true then run find side winner**/
//add instance for if players are out of money

//determines order for player moves and amounts bet/won
import java.util.*;

public class Move
{
	public int move(Player[] table, int dPos, int round, Card[] com)
	{//pre:recieves tables of players and decides whose turn it is
	//post: returns the value of the pot after the round of betting, sets status, bet and money appropriately 	
		int count=0, bPos=-1, highBet=0, pot=0, bet=0;
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
				else if(table[(bPos+count)%9].getStatus()==true&& table[(bPos+count)%9].getOut()==false)//opponenta turn
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
		highBet=0;
		for(int i=0;i<table.length;i++)//subtracts bets from player money
			table[i].setMoney(table[i].getMoney()-table[i].getBet());
		for(int i=0;i<table.length;i++)//adds bets to pot and resets bets
		{
			pot+=table[i].getBet();
			table[i].setBet(0);
		}
		return pot;
	}
	public int opponentMove(Player p, int round, Card[] com, int currentBet)
	{//pre: recieves player information to decide what move to make
	//post: returns 0 if the player folds sets status to fold, 0 if the player checks, value>0 is the amount bet
		Card c1=p.getC1(), c2=p.getC2();
		int money=p.getMoney(), result;
		if(round==0)
		{
			if(p.getAllIn() == true)
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=handBet(c1, c2, money, currentBet, p);
		}
			
		else if(round==1)
		{
			if(p.getAllIn() == true)
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=flopBet(c1, c2, com, money, currentBet, p);
		}
		else if(round==2)
		{
			if(p.getAllIn() == true)
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=turnBet(c1, c2, com, money, currentBet, p);
		}
		else 
		{
			if(p.getAllIn() == true)
			{
				System.out.println(p.getName() + " is all in.");
				return 0;
			}
			else
				result=riverBet(c1, c2, com, money, currentBet, p);
		}
		if(result==-1)//player folds
			p.setStatus(false);
		if(result==0)
			System.out.println("Player " + p.getName() + " Checks");
		else if(result>0)
			System.out.println("Player " + p.getName() + " Bets: " + result);
		else if(result==-1)
			System.out.println("Player " + p.getName() + " folds");
		else
			System.out.println("Something went wrong with Player " + p.getName());
		if(result>=0)//player bets
		{
			p.setBet(result);
			if(p.getMoney()==result)
				p.setAllIn(true);
			return result;
		}
		return 0;//player checks
	}
	public int playerMove(Player p, int currentBet)
	{
		Scanner in=new Scanner(System.in);
		
		String move="", sBet="";
		boolean validMove=false, validBet=false;
		int bet=0;
		
		System.out.println(currentBet);
		if(p.getAllIn() == true)
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
				if(p.getMoney()==bet)
					p.setAllIn(true);
				return bet;
			}
		}
		else//someone has bet
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
						p.setAllIn(true);
					return bet;
				}
			}
			else if(move.equals("Call") || move.equals("call"))
			{
				//need to make instance if players money is less than bet
				if(p.getMoney()<currentBet)
				{
					p.setBet(p.getMoney());
					return p.getMoney();
				}
				p.setBet(currentBet);
				return currentBet;
			}
		}
		return 0;
	}
	public int handBet(Card c1, Card c2, int money, int currentBet, Player p)//need to add current bet to these methods
	{
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
					return currentBet;
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
					return currentBet;
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
					return currentBet;
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
				case 0:
					if(chance<=90)//check
						bet=0;
					else//bet
					{
						chance=r.nextInt(100)+1;
						if(chance<=83)//bet 1-3
							bet=r.nextInt(3)+1;
						else if(chance>=84 && chance<=93)//bet 3-5
							bet=r.nextInt(5-3)+2;
						else//bet 6-100
							bet=r.nextInt(100-5)+6;
					}
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;		
				case 8:
					break;
				case 9:
					break;
			}
		}
		else//someone has bet
		{
			chance=r.nextInt(100)+1;
			switch(hs)
			{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;		
				case 8:
					break;
				case 9:
					break;
			}
		}  
		return 0;

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
			switch(hs)
			{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;		
				case 8:
					break;
				case 9:
					break;
			}
		}
		else//someone has bet
		{
			switch(hs)
			{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;		
				case 8:
					break;
				case 9:
					break;
			}
		}  
		return 0;
	}
	public int riverBet(Card c1, Card c2, Card[] com, int money, int currentBet, Player p)
	{
		int chance=0, bet=0, hs=0;
		
		Random r=new Random();	
		Rank rk=new Rank();
		
		hs=rk.rankHand(c1,c2,com);//gets handStrength(hs)
		
		
		if(currentBet==0)//no one has bet
		{
			switch(hs)
			{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;		
				case 8:
					break;
				case 9:
					break;
			}
		}
		else//someone has bet
		{
			switch(hs)
			{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;		
				case 8:
					break;
				case 9:
					break;
			}
		}  
		return 0;
	}
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