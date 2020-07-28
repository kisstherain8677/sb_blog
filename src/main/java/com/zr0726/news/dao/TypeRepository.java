package com.zr0726.news.dao;

import com.zr0726.news.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
