package com.fpt.config;


import com.fpt.entity.*;
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
        seedingPublisher();
        seedingCategory();
        seedingBook();
    }

    private void seedingBook() {
        bookRepository.deleteAll();
        Book book = new Book();
        book.setName("Làm Phụ Nữ Không Khổ Tí Nào");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90153Elam_phu.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: Skybooks,Mã Sản phẩm: 8936186541094,Khối lượng: 242.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 12.5 x 20 cm,Ngày phát hành: 18/10/2019,Số trang: 224");
        bookRepository.save(book);

        Book book1 = new Book();
        book1.setName("Làm Bạn Với Bầu Trời(Bìa Mềm)");
        book1.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/347103_p88536m1.jpg");
        book1.setDescription("Nhà xuất bản: Nxb Trẻ,Nhà phát hành: NXB Trẻ,Mã Sản phẩm: 8934974164135,Khối lượng: 330.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20 cm,Ngày phát hành: 11/09/2019,Số trang: 252");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setName("Ngày Tôi Đưa Tang Mình");
        book2.setPhotos("https://www.vinabook.com/images/detailed/347/P88742Escan0001.jpg");
        book2.setDescription("Nhà xuất bản: Nxb Tổng hợp TP.HCM,Nhà phát hành: NXBTH TPHCM,Mã Sản phẩm: 9786045895542,Khối lượng: 176.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 09/2019,Số trang: 128");
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setName("Đàn Ông Già - Đàn Bà Đẹp");
        book3.setPhotos("https://www.vinabook.com/images/detailed/346/P88632E1.jpg");
        book3.setDescription("Nhà xuất bản: Nxb văn họ,Nhà phát hành: Bachviet,BooksMã Sản phẩm: 9786049767838,Khối lượng: 352.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 27/09/2019,Số trang: 312");
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setName("Đời Như Một Trò Đùa");
        book4.setPhotos("https://www.vinabook.com/images/detailed/349/P90109Escan0001.jpg");
        book4.setDescription("Nhà xuất bản: Nxb Thế giới,Nhà phát hành: Nhã Nam,Mã Sản phẩm: 8935235223837,Khối lượng: 396.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 12/10/2019,Số trang: 344");
        bookRepository.save(book4);
    }

    private void seedingCategory() {
        categoryRepository.deleteAll();

        Category category = new Category();
        category.setName("Văn học trong nước");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category);

        Category category1 = new Category();
        category1.setName("Văn học nước ngoài");
        category1.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Sách thiếu nhi");
        category2.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setName("Sách Blockchain");
        category3.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category1);

        Category category4 = new Category();
        category4.setName("Sách Kinh tế");
        category4.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category4);

        Category category5 = new Category();
        category5.setName("Sách Thường Thức – Đời Sống");
        category5.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category5);

        Category category6 = new Category();
        category6.setName("Sách Phát Triển Bản Thân");
        category6.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryRepository.save(category6);
    }

    private void seedingPublisher() {
        publisherRepository.deleteAll();

        Publisher publisher = new Publisher();
        publisher.setName("NXB Trẻ");
        publisher.setAvatar("https://locrpctricks.files.wordpress.com/2018/04/tumblr_static_f25n7rsd1jscgw4koogg04woc.png");
        publisher.setDescription("NXB Trẻ đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherRepository.save(publisher);

        Publisher publisher1 = new Publisher();
        publisher1.setName("NXB Phụ Nữ");
        publisher1.setAvatar("http://hieusach.vn/upload/8415/20140123/logo_nxbphunu.jpg");
        publisher1.setDescription("NXB Phụ Nữ đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Nxb Sư Phạm");
        publisher2.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTA3600iqI36NmFZvaccmu4fy5W_jbEQIrX6AbNtbNsRMZuzrL&s");
        publisher2.setDescription("NXB Sư Phạm đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherRepository.save(publisher2);

        Publisher publisher3 = new Publisher();
        publisher3.setName("Nxb Thế Giới");
        publisher3.setAvatar("https://ebook.vn//uploads/producers/thumb_1514338374.png");
        publisher3.setDescription("NXB Thế Giới đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherRepository.save(publisher3);
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
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());

        authorRepository.save(Author.Builder.anAuthor()
                .withName("Nguyễn Du")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/T%C6%B0%E1%BB%A3ng_%C4%91%C3%A0i_c%E1%BB%A5_Nguy%E1%BB%85n_Du.jpg/222px-T%C6%B0%E1%BB%A3ng_%C4%91%C3%A0i_c%E1%BB%A5_Nguy%E1%BB%85n_Du.jpg")
                .withDescription("Nguyễn Du (chữ Hán: 阮攸; sinh ngày 3 tháng 1 năm 1766–1820)[cần dẫn nguồn] tên tự Tố Như (素如), hiệu Thanh Hiên (清軒), biệt hiệu Hồng Sơn lạp hộ (鴻山獵戶), Nam Hải điếu đồ (南海釣屠), là một nhà thơ, nhà văn hóa lớn thời Lê mạt, Nguyễn sơ ở Việt Nam. Ông được người Việt kính trọng tôn xưng là \"Đại thi hào dân tộc\".[1]")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Nam Cao")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/a/a2/Namcao.jpg")
                .withDescription("Nam Cao (1915 hoặc 1917 – 28 tháng 11 năm 1951) là một nhà văn và cũng là một chiến sĩ, liệt sĩ người Việt Nam. Ông là nhà văn hiện thực lớn (trước Cách mạng), một nhà báo kháng chiến (sau Cách mạng), một trong những nhà văn tiêu biểu nhất thế kỷ 20. Nam Cao có nhiều đóng góp quan trọng đối với việc hoàn thiện phong cách truyện ngắn và tiểu thuyết Việt Nam ở nửa đầu thế kỷ 20.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Huy Cận")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/thumb/9/9f/Huycan.jpg/220px-Huycan.jpg")
                .withDescription("Cù Huy Cận (1919 – 2005), bút danh hoạt động nghệ thuật là Huy Cận, là một chính khách, từng giữ nhiều chức vụ lãnh đạo cao cấp trong chính phủ Việt Nam như Bộ trưởng Bộ Canh nông đầu tiên, Thứ trưởng rồi Bộ trưởng Bộ Văn hóa Nghệ thuật, Bộ trưởng Bộ Văn hóa Giáo dục, Thứ trưởng Bộ Nội vụ, Bộ Kinh tế, Bộ trưởng Tổng Thư ký Hội đồng Bộ trưởng (nay là Bộ trưởng Chủ nhiệm Văn phòng Chính phủ), ngoài ra ông còn là một nhà lãnh đạo chủ chốt của Đảng Dân chủ Việt Nam[cần dẫn nguồn], đồng thời cũng là một trong những nhà thơ xuất sắc nhất của phong trào Thơ mới. Ông từng là Viện sĩ Viện Hàn lâm Thơ Thế giới và Chủ tịch Ủy ban Liên hiệp các Hiệp hội Văn học Việt Nam giai đoạn 1984-1995.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Chế Lan Viên")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/7/72/Chelanvien.jpg")
                .withDescription("Chế Lan Viên (1920-1989) là một nhà thơ, nhà văn hiện đại nổi tiếng ở Việt Nam.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 06")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 07")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 08")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 09")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 10")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 11")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.save(Author.Builder.anAuthor()
                .withName("Xuân Diệu 12")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Tác giả vội vàng.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
    }

}
