package com.bdi.sb.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bdi.sb.service.UserInfoService;
import com.bdi.sb.vo.TestInfoVO;
import com.bdi.sb.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins="http://localhost:801")
@RestController
@Slf4j
public class UserController {
	@Resource
	private UserInfoService uiService;

	@GetMapping("/test")
	public List<TestInfoVO> getTest(@ModelAttribute TestInfoVO test) {
		log.info("test=>{}", test);
		return uiService.selectTestInfo(test);
	}
	
	@GetMapping("/user")
	public Map<String, Object> getUser(@ModelAttribute UserInfoVO user) {
		log.info("test=>{}", user);
		return uiService.selectUserInfo(user);
	}

	@GetMapping("/users")
	public Map<String, Object> getUserInfoList() {
		return uiService.selectUserInfoList(null);
	}

	@PostMapping("/user/login")
	public Map<String, Object> doLogin(@RequestBody UserInfoVO user, HttpSession session) {
		log.info("login=>{}", user);
		return uiService.doLogin(user, session);
	}

	@GetMapping("/user/logout")
	public ModelAndView doLogout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("/app/menu");
		mav.addObject("msg", "로그아웃 되었습니다.");
		return mav;
	} 

	@PostMapping("/user")
	public Map<String, Object> insertUser(@RequestBody UserInfoVO user) {
		log.info("test=>{}", user);
		
		return uiService.insertUserInfo(user);
	}

	@PutMapping("/user")
	public Map<String, Object> updateUser(@ModelAttribute UserInfoVO user) {
		log.info("test=>{}", user);
		if(user.getUiPwd() == "") {
			user.setUiPwd(null);
		}
		
		return uiService.updateUserInfo(user);
	}

	@DeleteMapping("/user")
	public Map<String, Object> deleteUser(@RequestBody UserInfoVO user) {
		System.out.println(user);
		return uiService.deleteUserInfo(user);
	}
	// user목록 가져오기

	@GetMapping("/getUserList")
	public Map<String, Object> getUserList(UserInfoVO user) {
		return uiService.selectUserInfoList(user);
	}

	// user삭제 
	@GetMapping("/deleteUser")
	public Map<String, Object> deleteUserList(UserInfoVO user) {
		log.info("user=>{}", user);
		return uiService.deleteUserInfo(user);
	}
	
	@DeleteMapping("/dltBkMark")
	public Map<String, Object> deleteBkMark(@RequestBody UserInfoVO user) {
		System.out.println(user);
		return uiService.deleteBkMark(user);
	}
}
