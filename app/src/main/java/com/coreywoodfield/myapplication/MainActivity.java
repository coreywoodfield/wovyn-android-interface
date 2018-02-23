package com.coreywoodfield.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.coreywoodfield.myapplication.client.SkyQueryApi;
import com.coreywoodfield.myapplication.client.ApiGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SkyQueryApi skyQueryApi = ApiGenerator.createSkyApi();

		skyQueryApi.getTemperatures().enqueue(new Callback(R.id.button2) {
			@Override
			public void onResponse(Call<ArrayList<SkyQueryApi.TempReading>> call, Response<ArrayList<SkyQueryApi.TempReading>> response) {
				super.onResponse(call, response);
				ArrayList<SkyQueryApi.TempReading> body = response.body();
				((TextView) findViewById(R.id.current)).setText(String.valueOf(body.get(body.size() - 1).getTemperature()));
			}
		});
		skyQueryApi.getThresholdViolations().enqueue(new Callback(R.id.button3));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.profile:
				startActivity(new Intent(this, ProfileActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private class Callback implements retrofit2.Callback<ArrayList<SkyQueryApi.TempReading>> {
		int button;

		public Callback(int button) {
			this.button = button;
		}

		@Override
		public void onResponse(Call<ArrayList<SkyQueryApi.TempReading>> call, Response<ArrayList<SkyQueryApi.TempReading>> response) {
			findViewById(button).setOnClickListener(
					v -> startActivity(new Intent(MainActivity.this, ListActivity.class).putParcelableArrayListExtra("data", response.body())));
		}

		@Override
		public void onFailure(Call<ArrayList<SkyQueryApi.TempReading>> call, Throwable t) {

		}
	}
}
