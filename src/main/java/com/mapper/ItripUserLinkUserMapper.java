package com.mapper;

import com.po.ItripUserLinkUser;

import java.util.List;
import java.util.Map;

public interface ItripUserLinkUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItripUserLinkUser record);

    int insertSelective(ItripUserLinkUser record);

    ItripUserLinkUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItripUserLinkUser record);

    int updateByPrimaryKey(ItripUserLinkUser record);

    List<ItripUserLinkUser> find(Map param);
}