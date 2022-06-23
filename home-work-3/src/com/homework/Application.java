package com.homework;

import com.homework.constant.FileConstant;
import com.homework.entity.GamePlayer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Application {

    public static void main(String[] arg) {
        try {
            //音符檔案
            String[] tones = new String[]{"C5qq", "D5qq", "E5qq", "F5qq", "G5qq", "A5qq", "B5qq", "C6qq"};
            JFrame jFrame = new JFrame();
            Random random = new Random();
            Player player = new Player();
            List<Integer> answers = new ArrayList<>();
            List<GamePlayer> gamePlayers = new ArrayList<>();

            //讀取檔案
            File file = new File(FileConstant.FILE_3_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String str = reader.nextLine();
                System.out.println(str);
                String[] strs = str.split(" ");
                if (strs.length > 0) {
                    gamePlayers.add(new GamePlayer(strs[1].trim(), Integer.parseInt(strs[3])));
                }
            }
            reader.close();

            String playerName = JOptionPane.showInputDialog(jFrame, "請輸入您的名字進行遊戲", "絕對音感記憶遊戲", JOptionPane.INFORMATION_MESSAGE);
            if (StringUtils.isEmpty(playerName)) {
                return;
            }

            JOptionPane.showMessageDialog(jFrame, "請聆聽以下聲音");
            player.play(new Pattern(String.join(" ", tones)));
            FileWriter fw = new FileWriter(FileConstant.FILE_3_PATH);

            while (true) {
                int question = random.nextInt((8 - 1) + 1) + 1;
                answers.add(question);
                System.out.println(String.format("Round %d", answers.size()));
                player.play(new Pattern(String.join(" ", answers.stream().map(a -> tones[a - 1]).toArray(String[]::new)).trim()));
                String guess = JOptionPane.showInputDialog(jFrame, "您的答案", "請根據所聽到之音符，輸入1-8的數字串", JOptionPane.INFORMATION_MESSAGE);
                if (StringUtils.isNoneBlank(guess)) {
                    if (guess.equals(String.join("", answers.stream().map(a -> a + "").toArray(String[]::new)))) {
                        JOptionPane.showMessageDialog(jFrame, "正確, 請繼續");
                    } else {
                        JOptionPane.showMessageDialog(jFrame, String.format("答題錯誤, 共完成 %d 題", answers.size() - 1));
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(jFrame, String.format("遊戲結束, 共完成 %d 題", answers.size() - 1));
                    break;
                }
            }

            gamePlayers.add(new GamePlayer(playerName, answers.size() - 1));
            //重新排行並寫入資料
            String result = String.join("\n", gamePlayers.stream().sorted((a, b) -> {
                return b.getPoints() - a.getPoints();
            }).map(gamePlayer -> {
                return gamePlayers.stream().sorted((a, b) -> {
                    return b.getPoints() - a.getPoints();
                }).collect(Collectors.toList()).indexOf(gamePlayer) + 1 + ". " + gamePlayer.getName() + " 分數: " + gamePlayer.getPoints();
            }).toArray(String[]::new));
            JOptionPane.showMessageDialog(jFrame, result);
            fw.write(result);
            fw.close();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
