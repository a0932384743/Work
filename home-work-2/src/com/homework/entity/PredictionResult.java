package com.homework.entity;

public class PredictionResult {
    private String expected;
    private String predicted;

    public PredictionResult(String expected, String predicted) {
        this.expected = expected;
        this.predicted = predicted;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getPredicted() {
        return predicted;
    }

    public void setPredicted(String predicted) {
        this.predicted = predicted;
    }
}
