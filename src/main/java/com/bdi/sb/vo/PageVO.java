package com.bdi.sb.vo;

import lombok.Data;

@Data
public class PageVO {

	private Integer page=1;
	private Integer startNum=0;
	private Integer totalCount;
	private Integer pageSize;
}
