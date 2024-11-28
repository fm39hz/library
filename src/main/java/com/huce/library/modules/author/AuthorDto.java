package com.huce.library.modules.author;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private Integer age;

    public AuthorDto(@NotNull Author author) {
        setId(author.getId());
        setName(author.getName());
        setAge(author.getAge());
    }
}
