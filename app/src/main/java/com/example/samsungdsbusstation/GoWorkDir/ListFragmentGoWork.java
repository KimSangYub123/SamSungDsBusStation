package com.example.samsungdsbusstation.GoWorkDir;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.samsungdsbusstation.DataClass.Line;
import com.example.samsungdsbusstation.R;
import com.example.samsungdsbusstation.DataClass.Station;
import com.example.samsungdsbusstation.DataClass.GetGoWork;
import java.util.ArrayList;

/*
Class 명 : ListFragmentGoWork
기능     : 출발 textBox Click 시 호출되는 List Fragment
상속     : Fragment
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class ListFragmentGoWork extends Fragment {

    // 출발지 정보를 저장할 객체
    private GetGoWork list = GetGoWork.getInstance();

    //-----------------------------------  테스트를 위한 하드 코딩 ----------------------------------------//
    Station station = new Station("이지더원","37.2088668","127.108737");
    Station station2 = new Station("H2","37.217378","127.0705476");
    Line line = new Line("10:00","동탄2번");
    //-----------------------------------  테스트를 위한 하드 코딩 ----------------------------------------//

    // 가까운 정류장 찾기 Button Click 시 나타낼 지도 Fragment 객체
    private MpaFragmentGoWork a = new MpaFragmentGoWork();

    private int MY_REQUEST_PERMISSION =100;

    /*
    함수명 : onCreateView
    Para   : LayoutInflater, ViewGroup, Bundle
    return : View
    기능   : List Fragment 실행 시 처음 호출되는 함수
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_b,container,false);

        // 정류장 정보 저장

        final ArrayList<String> lst = line.getStationsName();
        lst.add(0,"가까운 정류장 검색");

        // List 생성
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item,lst) ;
        ListView listview = (ListView) layout.findViewById(R.id.GoWorkStationList) ;
        listview.setAdapter(adapter) ;

        // List Item Click 시 호출되는 함수 정의
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 가까운 정류장 검색 Click 시 호출
                if (position == 0)
                {
                    EditText et = (EditText) getActivity().findViewById(R.id.goWorkDes);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et.getWindowToken(), 0);


                    // Fragment Change For Map
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.framLayout, a);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    // map을 위한 Permission 요청
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    {
                        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) ||
                                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION))
                        {
                            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},MY_REQUEST_PERMISSION);
                        }
                        else
                        {
                            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},MY_REQUEST_PERMISSION);
                        }
                    }

                }else{
                    Log.d("선택된 아이템",lst.get(position));

                    // 선택된 아이템을 Text 박스에 넣기
                    EditText et = (EditText) getActivity().findViewById(R.id.goWorkDes);
                    et.setText(lst.get(position));
                }
            }
        });
        return layout;
    }

    /*
    함수명 : ListFragmentGoWork
    Para   : None
    return : 생성자
    기능   : 정류장 정보 데이터화 진행
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */

    public ListFragmentGoWork()
    {
        line.AddStation(station);
        line.AddStation(station2);
        list.addLine(line);
    }
}
