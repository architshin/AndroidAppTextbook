package com.websarva.wings.android.fragmentsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
 * 注文完了のフラグメントクラス。
 *
 * @author Shinzo SAITO
 */
public class MenuThanksFragment extends Fragment {
	/**
	 * コンストラクタ。
	 */
	public MenuThanksFragment() {
		super(R.layout.fragment_menu_thanks);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// このフラグメントに埋め込まれた引き継ぎデータを取得。
		Bundle extras = getArguments();
		// 注文した定食名と金額変数を用意。引き継ぎデータがうまく取得できなかった時のために""で初期化。
		String menuName = "";
		String menuPrice = "";
		// 引き継ぎデータ(Bundleオブジェクト)が存在すれば…
		if(extras != null) {
			// 定食名と金額を取得。
			menuName = extras.getString("menuName");
			menuPrice = extras.getString("menuPrice");
		}
		// 定食名と金額を表示させるTextViewを取得。
		TextView tvMenuName = view.findViewById(R.id.tvMenuName);
		TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);
		// TextViewに定食名と金額を表示。
		tvMenuName.setText(menuName);
		tvMenuPrice.setText(menuPrice);

		// 戻るボタンを取得。
		Button btBackButton = view.findViewById(R.id.btThxBack);
		// 戻るボタンにリスナを登録。
		btBackButton.setOnClickListener(new ButtonClickListener());
	}

	/**
	 * ［リストに戻る］ボタンが押されたときの処理が記述されたメンバクラス。
	 */
	private class ButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			// フラグメントマネージャーを取得。
			FragmentManager manager = getParentFragmentManager();
			// このフラグメントが所属するアクティビティオブジェクトを取得。
			Activity parentActivity = getActivity();
			// 自分が所属するアクティビティからfragmentMainContainerを取得。
			View fragmentMainContainer = parentActivity.findViewById(R.id.fragmentMainContainer);
			// 自分が所属するアクティビティからfragmentThanksContainerを取得。
			View fragmentThanksContainer = parentActivity.findViewById(R.id.fragmentThanksContainer);
			// fragmentMainContainerが存在するなら…
			if(fragmentMainContainer != null) {
				// バックスタックのひとつ前の状態に戻る。
				manager.popBackStack();
			}
			// fragmentThanksContainerが存在するなら…
			else if(fragmentThanksContainer != null) {
				// フラグメントトランザクションの開始。
				FragmentTransaction transaction = manager.beginTransaction();
				// フラグメントトランザクションが正しく動作するように設定。
				transaction.setReorderingAllowed(true);
				// 自分自身を削除。
				transaction.remove(MenuThanksFragment.this);
				// フラグメントトランザクションのコミット。
				transaction.commit();
			}
		}
	}
}
