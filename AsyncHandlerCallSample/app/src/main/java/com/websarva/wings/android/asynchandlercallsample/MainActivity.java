package com.websarva.wings.android.asynchandlercallsample;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 『Androidアプリ開発の教科書』
 * 第11章
 * Web API連携サンプル Handler+Callable版
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
	private static final String APP_ID = "xxxxxxxxxxx";
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
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), _list, android.R.layout.simple_list_item_1, from, to);
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
	 * 処理経過を表示するためにTextViewに引数のメッセージ文字列を追記するメソッド。
	 *
	 * @param msg メッセージ文字列。
	 */
	@UiThread
	private void addMsg(String msg) {
		// tvWeatherDescのTextViewを取得。
		TextView tvWeatherDesc = findViewById(R.id.tvWeatherDesc);
		// 現在表示されているメッセージを取得。
		String msgNow = tvWeatherDesc.getText().toString();
		// 現在表示されているメッセージが空でなければ、改行を追加。
		if(!msgNow.equals("")) {
			msgNow += "\n";
		}
		// 引数のメッセージを追加。
		msgNow += msg;
		// 追加されたメッセージをTextViewに表示。
		tvWeatherDesc.setText(msgNow);
	}

	/**
	 * お天気情報の取得処理を行うメソッド。
	 *
	 * @param urlFull お天気情報を取得するURL。
	 */
	@UiThread
	private void receiveWeatherInfo(final String urlFull) {
		// 天気情報表示TextView内の表示文字列をクリア。
		TextView tvWeatherTelop = findViewById(R.id.tvWeatherTelop);
		tvWeatherTelop.setText("");
		TextView tvWeatherDesc = findViewById(R.id.tvWeatherDesc);
		tvWeatherDesc.setText("");

		Looper mainLooper = Looper.getMainLooper();
		Handler handler = HandlerCompat.createAsync(mainLooper);
		WeatherInfoBackgroundReceiver backgroundReceiver = new WeatherInfoBackgroundReceiver(handler, urlFull);
		ExecutorService executorService  = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(backgroundReceiver);
		String result = "";
		try {
			result = future.get();
		}
		catch(ExecutionException ex) {
			Log.w(DEBUG_TAG, "非同期処理結果の取得で例外発生: ", ex);
		}
		catch(InterruptedException ex) {
			Log.w(DEBUG_TAG, "非同期処理結果の取得で例外発生: ", ex);
		}
		showWeatherInfo(result);
	}

	/**
	 * 取得したお天気情報JSON文字列を解析の上、画面に表示させるメソッド。
	 *
	 * @param result 取得したお天気情報JSON文字列。
	 */
	@UiThread
	private void showWeatherInfo(String result) {
		// 都市名。
		String cityName = "";
		// 天気。
		String weather = "";
		// 緯度
		String latitude = "";
		// 経度。
		String longitude = "";
		try {
			// ルートJSONオブジェクトを生成。
			JSONObject rootJSON = new JSONObject(result);
			// 都市名文字列を取得。
			cityName = rootJSON.getString("name");
			// 緯度経度情報JSONオブジェクトを取得。
			JSONObject coordJSON = rootJSON.getJSONObject("coord");
			// 緯度情報文字列を取得。
			latitude = coordJSON.getString("lat");
			// 経度情報文字列を取得。
			longitude = coordJSON.getString("lon");
			// 天気情報JSON配列オブジェクトを取得。
			JSONArray weatherJSONArray = rootJSON.getJSONArray("weather");
			// 現在の天気情報JSONオブジェクトを取得。
			JSONObject weatherJSON = weatherJSONArray.getJSONObject(0);
			// 現在の天気情報文字列を取得。
			weather = weatherJSON.getString("description");
		}
		catch(JSONException ex) {
			Log.e(DEBUG_TAG, "JSON解析失敗", ex);
		}

		// 画面に表示する「〇〇の天気」文字列を生成。
		String telop = cityName + "の天気";
		// 天気の詳細情報を表示する文字列を生成。
		String desc = "現在は" + weather + "です。\n緯度は" + latitude + "度で経度は" + longitude + "度です。";
		// 天気情報を表示するTextViewを取得。
		TextView tvWeatherTelop = findViewById(R.id.tvWeatherTelop);
		TextView tvWeatherDesc = findViewById(R.id.tvWeatherDesc);
		// 天気情報を表示。
		tvWeatherTelop.setText(telop);
		tvWeatherDesc.setText(desc);
	}

	/**
	 * 非同期でお天気情報APIにアクセスするためのクラス。
	 */
	private class WeatherInfoBackgroundReceiver implements Callable<String> {
		/**
		 * UIスレッドを表すハンドラオブジェクト。
		 */
		private final Handler _handler;
		/**
		 * お天気情報を取得するURL。
		 */
		private final String _urlFull;

		/**
		 * コンストラクタ。
		 * 非同期でお天気情報Web APIにアクセスするのに必要な情報を取得する。
		 *
		 * @param handler UIスレッドを表すハンドラオブジェクト。
		 * @param urlFull お天気情報を取得するURL。
		 */
		public WeatherInfoBackgroundReceiver(Handler handler, String urlFull) {
			_handler = handler;
			_urlFull = urlFull;
		}

		@WorkerThread
		@Override
		public String call() {
			// 途中経過表示。
			ProgressUpdateExecutor progressUpdate = new ProgressUpdateExecutor("バックグランド処理開始。");
			_handler.post(progressUpdate);

			// 天気情報サービスから取得したJSON文字列。天気情報が格納されている。
			String result = "";
			// HTTP接続を行うHttpURLConnectionオブジェクトを宣言。finallyで解放するためにtry外で宣言。
			HttpURLConnection con = null;
			// HTTP接続のレスポンスデータとして取得するInputStreamオブジェクトを宣言。同じくtry外で宣言。
			InputStream is = null;
			try {
				// 途中経過表示。
				progressUpdate = new ProgressUpdateExecutor("Webアクセス開始。");
				_handler.post(progressUpdate);

				// URLオブジェクトを生成。
				URL url = new URL(_urlFull);
				// URLオブジェクトからHttpURLConnectionオブジェクトを取得。
				con = (HttpURLConnection) url.openConnection();
				// 接続に使ってもよい時間を設定。
				con.setConnectTimeout(1000);
				// データ取得に使ってもよい時間。
				con.setReadTimeout(1000);
				// HTTP接続メソッドをGETに設定。
				con.setRequestMethod("GET");
				// 接続。
				con.connect();
				// HttpURLConnectionオブジェクトからレスポンスデータを取得。
				is = con.getInputStream();
				// レスポンスデータであるInputStreamオブジェクトを文字列に変換。
				result = is2String(is);

				// 途中経過表示。
				progressUpdate = new ProgressUpdateExecutor("Webアクセス終了。");
				_handler.post(progressUpdate);
			}
			catch(MalformedURLException ex) {
				Log.e(DEBUG_TAG, "URL変換失敗", ex);
			}
			// タイムアウトの場合の例外処理。
			catch(SocketTimeoutException ex) {
				Log.w(DEBUG_TAG, "通信タイムアウト", ex);
			}
			catch(IOException ex) {
				Log.e(DEBUG_TAG, "通信失敗", ex);
			}
			finally {
				// HttpURLConnectionオブジェクトがnullでないなら解放。
				if(con != null) {
					con.disconnect();
				}
				// InputStreamオブジェクトがnullでないなら解放。
				if(is != null) {
					try {
						is.close();
					}
					catch(IOException ex) {
						Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
					}
				}
			}

			// 途中経過表示。
			progressUpdate = new ProgressUpdateExecutor("バックグランド処理終了。");
			_handler.post(progressUpdate);
			return result;
		}

		/**
		 * InputStreamオブジェクトを文字列に変換するメソッド。 変換文字コードはUTF-8。
		 *
		 * @param is 変換対象のInputStreamオブジェクト。
		 * @return 変換された文字列。
		 * @throws IOException 変換に失敗した時に発生。
		 */
		private String is2String(InputStream is) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			StringBuffer sb = new StringBuffer();
			char[] b = new char[1024];
			int line;
			while(0 <= (line = reader.read(b))) {
				sb.append(b, 0, line);
			}
			return sb.toString();
		}
	}

	/**
	 * バックグラウンドスレッドの途中経過メッセージをUIスレッドで表示する処理用クラス。
	 */
	private class ProgressUpdateExecutor implements Runnable {
		/**
		 * 追加メッセージを表す文字列。
		 */
		private String _msg;

		/**
		 * コンストラクタ。
		 *
		 * @param msg 追加メッセージを表す文字列。
		 */
		public ProgressUpdateExecutor(String msg) {
			_msg = msg;
		}

		@UiThread
		@Override
		public void run() {
			addMsg(_msg);
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
