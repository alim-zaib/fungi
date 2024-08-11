package board;

import cards.*;
import java.util.*;

public class Display implements Displayable {
	private ArrayList<Card> displayList = new ArrayList<Card>();
    @Override

	public void add(Card x) {
		this.displayList.add(x);
    }
	public int size() {
		return this.displayList.size();
	}
	public Card getElementAt(int x) {
		return this.displayList.get(x); 
		
	}
	public Card removeElement(int x) {
		Card temp = this.displayList.get(x);
		this.displayList.remove(x);
		return temp;
	}

}