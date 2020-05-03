package com.bdi.sb.mapper;

import java.util.List;

import com.bdi.sb.vo.BkmarkStatsVO;
import com.bdi.sb.vo.UserInfoVO;

	public interface BkmarkInfoMapper {
		public List<BkmarkStatsVO> selectBkmarkName(BkmarkStatsVO bs);
		public BkmarkStatsVO selectBkvenue(BkmarkStatsVO bs);
		public int deleteBkMark(UserInfoVO user);
	}

	
