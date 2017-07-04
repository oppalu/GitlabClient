package com.example.phoebegl.gitlabclient.model.analyse;

import java.util.List;

/**
 * Created by phoebegl on 2017/7/4.
 */

public class testResult {

    private String git_url;
    private boolean compile_succeeded;
    private boolean tested;
    private List<testcase> testcases;

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public boolean isCompile_succeeded() {
        return compile_succeeded;
    }

    public void setCompile_succeeded(boolean compile_succeeded) {
        this.compile_succeeded = compile_succeeded;
    }

    public boolean isTested() {
        return tested;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    public List<testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<testcase> testcases) {
        this.testcases = testcases;
    }
}
