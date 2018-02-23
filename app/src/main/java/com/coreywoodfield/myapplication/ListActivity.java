package com.coreywoodfield.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coreywoodfield.myapplication.client.SkyQueryApi;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by coreywoodfield on 2/22/18.
 */

public class ListActivity extends Activity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		final List<SkyQueryApi.TempReading> data = getIntent().getParcelableArrayListExtra("data");

		RecyclerView recyclerView = findViewById(R.id.recycler);
		recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
			@Override
			public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
				return new ViewHolder(ListActivity.this.getLayoutInflater().inflate(R.layout.list_item, parent, false));
			}

			@Override
			public void onBindViewHolder(ViewHolder holder, int position) {
				SkyQueryApi.TempReading temp = data.get(position);
				holder.itemView.<TextView>findViewById(R.id.textView).setText(String.valueOf(temp.getTemperature()));
				holder.itemView.<TextView>findViewById(R.id.detailText).setText(temp.getTimestamp());
			}

			@Override
			public int getItemCount() {
				return data.size();
			}
		});
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(View itemView) {
			super(itemView);
		}
	}
}
