
import java.lang.Math;

public class BlackSuitsSimulator {

	public static void main(String[] args) {
		
		int groupBalance = 100;
		float positiveCounter = 0;
		float negativeCounter = 0;
		int clubs = 0;
		int spades = 0;
		float numberOfSims = 1; // integer from 1-50 *if greater than 50, remove player card stats
		int numberOfBets = 10; // integer from 1-10
		String card = "";
		String suit = "";
		int[] clubsMin = {0, 0, 0, 0};
		int[] clubsMax = {13, 13, 13, 13};
		int[] spadesMin = {14, 14, 14, 14};
		int[] spadesMax = {27, 27, 27, 27};
		int[] players = new int[4];
		
		for (int game = 0; game < numberOfSims; game++) {
			groupBalance = 0; // Resets balance after every game
			for (int i = 0; i < 4; i++) {
				clubsMax[i] = 13; // Resets all clubs at the end of the game
				spadesMax[i] = 27; // Resets all spades at the end of the game
			}
					for (int round = 0; round < numberOfBets; round++) {
						groupBalance = groupBalance - 10; // Subtracts $10 after every bet
						clubs = 0; // Resets club counter at the start of every round
						spades = 0; // Resets spade counter at the start of every round
						System.out.println("\nCards From Round #" + (round+1));
							for (int cards = 0; cards < 4; cards++) {
								players[cards] = (int) (Math.random() * (52 - round)) + 1; // Creates a four card hand
								
								if (players[cards] == 1 || players[cards] == 14 || players[cards] == 27 || players[cards] == 40) { // Creates deck of cards
									card = "Ace of ";
								} else if (players[cards] == 2 || players[cards] == 15 || players[cards] == 28 || players[cards] == 41) {
									card = "2 of ";
								} else if (players[cards] == 3 || players[cards] == 16 || players[cards] == 29 || players[cards] == 42) {
									card = "3 of ";
								} else if (players[cards] == 4 || players[cards] == 17 || players[cards] == 30 || players[cards] == 43) {
									card = "4 of ";
								} else if (players[cards] == 5 || players[cards] == 18 || players[cards] == 31 || players[cards] == 44) {
									card = "5 of ";
								} else if (players[cards] == 6 || players[cards] == 19 || players[cards] == 32 || players[cards] == 45) {
									card = "6 of ";
								} else if (players[cards] == 7 || players[cards] == 20 || players[cards] == 33 || players[cards] == 46) {
									card = "7 of ";
								} else if (players[cards] == 8 || players[cards] == 21 || players[cards] == 34 || players[cards] == 47) {
									card = "8 of ";
								} else if (players[cards] == 9 || players[cards] == 22 || players[cards] == 35 || players[cards] == 48) {
									card = "9 of ";
								} else if (players[cards] == 10 || players[cards] == 23 || players[cards] == 36 || players[cards] == 49) {
									card = "10 of ";
								} else if (players[cards] == 11 || players[cards] == 24 || players[cards] == 37 || players[cards] == 50) {
									card = "Jack of ";
								} else if (players[cards] == 12 || players[cards] == 25 || players[cards] == 38 || players[cards] == 51) {
									card = "Queen of ";
								} else if (players[cards] == 13 || players[cards] == 26 || players[cards] == 35 || players[cards] == 52) {
									card = "King of ";
								}
								
								if (players[cards] >= 1 && players[cards] <= clubsMax[cards]) {
									suit = "Clubs";
								} else if (players[cards] >= 14 && players[cards] < spadesMax[cards]) {
									suit = "Spades";
								} else if (players[cards] >= 27 && players[cards] < 40) {
									suit = "Diamonds";
								} else if (players[cards] >= 40 && players[cards] <= 52) {
									suit = "Hearts";
								}
								
								System.out.println("Player " + (cards+1) + "'s Card: " + card + suit); //+ "\nClubs Remaining: " + clubsMax[cards] + "\nSpades Remaining: " + (spadesMax[cards]-14)); // Remove this line if you want to do sims greater than 50	
								
								if (players[cards] > clubsMin[cards] && players[cards] <= clubsMax[cards]) { // Searches for black clubs in hand
										clubs = clubs + 1;
										clubsMax[cards] = clubsMax[cards] - 1;
										spadesMin[cards] = clubsMax[cards] + 1;
									}
									if (players[cards] >= spadesMin[cards] && players[cards] < spadesMax[cards]) { // Searches for black spades in hand
										spades = spades + 1;
										spadesMax[cards] = spadesMax[cards] - 1;
									}
									
									if (cards > 1 && (players[cards] == players[cards-1] && players[cards-1] == players[cards-2] && players[cards] > 0 && players[cards] < 27)) { // A trio pulls the same exact card
										groupBalance = groupBalance + 50;
									} else if (spades == 3 && cards == 3 || clubs == 3 && cards == 3) { // Trio draws the same black suit.
										groupBalance = groupBalance + 20;
									} else if (spades == 4 && cards == 3 || clubs == 4 && cards == 3) { // Everyone draws the same black suit.
										groupBalance = groupBalance + 30;
									} else if (spades == 2 && cards == 3 || clubs == 2 && cards == 3) { // Pair draws the same black suit.
										groupBalance = groupBalance + 15;
								}
									if (cards == 3) {
										System.out.println("Group's Balance After Round #" + (round+1) + ": " + groupBalance);
								}
							}
						/* if (round == 9) {
							System.out.println("The group made " + groupBalance + " dollars.");
						} */
						if (round == (numberOfBets - 1) && groupBalance > 0) {
							//System.out.println("The group managed to make $" + groupBalance + ".");
							positiveCounter = positiveCounter + 1;
							System.out.println("\nThe group made $" + groupBalance + " after Game #" + (game+1));
							} else if (round == (numberOfBets - 1) && groupBalance < 0) {
								negativeCounter = negativeCounter + 1;
								System.out.println("\nThe group lost $" + groupBalance*-1 + " after Game #" + (game+1));
							}
						if (game == (numberOfSims - 1) && round == (numberOfBets - 1)) {
							System.out.println("\nPlayers Wins: " + positiveCounter); // Counts the times the players make more than the house do
							System.out.println("House Wins: " + negativeCounter); // Counts the times the house makes more than the players do
							System.out.println("Breakeven: " + (numberOfSims - positiveCounter - negativeCounter)); // Counts the times the players breakeven
							float percentage = (numberOfSims - negativeCounter) / numberOfSims * 100;
							System.out.println("Group Winning/Breaking Even Percentage: " + percentage + "%");
					}
				}					
			}			
		}
	} 
						