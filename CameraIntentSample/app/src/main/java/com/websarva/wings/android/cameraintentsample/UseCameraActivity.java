package com.websarva.wings.android.cameraintentsample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * 『Androidアプリ開発の教科書』
 * 第15章
 * カメラアプリ連携サンプル
 *
 * アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class UseCameraActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_use_camera);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//カメラアプリとの連携からの戻りでかつ撮影成功の場合
		if(requestCode == 200 && resultCode == RESULT_OK) {
			//撮影された画像のビットマップデータを取得。
			Bitmap bitmap =  data.getParcelableExtra("data");
			//画像を表示するImageViewを取得。
			ImageView ivCamera = (ImageView) findViewById(R.id.ivCamera);
			//撮影された画像をImageViewに設定。
			ivCamera.setImageBitmap(bitmap);
		}
	}

	/**
	 * 画像部分がタップされたときの処理メソッド。
	 *
	 * @param view 画面部品
	 */
	public void onCameraImageClick(View view) {
		//Intentオブジェクトを生成。
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//アクティビティを起動。
		startActivityForResult(intent, 200);
	}
}
