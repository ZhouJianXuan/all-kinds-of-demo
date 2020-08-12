package work.koreyoshi.project.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Administrator
 */
public interface FileStorageService {

    /**
     * 存文件
     * @param file 文件
     * @return 记录id
     */
    String saveFile(File file);

    /**
     * 存文件
     * @param file 文件
     * @return 记录id
     */
    String saveMultipartFile(MultipartFile file);

    /**
     * 根据记录id删除文件记录
     * @param id 文件记录id
     * @return 删除结果
     */
    boolean deleteById(String id);

    /**
     * 根据id获取文件
     * @param id id
     * @return 文件
     */
    File getFileById(String id);
}
