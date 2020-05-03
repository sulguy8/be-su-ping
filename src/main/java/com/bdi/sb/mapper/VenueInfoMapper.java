package com.bdi.sb.mapper;

import java.util.List;

import com.bdi.sb.vo.VenueInfoVO;

public interface VenueInfoMapper {

	public List<VenueInfoVO> selectVenueInfoList(VenueInfoVO venue);
	public VenueInfoVO selectVenueInfo(VenueInfoVO venue);
	public VenueInfoVO selectVenueInfoForValidationCheck(VenueInfoVO venue);//고객이 주문할때 해당 가맹점이 영업중인지 확인하기 위한 메서드
	public VenueInfoVO selectVenueInfoForLogin(VenueInfoVO venue);
	public int totalVenueInfoCount(VenueInfoVO venue);
	public int updateVenueInfo(VenueInfoVO venue);
	public int updateVenueOpeningStatus(VenueInfoVO venue);
	public int deleteVenueInfo(VenueInfoVO venue);
	public int insertVenueInfo(VenueInfoVO venue);
}
