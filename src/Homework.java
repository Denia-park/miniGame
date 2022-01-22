import java.util.Scanner;

class userData{
    public static String answer;
    static Scanner scan = new Scanner(System.in);

    public static void scanUserTypingAnswer() {
        answer = scan.next();
    }

    public static void printUserTypingAnswer() {
        System.out.println(userData.answer);
    }
}

/**
 * mainGame
 */
class mainGame {
    static int questionFisrtNumber;
    static int questionSecondNumber;
    static int questionAnswer;

    public static void start() {
        questionFisrtNumber = (int)(Math.random() * 10.0); //0.0 <= Math.random return value < 1.0
        questionSecondNumber = (int)(Math.random() * 10.0); //0.0 <= Math.random return value < 1.0
        System.out.println("문제:"+questionFisrtNumber+"*"+questionSecondNumber+"?");
        userData.scanUserTypingAnswer();
        userData.printUserTypingAnswer();

        questionAnswer = Integer.parseInt(userData.answer);

        if(questionAnswer == (questionFisrtNumber * questionSecondNumber))
        {
            System.out.println("정답 입니다!");
        }
        else
        {
            System.out.println("오답 입니다!");
        }

        // 점수 세기

        // 게임 종료하기
    }
    
}

public class Homework {

    public static void main(String[] args) throws Exception {
        boolean whileFlag = true;
        boolean wrongInputFlag = false;
        boolean reGameFlag = false;

        System.out.println("안녕하세요 구구단 게임에 오신 것을 환영합니다.");
        System.out.println("게임 출제 범위는 2단 ~ 9단 입니다.\n");

        System.out.println("게임을 진행하시겠습니까 ? (Y / N)");

        while (whileFlag){

            if(wrongInputFlag == true)
            {
                System.out.println("잘못된 문자를 사용하셨습니다.");
                System.out.println("Y,y,N,n 중의 1개의 문자를 사용해주세요.");
            }
            else if (reGameFlag == true)
            {
                // 30점을 달성 , 게임을 다시 시작할 것인지 묻기.
                // System.out.println("잘못된 문자를 사용하셨습니다.");
                // System.out.println("Y,y,N,n 중의 1개의 문자를 사용해주세요.");
            }

            userData.scanUserTypingAnswer();
            userData.printUserTypingAnswer();

            if (userData.answer.equals("y") || userData.answer.equals("Y") ) {
                wrongInputFlag = false;
                System.out.println("게임을 시작하겠습니다.");
    
                //start game
                mainGame.start();
            }
            else if (userData.answer.equals("n") || userData.answer.equals("N")) {
                whileFlag = false;
                wrongInputFlag = false;
                System.out.println("다음에 다시 방문해주시면 감사하겠습니다.");

                break;
            }
            else{
                wrongInputFlag = true;
            }

        }





    }
}
