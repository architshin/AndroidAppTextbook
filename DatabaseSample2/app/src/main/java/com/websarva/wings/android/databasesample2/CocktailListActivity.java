package com.websarva.wings.android.databasesample2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;

/**
 * 『Androidアプリ開発の教科書』
 * 第10章
 * データベースサンプル改
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

	@Override
	protected void onResume() {
		//親クラスのメソッド呼び出し。
		super.onResume();
		//カクテルリスト用ListView(lvCocktail)の取得。
		ListView lvCocktail = (ListView) findViewById(R.id.lvCocktail);
		//データベースヘルパーオブジェクトを作成。
		DatabaseHelper helper = new DatabaseHelper(CocktailListActivity.this);
		//データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得。
		SQLiteDatabase db = helper.getWritableDatabase();
		//主キーによる検索SQL文字列の用意。
		String sql = "SELECT * FROM cocktailmemo";
		//SQLの実行。
		Cursor cursor = db.rawQuery(sql, null);
		//SimpleCursorAdapterで使用するfrom-toを用意。
		String[] from = { "name" };
		int[] to = { android.R.id.text1 };
		//SimpleCursorAdapterの生成。
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(CocktailListActivity.this, android.R.layout.simple_list_item_1, cursor, from, to, 0);
		//lvCocktailにアダプタをセット。
		lvCocktail.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//メニューインフレーターの取得。
		MenuInflater inflater = getMenuInflater();
		//オプションメニュー用xmlファイルをインフレイト。
		inflater.inflate(R.menu.menu_options_cocktail_list, menu);
		//親クラスの同名メソッドを呼び出し、その戻り値を返却。
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//タップされたメニューのidを取得。
		int itemId = item.getItemId();
		//タップされたメニューが新規ボタンなら…
		if(itemId == R.id.menuOptionsAdd) {
			//インテントオブジェクトを生成。
			Intent intent = new Intent(CocktailListActivity.this, CocktailMemoAddActivity.class);
			//カクテルメモ登録画面を起動。
			startActivity(intent);
		}
		//親クラスの同名メソッドを呼び出し、その戻り値を返却。
		return super.onOptionsItemSelected(item);
	}

	/**
	 * リストがタップされたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//タップされた行のデータを取得。SimpleCursorADapterではこれはCursorオブジェクトとなる。
			Cursor cocktail = (Cursor) parent.getItemAtPosition(position);
			//カクテル名のカラムインデックスを取得。
			int idxCocktailName = cocktail.getColumnIndex("name");
			//カラムインデックスからカラムデータを取得。
			String cocktailName = cocktail.getString(idxCocktailName);

			//インテントオブジェクトを生成。
			Intent intent = new Intent(CocktailListActivity.this, CocktailMemoActivity.class);
			//インテントに主キーとなるDBの主キーとカクテル名を格納。
			intent.putExtra("id", (int) id);
			intent.putExtra("cocktailName", cocktailName);
			//カクテルメモ画面を起動。
			startActivity(intent);
		}
	}
}
