import java.util.Timer;
import java.util.TimerTask;

public class MainGame {
    
    static int questionFisrtNumber = 0;
    static int questionSecondNumber = 0;
    static int questionAnswer = 0;

    public static void setTimerByDifficulty(Timer ti) {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time Out : 1점 감점");
                UserData.minusUserScore();
            }
        };

        if (UserData.gameDifficulty == UserData.GAME_DIFFICULTY_HARD) {
            ti.scheduleAtFixedRate(tt, 1100, 1000);
        }
        else if (UserData.gameDifficulty == UserData.GAME_DIFFICULTY_NORMAL) {
            ti.scheduleAtFixedRate(tt, 3100, 3000);
        }
        else if (UserData.gameDifficulty == UserData.GAME_DIFFICULTY_EASY) {
            ti.scheduleAtFixedRate(tt, 5100, 5000);
        }
    }

    public static void start() {

        UserData.clearGameScore();

        while (isBelowStandardScore()) {
            final Timer ti = new Timer();
            setTimerByDifficulty(ti);

            questionFisrtNumber = getRandomValue();
            questionSecondNumber = getRandomValue();

            System.out.println("\n문제:" + questionFisrtNumber + "*" + questionSecondNumber + "?");

            UserData.scanUserTypingAnswer();
            UserData.abandonRestOfUserTypingAnswer();
            // userData.printUserTypingAnswer();
            
            try {
                questionAnswer = Integer.parseInt(UserData.answer);
                
                if (isCorrectValue()) {
                    System.out.println(questionAnswer+ ": 정답 입니다!");
                    UserData.plusUserScore();
                } else {
                    System.out.println(questionAnswer+ ": 오답 입니다!");
                    UserData.minusUserScore();
                }
            } catch (Exception e) {
                System.out.println("소수점을 입력하셨습니다.");
                UserData.minusUserScore();
            }
            UserData.printUserScore();
            ti.cancel();
        }
        System.out.println("30점 이상을 획득하셨습니다. 축하드립니다!.\n");
        checkUserWantToRestartGame();
    }
    
    public static synchronized boolean isBelowStandardScore() {
        return UserData.gameScore<UserData.standardScore;
    }

    public static boolean isCorrectValue() {
        return questionAnswer == (questionFisrtNumber * questionSecondNumber);
    }

    public static int getRandomValue() {
        int returnValue;
        int randomMaxValue = 9;
        int randomMinValue = 2;
        returnValue = (int) ((Math.random() * (randomMaxValue + 1 - randomMinValue)) + randomMinValue); //(int) ((Math.random() * (최댓값+1-최소값)) + 최소값)

        return returnValue;
    }

    public static void checkUserWantToRestartGame() {
        System.out.println("게임을 이어서 진행하시겠습니까 ? (Y / N)");
        
    }

}
