package com.htwy.oa.service.note;

import java.util.Date;

public interface AttachService {

    Integer updateatt(String attname, String attpath, String shu, Long size, String type, Date uptime, Long attid);
}
