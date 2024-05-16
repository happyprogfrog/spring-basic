package happyprogfrog.springbasic.discount;

import happyprogfrog.springbasic.member.Grade;
import happyprogfrog.springbasic.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int disCountFixAmount = 1000; // 1000원 정액 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return disCountFixAmount;
        }

        return 0;
    }
}
