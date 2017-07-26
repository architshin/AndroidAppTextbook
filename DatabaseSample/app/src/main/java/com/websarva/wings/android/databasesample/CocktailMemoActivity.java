package com.websarva.wings.android.databasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

	private int _position = 0;
	private String _cocktailName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cocktail_memo);

		Intent intent = getIntent();
		_position = intent.getIntExtra("position", 0);
		_cocktailName = intent.getStringExtra("cocktailName");

		TextView tvCacktailName = (TextView) findViewById(R.id.tvCacktailName);
		tvCacktailName.setText(_cocktailName);
	}
}
