package com.websarva.wings.android.servicesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 『Androidアプリ開発の教科書』
 * 第13章
 * サービスサンプル
 *
 * アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class SoundStartActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_start);
	}

	/**
	 * 再生ボタンタップ時の処理メソッド。
	 *
	 * @param view 画面部品
	 */
	public void onPlayButtonClick(View view) {
		//インテントオブジェクトを生成。
		Intent intent = new Intent(SoundStartActivity.this, SoundManageService.class);
		//サービスを起動。
		startService(intent);
		//再生ボタンをタップ不可に、停止ボタンをタップ可に変更。
		Button btPlay = (Button) findViewById(R.id.btPlay);
		Button btStop = (Button) findViewById(R.id.btStop);
		btPlay.setEnabled(false);
		btStop.setEnabled(true);
	}

	/**
	 * 停止ボタンタップ時の処理メソッド。
	 *
	 * @param view 画面部品
	 */
	public void onStopButtonClick(View view) {
		//インテントオブジェクトを生成。
		Intent intent = new Intent(SoundStartActivity.this, SoundManageService.class);
		//サービスを停止。
		stopService(intent);
		//再生ボタンをタップ可に、停止ボタンをタップ不可に変更。
		Button btPlay = (Button) findViewById(R.id.btPlay);
		Button btStop = (Button) findViewById(R.id.btStop);
		btPlay.setEnabled(true);
		btStop.setEnabled(false);
	}
}
