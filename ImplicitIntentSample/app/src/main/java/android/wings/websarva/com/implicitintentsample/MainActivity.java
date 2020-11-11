package android.wings.websarva.com.implicitintentsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 『Androidアプリ開発の教科書』
 * 第14章
 * 暗黙的インテントサンプル
 *
 * アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
	/**
	 * 緯度フィールド。
	 */
	private double _latitude = 0;
	/**
	 * 経度フィールド
	 */
	private double _longitude = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 地図検索ボタンがタップされたときの処理メソッド。
	 */
	public void onMapSearchButtonClick(View view) {
		//入力欄に入力されたキーワード文字列を取得。
		EditText etSearchWord = findViewById(R.id.etSearchWord);
		String searchWord = etSearchWord.getText().toString();

		try {
			//入力されたキーワードをURLエンコード。
			searchWord = URLEncoder.encode(searchWord, "UTF-8");
			//マップアプリと連携するURI文字列を生成。
			String uriStr = "geo:0,0?q=" + searchWord;
			//URI文字列からURIオブジェクトを生成。
			Uri uri = Uri.parse(uriStr);
			//Intentオブジェクトを生成。
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			//アクティビティを起動。
			startActivity(intent);
		}
		catch(UnsupportedEncodingException ex) {
			Log.e("MainActivity", "検索キーワード変換失敗", ex);
		}
	}

	/**
	 * 現在地の地図表示ボタンがタップされたときの処理メソッド。
	 */
	public void onMapShowCurrentButtonClick(View view) {
		//フィールドの緯度と経度の値をもとにマップアプリと連携するURI文字列を生成。
		String uriStr = "geo:" + _latitude + "," + _longitude;
		//URI文字列からURIオブジェクトを生成。
		Uri uri = Uri.parse(uriStr);
		//Intentオブジェクトを生成。
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		//アクティビティを起動。
		startActivity(intent);
	}
}
