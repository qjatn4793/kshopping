package com.shopping.kshopping.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Service("fileService")
public class FileService {

    public void uploadFile(MultipartHttpServletRequest multiRequest) throws Exception{
        Map<String, MultipartFile> files = multiRequest.getFileMap();

        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();

        MultipartFile mFile;

        String filePath = "/temp/files/";

        String saveFileName = "", savaFilePath = "";

        while (itr.hasNext()){
            Entry<String, MultipartFile> entry = itr.next();
            mFile = entry.getValue();
            String fileName = mFile.getOriginalFilename();
            String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            String saveFilePath = filePath + File.separator + fileName;
            File fileFolder = new File(filePath);

            if(!fileFolder.exists()){
                if(fileFolder.mkdirs()){
                    System.out.println("Success");
                }else {
                    System.out.println("Fail");
                }
            }

            File saveFile = new File(saveFilePath);

            if(saveFile.isFile()){
                boolean _exist = true;

                int index = 0;

                while (_exist) {
                    index++;

                    saveFileName = fileCutName + "(" + index + ")." + fileExt;

                    String dictFile = filePath + File.separator + saveFileName;

                    _exist = new File(dictFile).isFile();

                    if(!_exist){
                        savaFilePath = dictFile;
                    }
                }

                mFile.transferTo(new File(savaFilePath));
            }else {
                mFile.transferTo(saveFile);
            }
        }
    }
}
