package com.jasper.learningspringfinal.repository;

import com.jasper.learningspringfinal.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Jasper");

        repository.save(member);

        Member things = repository.findById(member.getId()).get();

        assertThat(member)
                .isEqualTo(things);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Jasper1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Jasper2");
        repository.save(member2);

        Member result = repository.findByName("Jasper1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Jasper1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Jasper2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


}
