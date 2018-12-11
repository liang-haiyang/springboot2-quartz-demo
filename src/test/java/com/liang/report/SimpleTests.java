package com.liang.report;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author lianghaiyang
 * @date 2018/10/31
 */
public class SimpleTests {
    @Test
    public void test123() {
        String longitude = "";
        String latitude = "";
        String locatedProvince = "123123";
        if (StringUtils.isEmpty(longitude) && StringUtils.isEmpty(latitude)) {
            if (StringUtils.isEmpty(locatedProvince)) {
                System.out.println(1111111111);
            }
        }
        if (StringUtils.isNotEmpty(locatedProvince)) {
            if (StringUtils.isEmpty(longitude) || StringUtils.isEmpty(latitude)) {
                System.out.println(222222222);
            }
        }
    }
}
