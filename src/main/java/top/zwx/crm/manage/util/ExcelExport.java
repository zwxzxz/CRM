package top.zwx.crm.manage.util;

import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import top.zwx.crm.manage.entity.CluePool;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zwx
 */
public class ExcelExport {
    public static void export(List<Entity> customerList) {
        List<CluePool> cluePools = new ArrayList<>();
        for (Entity entity : customerList) {
            CluePool cluePool = new CluePool();
            cluePool.setId(entity.getLong("id"));
            cluePool.setName(entity.getStr("name"));
            cluePool.setCompany(entity.getStr("company"));
            cluePool.setSource(entity.getStr("source"));
            cluePool.setDetailed(entity.getStr("detailed"));
            cluePool.setPool((String) entity.get("pool"));
            cluePool.setCreatetime(entity.getDate("createtime").toString());
            cluePools.add(cluePool);
        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\25748\\Desktop\\cluePools.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "线索池信息表");
        // 一次性写出内容，使用默认样式
        writer.write(cluePools);
        // 关闭writer，释放内存
        writer.close();
    }
}
