package com.word.lockscreen.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.word.lockscreen.model.Word;

public class PullWordParser implements WordParser {

	@Override
	public List<Word> parse(InputStream is) throws Exception {
		List<Word> words = null;
		Word word = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				words = new ArrayList<Word>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("item")) {
					word = new Word();
				} else if (parser.getName().equals("word")) {
					eventType = parser.next();
					word.setWord(parser.getText().replace("\n", ""));
				} else if (parser.getName().equals("phonetic")) {
					eventType = parser.next();
					word.setPhonetic(parser.getText().replace("\n", ""));
				} else if (parser.getName().equals("trans")) {
					eventType = parser.next();
					word.setTrans(parser.getText());
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("item")) {
					words.add(word);
					word = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return words;
	}

}
