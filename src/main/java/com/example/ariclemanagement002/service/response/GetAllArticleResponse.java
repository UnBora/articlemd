package com.example.ariclemanagement002.service.response;

import com.example.ariclemanagement002.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllArticleResponse {
    int response_code;
    String message;
    int size;
    List<Article> data;
}
