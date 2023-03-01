package com.example.ariclemanagement002.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "Article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "author_name")
    private String authorName;
    @Column(name = "status")
    private int status;

    @Transient
    @JsonIgnore
    private MultipartFile multipartFile;

    @JsonIgnore
    public String getFullName() {
        return articleTitle+" - "+authorName;
    }

    @ManyToOne
    @JoinColumn(name = "db_file_id")
    @OrderBy("fileName ASC")
    private DBFile dbFile;


}
