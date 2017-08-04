package com.websarva.wings.android.databasesample2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 『Androidアプリ開発の教科書』
 * 第10章
 * データベースサンプル改
 *
 * データベースヘルパークラス。
 *
 * @author Shinzo SAITO
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	/**
	 * データベースファイル名の定数フィールド。
	 */
	private static final String DATABASE_NAME = "cocktailmemo.db";
	/**
	 * バージョン情報の定数フィールド。
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * コンストラクタ。
	 * @param context コンテキスト。
	 */
	public DatabaseHelper(Context context) {
		//親クラスのコンストラクタの呼び出し。
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//テーブル作成用SQL文字列の作成。
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE cocktailmemo (");
		sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sb.append("name TEXT,");
		sb.append("rate INTEGER,");
		sb.append("note TEXT");
		sb.append(");");
		String sql = sb.toString();

		//SQLの実行。
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}