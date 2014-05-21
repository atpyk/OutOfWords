package com.word.lockscreen.parser;

import java.io.InputStream;
import java.util.List;

import com.word.lockscreen.model.Word;

public interface WordParser {

	public List<Word> parse(InputStream is) throws Exception; 
	
}
