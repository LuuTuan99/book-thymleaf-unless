package com.fpt.service.admin;

import com.fpt.entity.Member;
import com.fpt.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Page<Member> getList(int page, int limit) {
        return memberRepository.findAll(PageRequest.of(page - 1, limit));
    }

    @Override
    public Member getById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member login(String email, String password) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            if (member.getHashPassword().equals(password)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public Member register(Member member) {
        member.setEmail(member.getEmail());
        return null;
    }

    @Override
    public Member update(long id, Member member) {
        return null;
    }

    @Override
    public Member getByEmail(String email) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
