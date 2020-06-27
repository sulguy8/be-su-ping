package com.bdi.sb.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bdi.sb.config.CredentialsConfig;
import com.bdi.sb.mapper.BkmarkInfoMapper;
import com.bdi.sb.mapper.UserInfoMapper;
import com.bdi.sb.mapper.VenueInfoMapper;
import com.bdi.sb.service.UserInfoService;
import com.bdi.sb.vo.BkmarkStatsVO;
import com.bdi.sb.vo.PageVO;
import com.bdi.sb.vo.TestInfoVO;
import com.bdi.sb.vo.UserInfoVO;
import com.bdi.sb.vo.VenueInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private CredentialsConfig cc;
	
	@Resource 
	private UserInfoMapper uiMapper;
	@Resource 
	private BkmarkInfoMapper biMapper;
	@Resource 
	private VenueInfoMapper viMapper;
	
	@Override
	public List<TestInfoVO> selectTestInfo(TestInfoVO test) {
		List<TestInfoVO> result = uiMapper.selectTestInfo();
		System.out.println(result);
		return result;
	}
	@Override
	public Map<String, Object> selectUserInfo(UserInfoVO user) {
		BkmarkStatsVO bs = new BkmarkStatsVO();
		VenueInfoVO vi = new VenueInfoVO();
				
		bs.setUiNum(user.getUiNum());
		List<BkmarkStatsVO> bkList = biMapper.selectBkmarkName(bs);
		
		List<Integer> nList = new ArrayList<>();
		List<String> rList = new ArrayList<>();
		List<String> vList = new ArrayList<>();
		List<Integer> dList = new ArrayList<>();
		
		for(int i=0; i<bkList.size(); i++) {
			bs.setViNum(bkList.get(i).getViNum());
			bs = biMapper.selectBkvenue(bs);
			
			nList.add(bs.getViNum());
			rList.add(bs.getViName());
			vList.add(bs.getViImg());
			dList.add(bkList.get(i).getBkNum());
		}
		
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("UserInfo", uiMapper.selectUserInfo(user));
		rMap.put("VenueNum", nList);
		rMap.put("VenueName", rList);
		rMap.put("VenueImg", vList);
		rMap.put("BkNum", dList);
		return rMap;
	}
	
	@Override
	public Map<String, Object> selectUserInfoList(UserInfoVO user) {
		int startNum = user.getPage().getPage() * 10 - 10;
		user.getPage().setStartNum(startNum);
		Integer totalCount = uiMapper.totalUserInfoCount(user);
		Map<String, Object> rMap = new HashMap<>();
		log.info("venueInfoVO=>{}",user);
		rMap.put("list", uiMapper.selectUserInfoList(user));
		PageVO page = user.getPage();
		page.setTotalCount(totalCount);
		rMap.put("page", page);
		log.info("rMap=>{}", rMap);
		return rMap;
	}

	@Override
	public Map<String, Object> doLogin(UserInfoVO user, HttpSession session) {
		Map<String,Object> rMap = new HashMap<>();
		user = uiMapper.doLogin(user);
		rMap.put("result", "false");
		if(user!=null) {
			rMap.put("result", "true");
			rMap.put("user", user);
			
			session.setAttribute("userNum", user.getUiNum());
			session.setAttribute("userId", user.getUiId());  
			session.setAttribute("user", user.getUiName());
		}
		return rMap;
	}
	
	@Override
	public Map<String, Object> insertUserInfo(UserInfoVO user) {
		int cnt = uiMapper.insertUserInfo(user);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("msg", "회원가입 실패");
		if(cnt==1) {
			rMap.put("msg", "회원가입 성공");
		}
		return rMap;
	}
	
	@Override
	public Map<String,Object> updateUserInfo(UserInfoVO user) {
		if(user.getUiImg() != null) {
			File filed = new File(user.getUiImg().getOriginalFilename());
			String file = user.getUiImg().getOriginalFilename();
			String fileName = System.nanoTime() + file.substring(file.indexOf("."));
			
			ObjectMetadata omd = new ObjectMetadata();
			omd.setContentType(user.getUiImg().getContentType());
			omd.setContentLength(user.getUiImg().getSize());
			

			try {
				cc.getS3Client().putObject("spring-test-haley", "test/"+fileName, user.getUiImg().getInputStream(), omd);
			} catch (SdkClientException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setUiImgname(fileName);
			user.setUiImg(null);   
		}
		if(user.getUiPwd().length() < 5) {
			user.setUiPwd(null);
		}
		int cnt = uiMapper.updateUserInfo(user);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("msg", "업데이트 실패");
		if(cnt==1) {
			rMap.put("msg", "업데이트 성공");
		}
		return rMap;
	}
	
	@Override
	public Map<String, Object> deleteUserInfo(UserInfoVO user) {
		int cnt = uiMapper.deleteUserInfo(user);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("msg", "삭제 실패");
		if(cnt!=0) {
			rMap.put("msg", "삭제 성공");
		}
		return rMap;
	}

	@Override
	public Map<String, Object> deleteBkMark(UserInfoVO user) {
		int cnt = biMapper.deleteBkMark(user);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("msg", "삭제 실패");
		if(cnt!=0) {
			rMap.put("msg", "삭제 성공");
		}
		return rMap;
	}
}
