package main;

import javax.swing.*;

/**
 * Gathers player count and player names.
 *
 * @author Fiona Cheng
 */

public class NewGameView extends JPanel implements GameView{
    private GameViewFrame superFrame;

    public NewGameView(GameViewFrame superFrame){
        this.superFrame = superFrame;
    }

    /**
     * @param number The number associated with the player
     *
     * @return the name of the player
     */
    public String requestPlayerName(int number){
        String name = "";
        name = JOptionPane.showInputDialog("Enter a name for player " + number + ":");
        while(name == null || name.length() == 0) {
            JOptionPane.showMessageDialog(superFrame, "Please enter a valid name", "Error", JOptionPane.ERROR_MESSAGE);
            name = JOptionPane.showInputDialog("Enter a name for player " + number + ":");
        }
        return name;
    }

    /**
     * @param min The minimum player count
     * @param max The maximum player count
     *
     * @return the number of players in the game
     */
    public int requestPlayerCount(int min, int max){
        int playerCount = 0;
        String input = JOptionPane.showInputDialog("Enter the number of players:");
        if(input != null && !input.isEmpty()){
            try {
                playerCount = Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.print(e); //or could also do nothing
            }
        }
        while(playerCount < min || playerCount > max){
            JOptionPane.showMessageDialog(superFrame, "Please enter a valid player count (2-4)", "Error", JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog("Enter the number of players:");
            if(input != null && !input.isEmpty()){
                try {
                    playerCount = Integer.parseInt(input);
                } catch (NumberFormatException e){
                    System.out.print(e); //or could also do nothing
                }
            }
        }
        return playerCount;
    }
    @Override
    public void setGame(Game game) {

    }

    @Override
    public void updateGetPlayerName(int index) {

    }

    @Override
    public void updateInvalidInput(String message) {

    }

    @Override
    public void handleNewTurn(Player player) {

    }

    @Override
    public void updatePlayCard(Card playedCard, String additionalMessage) {

    }

    @Override
    public void updateDrawCard(Card drawnCard) {

    }

    @Override
    public void updateGetCard() {

    }

    @Override
    public void updateGetColour() {

    }

    @Override
    public void updateConfirmColour(Card.Colour colour) {

    }
}
