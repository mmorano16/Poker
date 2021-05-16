//creates a deck card objects
import java.util.Random;

public class Deck
{
    private Card[] deck;
    private int currentCard=0;
    public Deck(String[]ranks, String[]suits, int[]values)
    {//pre: receives arrays of ranks, suits, and values
     //post: returns a constructed deck array
        int pos=0;
        deck=new Card[suits.length*ranks.length];
        for(int i=0;i<suits.length;i++)
            for(int k=0;k<ranks.length;k++)
            {
                deck[pos]=new Card(ranks[k],suits[i],values[k]);
                pos++;
            }
    }
    public boolean isEmpty()//returns true if there are no more cards left in the deck
    {
        return deck.length-currentCard==0;
    }
    public int size()//returns the size of the deck
    {
        return deck.length-currentCard;
    }
    public Card deal()//returns a card at the current position in the deck
    {
        if(isEmpty())
            return null;
        currentCard++;
        return deck[currentCard-1];       
    }
    public void shuffle()//shuffles the objects in the deck array
    {
        for(int i=0;i<deck.length;i++)
        {
            Random r1=new Random();
            int y=r1.nextInt(deck.length);
            Card x=deck[i];
            deck[i]=deck[y];
            deck[y]=x;
        }
    }
    public String toString()//returns the objects in the deck
    {
        String s="";
        for(int i=0;i<deck.length;i++)
            s+=deck[i]+" " + deck[i].getValue()+"\n";
        return s;        
    }    
}
