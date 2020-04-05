package com.example.samsungdsbusstation.DataClass;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
/*
Class 명 : Line
기능     : 노선 정보를 저장하기 위한 Class
상속     : None
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class Line{
    // 노선안에 정류장 정보들을 저장할 객체
    private ArrayList<Station> stationList=new ArrayList<Station>();
    // 노선 시간
    private String startTime;
    // 노선 이름
    private String lineName;

    public Line(String _startTime,String _lineName){
        this.startTime=_startTime;
        this.lineName=_lineName;
    }

    /*
    함수명 : AddStation
    Para   : Station
    return : void
    기능   : 노선에 정류장 추가하기
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public void AddStation(Station _station){
        try{
            stationList.add(_station);
        }catch (Exception ex){
            Log.e("ERROR",ex.getMessage());
        }
    }
    /*
    함수명 : getStationsName
    Para   : Station
    return : void
    기능   : 노선에 정류장 추가하기
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public ArrayList<String> getStationsName(){
        ArrayList<String> ret = new ArrayList<>();
        Iterator iterator = this.stationList.iterator();
        while(iterator.hasNext()){
            ret.add(((Station)iterator.next()).getStationName());
        }
        return ret;
    }
    /*
    함수명 : ArrayList
    Para   : None
    return : ArrayList<Station>
    기능   : 해당 노선의 정류장 List 반환
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public ArrayList<Station> getStation(){
        return stationList;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getLineName(){
        return this.lineName;
    }
}