package com.example.creditmarket.service;

import com.example.creditmarket.repository.FProductRespository;
import com.example.creditmarket.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrieNode {

    private final FProductRespository fProductRespository;
    private final OptionRepository optionRepository;
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;


    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    public void addWord(String word) {
        TrieNode current = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new TrieNode());
            }
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        TrieNode current = this;
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

    private void getWords(TrieNode node, StringBuilder prefix, List<String> words) {
        if (node.isEndOfWord) {
            words.add(prefix.toString());
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            char ch = entry.getKey();
            TrieNode child = entry.getValue();
            prefix.append(ch);
            getWords(child, prefix, words);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
