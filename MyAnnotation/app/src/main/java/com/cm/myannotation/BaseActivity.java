package com.cm.myannotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cm.myannotation.annotation.Injection;

public class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Injection.inject(this);
	}
}
