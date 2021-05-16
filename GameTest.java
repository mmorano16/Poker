//main class for testing purposes
import java.util.*;

public class GameTest
{
    public static void main(String[] args)
    {
		Scanner in=new Scanner(System.in);
		
        String ranks[]={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        String suits[]={"Clubs","Spades","Diamonds","Hearts"};
		Card hand[]=new Card[2];
        int values[]=new int[13];
		Card com[]=new Card[5];//community cards
		Player table[]=new Player[3];//table of players
		Player  winner=table[0];
		int dPos=0, pot=0, round=0, turn=0, bet=0, highBet=0, choice=0;
		Card temp=new Card("temp", "temp", -1);
		for(int i=0;i<values.length;i++)
            values[i]=i;
		
        Deck d1=new Deck(ranks, suits, values);
		Rank r1=new Rank();
		Compare c1=new Compare();
		TestMove m1=new TestMove();

		Card tempAC=d1.deal(),temp2C=d1.deal(),temp3C=d1.deal(),temp4C=d1.deal(),temp5C=d1.deal(),temp6C=d1.deal(),temp7C=d1.deal(),temp8C=d1.deal(),temp9C=d1.deal(),temp10C=d1.deal(),tempJC=d1.deal(),tempQC=d1.deal(),tempKC=d1.deal();
		Card tempAS=d1.deal(),temp2S=d1.deal(),temp3S=d1.deal(),temp4S=d1.deal(),temp5S=d1.deal(),temp6S=d1.deal(),temp7S=d1.deal(),temp8S=d1.deal(),temp9S=d1.deal(),temp10S=d1.deal(),tempJS=d1.deal(),tempQS=d1.deal(),tempKS=d1.deal();
		Card tempAD=d1.deal(),temp2D=d1.deal(),temp3D=d1.deal(),temp4D=d1.deal(),temp5D=d1.deal(),temp6D=d1.deal(),temp7D=d1.deal(),temp8D=d1.deal(),temp9D=d1.deal(),temp10D=d1.deal(),tempJD=d1.deal(),tempQD=d1.deal(),tempKD=d1.deal();
		Card tempAH=d1.deal(),temp2H=d1.deal(),temp3H=d1.deal(),temp4H=d1.deal(),temp5H=d1.deal(),temp6H=d1.deal(),temp7H=d1.deal(),temp8H=d1.deal(),temp9H=d1.deal(),temp10H=d1.deal(),tempJH=d1.deal(),tempQH=d1.deal(),tempKH=d1.deal();
	
		table[0]=new Player(tempAC, tempAS, 110, true, "My", 0, 0, false, false);//sets players hand
		table[1]=new Player(tempJC, tempJS, 100, true, "Player 1", 0, 0, false, false);//sets players hand
		table[2]=new Player(tempKC, tempKS, 100, true, "Player 2", 0, 0, false, false);//sets players hand

		for(int i=0;i<table.length;i++)
			System.out.println(table[i].toString());
		
		//round of betting
		pot+=m1.move(table, dPos, round, com);
		System.out.println(pot);
		for(int i=0;i<table.length;i++)
			System.out.println(table[i].toString());
		//flop
        com[0]=tempAD;
		com[1]=tempAH;
		com[2]=temp2C;
		System.out.println("The Flop: ");
		for(int i=0;i<3;i++)
			System.out.print(com[i] + ", ");
		System.out.println();
		
		//round of betting
		round++;
		pot+=m1.move(table, dPos, round, com);
		System.out.println(pot);
		//turn		
		com[3]=temp4H;//adds turn card
		System.out.println("\n\n" + "The Turn:");
		for(int i=0;i<4;i++)
			System.out.print(com[i] + ", "); 
		System.out.println();
		//round of betting
		round++;
		pot+=m1.move(table, dPos, round, com);
		System.out.println(pot);
		//river	
		com[4]=temp7S;
		System.out.println("\n\n"+"The River:"); 
		for(int i=0;i<5;i++)
			System.out.print(com[i] + ", ");
		System.out.println();
		//round of betting
		round++;
		pot+=m1.move(table, dPos, round, com);
		System.out.println(pot);
		
		for(int i=0;i<table.length;i++)
			System.out.println(table[i].toString());
		for(int i=0;i<table.length;i++)//finds hand strength for each player
		{
			if(table[i].getStatus()==true)
			{
				switch(r1.rankHand(table[i].getC1(), table[i].getC2(), com))//finds each players hand strength
				{
					case 10:
						table[i].setHandStrength(10);
						System.out.println("Royal Flush");
						break;
					case 9:
						table[i].setHandStrength(9);
						System.out.println("Striaght Flush " + i);
						break;
					case 8:
						table[i].setHandStrength(8);
						System.out.println("Four of a Kind " + i);
						break;
					case 7:
						table[i].setHandStrength(7);
						System.out.println("Full House " + i);
						break;
					case 6:
						table[i].setHandStrength(6);
						System.out.println("Flush " + i);
						break;
					case 5:
						table[i].setHandStrength(5);
						System.out.println("Straight " + i);
						break;
					case 4:
						table[i].setHandStrength(4);
						System.out.println("Three of a Kind " + i);
						break;
					case 3:
						table[i].setHandStrength(3);
						System.out.println("Two Pair " + i);
						break;
					case 2:
						table[i].setHandStrength(2);
						System.out.println("Pair " + i);
						break;
					case 1:
						table[i].setHandStrength(1);
						System.out.println("High Card " + i);
						break;
				}
			}
		}
		c1.findWinner(table, com, pot);
		for(int i=0;i<table.length;i++)
			System.out.println(table[i].toString());
		d1=new Deck(ranks, suits, values);//resets deck
		round=0;
		dPos++;
    }
}
