package top.zwx.crm.manage.util;

import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import top.zwx.crm.manage.entity.Customer;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zwx
 */
public class ExcelExport {
    public static void export(List<Entity> customerList) {
        List<Customer> customers = new ArrayList<>();
        for (Entity entity : customerList) {
            Customer customer = new Customer();
            customer.setId(entity.getLong("id"));
            customer.setName(entity.getStr("name"));
            customer.setCompany(entity.getStr("company"));
            customer.setSource(entity.getStr("source"));
            customer.setDetailed(entity.getStr("detailed"));
            customer.setPool((String) entity.get("pool"));
            customer.setCreatetime(entity.getDate("createtime").toString());
            customers.add(customer);
        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\25748\\Desktop\\customers.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "线索池信息表");
        // 一次性写出内容，使用默认样式
        writer.write(customers);
        // 关闭writer，释放内存
        writer.close();
    }
}
