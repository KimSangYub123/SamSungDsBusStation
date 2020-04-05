package com.example.samsungdsbusstation.DataClass;

/*
Class 명 : Station
기능     : 정류장 정보 저장 Class
상속     : None
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class Station {
    private String stationName;         // 정류장 이름
    private String latitude;            // 정류장 위도
    private String longitude;           // 정류장 경도

    // 생성자
    public Station(String _stationName,String _latitude,String _longitude){
        this.stationName =_stationName;
        this.latitude =_latitude;
        this.longitude=_longitude;
    }
    // 정류장 이름 반환
    public String getStationName(){
        return this.stationName;
    }
    // 정류장 위도 반환
    public String getLatitude(){
        return this.latitude;
    }
    // 정류장 경도 반환
    public String getLongitude(){
        return this.longitude;
    }
}

