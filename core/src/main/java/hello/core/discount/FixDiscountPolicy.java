package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //== 사용해도 되냐 ? enum 과 비교할 경우 == 사용 가능
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}
