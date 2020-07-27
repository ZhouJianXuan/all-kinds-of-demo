package work.koreyoshi.file.project.serivce.impl;

import com.jfinal.plugin.activerecord.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import work.koreyoshi.base.exception.FileException;
import work.koreyoshi.base.service.BaseService;
import work.koreyoshi.base.util.SequenceUtil;
import work.koreyoshi.file.project.common.model.FileStorage;
import work.koreyoshi.file.project.serivce.FileStorageService;

import java.io.*;
import java.util.Date;

/**
 * @author zhoujx
 */
@Service
public class SimpleFileStorageServiceImpl extends BaseService<FileStorage> implements FileStorageService{

    private static final FileStorage MODEL = new FileStorage();

    @Value("${fileStorage.basePath:H:\\zhoujx\\file}")
    private String basePath;

    @Override
    public Model<FileStorage> getModel() {
        return MODEL;
    }

    @Override
    public String saveFile(File file) {
        String name = file.getName();
        String suffix = name.substring(name.lastIndexOf("."));
        InputStream in = null;
        FileOutputStream os = null;
        String id = SequenceUtil.fastSimpleUUID();
        try {
            in = new FileInputStream(file);
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            String path = basePath + "/" + "";
            File tempFile = new File(basePath);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + id);
            // 开始读取
            while ((len = in.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            FileStorage fileStorage = new FileStorage();
            fileStorage.setId(id);
            fileStorage.setFileName(name);
            fileStorage.setFileSuffix(suffix);
            fileStorage.setCreateTime(new Date());
            fileStorage.setUpdateTime(new Date());
            save(fileStorage);
        } catch (FileNotFoundException e) {
            throw FileException.readError();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public String saveMultipartFile(MultipartFile file) {
        String name = file.getResource().getFilename();
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        InputStream in = null;
        FileOutputStream os = null;
        String id = SequenceUtil.fastSimpleUUID();
        try {
            in = file.getInputStream();
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            String path = basePath + "\\test";
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + id);
            // 开始读取
            while ((len = in.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            FileStorage fileStorage = new FileStorage();
            fileStorage.setId(id);
            fileStorage.setFileName(name);
            fileStorage.setFileSuffix(suffix);
            fileStorage.setCreateTime(new Date());
            fileStorage.setUpdateTime(new Date());
            save(fileStorage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw FileException.readError();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public File getFileById(String id) {
        String path = basePath + "\\test";
        File file = new File(path + File.separator + id);
        if (file.exists()) {
            return file;
        }
        throw FileException.notExists();
    }
}
