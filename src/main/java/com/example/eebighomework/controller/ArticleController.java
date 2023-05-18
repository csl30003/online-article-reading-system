package com.example.eebighomework.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.eebighomework.common.R;
import com.example.eebighomework.dto.ArticleDto;
import com.example.eebighomework.dto.CommentDto;
import com.example.eebighomework.model.Article;
import com.example.eebighomework.model.Comment;
import com.example.eebighomework.model.Likes;
import com.example.eebighomework.service.ArticleService;
import com.example.eebighomework.vo.ArticleRankVo;
import com.example.eebighomework.vo.ArticleVo;
import com.example.eebighomework.vo.CommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.NewThreadAction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/article")
@Api(tags = "文章相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取文章列表
     *
     * @param page    页码
     * @param size    每页条数
     * @param keyword 关键词
     * @return 文章列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取文章列表")
    //keyword 设置为false，不是必填项
    public R<Page<Article>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 @RequestParam(value = "keyword", required = false) String keyword) {
        Page<Article> result = articleService.list(page, size, keyword);
        return R.success(result);
    }

    /**
     * 获取文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取文章详情")
    public R<ArticleVo> get(@PathVariable Integer id) {
        ArticleVo result = articleService.get(id);
        if(result == null){
            return R.error("无法获取文章详情");
        }
        return R.success(result);
    }

    /**
     * 获取文章评论列表
     *
     * @param id 文章id
     * @return 评论列表
     */
    @GetMapping("/{id}/comment")
    @ApiOperation(value = "获取文章评论列表")
    public R<List<CommentVo>> comment(@PathVariable Integer id) {
        List<CommentVo> result = articleService.comment(id);
        return R.success(result);
    }

    /**
     * 上传文章
     *
     * @param articleDto 文章信息
     * @return 上传结果
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传文章")
    public R<String> upload(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        Integer userId = (Integer) request.getAttribute("userId");
        article.setUserId(userId);
        articleService.upload(article);
        return R.success("上传成功");
    }

    /**
     * 评论文章
     *
     * @param id         文章id
     * @param commentDto 评论信息
     * @return 评论结果
     */
    @PostMapping("/{id}/commentUpload")
    @ApiOperation(value = "评论文章")
    public R<String> comment(@PathVariable Integer id, @RequestBody CommentDto commentDto, HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setArticleId(id);
        comment.setContent(commentDto.getContent());
        Integer userId = (Integer) request.getAttribute("userId");
        comment.setUserId(userId);
        articleService.comment(comment);
        return R.success("评论成功");
    }
    /**
     * LISIZT
     * 获取文章排行榜
     *
     * @param days 排行榜类型：daily、weekly、monthly
     * @return 排行榜
     */
    @GetMapping("/rank/{days}")
    @ApiOperation(value = "获取文章排行榜")
    public R<List<ArticleRankVo>> rank(@PathVariable Integer days) {
        List<ArticleRankVo> result = articleService.rank(days);
        return R.success(result);
    }

    /**
     * LISIZT
     * 点赞文章
     *
     * @param id 文章id
     * @return 点赞结果
     */
    @PostMapping("/{id}/like")
    @ApiOperation(value = "点赞文章")
    public R<String> like(@PathVariable Integer id , HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Likes likes = articleService.selectlike(id,userId);
        if ( likes == null) {
            Likes likes1 = new Likes();
            likes1.setArticleId(id);
            likes1.setUserId(userId);
            articleService.insertlike(likes1);
            articleService.like(id);
            return R.ok("点赞成功");
        }
        else {
            return R.error("点赞失败");
        }
    }

    /**
     * LISIZT
     * 取消点赞文章
     *
     * @param id 文章id
     * @return 取消点赞结果
     */
    @PostMapping("/{id}/cancellike")
    @ApiOperation(value = "取消点赞文章")
    public R<String> cancelLike(@PathVariable Integer id, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Likes likes = articleService.selectlike(id,userId);
        if (likes!=null){
            articleService.cancelLike(id);
            articleService.unlike(id,userId);
            return R.ok("取消点赞成功");
        }
        else {
            return R.error("取消点赞失败");
        }
    }
}