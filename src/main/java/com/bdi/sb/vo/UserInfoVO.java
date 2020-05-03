package com.bdi.sb.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("user")
@Data
public class UserInfoVO {
	//체크박스 삭제여러개시 사용
	private Integer[] uiNums;
	
	private Integer uiNum;
	private String uiId;
	private String uiPwd;
	private String uiName;
	private String uiEmail;
	private String uiTel;
	
	private String uiAddr;
	private List<String> uiBkmark;
	
	private MultipartFile uiImg;
	private String uiImgname;
	
	private Integer bkNum;
	
	//검색옵션
	private String searchOpt;
	private String searchVal;
	//페이징
	private PageVO page = new PageVO();
}
