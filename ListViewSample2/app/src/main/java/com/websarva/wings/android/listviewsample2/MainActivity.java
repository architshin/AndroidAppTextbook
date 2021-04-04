package com.websarva.wings.android.listviewsample2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ListViewオブジェクトを取得。
		ListView lvMenu = findViewById(R.id.lvMenu);
		// リストビューに表示するリストデータ用Listオブジェクトを作成。
		List<String> menuList = new ArrayList<>();
		// リストデータの登録。
		menuList.add("から揚げ定食");
		menuList.add("ハンバーグ定食");
		menuList.add("生姜焼き定食");
		menuList.add("ステーキ定食");
		menuList.add("野菜炒め定食");
		menuList.add("とんかつ定食");
		menuList.add("ミンチかつ定食");
		menuList.add("チキンカツ定食");
		menuList.add("コロッケ定食");
		menuList.add("回鍋肉定食");
		menuList.add("麻婆豆腐定食");
		menuList.add("青椒肉絲定食");
		menuList.add("焼き魚定食");
		menuList.add("焼肉定食");
		// アダプタオブジェクトを生成。
		ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, menuList);
		// リストビューにアダプタオブジェクトを設定。
		lvMenu.setAdapter(adapter);
		// リストビューにリスナを設定。
		lvMenu.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストがタップされたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 注文確認ダイアログフラグメントオブジェクトを生成。
			OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();
			// ダイアログ表示。
			dialogFragment.show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
		}
	}
}
