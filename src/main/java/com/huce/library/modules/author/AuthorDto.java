package com.huce.library.modules.author;

import lombok.Data;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private Integer age;

    public AuthorDto(Author author) {
        setId(author.getId());
        setName(author.getName());
        setAge(author.getAge());
    }
}
