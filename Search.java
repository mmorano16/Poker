//searches each of players hands to determine handstrength at different intervals for odds of betting
public class Search
{
	//pretty sure the sgap and fgap funcitons dont work
	//consider deleting and not using for AI
	public String searchFlop(Card c1, Card c2, Card[] com)
	{
		int temp=0, high=0;
		String sGap="", fGap="", result="";
		boolean p=false, tp=false,trp=false, s=false, f=false, fp=false, fh=false, sf=false, rf=false;
		
		p=flopPair(c1,c2,com);
		tp=flopTwoPair(c1,c2,com);		
		trp=flopThreePair(c1,c2,com);
		s=flopStraight(c1,c2,com);
		f=flopFlush(c1,c2,com);
		fp=flopFourPair(c1,c2,com);
		fh=flopFullHouse(c1,c2,com);
		sf=flopStraightFlush(s,f);
		rf=flopRoyalFlush(c1,c2,com);
		if(p==true)//pair
			high=1;
		if(tp==true)//two pair
			high=2;
		if(trp==true)//three of a kind
			high=3;
		if(s==true)//straight
			high=4;
		if(f==true)//flush
			high=5;
		if(fp==true)//four of a kind
			high=6;
		if(fh==true)//full house
			high=7;
		if(sf==true)//straight flush
			high=8;
		if(rf==true)//royal flush
			high=9;
		if(s==false)
			temp=flopStraightGap(c1,c2,com);
		sGap=Integer.toString(temp);
		temp=0;
		if(f==false)
			temp=flopFlushGap(c1,c2,com);
		fGap=Integer.toString(temp);
		result+=Integer.toString(high);
		result+=sGap;
		result+=fGap;
		return result;
	}
	public String searchTurn(Card c1, Card c2, Card[] com)
	{
		int temp=0, high=0;
		String sGap="", fGap="", result="";
		boolean p=false, tp=false,trp=false, s=false, f=false, fp=false, fh=false, sf=false, rf=false;
		
		p=turnPair(c1,c2,com);
		tp=turnTwoPair(c1,c2,com);		
		trp=turnThreePair(c1,c2,com);
		s=turnStraight(c1,c2,com);
		f=turnFlush(c1,c2,com);
		fp=turnFourPair(c1,c2,com);
		fh=turnFullHouse(c1,c2,com);
		sf=turnStraightFlush(c1,c2,com);
		rf=turnRoyalFlush(c1,c2,com);
		if(p==true)
			high=1;
		if(tp==true)
			high=2;
		if(trp==true)
			high=3;
		if(s==true)
			high=4;
		if(f==true)
			high=5;
		if(fp==true)
			high=6;
		if(fh==true)
			high=7;
		if(sf==true)
			high=8;
		if(rf==true)
			high=9;
		if(s==false)
			temp=turnStraightGap(c1,c2,com);
		sGap=Integer.toString(temp);
		temp=0;
		if(f==false)
			temp=turnFlushGap(c1,c2,com);
		fGap=Integer.toString(temp);
		result+=Integer.toString(high);
		result+=sGap;
		result+=fGap;
		return result;	
	}
	public boolean flopPair(Card c1, Card c2, Card[] com)
	{
		if(c1.getValue()==c2.getValue())//pocket pair
			return true;
		for(int i=0;i<3;i++)//searches for pairs with cards 
		{
			if(c1.getValue()==com[i].getValue())
				return true;
			if(c2.getValue()==com[i].getValue())
				return true;
		}
		if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[1].getValue()==com[2].getValue())//searches for pairs in com
			return true;	
		return false;
	}
	public boolean flopTwoPair(Card c1, Card c2, Card[] com)
	{
		int count=0;
		
		if(c1.getValue()==c2.getValue())//pocket pair
			count++;
		for(int i=0;i<3;i++)//searches for pairs with cards
		{
			if(c1.getValue()==com[i].getValue())
				count++;
			if(c2.getValue()==com[i].getValue())
				count++;
		}
		if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[1].getValue()==com[2].getValue())//seraches for pairs in com
			count++;	
		if(count>=2)
			return true;
		return false;
	}
	public boolean flopThreePair(Card c1, Card c2, Card[] com)
	{
		int count=0;
		
		if(c1.getValue()==c2.getValue())//if pocket pair
		{
			count=2;
			for(int i=0;i<3;i++)//searches cards
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
		}
		else//in no pocket pair
		{
			for(int i=0;i<3;i++)//searches cards 
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
			if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[1].getValue()==com[2].getValue())//searches com
				count++;
		}
		if(count>=3)
			return true;
		return false;
	}
	public boolean flopFourPair(Card c1, Card c2, Card[] com)
	{
		int count=0;
		
		if(c1.getValue()==c2.getValue())//if pocket pair
		{
			count=2;
			for(int i=0;i<3;i++)//searches cards
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
		}
		else//in no pocket pair
		{
			for(int i=0;i<3;i++)//searches cards 
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
			if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[1].getValue()==com[2].getValue())//searches com
				count++;
		}
		if(count==4)
			return true;
		return false;
	}
	public boolean flopStraight(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		
		if(c1.getValue()==c2.getValue())
			return false;
		for(int i=0;i<3;i++)
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
		for(int i=0;i<3;i++)
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
			for(int i=0;i<3;i++)
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
		for(int i=0;i<3;i++)
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

		for(int i=0;i<3;i++)
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
			for(int i=0;i<3;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		if(count>=5)
			return true;
		return false;
	}
	public boolean flopFlush(Card c1, Card c2, Card[] com)
	{
		if(!c1.getSuit().equals(c2.getSuit()))
			return false;
		for(int i=0;i<3;i++)
			if(!c1.getSuit().equals(com[i].getSuit()))
				return false;
		return true;
	}
	public boolean flopFullHouse(Card c1, Card c2, Card[] com)
	{
		int count1=1, count2=1;
		if(c1.getValue()==c2.getValue())//pocket pair
		{
			count1=2;
			for(int i=0;i<3;i++)
				if(c1.getValue()==com[i].getValue())
					count1++;
			if(com[0].getValue()==com[1].getValue() && com[0].getValue()!=c1.getValue())
				count2++;
			if(com[0].getValue()==com[2].getValue() && com[0].getValue()!=c1.getValue())
				count2++;
			if(com[1].getValue()==com[2].getValue() && com[1].getValue()!=c1.getValue())
				count2++;
		}
		else//no pocket pair
		{
			for(int i=0;i<3;i++)
				if(c1.getValue()==com[i].getValue())
					count1++;
			for(int i=0;i<3;i++)
				if(c2.getValue()==com[i].getValue())
					count2++;
		}
		if((count1==2 && count2==3)||(count1==3 && count2==2))
				return true;
		return false;
	}
	public boolean flopStraightFlush(boolean s, boolean f)
	{
		if(s==true && f==true)
			return true;
		return false;
	}
	public boolean flopRoyalFlush(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		String suit=c1.getSuit();
		boolean ace=false;
		
		if(c1.getValue()==c2.getValue())
			return false;
		for(int i=0;i<3;i++)
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
		for(int i=0;i<3;i++)
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
			for(int i=0;i<3;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(low==0)//adds ace from hand to low count if 2 is low
			if(c1.getValue()==12 && suit.equals(c1.getSuit()) ||c2.getValue()==12 && suit.equals(c2.getSuit()))
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
		for(int i=0;i<3;i++)
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

		for(int i=0;i<3;i++)
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
			for(int i=0;i<3;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12 && suit.equals(c1.getSuit()) || c2.getValue()==12 && suit.equals(c2.getSuit()))
			{
				ace=true;
				count++;
			}
		if(count>=5 && ace==true)
			return true;
		return false;
	}
	public int flopStraightGap(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1, highCount=-1;
		
		for(int i=0;i<3;i++)
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
		for(int i=0;i<3;i++)
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
			for(int i=0;i<3;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to low count if 2 is low
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		highCount=count;
		high=c2.getValue();
		low=c2.getValue();
		count=1;
		for(int i=0;i<3;i++)
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
		for(int i=0;i<3;i++)
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
			for(int i=0;i<3;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		if(count>highCount)
			return count;
		return highCount;
	}
	public int flopFlushGap(Card c1, Card c2, Card[] com)
	{
		int count=1, highCount=0;
		if(c1.getSuit().equals(c2.getSuit()))
		{
			count=2;
			for(int i=0;i<3;i++)
				if(c1.getSuit().equals(com[i].getSuit()))
					count++;
			return count;
		}
		else
		{
			for(int i=0;i<3;i++)
				if(c1.getSuit().equals(com[i].getSuit()))
					count++;
			highCount=count;
			count=1;
			for(int i=0;i<3;i++)
				if(c2.getSuit().equals(com[i].getSuit()))
					count++;
			if(count>highCount)
				return count;
			return highCount;		
		}
	}
	public boolean turnPair(Card c1, Card c2, Card[] com)
	{
		if(c1.getValue()==c2.getValue())//pocket pair
			return true;
		for(int i=0;i<4;i++)//searches for pairs with cards 
		{
			if(c1.getValue()==com[i].getValue())
				return true;
			if(c2.getValue()==com[i].getValue())
				return true;
		}
		if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[0].getValue()==com[3].getValue())
			return true;
		if(com[1].getValue()==com[2].getValue() || com[1].getValue()==com[3].getValue() || com[2].getValue()==com[3].getValue())
			return true;
		return false;
	}
	public boolean turnTwoPair(Card c1, Card c2, Card[] com)
	{
		int count=0;
		
		if(c1.getValue()==c2.getValue())//pocket pair
			count++;
		for(int i=0;i<3;i++)//searches for pairs with cards
		{
			if(c1.getValue()==com[i].getValue())
				count++;
			if(c2.getValue()==com[i].getValue())
				count++;
		}
		if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[0].getValue()==com[3].getValue())
			count++;
		if(com[1].getValue()==com[2].getValue() || com[1].getValue()==com[3].getValue() || com[2].getValue()==com[3].getValue())
			count++;
		if(count>=2)
			return true;
		return false;
	}
	public boolean turnThreePair(Card c1, Card c2, Card[] com)
	{
		int count=0;
		
		if(c1.getValue()==c2.getValue())//if pocket pair
		{
			count=2;
			for(int i=0;i<3;i++)//searches cards
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
		}
		else//in no pocket pair
		{
			for(int i=0;i<3;i++)//searches cards 
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
			if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[0].getValue()==com[3].getValue())
			count++;
			if(com[1].getValue()==com[2].getValue() || com[1].getValue()==com[3].getValue() || com[2].getValue()==com[3].getValue())
			count++;
		}
		if(count>=3)
			return true;
		return false;
	}
	public boolean turnFourPair(Card c1, Card c2, Card[] com)
	{
		int count=0;
		
		if(c1.getValue()==c2.getValue())//if pocket pair
		{
			count=2;
			for(int i=0;i<3;i++)//searches cards
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
		}
		else//in no pocket pair
		{
			for(int i=0;i<3;i++)//searches cards 
			{
				if(c1.getValue()==com[i].getValue())
					count++;
				if(c2.getValue()==com[i].getValue())
					count++;
			}
			if(com[0].getValue()==com[1].getValue() || com[0].getValue()==com[2].getValue() || com[0].getValue()==com[3].getValue())
			count++;
			if(com[1].getValue()==com[2].getValue() || com[1].getValue()==com[3].getValue() || com[2].getValue()==com[3].getValue())
			count++;
		}
		if(count==4)
			return true;
		return false;
	}
	public boolean turnStraight(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		
		if(c1.getValue()==c2.getValue())
			return false;
		for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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

		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		if(count>=5)
			return true;
		return false;
	}
	public boolean turnFlush(Card c1, Card c2, Card[] com)
	{
		String suit=c1.getSuit();
		int count=1;
		//if c1.suit==c2.suit:check com
		if(c1.getSuit().equals(c2.getSuit()))
		{
			count=2;
			for(int i=0;i<4;i++)
				if(suit.equals(com[i].getSuit()))
					count++;
			if(count>=5)
				return true;
		}
		else
		{
			for(int i=0;i<4;i++)
				if(suit.equals(com[i].getSuit()))
					count++;
			if(count>=5)
				return true;
			suit=c2.getSuit();
			for(int i=0;i<4;i++)
				if(suit.equals(com[i].getSuit()))
					count++;
			if(count>=5)
				return true;
		}
		return false;		
	}
	public boolean turnFullHouse(Card c1, Card c2, Card[] com)
	{
		int count1=1, count2=1;
		if(c1.getValue()==c2.getValue())//pocket pair
		{
			count1=2;
			for(int i=0;i<4;i++)
				if(c1.getValue()==com[i].getValue())
					count1++;
			if(com[0].getValue()==com[1].getValue() && com[0].getValue()!=c1.getValue())
				count2++;
			if(com[0].getValue()==com[2].getValue() && com[0].getValue()!=c1.getValue())
				count2++;
			if(com[0].getValue()==com[3].getValue() && com[0].getValue()!=c1.getValue())
				count2++;
			if(com[1].getValue()==com[2].getValue() && com[1].getValue()!=c1.getValue())
				count2++;
			if(com[1].getValue()==com[3].getValue() && com[1].getValue()!=c1.getValue())
				count2++;
			if(com[2].getValue()==com[3].getValue() && com[2].getValue()!=c1.getValue())
				count2++;
		}
		else//no pocket pair
		{
			for(int i=0;i<4;i++)
				if(c1.getValue()==com[i].getValue())
					count1++;
			for(int i=0;i<4;i++)
				if(c2.getValue()==com[i].getValue())
					count2++;
		}
		if((count1==2 && count2==3)||(count1==3 && count2==2))
				return true;
		return false;		
	}
	public boolean turnStraightFlush(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		String suit=c1.getSuit();
		for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
					count++;
		if(low==0)//adds ace from handto low count if 2 is low
			if((c1.getValue()==12 && suit.equals(c1.getSuit())) || (c2.getValue()==12 && suit.equals(c2.getSuit())))
				count++;
		if(count>=5)
			return true;
		return false;				
	}
	public boolean turnRoyalFlush(Card c1, Card c2, Card[] com)
	{
		int high=c1.getValue(), low=c1.getValue(), count=1;
		String suit=c1.getSuit();
		boolean ace=false;
		
		if(c1.getValue()==c2.getValue())
			return false;
		for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(low==0)//adds ace from hand to low count if 2 is low
			if(c1.getValue()==12 && suit.equals(c1.getSuit()) ||c2.getValue()==12 && suit.equals(c2.getSuit()))
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
		for(int i=0;i<4;i++)
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

		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
				if(com[i].getValue()==12 && suit.equals(com[i].getSuit()))
				{
					ace=true;
					count++;
				}
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12 && suit.equals(c1.getSuit()) || c2.getValue()==12 && suit.equals(c2.getSuit()))
			{
				ace=true;
				count++;
			}
		if(count>=5 && ace==true)
			return true;
		return false;	
	}
	public int turnStraightGap(Card c1, Card c2, Card[] com)//might not return correct value
	{
		int high=c1.getValue(), low=c1.getValue(), count=1, highCount=-1;
		
		for(int i=0;i<4;i++)
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
		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to low count if 2 is low
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		highCount=count;
		high=c2.getValue();
		low=c2.getValue();
		count=1;
		for(int i=0;i<4;i++)
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

		for(int i=0;i<4;i++)
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
			for(int i=0;i<4;i++)
				if(com[i].getValue()==12)
					count++;
		if(low==0)//adds ace from hand to high count if king is high
			if(c1.getValue()==12||c2.getValue()==12)
				count++;
		if(count>highCount)
			return count;
		return highCount;
	}
	public int turnFlushGap(Card c1, Card c2, Card[] com)//might not return the correct value
	{
		int count=1, highCount=0;
		if(c1.getSuit().equals(c2.getSuit()))
		{
			count=2;
			for(int i=0;i<3;i++)
				if(c1.getSuit().equals(com[i].getSuit()))
					count++;
			return count;
		}
		else
		{
			for(int i=0;i<4;i++)
				if(c1.getSuit().equals(com[i].getSuit()))
					count++;
			highCount=count;
			count=1;
			for(int i=0;i<4;i++)
				if(c2.getSuit().equals(com[i].getSuit()))
					count++;
			if(count>highCount)
				return count;
			return highCount;		
		}
	}
}