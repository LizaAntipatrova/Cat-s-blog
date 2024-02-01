package com.example.Cat.s.Blog.db.entity.posts;

import com.example.Cat.s.Blog.db.entity.users.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    private String content;         //html mb
    private String title;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
    private Date publicationDate;

    public Blogpost(String content, String title, User author, Date publicationDate) {
        this.content = content;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }


}

