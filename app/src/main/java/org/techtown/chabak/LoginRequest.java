package org.techtown.chabak;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //LoginActivity에서 전달된 변수들을 서버로 전달합니다.
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://118.67.129.164/embeded/login.php";
    private Map<String, String>parameters;

    public LoginRequest(String u_id, String u_pw, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("u_id", u_id);
        parameters.put("u_pw", u_pw);
        //parameters.put("longtitude", longtitude);
        //parameters.put("latitude", latitude);
        //parameters.put("address",address);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return parameters;
    }
}