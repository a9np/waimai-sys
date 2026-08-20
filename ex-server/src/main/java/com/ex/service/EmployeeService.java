package com.ex.service;

import com.ex.dto.EmployeeDTO;
import com.ex.dto.EmployeeLoginDTO;
import com.ex.dto.EmployeePageQueryDTO;
import com.ex.dto.PasswordEditDTO;
import com.ex.entity.Employee;
import com.ex.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void addEmp(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void setStatus(Integer status, Long id);

    Employee getById(Long id);

    void update(EmployeeDTO employeeDTO);

    void editPassword(PasswordEditDTO passwordEditDTO);
}
