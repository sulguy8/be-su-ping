package com.bdi.sb.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("venue")
@Data
public class VenueInfoVO {
	//체크박스 삭제여러개시 사용
	private Integer[] viNums;
	
	private Integer viCode;
	private Integer viNum;
	private String viPwd;
	private String viName;
	private String viAddr;
	private String viTel;
	private String viImg;
	private MultipartFile viImgFile;
	private String viIntro;
	private String viBusinessdays;
	private String viBusinesshours;
	private String viStatus;
	
	//좌표 x = latitude , y = longitute
	private double viLongitude;
	private double viLatitude;
	
	//검색옵션
	private String searchOpt;
	private String searchVal;
	
	//페이징
	private PageVO page = new PageVO();
}
