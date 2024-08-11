package board;

import cards.*;
import java.util.*;

public class CardPile {
	private Stack<Card> cPile = new Stack<Card>(); 

	public CardPile() {

	}
	public void addCard(Card x) {
		this.cPile.push(x);

	}
	public Card drawCard() {
		Card temp = cPile.peek();
		this.cPile.pop();
		return temp;

	}
	public void shufflePile() {
		ArrayList<Card> shuffled_deck = new ArrayList<Card>();
		int current_size = this.cPile.size()-1;
		while (current_size != 0) {
			current_size = this.cPile.size()-1;

			Card next = this.cPile.pop();

			shuffled_deck.add(next);
		}
		Collections.shuffle(shuffled_deck);
		for (int i = 0; i < shuffled_deck.size(); i++) {
			this.cPile.push(shuffled_deck.get(i));
		}

	}
	public int pileSize() {
		return this.cPile.size();
		
	}
	public boolean isEmpty() {
		if (this.cPile.size() == 0) {
				return true;
		}
		return false;
	}

}