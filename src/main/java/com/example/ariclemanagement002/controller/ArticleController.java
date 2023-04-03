package com.example.ariclemanagement002.controller;

import com.example.ariclemanagement002.model.Article;
import com.example.ariclemanagement002.model.DBFile;
import com.example.ariclemanagement002.exception.AppConstants;
import com.example.ariclemanagement002.service.ArticleServiceIpm;
import com.example.ariclemanagement002.service.DBFileStorageService;
import com.example.ariclemanagement002.service.response.GetAllArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;


@Controller
public class ArticleController {
    private final ArticleServiceIpm articleServiceIpm;
    private final DBFileStorageService dbFileStorageService;
    @Autowired
    public ArticleController(DBFileStorageService dbFileStorageService, ArticleServiceIpm articleServiceIpm) {
        this.dbFileStorageService = dbFileStorageService;
        this.articleServiceIpm = articleServiceIpm;
    }


    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
//        Get Pagination page By page number and page size
        GetAllArticleResponse page = articleServiceIpm.findPaginated(pageNo, pageSize, 2);
//        Count Article that Active
        int allItemActive = page.getSize();
//        Get total page to show article that active
        int totalPage = allItemActive / pageSize;
        if ((allItemActive % pageSize) != 0) {
            totalPage += 1;
        }
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("totalItems", allItemActive);
        model.addAttribute("listArticle", page);
        return "index";
    }

    @GetMapping("/disableArticlesPage/{pageNo}")
    public String articleDisActivate(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
        GetAllArticleResponse page = articleServiceIpm.findPaginated(pageNo, pageSize, 1);

        int allItemActive = page.getSize();
//        Get total page to show article that active
        int totalPage = allItemActive / pageSize;
        if (((allItemActive % pageSize) != 0)) {
            totalPage += 1;
        }
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("totalItems", allItemActive);
        model.addAttribute("listArticle", page);
        return "disabled_article";
    }

    @GetMapping("/showNewArticleForm")
    public String showNewArticleForm(Model model) {
        Article article = new Article();
        article.setStatus(2);
        model.addAttribute("article", article);
        return "new_article";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public Object showFormUpdate(@PathVariable(value = "id") int id, Model model) {
        Article article = articleServiceIpm.getById(id);
        model.addAttribute("article", article);
        return "update_article";
    }

    @PostMapping("/updateArticle")
    public String updateArticle(@ModelAttribute Article article) {
        Article obj = articleServiceIpm.getById(article.getId());
        String imageId = obj.getDbFile().getId();
        article.setDbFile(obj.getDbFile());
        if (article.getMultipartFile() != null && !StringUtils.isEmpty(article.getMultipartFile().getOriginalFilename())) {
            DBFile dbFile = dbFileStorageService.storeFile(article.getMultipartFile());
            article.setDbFile(dbFile);
            articleServiceIpm.save(article);
            dbFileStorageService.deleteFileById(imageId);
        } else {
            articleServiceIpm.save(article);
        }
        return "redirect:/1";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@ModelAttribute Article article) {
        //To save Article to database
        DBFile dbFile = dbFileStorageService.storeFile(article.getMultipartFile());
        article.setDbFile(dbFile);
        articleServiceIpm.save(article);
        return "redirect:/1";
    }

    @GetMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable(value = "id") int id) {
        Article article = articleServiceIpm.getById(id);
        this.articleServiceIpm.deleteById(id, article);
        return "redirect:/1";
    }

    @GetMapping("/EnableArticle/{id}")
    public String enableArticle(@PathVariable(value = "id") int id) {
        Article article = articleServiceIpm.getById(id);
        this.articleServiceIpm.enableById(id, article);
        return "redirect:/disableArticlesPage/1";
    }

    @GetMapping("/disableArticle/{id}")
    public String disableAccById(@PathVariable(value = "id") int id) {
        Article article = articleServiceIpm.getById(id);
        this.articleServiceIpm.disableById(id, article);
        return "redirect:/1";
    }
}