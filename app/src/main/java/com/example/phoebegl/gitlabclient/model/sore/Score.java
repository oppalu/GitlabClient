package com.example.phoebegl.gitlabclient.model.sore;

import com.example.phoebegl.gitlabclient.model.sore.Ques;

import java.util.List;

/**
 * Created by phoebegl on 2017/6/20.
 */

public class Score {
    private int assignmentId;
    private List<Ques> questions;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<Ques> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Ques> questions) {
        this.questions = questions;
    }
}

