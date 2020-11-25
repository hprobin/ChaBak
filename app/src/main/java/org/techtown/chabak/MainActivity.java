package org.techtown.chabak;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        //서울의 위도 경도
        LatLng SEOUL = new LatLng(37.56, 126.97);
        LatLng GwangJu =new LatLng(35.180019,126.875832);
        LatLng start= new LatLng(36.558132, 127.905404);
        MarkerOptions markerOptions1= new MarkerOptions();
        markerOptions1.position(GwangJu);
        markerOptions1.title("광주");
        markerOptions1.snippet("우리집이다 이색갸~");
        mMap.addMarker(markerOptions1);
        //마커 옵션
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);

        //줌 인 1,5,10 ,20 단위였던듯
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 7));

        //줌 버튼 생성
        UiSettings mapUiSettings = mMap.getUiSettings ();
        mapUiSettings.setZoomControlsEnabled (true);
        //나침반 생성 안돼 왜지? 괜찮아 필요없어
        mapUiSettings.setCompassEnabled(true);


    }

}