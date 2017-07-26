package com.websarva.wings.android.databasesample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * 『Androidアプリ開発の教科書』
 * 第10章
 * データベースサンプル
 *
 * カクテルメモアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class CocktailMemoActivity extends AppCompatActivity {
	/**
	 * リスト画面でタップされた主キーとなるポジションを保持するフィールド。
	 */
	private int _id = 0;

	/**
	 * リスト画面でタップされたカクテル名を保持するフィールド。
	 */
	private String _cocktailName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cocktail_memo);

		//インテントオブジェクトを取得。
		Intent intent = getIntent();
		//引き継ぎデータとして主キーとなるポジションとカクテル名を取得し、フィールドに格納。
		_id = intent.getIntExtra("id", 0);
		_cocktailName = intent.getStringExtra("cocktailName");

		//カクテル名を表示するTextViewを取得し、カクテル名を設定。
		TextView tvCacktailName = (TextView) findViewById(R.id.tvCacktailName);
		tvCacktailName.setText(_cocktailName);

		//アクションバーに［戻る］メニューを設定。
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//メニューインフレーターの取得。
		MenuInflater inflater = getMenuInflater();
		//オプションメニュー用xmlファイルをインフレイト。
		inflater.inflate(R.menu.menu_options_cocktail_memo, menu);
		//親クラスの同名メソッドを呼び出し、その戻り値を返却。
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		//親クラスの同名メソッドを呼び出し、その戻り値を返却。
		return super.onOptionsItemSelected(item);
	}
}
