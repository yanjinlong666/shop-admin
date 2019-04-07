package com.fh.shop.backend.biz.log;

import com.fh.shop.backend.biz.log.ILogService;
import com.fh.shop.backend.mapper.log.ILogMapper;
import com.fh.shop.backend.po.log.LogInfo;
import com.fh.shop.backend.mapper.log.ILogMapper;
import com.fh.shop.backend.po.log.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class ILogServiceImpl implements ILogService {

    @Autowired
    private ILogMapper logMapper;

    @Override
    public void addLog(LogInfo logInfo) {
        logMapper.addLog(logInfo);
    }
}
