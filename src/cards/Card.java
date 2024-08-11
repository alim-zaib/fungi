package cards;

public class Card {
    protected CardType type; 
    protected String cardName; 

    public Card(CardType x, String y) {
        type = x;
        cardName = y;
    }
    public CardType getType()  {
        return type;
    }
    public String getName() {
        return cardName;

        
    }
    
}
