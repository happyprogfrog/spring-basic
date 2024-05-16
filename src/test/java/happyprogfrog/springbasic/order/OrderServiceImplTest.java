package happyprogfrog.springbasic.order;

import happyprogfrog.springbasic.discount.FixDiscountPolicy;
import happyprogfrog.springbasic.member.Grade;
import happyprogfrog.springbasic.member.Member;
import happyprogfrog.springbasic.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberRepository.save(member);

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(member.getId(), "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}