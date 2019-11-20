package com.fpt.service.admin;

import com.fpt.entity.Member;
import org.springframework.data.domain.Page;

public interface MemberService {

    Page<Member> getList(int page, int limit);

    Member getById(long id);

    // Thực hiện xác thực người dùng.
    Member login(String email, String password);

    // Đăng ký tài khoản, mã hoá mật khẩu...
    Member register(Member member);

    // Update thông tin tài khoản theo email.
    Member update(long id, Member updateMember);

    Member getByEmail(String email);

    Member getByName(String name);

    boolean delete(long id);
}
