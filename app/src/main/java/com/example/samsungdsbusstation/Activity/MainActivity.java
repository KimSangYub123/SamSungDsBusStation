package com.example.samsungdsbusstation.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.samsungdsbusstation.Activity.GoWorkCheck;
import com.example.samsungdsbusstation.R;

/*
Class 명 : MainActivity
기능     : 처음 실행되는 화면
상속     : AppCompatActivity
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class MainActivity extends AppCompatActivity {

    /*
    함수명 : onCreate
    Para   : Bundel
    return : void
    기능   : 처음 앱 실행 시 호출되는 함수
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    /*
    함수명 : onClickGoGoWork
    Para   : View
    return : void
    기능   : "출근 버스 조회 Button Click 시 호출"
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public void onClickGoGoWork(View v){
        Toast.makeText(this,R.string.move,Toast.LENGTH_SHORT).show();

        // GoWorkCheck 신규 Activity 호출
        Intent intent = new Intent(this, GoWorkCheck.class);
        startActivity(intent);
    }




}
