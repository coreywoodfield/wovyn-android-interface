package com.coreywoodfield.myapplication.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by coreywoodfield on 2/22/18.
 */

public interface EventApi {
	@POST("sensor/profile_updated")
	Call<ResponseBody> updateProfile(@Body SkyQueryApi.Profile profile);
}
