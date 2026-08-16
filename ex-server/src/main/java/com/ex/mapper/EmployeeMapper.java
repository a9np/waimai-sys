package com.ex.mapper;

import com.ex.dto.EmployeePageQueryDTO;
import com.ex.entity.Employee;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert("INSERT INTO employee\n" +
            "(name, username, password, phone, sex, id_number,\n" +
            " create_time, update_time, create_user, update_user)\n" +
            "VALUES\n" +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber},\n" +
            " #{createTime}, #{updateTime}, #{createUser}, #{updateUser})\n")
    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void update(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
