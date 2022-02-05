public class Homework {

    public static void main(String[] args) throws Exception {

        UserData UD = new UserData();
        MainGame MG = new MainGame();

        StringBuilder SB = new StringBuilder();

        SB.append("안녕하세요. 구구단 게임에 오신 것을 환영합니다.\n게임 출제 범위는 ").append(MG.randomMinValue).append("단 ~ ").append(MG.randomMaxValue).append("단이며 사용자가 문제를 맞추실 경우 ").append(UD.correctAnswerScore).append("점의 가점이, 틀리실 경우 ").append(UD.incorrectAnswerScore).append("점의 감점이 있습니다.\n").append("사용자의 점수가 ").append(UD.standardScore).append("점 이상이 될 경우, 게임이 끝나게 되며 이때 다시 게임 진행 여부를 확인합니다.\n");

        System.out.println(SB.toString());

        System.out.println( "해당 버전은 정답 입력시간에 제한을 건 버전입니다. 정답 입력시간으로 난이도 조절이 가능합니다.\n먼저 난이도 설정을 해주세요. ※Easy[5초] ,2: Normal[3초] ,3: Hard[1초]\n1: Easy ,2: Normal ,3: Hard / 숫자 1,2,3 중 1개의 값을 입력해주세요.\n");

        UD.scanUserTypingAnswer();
        UD.abandonRestOfUserTypingAnswer();
        UD.checkGameDifficulty();

        System.out.println("게임을 진행하시겠습니까 ? (Y / N)");

        while (true) {
            UD.scanUserTypingAnswer();
            UD.abandonRestOfUserTypingAnswer();
            // UD.printUserTypingAnswer();

            if (UD.isYes()) {
                System.out.println("게임을 시작하겠습니다.");

                MG.start();
            } else if (UD.isNo()) {
                System.out.println("다음에 다시 방문해주시면 감사하겠습니다.");

                break;
            } else {
                System.out.println( "잘못된 문자를 사용하셨습니다.\nY,y / N,n 중의 1개의 문자를 사용해주세요.");
            }
        }
    }
}
