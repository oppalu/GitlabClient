package com.example.phoebegl.gitlabclient.model.sore;

import java.util.List;

/**
 * Created by phoebegl on 2017/6/20.
 */
public class Ques {
    private info questionInfo;
    private List<studentScore> students;

    public info getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(info questionInfo) {
        this.questionInfo = questionInfo;
    }

    public List<studentScore> getStudents() {
        return students;
    }

    public void setStudents(List<studentScore> students) {
        this.students = students;
    }
}
