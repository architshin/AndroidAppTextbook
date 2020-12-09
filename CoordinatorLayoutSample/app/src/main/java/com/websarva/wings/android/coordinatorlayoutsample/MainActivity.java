package com.websarva.wings.android.coordinatorlayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * 『Androidアプリ開発の教科書』
 * 第16章
 * スクロール連動サンプル
 *
 * アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Toolbarを取得。
		Toolbar toolbar = findViewById(R.id.toolbar);
		//ツールバーにロゴを設定。
		toolbar.setLogo(R.mipmap.ic_launcher);
		//アクションバーにツールバーを設定。
		setSupportActionBar(toolbar);
		//CollapsingToolbarLayoutを取得。
		CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
		//タイトルを設定。
		toolbarLayout.setTitle(getString(R.string.toolbar_title));
		//通常サイズ時の文字色を設定。
		toolbarLayout.setExpandedTitleColor(Color.WHITE);
		//縮小サイズ時の文字色を設定。
		toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);
	}
}
