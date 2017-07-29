package com.websarva.wings.android.databasesample2;

import android.content.Intent;
import android.database.Cursor;
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
		TextView tvCacktailName = (TextView) findViewById(R.id.tvCocktailName);
		tvCacktailName.setText(_cocktailName);

		//データベースヘルパーオブジェクトを作成。
		DatabaseHelper helper = new DatabaseHelper(CocktailMemoActivity.this);
		//データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得。
		SQLiteDatabase db = helper.getWritableDatabase();
		//主キーによる検索SQL文字列の用意。
		String sql = "SELECT * FROM cocktailmemo WHERE _id = " + _id;
		//SQLの実行。
		Cursor cursor = db.rawQuery(sql, null);
		//データベースから取得した値を格納する変数の用意。データがなかった時のための初期値も用意。
		int rate = 0;
		String note = "";
		//SQL実行の戻り値であるカーソルオブジェクトをループさせてデータベース内のデータを取得。
		while(cursor.moveToNext()) {
			//カラムのインデックス値を取得。
			int idxRate = cursor.getColumnIndex("rate");
			int idxNote = cursor.getColumnIndex("note");
			//カラムのインデックス値を元に実際のデータを取得。
			rate = cursor.getInt(idxRate);
			note = cursor.getString(idxNote);
		}

		//評価のRatingBar、および、感想のEditTextの各画面部品を取得。
		RatingBar rtbRate = (RatingBar) findViewById(R.id.rtbRate);
		EditText etNote = (EditText) findViewById(R.id.etNote);
		//各画面部品にデータベースの値を反映。
		rtbRate.setRating(rate);
		etNote.setText(note);

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
			//RatingBarを取得。
			RatingBar rtbRate = (RatingBar) findViewById(R.id.rtbRate);
			//評価値にあたるRatingBarで選択された★の数を取得。これはgetRating()メソッドを使う。android:stepSizeの設定値では★半分などの選択が可能なので、戻り値はfloat。ここではそれをintにキャストして整数化している。
			int rate = (int) rtbRate.getRating();
			//入力された感想を取得。
			EditText etNote = (EditText) findViewById(R.id.etNote);
			String note = etNote.getText().toString();

			//データベースヘルパーオブジェクトを作成。
			DatabaseHelper helper = new DatabaseHelper(CocktailMemoActivity.this);
			//データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得。
			SQLiteDatabase db = helper.getWritableDatabase();

			//まず、リストで選択されたカクテルのメモデータを削除。その後インサートを行う。
			//削除用SQL文字列を用意。
			String sqlDelete = "DELETE FROM cocktailmemo WHERE _id = ?";
			//SQL文字列を元にプリペアドステートメントを取得。
			SQLiteStatement stmt = db.compileStatement(sqlDelete);
			//変数のバイド。
			stmt.bindLong(1, _id);
			//削除SQLの実行。
			stmt.executeUpdateDelete();

			//インサート用SQL文字列の用意。
			String sqlInsert = "INSERT INTO cocktailmemo (_id, name, rate, note) VALUES (?, ?, ?, ?)";
			//SQL文字列を元にプリペアドステートメントを取得。
			stmt = db.compileStatement(sqlInsert);
			//変数のバイド。
			stmt.bindLong(1, _id);
			stmt.bindString(2, _cocktailName);
			stmt.bindLong(3, rate);
			stmt.bindString(4, note);
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
