package com.jasper.learningspringfinal.service;

import com.jasper.learningspringfinal.domain.Member;
import com.jasper.learningspringfinal.repository.MemberRepository;
import com.jasper.learningspringfinal.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은이름이 있는 중복 회원X
        validateMemberName(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMemberName(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent( a -> {
                        throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
    }

    /**
     * 전체 멤버 조회
     */
    public List<Member> findMembers(){

        return memberRepository.findAll();

    }

    /**
     * ID로 해당 멤버 조회
     */
    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }

}
