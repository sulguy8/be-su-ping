package com.bdi.sb.vo;

import java.util.Map;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("bs")
@Data
public class BkmarkStatsVO {
	private Integer viNum;
	private Integer bkNum;
	private Integer uiNum;
	private String viName;
	private String viImg;
}
