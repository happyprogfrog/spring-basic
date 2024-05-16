package happyprogfrog.springbasic;

import happyprogfrog.springbasic.member.MemberRepository;
import happyprogfrog.springbasic.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "happyprogfrog.springbasic",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // @Bean(name = "memoryMemberRepository")
    // public MemberRepository memberRepository() {
    //     System.out.println("AutoAppConfig!!");
    //     return new MemoryMemberRepository();
    // }
}
