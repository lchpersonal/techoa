package com.tech.oa.service.techsupport;

import com.baitian.fourb.common.utils.FourbUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * Created by chengli on 2016/11/17.
 */
public class TechSupportSqlProvider {

    public static String genSelectSupportSql(Map<String, Object> map) {
        List<String> ids = (List<String>) map.get("list");
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("techsupport");
        sql.WHERE(String.format("%s in(%s)", "processId", genInClause(ids)));
        return sql.toString();
    }

    public static String genInClause(List<?> ids) {
        if (FourbUtil.collectionEmpty(ids)) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("#{list[");
            sb.append(i);
            sb.append("]}");
        }
        return sb.toString();
    }
}
