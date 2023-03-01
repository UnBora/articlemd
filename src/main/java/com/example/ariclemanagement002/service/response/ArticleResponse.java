package com.example.ariclemanagement002.service.response;

import com.example.ariclemanagement002.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    int response_code;
    String message;
    Article data;
}
