package com.example.phoebegl.gitlabclient.model.analyse;

/**
 * Created by phoebegl on 2017/7/4.
 */

public class scoreresult {

    private String git_url;
    private double score;
    private boolean scored;

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
