package board;

import cards.*;
import java.util.*;

public class Player {
	private Hand h = new Hand();
	private Display d = new Display(); 
	private int score;
	private int handlimit = 8;
	private int sticks;

	public Player() {
		Pan PlayerDefaultPan = new Pan();
		this.d.add(PlayerDefaultPan);
	}

	public int getScore() {
		return this.score;
	}
	public int getHandLimit() {
		return this.handlimit;
	}
    public int getStickNumber() {
        return this.sticks;
    }
	public int addSticks(int x) {
		this.sticks = this.sticks + x;
		for (int i = 0; i < x; i++) {
			this.d.add(new Stick());
		}
		return this.sticks;
	}
	public void removeSticks(int x) {
		int removecounter = 0;
		for (int i = this.d.size() - 1; i > -1; i--) {
			if ((this.d.getElementAt(i).getType() == CardType.STICK) && (removecounter != x)) {
				this.d.removeElement(i);
				removecounter++;
			}
		}
		this.sticks = this.sticks - x;
	}
	public Hand getHand() {
		return this.h;

	}
	public Display getDisplay() {
		return this.d;
	}
	public void addCardtoHand(Card x) {
		if (x.getType() == CardType.BASKET) {
			handlimit += 2;
			this.addCardtoDisplay(x);
		}
		else if (x.getType() == CardType.STICK) {
			this.addCardtoDisplay(x);
		}
		else {
			this.h.add(x);
		}
	}
	public void addCardtoDisplay(Card x) {
		this.d.add(x);
	}

// Actions to implement include:
// 		Check that taking a cards will not surpass the player's hand limit.
// 		Add the card to the player's hand or to the display if the card is a basket.
// 		Update the number of sticks you have, if you have used them.
// 		Update the display according to the number of sticks you have.
	
	public boolean takeCardFromTheForest(int x) {
		if (this.h.size() < handlimit) {
			CardList temp = Board.getForest();
			Card temp_C = temp.removeCardAt(( 8 - x ));

			if ( x > 2 ) {
				if ((this.getStickNumber() - ( x - 2 )) > -1) {
					this.removeSticks(( x - 2));
				}
				else if ((this.getStickNumber() - ( x - 2 )) < 0) { 
					return false;
				}	
			}

			this.addCardtoHand(temp_C);
			return true;
		} 
		return false;
	}

// Actions to implement include:
// 		Check that taking these cards will not surpass the player's hand limit.
// 		Check if there is a basket in the decay pile as this will increase the hand limit.

	public boolean takeFromDecay() {

		int BasketCount = 0;
		for(int i = 0; i < Board.getDecayPile().size(); i++) {
			Card temp_Card = Board.getDecayPile().get(i);
			if (temp_Card.getType() == CardType.BASKET) {
				BasketCount++;
			}
		}

		int OpenSpaces = (handlimit + (BasketCount * 2)) - this.h.size();

		int MinAvail = Math.min((OpenSpaces),Board.getDecayPile().size());
			
		if (MinAvail < (Board.getDecayPile().size() - BasketCount)) {
			return false;
		}

		if (MinAvail > 0) {
			for (int i = (MinAvail - 1); i > -1; i--) { 							
				Card temp_C = Board.getDecayPile().get(i); 					
				this.addCardtoHand(temp_C);		 								
				Board.getDecayPile().remove(i); 							
			}
			return true;	
		}	
		return false;
	}

// Actions to implement include:
//		Check there is a pan on the display or in the ArrayList.
//		Check the mushrooms are identical and there are at least 3. Remember night mushroom count as double.
//		Make sure there are enough cards if there is butter or cider or both (remember at least 4 mushrooms for butter, at least 5 for cider).
//		Update the player score, if the mushrooms were cooked.
//		Remove the cards used from the hand and/or display, if the mushrooms were cooked.

public boolean cookMushrooms(ArrayList<Card> x) {
	
		boolean hasPan = false;
		int InputCider = 0;
		int InputButter = 0;
		int DayCardCount = 0;
		int NightCardCount = 0;

		if (x.size() == 0) {
			return false;
		}

		String mushroom_Name = x.get(0).getName();
	
		for (int i = 0; i < x.size(); i++) {

			if ((x.get(i).getType() == CardType.DAYMUSHROOM || x.get(i).getType() == CardType.NIGHTMUSHROOM ) && x.get(i).getName() != mushroom_Name) {
				return false;
			}
			else if ((x.get(i).getType() == CardType.NIGHTMUSHROOM) && (x.get(i).getName() == mushroom_Name)) {
				NightCardCount++;
			}
			else if (x.get(i).getType() == CardType.DAYMUSHROOM && x.get(i).getName() == mushroom_Name) {
				DayCardCount++;
			}
			else {
				if (x.get(i).getType() == CardType.PAN) {
					hasPan = true;
				}
				else if ((x.get(i).getType() == CardType.BASKET) || (x.get(i).getType() ==  CardType.STICK)) {
					return false;
				}
			}
		}

		if (DayCardCount + (NightCardCount * 2) < 3) {
			return false;
		}

		for (int i = 0; i < d.size(); i++) {
			if (this.d.getElementAt(i).getType() == CardType.PAN) {
				hasPan = true;

			}
		}

		if (!hasPan) {
			return false;
		}

		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).getType() == CardType.CIDER) {
				InputCider++;
			}
			else if(x.get(i).getType() == CardType.BUTTER) {
				InputButter++;
			}
		}
		
		int MushroomsUsed = 0;
		for (int i = h.size() - 1; i > -1; i--) {
			if (h.getElementAt(i).getName() == mushroom_Name && (MushroomsUsed < x.size())) {
				MushroomsUsed++;
				h.removeElement(i);
			}
		}

		for (int i = x.size() - 1; i > -1; i--) {

			if (x.get(i).getName() == mushroom_Name && (MushroomsUsed < x.size())) {

				EdibleItem temp_Edible = (EdibleItem) x.get(i);
				if (temp_Edible.getType() == CardType.NIGHTMUSHROOM) {
					this.score = this.score + (temp_Edible.getFlavourPoints());
				}
				this.score = this.score + (temp_Edible.getFlavourPoints());
				MushroomsUsed++;
			}
		}

		if (hasPan == true) {

			int CidersUsed = 0;
			int ButtersUsed = 0;

			// 2 Ciders

			if (((DayCardCount + (NightCardCount * 2)) < 10) && hasPan && InputCider > 1) { 
				return false;
			}

			else if(((DayCardCount + (NightCardCount * 2)) > 9) && hasPan && InputCider > 1) {		
				if (InputCider > 1) {

					this.score = this.score + 10;
					
					for (int i = x.size() - 1; i > -1; i--) {
						if (x.get(i).getType() == CardType.CIDER && CidersUsed < 2) {
							x.remove(i);
							CidersUsed++;
						}
					}		
				}
			}

			// 1 Butter and 1 Cider

			else if(((DayCardCount + (NightCardCount * 2)) < 9) && (InputCider == 1 && InputButter == 1)) { 
				return false;
			}

			else if (((DayCardCount + (NightCardCount * 2)) > 8) && (InputCider == 1 && InputButter == 1)) {
				if ((InputCider == 1 && InputButter == 1)) {
					for (int i = x.size() - 1; i > -1; i--) {
						if (x.get(i).getType() == CardType.CIDER && CidersUsed != 1) {
							x.remove(i);
							CidersUsed++;
							this.score += 5;
					}	
				}
					for (int i = x.size() - 1; i > -1; i--) {
						if (x.get(i).getType() == CardType.BUTTER && ButtersUsed != 1) {
							x.remove(i);
							ButtersUsed++;
							this.score += 3;
					}
				}
			}
			}

			// 2 Butters

			else if(((DayCardCount + (NightCardCount * 2)) < 8) && hasPan && InputButter > 1) { 
				return false;
			}

			else if (((DayCardCount + (NightCardCount * 2)) > 7) && hasPan && InputButter > 1) {
				
				if (InputButter > 1) {
					this.score = this.score + 6;
					
					for (int i = x.size() - 1; i > -1; i--) {
						if (x.get(i).getType() == CardType.BUTTER && ButtersUsed < 2) {
							x.remove(i);
							ButtersUsed++;
						}
					}			
				}
			}

			// 1 Cider

			else if (((DayCardCount + (NightCardCount * 2)) < 5) && InputCider == 1) { 
				return false;
			}

			else if (((DayCardCount + (NightCardCount * 2)) > 4) && InputCider == 1) {
				for (int i = x.size() - 1; i > -1; i--) {
					if (x.get(i).getType() == CardType.CIDER && CidersUsed != 1) {
						x.remove(i);
						CidersUsed++;
						this.score = this.score + 5;
					}
				}
			}

			// 1 Butter
			
			else if (((DayCardCount + (NightCardCount * 2)) < 4) && InputButter == 1) {
				return false;
			}

			else if (((DayCardCount + (NightCardCount * 2)) > 3) && InputButter == 1) {
					for (int i = x.size() - 1; i > -1; i--) {
						if (x.get(i).getType() == CardType.BUTTER && ButtersUsed != 1) {
							x.remove(i);
							ButtersUsed++;
							this.score += 3;
						}
					}
			}
		}
		 return true;  
	}

// Actions to implement:
//		Transform the String if the input is not the canonical name (canonical name? Check the game rules for details). For instance, you may get "Lawyer's Wig"
//		which should be transformed into "lawyerswig". Fix the use of uppercase ("PORcini"), whitespaces before (" morel"), after ("shiitake ") and in compound names e.g "birch bolete"). If there is a typo, don't try to fix it and return false .
//		Check you are selling mushrooms of the same type and you have at least 2.
//		Take into account that night cards account for two mushrooms.
//		Add a number of sticks proportionate to the number of mushrooms sold and their type.
//		Remove cards from hand once mushrooms are sold.
//		Add sticks to display once mushrooms are sold.

	public boolean sellMushrooms(String x, int y) {
		ArrayList<String> allowed_shroom = new ArrayList<>();
		allowed_shroom.add("birchbolete");
		allowed_shroom.add("chanterelle");
		allowed_shroom.add("henofwoods");
		allowed_shroom.add("morel");
		allowed_shroom.add("lawyerswig");
		allowed_shroom.add("honeyfungus");
		allowed_shroom.add("porcini");
		allowed_shroom.add("shiitake");
		allowed_shroom.add("treeear");

		x = x.toLowerCase();
		x = x.replaceAll(" ", "");
		x = x.replaceAll("\\p{Punct}", "");

		for (int i = 0; i < allowed_shroom.size(); i++) {
			if (allowed_shroom.get(i) != x && i == allowed_shroom.size()) {
				return false;
			}
		}
		
		int DayCardcount = 0;
		int NightCardcount = 0;
		
		Card x_obj = h.getElementAt(0);
		
		for(int i = 0; i < this.h.size(); i++) {
			Card temp_Card_store = this.h.getElementAt((i));
			String temp_String_store = temp_Card_store.getName();
			temp_String_store = temp_String_store.toLowerCase();
			temp_String_store.replaceAll(" ", "");
			if (temp_String_store.equals(x)) {
				if (temp_Card_store.getType() == CardType.NIGHTMUSHROOM) {
					NightCardcount++;
				}
				else{
					DayCardcount++;
				}
				x_obj = this.h.getElementAt(i);
			}
		}

		if ( y < 2 ) {
			return false;
		}

		if ((NightCardcount < 1) && (DayCardcount < 2)) {
			return false;
		}

		if ((DayCardcount + ( 2 * NightCardcount)) < y) {
			return false;
		}

		Mushroom temp_x_obj = (Mushroom) x_obj;
		int wanted_stick_value = ((Mushroom) temp_x_obj).getSticksPerMushroom();

		int sticks_given = wanted_stick_value * y;
		this.addSticks(sticks_given);

		for (int i = this.h.size() - 1; i > -1; i--) {
			if (this.h.getElementAt(i).getName().equals(x)) {
				this.h.removeElement(i);
			}
		}
        return true;
	}

// Actions to implement:
//		Check if there is a pan in the players hand.
//		If there is at least one, take any pan, remove it from the hand and put in the display.
	
	public boolean putPanDown() {
		for (int i = 0; i < h.size(); i++) {
			if (h.getElementAt(i).getType() == CardType.PAN) {
				Card temp = this.h.getElementAt(i);
				this.addCardtoDisplay(temp);
				this.h.removeElement(i);
				return true;
			}
	}
	return false;
}
}