import java.util.Scanner;

class userData{
    public static String answer;
    public static int gameScore;
    public static int correctAnswerScore = 3;
    public static int incorrectAnswerScore = -1;
    public static int standardScore = 30; // you have to be over this score to end Game

    static Scanner scan = new Scanner(System.in);

    public static void scanUserTypingAnswer() {
        answer = scan.next();
    }

    public static void abandonRestOfUserTypingAnswer() {
        scan.nextLine();
    }

    public static void printUserTypingAnswer() {
        System.out.println(answer);
    }

    public static boolean UserWantToAgree() {
        return (answer.equals("y") || answer.equals("Y"));
    }

    public static boolean UserWantToRefuse() {
        return (answer.equals("n") || answer.equals("N"));
    }

    public static void printUserScore() {
        System.out.println("Your Score : " + gameScore);
    }

    public static void plusUserScore() {
        gameScore += 3;
    }

    public static void minusUserScore() {
        if (gameScore > 0)
            gameScore -= 1;
    }

    public static void clearGameScore(){
        gameScore = 0;
    }
}

/**
 * mainGame
 */
class mainGame {
    static int questionFisrtNumber = 0;
    static int questionSecondNumber = 0;
    static int questionAnswer = 0;

    public static void start() {
        userData.clearGameScore();

        while (isBelowStandardScore()) {
            questionFisrtNumber = getRandomValue();
            questionSecondNumber = getRandomValue();

            System.out.println("\n문제:" + questionFisrtNumber + "*" + questionSecondNumber + "?");

            userData.scanUserTypingAnswer();
            userData.abandonRestOfUserTypingAnswer();
            // userData.printUserTypingAnswer();

            questionAnswer = Integer.parseInt(userData.answer);

            if (isCorrectValue()) {
                System.out.println(questionAnswer+ ": 정답 입니다!");
                userData.plusUserScore();
            } else {
                System.out.println(questionAnswer+ ": 오답 입니다!");
                userData.minusUserScore();
            }
            userData.printUserScore();
        }

        System.out.println("30점 이상을 획득하셨습니다. 축하드립니다!.\n");
        checkUserWantToRestartGame();
    }
    
    public static boolean isBelowStandardScore() {
        return userData.gameScore<userData.standardScore;
    }

    public static boolean isCorrectValue() {
        return questionAnswer == (questionFisrtNumber * questionSecondNumber);
    }

    public static int getRandomValue() {
        int rtVal;
        int randomMaxValue = 9;
        int randomMinValue = 2;
        rtVal = (int) ((Math.random() * (randomMaxValue + 1 - randomMinValue)) + randomMinValue); //(int) ((Math.random() * (최댓값+1-최소값)) + 최소값)

        return rtVal;
    }

    public static void checkUserWantToRestartGame() {
        System.out.println("게임을 이어서 진행하시겠습니까 ? (Y / N)");
        
    }
}

public class Homework {

    public static void main(String[] args) throws Exception {

        System.out.println("안녕하세요. 구구단 게임에 오신 것을 환영합니다.");
        System.out.println("게임 출제 범위는 2단 ~ 9단이며 사용자가 문제를 맞추실 경우 3점의 가점이, 틀리실 경우 1점의 감점이 있습니다.");
        System.out.println("사용자의 점수가 30점 이상이 될 경우, 게임이 끝나게 되며 이때 다시 게임 진행 여부를 확인합니다.\n");

        System.out.println("게임을 진행하시겠습니까 ? (Y / N)");

        while (true) {
            userData.scanUserTypingAnswer();
            userData.abandonRestOfUserTypingAnswer();
            // userData.printUserTypingAnswer();

            if (userData.UserWantToAgree()) {
                System.out.println("게임을 시작하겠습니다.");

                mainGame.start();
            } else if (userData.UserWantToRefuse()) {
                System.out.println("다음에 다시 방문해주시면 감사하겠습니다.");

                break;
            } else {
                System.out.println("잘못된 문자를 사용하셨습니다.");
                System.out.println("Y,y / N,n 중의 1개의 문자를 사용해주세요.");
            }
        }
    }
}
