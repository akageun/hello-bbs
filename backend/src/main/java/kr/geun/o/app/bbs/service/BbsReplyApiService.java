package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsReplyEntity;
import kr.geun.o.app.bbs.repository.BbsReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author akageun
 */
@Slf4j
@Service
public class BbsReplyApiService {

    @Autowired
    private BbsReplyRepository bbsReplyRepository;

    public Page<BbsReplyEntity> page(Pageable pageable) {
        return bbsReplyRepository.findAll(pageable);
    }

    public Optional<BbsReplyEntity> get(Long articleId) {
        return bbsReplyRepository.findById(articleId);
    }


}
