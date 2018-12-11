package com.liang.report.business.mapper;

import com.liang.report.business.model.data.DeviceBusProtocolData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lianghaiyang
 * @date 2018/10/30
 */
@Mapper
public interface DeviceBusProtocolDataMapper {
    /**
     * 批量插入插入设备总线协议
     * @param deviceBusProtocols 设备总线协议数组
     */
    void bulkInsertDeviceBusProtocolData(List<DeviceBusProtocolData> deviceBusProtocols);
    /**
     * 获取指定日志的设备总线协议
     * @param sendTime 日期
     * @return 设备总线协议数据
     */
    List<DeviceBusProtocolData> selectDeviceBusProtocolData(@Param("sendTime") String sendTime);
}
