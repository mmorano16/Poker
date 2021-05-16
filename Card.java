//creates a class for each card with rank, suit, value
public class Card
{
    private String rank, suit;
    private int value;
    
    public Card(String newRank, String newSuit, int newValue)//constructor method
    {
        rank=newRank;
        suit=newSuit;
        value=newValue;
    }
    public String getRank()//getter method
    {
        return rank;
    }
    public String getSuit()//getter method
    {
        return suit;
    }
    public int getValue()//getter method
    {
        return value;
    }
    public boolean checkEqual(Card c1, Card c2)//returns if two cards are the same
    {
        return c1.getValue()==c2.getValue();
    }
    public String toString()//returns the rank suit and value of the card
    {
        return rank + " of " + suit /*+ "(point value=" + value + ")"*/;
    }
}
