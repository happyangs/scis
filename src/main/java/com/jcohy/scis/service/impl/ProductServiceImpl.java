package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.common.interfaces.IsDeleteEnum;
import com.jcohy.scis.common.interfaces.IsSwitchEnum;
import com.jcohy.scis.mapper.BkConfigMapper;
import com.jcohy.scis.mapper.BkProductMapper;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkConfigReq;
import com.jcohy.scis.model.BkProductReq;
import com.jcohy.scis.model.BkProductVo;
import com.jcohy.scis.service.ProductService;
import com.jcohy.scis.utils.EncryptDecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Bryant
 * @date 2018.12
 */
@Service
public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BkProductMapper bkProductMapper;

    @Autowired
    private BkConfigMapper bkConfigMapper;

    @Override
    public PageResponse queryByCondition(BkProductReq bkProductReq) {
        List<BkProductVo> list = bkProductMapper.selectByCondition(bkProductReq);
        PageHelper.startPage(bkProductReq.getPageNum() , bkProductReq.getPageSize());
        PageInfo<BkProductVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        if (!CollectionUtils.isEmpty(list)){
            BkConfigReq req = new BkConfigReq();
            req.setConfigType(ConfigTypeEnum.PRODUCT_TYPE.getCode());
            List<BkConfig> bkConfigs = bkConfigMapper.queryConfig(req);

            BkConfigReq themereq = new BkConfigReq();
            themereq.setConfigType(ConfigTypeEnum.PRODUCT_THEME.getCode());
            List<BkConfig> themeConfigs = bkConfigMapper.queryConfig(themereq);

            Map<Integer,String> productTypeMap = bkConfigs.stream().filter(bkConfig -> !StringUtils.isEmpty(bkConfig.getZhName())).collect(Collectors.toMap(BkConfig::getCode,BkConfig::getZhName));
            Map<Integer,String> productThemeMap = themeConfigs.stream().filter(bkConfig -> !StringUtils.isEmpty(bkConfig.getZhName())).collect(Collectors.toMap(BkConfig::getCode,BkConfig::getZhName));
            list.forEach(bkProductVo -> {
                bkProductVo.setIsSwitch(IsSwitchEnum.code2desc(bkProductVo.getIsDelete()));
                bkProductVo.setProductTypeName(productTypeMap.get(bkProductVo.getProductType()));
                bkProductVo.setProductTheme(productThemeMap.get(Integer.valueOf(bkProductVo.getProductTheme())));
                if (!StringUtils.isEmpty(bkProductVo.getLinkCode())){
                    bkProductVo.setLinkCode(EncryptDecrypt.decryptStringFromHex(bkProductVo.getLinkCode()));
                }
            });
        }

        return PageResponse.buildSuccessResponseWithResult(list,total.intValue());
    }

    /**
     * 查询商品详情
     * @param bkProductReq
     * @return
     */
    @Override
    public List<BkProductVo> queryList(BkProductReq bkProductReq) {
        List<BkProductVo> list = bkProductMapper.selectByCondition(bkProductReq);
        return list;
    }

    @Override
    public JsonResult insert(BkProductReq bkProductReq) {
        if (bkProductReq != null){
            if (bkProductReq.getIsDelete() == null){
                bkProductReq.setIsDelete(IsDeleteEnum.NORMAL_DELETE.getCode());
            }
            String linkCode = bkProductReq.getLinkCode();
            if (!StringUtils.isEmpty(linkCode)){
                bkProductReq.setLinkCode(EncryptDecrypt.encryptStringToHex(linkCode));
            }
            Integer id = bkProductReq.getId();
            if(id == null){
                bkProductReq.setProductId(this.getOrderId());
                bkProductReq.setAddTime(new Date());
                bkProductMapper.insert(bkProductReq);
            }else {
                bkProductMapper.update(bkProductReq);
            }
        }
        return JsonResult.ok();
    }

    @Override
    public JsonResult delete(Integer id) {
        bkProductMapper.deleteById(id);
        return JsonResult.ok();
    }

    @Override
    public BkProductVo queryById(Integer id) {
        BkProductVo bkProductVo = new BkProductVo();
        BkProductReq bkProductReq = new BkProductReq();
        bkProductReq.setId(id);
        List<BkProductVo> list = bkProductMapper.selectByCondition(bkProductReq);
        if (!CollectionUtils.isEmpty(list)){
            bkProductVo = list.get(0);
        }
        return bkProductVo;
    }

    private Integer getOrderId(){
        Integer productId = 10000;
        try {
            BkProductVo bkProductVo = bkProductMapper.getMaxProductId();
            if (bkProductVo != null){
                productId = bkProductVo.getProductId() + 1;
            }
        } catch (Exception e) {
            logger.error("getOrderId error",e);
        }
        return productId.intValue();
    }
}
