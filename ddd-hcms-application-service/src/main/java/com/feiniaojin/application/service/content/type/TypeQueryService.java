package com.feiniaojin.application.service.content.type;

import com.feiniaojin.application.service.content.type.dto.TypeView;
import com.feiniaojin.application.service.content.typefield.TypeFieldQueryService;
import com.feiniaojin.application.service.content.typefield.dto.TypeFiledView;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
import com.feiniaojin.ddd.hcms.domain.vo.PageQuery;
import com.feiniaojin.ddd.hcms.domain.vo.PageVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CQRS 调用mapper完成数据查询
 *
 * @author qinyujie
 */
@Service
public class TypeQueryService {

    @Resource
    private TypeEntityRepository repository;

    @Resource
    private TypeFieldQueryService typeFieldQueryService;

    public TypeView findOne(String id) {
        TypeEntity typeEntity = repository.load(new TypeId(id));
        List<TypeFiledView> typeFiledViewList = typeFieldQueryService.findList(typeEntity.getTypeId().getValue());

        TypeView typeView = this.translate(typeEntity);
        typeView.setTypeFiledList(typeFiledViewList);

        return typeView;
    }

    private TypeView translate(TypeEntity typeEntity) {

        TypeView typeView = new TypeView();
        typeView.setTypeEntityId(typeEntity.getTypeId().getValue());
        typeView.setDisplayName(typeEntity.getDisplayName());
        return typeView;
    }

    private List<TypeView> translate(List<TypeEntity> typeEntityList) {
        return typeEntityList.stream().map(this::translate).collect(Collectors.toList());
    }

    public List<TypeView> findList() {
        List<TypeEntity> typeEntityList = repository.loadList();
        return translate(typeEntityList);
    }

    public PageVo<TypeView> findByPage(PageQuery pageQuery) {
        PageVo<TypeEntity> pageVo = repository.findByPage(pageQuery.getPageIndex(),pageQuery.getPageSize());

        PageVo<TypeView> result = new PageVo<>();
        result.setPageIndex(pageQuery.getPageIndex());
        result.setPageSize(pageQuery.getPageSize());
        result.setTotal(pageVo.getTotal());
        result.setData(translate(pageVo.getData()));

        return result;
    }
}
