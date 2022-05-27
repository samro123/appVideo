package com.thellog.laravelapiandroid.Service;

public class APIService {
    private static String base_url = "http://192.168.1.15:8000/api/v1/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
