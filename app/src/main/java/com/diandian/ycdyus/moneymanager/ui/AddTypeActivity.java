package com.diandian.ycdyus.moneymanager.ui;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.NewRecordGridViewAdapter;
import com.diandian.ycdyus.moneymanager.model.NewRecordGridViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_add_type)
public class AddTypeActivity extends AppCompatActivity {
    @ViewById
    ImageView ivAddTypeBack;
    @ViewById
    TextView tvAddTypeOk;
    @ViewById
    EditText edAddType;
    @ViewById
    GridView viewgroupAddType;
    private List<NewRecordGridViewModel> list;
    private NewRecordGridViewAdapter adapter;
    private NewRecordGridViewModel model;
    private NewRecordGridViewModel currSelectedModel;

    @AfterViews
    public void init(){
        getSupportActionBar().hide();

        list = new ArrayList<>();

        addData();

        adapter = new NewRecordGridViewAdapter(this,list,R.layout.item_new_record_gridview);
        viewgroupAddType.setAdapter(adapter);
        viewgroupAddType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (NewRecordGridViewModel model : list) {
                    model.setSelected(false);
                }
                list.get(position).setSelected(true);
                adapter.notifyDataSetChanged();
                currSelectedModel = list.get(position);
            }
        });


        ivAddTypeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTypeActivity.this.finish();
            }
        });

        tvAddTypeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = edAddType.getText().toString();
                if( TextUtils.isEmpty(type) ){
                    Toast.makeText(AddTypeActivity.this, "请输入类别名称！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(currSelectedModel == null){
                    Toast.makeText(AddTypeActivity.this, "请选择图标！", Toast.LENGTH_SHORT).show();
                    return;
                }
                currSelectedModel.setDes(type);
                currSelectedModel.setSelected(false);
                EventBus.getDefault().post(currSelectedModel);
                AddTypeActivity.this.finish();
            }
        });
    }

    private void addData() {
        model = new NewRecordGridViewModel(R.mipmap.ic_apple);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_book);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_bowl);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_bus);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_capsule);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_cigarette);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_clothes);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_coconut_palm);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_coffeecup);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_cross);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_dinnerware);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_gift);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_handcart);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_home);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_laptop);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_high_heelshoe);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_laptop);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_muscle);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_taxi);
        list.add(model);
        model = new NewRecordGridViewModel(R.mipmap.ic_ticket);
        list.add(model);
    }
}
