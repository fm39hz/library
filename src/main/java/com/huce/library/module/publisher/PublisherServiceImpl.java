package com.huce.library.module.publisher;

import com.huce.library.module.book.Book;
import com.huce.library.module.book.BookDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public PublisherDto savePublisher(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDto.getId());
        return new PublisherDto(publisherRepository.save(publisher));
    }

    @Override
    public List<PublisherDto> getAllPublisher() {
        List<PublisherDto> publisherDtoList = new ArrayList<>();
        for (Publisher publisher : publisherRepository.findAll()) {
            publisherDtoList.add(new PublisherDto(publisher));
        }
        return publisherDtoList;
    }

    @Override
    public PublisherDto getPublisherById(Long id) {
        if (publisherRepository.findById(id).isEmpty()) {
            return null;
        }
        return new PublisherDto(publisherRepository.findById(id).get());
    }

    @Override
    public PublisherDto updatePublisher(Long id, PublisherDto publisherDto) {
        if (publisherRepository.findById(id).isEmpty()) {
            return null;
        }
        Publisher publisher = new Publisher();
        publisher.setId(id);
        publisher.setName(publisherDto.getName());
        List<Book> books = new ArrayList<>();
        for (BookDto book : publisherDto.getBooks()) {
            if (publisher.getBooks().contains(book)) {
                continue;
            }
            Book book1 = new Book();
            book1.setId(book.getId());
            book1.setTitle(book.getTitle());
            publisher.getBooks().add(book1);
            books.add(book1);
        }
        publisher.setBooks(books);
        publisherRepository.save(publisher);
        return new PublisherDto(publisherRepository.save(publisher));
    }

    @Override
    public void deletePublisher(Long id) {
        if (publisherRepository.findById(id).isEmpty()) {
            return;
        }
        publisherRepository.deleteById(id);
    }
}
