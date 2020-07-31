package com.zr0726.news.service;

import com.zr0726.news.po.News;
import com.zr0726.news.vo.NewQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewService {
    //新闻管理界面，组合条件查询新闻列表
    Page<News> listNew(Pageable pageable, NewQuery newQuery);

    News saveNews(News news);

    News getNew(Long id);

    News updateNew(Long id,News news);

    void deleteNew(Long id);

    //主页显示新闻列表
    Page<News> listNew(Pageable pageable);

    //主页推荐最新新闻列表
    List<News> ListRecommendNewTop(Integer size);

    //全局搜索
    Page<News> listNew(String query,Pageable pageable);

    //
    News getAndConvert(Long id);

}
