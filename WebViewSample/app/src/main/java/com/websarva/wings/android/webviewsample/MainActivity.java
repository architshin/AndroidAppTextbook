package com.websarva.wings.android.webviewsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
	private List<Map<String, String>> _list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_list = createList();

		ListView lvSiteList = findViewById(R.id.lvSiteList);
		String[] from = {"name", "url"};
		int[] to = {android.R.id.text1, android.R.id.text2};
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _list, android.R.layout.simple_list_item_2, from, to);
		lvSiteList.setAdapter(adapter);
		lvSiteList.setOnItemClickListener(new ListItemClickListener());
	}

	private List<Map<String, String>> createList() {
		List<Map<String, String>> list = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put("name", "CodeZine");
		map.put("url", "https://codezine.jp/");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "EnterpriseZine");
		map.put("url", "https://www.enterprisezine.jp/");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "MarkeZine");
		map.put("url", "https://markezine.jp/");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "ECzine");
		map.put("url", "https://eczine.jp/");
		list.add(map);

		return list;
	}

	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Map<String, String> item = _list.get(position);
			String url = item.get("url");
			WebView wvSite = findViewById(R.id.wvSite);
			WebSettings webSettings = wvSite.getSettings();
			webSettings.setJavaScriptEnabled(true);
			wvSite.stopLoading();
			wvSite.setWebChromeClient(new WebChromeClient());
			wvSite.loadUrl(url);
		}
	}
}
