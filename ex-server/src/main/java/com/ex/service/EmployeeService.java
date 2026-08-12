package com.ex.service;

import com.ex.dto.EmployeeLoginDTO;
import com.ex.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}
