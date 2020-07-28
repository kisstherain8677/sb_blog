package com.zr0726.news.service;

import com.zr0726.news.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    Page<Type> listType(Pageable pageable);

    Type saveType(Type type);

    Type getTypeByName(String name);

    void delete(Long id);

    Type getType(Long id);

    Type updateType(Long id,Type type);
}
