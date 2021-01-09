package com.fiberhome.management.langmenu.mappers;

import com.fiberhome.management.langmenu.entity.SysMenuLanguage;
import com.fiberhome.management.langmenu.entity.SysMenuLanguageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuLanguageMapper {
    long countByExample(SysMenuLanguageExample example);

    int deleteByExample(SysMenuLanguageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMenuLanguage record);

    int insertSelective(SysMenuLanguage record);

    List<SysMenuLanguage> selectByExample(SysMenuLanguageExample example);

    SysMenuLanguage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMenuLanguage record, @Param("example") SysMenuLanguageExample example);

    int updateByExample(@Param("record") SysMenuLanguage record, @Param("example") SysMenuLanguageExample example);

    int updateByPrimaryKeySelective(SysMenuLanguage record);

    int updateByPrimaryKey(SysMenuLanguage record);
}