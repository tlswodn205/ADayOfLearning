package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.request.UpdateUserData;
import com.tencoding.ADayOfLearning.dto.response.AdminCustomerResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainCustomerResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ChartResponseDto;
import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;


@Mapper
public interface UserRepository {
	public int insert(User user);
	public int updateByUserId(User user);
	public int agreeBusiness(Integer userId);
	public int deleteByUserId(int userId);
	public int deleteBusinessByUserId(int userId);
	public int deleteCustomerByUserId(int userId);
	public User findByUserId(int userId);
	public List<User> findByAll();
	public User findByUsername(String username);
	public User findByEmail(String email);
	public BusinessMainUserDataResponseDto findUserDataByUserId(int userId);
	public String findUsernameByEmail(String email);
	public int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);
	public int updatePasswordByUserId(@Param("userId") int userId, @Param("password") String password);
	public List<AdminMainCustomerResponseDto> findCustomer();
	public List<AdminMainBusinessResponseDto> findBusiness();
	public PagingResponseDto findCustomerPaging(@Param("type") String type, @Param("keyword") String keyword, @Param("page") Integer page);
	public List<AdminCustomerResponseDto> findCustomerList(@Param("type") String type, @Param("keyword") String keyword, @Param("startNum") int startNum);
	public AdminCustomerResponseDto findCustomerByUserId(Integer userId);
	public List<ChartResponseDto> customerChartData();
	public List<ChartResponseDto> businessChartData();
	public List<ChartResponseDto> requestBusinessChartData();
}