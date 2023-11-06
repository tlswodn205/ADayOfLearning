package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.Category;

@Mapper
public interface CategoryRepository {
	public int insert(Category category);
	public int updateByCategoryId(Category category);
	public int deleteByCategoryId(int categoryId);
	public Category findByCategoryId(int categoryId);
	public List<Category> findByAll();
	public List<String> findCategoryNameByAll();
}