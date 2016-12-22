package com.administrator.myapplication.myweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.myapplication.R;
import com.administrator.myapplication.bean.City;

import java.util.Iterator;
import java.util.List;

public class SelectCity extends AppCompatActivity implements View.OnClickListener {


    private MyApplication myApplication;
    private ImageView mBackBtn;
    private ListView mlistView;
    private TextView mtextView;
    private String Number;
    private String[] CityName;
    private String[] CityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mlistView = (ListView) findViewById(R.id.list_view);
        mtextView = (TextView) findViewById(R.id.title_name);
        myApplication = (MyApplication) getApplication();
        List<City> list = myApplication.getCityList();
        CityName = new String[list.size()];
        CityCode = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            City city = list.get(i);
            CityName[i] = city.getCity();
            CityCode[i] = city.getNumber();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SelectCity.this, android.R.layout.simple_list_item_1, CityName);
        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SelectCity.this, "你单击了:" + CityName[i], Toast.LENGTH_SHORT).show();
                Number = CityCode[i];
                mtextView.setText("选择城市： " + CityName[i]);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                // 生成一个Intent对象
                Intent intent = new Intent();
                intent.putExtra("Number", Number);   // 传递数据
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }


}
