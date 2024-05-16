package happyprogfrog.springbasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PrototypeProviderTest {

    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Test
    void providerTest2() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBeanV2.class, PrototypeBean.class);

        ClientBeanV2 clientBean1 = ac.getBean(ClientBeanV2.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanV2 clientBean2 = ac.getBean(ClientBeanV2.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Test
    void providerTest3() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBeanV3.class, PrototypeBean.class);

        ClientBeanV3 clientBean1 = ac.getBean(ClientBeanV3.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanV3 clientBean2 = ac.getBean(ClientBeanV3.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }
    static class ClientBeanV3 {

        @Autowired
        Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }

    static class ClientBeanV2 {

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    static class ClientBean {

        @Autowired
        private ApplicationContext ac;

        public int logic() {
            // 싱글톤 빈이 프로토타입을 사용할 때마다, 스프링 컨테이너에 새로 요청한다
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
