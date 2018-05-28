package com.cm.myannotation.annotation;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.cm.myannotation.MainActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ChenMing on 2018/5/28.
 */

public class Injection {
	public static void inject(Object object) {
		contentView(object);
		bindView(object);
		onClick(object);
	}

	private static void bindView(Object object) {
		Class cls = object.getClass();

		Field[] fields = cls.getFields();
		for(Field field : fields) {
			BindView bindView = field.getAnnotation(BindView.class);
			if(bindView != null && bindView.value() != 0) {
				int id = bindView.value();
				try {
					field.setAccessible(true);
					if(object instanceof Activity) {
						field.set(object, ((Activity)object).findViewById(id));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void onClick(final Object object) {
		Class cls = object.getClass();

		Method[] methods = cls.getMethods();
		for(final Method method : methods) {
			OnClick onClick = method.getAnnotation(OnClick.class);
			if (onClick != null && onClick.value() != 0) {
				int id = onClick.value();
				View view = null;
				if (object instanceof Activity) {
					view = ((Activity)object).findViewById(id);
					if (view != null) {
						view.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								try {
									method.invoke(object, view);
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}

							}
						});
					}
				}
			}
		}
	}

	private static void contentView(Object object) {
		Class cls = object.getClass();
		ContentView contentView = (ContentView)cls.getAnnotation(ContentView.class);
		if (object instanceof Activity) {
			int id = contentView.value();
			if (id != 0) {
				((Activity)object).setContentView(id);
			}
		}
	}
}
