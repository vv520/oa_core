package com.htwy.oa.dao.address;


import com.htwy.oa.entity.note.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Director, Long> {

    //根据姓名首拼模糊查询

}
