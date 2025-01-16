public class GameManager {
    public static GameManager instance;

    public static void main(String[] args) {
        DifficultySelectorFrame difficultySelectorFrame = new DifficultySelectorFrame();
    } 

    public static void startGame() {
        Game game = new Game();
    }

    public static void exitGame() {
        System.exit(0);
    }
}
