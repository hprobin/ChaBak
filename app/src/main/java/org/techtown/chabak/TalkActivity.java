package org.techtown.chabak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TalkActivity extends AppCompatActivity {
    ListView listView;
    TalkAdapter talkAdapter;
    String latitude;
    String longitude;
    ArrayList<TalkItem> talkItems= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        Intent intent = getIntent();
        latitude = Double.toString(intent.getExtras().getDouble("serversend_lat"));
        longitude = Double.toString(intent.getExtras().getDouble("serversend_long"));
        //데이터를 서버에서 읽어오는 함수입니다. 궁금한곳의 사진과 해당사진의 시간이 보이기 때문에 궁금한곳의 상황을 실시간으로 알 수 있게 됩니다.
       loadDB();

        listView = findViewById(R.id.listView);
        talkAdapter = new TalkAdapter(getLayoutInflater(),talkItems);
        listView.setAdapter(talkAdapter);
    }

    void loadDB(){
        //volley library로 사용
        new Thread(){
            @Override
            public void run(){
                //db서버에서 자신의 토큰에 해당되는 사진만 불러오게 됩니다.
                String serverUri="http://118.67.129.164/embeded/load1.php";
                //String token = FirebaseInstanceId.getInstance().getToken();
                //Double la = 36.1123553;
                //Double lo= 127.586589;
                try{
                    URL url = new URL(serverUri);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");//sd
                    connection.setDoInput(true);
                    connection.setDoOutput(true);//sd
                    connection.setUseCaches(false);
                    String query="latitude="+latitude + "&longitude="+longitude;
                    OutputStream os = connection.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(os);



                    writer.write(query, 0, query.length());
                    writer.flush();
                    writer.close();
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    String line=reader.readLine();
                    while(line!=null){
                        buffer.append(line+"\n");
                        line=reader.readLine();
                    }

                    String[] rows=buffer.toString().split(";");
                    talkItems.clear();
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            talkAdapter.notifyDataSetChanged();
                        }
                    });
                    for(String row:rows){
                        String[] datas=row.split("&");
                        if(datas.length!=2)continue;
                        int no = Integer.parseInt(datas[0]);
                        String imgPath="http://118.67.129.164/embeded/"+datas[1];
                        //String date=datas[2];

                        talkItems.add(new TalkItem(no,imgPath));

                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                talkAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }catch(MalformedURLException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();}
            }
        }.start();
    }
}
