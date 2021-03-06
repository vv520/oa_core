package com.htwy.oa.service.file.impl;

import com.htwy.oa.dao.filedao.FileListdao;
import com.htwy.oa.dao.filedao.FilePathdao;
import com.htwy.oa.entity.file.FileList;
import com.htwy.oa.entity.file.FilePath;
import com.htwy.oa.service.file.FileTransactionalHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FileTransactionalHandlerServiceImpl implements FileTransactionalHandlerService {

    @Autowired
    private FileListdao fldao;
    @Autowired
    private FilePathdao fpdao;
    @Autowired
    private FileServicesImpl fileServices;

    /**
     * 根据文件id 将文件放入回收站
     * @param fileids
     */

    @Transactional
    public void trashfile(List<Long> fileids, Long setistrashhowmany, Long userid){
        for (Long fileid : fileids) {
            FileList fileList = fldao.findById(fileid).get();
            fileList.setFileIstrash(setistrashhowmany);
            if(userid != null){
                fileList.setFpath(null);
            }

            fldao.save(fileList);
        }

    }

    /**
     * 文件还原
     * @param checkfileids
     */
    @Transactional
    public void filereturnback(List<Long> checkfileids,Long userid) {
        FilePath fpath = fpdao.findByParentIdAndPathUserId(1L, userid);
        for (Long checkfileid : checkfileids) {
            FileList fileList = fldao.findById(checkfileid).get();

            if (userid != null) {
                String name = fileServices.onlyname(fileList.getFileName(), fpath, fileList.getFileShuffix(), 1, true);
                fileList.setFpath(fpath);
                fileList.setFileName(name);
            }
            fileList.setFileIstrash(0L);
            fldao.save(fileList);
        }

    }
}
