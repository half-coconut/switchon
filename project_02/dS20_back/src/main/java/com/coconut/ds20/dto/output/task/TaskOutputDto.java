package com.coconut.ds20.dto.output.task;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 22:55
 * File: TaskOutputDto
 * Project: dS20
 */

@Data
public class TaskOutputDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Boolean isArchive;
    private Boolean isJob;
    private Integer projectId;
    private Integer archiveId;
    private String archiveName;

    private List<Integer> moduleIds;
}
