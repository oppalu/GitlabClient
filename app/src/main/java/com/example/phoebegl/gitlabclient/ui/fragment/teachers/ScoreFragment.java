package com.example.phoebegl.gitlabclient.ui.fragment.teachers;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.CourseService;
import com.example.phoebegl.gitlabclient.model.sore.Ques;
import com.example.phoebegl.gitlabclient.model.sore.Score;
import com.example.phoebegl.gitlabclient.model.sore.studentScore;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import rx.Subscriber;

/**
 * Created by phoebegl on 2017/6/20.
 */

public class ScoreFragment extends BaseBackFragment {

    @BindView(R.id.chart_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.chart)
    ColumnChartView chart;

    private View mView;
    private ColumnChartData data;
    static int assignId;

    private static String[] ranks = new String[]
            {"不及格(0~60)","及格(61~70)","中等(71~80)","良好(81~90)","优秀(91~100)"};
    int a=0,b=0,c=0,d=0,e=0;
    private int[] scores = new int[5];

    public List<studentScore> list = new ArrayList<>();

    public static ScoreFragment newInstance(int id) {
        Bundle args = new Bundle();
        ScoreFragment fragment = new ScoreFragment();
        ScoreFragment.assignId = id;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.score_charts, container, false);
        ButterKnife.bind(this,mView);
        initView();
        initData();
        return attachToSwipeBack(mView);
    }

    private void initView() {
        mToolbar.setTitle("成绩分布");
        initToolbarNav(mToolbar);
        chart.setZoomEnabled(false);
    }

    private void initData() {
        CourseService.getInstance().getScore(38)
                .subscribe(new Subscriber<Score>() {
                    @Override
                    public void onCompleted() {
                        for(studentScore s : list) {
                            if(s.getScore()>=0 && s.getScore()<=60)
                                a++;
                            if(s.getScore()>=61 && s.getScore()<=70)
                                b++;
                            if(s.getScore()>=71 && s.getScore()<=80)
                                c++;
                            if(s.getScore()>=81 && s.getScore()<=90)
                                d++;
                            if(s.getScore()>=91 && s.getScore()<=100)
                                e++;
                        }
                        scores[0] = a;
                        scores[1] = b;
                        scores[2] = c;
                        scores[3] = d;
                        scores[4] = e;
                        setChart();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Score score) {
                        List<Ques> ques = score.getQuestions();
                        for(Ques q : ques) {
                            list.addAll(q.getStudents());
                        }
                    }
                });
    }

    public void setChart() {
//        int numSubcolumns = 1;
        int numColumns = 5;
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            values.add(new SubcolumnValue((float) scores[i], ChartUtils.pickColor()));
            Column column = new Column(values);
            column.setHasLabels(true);
            columns.add(column);
            axisValuesX.add(new AxisValue(i).setValue(i).setLabel(ranks[i]));
        }

        data = new ColumnChartData(columns);

        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setValues(axisValuesX);
        axisX.setName("成绩分布");
        axisY.setName("人数");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        chart.setColumnChartData(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
