package happyprogfrog.springbasic;

import happyprogfrog.springbasic.discount.DiscountPolicy;
import happyprogfrog.springbasic.discount.RateDiscountPolicy;
import happyprogfrog.springbasic.member.MemberRepository;
import happyprogfrog.springbasic.member.MemberService;
import happyprogfrog.springbasic.member.MemberServiceImpl;
import happyprogfrog.springbasic.member.MemoryMemberRepository;
import happyprogfrog.springbasic.order.OrderService;
import happyprogfrog.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
