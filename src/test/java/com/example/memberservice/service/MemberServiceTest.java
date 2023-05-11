package com.example.memberservice.service;

import com.example.memberservice.domain.Member;
import com.example.memberservice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 회원가입
    @Test
    void join() {
        // given 무언가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        // when 이걸로 실행했을 때
        Long saveId = memberService.join(member);

        // then 이러한 결과가 도출되어야
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    // 중복 회원 예외
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        // when
        memberService.join(member1);



        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("already exist member name!");

        // 두 번째 방법
        // org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2))

        // 첫 번째 방법
        /*
        try {
            memberService.join(member2); // 에러가 발생해야 하는 코드
            // fail("예외가 발생해야 합니다.");
            // fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("already exist member name!");

        }
        */


        // then

    }

    @Test
    void findMembers() {
        // given

        // when

        // then

    }

    @Test
    void findOne() {
        // given

        // when

        // then

    }
}