package board;

import cards.*;
import java.util.*;

public class Board {
	private static CardPile forestCardsPile = new CardPile();

	private static CardList forest = new CardList(); 

	private static ArrayList<Card> decayPile = new ArrayList<Card>(); 

	public static void initialisePiles() {
		while (forestCardsPile.isEmpty() == false){
			forestCardsPile.drawCard();
		}

		while (forest.size() > 0) {
			forest.removeCardAt(0);
		}
	  }

	public static void setUpCards() {

		//All the instances of the cards including all the mushrooms, cidre, butter, pan and baskets are
		//instantiated in this method and are added to the forestCardsPile . Note that, while
		//sticks are also cards, they don't go into the forestCardsPile .

		//Sticks are infinite

		HoneyFungus temp_N_HF = new HoneyFungus(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_HF);

		Mushroom temp_N_TE = new TreeEar(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_TE);

		Mushroom temp_N_LW = new LawyersWig(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_LW);

		Mushroom temp_N_S = new Shiitake(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_S);

		Mushroom temp_N_HOW = new HenOfWoods(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_HOW);

		Mushroom temp_N_BB = new BirchBolete(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_BB);

		Mushroom temp_N_P = new Porcini(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_P);

		Mushroom temp_N_C = new Chanterelle(CardType.NIGHTMUSHROOM);
		forestCardsPile.addCard(temp_N_C);

//---------------------------------------------------------------------------------------------------------------------------------

		for (int i = 0; i < 10; i++) {
			Mushroom temp_HF = new HoneyFungus(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_HF);
		}

		for (int i = 0; i < 8; i++) {
			Mushroom temp_TE = new TreeEar(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_TE);
		}

		for (int i = 0; i < 6; i++) {
			Mushroom temp_LW = new LawyersWig(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_LW);
		}

		for (int i = 0; i < 5; i++) {
			Mushroom temp_S = new Shiitake(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_S); 
		}

		for (int i = 0; i < 5; i++) {
			Mushroom temp_HOW = new HenOfWoods(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_HOW);

		}

		for (int i = 0; i < 4; i++) {
			Mushroom temp_BB = new BirchBolete(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_BB);
		}

		for (int i = 0; i < 4; i++) {
			Mushroom temp_P = new Porcini(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_P);
		}
		
		for (int i = 0; i < 4; i++) {
			Mushroom temp_C = new Chanterelle(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_C);
		}

		for (int i = 0; i < 3; i++) {
			Mushroom temp_M = new Morel(CardType.DAYMUSHROOM);
			forestCardsPile.addCard(temp_M);
		}

		for (int i = 0; i < 3; i++) {
			Butter temp_Butter = new Butter();
			forestCardsPile.addCard(temp_Butter);
		}

		for (int i = 0; i < 3; i++) {
			Cider temp_Cider = new Cider();
			forestCardsPile.addCard(temp_Cider);
		}

		for (int i = 0; i < 11; i++) {
			Pan temp_Pan = new Pan();
			forestCardsPile.addCard(temp_Pan);
		}

		for (int i = 0; i < 5; i++) {
			Basket temp_Basket = new Basket();
			forestCardsPile.addCard(temp_Basket);
		}
	  }

	public static CardPile getForestCardsPile() {
		return forestCardsPile;
	  }

	public static CardList getForest() {
		return forest;
	  }

	public static ArrayList<Card> getDecayPile() {
		return decayPile;
		
	  }
	public static void updateDecayPile() {
		if (Board.decayPile.size() < 4) {
			Board.decayPile.add(forest.removeCardAt(forest.size() - 1));
		}
		else {
			for (int i = decayPile.size()-1; i > -1 ; i--) {
				decayPile.remove(i);
			}
		Board.decayPile.add(forest.removeCardAt(forest.size() - 1));
		}
	  }
}
