package com.example.samsungdsbusstation.DataClass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/*
Class 명 : GetGoWork
기능     : 모든 출발 정보를 담고 있는 Instance 생성
상속     : None
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class GetGoWork {
    // 노선 정보를 담고 있는 객체
    private ArrayList<Line> lineList = new ArrayList<Line>();
    // GetGoWork Instance 객체
    private static GetGoWork instance =null;
    // 노선 별 시간을 저장하기 위한 객체
    private Map<String,ArrayList<String>> timeInLine = new Map<String, ArrayList<String>>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(@Nullable Object key) {
            return false;
        }

        @Override
        public boolean containsValue(@Nullable Object value) {
            return false;
        }

        @Nullable
        @Override
        public ArrayList<String> get(@Nullable Object key) {
            return null;
        }

        @Nullable
        @Override
        public ArrayList<String> put(String key, ArrayList<String> value) {
            return null;
        }

        @Nullable
        @Override
        public ArrayList<String> remove(@Nullable Object key) {
            return null;
        }

        @Override
        public void putAll(@NonNull Map<? extends String, ? extends ArrayList<String>> m) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public Set<String> keySet() {
            return null;
        }

        @NonNull
        @Override
        public Collection<ArrayList<String>> values() {
            return null;
        }

        @NonNull
        @Override
        public Set<Entry<String, ArrayList<String>>> entrySet() {
            return null;
        }
    };
    // 생성자 사용 못하도록 명시적 Private 선언
    private GetGoWork()
    {
    }

    /*
    함수명 : addLIne
    Para   : Line
    return : void
    기능   : 해당 객체에 노선 정보 추가하기
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public void addLine(Line line)
    {
        lineList.add(line);
        if (timeInLine.containsKey(line)){
            ArrayList<String> tempList = timeInLine.get(line.getStationsName());
        }
    }
    /*
    함수명 : GetGoWork
    Para   : None
    return : GetGoWork
    기능   : 해당 객체 Return
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public static GetGoWork getInstance()
    {
        if(instance == null)
        {
            synchronized (GetGoWork.class)
            {
                if(instance == null)
                {
                    instance = new GetGoWork();
                }
            }
        }
        return instance;
    }

    /*
    함수명 : getLines
    Para   : None
    return : ArrayList<Line>
    기능   : 해당 객체의 노선들 Return
    작성자   : 김상엽 (2020.04.02)
    수정내역 :
     */
    public ArrayList<Line> getLines(){
        return this.lineList;
    }

}
