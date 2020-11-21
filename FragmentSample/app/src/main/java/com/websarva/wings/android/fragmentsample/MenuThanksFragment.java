package com.websarva.wings.android.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// このフラグメントが所属するアクティビティオブジェクトを取得。
		Activity parentActivity = getActivity();
		//フラグメントで表示する画面をXMLファイルからインフレートする。
		View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);
		//所属アクティビティからインテントを取得。
		Intent intent = parentActivity.getIntent();
		//インテントから引き継ぎデータをまとめたもの(Bundleオブジェクト)を取得。
		Bundle extras = intent.getExtras();

		//注文した定食名と金額変数を用意。引き継ぎデータがうまく取得できなかった時のために""で初期化。
		String menuName = "";
		String menuPrice = "";
		//引き継ぎデータ(Bundleオブジェクト)が存在すれば…
		if(extras != null) {
			//定食名と金額を取得。
			menuName = extras.getString("menuName");
			menuPrice = extras.getString("menuPrice");
		}
		//定食名と金額を表示させるTextViewを取得。
		TextView tvMenuName = view.findViewById(R.id.tvMenuName);
		TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);
		//TextViewに定食名と金額を表示。
		tvMenuName.setText(menuName);
		tvMenuPrice.setText(menuPrice);

		//戻るボタンを取得。
		Button btBackButton = view.findViewById(R.id.btBackButton);
		//戻るボタンにリスナを登録。
		btBackButton.setOnClickListener(new ButtonClickListener());

		//インフレートされた画面を戻り値として返す。
		return view;
	}

	/**
	 * ボタンが押されたときの処理が記述されたメンバクラス。
	 */
	private class ButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			// このフラグメントが所属するアクティビティオブジェクトを取得。
			Activity parentActivity = getActivity();
			//自分が所属するアクティビティを終了。
			parentActivity.finish();
		}
	}
}
