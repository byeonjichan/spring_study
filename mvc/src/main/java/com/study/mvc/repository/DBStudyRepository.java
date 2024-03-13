package com.study.mvc.repository;

import com.study.mvc.entity.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DBStudyRepository {
    public int save(Study study);
    public Study findStudyById(int id);
    public Study findStudyByName(String name);
    public List<Study> findAll();
    public int deleteById(int id);
    public int putById(Study study);
    public int patchById(Study study);
}











