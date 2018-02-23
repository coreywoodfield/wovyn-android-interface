package com.coreywoodfield.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.coreywoodfield.myapplication.client.ApiGenerator;
import com.coreywoodfield.myapplication.client.SkyQueryApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by coreywoodfield on 2/22/18.
 */

public class ProfileActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		EditText threshold = findViewById(R.id.threshold);
		EditText number = findViewById(R.id.number);
		EditText location = findViewById(R.id.location);
		EditText name = findViewById(R.id.name);
		SkyQueryApi skyQueryApi = ApiGenerator.createSkyApi();
		ProgressDialog dialog = ProgressDialog.show(this, "Loading", "loading", true, false);
		skyQueryApi.getProfile().enqueue(new Callback<SkyQueryApi.Profile>() {
			@Override
			public void onResponse(Call<SkyQueryApi.Profile> call, Response<SkyQueryApi.Profile> response) {
				dialog.dismiss();
				SkyQueryApi.Profile body = response.body();
				threshold.setText(String.valueOf(body.getThreshold()));
				number.setText(body.getNumber());
				location.setText(body.getLocation());
				name.setText(body.getName());
				findViewById(R.id.button).setOnClickListener(v -> {
					ApiGenerator.createEventApi().updateProfile(
							new SkyQueryApi.Profile(Double.parseDouble(threshold.getText().toString()),
													number.getText().toString(),
													location.getText().toString(),
													name.getText().toString())).enqueue(new Callback() {
						@Override
						public void onResponse(Call call, Response response) {

						}

						@Override
						public void onFailure(Call call, Throwable t) {

						}
					});
				});
			}

			@Override
			public void onFailure(Call<SkyQueryApi.Profile> call, Throwable t) {

			}
		});
	}
}
