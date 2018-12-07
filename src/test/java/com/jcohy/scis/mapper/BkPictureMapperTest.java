package com.jcohy.scis.mapper;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkProductPictureReq;
import com.jcohy.scis.service.PictureService;
import com.jcohy.scis.utils.JsonUtil;
import com.sun.glass.ui.Application;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Created by Bryant on 2018.12.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class BkPictureMapperTest extends TestCase {

    @Autowired
    PictureService pictureService;

    @Test
    public void selectByCondition() throws Exception {
        BkProductPictureReq bkProductPictureReq = new BkProductPictureReq();
        bkProductPictureReq.setPageNum(1);
        bkProductPictureReq.setPageSize(2);
        PageResponse list = pictureService.queryByCondition(bkProductPictureReq);
        System.out.println(JsonUtil.obj2String(list));
    }

    @Test
    public void insertOrUpdate() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

}