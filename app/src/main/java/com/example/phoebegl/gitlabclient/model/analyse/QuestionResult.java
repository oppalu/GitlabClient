package com.example.phoebegl.gitlabclient.model.analyse;

/**
 * Created by phoebegl on 2017/7/3.
 */

public class QuestionResult {

    private int questionId;
    private String questionTitle;
    private metricdata metricData;
    private testResult testResult;
    private scoreresult scoreResult;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public metricdata getMetricData() {
        return metricData;
    }

    public void setMetricData(metricdata metricData) {
        this.metricData = metricData;
    }

    public com.example.phoebegl.gitlabclient.model.analyse.testResult getTestResult() {
        return testResult;
    }

    public void setTestResult(com.example.phoebegl.gitlabclient.model.analyse.testResult testResult) {
        this.testResult = testResult;
    }

    public scoreresult getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(scoreresult scoreResult) {
        this.scoreResult = scoreResult;
    }
}
