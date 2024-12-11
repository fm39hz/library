package com.huce.library.modules.publisher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/")
    public List<PublisherDto> getAllPublisher() {
        return publisherService.getAllPublisher();
    }

    @GetMapping("/{id}")
    public PublisherDto getPublisher(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }
}
