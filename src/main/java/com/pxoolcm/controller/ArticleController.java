package com.pxoolcm.controller;

import com.pxoolcm.pojo.Article;
import com.pxoolcm.pojo.PageBean;
import com.pxoolcm.pojo.Result;
import com.pxoolcm.service.ArticleService;
import com.pxoolcm.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/8 9:36
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
    @GetMapping("/detail")
    public Result detail(Integer id){
        Article article = articleService.detail(id);
        return Result.success(article);
    }
    @PutMapping
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.controller
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-08  09:36
 * @Description: TODO
 * @Version: 1.0
 */
