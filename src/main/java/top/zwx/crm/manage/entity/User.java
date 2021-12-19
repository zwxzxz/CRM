package top.zwx.crm.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * User实体类
 *
 * @author zwx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String mobile;
    private String email;
    private String password;
    private String username;
    private String avatar;
    private String identity;
    private String department;
    private LocalDate createDate;

}
