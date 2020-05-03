package com.bdi.sb.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bdi.sb.vo.UserInfoVO;

public interface UserInfoService {
	public Map<String, Object> selectUserInfo(UserInfoVO user);
	public Map<String, Object> selectUserInfoList(UserInfoVO user);
	public Map<String,Object> doLogin(UserInfoVO user, HttpSession session);	
	public Map<String,Object> insertUserInfo(UserInfoVO user);
	public Map<String,Object> updateUserInfo(UserInfoVO user);
	public Map<String,Object> deleteUserInfo(UserInfoVO user);
	public Map<String,Object> deleteBkMark(UserInfoVO user);
	
}
