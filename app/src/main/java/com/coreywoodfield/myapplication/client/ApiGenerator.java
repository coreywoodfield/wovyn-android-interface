package com.coreywoodfield.myapplication.client;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cwoodfie on 6/27/16
 */
public class ApiGenerator {

	public static final int TIMEOUT = new OkHttpClient().connectTimeoutMillis() / 1000;
	private static final String SKY_BASE = "http://192.168.1.132:8080/sky/cloud/AK1xiuXjAtmUZboCEcS8vr/";
	private static final String EVENT_BASE = "http://192.168.1.132:8080/sky/event/AK1xiuXjAtmUZboCEcS8vr/1337/";

	public static SkyQueryApi createSkyApi() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(SKY_BASE)
				.addConverterFactory(GsonConverterFactory.create())
				.client(createClient(new OkHttpClient.Builder(), TIMEOUT))
				.build();

		return retrofit.create(SkyQueryApi.class);
	}

	public static EventApi createEventApi() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(EVENT_BASE)
				.addConverterFactory(GsonConverterFactory.create())
				.client(createClient(new OkHttpClient.Builder(), TIMEOUT))
				.build();

		return retrofit.create(EventApi.class);
	}

	private static OkHttpClient createClient(OkHttpClient.Builder builder, int timeout) {
		builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
		builder.readTimeout(timeout, TimeUnit.SECONDS);
		builder.connectTimeout(timeout, TimeUnit.SECONDS);
		return builder.build();
	}
}
