package com.fpt.service.admin;
import com.fpt.entity.Author;
import com.fpt.entity.Member;
import com.fpt.repository.MemberRepository;
import com.fpt.specification.AuthorSpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<Member> getList(int page, int limit) {
        return memberRepository.findAll(PageRequest.of(page - 1, limit));
    }

    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public Page<Member> findAllActive(Specification specification, Pageable pageable) {
        specification = specification
                .and(new AuthorSpecification(new SearchCriteria("status", "!=", Member.Status.DELETED.getValue())));
        return memberRepository.findAll(specification, pageable);
    }

    @Override
    public Member getById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member login(String username, String password) {
        // Tìm tài khoản có email trùng xem tồn tại không.
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if (optionalMember.isPresent()) {
            // So sánh password xem trùng không (trong trường hợp pwd đã mã hoá thì phải mã hoá pwd truyền vào theo muối)
            Member member = optionalMember.get();
            if (member.getHashPassword().equals(password)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public Member register(Member member) {
        member.setHashPassword(passwordEncoder.encode(member.getHashPassword()));
        member.setGender(Member.Gender.MALE.getValue());
        member.setRole(Member.Role.CUSTOMER.getValue());
        member.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        member.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        member.setStatus(Member.Status.ACTIVE.getValue());
        return memberRepository.save(member);
    }

    @Override
    public Member update(long id, Member updateMember) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isPresent()) {
            Member existMember = optionalMember.get();
            existMember.setUsername(updateMember.getUsername());
            existMember.setAvatar(updateMember.getAvatar());
            existMember.setEmail(updateMember.getEmail());
            existMember.setAddress(updateMember.getAddress());
            existMember.setPhone(updateMember.getPhone());
            existMember.setUpdatedAt(updateMember.getUpdatedAt());
        }
        return memberRepository.save(updateMember);
    }

    @Override
    public Member getByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Member getByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Member getByName(String name) {
        return memberRepository.findByUsername(name).orElse(null);
    }

    @Override
    public boolean delete(long id) {
        Member existMember = memberRepository.findById(id).orElse(null);
        if (existMember == null) {
            return false;
        }
        existMember.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        existMember.setStatus(Member.Status.DELETED.getValue());
        memberRepository.save(existMember);
        return true;
    }
}
