package happyprogfrog.springbasic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: A 사용자가 10000 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B 사용자가 20000 주문
        statefulService2.order("userB", 20000);

        // ThreadA: A 사용자의 주문 금액 조회
        int price = statefulService1.getPrice();

        // ThreadA: A 사용자는 10000원을 기대했지만, 20000원이 출력
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
