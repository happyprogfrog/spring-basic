package happyprogfrog.springbasic.discount;

import happyprogfrog.springbasic.annotation.MainDiscountPolicy;
import happyprogfrog.springbasic.member.Grade;
import happyprogfrog.springbasic.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
