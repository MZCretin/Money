/**
 *
 *   Copyright (C) 2014 Paul Cech
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.diandian.ycdyus.moneymanager.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.utils.HawkUtils;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.communication.IOnBarClickedListener;
import org.eazegraph.lib.models.BarModel;

import java.util.List;

public class BarChartFragment extends ChartFragment {
    private int[] colors = new int[]{Color.parseColor("#FE6DA8"), Color.parseColor("#56B7F1"), Color.parseColor("#CDA67F"),
            Color.parseColor("#FED70E"),Color.parseColor("#63F0FF"),Color.parseColor("#FF7AEC"),Color.parseColor("#A6FF2B")};
    private int index;

    public BarChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        mBarChart = (BarChart) view.findViewById(R.id.barchart);

        mBarChart.setOnBarClickedListener(new IOnBarClickedListener() {
            @Override
            public void onBarClicked(int _Position) {
                Log.d("BarChart", "Position: " + _Position);
            }
        });

        loadData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBarChart.startAnimation();
    }

    @Override
    public void restartAnimation() {
        mBarChart.startAnimation();
    }

    @Override
    public void onReset() {

    }

    private void loadData() {
        List<HawkUtils.DataModel> dataList = new HawkUtils().getDataList();

        for (HawkUtils.DataModel data:dataList) {
            mBarChart.addBar(new BarModel((float) data.getCount(),colors[index++]));
        }
    }

    private BarChart mBarChart;
}
