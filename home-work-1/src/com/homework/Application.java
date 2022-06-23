package com.homework;

import com.homework.constant.FileConstant;
import com.homework.entity.RegressionModel;
import com.homework.entity.StudentHeight;
import com.homework.math.RegressionEquation;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] arg) {
        List<StudentHeight> studentHeightList = new ArrayList<>();

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(FileConstant.FILE_1_PATH));
            BufferedReader reader = new BufferedReader(isr);
            reader.readLine();//過濾標頭
            String line = null;
            while ((line = reader.readLine()) != null) {
                StudentHeight studentHeight = StudentHeight.convertToStudentHeight(line);
                if (studentHeight != null) {
                    studentHeightList.add(studentHeight);
                }
            }

            Scanner scan = new Scanner(System.in);
            System.out.println("請輸入年齡");
            String age = scan.nextLine();
            System.out.println("請輸入性別:M or F");
            String sex = scan.nextLine();
            System.out.println(String.format("輸入的資料為 年齡:%s , 性別: %s", age, sex));

            List<Float> dependentValues = studentHeightList.stream().filter(studentHeight -> studentHeight.getAge() == Integer.parseInt(age)).map(studentHeight -> {
                if ("M".equals(sex)) {
                    return studentHeight.getMaleHeight();
                } else {
                    return studentHeight.getFemaleHeight();
                }
            }).collect(Collectors.toList());

            List<Integer> independentValues = studentHeightList.stream().filter(studentHeight -> studentHeight.getAge() == Integer.parseInt(age)).map(studentHeight -> {
                return studentHeight.getSchoolYear();
            }).collect(Collectors.toList());

            RegressionEquation re = new RegressionEquation(dependentValues.stream().mapToDouble(num -> num).toArray(), independentValues.stream().mapToDouble(num -> num).toArray());
            RegressionModel regressionModel = re.getRegressionModel();
            System.out.println(String.format("linear function : Y = %fX + %f", regressionModel.getB1(), regressionModel.getB0()));
            System.out.println(String.format("Coefficient of Determination(R^2): %f", regressionModel.getR()));
            System.out.println(String.format("T test :%f ", new BigDecimal(+regressionModel.getP())));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
