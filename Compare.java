//compares two players with the same hand strenght
import java.util.Arrays;
import java.util.ArrayList;

public class Compare
{
	public void findWinner(Player[] table, Card[] com, int pot, boolean isSidePot)
	{//pre:compares players hand strength finding the highest
	//comparing hands if equal
	//post:finds the winner and transfers money to winner
		int pos = 0, comp=-1, winPos=0, split=0;
		while(table[pos].getOut())
			pos++;
		Player winner=table[pos];
		ArrayList<Integer> tie=new ArrayList<Integer>();
		winPos = pos;
		
		for(int i=0;i<table.length;i++)//searches for a winner with highest handStrenght
			if(winner.getHandStrength()<table[i].getHandStrength() && table[i].getOut()==false)
			{
				winner=table[i];
				winPos=i;
			}
		tie.add(winPos);
		for(int i=0;i<table.length;i++)//searches to see if any players in table have same handStrenght as winner
		{
			if(winner.getHandStrength()==table[i].getHandStrength() && table[i].getOut()==false && i!=winPos)
			{
				comp=compareHand(winner, table[i], table[i].getHandStrength(), com);
				if(comp==1 && tie.size()==1)
				{
					//System.out.println("\n" + table[i].getName() + " Wins");
					tie.clear();
					tie.add(i-1);
					//winner stays the same, set same false
				}
				else if(comp==2)
				{
					//System.out.println("\n" + table[i].getName() + " Wins");
					winner=table[i];
					tie.clear();
					tie.add(i);
					//set winner to table[i]
				}
				else if(comp==0)
				{
					//System.out.println("\n"+comp + " Players split");
					tie.add(i);
					//split pot,set int same true
				}
			}
		}
		for(int i=0;i<tie.size();i++)
			System.out.println(tie.get(i));
		if(tie.size()>=2)
		{
			split=pot/tie.size();
			for(int i=0;i<tie.size();i++)
			{
				System.out.print(table[tie.get(i)].getName() + ", ");
				table[tie.get(i)].setMoney(table[tie.get(i)].getMoney()+split);
			}
			System.out.print("split");
			if(isSidePot)
			{
				System.out.print(" Side Pot");
			}
			Simple.print(".\n");
			System.out.print("Players Earn: " + split + " Each");
		}
		else
		{
			System.out.print(winner.getName() + " wins");
			if(isSidePot)
			{
				System.out.print(" Side Pot");
			}
			System.out.print(".\n");
			winner.setMoney(winner.getMoney()+pot);
		}
		
		//transfer money to winner or winners
	}
	public int compareHand(Player p1, Player p2, int rank, Card[] com)
	{//pre:compares two hands with the same handStrenght
	//post:returns an int 0=hands are same, 1=p1 wins, 2=p2 wins
		int win=0;
		switch(rank)//no need to compare royal flush
		{
			case 8:
				win=compareStraight(p1,p2,com);//straight flush same compare as straught
				break;
			case 7:
				win=compareFourPair(p1,p2,com);
				break;
			case 6:
				p1.sortHand();
				p2.sortHand();
				win=compareFullHosue(p1,p2,com);
				break;
			case 5:
				win=compareFlush(p1,p2,com);
				break;
			case 4:
				win=compareStraight(p1,p2,com);
				break;
			case 3:
				win=compareThreePair(p1,p2,com);
				break;
			case 2:
				win=compareTwoPair(p1,p2,com);
				break;
			case 1:
				win=comparePair(p1,p2,com);
				break;
			case 0:
				win=compareHighCard(p1,p2,com);
				break;
		}
		return win;
	}
	public int compareHighCard(Player p1, Player p2, Card[] com)
	{
		int topP1[]=new int[5];//top 5 cards for p1
		int topP2[]=new int[5];//top 5 cards for p2
		int temp;
		
		for(int i=0;i<com.length;i++)//puts com values into topP1
			topP1[i]=com[i].getValue();
		Arrays.sort(topP1);//sorts topP1
		for(int i=0;i<topP1.length;i++)//fills topP2 with com values
			topP2[i]=topP1[i];
		if(p1.getC1().getValue()>topP1[0])//if c1 is>topP1[0]replace it
			topP1[0]=p1.getC1().getValue();
		for(int i=0;i<topP1.length-1;i++)//sorts c1 to correct position
			if(topP1[i]>topP1[i+1])
			{
				temp=topP1[i];
				topP1[i]=topP1[i+1];
				topP1[i+1]=temp;
			}
		if(p1.getC2().getValue()>topP1[0])//if c2>topP1[0] replace it
			topP1[0]=p1.getC2().getValue();
		for(int i=0;i<topP1.length-1;i++)//sorts c2 to correct position
			if(topP1[i]>topP1[i+1])
			{
				temp=topP1[i];
				topP1[i]=topP1[i+1];
				topP1[i+1]=temp;
			}
//---------------player two----------------------------------------------------------
		Arrays.sort(topP2);//sorts topP2
		if(p2.getC1().getValue()>topP2[0])//if c1 is>topP2[0]replace it
			topP2[0]=p2.getC1().getValue();
		for(int i=0;i<topP2.length-1;i++)//sorts c1 to correct position
			if(topP2[i]>topP2[i+1])
			{
				temp=topP2[i];
				topP2[i]=topP2[i+1];
				topP2[i+1]=temp;
			}
		if(p2.getC2().getValue()>topP2[0])//if c2>topP2[0] replace it
			topP2[0]=p2.getC2().getValue();
		for(int i=0;i<topP2.length-1;i++)//sorts c2 to correct position
			if(topP2[i]>topP2[i+1])
			{
				temp=topP2[i];
				topP2[i]=topP2[i+1];
				topP2[i+1]=temp;
			}

		for(int i=4;i>=0;i--)
		{
			if(topP1[i]>topP2[i])
				return 1;
			else if(topP1[i]<topP2[i])
				return 2;
		}
		return 0;
	}
	public int comparePair(Player p1, Player p2, Card[] com)
	{
		int pair1=-1,pair2=-1, pairCard1=0, pairCard2=0, count=0, pos=0,temp;
		int top1[]=new int[3];
		int top2[]=new int[3];
		boolean pocket1=false, pocket2=false, com1=false, com2=false;
		
		if(p1.getC1().getValue()==p1.getC2().getValue())//finds p1 pocket pair
		{
			pocket1=true;
			pair1=p1.getC1().getValue();
		}
		if(p2.getC1().getValue()==p2.getC2().getValue())//finds p2 pocket pair
		{
			pocket2=true;
			pair2=p2.getC1().getValue();
		}		
		for(int i=0;i<com.length;i++)//checks for normal pair
		{
			if(p1.getC1().getValue()==com[i].getValue())
			{
				pairCard1=p1.getC1().getValue();
				pair1=com[i].getValue();
			}	
			if(p1.getC2().getValue()==com[i].getValue())
			{
				pairCard1=p1.getC2().getValue();
				pair1=com[i].getValue();
			}	
			if(p2.getC1().getValue()==com[i].getValue())
			{
				pairCard2=p2.getC1().getValue();
				pair2=com[i].getValue();
			}	
			if(p2.getC2().getValue()==com[i].getValue())
			{
				pairCard2=p2.getC2().getValue();
				pair2=com[i].getValue();
			}
		}
		Card check=com[0];
		count=1;
		for(int i=count;i<5;i++)//checks for compair
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue())
				{
					if(pair1==-1)
					{
						com1=true;
						pair1=check.getValue();
					}
					if(pair2==-1)
					{
						com2=true;
						pair2=check.getValue();
					}
				}
			}
			count++;
		}
		if(pair1>pair2)
			return 1;
		if(pair1<pair2)
			return 2;
		if(pocket1==true && pocket2==true)
			return 0;
		count=0;
		for(int i=0;i<5;i++)//finds 3 highest cards that arent the pair for p1
		{
			if(pair1!=com[i].getValue() && count<3)
			{
				top1[count]=com[i].getValue();
				count++;
			}
		}
		Arrays.sort(top1);
		if(com1!=true)
		{
			for(int i=3;i<5;i++)
			{
				if(top1[0]<com[i].getValue() && pair1!=com[i].getValue())
				{
					top1[0]=com[i].getValue();
					Arrays.sort(top1);
				}
			}
		}			
		count=0;
		for(int i=0;i<5;i++)//finds 3 highest cards that arent the pair for p2
		{
			if(pair2!=com[i].getValue() && count<3)
			{
				top2[count]=com[i].getValue();
				count++;
			}
		}
		Arrays.sort(top2);
		if(com2!=true)
		{
			for(int i=3;i<5;i++)
			{
				if(top2[0]<com[i].getValue() && pair2!=com[i].getValue())
				{
					top2[0]=com[i].getValue();
					Arrays.sort(top2);
				}
			}
		}		
		if(p1.getC1().getValue()!=pair1 && com1==false)//adds c2 to top1 if>top1[0] & !pair
		{
			if(p1.getC1().getValue()>top1[0])
			{
				top1[0]=p1.getC1().getValue();
				Arrays.sort(top1);
			}
		}
		else if(p1.getC2().getValue()!=pair1 && com1==false)//adds c2 to top1 if>top1[0] & !pair
		{
			if(p1.getC2().getValue()>top1[0])
			{
				top1[0]=p1.getC2().getValue();
				Arrays.sort(top1);
			}
		}
		else//if pair is in com
		{
			if(p1.getC1().getValue()>top1[0])
			{
				top1[0]=p1.getC1().getValue();
				Arrays.sort(top1);
			}
			if(p1.getC2().getValue()>top1[0])
			{
				top1[0]=p1.getC2().getValue();
				Arrays.sort(top1);
			}
		}
		if(p2.getC1().getValue()!=pair2 && com2==false)//adds c2 to top2 if>top2[0] & !pair
		{
			if(p2.getC1().getValue()>top2[0])
			{
				top2[0]=p2.getC1().getValue();
				Arrays.sort(top2);
			}
		}
		else if(p2.getC2().getValue()!=pair2 && com2==false)//adds c2 to top2 if>top2[0] & !pair
		{
			if(p2.getC2().getValue()>top2[0])
			{
				top2[0]=p2.getC2().getValue();
				Arrays.sort(top2);
			}
		}
		else//if pair is in com
		{
			if(p2.getC1().getValue()>top2[0])
			{
				top2[0]=p2.getC1().getValue();
				Arrays.sort(top2);
			}
			if(p2.getC2().getValue()>top2[0])
			{
				top2[0]=p2.getC2().getValue();
				Arrays.sort(top2);
			}
		}		
		for(int i=2;i>=0;i--)
		{
			if(top1[i]>top2[i])
				return 1;
			else if(top1[i]<top2[i])
				return 2;
		}
		return 0;
	}
	public int compareTwoPair(Player p1, Player p2, Card[] com)
	{
		int pair1=-1, pair1High=-1, pair2=-1, pair2High=-1, high1=-1, high2=-1, count=0, temp;
		Card check=com[0];
		
		if(p1.getC1().getValue()==p1.getC2().getValue())//finds pocket pair for p1
			pair1=p1.getC1().getValue();
		if(p2.getC1().getValue()==p2.getC2().getValue())//finds pocket pair for p2
			pair2=p2.getC1().getValue();
		for(int i=0;i<5;i++)//finds pairs for p1
		{
			if(p1.getC1().getValue()==com[i].getValue())
			{
				if(pair1!=-1)
					pair1High=com[i].getValue();
				else if(pair1==-1)
					pair1=com[i].getValue();
				else if(pair1High==-1)
					pair1High=com[i].getValue();
			}
			if(p1.getC2().getValue()==com[i].getValue())
			{
				if(pair1!=-1)
					pair1High=com[i].getValue();
				else if(pair1==-1)
					pair1=com[i].getValue();
				else if(pair1High==-1)
					pair1High=com[i].getValue();
			}
		}
		for(int i=0;i<5;i++)//finds pairs for p2
		{
			if(p2.getC1().getValue()==com[i].getValue())
			{
				if(pair2!=-1)
					pair2High=com[i].getValue();
				else if(pair2==-1)
					pair2=com[i].getValue();
				else if(pair2High==-1)
					pair2High=com[i].getValue();
			}
			if(p2.getC2().getValue()==com[i].getValue())
			{
				if(pair2!=-1)
					pair2High=com[i].getValue();
				else if(pair2==-1)
					pair2=com[i].getValue();
				else if(pair2High==-1)
					pair2High=com[i].getValue();
			}
		}
		
		if(pair1>pair1High)//orders pairs high to low
		{
			temp=pair1High;
			pair1High=pair1;
			pair1=temp;
		}
		if(pair2>pair2High)//orders pairs high to low
		{
			temp=pair2High;
			pair2High=pair2;
			pair2=temp;
		}

		count=1;
		for(int i=count;i<5;i++)//finds com pairs for p1 and orders incase of three seperate pairs finds best two
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue() && com[i].getValue()>pair1 && com[i].getValue()!=pair1High)//if com has a pair and its greater that pair1
				{
					pair1=com[i].getValue();
					if(pair1>pair1High)//orders pairs high to low
					{
						temp=pair1High;
						pair1High=pair1;
						pair1=temp;
					}
				}
			}
			count++;
		}
		count=1;
		for(int i=count;i<5;i++)//finds com pairs for p2 and orders incase of three seperate pairs finds best two
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue() && com[i].getValue()>pair2 && com[i].getValue()!=pair2High)//if com has a pair and its greater that pair1
				{
					pair2=com[i].getValue();
					if(pair2>pair2High)//orders pairs high to low
					{
						temp=pair2High;
						pair2High=pair2;
						pair2=temp;
					}
				}
			}
			count++;
		}

		if(pair1High>pair2High)
			return 1;
		if(pair1High<pair2High)
			return 2;
		if(pair1>pair2)
			return 1;
		if(pair1<pair2)
			return 2;
		
		//finds the high card that isnt a pair and compare
		if(p1.getC1().getValue()!=pair1 && p1.getC1().getValue()!=pair1High)
			high1=p1.getC1().getValue();
		if(p1.getC2().getValue()!=pair1 && p1.getC2().getValue()!=pair1High)
			high1=p1.getC2().getValue();
		if(p2.getC1().getValue()!=pair2 && p2.getC1().getValue()!=pair2High)
			high2=p2.getC1().getValue();
		if(p2.getC2().getValue()!=pair2 && p2.getC2().getValue()!=pair2High)
			high2=p2.getC2().getValue();	
		for(int i=0;i<5;i++)
			if(com[i].getValue()>high1 && com[i].getValue()!=pair1 && com[i].getValue()!=pair1High)
				high1=com[i].getValue();
		for(int i=0;i<5;i++)
			if(com[i].getValue()>high2 && com[i].getValue()!=pair2 && com[i].getValue()!=pair2High)
				high2=com[i].getValue();
		if(high1>high2)
			return 1;
		if(high1<high2)
			return 2;
		return 0;
	}
	public int compareThreePair(Player p1, Player p2, Card[] com)
	{
		int count1=1, count2=1, cardCount=0, pos=-1, tp1=-1, tp2=-1;
		int top1[]=new int[2];
		int top2[]=new int[2];
		Card check=com[0];
		//finds 3 of a kind for p1 
		if(p1.getC1().getValue()==p1.getC2().getValue())//checks pocket pair
			count1=2;
		for(int i=0;i<5;i++)
			if(p1.getC1().getValue()==com[i].getValue())
				count1++;
		for(int i=0;i<5;i++)
			if(p1.getC2().getValue()==com[i].getValue())
				count2++;
		if(count1==3)
			tp1=p1.getC1().getValue();
		if(count2==3)
			tp1=p1.getC2().getValue();
		//search for three of a kind in com		
		if(count1!=3 && count2!=3)
		{
			for(int i=0;i<5;i++)
			{
				for(int k=0;k<5;k++)
				{
					if(check.getValue()==com[i].getValue())
					{
						cardCount++;
						pos=i;
					}
					check=com[k];
				}
				if(cardCount==3)
					tp1=com[pos].getValue();
				cardCount=0;
			}
		}
		//finds 3 of a kind for p2
		count1=1;
		count2=1;
		pos=0;
		if(p2.getC1().getValue()==p2.getC2().getValue())//checks for pocket pair
			count1=2;
		for(int i=0;i<5;i++)
			if(p2.getC1().getValue()==com[i].getValue())
				count1++;
		for(int i=0;i<5;i++)
			if(p2.getC2().getValue()==com[i].getValue())
				count2++;
		if(count1==3)
			tp2=p2.getC1().getValue();
		if(count2==3)
			tp2=p2.getC2().getValue();
		//search for three of a kind in com
		if(count1!=3 && count2!=3)
		{
			for(int i=0;i<5;i++)
			{
				for(int k=0;k<5;k++)
				{
					if(check.getValue()==com[i].getValue())
					{
						cardCount++;
						pos=i;
					}
					check=com[k];
				}
				if(cardCount==3)
					tp2=com[pos].getValue();
				cardCount=0;
			}
		}
		if(tp1>tp2)
			return 1;
		if(tp1<tp2)
			return 2;
		//checks who has higher cards
		for(int i=0;i<2;i++)
		{
			top1[i]=-1;
			top2[i]=-1;
		}
		//sets highest 2 for p1 and p2
		if(p1.getC1().getValue()!=tp1)
			top1[0]=p1.getC1().getValue();
		if(p1.getC2().getValue()!=tp1)
			top1[1]=p1.getC2().getValue();
		if(p2.getC1().getValue()!=tp2)
			top2[0]=p2.getC1().getValue();
		if(p2.getC2().getValue()!=tp2)
			top2[0]=p2.getC2().getValue();
		Arrays.sort(top1);
		Arrays.sort(top2);
		for(int i=0;i<5;i++)
		{
			if(com[i].getValue()!=tp1 && com[i].getValue()>top1[0])
			{
				top1[0]=com[i].getValue();
				Arrays.sort(top1);
			}
			if(com[i].getValue()!=tp2 && com[i].getValue()>top2[0])
			{
				top2[0]=com[i].getValue();
				Arrays.sort(top2);
			}
		}
		for(int i=1;i>=0;i--)
		{
			if(top1[i]>top2[i])
				return 1;
			if(top1[i]<top2[i])
				return 2;
		}
		return 0;
	}
	public int compareFourPair(Player p1, Player p2, Card[] com)
	{
		int count1=1, count2=1, cardCount=0, pos=-1, fp1=-1, fp2=-1, high1=-1, high2=-1;
		Card check=com[0];
		//finds four of a kind for p1
		if(p1.getC1().getValue()==p1.getC2().getValue())//checks pocket pair
			count1=2;
		for(int i=0;i<5;i++)
			if(p1.getC1().getValue()==com[i].getValue())
				count1++;
		for(int i=0;i<5;i++)
			if(p1.getC2().getValue()==com[i].getValue())
				count2++;
		if(count1==4)
			fp1=p1.getC1().getValue();
		if(count2==4)
			fp1=p1.getC2().getValue();
		//search for four of a kind in com
		if(count1!=4 && count2!=4)
		{
			for(int i=0;i<5;i++)
			{
				for(int k=0;k<5;k++)
				{
					if(check.getValue()==com[i].getValue())
					{
						cardCount++;
						pos=i;
					}
					check=com[k];
				}
				if(cardCount==4)
					fp1=com[pos].getValue();
				cardCount=0;
			}
		}
		//finds 4 of a kind for p2
		count1=1;
		count2=1;
		pos=0;
		if(p2.getC1().getValue()==p2.getC2().getValue())//checks for pocket pair
			count1=2;
		for(int i=0;i<5;i++)
			if(p2.getC1().getValue()==com[i].getValue())
				count1++;
		for(int i=0;i<5;i++)
			if(p2.getC2().getValue()==com[i].getValue())
				count2++;
		if(count1==4)
			fp2=p2.getC1().getValue();
		if(count2==4)
			fp2=p2.getC2().getValue();
		//search for three of a kind in com
		if(count1!=4 && count2!=4)
		{
			for(int i=0;i<5;i++)
			{
				for(int k=0;k<5;k++)
				{
					if(check.getValue()==com[i].getValue())
					{
						cardCount++;
						pos=i;
					}
					check=com[k];
				}
				if(cardCount==4)
					fp2=com[pos].getValue();
				cardCount=0;
			}
		}
		if(fp1>fp2)
			return 1;
		if(fp1<fp2)
			return 2;
		//finds the high card for both players to compare
		if(p1.getC1().getValue()!=fp1)
			high1=p1.getC1().getValue();
		if(p1.getC2().getValue()!=fp1 && p1.getC2().getValue()>high1)
			high1=p1.getC2().getValue();
		if(p2.getC1().getValue()!=fp2 && p2.getC2().getValue()>high2)
			high2=p2.getC1().getValue();
		if(p2.getC2().getValue()!=fp2)
			high2=p2.getC2().getValue();
		for(int i=0;i<5;i++)
		{
			if(com[i].getValue()!=fp1 && com[i].getValue()>high1)
				high1=com[i].getValue();
			if(com[i].getValue()!=fp2 && com[i].getValue()>high2)
				high2=com[i].getValue();
		}
		if(high1>high2)
			return 1;
		if(high1<high2)
			return 2;
		return 0;
	}
	public int compareStraight(Player p1, Player p2, Card[] com)
	{
		int high1=p1.getC1().getValue(), low1=p1.getC1().getValue(), high2=p2.getC1().getValue(), low2=p2.getC1().getValue(), count1=1, count2=1;;
		//searches high for p1 c1
		for(int i=0;i<5;i++)
		{
			if(high1==p1.getC2().getValue()-1)
			{
				high1=p1.getC2().getValue();
				count1++;
			}
			if(high1==com[i].getValue()-1)
			{
				high1=com[i].getValue();
				count1++;
				i=-1;
			}
		}
		//searches high for p2 c1	
		for(int i=0;i<5;i++)
		{
			if(high2==p2.getC2().getValue()-1)
			{
				high2=p2.getC2().getValue();
				count2++;
			}
			if(high2==com[i].getValue()-1)
			{
				high2=com[i].getValue();
				count2++;
				i=-1;
			}
		}

		//searches low for p1 c1
		for(int i=0;i<5;i++)
		{
			if(low1==p1.getC2().getValue()+1)
			{
				low1=p1.getC2().getValue();
				count1++;
			}
			if(low1==com[i].getValue()+1)
			{
				low1=com[i].getValue();
				count1++;
				i=-1;
			}
		}
		if(low1==0)//adds ace from com to low count if 2 is low
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12)
					count1++;
		if(low1==0)//adds ace from hand to low count if 2 is low
			if(p1.getC1().getValue()==12||p1.getC2().getValue()==12)
				count1++;
		//searches low for p2 c1
		for(int i=0;i<5;i++)
		{
			if(low2==p2.getC2().getValue()+1)
			{
				low2=p2.getC2().getValue();
				count2++;
			}
			if(low2==com[i].getValue()+1)
			{
				low2=com[i].getValue();
				count2++;
				i=-1;
			}
		}
		if(low2==0)//adds ace from com to low count if 2 is low
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12)
					count2++;
		if(low2==0)//adds ace from hand to low count if 2 is low
			if(p2.getC1().getValue()==12||p2.getC2().getValue()==12)
				count2++;
		//search for card 2
		if(count1<5)//searches p1 for c2 if no straight yet
		{
			high1=p1.getC2().getValue();
			low1=p1.getC2().getValue();
			count1=1;
			for(int i=0;i<5;i++)//searches for high
			{
				if(high1==p1.getC1().getValue()-1)
				{
					high1=p1.getC1().getValue();
					count1++;
				}
				if(high1==com[i].getValue()-1)
				{
					high1=com[i].getValue();
					count1++;
					i=-1;
				}
			}
			for(int i=0;i<5;i++)//searches for low
			{
				if(low1==p1.getC1().getValue()+1)
				{
					low1=p1.getC1().getValue();
					count1++;
				}
				if(low1==com[i].getValue()+1)
				{
					low1=com[i].getValue();
					count1++;
					i=-1;
				}
			}
			if(low1==0)//adds ace from com to low count if 2 is low
				for(int i=0;i<5;i++)
					if(com[i].getValue()==12)
						count1++;
			if(low1==0)//adds ace from hand to low count if 2 is low
				if(p1.getC1().getValue()==12||p1.getC2().getValue()==12)
					count1++;
		}
		if(count2<5)//searches p2 for c2 if no straight yet
		{
			high2=p2.getC2().getValue();
			low2=p2.getC2().getValue();
			count2=1;
			for(int i=0;i<5;i++)//searches for high
			{
				if(high2==p2.getC1().getValue()-1)
				{
					high2=p2.getC1().getValue();
					count2++;
				}
				if(high2==com[i].getValue()-1)
				{
					high2=com[i].getValue();
					count2++;
					i=-1;
				}
			}
			for(int i=0;i<5;i++)//searches for low
			{
				if(low2==p2.getC1().getValue()+1)
				{
					low2=p2.getC1().getValue();
					count2++;
				}
				if(low2==com[i].getValue()+1)
				{
					low2=com[i].getValue();
					count2++;
					i=-1;
				}
			}
			if(low2==0)//adds ace from com to low count if 2 is low
				for(int i=0;i<5;i++)
					if(com[i].getValue()==12)
						count2++;
			if(low2==0)//adds ace from hand to low count if 2 is low
				if(p2.getC1().getValue()==12||p2.getC2().getValue()==12)
					count2++;
		}
		if(count1<5)//finds high1 in com
		{
			high1=com[0].getValue();
			for(int i=0;i<5;i++)
				if(high1<com[i].getValue())
					high1=com[i].getValue();
		}
		if(count2<5)//finds high2 in com
		{
			high2=com[0].getValue();
			for(int i=0;i<5;i++)
				if(high2<com[i].getValue())
					high2=com[i].getValue();
		}
		if(high1>high2)
			return 1;
		if(high1<high2)
			return 2;
		return 0;
	}
	public int compareFlush(Player p1, Player p2, Card[] com)
	{
		int count1=1, count2=1;
		int top1[]=new int[5];
		int top2[]=new int[5];
		for(int i=0;i<5;i++)//fills top arrays with -1
		{
			top1[i]=-1;
			top2[i]=-1;
		}
		//finds flush for c1 for p1/p2
		for(int i=0;i<5;i++)//compares c1 suit with com
		{
			if(p1.getC1().getSuit().equals(com[i].getSuit()))
				count1++;
			if(p2.getC1().getSuit().equals(com[i].getSuit()))
				count2++;
		}
		if(p1.getC1().getSuit().equals(p1.getC2().getSuit()))//checks c2 for same suit for p1
		{
			count1++;
			if(top1[0]<p1.getC2().getValue())
				top1[0]=p1.getC2().getValue();
		}
		Arrays.sort(top1);
		if(p2.getC1().getSuit().equals(p2.getC2().getSuit()))//checks c2 for same suit for p2
		{
			count2++;
			if(top2[0]<p2.getC2().getValue())
				top2[0]=p2.getC2().getValue();
		}
		Arrays.sort(top2);
		if(count1>=5)//fills top1 if flush with c1
		{
			top1[0]=p1.getC1().getValue();
			Arrays.sort(top1);
			for(int i=0;i<5;i++)
				if(top1[0]<com[i].getValue() && com[i].getSuit().equals(p1.getC1().getSuit()))
				{
					top1[0]=com[i].getValue();
					Arrays.sort(top1);
				}
		}
		if(count2>=5)//fills top2 if flush with c1
		{
			top2[0]=p2.getC1().getValue();
			Arrays.sort(top2);
			for(int i=0;i<5;i++)
				if(top2[0]<com[i].getValue() && com[i].getSuit().equals(p2.getC1().getSuit()))
				{
					top2[0]=com[i].getValue();
					Arrays.sort(top2);
				}
		}
		//finds flush if not with c2 for p1/p2
		if(count1<5)//checks for flush with c2 for p1
		{
			count1=1;
			for(int i=0;i<5;i++)
				if(p1.getC2().getSuit().equals(com[i].getSuit()))
					count1++;
			if(count1>=5)//fills top1 if flush with c2
			{
				top1[0]=p1.getC2().getValue();
				Arrays.sort(top1);
				for(int i=0;i<5;i++)
					if(top1[0]<com[i].getValue() && com[i].getSuit().equals(p1.getC2().getSuit()))
					{
						top1[0]=com[i].getValue();
						Arrays.sort(top1);
					}
			}
		}
		if(count2<5)
		{
			count2=1;
			for(int i=0;i<5;i++)
				if(p2.getC2().getSuit().equals(com[i].getSuit()))
				{
					count2++;
					top2[0]=com[i].getValue();
					Arrays.sort(top2);
				}
			if(count2>=5)//fills top2 if flush with c2
			{
				top2[0]=p2.getC2().getValue();
				Arrays.sort(top2);
				for(int i=0;i<5;i++)
					if(top2[0]<com[i].getValue() && com[i].getSuit().equals(p2.getC1().getSuit()))
					{
						top2[0]=com[i].getValue();
						Arrays.sort(top2);
					}
			}
		}
		//if flush is in the com for p1/p2
		if(count1<5)//fills top1 if flush with c1
		{
			for(int i=0;i<5;i++)//fills top arrays with -1
				top1[i]=-1;
			for(int i=0;i<5;i++)
				if(top1[0]<com[i].getValue())
					{
						top1[0]=com[i].getValue();
						Arrays.sort(top1);
					}
		}
		if(count2<5)//fills top2 if flush with c1
		{
			for(int i=0;i<5;i++)//fills top arrays with -1
				top2[i]=-1;
			for(int i=0;i<5;i++)
				if(top2[0]<com[i].getValue())
				{
					top2[0]=com[i].getValue();
					Arrays.sort(top2);
				}
		}
		for(int i=4;i>=0;i--)
		{
			if(top1[i]>top2[i])
				return 1;
			if(top1[i]<top2[i])
				return 2;
		}
		return 0;	
	}
	public int compareFullHosue(Player p1, Player p2, Card[] com)
	{
		int p1TP=-1, p1TWP=-1, p2TP=-1, p2TWP=-1, count1=1, count2=1, count;
		Card check=com[0];
		//finds three of a kind for p1
		for(int i=0;i<5;i++)//searches for c1
			if(p1.getC1().getValue()==com[i].getValue())
				count1++;
		if(p1.getC1().getValue()==p1.getC2().getValue())
			count1++;
		if(count1==3)
			p1TP=p1.getC1().getValue();
		if(count1!=3)//searches for c2
		{
			count1=0;
			for(int i=0;i<5;i++)
				if(p1.getC2().getValue()==com[i].getValue())
					count1++;
			if(count1==3)
				p1TP=p1.getC2().getValue();
		}
		//searches com
		count1=-1;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<5;k++)
			{
				if(check.getValue()==com[i].getValue())
				{
					count1++;
				}
				if(count1==3 && check.getValue()>p1TP)
				{
					p1TP=check.getValue();		
					count1++;
				}
				check=com[k];
			}
			count1=-1;
		}	
		//finds pair for p1
		if(p1TP!=p1.getC1().getValue())//searches c1
		{
			count1=1;
			for(int i=0;i<5;i++)
			{
				if(p1.getC1().getValue()==com[i].getValue())
					p1TWP=com[i].getValue();
			}
		}
		if(p1TP!=p1.getC2().getValue())//searches c2
		{
			count1=1;
			for(int i=0;i<5;i++)
				if(p1.getC2().getValue()==com[i].getValue() && com[i].getValue()>p1TWP)
					p1TWP=com[i].getValue();
		}
		//searches com
		check=com[0];
		count=1;
		for(int i=count;i<5;i++)
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue() && check.getValue()!=p1TP && check.getValue()>p1TWP)
					p1TWP=com[k].getValue();
			}
			count++;
		}
		System.out.println(p1TP + " " + p1TWP);
		
		//finds three of a kind for p2
		for(int i=0;i<5;i++)//searches for c1
			if(p2.getC1().getValue()==com[i].getValue())
				count2++;
		if(p2.getC1().getValue()==p2.getC2().getValue())
			count2++;
		if(count2==3)
			p2TP=p2.getC1().getValue();			
		if(count2!=3)//searches for c2
		{
			count2=0;
			for(int i=0;i<5;i++)
				if(p2.getC2().getValue()==com[i].getValue())
					count2++;
			if(count2==3)
				p2TP=p2.getC2().getValue();
		}
		//searches com
		count2=-1;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<5;k++)
			{
				if(check.getValue()==com[i].getValue())
				{
					count2++;
				}
				if(count2==3 && check.getValue()>p2TP)
				{
					p2TP=check.getValue();		
					count2++;
				}
				check=com[k];
			}
			count2=-1;
		}		
		//finds pair for p2
		if(p2TP!=p2.getC1().getValue())//searches c1
		{
			count2=1;
			for(int i=0;i<5;i++)
			{
				if(p2.getC1().getValue()==com[i].getValue())
					p2TWP=com[i].getValue();
			}
		}
		if(p2TP!=p2.getC2().getValue())//searches c2
		{
			count2=1;
			for(int i=0;i<5;i++)
				if(p2.getC2().getValue()==com[i].getValue() && com[i].getValue()>p2TWP)
					p2TWP=com[i].getValue();
		}		
		//searches com
		check=com[0];
		count=1;
		for(int i=count;i<5;i++)
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue() && check.getValue()!=p2TP && check.getValue()>p2TWP)
					p2TWP=com[k].getValue();
			}
			count++;
		}
		if(p1TP>p2TP)
			return 1;
		if(p1TP<p2TP)
			return 2;
		if(p1TWP>p2TWP)
			return 1;
		if(p1TWP<p2TWP)
			return 2;
		return 0;
	}
}