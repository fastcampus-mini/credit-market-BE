package com.example.creditmarket.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoCompleteUtil {

    private Map<Character, AutoCompleteUtil> children;
    private boolean isEndOfWord;


    public AutoCompleteUtil() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    public void addWord(String word) {
        AutoCompleteUtil current = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new AutoCompleteUtil());
            }
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        AutoCompleteUtil current = this;
        List<String> words = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.children.containsKey(ch)) {
                return words;
            }
            current = current.children.get(ch);
        }
        getWords(current, new StringBuilder(prefix), words);
        return words;
    }

    private void getWords(AutoCompleteUtil node, StringBuilder prefix, List<String> words) {
        if (node.isEndOfWord) {
            words.add(prefix.toString());
        }
        for (Map.Entry<Character, AutoCompleteUtil> entry : node.children.entrySet()) {
            char ch = entry.getKey();
            AutoCompleteUtil child = entry.getValue();
            prefix.append(ch);
            getWords(child, prefix, words);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
