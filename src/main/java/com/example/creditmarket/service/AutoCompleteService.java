package com.example.creditmarket.service;

import com.example.creditmarket.repository.FProductRespository;
import com.example.creditmarket.repository.OptionRepository;

import java.util.List;

public interface AutoCompleteService {

    public List<String> getAutoComplete(String prefix);
}
