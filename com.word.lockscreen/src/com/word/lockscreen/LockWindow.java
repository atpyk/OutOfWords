package com.word.lockscreen;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.word.lockscreen.model.Word;
import com.word.lockscreen.parser.PullWordParser;

public class LockWindow implements OnClickListener {

	private static WindowManager.LayoutParams wmParams = null;
	public static View lockWindow1;
	private DisplayMetrics displayMetrics;
	private WindowManager windowManager;
	private ImageButton addListBtn;
	private ImageButton unlockBtn;
	private ImageButton pinBtn;
	private Context context;
	private EditText inputView;
	private PullWordParser parser;
	private List<Word> words;
	private TextView wordView;
	private TextView phoneticView;
	private TextView meaningView;
	private Word currentWord;
	private Button btnNext;

	String answer = "E";
	int minute = 10;
	int second = 0;
	NumberFormat nf;
	Handler h;
	SharedPreferences prefs;
	View lockWindow;
	private Button btnA;
	private Button btnB;
	private Button btnC;
	private Button btnD;
	private Button btnE;
	private Button btnF;
	private Button btnG;
	private Button btnH;
	private Button btnI;
	private Button btnJ;
	private Button btnK;
	private Button btnL;
	private Button btnM;
	private Button btnN;
	private Button btnO;
	private Button btnP;
	private Button btnQ;
	private Button btnR;
	private Button btnS;
	private Button btnT;
	private Button btnU;
	private Button btnV;
	private Button btnW;
	private Button btnX;
	private Button btnY;
	private Button btnZ;
	private Button btnClear;
	private Button btnDelete;

	public LockWindow(final Context context, int counter) {
		this.context = context;
		// prefs = context.getSharedPreferences(
		// ScreenOn_OffActivity.SHARED_PREFS_NAME, 0);
		windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wmParams = new WindowManager.LayoutParams();
		wmParams.type = 2003; // typeÊÇ¹Ø¼ü£¬ÕâÀïµÄ2002±íÊ¾ÏµÍ³¼¶´°¿Ú£¬ÄãÒ²¿ÉÒÔÊÔÊÔ2003¡£
		wmParams.format = 1;
		wmParams.flags = 40;
		wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL; // µ÷ÕûÐü¸¡´°¿ÚÖÁ×óÉÏ½Ç
		// ÒÔÖÐ¼äÎ»Ô­µã£¬ÉèÖÃx¡¢y³õÊ¼Öµ
		wmParams.x = 0;
		wmParams.y = 0;
		wmParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
		displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		wmParams.width = displayMetrics.widthPixels;
		wmParams.height = displayMetrics.heightPixels;
		wmParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
		LayoutInflater inflater = LayoutInflater.from(context);
		lockWindow = inflater.inflate(R.layout.lockwindow, null);
		// lockWindow.setBackgroundDrawable(getBack());
		lockWindow1 = lockWindow;
		inputView = (EditText) lockWindow.findViewById(R.id.input_view);
		// inputView.setFocusable(true);
		addListBtn = (ImageButton) lockWindow.findViewById(R.id.add_list_btn);
		addListBtn.setOnClickListener(this);
		unlockBtn = (ImageButton) lockWindow.findViewById(R.id.unlock_btn);
		unlockBtn.setOnClickListener(this);
		pinBtn=(ImageButton)lockWindow.findViewById(R.id.pin_imageview);
		pinBtn.setOnClickListener(this);
		initButtons();

		h = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					// tv.setTextSize(34);
					// tv.setGravity(Gravity.CENTER);
					// tv.setText(nf.format(minute) + "£º" + nf.format(second));
					// rba.setChecked(false);
					// rbb.setChecked(false);
					// rbc.setChecked(false);
					// rbd.setChecked(false);
					// setEnabled(false);
					// answer = "E";
				} else {
					// tv.setTextSize(14);
					// tv.setGravity(0);
					// tv.setText(q.getKey());
					// setEnabled(true);
					// tv3.setText("");
					// minute = Integer.parseInt(prefs.getString(context
					// .getResources().getString(R.string.leaf1Count),
					// "10"));
					// second = 0;
				}
			}
		};
		// q = new question();
		// tv.setText(q.getKey());
		wordView = (TextView) lockWindow.findViewById(R.id.word_view);
		phoneticView = (TextView) lockWindow
				.findViewById(R.id.phonetic_symbol_view);
		meaningView = (TextView) lockWindow.findViewById(R.id.meaning_view);
		currentWord = getWordByRandom();
		if (currentWord != null) {
			wordView.setText(currentWord.getWord());
			phoneticView.setText(currentWord.getPhonetic());
			meaningView.setText(currentWord.getTrans());
		}

		windowManager.addView(lockWindow, wmParams);

	}

	private void initButtons() {
		btnA = (Button) lockWindow.findViewById(R.id.btn_a);
		btnB = (Button) lockWindow.findViewById(R.id.btn_b);
		btnC = (Button) lockWindow.findViewById(R.id.btn_c);
		btnD = (Button) lockWindow.findViewById(R.id.btn_d);
		btnE = (Button) lockWindow.findViewById(R.id.btn_e);
		btnF = (Button) lockWindow.findViewById(R.id.btn_f);
		btnG = (Button) lockWindow.findViewById(R.id.btn_g);
		btnH = (Button) lockWindow.findViewById(R.id.btn_h);
		btnI = (Button) lockWindow.findViewById(R.id.btn_i);
		btnJ = (Button) lockWindow.findViewById(R.id.btn_j);
		btnK = (Button) lockWindow.findViewById(R.id.btn_k);
		btnL = (Button) lockWindow.findViewById(R.id.btn_l);
		btnM = (Button) lockWindow.findViewById(R.id.btn_m);
		btnN = (Button) lockWindow.findViewById(R.id.btn_n);
		btnO = (Button) lockWindow.findViewById(R.id.btn_o);
		btnP = (Button) lockWindow.findViewById(R.id.btn_p);
		btnQ = (Button) lockWindow.findViewById(R.id.btn_q);
		btnR = (Button) lockWindow.findViewById(R.id.btn_r);
		btnS = (Button) lockWindow.findViewById(R.id.btn_s);
		btnT = (Button) lockWindow.findViewById(R.id.btn_t);
		btnU = (Button) lockWindow.findViewById(R.id.btn_u);
		btnV = (Button) lockWindow.findViewById(R.id.btn_v);
		btnW = (Button) lockWindow.findViewById(R.id.btn_w);
		btnX = (Button) lockWindow.findViewById(R.id.btn_x);
		btnY = (Button) lockWindow.findViewById(R.id.btn_y);
		btnZ = (Button) lockWindow.findViewById(R.id.btn_z);
		btnClear = (Button) lockWindow.findViewById(R.id.btn_clear);
		btnDelete = (Button) lockWindow.findViewById(R.id.btn_delete);
		btnNext = (Button) lockWindow.findViewById(R.id.btn_next);

		btnA.setOnClickListener(btnClickListener);
		btnB.setOnClickListener(btnClickListener);
		btnC.setOnClickListener(btnClickListener);
		btnD.setOnClickListener(btnClickListener);
		btnE.setOnClickListener(btnClickListener);
		btnF.setOnClickListener(btnClickListener);
		btnG.setOnClickListener(btnClickListener);
		btnH.setOnClickListener(btnClickListener);
		btnI.setOnClickListener(btnClickListener);
		btnJ.setOnClickListener(btnClickListener);
		btnK.setOnClickListener(btnClickListener);
		btnL.setOnClickListener(btnClickListener);
		btnM.setOnClickListener(btnClickListener);
		btnN.setOnClickListener(btnClickListener);
		btnO.setOnClickListener(btnClickListener);
		btnP.setOnClickListener(btnClickListener);
		btnQ.setOnClickListener(btnClickListener);
		btnR.setOnClickListener(btnClickListener);
		btnS.setOnClickListener(btnClickListener);
		btnT.setOnClickListener(btnClickListener);
		btnU.setOnClickListener(btnClickListener);
		btnV.setOnClickListener(btnClickListener);
		btnW.setOnClickListener(btnClickListener);
		btnX.setOnClickListener(btnClickListener);
		btnY.setOnClickListener(btnClickListener);
		btnZ.setOnClickListener(btnClickListener);
		btnClear.setOnClickListener(btnClickListener);
		btnDelete.setOnClickListener(btnClickListener);
		btnNext.setOnClickListener(btnClickListener);
	}

	OnClickListener btnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String str = inputView.getText().toString().toLowerCase();

			switch (v.getId()) {
			case R.id.btn_a:
				inputView
						.setText(str + btnA.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_b:
				inputView
						.setText(str + btnB.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_c:
				inputView
						.setText(str + btnC.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_d:
				inputView
						.setText(str + btnD.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_e:
				inputView
						.setText(str + btnE.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_f:
				inputView
						.setText(str + btnF.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_g:
				inputView
						.setText(str + btnG.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_h:
				inputView
						.setText(str + btnH.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_i:
				inputView
						.setText(str + btnI.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_j:
				inputView
						.setText(str + btnJ.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_k:
				inputView
						.setText(str + btnK.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_l:
				inputView
						.setText(str + btnL.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_m:
				inputView
						.setText(str + btnM.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_n:
				inputView
						.setText(str + btnN.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_o:
				inputView
						.setText(str + btnO.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_p:
				inputView
						.setText(str + btnP.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_q:
				inputView
						.setText(str + btnQ.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_r:
				inputView
						.setText(str + btnR.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_s:
				inputView
						.setText(str + btnS.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_t:
				inputView
						.setText(str + btnT.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_u:
				inputView
						.setText(str + btnU.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_v:
				inputView
						.setText(str + btnV.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_w:
				inputView
						.setText(str + btnW.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_x:
				inputView
						.setText(str + btnX.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_y:
				inputView
						.setText(str + btnY.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_z:
				inputView
						.setText(str + btnZ.getText().toString().toLowerCase());
				setSelection();
				break;
			case R.id.btn_clear:
				inputView.setText(null);
				break;
			case R.id.btn_delete:

				String inputStr = inputView.getText().toString();
				inputStr = inputStr.substring(0, inputStr.length() - 1);
				inputView.setText(inputStr);
				setSelection();
				break;
			case R.id.btn_next:
				currentWord = getWordByRandom();
				if (currentWord != null) {
					wordView.setText(currentWord.getWord());
					phoneticView.setText(currentWord.getPhonetic());
					meaningView.setText(currentWord.getTrans());
				}
				break;
			}
		}
	};

	private void setSelection() {

		inputView.setSelection(inputView.getText().toString().length());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.unlock_btn) {

			if (currentWord != null && inputView.getText() != null) {
                String wordStr=currentWord.getWord().toString().toLowerCase();
				if (wordStr.equals(inputView.getText().toString())) {
					if (lockWindow1 != null) {

						windowManager.removeView(lockWindow1);
						lockWindow1 = null;
					}
				}else{
				    ToastView.show(context, 0, 300, "输入单词不正确", 23);
				}

			}

		}else if(v.getId()==R.id.pin_imageview){
			
			 ToastView.show(context, 0, 300, "已标记", 23);
			
		}

		// if (v == next) {
		// tv.setText(q.getKey());
		// tv3.setText("");
		// } else if (v == send) {
		// if (!answer.equals("E")) {
		// if (answer.equals(q.getValue())) {
		// tv3.setText("´ð°¸ÕýÈ·£¬½âËø³É¹¦£¡");
		// if (lockWindow1 != null) {
		// windowManager.removeView(lockWindow1);
		// lockWindow1 = null;
		// }
		// } else {
		// minute = Integer.parseInt(prefs.getString(context
		// .getResources().getString(R.string.leaf1Count),
		// "10"));
		// tv3.setText("´ð°¸´íÎó£¬½âËøÊ§°Ü,Çë" + minute + "·ÖÖÓºóÔÙÀ´£¡");
		// new myThread().start();
		// }
		// } else
		// tv3.setText("ÇëÊäÈë´ð°¸£¡");
		// } else {
		// Toast.makeText(context, "¼üÅÌ", Toast.LENGTH_SHORT).show();
		//
		// }
	}

	private Word getWordByRandom() {
		Word word = null;
		try {
			if (words == null) {
				InputStream is = context.getAssets().open("IELTS-A.xml");
				parser = new PullWordParser();
				words = parser.parse(is);
			}
			Random r = new Random();
			int index = r.nextInt(words.size());
			word = words.get(index);
			Log.d("mytest", "list size=" + words.size() + ",index=" + index);
		} catch (Exception e) {

		}
		return word;
	}

	public BitmapDrawable getBack() {
		WallpaperManager wallpaperManager = WallpaperManager
				.getInstance(context);
		// »ñÈ¡µ±Ç°±ÚÖ½
		Drawable wallpaperDrawable = wallpaperManager.getDrawable();
		// ½«Drawable,×ª³ÉBitmap
		Bitmap bm = ((BitmapDrawable) wallpaperDrawable).getBitmap();
		float step = 0;
		// ¼ÆËã³öÆÁÄ»µÄÆ«ÒÆÁ¿
		step = (bm.getWidth() - 480) / (7 - 1);
		// ½ØÈ¡ÏàÓ¦ÆÁÄ»µÄBitmap
		Bitmap pbm = Bitmap.createBitmap(bm, (int) (5 * step), 0,
				displayMetrics.widthPixels, displayMetrics.heightPixels);

		return new BitmapDrawable(pbm);
	}

	class myThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(2);
			Message m = new Message();
			while (minute > 0 || second > 0) {
				try {
					m = h.obtainMessage();
					m.what = 0;
					h.sendMessage(m);
					Thread.sleep(1000);
					if (second == 0) {
						second = 59;
						minute--;
					} else
						second--;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			m = h.obtainMessage();
			m.what = 1;
			h.sendMessage(m);
		}
	}

	public void setEnabled(boolean flag) {
		// send.setEnabled(flag);
		// next.setEnabled(flag);
		// group.setEnabled(flag);
		// rba.setEnabled(flag);
		// rbb.setEnabled(flag);
		// rbc.setEnabled(flag);
		// rbd.setEnabled(flag);
	}

}
