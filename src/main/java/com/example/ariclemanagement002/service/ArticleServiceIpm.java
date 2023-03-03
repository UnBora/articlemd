package com.example.ariclemanagement002.service;

import com.example.ariclemanagement002.model.Article;
import com.example.ariclemanagement002.repository.ArticleRepository;
import com.example.ariclemanagement002.service.response.ArticleResponse;
import com.example.ariclemanagement002.service.response.GetAllArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Service
public class ArticleServiceIpm implements BaseService<Article, Integer, ArticleResponse> {

    private final ArticleRepository articleRepository;
    final
    DBFileStorageService dbFileStorageService;
    @Autowired
    public ArticleServiceIpm(ArticleRepository articleRepository, DBFileStorageService dbFileStorageService) {
        this.articleRepository = articleRepository;
        this.dbFileStorageService = dbFileStorageService;
    }

    public GetAllArticleResponse findPaginated(int pageNo, int pageSize, int status) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Article> articles = articleRepository.findAllByStatus(status, pageable);
        return new GetAllArticleResponse(200, "Data Found!", articleRepository.countAllByStatusIs(status), articles);
    }

    @Override
    public void save(Article obj) {
        obj.setStatus(2);
        this.articleRepository.save(obj);
    }

    @Override
    public ArticleResponse getById(Integer integer) {
        Article article = articleRepository.findById(integer).orElse(null);
        ArticleResponse articleResponse = new ArticleResponse(200, "The Article ID:: " + integer + " Get Successfully", article);
       if (article == null){
           articleResponse.setResponse_code(404);
           articleResponse.setMessage("The Article ID: "+integer+ " Not Found!");
       } else if (article.getStatus() == 0) {
           articleResponse.setResponse_code(404);
           articleResponse.setMessage("The Article ID: "+integer+ " Had Deleted!");
           articleResponse.setData(null);
       }
        return articleResponse;
    }

    public Article getById1(Integer integer) {
        return articleRepository.findById(integer).orElse(null);
    }

    @Override
    public ArticleResponse deleteById(Integer integer, Article obj) {
        Article article = articleRepository.findById(integer).orElse(null);
        ArticleResponse articleResponse = new ArticleResponse(200, "The Article ID:: " + integer + " Deleted!", article);
        if (article != null) {
            article.setStatus(0);
            articleRepository.save(article);
            articleResponse.setData(article);
        } else {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID: " + integer + " Not Found!");
        }
        return articleResponse;
    }

    @Override
    public ArticleResponse enableById(Integer integer, Article obj) {
        Article article = articleRepository.findById(integer).orElse(null);
        ArticleResponse articleResponse = new ArticleResponse(200, "Data Found!", article);
        if (article == null) {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID: " + integer + " Not Found!");
        } else if (article.getStatus() == 0) {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID:  " + integer + " Had Deleted!");
            articleResponse.setData(null);
        } else {
            article.setStatus(2);
            articleRepository.save(article);
            articleResponse.setData(article);
        }
        return articleResponse;
    }

    @Override
    public ArticleResponse disableById(Integer integer, Article obj) {
        Article article = articleRepository.findById(integer).orElse(null);
        ArticleResponse articleResponse = new ArticleResponse(200, "Data Found!", article);
        if (article == null) {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID: " + integer + " Not Found!");
        } else if (article.getStatus() == 0) {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID: " + integer + " Had Deleted!");
            articleResponse.setData(null);
        } else {
            article.setStatus(1);
            articleRepository.save(article);
            articleResponse.setData(article);
        }
        return articleResponse;
    }

    public ArticleResponse update(int integer, Article obj, MultipartFile file) throws IOException {
        Article article = articleRepository.findById(integer).orElse(null);
        ArticleResponse articleResponse = new ArticleResponse(200, "Data Found!", article);
        System.out.println(obj);
        if (article == null) {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID: " + integer + " Not Found!");
        } else if (article.getStatus() == 0) {
            articleResponse.setResponse_code(404);
            articleResponse.setMessage("The Article ID: " + integer + " Had Deleted!");
            articleResponse.setData(null);
        } else {
            if (!Objects.requireNonNull(file.getOriginalFilename()).isEmpty())
                dbFileStorageService.updateFile(article.getDbFile().getId(), file);
            if (obj.getArticleTitle() != null)
                article.setArticleTitle(obj.getArticleTitle());
            if (obj.getAuthorName() != null)
                article.setAuthorName(obj.getAuthorName());
            if (obj.getDescription() != null)
                article.setDescription(obj.getDescription());
            article.setStatus(2);
            articleRepository.save(article);
            articleResponse.setData(article);
        }
        return articleResponse;
    }
}