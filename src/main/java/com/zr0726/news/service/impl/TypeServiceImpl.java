package com.zr0726.news.service.impl;

import com.zr0726.news.dao.TypeRepository;
import com.zr0726.news.po.Type;
import com.zr0726.news.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type type1=typeRepository.findById(id).orElse(null);
        if(type1==null){
            System.out.println("未获得对象");
            return null;
        }
        BeanUtils.copyProperties(type,type1);
        return typeRepository.save(type1);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }
}



