package com.example.phoebegl.gitlabclient.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class Status {

    private Map<String,String> status;
    private static Status instance;

    public static Status getInstance() {
        if(instance == null)
            instance = new Status();
        return instance;
    }

    private Status() {
        status = new HashMap<>();
        status.put("newly","新建态");
        status.put("initing","正在初始化");
        status.put("initFail","初始化失败");
        status.put("initSuccess","初始化成功");
        status.put("ongoing","考试正在进行");
        status.put("timeup","考试时间到");
        status.put("analyzing","正在分析结果");
        status.put("analyzingFinish","结果分析完毕");
    }

    public String getStatus(String eng) {
        return status.get(eng);
    }
}
