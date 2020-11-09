package com.websarva.wings.android.asyncsample;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 『Androidアプリ開発の教科書』
 * 第11章
 * Web API連携サンプル
 *
 * アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
	/**
	 * ログに記載するタグ用の文字列。
	 */
	private static final String DEBUG_TAG = "AsyncSample";
	/**
	 * お天気情報のURL。
	 */
	private static final String WEATHERINFO_URL = "https://api.openweathermap.org/data/2.5/weather?lang=ja";
	/**
	 * お天気APIにアクセスすするためのAPIキー。
	 * ※※※※※この値は各自のものに書き換える!!※※※※※
	 */
	private static final String APP_ID = "913136635cfa3182bbe18e34ffd44849";
	/**
	 * リストビューに表示させるリストデータ。
	 */
	private List<Map<String, String>> _list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_list  = createList();

		ListView lvCityList = findViewById(R.id.lvCityList);
		String[] from = {"name"};
		int[] to = {android.R.id.text1};
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), _list, android.R.layout.simple_expandable_list_item_1, from, to);
		lvCityList.setAdapter(adapter);
		lvCityList.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストビューに表示させる天気ポイントリストデータを生成するメソッド。
	 *
	 * @return 生成された天気ポイントリストデータ。
	 */
	private List<Map<String, String>> createList() {
		List<Map<String, String>> list = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put("name", "大阪");
		map.put("q", "Osaka");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "神戸");
		map.put("q", "Kobe");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "京都");
		map.put("q", "Kyoto");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "大津");
		map.put("q", "Otsu");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "奈良");
		map.put("q", "Nara");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "和歌山");
		map.put("q", "Wakayama");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "姫路");
		map.put("q", "Himeji");
		list.add(map);

		return list;
	}

	/**
	 * お天気情報の取得処理を行うメソッド。
	 *
	 * @param urlFull お天気情報を取得するURL。
	 */
	public void receiveWeatherInfo(final String urlFull) {
		WeatherInfoBackgroundReceiver backgroundReceiver = new WeatherInfoBackgroundReceiver();
		ExecutorService executorService  = Executors.newSingleThreadExecutor();
		executorService.submit(backgroundReceiver);
	}

	/**
	 * 非同期でお天気情報APIにアクセスするためのクラス。
	 */
	private class WeatherInfoBackgroundReceiver implements Runnable {
		@Override
		public void run() {
		}
	}

	/**
	 * リストがタップされた時の処理が記述されたリスナクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Map<String, String> item = _list.get(position);
			String q = item.get("q");
			String urlFull = WEATHERINFO_URL + "&q=" + q + "&appid=" + APP_ID;

			receiveWeatherInfo(urlFull);
		}
	}
}
