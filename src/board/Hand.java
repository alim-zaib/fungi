package board;

import cards.*;
import java.util.*;

public class Hand implements Displayable {
	private ArrayList<Card> handList = new ArrayList<Card>();
    
    @Override

	public void add(Card x) {
		this.handList.add(x);
	}
	public int size() {
		return this.handList.size();
	}
	public Card getElementAt(int x) {
		return this.handList.get(x); 
		
	}
	public Card removeElement(int x) {
		Card temp = this.handList.get(x);
		this.handList.remove(x);
		return temp;
	}
}