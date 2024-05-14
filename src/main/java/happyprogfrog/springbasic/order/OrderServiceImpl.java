package happyprogfrog.springbasic.order;

import happyprogfrog.springbasic.discount.DiscountPolicy;
import happyprogfrog.springbasic.discount.FixDiscountPolicy;
import happyprogfrog.springbasic.member.Member;
import happyprogfrog.springbasic.member.MemberRepository;
import happyprogfrog.springbasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
