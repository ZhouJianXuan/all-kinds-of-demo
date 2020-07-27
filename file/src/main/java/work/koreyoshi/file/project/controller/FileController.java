package work.koreyoshi.file.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.koreyoshi.base.exception.FileException;
import work.koreyoshi.base.result.RestResult;
import work.koreyoshi.file.project.common.model.FileStorage;
import work.koreyoshi.file.project.serivce.FileStorageService;
import work.koreyoshi.file.project.serivce.impl.SimpleFileStorageServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhoujx
 */
@RestController
public class FileController {

    @Autowired
    private SimpleFileStorageServiceImpl fileStorageService;

    @PostMapping(value = "/file/save")
    public RestResult save(MultipartFile file){
        String id = fileStorageService.saveMultipartFile(file);
        return RestResult.ok(id);
    }

    @GetMapping(value = "/file")
    public void get(String id, HttpServletResponse response) {
        File file = fileStorageService.getFileById(id);
        FileStorage fileStorage = fileStorageService.findById(id);
        try {
            byte[] buf = new byte[8096];
            int len = 0;
            response.setContentType("application/" + fileStorage.getFileSuffix());
            String fileName = fileStorage.getFileName();
            response.setHeader("Content-Disposition",
                    "inline; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
            OutputStream out = response.getOutputStream();
            while ((len = br.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            br.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw FileException.writeError();
        }
    }

}
