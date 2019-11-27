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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Thạch Sanh");
        book.setPhotos("https://www.vinabook.com/images/detailed/346/P88623Escan0001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Mỹ thuật,Nhà phát hành: Huy Hoàng,Mã Sản phẩm: 8935095628407,Khối lượng: 110.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 17 x 24 cm,Ngày phát hành: 06/09/2019,Số trang: 28");
        book.setPrice(16000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Sọ Dừa");
        book.setPhotos("https://www.vinabook.com/images/detailed/346/P88619Escan0001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Mỹ thuật,Nhà phát hành: Huy Hoàng,Mã Sản phẩm: 8935095628384,Khối lượng: 88.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 17 x 24 cm,Ngày phát hành: 06/09/2019,Số trang: 20");
        book.setPrice(16000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Cây Khế (Song Ngữ Anh - Việt)");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90157Ecay_khe.jpg");
        book.setDescription("Nhà xuất bản: Nxb văn học,Mã Sản phẩm: 1130000090157,Khối lượng: 132.00 gam,Ngôn ngữ: Tiếng Anh, Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 21 x 18.5 cm,Ngày phát hành: 10/11/2019,Số trang: 24");
        book.setPrice(28000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Cổ Tích Trăng Non - Sự Tích Khoai Lang");
        book.setPhotos("https://www.vinabook.com/images/detailed/345/P88497Esu_tich_khoai.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: Huy Hoàng,Mã Sản phẩm: 8935095628124,Khối lượng: 110.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 17 x 24 cm,Ngày phát hành: 26/08/2019,Số trang: 24");
        book.setPrice(16000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Cuộc Cách Mạng Blockchain");
        book.setPhotos("https://www.vinabook.com/images/detailed/302/P82462Eb__a_tr_____c.jpg");
        book.setDescription("Nhà xuất bản: NXB ĐH Kinh tế Quốc dân,Nhà phát hành: Alpha books,Mã Sản phẩm: 8935251408591,Khối lượng: 792.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 16 x 24 cm,Ngày phát hành: 05/2018,Số trang: 508");
        book.setPrice(299000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("8 Bước Dẫn Đến Thành Công Của Các Nhà Doanh Nghiệp");
        book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/11623_p17985.jpg");
        book.setDescription("Nhà xuất bản: Nxb Văn hóa Thông tin,Nhà phát hành: Minh Lâm,Ngày phát hành: 03/5005,Số trang: 280");
        book.setPrice(30000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Độc Tôn Để Trường Tồn");
        book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/349890_p90126mdocton.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Mã Sản phẩm: 1130000090126,Khối lượng: 396.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 15 x 23 cm,Ngày phát hành: 03/11/2019,Số trang: 320");
        book.setPrice(149000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("21 Nguyên Tắc Vàng Của Nghệ Thuật Lãnh Đạo");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90127Et__i_b___n___21_nguyen.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Mã Sản phẩm: 1130000090127,Khối lượng: 418.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/11/2019,Số trang: 352");
        book.setPrice(129000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Mã Vân - Triết Lý Sống Của Tôi");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90128Et__i_b___n___ma_van.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Mã Sản phẩm: 1130000090128,Khối lượng: 506.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/11/2019,Số trang: 440");
        book.setPrice(169000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Trí Tuệ Xúc Cảm - Ứng Dụng Trong Công Việc");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90129Et__i_b___n___tri_tue.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Mã Sản phẩm: 1130000090129,Khối lượng: 528.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/11/2019,Số trang: 448");
        book.setPrice(159000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Nghệ Thuật Tư Duy Chiến Lược");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90195Ep86819enghe_thuat.png");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090195,Khối lượng: 836.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 16 x 24 cm,Ngày phát hành: 26/03/2019,Số trang: 560");
        book.setPrice(239000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Năng Lực Kết Nối");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90189Ep69624e001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090189,Khối lượng: 330.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 28/06/2016,Số trang: 328");
        book.setPrice(119000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("24 Bài Học Sống Còn Để Đầu Tư Thành Công Trên Thị Trường Chứng Khoán");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90188Ep82335eb__a_tr_____c.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090188,Khối lượng: 264.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/2018,Số trang: 252");
        book.setPrice(99000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Kích Thích Não Bộ Cho Trẻ Bằng Tính Nhẩm");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90162Escan0001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Nhà phát hành: Thái Hà,Mã Sản phẩm: 8935280904026,Khối lượng: 286.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 19 cm,Ngày phát hành: 22/10/2019,Số trang: 236");
        book.setPrice(89000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Kỹ Năng Sinh Tồn Cho Trẻ - Tập 1: Sống Khỏe Ư? Đơn Giản Cực!");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90002Eml.jpg");
        book.setDescription("Nhà xuất bản: NXB Dân Trí,Nhà phát hành: Alpha books,Mã Sản phẩm: 8935251414233,Khối lượng: 220.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 15 x 23 cm,Ngày phát hành: 12/10/2019,Số trang: 144");
        book.setPrice(75000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Những Ngày Đầy Nắng");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90102Enhung_ngay.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Nhà phát hành: Thái Hà,Mã Sản phẩm: 8935280903890,Khối lượng: 396.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 16 x 23 cm,Ngày phát hành: 09/10/2019,Số trang: 176");
        book.setPrice(89000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Mẹ Ơi, Ở Đâu Con Mới Được An Toàn?");
        book.setPhotos("https://www.vinabook.com/images/detailed/348/P88829Eme_oi.jpg");
        book.setDescription("Nhà xuất bản: NXB Thanh Niên,Nhà phát hành: Alpha books,Mã Sản phẩm: 8936158590945,Khối lượng: 286.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 07/10/2019,Số trang: 248");
        book.setPrice(119000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Khởi Sự Ăn Chay");
        book.setPhotos("https://www.vinabook.com/images/detailed/348/P90035Escan0002.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Nhà phát hành: AZ books,Mã Sản phẩm: 8936186540806,Khối lượng: 726.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 17 x 24 cm,Ngày phát hành: 02/10/2019,Số trang: 256");
        book.setPrice(179000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Món Ngon Xứ Huế (Song Ngữ)");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90201Escan0001.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: NXB Phụ Nữ,Mã Sản phẩm: 9786045668283,Khối lượng: 638.00 gam,Ngôn ngữ: Tiếng Anh, Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 19 x 23 cm,Ngày phát hành: 09/2019,Số trang: 212");
        book.setPrice(189000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Tôi Và Paris - Câu Chuyện Một Dòng Sông (Tái Bản 2018)");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90193Ep90192ep85706escan0001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090193,Khối lượng: 308.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 12/2018,Số trang: 360");
        book.setPrice(129000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Con Mẹ Thật Giỏi");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90180Ep71773e001.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động - Xã hội,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090180,Khối lượng: 264.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 19/10/2016,Số trang: 256");
        book.setPrice(75000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Khi Cha Mẹ Làm Teen Phát Điên");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90179Ep57936exx965.jpg");
        book.setDescription("Nhà xuất bản: NXB Dân Trí,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090179,Khối lượng: 286.00 gam,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/2014,Số trang: 300");
        book.setPrice(75000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Dạy Con Dạy Cha");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90178Ep70796eday_con_day_cha.jpg");
        book.setDescription("Nhà xuất bản: Nxb văn học,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090178,Khối lượng: 242.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 23/08/2016,Số trang: 248");
        book.setPrice(69000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Em Đồng Ý Ly Hô");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90176Ep72551ebia_truoc.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090176,Khối lượng: 198.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 06/12/2016,Số trang: 220");
        book.setPrice(65000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Mẹ Trẻ Chăm Con Khỏe");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90175Ep68883ebt.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090175,Khối lượng: 176.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 16/05/2016,Số trang: 176");
        book.setPrice(65000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Giữ Ấm Cho Bé");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90171Ep69119ebt.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090171,Khối lượng: 154.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 19 cm,Ngày phát hành: 25/05/2016,Số trang: 136");
        book.setPrice(54000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Này Sản Phụ, Cô Làm Ơn Ăn Ít Đi Nhé");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90170Ep68713ebt.jpg");
        book.setDescription("Nhà xuất bản: NXB Phụ Nữ,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090170,Khối lượng: 198.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/2016,Số trang: 180");
        book.setPrice(54000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Ẩm Thực - Con Dao Hai Lưỡi - Tập 6: Giải Phẫu Thực Phẩm Không An Toàn");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90169Ep63395e002.jpg");
        book.setDescription("Nhà xuất bản: Nxb Lao động,Nhà phát hành: Alpha books,Mã Sản phẩm: 1130000090169,Khối lượng: 154.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 31/03/2015,Số trang: 132");
        book.setPrice(35000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Sống Những Ngày Không Hối Tiếc");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90211Esong_nhung_ngay.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Mã Sản phẩm: 1130000090211,Khối lượng: 198.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20 cm,Ngày phát hành: 15/11/2019,Số trang: 158");
        book.setPrice(89000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Khai Thác Sức Mạnh Tiềm Thức");
        book.setPhotos("https://www.vinabook.com/images/detailed/268/P76484E023.jpg");
        book.setDescription("Nhà xuất bản: NXB Hồng Đức,Mã Sản phẩm: 1130000076484,Khối lượng: 330.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 15/11/2019,Số trang: 336");
        book.setPrice(158000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Cách Ta Nghĩ Vẽ Đường Đời Ta Đi");
        book.setPhotos("https://www.vinabook.com/images/detailed/349/P90124Ecach_ta_nghi.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Mã Sản phẩm: 1130000090124,Khối lượng: 308.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/11/2019,Số trang: 262");
        book.setPrice(99000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Chuỗi Thói Quen");
        book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/349888_p90125mchuoithoiquen.jpg");
        book.setDescription("Nhà xuất bản: Nxb Công Thương,Mã Sản phẩm: 1130000090125,Khối lượng: 484.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 20.5 cm,Ngày phát hành: 03/11/2019,Số trang: 416");
        book.setPrice(159000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Bộ Sách Chân Dung Những Người Thay Đổi Thế Giới - Walt Disney Là Ai?");
        book.setPhotos("https://www.vinabook.com/images/thumbnails/product/240x/349898_p90130mtibanwalt.jpg");
        book.setDescription("Nhà xuất bản: NXB Dân Trí,Mã Sản phẩm: 1130000090130,Khối lượng: 132.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 13 x 19 cm,Ngày phát hành: 03/11/2019,Số trang: 108");
        book.setPrice(49000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Tôi Không Thích Ồn Ào");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90229Etoi_khong.jpg");
        book.setDescription("Nhà xuất bản: Nxb Hà Nội,Nhà phát hành: Skybooks,Mã Sản phẩm: 8936186541032,Khối lượng: 154.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 11.5 x 17.5 cm,Ngày phát hành: 25/10/2019,Số trang: 136");
        book.setPrice(165000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Tôi Không Muốn Sống Đời Tẻ Nhạt");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90226Etoi_khong.png");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Nhà phát hành: AZ books,Mã Sản phẩm: 8936186541018,Khối lượng: 440.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14.5 x 20 cm,Ngày phát hành: 25/10/2019,Số trang: 352");
        book.setPrice(98000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Sách Đen");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90225Esach_den.jpg");
        book.setDescription("Nhà xuất bản: Nxb Thế giới,Nhà phát hành: AZ book,Mã Sản phẩm: 8936186541070,Khối lượng: 220.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14.5 x 20 cm,Ngày phát hành: 25/10/2019,Số trang: 148");
        book.setPrice(79000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
        book.addCategory(categoryList.get(new Random().nextInt(categoryList.size())));
        book.setPublisher(publisherList.get(new Random().nextInt(publisherList.size())));
        book.setAuthor(authorList.get(new Random().nextInt(authorList.size())));
        bookList.add(book);

        book = new Book();
        book.setName("Thế Giới Nợ Tôi Một Người Là Bạn");
        book.setPhotos("https://www.vinabook.com/images/detailed/350/P90228Escan0001.jpg");
        book.setDescription("Nhà xuất bản: NXB Thanh Niên,Nhà phát hành: AZ books,Mã Sản phẩm: 8936186541117,Khối lượng: 396.00 gam,Ngôn ngữ: Tiếng Việt,Định dạng: Bìa mềm,Kích thước: 14 x 20.5 cm,Ngày phát hành: 25/10/2019,Số trang: 288");
        book.setPrice(99000);
        book.setQuantity(200);
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        book.setStatus(Book.Status.ACTIVE.getValue());
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
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách văn học nước ngoài");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách thiếu nhi");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Blockchain");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Ngoại văn");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Kinh tế");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Thường Thức – Đời Sống");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Phát Triển Bản Thân");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Tin Học - Ngoại Ngữ");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Chuyên Ngành");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Sách Giáo Khoa - Giáo Trình");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
        categoryList.add(category);

        category = new Category();
        category.setName("Tạp chí - Văn phòng phẩm");
        category.setThumbnail("https://bitex.com.vn/vnt_upload/download/09_2013/thumbs/500_sach_huong_dan_casio_18.png");
        category.setStatus(Category.Status.ACTIVE.getValue());
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
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("NXB Phụ Nữ");
        publisher.setAvatar("http://hieusach.vn/upload/8415/20140123/logo_nxbphunu.jpg");
        publisher.setDescription("NXB Phụ Nữ đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Sư Phạm");
        publisher.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTA3600iqI36NmFZvaccmu4fy5W_jbEQIrX6AbNtbNsRMZuzrL&s");
        publisher.setDescription("NXB Sư Phạm đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Thế Giới");
        publisher.setAvatar("https://ebook.vn//uploads/producers/thumb_1514338374.png");
        publisher.setDescription("NXB Thế Giới đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Kim Đồng");
        publisher.setAvatar("https://www.nxbkimdong.com.vn/sites/default/files/default_images/img-default.png");
        publisher.setDescription("NXB Kim Đồng đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Lao Động");
        publisher.setAvatar("https://lazi.vn/files/large/593558a3daf1914");
        publisher.setDescription("Nxb Lao Độngđã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName(" Nxb Tổng hợp thành phố Hồ Chí Minh");
        publisher.setAvatar("https://yt3.ggpht.com/a/AGF-l7-wTwmOS_0WpxxbAcrYds4oYrPkOG7tIq-RYg=s900-c-k-c0xffffffff-no-rj-mo");
        publisher.setDescription("Nxb Tổng hợp thành phố Hồ Chí Minh đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Chính Trị Quốc Gia");
        publisher.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDRt3-IICc3pzHRSaolPcMJss5UdiHPPWyfBfEg-JahrvP4Oev&s");
        publisher.setDescription("Nxb Chính Trị Quốc Gia đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Giáo Dục");
        publisher.setAvatar("http://ppower.vn/wp-content/uploads/2018/01/logo-nxbgd.jpg");
        publisher.setDescription("Nxb Giáo Dục đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Hội Nhà Năn");
        publisher.setAvatar("https://vanhaiphong.com/wp-content/uploads/2017/06/nxb-hi-nh-vn.jpg");
        publisher.setDescription("Nxb Hội Nhà Năn đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        publisherList.add(publisher);

        publisher = new Publisher();
        publisher.setName("Nxb Tư pháp");
        publisher.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNdqNwaeOn9vuHTZprLe46eSHxKhnGTXOiIibnESq5NW-ZnaC4&s");
        publisher.setDescription("Nxb Tư pháp đã xuất bản được gần 1000 ấn phẩm các loại, trong đó chủ yếu là sách giáo trình (Đại học và sau Đại học); sách hướng dẫn giảng dạy; sách phục vụ công tác nghiên cứu và học tập thuộc các lĩnh vực khác nhau như khoa học giáo dục, khoa học xã hội nhân văn, khoa học tự nhiên…; bên cạnh đó, là hàng trăm đầu sách là tác phẩm văn học (thơ, tiểu thuyết, truyện…); sách chính trị, xã hội của các tác giả các đơn vị, các tổ chức chính trị xã hội trong khu vực và trên toàn quốc.");
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
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
        authorList.add(Author.Builder.anAuthor()
                .withName("Bùi Ngọc Tấn")
                .withAvatar("http://khoavanhoc-ngonngu.edu.vn/images/Nghiencuu/VanhocVietNam/Bui-Ngoc-Tan.jpg")
                .withDescription("Bùi Ngọc Tấn (1934 – 2014, Hải Phòng) là một sự phục sinh kỳ diệu. Ông viết văn, làm báo từ năm 20 tuổi, đã được dư luận thừa nhận. Nhưng từ 1968 đến 1973, ông bị bắt đi cải tạo vì tội “tuyên truyền chống Đảng” mà không được xét xử. Trải qua nhiều nghề lao động chân tay để mưu sinh, mãi đến năm 1993, ông mới viết lại. 20 năm cuối đời, trang viết của ông bừng sáng. Đặc biệt, từ 2000-2015, ông có sáu tác phẩm, tất cả đều ánh lên một vẻ đẹp kỳ lạ qua trải nghiệm đớn đau cùng khổ mà tràn đầy lòng bao dung của một tù nhân: Chuyện kể năm 2000, tiểu thuyết; Rừng xưa xanh lá, ký, 2004 (Giải B văn xuôi (không có giải A) của Hội Nhà văn Việt Nam 2004); Kiếp chó, tập truyện ngắn, 2007; Biển và chim bói cá, tiểu thuyết, 2008 (Giải Henri Queffenlec, Pháp, năm 2012); Viết về bè bạn, ký, 2012; Thời biến đổi gien 2015. Chuyện kể năm 2000 được xem là tác phẩm hay nhất của Bùi Ngọc Tấn. Ông viết nó trong 10 năm, được in năm 2000 (Nxb.Thanh Niên), nhưng bị thu hồi và thiêu hủy ngay sau đó. Hiện nay tiểu thuyết đã được dịch ra tiếng Anh, tiếng Đức và tiếng Pháp.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Ngọc Tư ")
                .withAvatar("http://khoavanhoc-ngonngu.edu.vn/images/Nghiencuu/VanhocVietNam/Nguyen-Ngoc-Tu.JPG")
                .withDescription("Nguyễn Ngọc Tư (1976, Cà Mau) là một tài năng đặc biệt của Việt Nam. Xuất hiện từ năm 2000, viết rất đều tay, đến nay bà đã có 24 tác phẩm gồm các thể loại: truyện ngắn, tạp văn, tiểu thuyết, thơ. Đã nhận được hai giải thưởng của Hội nhà văn Việt Nam: Ngọn đèn không tắt (2001) và Cánh đồng bất tận (2006). Phong cách riêng xuất hiện ngay từ trang viết đầu tay: giọng văn đậm đà vị mặn của đời sống, chân thực mà sâu lắng, giản dị và tinh tế, tươi tắn và táo bạo. Dù có một khởi điểm học vấn không cao và sống nơi mảnh đất tận cùng đất nước (Cà Mau) nhưng trang viết của Nguyễn Ngọc Tư luôn chạm vào tâm điểm của xã hội Việt Nam với một cách viết hiện đại và quan điểm mới mẻ. Cánh đồng bất tận được tái bản nhiều lần và đã chuyển thể thành một bộ phim điện ảnh công chiếu năm 2010, được dư luận đánh giá cao.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("")
                .withAvatar("http://khoavanhoc-ngonngu.edu.vn/images/Nghiencuu/VanhocVietNam/Nguyen-binh-phuong.JPG")
                .withDescription("")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Bình Phương")
                .withAvatar("http://khoavanhoc-ngonngu.edu.vn/images/Nghiencuu/VanhocVietNam/Nguyen-binh-phuong.JPG")
                .withDescription("Nguyễn Bình Phương (1965, Thái Nguyên), đại tá, tổng biên tập tạp chí Văn Nghệ Quân Đội, Ủy viên Ban chấp hành Hội nhà văn Việt Nam. Được xem là nhà văn luôn có ý thức cách tân về kỹ thuật. Đã xuất bản 8 tiểu thuyết và 6 tập thơ, trong đó có hai tác phẩm được giải thưởng của Hội nhà văn Hà Nội: Buổi câu hờ hững (thơ, 2013) và Mình và họ (tiểu thuyết, 2015). Bút pháp sắc cạnh, hiện đại, thoáng chất ma quái, huyền ảo, Nguyễn Bình Phương thể hiện nét đặc biệt của con người và không gian miền Bắc Việt Nam: cái rối ren trong tâm hồn của từng cá nhân và cái hỗn độn thời đại. Mình và họ là tác phẩm được chú ý nhiều nhất, nói về sự hòa trộn giữa quá khứ (cuộc chiến tranh biên giới phía Bắc 1979) và hiện tại (bóng tối tội ác và bạo lực của lớp trẻ làm ăn, kinh doanh).")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nhật Chiêu")
                .withAvatar("http://khoavanhoc-ngonngu.edu.vn/images/Nghiencuu/VanhocVietNam/NhatChieu.JPG")
                .withDescription("Nhật Chiêu (1951, Sài Gòn), khởi đầu bằng dạy học, nghiên cứu và dịch thuật (giảng viên Khoa Văn học và ngôn ngữ Trường đại học Khoa học xã hội và Nhân văn, ĐHQG Thành phố Hồ Chí Minh), từ 2007 đến nay chuyển sang viết văn và đã có 5 tập truyện ngắn: Người ăn gió và quả chuông bay đi (2007), Mưa mặt nạ (2008), Viết tên trên nước (2010), Lời tiên tri của giọt sương (2011), Ân ái với hư không (2015) và 1 tập thơ: Tôi là một kẻ khác – Thơ giao lời kể và thơ tượng quẻ. Tác phẩm của Nhật Chiêu tinh, gọn, rất kén người đọc. Hầu hết đều được viết bằng tư duy và kỹ thuật hậu hiện đại. Người đọc có thể tìm thấy ở đó độ nén cao của cảm xúc và tư tưởng, những nút thắt của các giao điểm văn hóa Đông- Tây, Việt Nam- thế giới. Tất cả hình thành từ những chiêm nghiệm trong không gian chữ nghĩa, sách vở hơn là những trải nghiệm thực của đời sống.Vẻ đẹp và tính mới mẻ của trang viết Nhật Chiêu tạo cảm hứng cho những người trẻ làm việc ở đại học, họ xem đó là những mẫu tốt để thực hành các lý thuyết văn học hiện đại")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Huy Tưởng")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/1/15/Nguy%E1%BB%85n_Huy_T%C6%B0%E1%BB%9Fng.jpg")
                .withDescription("Nguyễn Huy Tưởng (1912 – 1960) là một nhà văn, nhà viết kịch Việt Nam nổi tiếng. Ông là tác giả của những tiểu thuyết lịch sử, vở kịch lớn như: Vũ Như Tô, Đêm hội Long Trì, Bắc Sơn, Sống mãi với thủ đô.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Nguyễn Tuân")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/4/4b/NguyenTuan.jpg")
                .withDescription("Nguyễn Tuân (13 tháng 10 năm 1910 – 28 tháng 7 năm 1987), sở trường về tùy bút và ký, được tặng Giải thưởng Hồ Chí Minh về văn học nghệ thuật năm 1996. Tác phẩm của Nguyễn Tuân luôn thể hiện phong cách độc đáo, tài hoa, sự hiểu biết phong phú nhiều mặt và vốn ngôn ngữ, giàu có, điêu luyện. Sách giáo khoa hiện hành xếp ông vào một trong 9 tác giả tiêu biểu của văn học Việt Nam hiện đại. Ông viết văn với một phong cách tài hoa uyên bác và được xem là bậc thầy trong việc sáng tạo và sử dụng Tiếng Việt. Hiện nay, ở Hà Nội có một con đường mang tên ông, nối từ đường Nguyễn Trãi cắt ngang qua các phố Nguyễn Huy Tưởng, Ngụy Như Kon Tum đến đường Lê Văn Lương, nối với phố Hoàng Minh Giám.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Hoài Thanh")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/thumb/a/a1/Ho%C3%A0i_thanh.jpg/240px-Ho%C3%A0i_thanh.jpg")
                .withDescription("Hoài Thanh (1909 – 1982) có tên khai sinh là Nguyễn Đức Nguyên (ngoài ra ông còn sử dụng các bút danh khác như Văn Thiên, Le Nhà Quê), là một nhà phê bình văn học Việt Nam, đã có những đóng góp về mặt phê bình, lý luận để khẳng định Thơ mới trong văn học Việt Nam thế kỉ XX. Tác phẩm Thi nhân Việt Nam do ông và em trai Hoài Chân viết đã đưa tác giả lên vị trí một nhà phê bình lớn của nền văn học Việt Nam đầu thế kỷ 20.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Thế Lữ")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/1/15/The_Lu.jpg")
                .withDescription("Thế Lữ (10 tháng 6 năm 1907 – 3 tháng 6 năm 1989; tên khai sinh là Nguyễn Đình Lễ (có tài liệu khác ghi tên ông là Nguyễn Thứ Lễ) là nhà thơ, nhà văn, nhà hoạt động sân khấu người Việt Nam. Thế Lữ nổi danh trên văn đàn vào những năm 1930, với những tác phẩm Thơ mới, đặc biệt là bài Nhớ rừng, cùng những tác phẩm văn xuôi, tiêu biểu là tập truyện Vàng và máu (1934). Trở thành thành viên của nhóm Tự Lực văn đoàn kể từ khi mới thành lập (1934), ông hầu hết hoạt động sáng tác văn chương trong thời gian là thành viên của nhóm, đồng thời cũng đảm nhận vai trò một nhà báo, nhà phê bình, biên tập viên mẫn cán của các tờ báo Phong hóa và Ngày nay.")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorList.add(Author.Builder.anAuthor()
                .withName("Đoàn Phú Tứ")
                .withAvatar("https://upload.wikimedia.org/wikipedia/vi/b/be/Doanphutu.jpg")
                .withDescription("Đoàn Phú Tứ (1910 - 1989) là một nhà soạn kịch, nhà thơ, dịch giả Việt Nam nổi danh từ thời tiền chiến. Khi viết, ông ký tên thật hoặc các bút danh: Ngộ Không, Tam Tinh, Tuấn Đô,...")
                .withStatus(Author.Status.ACTIVE.getValue())
                .build());
        authorRepository.saveAll(authorList);
    }

}
