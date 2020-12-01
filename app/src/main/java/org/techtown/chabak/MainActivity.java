package org.techtown.chabak;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static String IP_ADDRESS ="118.67.129.164";
    private static String TAG ="php";
    private ArrayList<AttractionData> mArrayList;
    private String mJsonString;

    //public static double[] la = {36.1123553,37.5146143,34.5283651,37.58839,37.7304486,37.4324716,36.2782276,37.6442705,35.0180651,36.8993337,35.1919657,35.1073405,34.3685481,36.2591721,37.3877587,36.0463175,37.4703415,36.3242512,37.7034643,37.0695502};
    //public static double[] lo = {127.586589,127.998454,127.123507,126.522441,127.567087,126.417772,127.342052,126.343365,126.854553,127.92256,127.377534,126.609115,126.927839,127.641961,127.546423,128.99207,128.843965,127.672127,127.605357,128.031064};
    //public static String[] name = {"기러기 공원","내지리 섬강 노지","녹동 소록대교 밑","동검도 선착장 앞","마곡유원지","마시안 해변","상보안 유원지","석모도 어류정항","솔밭유원지","수주팔봉 강변","압록유원지","앵두공원","약산 가사동백숲 방파제","옥천 야영장 부근 금강변","이포보 오토캠핑장 인근 강변","임고 강변공원","정선 미락숲","지수리 금강변","홍천강변","삼탄유원지 다리밑"};
    /*la[0] = 36.1123553;lo[0]=127.586589;
    name[0] = "기러기 공원";
    la[1] = 37.5146143;lo[1]=127.998454;
    name[1] = "내지리 섬강 노지";
    la[2] = 34.5283651;lo[2]=127.123507;
    name[2] = "녹동 소록대교 밑";
    la[3] = 37.58839;lo[3]= 126.522441;
    name[3] = "동검도 선착장 앞";
    la[4] = 37.7304486;lo[4]= 127.567087;
    name[4] = "마곡유원지";
    la[5] = 37.4324716;lo[5]= 126.417772;
    name[5] = "마시안 해변";
    la[6] = 36.2782276;lo[6]= 127.342052;
    name[6] = "상보안 유원지";
    la[7] = 37.6442705;lo[7]= 126.343365;
    name[7] = "석모도 어류정항";
    la[8] = 35.0180651;lo[8]= 126.854553;
    name[8] = "솔밭유원지";
    la[9] = 36.8993337;lo[9]= 127.92256;
    name[9] = "수주팔봉 강변";
    la[10] = 35.1919657;lo[10]= 127.377534;
    name[10] = "압록유원지";
    la[11] = 35.1073405;lo[11]= 126.609115;
    name[11] = "앵두공원";
    la[12] = 34.3685481;lo[12]= 126.927839;
    name[12] = "약산 가사동백숲 방파제";
    la[13] = 36.2591721;lo[13]= 127.641961;
    name[13] = "옥천 야영장 부근 금강변";
    la[14] = 37.3877587;lo[14]= 127.546423;
    name[14] = "이포보 오토캠핑장 인근 강변";
    la[15] = 36.0463175;lo[15]= 128.99207;
    name[15] = "임고 강변공원";
    la[16] = 37.4703415;lo[16]= 128.843965;
    name[16] = "정선 미락숲";
    la[17] = 36.3242512;lo[17]= 127.672127;
    name[17] = "지수리 금강변";
    la[18] = 37.7034643;lo[18]= 127.605357;
    name[18] = "홍천강변";
    la[19] = 37.0695502;lo[19]= 128.031064;
    name[19] = "삼탄유원지 다리밑";

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        /*
        mArrayList = new ArrayList<>();
        mArrayList.clear();
        GetData task = new GetData();
        task.execute("http://"+IP_ADDRESS+"/embeded/load.php","");

         */



    }
    /*
    private class GetData extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = progressDialog.show(MainActivity.this,"Please Wait",null,true,true);
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            progressDialog.dismiss();
            if(result==null){
                Log.d(TAG,"error"+errorString);
            }
            else{
                mJsonString = result;
                showResult();
            }
        }
        @Override
        protected String doInBackground(String... params){
            String serverURL = params[0];
            String postParameters = params[1];
            try{
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG,"response code - "+responseStatusCode);
                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader =new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while((line =bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString().trim();
            }catch(Exception e){
                Log.d(TAG,"Get Daata : error",e);
                errorString = e.toString();
                return null;
            }
        }
    }
    private void showResult(){
        String TAG_JSON="chabak";
        String TAG_ATT="attraction";
        String TAG_LAT="latitude";
        String TAG_LON="longitude";

        try{
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String attraction = item.getString(TAG_ATT);
                String latitude = item.getString(TAG_LAT);
                String longitude = item.getString(TAG_LON);

                AttractionData attractionData = new AttractionData();
                attractionData.setAttraction_name(attraction);
                attractionData.setAttraction_latitude(latitude);
                attractionData.setAttraction_longitude(longitude);
                mArrayList.add(attractionData);

            }
        }catch (JSONException e){
            Log.d(TAG,"showResult : ",e);
        }
    }

     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        /*
        String attraction1 = mArrayList.get(1).getAttraction_name();
        String latitude1 =mArrayList.get(1).getLatitude();
        String longitude1 = mArrayList.get(1).getLongitude();
        */
        double[] la = {36.1123553,37.5146143,34.5283651,37.58839,37.7304486,37.4324716,36.2782276,37.6442705,35.0180651,36.8993337,35.1919657,35.1073405,34.3685481,36.2591721,37.3877587,36.0463175,37.4703415,36.3242512,37.7034643,37.0695502};
        double[] lo = {127.586589,127.998454,127.123507,126.522441,127.567087,126.417772,127.342052,126.343365,126.854553,127.92256,127.377534,126.609115,126.927839,127.641961,127.546423,128.99207,128.843965,127.672127,127.605357,128.031064};
        String[] name = {"기러기 공원","내지리 섬강 노지","녹동 소록대교 밑","동검도 선착장 앞","마곡유원지","마시안 해변","상보안 유원지","석모도 어류정항","솔밭유원지","수주팔봉 강변","압록유원지","앵두공원","약산 가사동백숲 방파제","옥천 야영장 부근 금강변","이포보 오토캠핑장 인근 강변","임고 강변공원","정선 미락숲","지수리 금강변","홍천강변","삼탄유원지 다리밑"};
        mMap = googleMap;
        //서울의 위도 경도
        LatLng SEOUL = new LatLng(37.56, 126.97);
        LatLng GwangJu =new LatLng(35.180019,126.875832);
        LatLng start= new LatLng(36.558132, 127.905404);

        MarkerOptions[] marker = new MarkerOptions[20];
        for(int i=0;i<20;i++)
        {
            marker[i] = new MarkerOptions();
            marker[i].position(new LatLng(la[i],lo[i]));
            marker[i].title(name[i]);
            marker[i].snippet("1");
            mMap.addMarker(marker[i]);
        }
        /*
        MarkerOptions marker1 = new MarkerOptions();
        marker1.position(new LatLng(la[0],lo[0]));
        marker1.title(name[0]);
        mMap.addMarker(marker1);*/

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