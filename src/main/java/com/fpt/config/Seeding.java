package com.fpt.config;


import com.fpt.entity.*;
import com.fpt.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    private List<Category> categoryList = new ArrayList<>();
    private List<Publisher> publisherList = new ArrayList<>();
    private List<Author> authorList = new ArrayList<>();
    private List<Member> memberList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.log(Level.INFO, String.format("Start seeding..."));
        categoryRepository.disableForeignKeyCheck();
        seedingAuthor();
        seedingMember();
        seedingPublisher();
        seedingCategory();
        seedingBook();
        categoryRepository.enableForeignKeyCheck();
        LOGGER.log(Level.INFO, String.format("Seeding success!"));
    }

     void seedingBook() {
        bookRepository.deleteAll();
        bookRepository.resetIncrement();

        Book book = new Book();
        book.setName("Làm Phụ Nữ Không Khổ Tí Nào");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90153Elam_phu.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: Skybooks,Mã Sản phẩm: 8936186541094,Khối lượng: 242.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 12.5 x 20 cm,Ngày phát hành: 18/10/2019,Số trang: 224");
        book.setPrice(86000);
        book.setQuantity(200);
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Làm Bạn Với Bầu Trời(Bìa Mềm)");
        book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/347103_p88536m1.jpg");
        book.setDescription("Nhà xuất bản: Nxb Trẻ,Nhà phát hành: NXB Trẻ,Mã Sản phẩm: 8934974164135,Khối lượng: 330.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20 cm,Ngày phát hành: 11/09/2019,Số trang: 252");
        book.setPrice(110000);
        book.setQuantity(200);
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Cuộc Phiêu Lưu Của Bầy Thần Khuyển");
        book.setPhotos("https://www.vinabook.com/images/detailed/347/P88658Ecuoc_phieu.jpg");
        book.setDescription("Nhà xuất bản: Nxb Trẻ ,Nhà phát hành: NXB Trẻ,Mã Sản phẩm: 8934974163701,Khối lượng: 286.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20 cm,Ngày phát hành: 11/09/2019,Số trang: 264");
        book.setPrice(85000);
        book.setQuantity(200);
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

         book = new Book();
         book.setName("Làm Bạn Với Bầu Trời(Bìa Cứng)");
         book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/347103_p88536m1.jpg");
         book.setDescription("Nhà xuất bản: Nxb Trẻ,Nhà phát hành: NXB Trẻ,Mã Sản phẩm: 8934974164135,Khối lượng: 506.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa cứng,Kích thước: 13 x 20 cm,Ngày phát hành: 11/09/2019,Số trang: 252");
         book.setPrice(220000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Gà Béo Đang Bay");
         book.setPhotos("https://www.vinabook.com/images/detailed/350/P90154Ega_beo.jpg");
         book.setDescription("Nhà xuất bản: Nxb văn học,Nhà phát hành: Bachviet,BooksMã Sản phẩm: 9786049767838,Khối lượng: 352.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 27/09/2019,Số trang: 312");
         book.setPrice(70000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Yêu Em Bằng Cả Trái Tim Anh");
         book.setPhotos("https://www.vinabook.com/images/detailed/349/P90093Et__i_b___n___yeu_em.png");
         book.setDescription("Nhà xuất bản: NXB Dân Trí,Mã Sản phẩm: 1130000090093,Khối lượng: 550.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14.5 x 20.5 cm,Ngày phát hành: 31/10/2019,Số trang: 476");
         book.setPrice(132000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Cuộc Sống Thượng Lưu Của Hoàng Đế Mèo");
         book.setPhotos("https://www.vinabook.com/images/detailed/236/P72316Escan0001.jpg");
         book.setDescription("Nhà xuất bản: NXB Dân Trí,Nhà phát hành: Skybooks,Mã Sản phẩm: 9786048859640,Khối lượng: 374.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 18 x 18 cm,Ngày phát hành: 03/2018,Số trang: 144");
         book.setPrice(89000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Thánh Gióng (Song Ngữ Anh - Việt)");
         book.setPhotos("https://www.vinabook.com/images/detailed/350/P90160Ethanh_giong.jpg");
         book.setDescription("Nhà xuất bản: Nxb văn học,Mã Sản phẩm: 1130000090160,Khối lượng: 132.00 gam,Ngôn ngữ: Tiếng Anh, Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 21 x 18.5 cm,Ngày phát hành: 10/11/2019,Số trang: 24");
         book.setPrice(28000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Sự Tích Lễ Vu Lan (Song Ngữ Anh - Việt) ");
         book.setPhotos("https://www.vinabook.com/images/detailed/350/P90159Esu_tich.jpg");
         book.setDescription("Nhà xuất bản: Nxb văn học,Mã Sản phẩm: 1130000090159,Khối lượng: 132.00 gam,Ngôn ngữ: Tiếng Anh, Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 21 x 18.5 cm,Ngày phát hành: 10/11/2019,Số trang: 24");
         book.setPrice(28000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Cây Tre Trăm Đốt (Song Ngữ Anh - Việt)");
         book.setPhotos("https://www.vinabook.com/images/detailed/350/P90158Ecay_tre.jpg");
         book.setDescription("Nhà xuất bản: Nxb văn học,Mã Sản phẩm: 1130000090158,Khối lượng: 132.00 gam,Ngôn ngữ: Tiếng Anh, Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 21 x 18.5 cm,Ngày phát hành: 10/11/2019,Số trang: 24");
         book.setPrice(28000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Combo Trạng Quỳnh - Trạng Dạy Học (Bộ 4 Cuốn)");
         book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/345676_p88490mbo.jpg");
         book.setDescription("Nhà xuất bản: Nxb Thanh Hóa,Nhà phát hành: Nhà sách Hoa Sen,Mã Sản phẩm: 9999900039146,Khối lượng: 242.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 17 x 24 cm,Ngày phát hành: 23/08/2019,Số trang: 64");
         book.setPrice(48000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Mamma Moo Và Chú Quạ Rắc Rối - Nhanh Lên Nào, Mamma Moo!");
         book.setPhotos("https://www.vinabook.com/images/detailed/346/P88551Escan0001.jpg");
         book.setDescription("Nhà xuất bản: Nxb Mỹ thuật,Nhà phát hành: Huy Hoàng,Mã Sản phẩm: 8935095628476,Khối lượng: 198.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 20.5 x 29.5 cm,Ngày phát hành: 30/08/2019,Số trang: 28");
         book.setPrice(58000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Lưu Bình Dương Lễ");
         book.setPhotos("https://www.vinabook.com/images/detailed/346/P88622Escan0001.jpg");
         book.setDescription("Nhà xuất bản: Nxb Mỹ thuật,Nhà phát hành: Huy Hoàng,Mã Sản phẩm: 8935095628377,Khối lượng: 88.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 17 x 24 cm,Ngày phát hành: 06/09/2019,Số trang: 20");
         book.setPrice(16000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Digital Gold - Rủ Nhau Lên Mạng Đào Vàng");
         book.setPhotos("https://www.vinabook.com/images/detailed/309/P83476Edigital.jpg");
         book.setDescription("Nhà xuất bản: NXB ĐH Kinh tế Quốc dân,Nhà phát hành: Alpha books,Mã Sản phẩm: 8935251409086,Khối lượng: 616.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 16 x 24 cm,Ngày phát hành: 07/2018,Số trang: 392");
         book.setPrice(299000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Cách Mạng Công Nghệ 4.0 (Chuyên Đề 2 - Tháng 4/2018)");
         book.setPhotos("https://www.vinabook.com/images/detailed/332/P86867Escan0001.jpg");
         book.setDescription("Nhà xuất bản: NXB Thanh Niên,Nhà phát hành: Tri Thức Trẻ,Mã Sản phẩm: 9786049702778,Khối lượng: 352.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 19 x 27 cm,Ngày phát hành: 09/2018,Số trang: 80");
         book.setPrice(36000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Bitcoin Thực Hành Những Khái Niệm Cơ Bản Và Cách Sử Dụng Đúng Đồng Tiền Mã Hóa");
         book.setPhotos("https://www.vinabook.com/images/detailed/298/P81324Ebia_truoc.jpg");
         book.setDescription("Nhà xuất bản: NXB ĐH Kinh tế Quốc Dân,Nhà phát hành: Alpha books,Mã Sản phẩm: 8935251407730,Khối lượng: 704.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 16 x 24 cm,Ngày phát hành: 03/2018,Số trang: 540");
         book.setPrice(299000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Kỷ Nguyên Tiền Điện Tử");
         book.setPhotos("https://www.vinabook.com/images/detailed/298/P81306Eky_nguyen.jpg");
         book.setDescription("Nhà xuất bản: NXB ĐH Kinh tế Quốc dân,Nhà phát hành: 1980Books,Mã Sản phẩm: 8936066685122,Khối lượng: 770.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14.5 x 20.5 cm,Ngày phát hành: 03/2018,Số trang: 660");
         book.setPrice(189000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Cuộc Chiến Smart Phone");
         book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/350266_p90181mp60658mbiatruoc.jpg");
         book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090181,Khối lượng: 220.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 28/11/2014,Số trang: 220");
         book.setPrice(30000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Nền Giáo Dục Của Người Giàu");
         book.setPhotos("https://www.vinabook.com/images/detailed/350/P90199E47619_54162.jpg");
         book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090199,Khối lượng: 308.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 06/2013,Số trang: 324");
         book.setPrice(85000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Khoảng Cách Từ Nói Đến Làm");
         book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/350411_p90197m87051.jpg");
         book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090197,Khối lượng: 374.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 23/10/2019,Số trang: 360");
         book.setPrice(99000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

         book = new Book();
         book.setName("Tôi Không Thích Ồn Ào");
         book.setPhotos("https://www.vinabook.com/images/detailed/350/P90229Etoi_khong.jpg");
         book.setDescription("Nhà xuất bản: Nxb Hà Nội,Nhà phát hành: Skybooks,Mã Sản phẩm: 8936186541032,Khối lượng: 154.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 11.5 x 17.5 cm,Ngày phát hành: 25/10/2019,Số trang: 136");
         book.setPrice(86000);
         book.setQuantity(200);
         book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
         book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
         book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
         bookList.add(book);

        book = new Book();
        book.setName("Đàn Ông Già - Đàn Bà Đẹp");
        book.setPhotos("https://www.vinabook.com/images/detailed/346/P88632E1.jpg");
        book.setDescription("Nhà xuất bản: Nxb văn họ,Nhà phát hành: Bachviet,BooksMã Sản phẩm: 9786049767838,Khối lượng: 352.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 27/09/2019,Số trang: 312");
        book.setPrice(85000);
        book.setQuantity(200);
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Đời Như Một Trò Đùa");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90109Escan0001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Nhà phát hành: Nhã Nam,Mã Sản phẩm: 8935235223837,Khối lượng: 396.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 12/10/2019,Số trang: 344");
        book.setPrice(125000);
        book.setQuantity(200);
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        bookRepository.saveAll(bookList);
    }

     void seedingCategory() {
        categoryRepository.deleteAll();
        categoryRepository.resetIncrement();

        Category category = new Category();
        category.setName("Sách văn học trong nước");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

        category = new Category();
        category.setName("Sách văn học nước ngoài");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

        category = new Category();
        category.setName("Sách thiếu nhi");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Blockchain");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

         category = new Category();
         category.setName("Sách Ngoại văn");
         category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
         categoryList.add(category);

        category = new Category();
        category.setName("Sách Kinh tế");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Thường Thức – Đời Sống");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Phát Triển Bản Thân");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        categoryList.add(category);

         category = new Category();
         category.setName("Sách Tin Học - Ngoại Ngữ");
         category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
         categoryList.add(category);

         category = new Category();
         category.setName("Sách Chuyên Ngành");
         category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
         categoryList.add(category);

         category = new Category();
         category.setName("Sách Giáo Khoa - Giáo Trình");
         category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
         categoryList.add(category);

         category = new Category();
         category.setName("Tạp chí - Văn phòng phẩm");
         category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
         categoryList.add(category);

        categoryRepository.saveAll(categoryList);
    }

     void seedingPublisher() {
        publisherRepository.deleteAll();
        publisherRepository.resetIncrement();

        Publisher publisher = new Publisher();
        publisher.setName("NXB Trẻ");
        publisher.setAvatar("https://locrpctricks.files.wordpress.com/2018/04/tumblr_static_f25n7rsd1jscgw4koogg04woc.png");
        publisher.setDescription("NXB Trẻ đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("NXB Phụ Nữ");
        publisher.setAvatar("http://hieusach.vn/upload/8415/20140123/logo_nxbphunu.jpg");
        publisher.setDescription("NXB Phụ Nữ đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Sư Phạm");
        publisher.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTA3600iqI36NmFZvaccmu4fy5W_jbEQIrX6AbNtbNsRMZuzrL&s");
        publisher.setDescription("NXB Sư Phạm đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Thế Giới");
        publisher.setAvatar("https://ebook.vn//uploads/producers/thumb_1514338374.png");
        publisher.setDescription("NXB Thế Giới đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName("Nxb Kim Đồng");
         publisher.setAvatar("https://www.nxbkimdong.com.vn/sites/default/files/default_images/img-default.png");
         publisher.setDescription("NXB Kim Đồng đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName("Nxb Lao Động");
         publisher.setAvatar("https://lazi.vn/files/large/593558a3daf1914");
         publisher.setDescription("Nxb Lao Độngđã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName(" Nxb Tổng hợp thành phố Hồ Chí Minh");
         publisher.setAvatar("https://yt3.ggpht.com/a/AGF-l7-wTwmOS_0WpxxbAcrYds4oYrPkOG7tIq-RYg=s900-c-k-c0xffffffff-no-rj-mo");
         publisher.setDescription("Nxb Tổng hợp thành phố Hồ Chí Minh đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName("Nxb Chính Trị Quốc Gia");
         publisher.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDRt3-IICc3pzHRSaolPcMJss5UdiHPPWyfBfEg-JahrvP4Oev&s");
         publisher.setDescription("Nxb Chính Trị Quốc Gia đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName("Nxb Giáo Dục");
         publisher.setAvatar("http://ppower.vn/wp-content/uploads/2018/01/logo-nxbgd.jpg");
         publisher.setDescription("Nxb Giáo Dục đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName("Nxb Hội Nhà Năn");
         publisher.setAvatar("https://vanhaiphong.com/wp-content/uploads/2017/06/nxb-hi-nh-vn.jpg");
         publisher.setDescription("Nxb Hội Nhà Năn đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

         publisher = new Publisher();
         publisher.setName("Nxb Tư pháp");
         publisher.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNdqNwaeOn9vuHTZprLe46eSHxKhnGTXOiIibnESq5NW-ZnaC4&s");
         publisher.setDescription("Nxb Tư pháp đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
         publisherList.add(publisher);

        publisherRepository.saveAll(publisherList);
    }

    void seedingMember() {
        memberRepository.deleteAll();
        memberRepository.resetIncrement();

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
        memberList.add(member);

        member = new Member();
        member.setUsername("user");
        member.setEmail("user@gmail.com");
        member.setHashPassword(new BCryptPasswordEncoder().encode("123"));
        member.setPhone("012345678");
        member.setAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg");
        member.setAddress("Ha Noi");
        member.setRole(Member.Role.CUSTOMER.getValue());
        member.setGender(Member.Gender.FEMALE.getValue());
        member.setStatus(Member.Status.ACTIVE.getValue());
        memberList.add(member);

        memberRepository.saveAll(memberList);
    }

    void seedingAuthor() {
        authorRepository.deleteAll();
        authorRepository.resetIncrement();
        authorList.add(Author.Builder.anAuthor()
                .withName("Xuân Diệu")
                .withAvatar("https://sites.google.com/site/cauchuyenvanhocnghethuat/_/rsrc/1467889570104/vhnt-56/XD%202.jpg")
                .withDescription("Xuân Diệu (2 tháng 2 năm 1916 – 18 tháng 12 năm 1985) là một trong những nhà thơ lớn của Việt Nam. Ông nổi tiếng từ phong trào Thơ mới với tập Thơ thơ và Gửi hương cho gió. Những bài được yêu thích nhất của Xuân Diệu là thơ tình làm trong khoảng 1936 – 1944, thể hiện một triết lý bi quan, tuyệt vọng về tình ái nhưng lại có một mạch ngầm thúc giục, nhiều khi hừng hực sức sống. Nhờ đó, ông được mệnh danh là \"ông hoàng thơ tình\". Ông từng được Hoài Thanh và Hoài Chân đưa vào cuốn Thi nhân Việt Nam (1942).")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Du")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/T%C6%B0%E1%BB%A3ng_%C4%91%C3%A0i_c%E1%BB%A5_Nguy%E1%BB%85n_Du.jpg/222px-T%C6%B0%E1%BB%A3ng_%C4%91%C3%A0i_c%E1%BB%A5_Nguy%E1%BB%85n_Du.jpg")
                .withDescription("Nguyễn Du (chữ Hán: 阮攸; sinh ngày 3 tháng 1 năm 1766–1820)[cần dẫn nguồn] tên tự Tố Như (素如), hiệu Thanh Hiên (清軒), biệt hiệu Hồng Sơn lạp hộ (鴻山獵戶), Nam Hải điếu đồ (南海釣屠), là một nhà thơ, nhà văn hóa lớn thời Lê mạt, Nguyễn sơ ở Việt Nam. Ông được người Việt kính trọng tôn xưng là \"Đại thi hào dân tộc\".[1]")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nam Cao")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/a/a2/Namcao.jpg")
                .withDescription("Nam Cao (1915 hoặc 1917 – 28 tháng 11 năm 1951) là một nhà văn và cũng là một chiến sĩ, liệt sĩ người Việt Nam. Ông là nhà văn hiện thực lớn (trước Cách mạng), một nhà báo kháng chiến (sau Cách mạng), một trong những nhà văn tiêu biểu nhất thế kỷ 20. Nam Cao có nhiều đóng góp quan trọng đối với việc hoàn thiện phong cách truyện ngắn và tiểu thuyết Việt Nam ở nửa đầu thế kỷ 20.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Huy Cận")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/thumb/9/9f/Huycan.jpg/220px-Huycan.jpg")
                .withDescription("Cù Huy Cận (1919 – 2005), bút danh hoạt động nghệ thuật là Huy Cận, là một chính khách, từng giữ nhiều chức vụ lãnh đạo cao cấp trong chính phủ Việt Nam như Bộ trưởng Bộ Canh nông đầu tiên, Thứ trưởng rồi Bộ trưởng Bộ Văn hóa Nghệ thuật, Bộ trưởng Bộ Văn hóa Giáo dục, Thứ trưởng Bộ Nội vụ, Bộ Kinh tế, Bộ trưởng Tổng Thư ký Hội đồng Bộ trưởng (nay là Bộ trưởng Chủ nhiệm Văn phòng Chính phủ), ngoài ra ông còn là một nhà lãnh đạo chủ chốt của Đảng Dân chủ Việt Nam[cần dẫn nguồn], đồng thời cũng là một trong những nhà thơ xuất sắc nhất của phong trào Thơ mới. Ông từng là Viện sĩ Viện Hàn lâm Thơ Thế giới và Chủ tịch Ủy ban Liên hiệp các Hiệp hội Văn học Việt Nam giai đoạn 1984-1995.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Chế Lan Viên")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/7/72/Chelanvien.jpg")
                .withDescription("Chế Lan Viên (1920-1989) là một nhà thơ, nhà văn hiện đại nổi tiếng ở Việt Nam.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Nhật Ánh")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/9/96/Nguyen_Nhat_Anh.jpg")
                .withDescription("Nguyễn Nhật Ánh (sinh ngày 7 tháng 5 năm 1955) là một nhà văn người Việt. Ông được biết đến qua nhiều tác phẩm văn học về đề tài tuổi mới lớn, các tác phẩm của ông rất được độc giả ưa chuộng và nhiều tác phẩm đã được chuyển thể thành phim.Ông lần lượt viết về sân khấu, phụ trách mục tiểu phẩm, phụ trách trang thiếu nhi và hiện nay là bình luận viên thể thao trên báo Sài Gòn Giải phóng Chủ nhật với bút danh Chu Đình Ngạn. Ngoài ra, ông còn có những bút danh khác như Anh Bồ Câu, Lê Duy Cật, Đông Phương Sóc, Sóc Phương Đông,...")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Ngô Tất Tố")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/thumb/9/92/NgoTatTo.jpg/175px-NgoTatTo.jpg")
                .withDescription("Ngô Tất Tố (1894 – 20 tháng 4 năm 1954) là một nhà văn, nhà báo, nhà Nho học, dịch giả và nhà nghiên cứu có ảnh hưởng lớn ở Việt Nam giai đoạn trước 1954.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Tuân")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/4/4b/NguyenTuan.jpg")
                .withDescription("Nguyễn Tuân (10 tháng 7 năm 1910 – 28 tháng 7 năm 1987), sở trường về tùy bút và ký, được tặng Giải thưởng Hồ Chí Minh về văn học nghệ thuật năm 1996. Tác phẩm của Nguyễn Tuân luôn thể hiện phong cách độc đáo, tài hoa, sự hiểu biết phong phú nhiều mặt và vốn ngôn ngữ, giàu có, điêu luyện. Sách giáo khoa hiện hành xếp ông vào một trong 9 tác giả tiêu biểu của văn học Việt Nam hiện đại. Ông viết văn với một phong cách tài hoa uyên bác và được xem là bậc thầy trong việc sáng tạo và sử dụng Tiếng Việt. Hiện nay, ở Hà Nội có một con đường mang tên ông, nối từ đường Nguyễn Trãi cắt ngang qua các phố Nguyễn Huy Tưởng, Ngụy Như Kon Tum đến đường Lê Văn Lương, nối với phố Hoàng Minh Giám.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nam Cao")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/a/a2/Namcao.jpg")
                .withDescription("Nam Cao (1915 hoặc 1917 – 28 tháng 11 năm 1951) là một nhà văn và cũng là một chiến sĩ, liệt sĩ người Việt Nam. Ông là nhà văn hiện thực lớn (trước Cách mạng), một nhà báo kháng chiến (sau Cách mạng), một trong những nhà văn tiêu biểu nhất thế kỷ 20. Nam Cao có nhiều đóng góp quan trọng đối với việc hoàn thiện phong cách truyện ngắn và tiểu thuyết Việt Nam ở nửa đầu thế kỷ 20.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Tô Hoài")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/thumb/7/73/Nhavan_t%C3%B4_ho%C3%A0i.jpg/240px-Nhavan_t%C3%B4_ho%C3%A0i.jpg")
                .withDescription("Tô Hoài (tên khai sinh: Nguyễn Sen; 27 tháng 9 năm 1920 – 6 tháng 7 năm 2014)[1] là một nhà văn Việt Nam. Một số tác phẩm đề tài thiếu nhi của ông được dịch ra ngoại ngữ. Ông được nhà nước Việt Nam trao tặng Giải thưởng Hồ Chí Minh về Văn học – Nghệ thuật Đợt 1 (1996) cho các tác phẩm: Xóm giếng, Nhà nghèo, O chuột, Dế mèn phiêu lưu ký, Núi Cứu quốc, Truyện Tây Bắc, Mười năm, Xuống làng, Vỡ tỉnh, Tào lường, Họ Giàng ở Phìn Sa, Miền Tây, Vợ chồng A Phủ, Tuổi trẻ Hoàng Văn Thụ.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyên Hồng")
                .withAvatar("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Nguy%E1%BB%85n_Nguy%C3%AAn_H%E1%BB%93ng.jpg/240px-Nguy%E1%BB%85n_Nguy%C3%AAn_H%E1%BB%93ng.jpg")
                .withDescription("Nguyên Hồng (1918-1982), tên thật của ông là Nguyễn Nguyên Hồng, là một nhà văn, nhà thơ Việt Nam.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Trần Đăng Khoa")
                .withAvatar("https://vanvietnam.files.wordpress.com/2015/03/tre1baa7n-c491c483ng-khoa.jpg")
                .withDescription("Trần Đăng Khoa (sinh ngày 26 tháng 4 năm 1958), quê làng Trực Trì, xã Quốc Tuấn, huyện Nam Sách, tỉnh Hải Dương, là một nhà thơ, nhà báo, biên tập viên Tạp chí Văn nghệ Quân đội, hội viên của Hội Nhà văn Việt Nam. Ông nguyên là Trưởng ban Văn học Nghệ thuật, Giám đốc Hệ Phát thanh có hình VOVTV của Đài tiếng nói Việt Nam, Phó Bí thư thường trực Đảng ủy Đài Tiếng nói Việt Nam[3]. Hiện nay, ông giữ chức Phó Chủ tịch Hội Nhà văn Việt Nam, Phó Chủ tịch Liên hiệp VHNT Hà Nội. Ông cũng là Trưởng Ban Chung khảo, Hội đồng Giám khảo Quốc gia của cuộc thi Viết thư quốc tế UPU tại Việt Nam từ năm 2016 đến nay, thay cho Phó chủ tịch Hội Nhà văn Việt Nam (Nguyễn Trí Huân).")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());

        authorRepository.saveAll(authorList);
    }

}
