package com.websarva.wings.android.fragmentsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 『Androidアプリ開発の教科書』
 * 第9章
 * フラグメントサンプル
 *
 * 注文リストのフラグメントクラス。
 *
 * @author Shinzo SAITO
 */
public class MenuListFragment extends Fragment {
	/**
	 * コンストラクタ。
	 */
	public MenuListFragment() {
		super(R.layout.fragment_menu_list);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// 画面部品ListViewを取得
		ListView lvMenu = view.findViewById(R.id.lvMenu);

		// SimpleAdapterで使用するListオブジェクトを用意。
		List<Map<String, String>> menuList = new ArrayList<>();

		//「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
		Map<String, String> menu = new HashMap<>();
		menu.put("name", "から揚げ定食");
		menu.put("price", "800円");
		menuList.add(menu);

		//「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
		menu = new HashMap<>();
		menu.put("name", "ハンバーグ定食");
		menu.put("price", "850円");
		menuList.add(menu);

		// 以下データ登録の繰り返し。
		menu = new HashMap<>();
		menu.put("name", "生姜焼き定食");
		menu.put("price", "850円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "ステーキ定食");
		menu.put("price", "1000円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "野菜炒め定食");
		menu.put("price", "750円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "とんかつ定食");
		menu.put("price", "900円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "ミンチかつ定食");
		menu.put("price", "850円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "チキンカツ定食");
		menu.put("price", "900円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "コロッケ定食");
		menu.put("price", "850円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "回鍋肉定食");
		menu.put("price", "750円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "麻婆豆腐定食");
		menu.put("price", "800円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "青椒肉絲定食");
		menu.put("price", "900円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "八宝菜定食");
		menu.put("price", "800円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "酢豚定食");
		menu.put("price", "850円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "焼き魚定食");
		menu.put("price", "850円");
		menuList.add(menu);

		menu = new HashMap<>();
		menu.put("name", "焼肉定食");
		menu.put("price", "950円");
		menuList.add(menu);

		// このフラグメントが所属するアクティビティオブジェクトを取得。
		Activity parentActivity = getActivity();
		// SimpleAdapter第4引数from用データの用意。
		String[] from = {"name", "price"};
		// SimpleAdapter第5引数to用データの用意。
		int[] to = {android.R.id.text1, android.R.id.text2};
		// SimpleAdapterを生成。
		SimpleAdapter adapter = new SimpleAdapter(parentActivity, menuList, android.R.layout.simple_list_item_2, from, to);
		// アダプタの登録。
		lvMenu.setAdapter(adapter);
		// リスナの登録。
		lvMenu.setOnItemClickListener(new ListItemClickListener());
	}

	/**
	 * リストがタップされたときの処理が記述されたメンバクラス。
	 */
	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMap型!
			Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
			// 定食名と金額を取得。
			String menuName = item.get("name");
			String menuPrice = item.get("price");

			// 引き継ぎデータをまとめて格納できるBundleオブジェクト生成。
			Bundle bundle = new Bundle();
			// Bundleオブジェクトに引き継ぎデータを格納。
			bundle.putString("menuName", menuName);
			bundle.putString("menuPrice", menuPrice);

			// フラグメントマネージャーの取得。
			FragmentManager manager = getParentFragmentManager();
			// フラグメントトランザクションの開始。
			FragmentTransaction transaction = manager.beginTransaction();
			// フラグメントトランザクションが正しく動作するように設定。
			transaction.setReorderingAllowed(true);
			// このフラグメントが所属するアクティビティオブジェクトを取得。
			Activity parentActivity = getActivity();
			// 自分が所属するアクティビティからfragmentMainContainerを取得。
			View fragmentMainContainer = parentActivity.findViewById(R.id.fragmentMainContainer);
			// 自分が所属するアクティビティからfragmentThanksContainerを取得。
			View fragmentThanksContainer = parentActivity.findViewById(R.id.fragmentThanksContainer);
			// fragmentMainContainerが存在するなら…
			if(fragmentMainContainer != null) {
				// 現在の表示内容をバックスタックに追加。
				transaction.addToBackStack("Only List");
				// fragmentMainContainerのフラグメントを注文完了フラグメントに置き換え。
				transaction.replace(R.id.fragmentMainContainer, MenuThanksFragment.class, bundle);
			}
			// fragmentThanksContainerが存在するなら…
			else if(fragmentThanksContainer != null) {
				// fragmentThanksContainerのフラグメントを注文完了フラグメントに置き換え。
				transaction.replace(R.id.fragmentThanksContainer, MenuThanksFragment.class, bundle);
			}
			// フラグメントトランザクションのコミット。
			transaction.commit();
		}
	}
}
