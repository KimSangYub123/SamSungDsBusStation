package com.example.samsungdsbusstation.GoWorkDir;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.samsungdsbusstation.DataClass.GetGoWork;
import com.example.samsungdsbusstation.R;
import com.example.samsungdsbusstation.DataClass.Station;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
Class 명 : MpaFragmentGoWork
기능     : 가까운 정류장 찾기 List Item Click 시 호출되는 Fragment
상속     : Fragment , OnMapReadyCallback
작성자   : 김상엽 (2020.04.02)
수정내역 :
 */
public class MpaFragmentGoWork extends Fragment implements OnMapReadyCallback {
    private LocationRequest locationRequest;
    private Location location;
    private GoogleMap mMap;
    private Marker marker=null;
    private static final String TAG = "googlemap_example";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View layout = inflater.inflate(R.layout.fragment_a,container,false);

        SupportMapFragment mMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);
         FusedLocationProviderClient locClient = LocationServices.getFusedLocationProviderClient(getActivity());
         locationRequest = new LocationRequest()
                 .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                 .setInterval(1000)
                 .setFastestInterval(500);

         locClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());



        return layout;
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);

                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());
                  Toast.makeText(getActivity(),markerSnippet,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onLocationResult : " + markerSnippet);


                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location,"현재","내 위치");

            }


        }

    };

    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(getActivity(), "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(getActivity(), "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getActivity(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }

    private void setCurrentLocation(Location location,String title,String snippet){
        LatLng SEOUL = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("내위치");
        markerOptions.snippet("수도");
        if (marker!=null) {
            marker.remove();
        }
        marker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
    }


    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        GetGoWork temp = GetGoWork.getInstance();
        for (int i =0;i<temp.getLines().size();i++){
            ArrayList<Station> lst = temp.getLines().get(i).getStation();
            for (int stationIndex =0;stationIndex<lst.size();stationIndex++){
                String name = lst.get(stationIndex).getStationName();
                Double Lati = Double.parseDouble(lst.get(stationIndex).getLatitude());
                Double logi = Double.parseDouble(lst.get(stationIndex).getLongitude());

                LatLng SEOUL = new LatLng(Lati, logi);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(SEOUL);
                markerOptions.title("출발");
                markerOptions.snippet(name);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (marker.getTitle() != "내위치"){
                    EditText et = (EditText) getActivity().findViewById(R.id.goWorkDes);
                    et.setText(marker.getSnippet());
                }
                return false;
            }
        });

    }

}
