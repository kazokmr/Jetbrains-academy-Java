package battleship;

import java.util.Arrays;

public class Game {

    private final Player[] players = new Player[2];

    public Game() {
        Field fieldOfPlayer1 = new Field();
        Field fieldOfPlayer2 = new Field();
        this.players[0] = new Player("Player 1", fieldOfPlayer1, fieldOfPlayer2);
        this.players[1] = new Player("Player 2", fieldOfPlayer2, fieldOfPlayer1);
    }

    public void start() {
        Arrays.stream(players).forEach(Player::ready);
        goBattle();
    }

    private void goBattle() {
        for (Player player : players) {
            player.takeShots();
            if (player.isWon()) {
                return;
            }
        }
        goBattle();
    }
}
