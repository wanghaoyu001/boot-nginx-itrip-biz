package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.po.Dto;
import com.po.ItripUser;
import com.po.ItripUserLinkUser;
import com.service.ItripUserInfoService;
import com.util.DtoUtil;
import com.util.EmptyUtil;
import com.util.vo.ItripAddUserLinkUserVO;

import com.util.vo.ItripModifyUserLinkUserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@RequestMapping(value = "/api/userinfo")
public class ItripUserInfoController {
    @Autowired
    private ItripUserInfoService itripUserInfoService;

    public ItripUserInfoService getItripUserInfoService() {
        return itripUserInfoService;
    }

    public void setItripUserInfoService(ItripUserInfoService itripUserInfoService) {
        this.itripUserInfoService = itripUserInfoService;
    }
    /*jedis启动*/
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    //按id删除
    @RequestMapping(value = "/deluserlinkuser")
    private  Dto deluserlinkuser (HttpServletRequest request, HttpServletResponse response, Long[] ids){
        System.out.println("删除旅客信息");
        //从请求中获取当前用户token
        String token = request.getHeader("token");
        System.out.println(token);
        //获取redis中缓存的key值--token
        String strtoken=jedis.get(token);
        System.out.println(strtoken);
        //通过key值（pc-XXX）找到json的value转为字符
        JSONObject jsonobject=JSONObject.parseObject(strtoken.toString());
        //将jsonobject字符串转化为user对象
        ItripUser currentUser=(ItripUser) JSONObject.toJavaObject(jsonobject,ItripUser.class);
        System.out.println(currentUser.toString());
        List<Long>idlist=new ArrayList<>();
        if (currentUser!=null&&EmptyUtil.isNotEmpty(ids)){
            try {
                Collections.addAll(idlist,ids);
                for(int i=0;i<idlist.size();i++){
                    System.out.println("删除");
                    System.out.println("idlist==="+idlist.get(i));
                    itripUserInfoService.delete(idlist.get(i));
                }
            }catch (Exception e){
                return DtoUtil.returnFail("删除联系人失败", "100411");
            }
            return DtoUtil.returnSuccess("删除成功");
        }return DtoUtil.returnFail("用户身份过期，请重新登录", "100411");
    }

    //按用户名查找
    @RequestMapping(value = "/queryuserlinkuser")
    private Dto find(HttpServletRequest request, HttpServletResponse response,@RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVO){
        System.out.println("按旅客姓名查找");
        //从请求中获取当前用户token
        String token = request.getHeader("token");
        System.out.println(token);
        //获取redis中缓存的key值--token
        String strtoken=jedis.get(token);
        System.out.println(strtoken);
        //通过key值（pc-XXX）找到json的value转为字符
        JSONObject jsonobject=JSONObject.parseObject(strtoken.toString());
        //将jsonobject字符串转化为user对象
        ItripUser currentUser=(ItripUser) JSONObject.toJavaObject(jsonobject,ItripUser.class);
        System.out.println(currentUser.toString());
        if (currentUser!=null&&itripAddUserLinkUserVO!=null){
            try {
                List<ItripUserLinkUser> linkUserList;
                Map param=new HashMap();
                param.put("userId", currentUser.getId());
                param.put("linkUserName",itripAddUserLinkUserVO.getLinkUserName());
                linkUserList=itripUserInfoService.find(param);
                for (int i=0;i<linkUserList.size();i++){
                    System.out.println("常用联系人:"+linkUserList.get(i).toString());

                }
                return DtoUtil.returnSuccess("查询成功",linkUserList);
            }catch (Exception e){
                return DtoUtil.returnFail("获取联系人失败", "100411");
            }
        }return DtoUtil.returnFail("获取联系人失败", "100411");
    }

    @RequestMapping(value = "/adduserlinkuser")
    private Dto adduserlinkuser(HttpServletRequest request, HttpServletResponse response, @RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVO) {
        System.out.println("新增常用联系人");
        //从请求中获取当前用户token
        String token = request.getHeader("token");
        System.out.println(token);
        //获取redis中缓存的key值--token
        String strtoken=jedis.get(token);
        System.out.println(strtoken);
        //通过key值（pc-XXX）找到json的value转为字符
        JSONObject jsonobject=JSONObject.parseObject(strtoken.toString());
        //将jsonobject字符串转化为user对象
        ItripUser currentUser=(ItripUser) JSONObject.toJavaObject(jsonobject,ItripUser.class);
        System.out.println(currentUser.toString());
        //用户对象不为空，注入数据
        if (currentUser!=null&&itripAddUserLinkUserVO!=null){
            System.out.println(itripAddUserLinkUserVO.toString());
            ItripUserLinkUser UserLinkUser=new ItripUserLinkUser();
            UserLinkUser.setLinkUserName(itripAddUserLinkUserVO.getLinkUserName());
            UserLinkUser.setLinkIdCardType(itripAddUserLinkUserVO.getLinkIdCardType());
            UserLinkUser.setLinkIdCard(itripAddUserLinkUserVO.getLinkIdCard());
            UserLinkUser.setLinkPhone(itripAddUserLinkUserVO.getLinkPhone());
            UserLinkUser.setUserId(currentUser.getId());
            UserLinkUser.setCreatedBy(currentUser.getId());
            UserLinkUser.setCreationDate(new Date(System.currentTimeMillis()));
            try {
                System.out.println("新增联系人返回数据："+UserLinkUser.toString());
                itripUserInfoService.insert(UserLinkUser);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("新增常用联系人失败", "100411");
            }
            return DtoUtil.returnSuccess("新增常用联系人成功");
        } else if (null != currentUser && null == itripAddUserLinkUserVO) {
            return DtoUtil.returnFail("不能提交空，请填写常用联系人信息", "100412");
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    //更新联系人
    @RequestMapping(value = "/modifyuserlinkuser")
    private Dto modifyuserlinkuser(HttpServletRequest request, HttpServletResponse response, @RequestBody ItripModifyUserLinkUserVO itripModifyUserLinkUserVO) {
        System.out.println("更新联系人");
        //从请求中获取当前用户token
        String token = request.getHeader("token");
        System.out.println(token);
        //获取redis中缓存的key值--token
        String strtoken=jedis.get(token);
        System.out.println(strtoken);
        //通过key值（pc-XXX）找到json的value转为字符
        JSONObject jsonobject=JSONObject.parseObject(strtoken.toString());
        //将jsonobject字符串转化为user对象
        ItripUser currentUser=(ItripUser) JSONObject.toJavaObject(jsonobject,ItripUser.class);
        System.out.println(currentUser.toString());
        //用户对象不为空，注入数据
        if (currentUser!=null&&itripModifyUserLinkUserVO!=null){
            System.out.println(itripModifyUserLinkUserVO.toString());
            ItripUserLinkUser UserLinkUser=new ItripUserLinkUser();
            UserLinkUser.setLinkUserName(itripModifyUserLinkUserVO.getLinkUserName());
            UserLinkUser.setLinkIdCardType(itripModifyUserLinkUserVO.getLinkIdCardType());
            UserLinkUser.setLinkIdCard(itripModifyUserLinkUserVO.getLinkIdCard());
            UserLinkUser.setLinkPhone(itripModifyUserLinkUserVO.getLinkPhone());
            UserLinkUser.setId(itripModifyUserLinkUserVO.getId());
            UserLinkUser.setUserId(currentUser.getId());
            UserLinkUser.setModifiedBy(currentUser.getId());
            UserLinkUser.setModifyDate(new Date(System.currentTimeMillis()));

            try {
                System.out.println("更新联系人返回数据："+UserLinkUser.toString());
                itripUserInfoService.update(UserLinkUser);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("更新常用联系人失败", "100411");
            }
            return DtoUtil.returnSuccess("更新常用联系人成功");
        } else if (null != currentUser && null == itripModifyUserLinkUserVO) {
            return DtoUtil.returnFail("不能提交空，请填写常用联系人信息", "100412");
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
}
