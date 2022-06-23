package com.homework;


import com.homework.game.PokerCardGames;

public class Application {

    public static void main(String[] arg) {

        try {
            PokerCardGames pokerCardGames = new PokerCardGames();
            System.out.println("21點遊戲");
            System.out.println("****************");
            System.out.println("A表示1點,2-10表示2-10點,J、Q、K表示10點");
            pokerCardGames.reset();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
