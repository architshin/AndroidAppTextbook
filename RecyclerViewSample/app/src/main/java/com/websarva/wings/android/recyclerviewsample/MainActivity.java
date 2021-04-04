package com.websarva.wings.android.recyclerviewsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 『Androidアプリ開発の教科書』
 * 第17章
 * リサイクラービューサンプル
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

		// Toolbarを取得。
		Toolbar toolbar = findViewById(R.id.toolbar);
		// ツールバーにロゴを設定。
		toolbar.setLogo(R.mipmap.ic_launcher);
		// アクションバーにツールバーを設定。
		setSupportActionBar(toolbar);
		// CollapsingToolbarLayoutを取得。
		CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
		// タイトルを設定。
		toolbarLayout.setTitle(getString(R.string.toolbar_title));
		// 通常サイズ時の文字色を設定。
		toolbarLayout.setExpandedTitleColor(Color.WHITE);
		// 縮小サイズ時の文字色を設定。
		toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

		// RecyclerViewを取得。
		RecyclerView lvMenu = findViewById(R.id.lvMenu);
		// LinearLayoutManagerオブジェクトを生成。
		LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
		// 以下は他の2種のレイアウトマネージャー。
//		GridLayoutManager layout = new GridLayoutManager(MainActivity.this, 5);
//		StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
		// RecyclerViewにレイアウトマネージャーとしてLinearLayoutManagerを設定。
		lvMenu.setLayoutManager(layout);
		// 定食メニューリストデータを生成。
		List<Map<String, Object>> menuList = createTeishokuList();
		// アダプタオブジェクトを生成。
		RecyclerListAdapter adapter = new RecyclerListAdapter(menuList);
		// RecyclerViewにアダプタオブジェクトを設定。
		lvMenu.setAdapter(adapter);

		// 区切り専用のオブジェクトを生成。
		DividerItemDecoration decorator = new DividerItemDecoration(MainActivity.this, layout.getOrientation());
		// RecyclerViewに区切り線オブジェクトを設定。
		lvMenu.addItemDecoration(decorator);
	}

	/**
	 * リストビューに表示させる定食リストデータを生成するメソッド。
	 *
	 * @return 生成された定食リストデータ。
	 */
	private List<Map<String, Object>> createTeishokuList() {
		// 定食メニューリスト用のListオブジェクトを用意。
		List<Map<String, Object>> menuList = new ArrayList<>();

		// 「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
		Map<String, Object> menu = new HashMap<>();
		menu.put("name", "から揚げ定食");
		menu.put("price", 800);
		menu.put("desc", "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		// 「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
		menu = new HashMap<>();
		menu.put("name", "ハンバーグ定食");
		menu.put("price", 850);
		menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		// 以下データ登録の繰り返し。
		menu = new HashMap<>();
		menu.put("name", "生姜焼き定食");
		menu.put("price", 850);
		menu.put("desc", "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "ステーキ定食");
		menu.put("price", 1000);
		menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "野菜炒め定食");
		menu.put("price", 750);
		menu.put("desc", "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "とんかつ定食");
		menu.put("price", 900);
		menu.put("desc", "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "ミンチかつ定食");
		menu.put("price", 850);
		menu.put("desc", "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "チキンカツ定食");
		menu.put("price", 900);
		menu.put("desc", "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "コロッケ定食");
		menu.put("price", 850);
		menu.put("desc", "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "回鍋肉定食");
		menu.put("price", 750);
		menu.put("desc", "ピリ辛回鍋肉にサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "麻婆豆腐定食");
		menu.put("price", 800);
		menu.put("desc", "本格四川風麻婆豆腐にサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "青椒肉絲定食");
		menu.put("price", 900);
		menu.put("desc", "ピーマンの香り豊かな青椒肉絲にサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "焼き魚定食");
		menu.put("price", 850);
		menu.put("desc", "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "焼肉定食");
		menu.put("price", 950);
		menu.put("desc", "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * RecyclerViewのビューホルダクラス。
	 */
	private class RecyclerListViewHolder extends RecyclerView.ViewHolder {
		/**
		 * リスト1行分中でメニュー名を表示する画面部品。
		 */
		public TextView _tvMenuNameRow;

		/**
		 * リスト1行分中でメニュー金額を表示する画面部品。
		 */
		public TextView _tvMenuPriceRow;

		/**
		 * コンストラクタ。
		 *
		 * @param itemView リスト1行分の画面部品。
		 */
		public RecyclerListViewHolder(View itemView) {
			// 親クラスのコンストラクタの呼び出し。
			super(itemView);
			// 引数で渡されたリスト1行分の画面部品中から表示に使われるTextViewを取得。
			_tvMenuNameRow = itemView.findViewById(R.id.tvMenuNameRow);
			_tvMenuPriceRow = itemView.findViewById(R.id.tvMenuPriceRow);
		}
	}

	/**
	 * RecyclerViewのアダプタクラス。
	 */
	private class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListViewHolder> {
		/**
		 * リストデータを保持するフィールド。
		 */
		private List<Map<String, Object>> _listData;

		/**
		 * コンストラクタ。
		 *
		 * @param listData リストデータ。
		 */
		public RecyclerListAdapter(List<Map<String, Object>> listData) {
			// 引数のリストデータをフィールドに格納。
			_listData = listData;
		}

		@Override
		public RecyclerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			// レイアウトインフレータを取得。
			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			// row.xmlをインフレートし、1行分の画面部品とする。
			View view = inflater.inflate(R.layout.row, parent, false);
			// インフレートされた1行分の画面部品にリスナを設定。
			view.setOnClickListener(new ItemClickListener());
			// ビューホルダオブジェクトを生成。
			RecyclerListViewHolder holder = new RecyclerListViewHolder(view);
			// 生成したビューホルダをリターン。
			return holder;
		}

		@Override
		public void onBindViewHolder(RecyclerListViewHolder holder, int position) {
			// リストデータから該当1行分のデータを取得。
			Map<String, Object> item = _listData.get(position);
			// メニュー名文字列を取得。
			String menuName = (String) item.get("name");
			// メニュー金額を取得。
			int menuPrice = (Integer) item.get("price");
			// 表示用に金額を文字列に変換。
			String menuPriceStr = String.valueOf(menuPrice);
			// メニュー名と金額をビューホルダ中のTextViewに設定。
			holder._tvMenuNameRow.setText(menuName);
			holder._tvMenuPriceRow.setText(menuPriceStr);
		}

		@Override
		public int getItemCount() {
			// リストデータ中の件数をリターン。
			return _listData.size();
		}
	}

	/**
	 * リストをタップした時のリスナクラス。
	 */
	private class ItemClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			// タップされたLinearLayout内にあるメニュー名表示TextViewを取得。
			TextView tvMenuName =  view.findViewById(R.id.tvMenuNameRow);
			// メニュー名表示TextViewから表示されているメニュー名文字列を取得。
			String menuName = tvMenuName.getText().toString();
			// トーストに表示する文字列を生成。
			String msg = getString(R.string.msg_header) + menuName;
			// トースト表示。
			Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
		}
	}
}
