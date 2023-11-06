package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public List<String> findCategoryNameByAll() {
		return categoryRepository.findCategoryNameByAll();
	}
}
