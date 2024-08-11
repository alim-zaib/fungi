package cards;

public class EdibleItem extends Card {
    protected int flavourPoints; 

    public EdibleItem(CardType x, String y) {
        super(x,y);
    }
    
    public int getFlavourPoints() {
        return flavourPoints;
    }

}
