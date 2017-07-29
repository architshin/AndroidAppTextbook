package com.websarva.wings.android.databasesample2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * 『Androidアプリ開発の教科書』
 * 第10章
 * データベースサンプル改
 *
 * カクテルメモ登録アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class CocktailMemoAddActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cocktail_memo_add);

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
		//タップされたメニューのidを取得。
		int itemId = item.getItemId();
		//タップされたメニューが保存ボタンなら…
		if(itemId == R.id.menuOptionsSave) {
			//入力されたカクテル名を取得。
			EditText etCocktailName = (EditText) findViewById(R.id.etCocktailName);
			String cocktailName = etCocktailName.getText().toString();
			//RatingBarを取得。
			RatingBar rtbRate = (RatingBar) findViewById(R.id.rtbRate);
			//評価値にあたるRatingBarで選択された★の数を取得。これはgetRating()メソッドを使う。android:stepSizeの設定値では★半分などの選択が可能なので、戻り値はfloat。ここではそれをintにキャストして整数化している。
			int rate = (int) rtbRate.getRating();
			//入力された感想を取得。
			EditText etNote = (EditText) findViewById(R.id.etNote);
			String note = etNote.getText().toString();

			//データベースヘルパーオブジェクトを作成。
			DatabaseHelper helper = new DatabaseHelper(CocktailMemoAddActivity.this);
			//データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得。
			SQLiteDatabase db = helper.getWritableDatabase();
			//インサート用SQL文字列の用意。主キーの_idは自動採番なので登録対象カラムとしない。
			String sqlInsert = "INSERT INTO cocktailmemo (name, rate, note) VALUES (?, ?, ?)";
			//SQL文字列を元にプリペアドステートメントを取得。
			SQLiteStatement stmt = db.compileStatement(sqlInsert);
			//変数のバイド。
			stmt.bindString(1, cocktailName);
			stmt.bindLong(2, rate);
			stmt.bindString(3, note);
			//インサートSQLの実行。
			stmt.executeInsert();

			//データベース接続オブジェクトの解放。
			db.close();
		}
		//アクティビティの終了。
		finish();
		//親クラスの同名メソッドを呼び出し、その戻り値を返却。
		return super.onOptionsItemSelected(item);
	}
}
