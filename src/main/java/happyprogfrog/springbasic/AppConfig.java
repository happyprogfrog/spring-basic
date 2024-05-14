package happyprogfrog.springbasic;

import happyprogfrog.springbasic.discount.DiscountPolicy;
import happyprogfrog.springbasic.discount.RateDiscountPolicy;
import happyprogfrog.springbasic.member.MemberRepository;
import happyprogfrog.springbasic.member.MemberService;
import happyprogfrog.springbasic.member.MemberServiceImpl;
import happyprogfrog.springbasic.member.MemoryMemberRepository;
import happyprogfrog.springbasic.order.OrderService;
import happyprogfrog.springbasic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
