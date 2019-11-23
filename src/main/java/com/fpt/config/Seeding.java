package com.fpt.config;


import com.fpt.entity.Author;
import com.fpt.entity.Member;
import com.fpt.entity.Publisher;
import com.fpt.repository.*;

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
    CategoryRepository categoryRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookRepository bookRepository;

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
                .withName("Xuân Diệu")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Xuân Diệu (2 tháng 2 năm 1916 – 18 tháng 12 năm 1985) là một trong những nhà thơ lớn của Việt Nam. Ông nổi tiếng từ phong trào Thơ mới với tập Thơ thơ và Gửi hương cho gió. Những bài được yêu thích nhất của Xuân Diệu là thơ tình làm trong khoảng 1936 – 1944, thể hiện một triết lý bi quan, tuyệt vọng về tình ái nhưng lại có một mạch ngầm thúc giục, nhiều khi hừng hực sức sống. Nhờ đó, ông được mệnh danh là \"ông hoàng thơ tình\". Ông từng được Hoài Thanh và Hoài Chân đưa vào cuốn Thi nhân Việt Nam (1942).")
                .build());

        authorRepository.save(Author.Builder.anAuthor()
                .withName("Nguyễn Du")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/T%C6%B0%E1%BB%A3ng_%C4%91%C3%A0i_c%E1%BB%A5_Nguy%E1%BB%85n_Du.jpg/222px-T%C6%B0%E1%BB%A3ng_%C4%91%C3%A0i_c%E1%BB%A5_Nguy%E1%BB%85n_Du.jpg")
                .withDescription("Nguyễn Du (chữ Hán: 阮攸; sinh ngày 3 tháng 1 năm 1766–1820)[cần dẫn nguồn] tên tự Tố Như (素如), hiệu Thanh Hiên (清軒), biệt hiệu Hồng Sơn lạp hộ (鴻山獵戶), Nam Hải điếu đồ (南海釣屠), là một nhà thơ, nhà văn hóa lớn thời Lê mạt, Nguyễn sơ ở Việt Nam. Ông được người Việt kính trọng tôn xưng là \"Đại thi hào dân tộc\".[1]")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Nam Cao")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/a/a2/Namcao.jpg")
                .withDescription("Nam Cao (1915 hoặc 1917 – 28 tháng 11 năm 1951) là một nhà văn và cũng là một chiến sĩ, liệt sĩ người Việt Nam. Ông là nhà văn hiện thực lớn (trước Cách mạng), một nhà báo kháng chiến (sau Cách mạng), một trong những nhà văn tiêu biểu nhất thế kỷ 20. Nam Cao có nhiều đóng góp quan trọng đối với việc hoàn thiện phong cách truyện ngắn và tiểu thuyết Việt Nam ở nửa đầu thế kỷ 20.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Huy Cận")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/thumb/9/9f/Huycan.jpg/220px-Huycan.jpg")
                .withDescription("Cù Huy Cận (1919 – 2005), bút danh hoạt động nghệ thuật là Huy Cận, là một chính khách, từng giữ nhiều chức vụ lãnh đạo cao cấp trong chính phủ Việt Nam như Bộ trưởng Bộ Canh nông đầu tiên, Thứ trưởng rồi Bộ trưởng Bộ Văn hóa Nghệ thuật, Bộ trưởng Bộ Văn hóa Giáo dục, Thứ trưởng Bộ Nội vụ, Bộ Kinh tế, Bộ trưởng Tổng Thư ký Hội đồng Bộ trưởng (nay là Bộ trưởng Chủ nhiệm Văn phòng Chính phủ), ngoài ra ông còn là một nhà lãnh đạo chủ chốt của Đảng Dân chủ Việt Nam[cần dẫn nguồn], đồng thời cũng là một trong những nhà thơ xuất sắc nhất của phong trào Thơ mới. Ông từng là Viện sĩ Viện Hàn lâm Thơ Thế giới và Chủ tịch Ủy ban Liên hiệp các Hiệp hội Văn học Việt Nam giai đoạn 1984-1995.")
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Chế Lan Viên")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/7/72/Chelanvien.jpg")
                .withDescription("Chế Lan Viên (1920-1989) là một nhà thơ, nhà văn hiện đại nổi tiếng ở Việt Nam.")
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
