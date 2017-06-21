package com.websarva.wings.android.listviewsample2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 『Androidアプリ開発の教科書』
 * 第5章
 * リスト選択サンプル2
 *
 * メインアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class ListViewSample2Activity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_sample2);

		ListView lvMenu = (ListView) findViewById(R.id.lvMenu);

		List<String> menuList = new ArrayList<String>();
		menuList.add("から揚げ定食");
		menuList.add("ハンバーグ定食");
		menuList.add("生姜焼き定食");
		menuList.add("ステーキ定食");
		menuList.add("野菜炒め定食");
		menuList.add("とんかつ定食");
		menuList.add("ミンチかつ定食");
		menuList.add("チキンカツ定食");
		menuList.add("コロッケ定食");
		menuList.add("焼き魚定食");
		menuList.add("焼肉定食");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewSample2Activity.this, android.R.layout.simple_list_item_1, menuList);
		lvMenu.setAdapter(adapter);

		lvMenu.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストがタップされたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TextView tvText = (TextView) view;
			String item = tvText.getText().toString();
			String show = "あなたが選んだ定食: " + item;

			AlertDialog.Builder builder = new AlertDialog.Builder(ListViewSample2Activity.this);
			builder.setTitle("注文確認");
			builder.setMessage(show);
			builder.setPositiveButton("注文", new DialogButtonClickListener());
			builder.setNeutralButton("問合せ", new DialogButtonClickListener());
			builder.setNegativeButton("キャンセル", new DialogButtonClickListener());
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}

	/**
	 * ダイアログのボタンがタップされた時の処理が記述されたメンバクラス。
	 */
	private class DialogButtonClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which) {
				case DialogInterface.BUTTON_POSITIVE:
					Toast.makeText(ListViewSample2Activity.this, "ご注文ありがとうございます。", Toast.LENGTH_LONG).show();
			}
		}
	}
	}
