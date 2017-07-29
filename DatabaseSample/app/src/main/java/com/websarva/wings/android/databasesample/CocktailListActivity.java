package com.websarva.wings.android.databasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 『Androidアプリ開発の教科書』
 * 第10章
 * データベースサンプル
 *
 * カクテルリストアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class CocktailListActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cocktail_list);

		//カクテルリスト用ListView(lvCocktail)を取得。
		ListView lvCocktail = (ListView) findViewById(R.id.lvCocktail);
		//lvCocktailにリスナを登録。
		lvCocktail.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストがタップされたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//タップされた行のデータを取得。これがカクテル名となる。
			String cocktailName = (String) parent.getItemAtPosition(position);

			//インテントオブジェクトを生成。
			Intent intent = new Intent(CocktailListActivity.this, CocktailMemoActivity.class);
			//インテントに主キーとなるポジションとカクテル名を格納。
			intent.putExtra("id", position);
			intent.putExtra("cocktailName", cocktailName);
			//カクテルメモ画面を起動。
			startActivity(intent);
		}
	}
}
