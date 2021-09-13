import java.util.*;

public class Tests
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

		Card tempAC=d1.deal(),temp2C=d1.deal(),temp3C=d1.deal(),temp4C=d1.deal(),temp5C=d1.deal(),temp6C=d1.deal(),temp7C=d1.deal(),temp8C=d1.deal(),temp9C=d1.deal(),temp10C=d1.deal(),tempJC=d1.deal(),tempQC=d1.deal(),tempKC=d1.deal();
		Card tempAS=d1.deal(),temp2S=d1.deal(),temp3S=d1.deal(),temp4S=d1.deal(),temp5S=d1.deal(),temp6S=d1.deal(),temp7S=d1.deal(),temp8S=d1.deal(),temp9S=d1.deal(),temp10S=d1.deal(),tempJS=d1.deal(),tempQS=d1.deal(),tempKS=d1.deal();
		Card tempAD=d1.deal(),temp2D=d1.deal(),temp3D=d1.deal(),temp4D=d1.deal(),temp5D=d1.deal(),temp6D=d1.deal(),temp7D=d1.deal(),temp8D=d1.deal(),temp9D=d1.deal(),temp10D=d1.deal(),tempJD=d1.deal(),tempQD=d1.deal(),tempKD=d1.deal();
		Card tempAH=d1.deal(),temp2H=d1.deal(),temp3H=d1.deal(),temp4H=d1.deal(),temp5H=d1.deal(),temp6H=d1.deal(),temp7H=d1.deal(),temp8H=d1.deal(),temp9H=d1.deal(),temp10H=d1.deal(),tempJH=d1.deal(),tempQH=d1.deal(),tempKH=d1.deal();
	
		table[0]=new Player(temp, temp, 110, true, "Me", 0, 0, false, false);//sets players hand
		table[1]=new Player(temp, temp, 100, true, "Player 1", 0, 0, false, false);//sets players hand
		table[2]=new Player(temp, temp, 100, true, "Player 2", 0, 0, false, false);//sets players hand


        com[0]=temp3D;
		com[1]=temp9H;
		com[2]=temp2C;
		com[3]=temp4H;
		com[4]=temp7S;
		
		/*if(r1.rankHand(tempAC, temp10D, com) == 0)
			print("Pass");
		else
			print("Fail");*/
		
		c1.findWinner(table, com, 10);
		System.out.println(table[0].getMoney() + " " + table[1].getMoney() + " " + table[2].getMoney());
    }


	static void print(String x)
	{
		System.out.println(x);
	}
}

