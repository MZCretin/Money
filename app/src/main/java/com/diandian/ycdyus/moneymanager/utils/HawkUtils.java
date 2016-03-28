package com.diandian.ycdyus.moneymanager.utils;

import com.diandian.ycdyus.moneymanager.bean.ManagerBean;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by cretin on 16/3/28.
 */
public class HawkUtils {
    public List<DataModel> getDataList(){
        List<ManagerBean> tempList = Hawk.get("main_list");
        List<DataModel> dataList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int todayWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = todayWeek; i < todayWeek + 7; i++) {
            if (tempList != null) {
                List<ManagerBean> newList = new ArrayList<>();
                for (ManagerBean bean : tempList) {
                    if (bean.getWeek() == i % 7) {
                        newList.add(bean);

                        boolean flag = false;
                        for (DataModel dataModel : dataList) {
                            if (dataModel.getName().equals(bean.getType())) {
                                dataModel.setCount(dataModel.getCount() + bean.getCount());
                                flag = true;
                                break;
                            }
                        }

                        if (flag == false) {
                            DataModel dataModel = new DataModel();
                            dataModel.setName(bean.getType());
                            dataModel.setCount(bean.getCount());
                            dataList.add(dataModel);
                        }
                    }
                }
            }
        }
        return dataList;
    }

   public class DataModel {
        private String name;
        private double count;
        private int position;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCount() {
            return count;
        }

        public void setCount(double count) {
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DataModel dataModel = (DataModel) o;

            return !(name != null ? !name.equals(dataModel.name) : dataModel.name != null);
        }
    }
}
