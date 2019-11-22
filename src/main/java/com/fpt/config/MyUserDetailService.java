package com.fpt.config;

import com.fpt.entity.Member;
import com.fpt.service.admin.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {
    @Autowired
    MemberServiceImpl memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.getByName(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(member.getUsername())
                .password(member.getHashPassword())
                .roles(member.getRole())
                .build();
    }
}
