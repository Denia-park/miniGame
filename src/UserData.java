import java.util.Scanner;

public class UserData {
    
    public static final int GAME_DIFFICULTY_HARD = 3;
    public static final int GAME_DIFFICULTY_NORMAL = 2;
    public static final int GAME_DIFFICULTY_EASY = 1;

    public static String answer;
    public static int gameScore;
    public static int gameDifficulty;
    public static final int correctAnswerScore = 3;
    public static final int incorrectAnswerScore = -1;
    public static final int standardScore = 30; // you have to be over this score to end Game

    // public static String tempDiff;

    static Scanner scan = new Scanner(System.in);

    public static void scanUserTypingAnswer() {
        answer = scan.next();
    }

    public static void abandonRestOfUserTypingAnswer() {
        scan.nextLine();
    }

    public static boolean isAnswerFloatExcept0(){
        return (Double.compare(Double.parseDouble(answer),GAME_DIFFICULTY_HARD)  != 0 && Double.compare(Double.parseDouble(answer),GAME_DIFFICULTY_NORMAL)  != 0 && Double.compare(Double.parseDouble(answer),GAME_DIFFICULTY_EASY)  != 0);
    }

    public static void checkGameDifficulty(){
        String tempDifficulty;

        if(isAnswerFloatExcept0())
        {
            if(Double.parseDouble(answer) != 0)
                System.out.println(answer + ": 소수점 입력은 버림으로 처리해서 진행합니다.");
        }

        gameDifficulty = (int)Math.floor(Double.parseDouble(answer));

        if(gameDifficulty<1 || gameDifficulty>3)
        {
            System.out.println(answer + ": 잘못된 값을 입력하셨습니다.\n"+
                                        "normal 난이도로 진행합니다.");
            
            gameDifficulty = GAME_DIFFICULTY_NORMAL;
            return;
        }

        if(gameDifficulty == GAME_DIFFICULTY_HARD)
        {
            tempDifficulty = "Hard";
        }
        else if(gameDifficulty == GAME_DIFFICULTY_NORMAL)
        {
            tempDifficulty = "Normal";
        }
        else if(gameDifficulty == GAME_DIFFICULTY_EASY)
        {
            tempDifficulty = "Easy";
        }
        else
        {
            tempDifficulty = "";
        }

        System.out.println(tempDifficulty+" 난이도를 선택하셨습니다.");
    }

    public static void printUserTypingAnswer() {
        System.out.println(answer);
    }

    public static boolean isYes() {
        return (answer.equals("y") || answer.equals("Y"));
    }

    public static boolean isNo() {
        return (answer.equals("n") || answer.equals("N"));
    }

    public static synchronized void printUserScore() {
        System.out.println("Your Score : " + gameScore);
    }

    public static synchronized void plusUserScore() {
        gameScore += 3;
    }

    public static synchronized void minusUserScore() {
        if (gameScore > 0)
            gameScore -= 1;
    }

    public static synchronized void clearGameScore(){
        gameScore = 0;
    }

    
}
