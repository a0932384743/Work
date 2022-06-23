package com.homework;

import com.homework.constant.FileConstant;
import com.homework.entity.Abalone;
import com.homework.entity.ConfusionMatrix;
import com.homework.entity.PredictionResult;
import com.homework.entity.StudentHeight;
import com.homework.math.KNNClassify;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void main(String[] arg) {
        List<Abalone> abalones = new ArrayList<>();

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(FileConstant.FILE_2_PATH));
            BufferedReader reader = new BufferedReader(isr);
            reader.readLine();//過濾標頭
            String line = null;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (StringUtils.isNotEmpty(line)) {

                    String[] strs = line.split("\\s*,\\s*");
                    String sex = StringUtils.isNotEmpty(strs[0]) ? strs[0].trim() : "";
                    Double length = StringUtils.isNotEmpty(strs[1]) ? Double.parseDouble(strs[1].trim()) : null;
                    Double diameter = StringUtils.isNotEmpty(strs[2]) ? Double.parseDouble(strs[2].trim()) : null;
                    Double height = StringUtils.isNotEmpty(strs[3]) ? Double.parseDouble(strs[3].trim()) : null;
                    Double totalWeight = StringUtils.isNotEmpty(strs[4]) ? Double.parseDouble(strs[4].trim()) : null;
                    Double weight = StringUtils.isNotEmpty(strs[5]) ? Double.parseDouble(strs[5].trim()) : null;
                    Double visceralWeight = StringUtils.isNotEmpty(strs[6]) ? Double.parseDouble(strs[6].trim()) : null;
                    Double shellWeight = StringUtils.isNotEmpty(strs[7]) ? Double.parseDouble(strs[7].trim()) : null;
                    Double ageRing = StringUtils.isNotEmpty(strs[8]) && StringUtils.isNumeric(strs[8]) ? Double.parseDouble(strs[8].trim()) : null;
                    Abalone abalone = new Abalone(sex, length, diameter, height, totalWeight, weight, visceralWeight, shellWeight, ageRing);
                    abalones.add(abalone);
                }
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("請輸入K值：");
            int k = scanner.nextInt();

            List<Abalone> trainingData = abalones.subList(0, 3800);
            List<Abalone> testData = abalones.subList(abalones.size() - 377, abalones.size());
            KNNClassify knnClassify = new KNNClassify(k);
            knnClassify.setTrainingSet(trainingData);
            List<PredictionResult> predictionsResults = new ArrayList<>();


            int goodPredictions = 0;

            for (Abalone entry : testData) {
                String prediction = knnClassify.predict(entry);
                PredictionResult result = new PredictionResult(entry.getSex(), prediction);
                predictionsResults.add(result);
                if (prediction.equals(entry.getSex())) {
                    goodPredictions++;
                }
                System.out.println("prediction = " + prediction + " | expected = " + entry.getSex());
            }

            System.out.println("-----");
            System.out.println("For K=" + k + " and results are:");
            System.out.println("     - Total predictions = " + testData.size() + " | good = " + goodPredictions + " | bad = " + (testData.size() - goodPredictions));
            double accuracy = goodPredictions * 1.0 / testData.size();
            System.out.println("     - Accuracy = " + accuracy * 100 + "%");
            createAndDisplayConfusionMatrix(predictionsResults);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void createAndDisplayConfusionMatrix(List<PredictionResult> results) {
        ConfusionMatrix cm = new ConfusionMatrix();
        for (PredictionResult result : results) {
            cm.increaseValue(result.getExpected(), result.getPredicted(), 1);
        }
        System.out.println("\n" + cm);
    }
}

