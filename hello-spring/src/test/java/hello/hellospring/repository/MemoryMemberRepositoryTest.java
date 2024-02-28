package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/* 모든 테스트는 구현 순서가 보장되지 않음. 순서에 의존적으로 설계하면 안되고 독립적으로 실행될 수 있어야함.
 하나의 테스트가 끝날때 마다 공용 데이터는 지워준다.
 +테스트를 먼저 만들고 구현 클래스를 시작하는 것을 TDD 라고 한다.
*/

class MemoryMemberRepositoryTest { //굳이 public 하지 않아도 됨. 가져다 쓸 건 없으니
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 끝날때 까지 메모리를 지워줌
    @AfterEach //각 테스트가 끝날때 마다 실행됨. 콜백함수 처럼.
    public void afterEach(){
        repository.clearStore(); //저장소를 지우도록 한다.
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //검증
        Member result = repository.findById(member.getId()).get();
        //Optional 객체를 가져오기 위해 .get() 한다. 이보다 더 많이 쓰고 좋은 방법이 있지만 현재 테스트 코드 이기때문에 우선 그냥 씀.

        //방법 1 System.out.println
        //이렇게 확인해도 가능은 하나, 매번 문자열 찾아 확인해야 하는 번거로움.
        //System.out.println("result = " + (result == member));

        //방법 2 Assertions.assertEquals
        //Assertions.assertEquals(member, result); //Expected 기댓값, Actual 결과값

        //방법 3 Assertions.assertThat
        //Assertions.assertEquals 과 비슷함.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
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
