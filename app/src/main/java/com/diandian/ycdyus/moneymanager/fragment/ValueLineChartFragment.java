/**
 * Copyright (C) 2014 Paul Cech
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.diandian.ycdyus.moneymanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.model.BudgetManagerModel;
import com.diandian.ycdyus.moneymanager.utils.HawkUtils;
import com.orhanobut.hawk.Hawk;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.communication.IOnPointFocusedListener;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.List;

public class ValueLineChartFragment extends ChartFragment {


    public ValueLineChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_value_line_chart, container, false);
        mValueLineChart = (ValueLineChart) view.findViewById(R.id.linechart);
        loadData();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mValueLineChart.startAnimation();
    }

    @Override
    public void restartAnimation() {
        mValueLineChart.startAnimation();
    }

    @Override
    public void onReset() {
        mValueLineChart.resetZoom(true);
    }

    private void loadData() {
        List<HawkUtils.DataModel> dataList = new HawkUtils().getDataList();


        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF63CBB0);

        for (HawkUtils.DataModel data : dataList) {
            series.addPoint(new ValueLinePoint((float) data.getCount()));
        }

        float standValue = 0;
        if (Hawk.get("budget_manager") != null) {
            BudgetManagerModel model = Hawk.get("budget_manager");
            standValue = model.getBudgetCount();
            if (standValue != 0) {
                mValueLineChart.addStandardValue(standValue);
            }
        }

        mValueLineChart.addSeries(series);
        mValueLineChart.setOnPointFocusedListener(new IOnPointFocusedListener() {
            @Override
            public void onPointFocused(int _PointPos) {
                Log.d("Test", "Pos: " + _PointPos);
            }
        });
    }

    private ValueLineChart mValueLineChart;
}
