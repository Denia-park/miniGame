import java.util.Timer;
import java.util.TimerTask;

public class MainGame {
    
    int questionFisrtNumber = 0;
    int questionSecondNumber = 0;
    int questionAnswer = 0;

    final int randomMaxValue = 9;
    final int randomMinValue = 2;

    UserData UD = new UserData();

    void setTimerByDifficulty(Timer ti) {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time Out : 1점 감점");
                UD.minusUserScore();
            }
        };

        if (UD.gameDifficulty == UD.GAME_DIFFICULTY_HARD) {
            ti.scheduleAtFixedRate(tt, 1100, 1000);
        }
        else if (UD.gameDifficulty == UD.GAME_DIFFICULTY_NORMAL) {
            ti.scheduleAtFixedRate(tt, 3100, 3000);
        }
        else if (UD.gameDifficulty == UD.GAME_DIFFICULTY_EASY) {
            ti.scheduleAtFixedRate(tt, 5100, 5000);
        }
    }

    void start() {

        UD.clearGameScore();

        while (UD.isBelowStandardScore()) {
            final Timer ti = new Timer();
            setTimerByDifficulty(ti);

            questionFisrtNumber = getRandomValue();
            questionSecondNumber = getRandomValue();

            System.out.println("\n문제:" + questionFisrtNumber + "*" + questionSecondNumber + "?");

            UD.scanUserTypingAnswer();
            UD.abandonRestOfUserTypingAnswer();
            // UD.printUserTypingAnswer();
            
            try {
                questionAnswer = Integer.parseInt(UD.answer);
                
                if (isCorrectValue()) {
                    System.out.println(questionAnswer+ ": 정답 입니다!");
                    UD.plusUserScore();
                } else {
                    System.out.println(questionAnswer+ ": 오답 입니다!");
                    UD.minusUserScore();
                }
            } catch (Exception e) {
                System.out.println("소수점을 입력하셨습니다.");
                UD.minusUserScore();
            }
            UD.printUserScore();
            ti.cancel();
        }
        System.out.println("30점 이상을 획득하셨습니다. 축하드립니다!.\n");
        checkUserWantToRestartGame();
    }
    
    boolean isCorrectValue() {
        return questionAnswer == (questionFisrtNumber * questionSecondNumber);
    }

    int getRandomValue() {
        int returnValue;
        returnValue = (int) ((Math.random() * (randomMaxValue + 1 - randomMinValue)) + randomMinValue); //(int) ((Math.random() * (최댓값+1-최소값)) + 최소값)

        return returnValue;
    }

    void checkUserWantToRestartGame() {
        System.out.println("게임을 이어서 진행하시겠습니까 ? (Y / N)");
        
    }

}
