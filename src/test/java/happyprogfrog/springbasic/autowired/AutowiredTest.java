package happyprogfrog.springbasic.autowired;

import happyprogfrog.springbasic.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 의존관계가 없으면 이 메서드 자체가 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("TestBean.setNoBean1 = " + member);
        }

        // null 호출
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("TestBean.setNoBean2 = " + member);
        }

        // optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("TestBean.setNoBean3 = " + member);
        }
    }
}
