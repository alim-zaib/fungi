package board;

import cards.*;

public interface Displayable {
	public void add(Card x);
	public int size();
	public Card getElementAt(int x);
	public Card removeElement(int x);
}