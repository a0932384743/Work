package com.homework.game;

import com.homework.entity.PokerCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PokerCardGames {

    private ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
    private ArrayList<PokerCard> cardsComputer = new ArrayList<PokerCard>();
    private ArrayList<PokerCard> cardsPlayer = new ArrayList<PokerCard>();

    public int sendCardInit(int id) {
        int counter = 1;
        int point = 0;
        if (id == 1) {
            point = sendCard(id, counter) + sendCard(id, counter + 1);
            System.out.println("玩家的總點數: " + point);
        } else {
            int x = sendCard(id, counter);
            counter = counter + 1;
            int y = sendCard(id, counter);
            point = x + y;
            System.out.println("電腦可能的點數: " + (point - x + 1) + "~" + (point - x + 10));
        }
        return point;
    }

    public int sendCard(int id, int counter) {
        int sum = 0;
        PokerCard curr = cards.get(0);
        if (id == 0) {
            if (counter == 1) {
                System.out.println("第" + counter + "張牌: *");
                sum += curr.getValue();
            } else {
                sum += curr.getValue();
                System.out.print("第" + counter + "張牌: ");
                System.out.println(curr.getIconMask() + curr.getMask());
            }
            cardsComputer.add(cards.get(0));
        } else {
            sum += curr.getValue();
            System.out.print("第" + counter + "張牌: ");
            System.out.println(curr.getIconMask() + curr.getMask());
            cardsPlayer.add(cards.get(0));
        }
        cards.remove(0);
        return sum;
    }

    public int extraCard(int id, int counter, int point) {
        int sum = point;
        System.out.print("第" + (counter + 2) + "張牌: ");
        System.out.println(cards.get(0).getIconMask() + cards.get(0).getMask());
        sum += cards.get(0).getValue();
        if (id == 0) {
            cardsComputer.add(cards.get(0));
        } else {
            cardsPlayer.add(cards.get(0));
        }
        cards.remove(0);
        return sum;
    }

    public void printCards() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i).getIconMask() + cards.get(i).getMask() + " ;");
        }
    }

    public void init() {
        initMethod(1);
        initMethod(2);
        initMethod(3);
        initMethod(4);
    }

    public void initMethod(int icon) {
        int index = 0;
        String iconMask = "";
        //初始化花色、字標
        if (icon == 1) {
            index = 0;
            iconMask = "♥";
        } else if (icon == 2) {
            index = 13;
            iconMask = "♦";
        } else if (icon == 3) {
            index = 26;
            iconMask = "♠";
        } else if (icon == 4) {
            index = 39;
            iconMask = "♣";
        }
        for (int i = index; i < index + 13; i++) {
            if (i == index + 0) {
                PokerCard c = new PokerCard("A", i - index + 1, icon, iconMask, true);
                cards.add(c);
            } else if (i > index && i < 10 + index) {
                PokerCard c = new PokerCard(String.valueOf(i + 1 - index), i - index + 1, icon, iconMask, true);
                cards.add(c);
            } else if (i >= 10 + index && i < 13 + index) {
                if (i == 10 + index) {
                    PokerCard c = new PokerCard("J", 10, icon, iconMask, true);
                    cards.add(c);
                } else if (i == 11 + index) {
                    PokerCard c = new PokerCard("Q", 10, icon, iconMask, true);
                    cards.add(c);
                } else if (i == 12 + index) {
                    PokerCard c = new PokerCard("K", 10, icon, iconMask, true);
                    cards.add(c);
                }
            }
        }
    }

    public void printComputer() {
        System.out.println("電腦的牌: ");
        for (int j = 0; j < cardsComputer.size(); j++) {
            System.out.print("第" + (j + 1) + "張牌: ");
            System.out.println(cardsComputer.get(j).getIconMask() + cardsComputer.get(j).getMask());
        }
    }

    public void printPlayer() {
        System.out.println("玩家的牌: ");
        for (int j = 0; j < cardsPlayer.size(); j++) {
            System.out.print("第" + (j + 1) + "張牌: ");
            System.out.println(cardsPlayer.get(j).getIconMask() + cardsPlayer.get(j).getMask());
        }
    }

    public void reset() {
        init();
        Collections.shuffle(cards);
        System.out.println("電腦的牌: ");
        int computer = sendCardInit(0);
        System.out.println("----------------");
        System.out.println("玩家的牌: ");
        int player = sendCardInit(1);
        System.out.println("----------------");
        System.out.println("請輸入指令:");
        System.out.println("輸入K表示開牌;輸入A表示加牌;輸入R表示重置;輸入Q表示退出");
        Scanner s = new Scanner(System.in);
        while (true) {
            String command = s.nextLine();
            if (command.equals("K") || command.equals("k")) {
                if (player > 21) {
                    System.out.println("****************");
                    printComputer();
                    System.out.println("電腦的最終點數: " + computer);
                    System.out.println("----------------");
                    printPlayer();
                    System.out.println("玩家的最終點數: " + player);
                    System.out.println("真遺憾，您輸了，電腦獲勝！");
                }
                if (computer < 16) {
                    System.out.println("電腦加牌");
                    for (int j = 0; j < cardsComputer.size(); j++) {
                        System.out.print("第" + (j + 1) + "張牌: ");
                        System.out.println(cardsComputer.get(j).getIconMask() + cardsComputer.get(j).getMask());
                    }
                }
                int extraCounter = 0;
                while (computer < 16) {
                    computer = extraCard(0, extraCounter + 1, computer);
                    extraCounter = extraCounter + 1;
                }
                if (computer > 21) {
                    System.out.println("****************");
                    printComputer();
                    System.out.println("電腦的最終點數: " + computer);
                    System.out.println("----------------");
                    printPlayer();
                    System.out.println("玩家的最終點數: " + player);
                    System.out.println("真厲害，您贏了，玩家獲勝！");
                } else if (computer > player) {
                    System.out.println("****************");
                    printComputer();
                    System.out.println("電腦的最終點數: " + computer);
                    System.out.println("----------------");
                    printPlayer();
                    System.out.println("玩家的最終點數: " + player);
                    System.out.println("真遺憾，您輸了，電腦獲勝！");
                } else if (computer < player) {
                    System.out.println("****************");
                    printComputer();
                    System.out.println("電腦的最終點數: " + computer);
                    System.out.println("----------------");
                    printPlayer();
                    System.out.println("玩家的最終點數: " + player);
                    System.out.println("真厲害，您贏了，玩家獲勝！");
                } else {
                    System.out.println("****************");
                    printComputer();
                    System.out.println("電腦的最終點數: " + computer);
                    System.out.println("----------------");
                    printPlayer();
                    System.out.println("玩家的最終點數: " + player);
                    System.out.println("平局，無人獲勝！");
                }
            } else if (command.equals("A") || command.equals("a")) {
                System.out.println("玩家加牌");
                for (int j = 0; j < cardsPlayer.size(); j++) {
                    System.out.print("第" + (j + 1) + "張牌: ");
                    System.out.println(cardsPlayer.get(j).getIconMask() + cardsPlayer.get(j).getMask());
                }
                player = extraCard(1, cardsPlayer.size() - 2 + 1, player);
                System.out.println("玩家的總點數: " + player);
                if (player > 21) {
                    System.out.println("****************");
                    printComputer();
                    System.out.println("電腦的最終點數: " + computer);
                    System.out.println("----------------");
                    printPlayer();
                    System.out.println("玩家的最終點數: " + player);
                    System.out.println("真遺憾，您輸了，電腦獲勝！");
                }
            } else if (command.equals("R") || command.equals("r")) {
                System.out.println("21點遊戲");
                System.out.println("****************");
                System.out.println("A表示1點,2-10表示2-10點,J、Q、K表示10點");
                cards.clear();
                cardsComputer.clear();
                cardsPlayer.clear();
                init();
                Collections.shuffle(cards);
                System.out.println("電腦的牌: ");
                computer = sendCardInit(0);
                System.out.println("----------------");
                System.out.println("玩家的牌: ");
                player = sendCardInit(1);
                System.out.println("----------------");
                print();
            } else if (command.equals("Q") || command.equals("q")) {
                System.err.println("成功退出遊戲");
                break;
            } else {
                System.err.println("無效輸入！輸入K表示開牌;輸入A表示加牌;輸入R表示重置;輸入Q表示退出");
            }
        }
        s.close();
    }

    public void print() {
        System.out.println("請輸入指令:");
        System.out.println("輸入K表示開牌;輸入A表示加牌;輸入R表示重置;輸入Q表示退出");
    }
}
