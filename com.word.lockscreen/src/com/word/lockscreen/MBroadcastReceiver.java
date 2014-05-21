package com.word.lockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;

public class MBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		if (arg1.getAction().equals("android.intent.action.PHONE_STATE")
				&& LockWindow.lockWindow1 != null) { // 如果是来电广播，则移除此view
			WindowManager wm = (WindowManager) arg0
					.getSystemService(Context.WINDOW_SERVICE);
			wm.removeView(LockWindow.lockWindow1);
			LockWindow.lockWindow1 = null;
		} else if (arg1.getAction().equals(Intent.ACTION_SCREEN_OFF)
				&& LockWindow.lockWindow1 == null) { // 一黑屏即添加view，亮屏直接显示，防止延迟
			new LockWindow(arg0, 4);
		}
		Intent service = new Intent(); // 确保服务在每次亮屏都能运行
		service.setClass(arg0, MService.class);
		arg0.startService(service);

	}

}
