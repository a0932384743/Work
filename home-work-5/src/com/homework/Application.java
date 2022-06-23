package com.homework;


import com.homework.constant.FileConstant;
import com.homework.constant.MySQLConstant;
import com.homework.db.MySqlConnector;
import com.homework.entity.StudentHeight;
import com.homework.entity.Traveler;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Application {

    public static void main(String[] arg) {
        try {
            MySqlConnector mySqlConnector = new MySqlConnector(MySQLConstant.JDBC_DRIVER, MySQLConstant.DB_URL, MySQLConstant.USER, MySQLConstant.PASSWORD);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(FileConstant.FILE_5_PATH));
            BufferedReader reader = new BufferedReader(isr);
            reader.readLine();//過濾英文標頭
            reader.readLine();//過濾中文標頭
            String line = null;

            List<Traveler> travelers = new ArrayList<>();
            //存入資料
            while ((line = reader.readLine()) != null) {
                line = String.join(" ", line.split(", "));
                line = String.join(",,", line.split(",,"));
                line = String.join(",", line.split("\","));
                line = String.join(",", line.split(",\""));

                String[] strs = line.split("\\s*,\\s*");

                if (StringUtils.isNotEmpty(strs[0]) && StringUtils.isNumeric(strs[0])) {
                    try {
                        Long id = Long.parseLong(strs[0]);
                        Boolean isAlive = "1".equals(strs[1]);
                        String ticketType = StringUtils.isNotEmpty(strs[2]) ? strs[2].trim() : null;
                        String travelerName = StringUtils.isNotEmpty(strs[3]) ? strs[3].trim() : null;
                        String sex = StringUtils.isNotEmpty(strs[4]) ? strs[4].trim() : null;
                        Integer age = StringUtils.isNotEmpty(strs[5]) && StringUtils.isNumeric(strs[5]) ? Integer.parseInt(strs[5].trim()) : null;
                        Integer siblingNum = StringUtils.isNotEmpty(strs[6]) && StringUtils.isNumeric(strs[5]) ? Integer.parseInt(strs[5].trim()) : null;
                        Integer parentNum = StringUtils.isNotEmpty(strs[7]) && StringUtils.isNumeric(strs[7]) ? Integer.parseInt(strs[7].trim()) : null;
                        String ticketSeries = StringUtils.isNotEmpty(strs[8]) ? strs[8].trim() : null;
                        Integer price = StringUtils.isNotEmpty(strs[9]) && StringUtils.isNumericSpace(strs[9]) ? Integer.parseInt(strs[9].trim()) : null;
                        String boardSeries = StringUtils.isNotEmpty(strs[10]) ? strs[10].trim() : null;
                        String port = StringUtils.isNotEmpty(strs[11]) ? strs[11].trim() : null;
                        Traveler traveler = new Traveler(id, isAlive, ticketType, travelerName, sex, age, siblingNum, parentNum, ticketSeries, price, boardSeries, port);
                        travelers.add(traveler);
                    } catch (Exception e) {
                        System.out.println("格式錯誤: " + line);
                    }

                }
            }


            if (travelers.size() > 0) {
                //存入資料
                mySqlConnector.addTravelerList(travelers);
            }

            //查詢出所有倖存男性的資料以及資料筆數
            List<String> result = mySqlConnector.countTitanicBySurvivedAndSex(true, "male");
            System.out.println("所有倖存男性的資料以及資料筆數: " + result.size());

            FileWriter writer = new FileWriter("QueryOutput.txt");
            writer.write(String.join("\n", result.stream().toArray(String[]::new)));
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
