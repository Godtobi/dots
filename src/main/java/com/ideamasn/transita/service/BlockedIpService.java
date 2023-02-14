package com.ideamasn.transita.service;

import com.ideamasn.transita.dao.BlockedIpRepository;
import com.ideamasn.transita.model.entity.BlockedIp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BlockedIpService {

    private final BlockedIpRepository blockedIpRepository;

    public BlockedIp createBlock(Long count,String ip,String comment){
        BlockedIp blockedIp = new BlockedIp();
        blockedIp.setComment(comment);
        blockedIp.setIp(ip);
        blockedIp.setRequestNumber(count);
        return blockedIpRepository.save(blockedIp);
    }

}



