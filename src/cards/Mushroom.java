package cards;

public class Mushroom extends EdibleItem {
    protected int sticksPerMushroom; 

    public Mushroom(CardType x, String y) {
        super(x,y);
    }
    
    public int getSticksPerMushroom() {
        return this.sticksPerMushroom;

    }

}
