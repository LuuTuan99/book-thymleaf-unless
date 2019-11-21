package com.fpt.config;

import com.fpt.entity.Author;
import com.fpt.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Seeding implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = Logger.getLogger(Seeding.class.getSimpleName());

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.log(Level.INFO, String.format("Seeding author"));
        seedingAuthor();
    }

    void seedingAuthor() {
        authorRepository.deleteAll();

        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 01")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());

        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 02")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 03")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 04")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 05")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 06")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 07")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 08")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 09")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 10")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 11")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 12")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .build());
    }
}
