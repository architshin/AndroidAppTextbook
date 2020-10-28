package com.websarva.wings.android.lifecyclesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 『Androidアプリ開発の教科書』
 * 第7章
 * ライフサイクルサンプル
 *
 * 第1画面のアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("LifeCycleSample", "Main onCreate() called.");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onStart() {
		Log.i("LifeCycleSample", "Main onStart() called.");
		super.onStart();
	}

	@Override
	public void onRestart() {
		Log.i("LifeCycleSample", "Main onRestart() called.");
		super.onRestart();
	}

	@Override
	public void onResume() {
		Log.i("LifeCycleSample", "Main onResume() called.");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i("LifeCycleSample", "Main onPause() called.");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i("LifeCycleSample", "Main onStop() called.");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		Log.i("LifeCycleSample", "Main onDestory() called.");
		super.onDestroy();
	}

	/**
	 * ［次の画面を表示］ボタンがタップされた時の処理。
	 */
	public void onButtonClick(View view) {
		//インテントオブジェクトを用意。
		Intent intent = new Intent(MainActivity.this, SubActivity.class);
		//アクティビティを起動。
		startActivity(intent);
	}
}
