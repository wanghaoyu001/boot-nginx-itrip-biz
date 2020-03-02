package com.service;

import com.po.ItripUserLinkUser;
import java.util.List;
import java.util.Map;


public interface ItripUserInfoService {
    //添加用户信息
    public int insert(ItripUserLinkUser linkUser)throws Exception;
    //查询
    public List<ItripUserLinkUser> find(Map param) throws Exception;
    //删除
    public int delete(Long id) throws Exception;
    //修改
    public int update(ItripUserLinkUser linkUser) throws Exception;
}
