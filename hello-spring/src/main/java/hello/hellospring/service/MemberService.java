package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //@Service : 스프링이 스프링 컨테이너에 서비스를 등록해줌
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     * @param member
     * @return
     */
    public long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //result.orElseGet() //이렇게도 가능함 
        result.ifPresent(m->{ //optional 객체이기 때문에 사용할 수 있음
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{ //optional 객체이기 때문에 사용할 수 있음
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
