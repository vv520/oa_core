package com.htwy.oa.service.file;

import java.util.List;

public interface FileTransactionalHandlerService {

    /**
     * 根据文件id 将文件放入回收站
     *
     * @param fileids
     */
    void trashfile(List<Long> fileids, Long setistrashhowmany, Long userid);

    /**
     * 文件还原
     *
     * @param checkfileids
     */
    void filereturnback(List<Long> checkfileids, Long userid);
}
