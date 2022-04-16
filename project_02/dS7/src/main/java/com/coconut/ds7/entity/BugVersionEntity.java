package com.coconut.ds7.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/31 15:32
 * File: BugVersionEntity
 * Project: dS7
 */

/**
 * Bug-版本关系实体类
 */
@Data
@TableName("bug_version")
public class BugVersionEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * bug ID
     */
    private Integer bugId;
    /**
     * 版本 ID
     */
    private Integer versionId;
}
