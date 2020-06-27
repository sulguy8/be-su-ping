package com.bdi.sb.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("test")
@Data
public class TestInfoVO {
	private Integer tstNum;
	
}
