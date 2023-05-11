package com.example.memberservice.service;

import com.example.memberservice.domain.Member;
import com.example.memberservice.repository.MemberRepository;
import com.example.memberservice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // 테스트 케이스 생성 시 Command + Shift + T
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        checkDuplicatedMemberName(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 중복 이름 확인
    private void checkDuplicatedMemberName(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("already exist member name!");
            });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
