package com.service.Impl;

import com.mapper.ItripUserLinkUserMapper;
import com.po.ItripUserLinkUser;
import com.service.ItripUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItripUserInfoServiceImpl implements ItripUserInfoService {
    @Autowired
    private ItripUserLinkUserMapper itripUserLinkUserMapper;

    public ItripUserLinkUserMapper getItripUserLinkUserMapper() {
        return itripUserLinkUserMapper;
    }

    public void setItripUserLinkUserMapper(ItripUserLinkUserMapper itripUserLinkUserMapper) {
        this.itripUserLinkUserMapper = itripUserLinkUserMapper;
    }


    @Override
    public int insert(ItripUserLinkUser linkUser) throws Exception {
        return itripUserLinkUserMapper.insert(linkUser);
    }

    @Override
    public List<ItripUserLinkUser> find(Map param) throws Exception {
        return itripUserLinkUserMapper.find(param);
    }


    @Override
    public int delete(Long id) throws Exception {
        return itripUserLinkUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(ItripUserLinkUser linkUser) throws Exception {
        return itripUserLinkUserMapper.updateByPrimaryKey(linkUser);
    }
}
