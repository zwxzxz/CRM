package top.zwx.crm.manage.util;

import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import top.zwx.crm.manage.entity.Clue;
import top.zwx.crm.manage.entity.CluePool;
import top.zwx.crm.manage.entity.Consumer;
import top.zwx.crm.manage.entity.HighSeasPool;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zwx
 */
public class ExcelExport {
    public static void export(List<Entity> cluePoolList) {
        List<CluePool> cluePools = new ArrayList<>();
        for (Entity entity : cluePoolList) {
            CluePool cluePool = new CluePool();
            cluePool.setId(entity.getLong("id"));
            cluePool.setName(entity.getStr("name"));
            cluePool.setCompany(entity.getStr("company"));
            cluePool.setSource(entity.getStr("source"));
            cluePool.setDetailed(entity.getStr("detailed"));
            cluePool.setPool((String) entity.get("pool"));
            cluePool.setCreatetime(entity.getDate("createtime").toString());
            cluePool.setCategory(entity.getLong("category"));
            cluePools.add(cluePool);

        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\25748\\Desktop\\CluePools.xlsx");

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(8, "线索池信息表");
        // 一次性写出内容，使用默认样式
        writer.write(cluePools);
        // 关闭writer，释放内存
        writer.close();
    }


    public static void export1(List<Entity> clueList) {
        List<Clue> clues = new ArrayList<>();
        for (Entity entity : clueList) {
            Clue clue = new Clue();
            clue.setId(entity.getLong("id"));
            clue.setName(entity.getStr("name"));
            clue.setCompany(entity.getStr("company"));
            clue.setSource(entity.getStr("source"));
            clue.setDetailed(entity.getStr("detailed"));
            clue.setPool((String) entity.get("pool"));
            clue.setCreatetime(entity.getDate("createtime").toString());
            clue.setCategory(entity.getLong("category"));
            clues.add(clue);

        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\25748\\Desktop\\Clue.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(8, "线索池信息表");
        // 一次性写出内容，使用默认样式
        writer.write(clues);
        // 关闭writer，释放内存
        writer.close();
    }

    public static void export2(List<Entity> highSeasPoolList) {
        List<HighSeasPool> highSeasPools = new ArrayList<>();
        for (Entity entity : highSeasPoolList) {
            HighSeasPool highSeasPool = new HighSeasPool();
            highSeasPool.setId(entity.getLong("id"));
            highSeasPool.setName(entity.getStr("name"));
            highSeasPool.setGrade(entity.getStr("grade"));
            highSeasPool.setSource(entity.getStr("source"));
            highSeasPool.setCreatetime(entity.getDate("createtime").toString());
            highSeasPool.setCompany(entity.getStr("company"));
            highSeasPool.setDetails(entity.getStr("details"));
            highSeasPool.setPool((String) entity.get("pool"));
            highSeasPool.setSourceid(entity.getLong("sourceid"));
            highSeasPool.setGradeid(entity.getLong("gradeid"));
            highSeasPool.setPoolid(entity.getLong("poolid"));
            highSeasPools.add(highSeasPool);

        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\25748\\Desktop\\HighSeasPool.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(11, "公海池信息表");
        // 一次性写出内容，使用默认样式
        writer.write(highSeasPools);
        // 关闭writer，释放内存
        writer.close();
    }

    public static void export3(List<Entity> consumerList) {
        List<Consumer> consumers = new ArrayList<>();
        for (Entity entity : consumerList) {
            Consumer consumer = new Consumer();
            consumer.setId(entity.getLong("id"));
            consumer.setName(entity.getStr("name"));
            consumer.setGrade(entity.getStr("grade"));
            consumer.setSource(entity.getStr("source"));
            consumer.setCreatetime(entity.getDate("createtime").toString());
            consumer.setCompany(entity.getStr("company"));
            consumer.setDetails(entity.getStr("details"));
            consumer.setPool((String) entity.get("pool"));
            consumer.setSourceid(entity.getLong("sourceid"));
            consumer.setGradeid(entity.getLong("gradeid"));
            consumer.setPoolid(entity.getLong("poolid"));
            consumers.add(consumer);

        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\25748\\Desktop\\Consumer.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(11, "客户信息表");
        // 一次性写出内容，使用默认样式
        writer.write(consumers);
        // 关闭writer，释放内存
        writer.close();
    }
}
