package com.example.phoebegl.gitlabclient.model.analyse;

import java.util.List;

/**
 * Created by phoebegl on 2017/7/3.
 */

public class Analyse {

    private int studentId;
    private int assignmentId;
    private List<QuestionResult> questionResults;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionResult> getQuestionResult() {
        return questionResults;
    }

    public void setQuestionResult(List<QuestionResult> questionResult) {
        this.questionResults = questionResult;
    }
}
