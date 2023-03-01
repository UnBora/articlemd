package com.example.ariclemanagement002.controller;

import com.example.ariclemanagement002.model.Article;
import com.example.ariclemanagement002.model.DBFile;
import com.example.ariclemanagement002.exception.AppConstants;
import com.example.ariclemanagement002.service.ArticleServiceIpm;
import com.example.ariclemanagement002.service.DBFileStorageService;
import com.example.ariclemanagement002.service.response.ArticleResponse;
import com.example.ariclemanagement002.service.response.GetAllArticleResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1")
public class ArticleRestController {
    private final ArticleServiceIpm articleServiceIpm;
    private final DBFileStorageService dbFileStorageService;

    @Autowired
    public ArticleRestController(ArticleServiceIpm articleServiceIpm, DBFileStorageService dbFileStorageService) {
        this.articleServiceIpm = articleServiceIpm;
        this.dbFileStorageService = dbFileStorageService;
    }

    @GetMapping("/articles")
    public GetAllArticleResponse getAllArticle(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "status", defaultValue = AppConstants.DEFAULT_ARTICLE_STATUS, required = false) int status) {
        return articleServiceIpm.findPaginated(pageNo, pageSize, status);
    }

    @GetMapping("/article/{id}")
    public ArticleResponse getArticleById(@PathVariable(value = "id") int articleId) {

        return  articleServiceIpm.getById(articleId);
    }

    @PostMapping(value = "/add")
    public ArticleResponse insertArticle(@RequestParam String article, @RequestParam MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);
        Article article1 = new Gson().fromJson(article, Article.class);
        article1.setDbFile(dbFile);
        articleServiceIpm.save(article1);
        return new ArticleResponse(200,"The Article Add Successfully", article1);
    }

    @PostMapping("updateArticle/{id}")
    public ArticleResponse updateArticleById(@PathVariable(value = "id") int articleId,
                                             @RequestParam String articleDetail,
                                             @RequestParam MultipartFile file ) throws IOException {
        Article article1 = new Gson().fromJson(articleDetail, Article.class);
        return articleServiceIpm.update(articleId, article1, file);
    }
    @DeleteMapping("deleteArticle/{id}")
    public ArticleResponse deleteArticleById(@PathVariable("id") int articleId) {
        return articleServiceIpm.deleteById(articleId, articleServiceIpm.getById(articleId).getData());
    }

    @PostMapping("disable/{id}")
    public ArticleResponse disableArticleById(@PathVariable("id") int articleId) {
        return articleServiceIpm.disableById(articleId, articleServiceIpm.getById(articleId).getData());
    }

    @PostMapping("enable/{id}")
    public ArticleResponse enableArticleById(@PathVariable("id") int articleId) {
        return articleServiceIpm.enableById(articleId, articleServiceIpm.getById(articleId).getData());
    }
}
