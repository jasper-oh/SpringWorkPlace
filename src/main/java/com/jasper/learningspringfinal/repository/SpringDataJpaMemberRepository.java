package com.jasper.learningspringfinal.repository;

import com.jasper.learningspringfinal.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

//    findByName 메서드 이름의 규칙에 따라 JPQL 이 select m from Member m where m.name = ? 로 쿼리를 만들어준다.
    @Override
    Optional<Member> findByName(String name);
}
