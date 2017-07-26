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

		ListView lvCocktail = (ListView) findViewById(R.id.lvCocktail);
		lvCocktail.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストがタップされたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String cocktailName = (String) parent.getItemAtPosition(position);

			Intent intent = new Intent(CocktailListActivity.this, CocktailMemoActivity.class);
			intent.putExtra("position", position);
			intent.putExtra("cocktailName", cocktailName);
			startActivity(intent);
		}
	}
}
