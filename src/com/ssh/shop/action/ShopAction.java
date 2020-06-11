package com.ssh.shop.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.auth.domain.User;
import com.ssh.common.bean.CommonResult;
import com.ssh.shop.domain.Shop;
import com.ssh.shop.service.ShopService;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2020-05-18 18
 */
public class ShopAction extends ActionSupport {

    private Shop shop;

    private ShopService shopService;

    private User loginUser;

    //用于数据返回格式化
    private CommonResult result = null;
    private CommonResult authResult = CommonResult.authFail();
    //用于验证错误信息收集
    private List<String> error = new ArrayList<>();

    //当前页
    private Integer currentPage;
    //显示的页数
    private Integer pageSize;



    public String save(){
        //清空错误信息
        error.clear();
        if(shop.getDescription() == null  || shop.getDescription() == "")
            error.add("礼品描述不能为空");
        if(shop.getName() == null || shop.getName() == "")
            error.add("名称不能为空");
        if(shop.getIntegral() == null)
            error.add("所需积分不能为空");

        if(error.size() != 0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }

        result = shopService.save(shop);
        return SUCCESS;
    }


    public String saveShopOfUser(){
        error.clear();
        if(shop.getId() == null){
            error.add("礼品id不能为空");
            result = CommonResult.validateFail(error);
        }

        result = shopService.saveShopOfUser(loginUser.getId(),shop.getId());
        return SUCCESS;
    }

    public String deleteByShopId(){
        error.clear();
        if(loginUser.getRoleId() != 1){
                return "authFail";
        }

        if(shop.getId() == null){
            error.add("礼品id不能为空");
            result = CommonResult.validateFail(error);
        }
        result = shopService.deleteByShopId(shop.getId());
        return SUCCESS;
    }


    public String update(){
        //清空错误信息
        error.clear();
        if(shop.getId() == null )
            error.add("礼品id不能为空");
        if(shop.getDescription() == null  || shop.getDescription() == "")
            error.add("礼品描述不能为空");
        if(shop.getName() == null || shop.getName() == "")
            error.add("名称不能为空");
        if(shop.getIntegral() == null)
            error.add("所需积分不能为空");

        //表单验证失败
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = shopService.update(shop);
        return  SUCCESS;
    }



    public String getAllShop(){
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = shopService.getAll(currentPage,pageSize);
        return SUCCESS;
    }

    public String getShopOfUser(){
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result  = shopService.getShopByUserId(currentPage,pageSize,loginUser.getId());
        return SUCCESS;
    }

    public String getShopByShopId(){
        error.clear();
        if(shop.getId() == null )
            error.add("礼品id不能为空");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = shopService.getShopByShopId(shop.getId());
        return SUCCESS;
    }





    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public CommonResult getResult() {
        return result;
    }

    public void setResult(CommonResult result) {
        this.result = result;
    }

    public CommonResult getAuthResult() {
        return authResult;
    }

    public void setAuthResult(CommonResult authResult) {
        this.authResult = authResult;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
