package work.koreyoshi.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.*;
import work.koreyoshi.base.annotation.Transactional;
import work.koreyoshi.base.result.RestResult;
import work.koreyoshi.project.common.model.Videos;
import work.koreyoshi.project.videos.VideosService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujx
 */
@RestController
@RequestMapping("/test/admin")
public class VideosController {

    @Resource
    private VideosService videosService;

    @GetMapping("/videos")
    public RestResult videos() {
        List<Videos> all = videosService.findAll();
        return RestResult.ok(all);
    }

    @GetMapping("/videos/{id}")
    public RestResult getVideos(@PathVariable String id) {
        Videos videos = videosService.findById(id);
        return RestResult.ok(videos);
    }

    @PutMapping("/videos")
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@RequestBody Videos videos) {
        boolean update = videosService.update(videos);
        return RestResult.ok(update);
    }

    @PostMapping("/videos")
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@RequestBody Videos videos) {
        videos.setId(IdUtil.fastSimpleUUID());
        boolean save = videosService.save(videos);
        return RestResult.ok(save);
    }

    @DeleteMapping("/videos/{id}")
    public RestResult delete(@PathVariable String id) {
        boolean b = videosService.deleteById(id);
        return RestResult.ok(b);
    }
}
