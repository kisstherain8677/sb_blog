package com.zr0726.news.service.impl;

import com.zr0726.news.dao.NewRepository;
import com.zr0726.news.po.News;
import com.zr0726.news.service.NewService;
import com.zr0726.news.vo.NewQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    private NewRepository newRepository;

    //新闻管理中的列表 包含了查询
    @Override
    public Page<News> listNew(Pageable pageable, NewQuery newQuery) {
        return newRepository.findAll(new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                if(!"".equals(newQuery.getTitle()) && newQuery.getTitle()!=null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+newQuery.getTitle()+"%"));
                }
                if(newQuery.getTypeId()!=null){
                    predicates.add(cb.equal(root.get("type").get("id"),newQuery.getTypeId()));
                }
                if(newQuery.isRecommend()){
                    predicates.add(cb.equal(root.get("recommand"),newQuery.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public News saveNews(News news) {
        if(news.getId()==null){
            news.setCreateTime(new Date());
            news.setUpdateTime(new Date());
        }
        return newRepository.save(news);
    }

    @Override
    public News getNew(Long id) {
        return newRepository.findById(id).orElse(null);
    }

    @Override
    public News updateNew(Long id, News news) {
        News news1 = newRepository.findById(id).orElse(null);
        if (news1 == null) {
            System.out.println("未获得对象");
        }
        BeanUtils.copyProperties(news, news1);
        news1.setUpdateTime(new Date());
        return newRepository.save(news1);
    }


}
