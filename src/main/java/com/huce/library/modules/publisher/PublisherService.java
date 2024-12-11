package com.huce.library.modules.publisher;

import java.util.List;

public interface PublisherService {
    PublisherDto savePublisher(PublisherDto publisher);

    List<PublisherDto> getAllPublisher();

    PublisherDto getPublisherById(Long id);

    PublisherDto updatePublisher(Long id, PublisherDto publisher);

    void deletePublisher(Long id);
}
