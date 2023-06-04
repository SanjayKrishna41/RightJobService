package com.sywastech.rightjobservice.networking;

public class ApiUtils{
    private ApiUtils(){
    }

    //STAGE
    public static final String DOMAIN_NAME = "http://qa-api.top-doc.com/";

    public static APIService getAPIService(){
        return ApiClient.getClient(DOMAIN_NAME).create(APIService.class);
    }
}
