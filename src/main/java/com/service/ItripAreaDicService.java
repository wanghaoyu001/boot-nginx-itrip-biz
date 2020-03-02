package com.service;

import com.po.ItripAreaDic;

import java.util.List;
import java.util.Map;

public interface ItripAreaDicService {
    //热门城市查询
    public List<ItripAreaDic>getItripAreaDicListByMap(Map<String,Object> param)throws Exception;
}
