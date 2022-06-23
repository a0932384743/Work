package com.homework.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StudentHeight {
    public int schoolYear;
    public int age;
    public float averageHeight;
    public float maleHeight;
    public float femaleHeight;

    public StudentHeight(int schoolYear, int age, float averageHeight, float maleHeight, float femaleHeight) {
        this.schoolYear = schoolYear;
        this.age = age;
        this.averageHeight = averageHeight;
        this.maleHeight = maleHeight;
        this.femaleHeight = femaleHeight;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(float averageHeight) {
        this.averageHeight = averageHeight;
    }

    public float getMaleHeight() {
        return maleHeight;
    }

    public void setMaleHeight(float maleHeight) {
        this.maleHeight = maleHeight;
    }

    public float getFemaleHeight() {
        return femaleHeight;
    }

    public void setFemaleHeight(float femaleHeight) {
        this.femaleHeight = femaleHeight;
    }

    public static StudentHeight convertToStudentHeight(String str) {
        String strs[] = str.split("\t", -1);
        int schoolYear = StringUtils.isNoneBlank(strs[0]) ? Integer.parseInt(strs[0]) : 0;
        int age = StringUtils.isNoneBlank(strs[1]) ? Integer.parseInt(strs[1]) : 0;
        float averageHeight = StringUtils.isNoneBlank(strs[2]) ? Float.parseFloat(strs[2]) : 0;
        float maleHeight = StringUtils.isNoneBlank(strs[3]) ? Float.parseFloat(strs[3]) : 0;
        float femailHeight = StringUtils.isNoneBlank(strs[4]) ? Float.parseFloat(strs[4]) : 0;
        if (StringUtils.isNoneBlank(str)) {
            return new StudentHeight(schoolYear, age, averageHeight, maleHeight, femailHeight);
        } else {
            return null;
        }
    }
}
