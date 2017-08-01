package com.websarva.wings.android.asyncsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 『Androidアプリ開発の教科書』
 * 第11章
 * クラウド連携サンプル
 *
 * カクテルメモアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class WeatherInfoActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_info);

		//画面部品ListViewを取得
		ListView lvCityList = (ListView) findViewById(R.id.lvCityList);
		//SimpleAdapterで使用するListオブジェクトを用意。
		List<Map<String, String>> cityList = new ArrayList<Map<String,String>>();
		//都市データを格納するMapオブジェクトの用意とcityListへのデータ登録。
		Map<String, String> city = new HashMap<String, String>();
		city.put("name", "大阪");
		city.put("id", "270000");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "神戸");
		city.put("id", "280010");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "豊岡");
		city.put("id", "280020");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "京都");
		city.put("id", "260010");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "舞鶴");
		city.put("id", "260020");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "奈良");
		city.put("id", "290010");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "風屋");
		city.put("id", "290020");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "和歌山");
		city.put("id", "300010");
		cityList.add(city);
		city = new HashMap<String, String>();
		city.put("name", "潮岬");
		city.put("id", "300020");
		cityList.add(city);
		//SimpleAdapterで使用するfrom-to用変数の用意。
		String[] from = {"name"};
		int[] to = {android.R.id.text1};
		//SimpleAdapterを生成。
		SimpleAdapter adapter = new SimpleAdapter(WeatherInfoActivity.this, cityList, android.R.layout.simple_expandable_list_item_1, from, to);
		//ListViewにSimpleAdapterを設定。
		lvCityList.setAdapter(adapter);
		//ListViewにリスナを設定。
		lvCityList.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストが選択されたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//ListViewでタップされた行の都市名と都市IDを取得
			Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
			String cityName = item.get("name");
			String cityId = item.get("id");
			//取得した都市名をtvCityNameに設定。
			TextView tvCityName = (TextView) findViewById(R.id.tvCityName);
			tvCityName.setText(cityName + "の天気: ");
		}
	}

	/**
	 * 非同期でお天気データを取得するクラス。
	 */
	private class WeatherInfoReceiver extends AsyncTask<String, String, String> {
		/**
		 * 現在の天気を表示する画面部品フィールド。
		 */
		private TextView _tvWeatherTelop;
		/**
		 * 天気の詳細を表示する画面部品フィールド。
		 */
		private TextView _tvWeatherDesc;

		/**
		 * コンストラクタ。
		 * お天気情報を表示する画面部品をあらかじめ取得してフィールドに格納している。
		 *
		 * @param tvWeatherTelop 現在の天気を表示する画面部品。
		 * @param tvWeatherDesc 天気の詳細を表示する画面部品。
		 */
		public WeatherInfoReceiver(TextView tvWeatherTelop, TextView tvWeatherDesc) {
			//引数をそれぞれのフィールドに格納。
			_tvWeatherTelop = tvWeatherTelop;
			_tvWeatherDesc = tvWeatherDesc;
		}

		@Override
		public String doInBackground(String... params) {
			//可変長引数の1個目(インデックス0)を取得。これが都市ID
			String id = params[0];
			//都市IDを使って接続URL文字列を作成。
			String urlStr = "http://weather.livedoor.com/forecast/webservice/json/v1?city=" + id;
			//天気情報サービスから取得したJSON文字列。天気情報が格納されている。
			String result = "";

			//JSON文字列を返す。
			return result;
		}

		@Override
		public void onPostExecute(String result) {
			//天気情報用文字列変数を用意。
			String telop = "";
			String desc = "";

			//天気上表用文字列をTextViewにセット。
			_tvWeatherTelop.setText(telop);
			_tvWeatherDesc.setText(desc);
		}
	}
}
