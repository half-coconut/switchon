package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.task.ChangeIsJobOrNotInputDto;
import com.coconut.ds20.dto.input.task.TaskCreateInputDto;
import com.coconut.ds20.dto.input.task.TaskRunInputDto;
import com.coconut.ds20.dto.input.task.TaskUpdateInputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.dto.output.task.TaskDetailOutputDto;
import com.coconut.ds20.dto.output.task.TaskOutputDto;
import com.coconut.ds20.dto.output.task.TaskQueryOutputDto;
import com.coconut.ds20.entity.TaskEntity;

import java.util.List;

public interface TaskService extends IService<TaskEntity> {
    ResponseData<List<TaskQueryOutputDto>> query(Integer pageIndex, Integer pageSize,String name,Integer projectId);
    ResponseData<List<TaskDetailOutputDto>> queryDetailByProjectId(Integer projectId);
    ResponseData<List<TaskOutputDto>> queryByProjectId(Integer projectId);
    ResponseData<TaskOutputDto> getById(Integer id);
    ResponseData<TaskOutputDto> create(TaskCreateInputDto inputDto);
    ResponseData<TaskOutputDto> update(TaskUpdateInputDto inputDto);
    ResponseData<Boolean> delete(Integer id);
    ResponseData<List<ModuleOutputDto>> queryModulesByTaskId(Integer taskId);
    // TODO:待完成
    ResponseData<Boolean> run(TaskRunInputDto taskRunInputDto);
    ResponseData<Boolean> changeIsJobOrNot(ChangeIsJobOrNotInputDto inputDto);
    ResponseData<Boolean> archive(Integer id);
}
