package com.htwy.oa.service.address;


import com.htwy.oa.entity.note.Director;

import java.util.List;
import java.util.Map;

public interface AddressService {

    Director sava(Director director);

    void deleteDirector(Director director);

    List<Map<String, Object>> fengzhaung(List<Map<String, Object>> addressList);

}
