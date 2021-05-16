//determines player hand strength after flop
public class Rank
{
	public int rankHand(Card c1, Card c2, Card[] com)
	{//Pre:recieves the cards in a hand and the community cards on the table
	//Post:sends the hand through multiple methods and returns the best possible ranking for the hand
	
		boolean p=false,tp=false,trp=false,s=false,f=false,fh=false,fp=false,sf=false,rf=false;

		p=searchPair(c1, c2, com);
		tp=searchTwoPair(c1,c2,com);
		trp=searchThreePair(c1,c2,com);
		fp=searchFourPair(c1,c2,com);
		s=searchStraight(c1,c2,com);
		f=searchFlush(c1,c2,com);
		fh=searchFullHouse(c1,c2,com);
		sf=searchStraightFlush(c1,c2,com);
		rf=searchRoyalFlush(c1,c2,com);
		if(rf==true)
			return 9;
		else if(sf==true)
			return 8;
		else if(fp==true)
			return 7;
		else if(fh==true)
			return 6;
		else if(f==true)
			return 5;
		else if(s==true)
			return 4;
		else if(trp==true)
			return 3;
		else if(tp==true)
			return 2;
		else if(p==true)
			return 1;
		return 0;
	}
	public boolean searchPair(Card c1, Card c2, Card[] com)
	{
		if(c1.getValue()==c2.getValue())//return true if you have a pocket pair
			return true;
		else if(searchComPair(com)==true)//searches com for a pair
			return true;
		for(int i=0;i<5;i++)
		{
			if(c1.getValue()==com[i].getValue())
				return true;
			else if(c2.getValue()==com[i].getValue())
				return true;
		}
		return false;
	}
	public boolean searchTwoPair(Card c1, Card c2, Card[] com)
	{
		if(c1.getValue()==c2.getValue())//if hand is pair checks com for second pair
			return searchComPair(com);	
		if(searchComPair(com)==true)//if there is a pair in com, searches hand for pair with com
			for(int i=0;i<5;i++)
			{
				if(c1.getValue()==com[i].getValue())
					return true;
				else if(c2.getValue()==com[i].getValue())
					return true;
			}
		if(searchComTwoPair(com)==true)
			return true;
		boolean one=false, two=false;	
		for(int i=0;i<5;i++)
		{
			if(one!=true)
				if(c1.getValue()==com[i].getValue())
					one=true;
			if(two!=true)
				if(c2.getValue()==com[i].getValue())
					two=true;
		}
		if((one==true)&&(two==true))
			return true;
		return false;
	}
	public boolean searchThreePair(Card c1, Card c2, Card[] com)
	{
		int count=0, count2=1;//add search com three pair
		if(searchComThreePair(com)==true)
			return true;
		count=1;
		if(c1.getValue()==c2.getValue())
			count=2;
		for(int i=0; i<5; i++)
			if(c1.getValue()==com[i].getValue())
				count++;
		for(int i=0; i<5; i++)
			if(c2.getValue()==com[i].getValue())
				count2++;
		if(count>=3)
			return true;
		if(count2>=3)
			return true;
		return false;
	}
	public boolean searchFourPair(Card c1, Card c2, Card[] com)
	{
		int count=0, count2=1;//add search com three pair
		if(searchComFourPair(com)==true)
			return true;
		count=1;
		if(c1.getValue()==c2.getValue())
			count=2;
		for(int i=0; i<5; i++)
			if(c1.getValue()==com[i].getValue())
				count++;
		for(int i=0; i<5; i++)
			if(c2.getValue()==com[i].getValue())
				count2++;	
		if(count==4)
			return true;
		if(count2==4)
			return true;
		return false;
	}
	public boolean searchComPair(Card[] com)
	{											   
		Card check=com[0];
		int count=1;
		for(int i=count;i<5;i++)
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue())
					return true;
			}
			count++;
		}
		return false;
	}
	public boolean searchComTwoPair(Card[] com)
	{												  
		Card check=com[0];							 
		int count=1, pairCount=0;					  
		for(int i=count;i<5;i++)
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue())
				{
					pairCount++;
					check=com[k];
				}
				else
				{
					check=com[k];
				}
			}
			count++;
		}
		if(pairCount==2)
			return true;
		return false;
	}
	public boolean searchComThreePair(Card[] com)
	{													
		Card check=com[0];
		int cardCount=-1;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<5;k++)
			{
				if(check.getValue()==com[i].getValue())
				{
					cardCount++;
					check=com[k];
				}
				else
				{
					check=com[k];
				}
			}
			if(cardCount==3)
				return true;
			cardCount=0;
		}
		return false;
	}
	public boolean searchComFourPair(Card[] com)
	{													
		Card check=com[0];
		int cardCount=-1;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<5;k++)
			{
				if(check.getValue()==com[i].getValue())
				{
					cardCount++;
					check=com[k];
				}
				else
				{
					check=com[k];
				}
			}
			if(cardCount==4)
				return true;
			cardCount=0;
		}
		return false;
	}
	public boolean searchStraight(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		
		if(searchComStraight(com)==true)
			return true;
		for(int i=0;i<5;i++)
		{
			if(high==c2.getValue()-1)
			{
				high=c2.getValue();
				count++;
			}
			if(high==com[i].getValue()-1)
			{
				high=com[i].getValue();
				count++;
				i=-1;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==c2.getValue()+1)
			{
				low=c2.getValue();
				count++;
			}
			if(low==com[i].getValue()+1)
			{
				low=com[i].getValue();
				count++;
				i=-1;
			}
		}
		if(low==0)//adds ace from com to low count if 2 is low
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to low count if 2 is low
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		if(count>=5)
			return true;
		high=c2.getValue();
		low=c2.getValue();
		count=1;
		for(int i=0;i<5;i++)
		{
			if(high==c1.getValue()-1)
			{
				high=c1.getValue();
				count++;
			}
			if(high==com[i].getValue()-1)
			{
				high=com[i].getValue();
				count++;
				i=-1;
			}
		}

		for(int i=0;i<5;i++)
		{
			if(low==c1.getValue()+1)
			{
				low=c1.getValue();
				count++;
			}
			if(low==com[i].getValue()+1)
			{
				low=com[i].getValue();
				count++;
				i=-1;
			}
		}
		if(low==0)//adds ace from com to high count if king is high
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		if(count>=5)
			return true;
		return false;
	}
	public boolean searchComStraight(Card[] com)
	{
		int high=com[0].getValue(),low=com[0].getValue(), count=1;
		
		for(int i=0;i<5;i++)
		{
			if(high==com[i].getValue()-1)
			{
				high=com[i].getValue();
				count++;
				i=0;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==com[i].getValue()+1)
			{
				low=com[i].getValue();
				count++;
				i=0;
			}
		}
		if(low==0)//adds ace to high straight if there
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12)
					count++;
		if(count>=5)
			return true;
		return false;
	}
	public boolean searchFlush(Card c1, Card c2, Card[] com)
	{
		int count=1;
		if(searchComFlush(com)==true)
			return true;
		for(int i=0;i<5;i++)
			if(c1.getSuit().equals(com[i].getSuit()))
				count++;
		if(c1.getSuit().equals(c2.getSuit()))
			count++;
		if(count>=5)
			return true;
		count=1;
		for(int i=0;i<5;i++)
			if(c2.getSuit().equals(com[i].getSuit()))
				count++;
		if(c1.getSuit().equals(c2.getSuit()))
			count++;
		if(count>=5)
			return true;
		return false;
	}
	public boolean searchComFlush(Card[] com)
	{
		String check=com[0].getSuit();
		for(int i=1;i<5;i++)
			if(!check.equals(com[i].getSuit()))
				return false;
		return true;
	}
	public boolean searchFullHouse(Card c1, Card c2, Card[] com)
	{
		int c1Count=1,c2Count=1;

		if(searchComFullHouse(com)==true)
			return true;
		if(c1.getValue()==c2.getValue())//if pocket pair
		{
			c1Count=2;
			if(searchComThreePair(com)==true)//if pocket pair and three kind in com
				return true;
			if(searchComPair(com)==true)//if pair in com searches for third card matching pocket pair
			{
				for(int i=0;i<5;i++)
					if(c1.getValue()==com[i].getValue())
						c1Count++;
				if(c1Count>=3)
					return true;
			}
		}
		else if(searchComTwoPair(com)==true)
		{
			for(int i=0;i<5;i++)
				if(com[i].getValue()==c1.getValue())
					c1Count++;
			for(int i=0;i<5;i++)
				if(com[i].getValue()==c2.getValue())
					c2Count++;
			if(c1Count==3 || c2Count==3)
				return true;
		}
		else
		{
			for(int i=0;i<5;i++)
				if(com[i].getValue()==c1.getValue())
					c1Count++;
			for(int i=0;i<5;i++)
				if(com[i].getValue()==c2.getValue())
					c2Count++;
			if((c1Count==2&&c2Count==3)||(c1Count==3&&c2Count==2))
				return true;
			if((c1Count==2 || c2Count==2) && searchComThreePair(com)==true)
				return true;
		}
		return false;
	}
	public boolean searchComFullHouse(Card[] com)
	{
		Card check=com[0];
		int cardCount=-1;
		int tp=-1, temp=-1,twp=-1;
		for(int i=0;i<5;i++)//checks for three of a kind and stores the value of the cards
		{
			for(int k=0;k<5;k++)
			{
				if(check.getValue()==com[i].getValue())
				{
					cardCount++;
					temp=com[i].getValue();
					check=com[k];
				}
				else
				{
					check=com[k];
				}
			}
			if(cardCount==3)
				tp=temp;
			cardCount=0;
		}
		if(tp==-1)
			return false;
		check=com[0];
		int count=1;
		for(int i=count;i<5;i++)//checks for pair and if pair value is != tok value return true
		{
			for(int k=count;k<5;k++)
			{
				check=com[count-1];
				if(check.getValue()==com[k].getValue())
					twp=com[k].getValue();
			}
			count++;
			if(twp!=tp && twp!=-1)
				return true;
		}	
		
		return false;
	}
	public boolean searchStraightFlush(Card c1, Card c2, Card[] com)
	{
		if(searchComStraightFlush(com)==true)
			return true;
		int high=c1.getValue(), low=c1.getValue(), count=1;
		String suit=c1.getSuit();
		for(int i=0;i<5;i++)
		{
			if(high==c2.getValue()-1 && suit.equals(c2.getSuit()))
			{
				high=c2.getValue();
				count++;
			}
			if(high==com[i].getValue()-1 && suit.equals(com[i].getSuit()))
			{
				high=com[i].getValue();
				count++;
				i=-1;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==c2.getValue()+1 && suit.equals(c2.getSuit()))
			{
				low=c2.getValue();
				count++;
			}
			if(low==com[i].getValue()+1 && suit.equals(com[i].getSuit()))
			{
				low=com[i].getValue();
				count++;
				i=-1;
			}
		}
		if(low==0)//adds ace from com to low count if 2 is low
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
					count++;
		if(low==0)//adds ace from handto low count if 2 is low
			if((c1.getValue()==12 && suit.equals(c1.getSuit())) || (c2.getValue()==12 && suit.equals(c2.getSuit())))
				count++;
		if(count>=5)
			return true;
		high=c2.getValue();
		low=c2.getValue();
		suit=c2.getSuit();
		count=1;
		for(int i=0;i<5;i++)
		{
			if(high==c1.getValue()-1 && suit.equals(c1.getSuit()))
			{
				high=c1.getValue();
				count++;
			}
			if(high==com[i].getValue()-1 && suit.equals(com[i].getSuit()))
			{
				high=com[i].getValue();
				count++;
				i=-1;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==c1.getValue()+1 && suit.equals(c1.getSuit()))
			{
				low=c1.getValue();
				count++;
			}
			if(low==com[i].getValue()+1 && suit.equals(com[i].getSuit()))
			{
				low=com[i].getValue();
				count++;
				i=-1;
			}
		}
		if(low==0)//adds ace from com to low count if 2 is low
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
					count++;
		if(low==0)//adds ace from handto low count if 2 is low
			if((c1.getValue()==12 && suit.equals(c1.getSuit())) || (c2.getValue()==12 && suit.equals(c2.getSuit())))
				count++;
		if(count>=5)
			return true;
		return false;		
	}
	public boolean searchComStraightFlush(Card[] com)
	{
		int high=com[0].getValue(),low=com[0].getValue(), count=1;
		String suit=com[0].getSuit();
		
		for(int i=0;i<5;i++)
		{
			if(high==com[i].getValue()-1)
			{
				if(!suit.equals(com[i].getSuit()))
					return false;
				high=com[i].getValue();
				count++;
				i=0;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==com[i].getValue()+1)
			{
				if(!suit.equals(com[i].getSuit()))
					return false;
				low=com[i].getValue();
				count++;
				i=0;
			}
		}
		if(low==0)//adds ace to high straight if there
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
					count++;		
		if(count>=5)
			return true;
		return false;
	}
	public boolean searchRoyalFlush(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		String suit=c1.getSuit();
		boolean ace=false;
		if(searchComRoyalFlush(com)==true)
		return true;
		for(int i=0;i<5;i++)
		{
			if(high==c2.getValue()-1 && suit.equals(c2.getSuit()))
			{
				high=c2.getValue();
				count++;
			}
			if(high==com[i].getValue()-1 && suit.equals(com[i].getSuit()))
			{
				high=com[i].getValue();
				count++;
				i=-1;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==c2.getValue()+1 && suit.equals(c2.getSuit()))
			{
				low=c2.getValue();
				count++;
			}
			if(low==com[i].getValue()+1 && suit.equals(com[i].getSuit()))
			{
				low=com[i].getValue();
				count++;
				i=-1;
			}
		}
		if(low==0)//adds ace from com to high count if king is high
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(low==0)//adds ace from hand to high count if king is high
			if((c1.getValue()==12 && suit.equals(c1.getSuit())) || (c2.getValue()==12 && suit.equals(c2.getSuit())))
			{
				ace=true;
				count++;
			}
		if(count>=5 && ace==true)
			return true;
		high=c2.getValue();
		low=c2.getValue();
		suit=c2.getSuit();
		count=1;
		ace=false;
		for(int i=0;i<5;i++)
		{
			if(high==c1.getValue()-1 && suit.equals(c1.getSuit()))
			{
				high=c1.getValue();
				count++;
			}
			if(high==com[i].getValue()-1 && suit.equals(com[i].getSuit()))
			{
				high=com[i].getValue();
				count++;
				i=-1;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==c1.getValue()+1 && suit.equals(c1.getSuit()))
			{
				low=c1.getValue();
				count++;
			}
			if(low==com[i].getValue()+1 && suit.equals(com[i].getSuit()))
			{
				low=com[i].getValue();
				count++;
				i=-1;
			}
		}
		if(low==0)//adds ace from com to high count if king is high
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(low==0)//adds ace from hand to high count if king is high
			if((c1.getValue()==12 && suit.equals(c1.getSuit())) || (c2.getValue()==12 && suit.equals(c2.getSuit())))
			{
				ace=true;
				count++;
			}
		if(count>=5 && ace==true)
			return true;
		return false;		
	}
	public boolean searchComRoyalFlush(Card[] com)
	{
		int high=com[0].getValue(),low=com[0].getValue(), count=1;
		String suit=com[0].getSuit();
		boolean ace=false;
		
		for(int i=0;i<5;i++)
		{
			if(high==com[i].getValue()-1)
			{
				if(!suit.equals(com[i].getSuit()))
					return false;
				high=com[i].getValue();
				count++;
				i=0;
			}
		}
		for(int i=0;i<5;i++)
		{
			if(low==com[i].getValue()+1)
			{
				if(!suit.equals(com[i].getSuit()))
					return false;
				low=com[i].getValue();
				count++;
				i=0;
			}
		}
		if(low==0)//adds ace to high straight if there
			for(int i=0;i<5;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(count>=5 && ace==true)
			return true;
		return false;
	}
}