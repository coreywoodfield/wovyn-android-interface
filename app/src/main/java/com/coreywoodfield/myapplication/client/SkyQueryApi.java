package com.coreywoodfield.myapplication.client;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by coreywoodfield on 2/22/18.
 */
public interface SkyQueryApi {
	@GET("temperature_store/temperatures")
	Call<ArrayList<TempReading>> getTemperatures();

	@GET("temperature_store/inrange_temperatures")
	Call<ArrayList<TempReading>> getInrangeTemperatures();

	@GET("temperature_store/threshold_violations")
	Call<ArrayList<TempReading>> getThresholdViolations();

	@GET("sensor_profile/query")
	Call<Profile> getProfile();

	public static class TempReading implements Parcelable {

		/**
		 * temperature : 75.31
		 * timestamp : 2018-02-23T01:42:22.860Z
		 */

		private double temperature;
		private String timestamp;

		protected TempReading(Parcel in) {
			temperature = in.readDouble();
			timestamp = in.readString();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeDouble(temperature);
			dest.writeString(timestamp);
		}

		@Override
		public int describeContents() {
			return 0;
		}

		public static final Creator<TempReading> CREATOR = new Creator<TempReading>() {
			@Override
			public TempReading createFromParcel(Parcel in) {
				return new TempReading(in);
			}

			@Override
			public TempReading[] newArray(int size) {
				return new TempReading[size];
			}
		};

		public double getTemperature() { return temperature;}

		public void setTemperature(double temperature) { this.temperature = temperature;}

		public String getTimestamp() { return timestamp;}

		public void setTimestamp(String timestamp) { this.timestamp = timestamp;}
	}

	public static class Profile implements Parcelable {
		/**
		 * threshold : 73.0
		 * number : 801-whatever
		 * location : Provo
		 * name : wovyn
		 */

		private double threshold;
		private String number;
		private String location;
		private String name;

		public Profile(double threshold, String number, String location, String name) {
			this.threshold = threshold;
			this.number = number;
			this.location = location;
			this.name = name;
		}

		protected Profile(Parcel in) {
			threshold = in.readDouble();
			number = in.readString();
			location = in.readString();
			name = in.readString();
		}

		public static final Creator<Profile> CREATOR = new Creator<Profile>() {
			@Override
			public Profile createFromParcel(Parcel in) {
				return new Profile(in);
			}

			@Override
			public Profile[] newArray(int size) {
				return new Profile[size];
			}
		};

		public double getThreshold() { return threshold;}

		public void setThreshold(double threshold) { this.threshold = threshold;}

		public String getNumber() { return number;}

		public void setNumber(String number) { this.number = number;}

		public String getLocation() { return location;}

		public void setLocation(String location) { this.location = location;}

		public String getName() { return name;}

		public void setName(String name) { this.name = name;}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeDouble(threshold);
			dest.writeString(number);
			dest.writeString(location);
			dest.writeString(name);
		}
	}
}
