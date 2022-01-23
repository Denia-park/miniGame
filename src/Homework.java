import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

//해당 과제를 하면서 추후 다시 확인할 것
//1. 공유 변수에 관해서 sync 함수를 쓰면 된다고 하는데 이것만 써도 되는지 확인하기.

class userData{
    public static final int Hard = 3;
    public static final int Normal = 2;
    public static final int Easy = 1;

    public static String answer;
    public static int gameScore;
    public static int gameDifficulty;
    public static int correctAnswerScore = 3;
    public static int incorrectAnswerScore = -1;
    public static int standardScore = 30; // you have to be over this score to end Game

    // public static String tempDiff;

    static Scanner scan = new Scanner(System.in);

    public static void scanUserTypingAnswer() {
        answer = scan.next();
    }

    public static void abandonRestOfUserTypingAnswer() {
        scan.nextLine();
    }

    public static boolean IsAnswerFloatExcept0(){
        return (Double.compare(Double.parseDouble(answer),Hard)  != 0 && Double.compare(Double.parseDouble(answer),Normal)  != 0 && Double.compare(Double.parseDouble(answer),Easy)  != 0);
    }

    public static void checkGameDifficulty(){
        String tempDiff;

        if(IsAnswerFloatExcept0())
        {
            if(Double.parseDouble(answer) != 0)
                System.out.println(answer + ": 소수점 입력은 버림으로 처리해서 진행합니다.");
        }

        gameDifficulty = (int)Math.floor(Double.parseDouble(answer));

        if(gameDifficulty<1 || gameDifficulty>3)
        {
            System.out.println(answer + ": 잘못된 값을 입력하셨습니다.");
            System.out.println("normal 난이도로 진행합니다.");
            
            gameDifficulty = Normal;
            return;
        }

        if(gameDifficulty == Hard)
        {
            tempDiff = "Hard";
        }
        else if(gameDifficulty == Normal)
        {
            tempDiff = "Normal";
        }
        else if(gameDifficulty == Easy)
        {
            tempDiff = "Easy";
        }
        else
        {
            tempDiff = "";
        }

        System.out.println(tempDiff+" 난이도를 선택하셨습니다.");
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

/**
 * mainGame
 */
class mainGame {
    static int questionFisrtNumber = 0;
    static int questionSecondNumber = 0;
    static int questionAnswer = 0;

    public static void setTimerByDiff(Timer ti) {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time Out : 1점 감점");
                userData.minusUserScore();
            }
        };

        if (userData.gameDifficulty == userData.Hard) {
            ti.scheduleAtFixedRate(tt, 1100, 1000);
        }
        else if (userData.gameDifficulty == userData.Normal) {
            ti.scheduleAtFixedRate(tt, 3100, 3000);
        }
        else if (userData.gameDifficulty == userData.Easy) {
            ti.scheduleAtFixedRate(tt, 5100, 5000);
        }
    }

    public static void start() {

        userData.clearGameScore();

        while (isBelowStandardScore()) {
            final Timer ti = new Timer();
            setTimerByDiff(ti);

            questionFisrtNumber = getRandomValue();
            questionSecondNumber = getRandomValue();

            System.out.println("\n문제:" + questionFisrtNumber + "*" + questionSecondNumber + "?");

            userData.scanUserTypingAnswer();
            userData.abandonRestOfUserTypingAnswer();
            // userData.printUserTypingAnswer();
            
            try {
                questionAnswer = Integer.parseInt(userData.answer);
                
                if (isCorrectValue()) {
                    System.out.println(questionAnswer+ ": 정답 입니다!");
                    userData.plusUserScore();
                } else {
                    System.out.println(questionAnswer+ ": 오답 입니다!");
                    userData.minusUserScore();
                }
            } catch (Exception e) {
                System.out.println("소수점을 입력하셨습니다.");
                userData.minusUserScore();
            }
            userData.printUserScore();
            ti.cancel();
        }
        System.out.println("30점 이상을 획득하셨습니다. 축하드립니다!.\n");
        checkUserWantToRestartGame();
    }
    
    public static synchronized boolean isBelowStandardScore() {
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

        System.out.println("해당 버전은 정답 입력시간에 제한을 건 버전입니다. 정답 입력시간으로 난이도 조절이 가능합니다.");
        System.out.println("먼저 난이도 설정을 해주세요. ※Easy[5초] ,2: Normal[3초] ,3: Hard[1초]");
        System.out.println("1: Easy ,2: Normal ,3: Hard 숫자 1,2,3 중 1개의 값을 입력해주세요.\n");

        userData.scanUserTypingAnswer();
        userData.abandonRestOfUserTypingAnswer();
        userData.checkGameDifficulty();

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
