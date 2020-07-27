package work.koreyoshi.file.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.koreyoshi.base.result.RestResult;
import work.koreyoshi.file.project.serivce.FileStorageService;

/**
 * @author zhoujx
 */
@RestController
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/file/save")
    public RestResult save(MultipartFile file){
        String id = fileStorageService.saveMultipartFile(file);
        return RestResult.ok(id);
    }

}
