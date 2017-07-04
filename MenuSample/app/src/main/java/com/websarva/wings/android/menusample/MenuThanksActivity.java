package com.websarva.wings.android.menusample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 『Androidアプリ開発の教科書』
 * 第8章
 * メニューサンプル
 *
 * 注文完了画面のアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MenuThanksActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_thanks);

		Intent intent = getIntent();
		String menuName = intent.getStringExtra("menuName");
		String menuPrice = intent.getStringExtra("menuPrice");

		TextView tvMenuName = (TextView) findViewById(R.id.tvMenuName);
		TextView tvMenuPrice = (TextView) findViewById(R.id.tvMenuPrice);

		tvMenuName.setText(menuName);
		tvMenuPrice.setText(menuPrice);
	}

	/**
	 * 戻るボタンをタップした時の処理。
	 * @param view 画面部品。
	 */
	public void onBackButtonClick(View view) {
		finish();
	}
}
