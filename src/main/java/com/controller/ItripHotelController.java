package com.controller;

import com.po.Dto;
import com.po.ItripAreaDic;
import com.service.ItripAreaDicService;
import com.util.DtoUtil;
import com.util.EmptyUtil;
import com.util.vo.ItripAreaDicVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/hotel")
public class ItripHotelController {
    @Autowired
    private ItripAreaDicService itripAreaDicService;

    public ItripAreaDicService getItripAreaDicService() {
        return itripAreaDicService;
    }

    public void setItripAreaDicService(ItripAreaDicService itripAreaDicService) {
        this.itripAreaDicService = itripAreaDicService;
    }

    //热门城市酒店查询
    @RequestMapping(value = "/queryhotcity/{type}")
    public Dto queryhotcity(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer type){
        System.out.println("热门城市酒店查询业务模块");
        //热门城市集合
        List<ItripAreaDic> itripAreaDicList=null;
        //返回到前台页面的热门城市数据集合
        List<ItripAreaDicVO> itripAreaDicVOS=null;
        if (EmptyUtil.isNotEmpty(type)){
            Map<String,Object> param=new HashMap<>();
            param.put("isChina",type);//确定国内外
            param.put("isHot",1);//确定热门城市
            try {System.out.println("0");
                itripAreaDicList=itripAreaDicService.getItripAreaDicListByMap(param);
                if (EmptyUtil.isNotEmpty(itripAreaDicList)){
                    itripAreaDicVOS=new ArrayList<>();
                    for(ItripAreaDic dic:itripAreaDicList){
                        ItripAreaDicVO vo=new ItripAreaDicVO();
                        //属性对象间赋值
                        BeanUtils.copyProperties(dic, vo);
                        itripAreaDicVOS.add(vo);
                    }
                }else{System.out.println("1");
                    return DtoUtil.returnFail("没有数据", "10202");}
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("2");
                return DtoUtil.returnFail("没有国内国外数据", "10202");
            }
        }else{
            System.out.println("3");
            return DtoUtil.returnFail("没有国内国外数据", "10202");
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOS);
    }
    //查询酒店特色列表
    @RequestMapping(value = "/queryhotelfeature")
    public Dto queryhotelfeature(){
        System.out.println("查询酒店特色模块方法");
        return null;
    }
}
