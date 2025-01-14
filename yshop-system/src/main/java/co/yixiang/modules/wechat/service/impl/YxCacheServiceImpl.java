package co.yixiang.modules.wechat.service.impl;

import co.yixiang.modules.wechat.service.dto.YxCacheDTO;
import co.yixiang.modules.wechat.service.dto.YxCacheQueryCriteria;
import co.yixiang.modules.wechat.domain.YxCache;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.modules.wechat.repository.YxCacheRepository;
import co.yixiang.modules.wechat.service.YxCacheService;
import co.yixiang.modules.wechat.service.mapper.YxCacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-06
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxCacheServiceImpl implements YxCacheService {

    @Autowired
    private YxCacheRepository yxCacheRepository;

    @Autowired
    private YxCacheMapper yxCacheMapper;

    @Override
    public Map<String,Object> queryAll(YxCacheQueryCriteria criteria, Pageable pageable){
        Page<YxCache> page = yxCacheRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxCacheMapper::toDto));
    }

    @Override
    public List<YxCacheDTO> queryAll(YxCacheQueryCriteria criteria){
        return yxCacheMapper.toDto(yxCacheRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxCacheDTO findById(String key) {
        Optional<YxCache> yxCache = yxCacheRepository.findById(key);
        ValidationUtil.isNull(yxCache,"YxCache","key",key);
        return yxCacheMapper.toDto(yxCache.get());
    }

    @Override
    public boolean isExist(String key) {
        Optional<YxCache> yxCache = yxCacheRepository.findById(key);
        if(!yxCache.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxCacheDTO create(YxCache resources) {
        //resources.setKey(IdUtil.simpleUUID());
        return yxCacheMapper.toDto(yxCacheRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxCache resources) {
        Optional<YxCache> optionalYxCache = yxCacheRepository.findById(resources.getKey());
        ValidationUtil.isNull( optionalYxCache,"YxCache","id",resources.getKey());
        YxCache yxCache = optionalYxCache.get();
        yxCache.copy(resources);
        yxCacheRepository.save(yxCache);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String key) {
        yxCacheRepository.deleteById(key);
    }
}