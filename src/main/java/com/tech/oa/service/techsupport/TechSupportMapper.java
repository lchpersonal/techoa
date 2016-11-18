package com.tech.oa.service.techsupport;

import com.baitian.fourb.common.mybatis.FourbMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by chengli on 2016/11/17.
 */
@FourbMapper(db = "oa_test")
interface TechSupportMapper {

    @Insert("insert into techsupport(applyUserId,content)values(#{applyUserId},#{content})")
    @Options(useGeneratedKeys = true)
    int add(ApplyTechSupportParam param);

    /** 评分 */
    @Update("update techsupport set mark = #{mark} where id = #{id}")
    void mark(@Param("id") int id, @Param("mark") int mark);

    /** 修改申请 */
    @Update("update techsupport set content = #{content} where id = #{id}")
    void modify(@Param("id") int id, @Param("content") String content);

    @Update("update techsupport set processId = #{processId} where id = #{id}")
    void updateProcessId(@Param("id") int id, @Param("processId") String processId);

    @Update("update techsupport set signUserId = #{signUserId}, signResult=#{signResult} where id = #{id}")
    void handleNewApplyTask(@Param("id") int id, @Param("signUserId") String signUserId, @Param("signResult") int signResult);

    @SelectProvider(type = TechSupportSqlProvider.class, method = "genSelectSupportSql")
    List<TechSupport> queryByProcessIds(@Param("list") List<String> processIds);

}
