package work.koreyoshi.project.videos;

import com.jfinal.plugin.activerecord.Model;
import org.springframework.stereotype.Service;
import work.koreyoshi.base.service.BaseService;
import work.koreyoshi.project.common.model.Videos;

/**
 * @author zhoujx
 */
@Service
public class VideosService extends BaseService<Videos> {

    private final static Videos MODEL = new Videos();

    /**
     * 必须实现
     *
     * @return 获取对应的mapper
     */
    @Override
    public Model<Videos> getModel() {
        return MODEL;
    }
}
