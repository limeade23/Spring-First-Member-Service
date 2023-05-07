package repository;

import com.example.memberservice.domain.Member;
import com.example.memberservice.repository.MemberRepository;
import com.example.memberservice.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("test");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 단순하게 한다면
        // System.out.println("result = " + (result == member)); // result = true

        // Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    /*
    테스트를 진행할 때 위 또는 아래 데이터가 테스트가 끝난 후에도 유지되어
    다른 테스트에 지장이 갈 수 있기에 각 테스트가 끝나면 clear 해주는 것이 좋다.
    상단의 @AfterEach 내용 참고
     */

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
