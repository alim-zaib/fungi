package board;

import cards.*;
import java.util.*;

public class CardList {
	private ArrayList<Card> cList = new ArrayList<Card>();

	public CardList() {

	}
	public void add(Card x) {
		this.cList.add(x);

	}
	public int size() {
		return this.cList.size();
	}
	public Card getElementAt(int x) {
		return this.cList.get(x); 
		
	}
	public Card removeCardAt(int x) {
		Card temp = this.cList.get(x);
		this.cList.remove(x);
		return temp;
	}
}