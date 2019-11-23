package com.fpt.config;

import com.fpt.entity.Author;
import com.fpt.entity.Member;
import com.fpt.entity.Publisher;
import com.fpt.repository.AuthorRepository;
import com.fpt.repository.MemberRepository;
import com.fpt.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Seeding implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = Logger.getLogger(Seeding.class.getSimpleName());

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    MemberRepository memberRepository;


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.log(Level.INFO, String.format("Seeding"));
        seedingAuthor();
        seedingMember();
    }

     void seedingMember() {
        memberRepository.deleteAll();
        Member member = new Member();
        member.setUsername("admin");
        member.setEmail("admin@gmail.com");
        member.setHashPassword(new BCryptPasswordEncoder().encode("123"));
        member.setPhone("0123456");
        member.setAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg");
        member.setAddress("Hai Duong");
        member.setRole(Member.Role.ADMIN.getValue());
        member.setGender(Member.Gender.MALE.getValue());
        member.setStatus(Member.Status.ACTIVE.getValue());

        memberRepository.save(member);

         Member member1 = new Member();
         member1.setUsername("user");
         member1.setEmail("user@gmail.com");
         member1.setHashPassword(new BCryptPasswordEncoder().encode("123"));
         member1.setPhone("012345678");
         member1.setAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg");
         member1.setAddress("Ha Noi");
         member1.setRole(Member.Role.CUSTOMER.getValue());
         member1.setGender(Member.Gender.FEMALE.getValue());
         member1.setStatus(Member.Status.ACTIVE.getValue());

         memberRepository.save(member1);
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
