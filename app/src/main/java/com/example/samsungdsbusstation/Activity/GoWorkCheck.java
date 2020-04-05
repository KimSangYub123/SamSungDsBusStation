package com.example.samsungdsbusstation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.example.samsungdsbusstation.R;
import com.example.samsungdsbusstation.GoWorkDir.MpaFragmentGoWork;
import com.example.samsungdsbusstation.GoWorkDir.ListFragmentGoWork;
import java.util.ArrayList;

/*
Class 명 : GoWorkCheck
기능     : 출근 버스 조회를 관장하는 Activigy Class
상속     : AppCompatActivity
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class GoWorkCheck extends AppCompatActivity  {
    // 위치정보 조회 권한
    private int MY_REQUEST_PERMISSION =100;
    // 지도 Fragment 객체
    private MpaFragmentGoWork a = new MpaFragmentGoWork();
    // List Fragment 객체
    private ListFragmentGoWork b = new ListFragmentGoWork();
    // List Fragment에서 사용할 adapter
    private ArrayAdapter adapter;

    /*
    함수명 : onCreate
    Para   : Bundel
    return : void
    기능   : 현재 Activity 처음 실행 시 동작
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_work_check);

        // 도착 지 Spinner 항목 생성
        ArrayList<String> goWorkArrList = new ArrayList<>();
        goWorkArrList.add("기흥");
        goWorkArrList.add("H1");
        goWorkArrList.add("H2");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,goWorkArrList) ;
        Spinner spineer = (Spinner) findViewById(R.id.GoWork_spinner);
        spineer.setAdapter(adapter);

        // 출발 Button Click 시 동작될 기능 정의
        EditText et = (EditText) findViewById(R.id.goWorkDes);
        et.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // 입력하기전에
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // 입력되는 Text가 변할때
                adapter.getFilter().filter(s);
                ListView listview = (ListView) findViewById(R.id.GoWorkStationList) ;

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                // 입력이 끝났을때
            }
        });
    }

    /*
    함수명 : onClickForMap
    Para   : View
    return : void
    기능   : Map Button Click 시 호출
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public void onClickForMap(View v){
        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
        tran.replace(R.id.framLayout,a).commit();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION) ||
            ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},MY_REQUEST_PERMISSION);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},MY_REQUEST_PERMISSION);
            }
        }
    }

    /*
    함수명 : onClickGoWorkTextBox
    Para   : View
    return : void
    기능   : 출발 Button Click 시 생성될 Fragment 설정
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public void onClickGoWorkTextBox(View v){
        Log.d("Test","onClickTextBox");
        EditText et = (EditText) findViewById(R.id.goWorkDes);
        et.setText("");
        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();

        tran.replace(R.id.framLayout,b).commit();
    }

}

