package com.liang.report;

import com.bonc.report.business.mapper.*;
import com.liang.report.business.model.DeviceBusProtocol;
import com.liang.report.business.mapper.*;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataReportApplicationTests {

	@Resource
	private DeviceBusProtocolService deviceBusProtocolService;
	@Resource
	private DeviceDataMapper deviceDataMapper;
	@Resource
	private IndustryDeviceCategoryMapper industryDeviceCategoryMapper;
	@Resource
	private PlatformInfoMapper platformInfoMapper;
	@Resource
	private PlatformLinkedEnterpriseMapper platformLinkedEnterpriseMapper;
	@Resource
	private PlatformSummaryInfoMapper platformSummaryInfoMapper;
	@Test
	public void deviceBusProtocolTest() {
		DeviceBusProtocol protocol = new DeviceBusProtocol();
		protocol.setEnterpriseNum("112312312312312");
		protocol.setTableNum("12312312312331");
		protocol.setIndustry("钢铁");
		protocol.setSupportedBusTypes("MOD BUS");
		protocol.setSupportingBusTypes("MQTT");
		DeviceBusProtocol protocol1 = new DeviceBusProtocol();
		protocol1.setEnterpriseNum("22222222222222222");
		protocol1.setTableNum("22222222222222");
		protocol1.setIndustry("2钢铁");
		protocol1.setSupportedBusTypes("2MOD BUS");
		protocol1.setSupportingBusTypes("2MQTT");
		DeviceBusProtocol protocol2 = new DeviceBusProtocol();
		protocol2.setEnterpriseNum("333333333333");
		protocol2.setTableNum("33333333333");
		protocol2.setIndustry("3钢铁");
		protocol2.setSupportedBusTypes("3MOD BUS");
		protocol2.setSupportingBusTypes("3MQTT");
		deviceBusProtocolService.insertAndSendDeviceBusProtocol(protocol);
		List<DeviceBusProtocol> list = new ArrayList<>();
		list.add(protocol1);
		list.add(protocol2);
		deviceBusProtocolService.bulkInsertAndSendDeviceBusProtocol(list);
	}


	@Autowired
	StringEncryptor encryptor;
	@Test
	public void getPass() {
		String name = encryptor.encrypt("org.apache.kafka.common.security.plain.PlainLoginModule required username=\"ckafka-7cnc9pvr#iiot2018_cspiii\" password=\"iiot_tencent2018\";");
		String password = encryptor.encrypt("DBpassword");
		String s1 = encryptor.encrypt("admin");
		String s2 = encryptor.encrypt("123456");
		System.err.println(name+"}}}}");
		System.err.println(password+"----------------");
		System.err.println(s1+"/----------------");
		System.err.println(s2+"/----------------");
		Assert.assertTrue(name.length() > 0);
		Assert.assertTrue(password.length() > 0);
	}

}
