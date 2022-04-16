package com.coconut.ds7.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/31 15:24
 * File: BugEntity
 * Project: dS7
 */

@Data
@TableName("bug")
public class BugEntity extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer reportUserId;
    private Integer gradeId;
    private String description;

    // 一对一
    /**
     * bug等级
     */
    private GradeEntity grade;

    // 一对多
    /**
     * bug关联等级
     */
    private List<BugVersionEntity> bugVersion;
}
