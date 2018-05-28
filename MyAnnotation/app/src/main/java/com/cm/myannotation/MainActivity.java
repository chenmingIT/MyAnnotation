package com.cm.myannotation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cm.myannotation.annotation.BindView;
import com.cm.myannotation.annotation.ContentView;
import com.cm.myannotation.annotation.OnClick;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity {

	@BindView(value = R.id.button1)
	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@OnClick(value = R.id.button1)
	public void button1Click(View view) {
		Toast.makeText(MainActivity.this, "button1 clicked", Toast.LENGTH_LONG).show();
	}
}
