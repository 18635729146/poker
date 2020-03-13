import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Pai {
    protected String suit; //花色
    protected String count; //点数

    public Pai(String suit, String count) {
        this.suit = suit;
        this.count = count;
    }

    @Override
    public String toString() {
        return "(" +count + suit +  ")";
    }
}

public class KaPai {
    //创建卡牌类

    public static void main(String[] args) {
        //1.构造一副牌
        List<Pai> poker = buyCard();
        //2.洗牌
        XiPai(poker);
        System.out.println("洗完牌如下：");
        System.out.println(poker);
        //3.分牌
        List<List<Pai>> players = new ArrayList<>();
        //两个玩家
        players.add(new ArrayList<Pai>());
        players.add(new ArrayList<Pai>());
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            for (int cardIndex = 0; cardIndex < 5; cardIndex++) {
                players.get(playerIndex).add(poker.remove(0));
            }
        }
        System.out.println("Black: ");
        System.out.println(players.get(0));
        System.out.println("White:");
        System.out.println(players.get(1));

        //4.比大小
        match(players);
    }
    private final static String[] SUITS = {"D","S","H","C"};
    public static List<Pai> buyCard() {
        List<Pai> poker = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j <= 9; j++) {
                poker.add(new Pai(SUITS[i], j + ""));
            }
            poker.add(new Pai(SUITS[i], "T"));
            poker.add(new Pai(SUITS[i], "J"));
            poker.add(new Pai(SUITS[i], "Q"));
            poker.add(new Pai(SUITS[i], "K"));
            poker.add(new Pai(SUITS[i], "A"));
        }
        return poker;
    }
    //洗牌
    public static void XiPai(List<Pai> list) {
        Random random = new Random();
        for (int i = list.size() - 1; i >= 0; i--) {
            int ret = random.nextInt(list.size());
            Pai tmp = list.get(i);
            list.set(i,list.get(ret));
            list.set(ret,tmp);
        }
    }
    //比大小
    public static void match(List<List<Pai>> players) {
        float[] player = new float[2];
        String[] arr1 = {players.get(0).get(0).count,players.get(0).get(1).count,
                players.get(0).get(2).count,players.get(0).get(3).count,players.get(0).get(4).count};
        String[] arr2 = {players.get(1).get(0).count,players.get(1).get(1).count,
                players.get(1).get(2).count,players.get(0).get(3).count,players.get(0).get(4).count};

        swap(arr1);
        swap(arr2);

        String[][] cur = {arr1,arr2};
        for (int i = 0; i < 2; i++) {
            if (    players.get(i).get(0).suit.equals(players.get(i).get(1).suit)   //同花顺
                    && players.get(i).get(0).suit.equals(players.get(i).get(2).suit)
                    && players.get(i).get(0).suit.equals(players.get(i).get(3).suit)
                    && players.get(i).get(0).suit.equals(players.get(i).get(4).suit)
                    && cur[i][0].compareTo(cur[i][1]) == 1 && cur[i][1].compareTo(cur[i][2]) == 1
                    && cur[i][2].compareTo(cur[i][3]) == 1 && cur[i][3].compareTo(cur[i][4]) == 1
                    ) {
                player[i] = 9000000.0f;
                player[i] += compaire(cur[i]);

            }
            else if ((cur[i][0].equals(cur[i][1]) && cur[i][1].equals(cur[i][2])&& cur[i][2].equals(cur[i][3]))||
                    (cur[i][1].equals(cur[i][2]) && cur[i][2].equals(cur[i][3])&& cur[i][3].equals(cur[i][4]))) {//铁支
                player[i] = 8000000.0f;
                player[i] += compaire(cur[i]);
            } else if (
                    ( (cur[i][0].equals(cur[i][1]) && cur[i][1].equals(cur[i][2]))||
                    (cur[i][1].equals(cur[i][2]) && cur[i][2].equals(cur[i][3]))||
                    (cur[i][2].equals(cur[i][3]) && cur[i][3].equals(cur[i][4])) )&&
                    (cur[i][0].equals(cur[i][1]) || cur[i][1].equals(cur[i][2])||
                            cur[i][2].equals(cur[i][3]) || cur[i][3].equals(cur[i][4]))) {//葫芦
                player[i] = 7000000.0f;
                player[i] += compaire(cur[i]);
            } else if (players.get(i).get(0).suit.equals(players.get(i).get(1).suit)
                    && players.get(i).get(0).suit.equals(players.get(i).get(2).suit)
                    && players.get(i).get(0).suit.equals(players.get(i).get(3).suit)
                    && players.get(i).get(0).suit.equals(players.get(i).get(4).suit)) {
                player[i] = 6000000.0f;
                player[i] += compaire(cur[i]);//同花
            } else if (cur[i][0].compareTo(cur[i][1]) == 1 && cur[i][1].compareTo(cur[i][2]) == 1
                    && cur[i][2].compareTo(cur[i][3]) == 1 && cur[i][3].compareTo(cur[i][4]) == 1 ) {//顺子
                player[i] = 5000000.0f;
                player[i] += compaire(cur[i]);
            } else if ((cur[i][0].equals(cur[i][1]) && cur[i][1].equals(cur[i][2]))||
                    (cur[i][1].equals(cur[i][2]) && cur[i][2].equals(cur[i][3]))||
                    (cur[i][2].equals(cur[i][3]) && cur[i][3].equals(cur[i][4]))) {//三条
                player[i] = 400000.0f;
                player[i] += compaire(cur[i]);
            } else if ((cur[i][0].equals(cur[i][1]) && cur[i][2].equals(cur[i][3]))||
                    (cur[i][0].equals(cur[i][1]) && cur[i][3].equals(cur[i][4]))||
                    (cur[i][1].equals(cur[i][2]) && cur[i][3].equals(cur[i][4]))) {//两对
                player[i] = 3000000.0f;
                player[i] += compaire(cur[i]);
            } else if (cur[i][0].equals(cur[i][1]) || cur[i][1].equals(cur[i][2])||
                       cur[i][2].equals(cur[i][3]) || cur[i][3].equals(cur[i][4])) {// 对子
                player[i] = 2000000.0f;
                player[i] += compaire(cur[i]);

            } else if (cur[i][0].compareTo(cur[i][1]) != 0 && cur[i][0].compareTo(cur[i][2]) != 0
                    && cur[i][0].compareTo(cur[i][3]) != 0 && cur[i][0].compareTo(cur[i][4]) != 0) {//单张牌
                player[i] = 1000000.0f;
                player[i] += compaire(cur[i]);
            }
        }

        if (player[0] == player[1]){
            System.out.println("平局");
        }else if (player[0] > player[1]){
            System.out.println("Black wins");
        }else System.out.println("White wins");

    }
    private static void swap(String[] arr) {

        for (int i = 0; i < 5; i++) {
            if (arr[i].equals("T")) {
                arr[i] = ":";
            }
            if (arr[i].equals("J")) {
                arr[i] = ";";
            }
            if (arr[i].equals("Q")) {
                arr[i] = "<";
            }
            if (arr[i].equals("K")) {
                arr[i] = "=";
            }
            if (arr[i].equals("A")) {
                arr[i] = ">";
            }
        }
      //冒泡排序
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5 - i - 1; j++) {
                if (arr[j].compareTo(arr[j+1])<0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return;
    }
    private static float compaire(String[] arr) {
        float[] ret = new float[5];
        for (int i = 0; i < 5; i++) {
            if (arr[i] == ">") {
                ret[i] = 0.13f;
            } else if (arr[i] == "=") {
                ret[i] = 0.12f;
            } else if (arr[i] == "<") {
                ret[i] = 0.11f;
            } else if (arr[i] == ";") {
                ret[i] = 0.10f;
            } else if (arr[i] == ":") {
                ret[i] = 0.09f;
            } else if (arr[i] == "9") {
                ret[i] = 0.08f;
            } else if (arr[i] == "8") {
                ret[i] = 0.07f;
            } else if (arr[i] == "7") {
                ret[i] = 0.06f;
            } else if (arr[i] == "6") {
                ret[i] = 0.05f;
            } else if (arr[i] == "5") {
                ret[i] = 0.04f;
            } else if (arr[i] == "4") {
                ret[i] = 0.03f;
            } else if (arr[i] == "3") {
                ret[i] = 0.02f;
            } else if (arr[i] == "2") {
                ret[i] = 0.01f;
            }
            if (i == 0) {
                ret[i] *= 100000;
            } else if (i == 1) {
                ret[i] *= 10000;
            } else if (i == 2) {
                ret[i] *= 1000;
            } else if (i == 3) {
                ret[i] *= 100;
            } else if (i == 4) {
                ret[i] *= 10;
            } else {
                ret[i] *= 1;
            }
        }
        return ret[0] + ret[1] + ret[2]+ ret[3]+ ret[4];
    }
}
