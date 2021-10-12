## Spring Adventure Final

### Context

1. Basic Concept
2. Learning Schedule
3. .....

### 1. Basic Concept

웹 개발 ??

- 정적컨텐츠

- MVC 와 템플릿 엔진

View -> 화면에 보여지는 것에 모든것을 집중 // Controller Model 에 data 를 담아서 View 로 전달해줌

- API

#### What is Gradle ?

Gradle 은 의존성 관리 툴로써, 과거에 사용하던 Maven 보다 좀더 편리하게 의존성을 관리 해준다. 과거에 진행했던 Tomcat 을 라이브러리로 알아서 관리해줘서 Main method 를 돌리기만 해도, 서버를 띄워 준다.

!! Log 관리 라이브러리를 알아 보자!! 현재 본인은 System.out.print 를 이용해서 흐름을 파악하는데, Log 를 사용해서 심각한 에러를 좀더 관리 하기 위해서 slf4j 나 Logback 라이브러리를 공부해서 확인 해보자.

Thymeleaf Template Engine에 대해서 좀 더 공부를 해보자.

%%% spring-boot-devtools 라이브러리를 추가 하면, html 파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경 가능, inIntelliJ -> Menu build -> Recompile

#### test Case

Using Junit Framework // 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다, 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행이 어렵고, 여러 테스트를 한번에 실행하기 어렵다는 단점이 있기 때문에.

#### Spring Integration test ( 스프링 통합 테스트 )

> @SpringBootTest -> 스프링 컨테이너와 테스트를 함께 실행한다.

> @Transactional -> DB 에는 Auto-Commit 이라는 개념이 있는데, ( 이건 DB 마다 틀리긴하다.) Commit 을 해줘야 DB 에 적용이 된다. 결국 TEST 클래스에 이 어노테이션이 존재할 경우, 테스트가 끝났을 경우 그 쿼리( 트랜잭션을 ) 를 롤백 한다. 그렇기 때문에
> 쿼리를 실행할때 aftereach를 이용해서 지우지 않아도 된다. ( 다음 테스트에 영향을 주지 않는다. )

> @Transactional 에 해당 하지 않는 메소드를 실행하고 싶을때, @Commit 이라는 메소드를 이용해서 실행한다.

#### Annotation

> @Controller -> 웹서버의 통신시에 가장 먼저 진입하는 진입점

> @GetMapping("A") -> Get 방식으로 들어오는 친구들을 잡아줘서 해당 메소드로 보내준다. 그다음 ViewResolver 를 통해서 Thymeleaf 템플릿 엔진이 처리 될수 있게 resources:templates/`+{ViewName}+`.html 이런식으로 전달됨

> @RequestParam("A") String A get 방식일떄의 param 을 받아서 처리해줌 @RequestParam() 내부에 해당하는 value 값과, 무조건 필요한것인가를 옵션으로 선택할수 있게끔 required 라는 param 도 내부적으로 존재.

> @ResponseBody Http 의 header 부 와 body 부 중에 response body 부에 저 annotation 이 위치한 메소드가 직접 넣어주겠다 라는 의미 HTML 이 아닌, 그대로 data 로 보내준다. Property 방식으로써 객체를 반환하게 되면, JSON으로 값을 전달하게 정해져있다.
>
> > @ResponseBody 좀더 정리 --> HTTP 의 Body 에 문자 내용을 직접 반환, viewResolver 대신에 HttpMessageConverter 가 동작한다. 기본 문자처리 : StringHttpMessageConverter , 기본 객체 처리 : MappingJackson2HttpMessageConverter byte 처리 등등 기타 여러 HttpMessageConverter 가 기본 적으로 등록되어 있다.

@@@@ Spring Bean 과 의존관계

@@@@@ Spring Bean 을 등록하는 2가지 방법

- 컴포넌트 스캔과 자동 의존관계 설정 ( v ) -> 해당하는 패키지 하위의 컴포넌트만 스캔

  - 스프링은 스프링 컨테이너에 스프링 빈을 등록할때, 기본으로 싱글톤으로 등록한다.( 유일하게 하나만 등록해서 공유한다. ) 따라서 같은 스프링 빈이면 모두 같은 인스턴스이다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.

- 자바 코드로 직접 스프링 빈 등록하기

기본적으로 [ Controller <- ( @ Autowired ) -> Service <- ( @ Autowired ) -> Repository ] 의 연결 관계로 이루어져 있다.

> @Controller -> Spring Container 안에 controller annotation 이 붙은 곳의 클래스의 객체를 생성해서 넣어두며 그리고 Spring 이 관리. ( 스프링 빈이 관리된다 ) 내부 종속성을 확인 해보면, @Component 가 있는 것을 확인할 수 있다.

> @Service -> 비즈니스 로직인 서비스 또한 스프링에 의해서 관리 될 수 있게끔 만들어준다. ( 새로운 객체를 생성하는 것이 아닌, 생성자를 이용해서 ) 내부 종속성을 확인 해보면, @Component 가 있는 것을 확인할 수 있다.

> @Repository -> 데이터 객체를 다루는 어노테이션 ( @Component 라는 어노테이션 포함 되어 있다! )

- 자바 코드로 직접 스프링 빈 등록하기

@Controller 는 그대로 사용하고, @Service 나 @Repository 를 등록하지 않고, @Configuration 과 @Bean 을 이용해서 등록한다.

> @Configuration

```java
@Cofiguration
public class SpringConfig{

```

스프링 컨테이너의 설정을 관리해주는 클래스를 생성해준 다음에, @Configuration 어노테이션으로 설정 객체라는 것을 나타내준다.

> @Bean

```java

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}


```

```java
private final MemberService memberService = new MemberService(); ( X )

// 요로코롬
@Autowired
private final MemberService memberService;

// DI ( Dependency Injection 의존성 주입 )
public MemberController(MemberService memberService){
    this.memberService = memberService
}
( O )
```

> @Autowired -> Controller 가 스프링을 생성할때, 같이 생성해준다. 그때 생성자를 호출하는데, 이때 생성자에 Autowired 가 있으면, 스프링 컨테이너 안에 존재하는 service ( 위 같은 경우 member Service )를 가져와 연결을 시켜준다. 이때 service 는 @Service 라는 어노테이션으로 스프링 컨테이너에 넣어져있어야 한다. 또한 Service에서 사용하고 있는 Repository 는 @Repository 라는 어노테이션으로 관리를 해줘야 한다.

> @PostMapping("/something/something2") html 에서의 post 방식에서 ( 데이터를 담을때 사용되는 방법 ) // "redirect:/something" -> something으로 다시 http 통신을 전송한다.

@@@@ Thymeleaf List Extract

@GetMapping 메소드에 Model 을 Param 을 받은 다음, addAttribute("members" , members) 되어 있는 객체를 전달한다.
thymeleaf 는 member 를 렌더링 하여 브라우저가 보여준다.

```html
<div>
  <table>
    <thead>
      <tr>
        <th>#</th>
        <th>이름</th>
      </tr>
    </thead>
    <tbody>
      <tr th:each="member : ${members}">
        <td th:text="${member.id}"></td>
        <td th:text="${member.name}"></td>
      </tr>
    </tbody>
  </table>
</div>
```

#### Spring JdbcTemplate

순수 JDBC 와 동일한 환경설정을 하면 된다. 스프링 JdbcTemplate 과 MyBatis 같은 라이브러리는 JDBC API 에서 본 반복 코드를 대부분 제거해준다. ( ResultSet , Connection 등등 )
하지만, SQL 은 직접 작성해야 한다.

[코드 보기](https://github.com/jasper-oh/SpringWorkPlace/blob/master/src/main/java/com/jasper/learningspringfinal/repository/JdbcTemplateMemberRepository.java)

#### Spring JPA

JPA 는 기존의 반복 코드는 물론, 기본적인 SQL 도 JPA 가 직접 만들어서 실행해준다.
JPA 를 사용하면, SQL 과 데이터 중심의 설계에서 객체중심의 설계로 패러다임을 전환 가능
그로인해 개발 생산성 높을 수 있다.

EntityManager 라는 객체를 불러 온다음 DI 시킨다.

이 EntityManager 를 통해서 CRUD 를 실행 할 수 있는데, PK 값이 아닌 예를 들어, 이름으로 검색하기 같은 내용과 전체 내용을 불러오는 쿼리는 직접 써주어야 하지만, 나머지는 모두 쿼리를 사용하지 않고,

저장 같은 것은 persist(DTO 객체) 메소드
pk 로 검색 find(DTO 객체.class , pk) 같은 식으로 작성 가능하다.

[코드 보기](https://github.com/jasper-oh/SpringWorkPlace/blob/master/src/main/java/com/jasper/learningspringfinal/repository/JpaMemberRepository.java)

#### Spring Data JPA

스프링 부트 + JPA 로도 개발 생산성이 증가하고, 코드는 줄어든다. 여기에 스프링 데이터 JPA 를 사용하면, 기존의 한계를 넘어 Repository에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다. 그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA 가 모두 제공한다.

spring data Jpa 의 기능을 정리하면,

1. 인터페이스를 통한 기본적인 CRUD
2. 'findByName' 이나 'findByEmail' 처럼 메서드 이름 만으로 조회 기능 제공
3. 페이징 기능 자동 제공

+추가
실무에서는 JPA 와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적 쿼리는 QueryDSL 이라는 라이브러리를 사용하면된다.
QueryDSL을 사용하게 되면, 쿼리도 자바코드로 안전하게 작성이 가능하고, 동적 쿼리도 편리하게 작성할 수 있다.
이러한 조합으로 해결하기 어려운 쿼리는 JPA 가 제공하는 네이티브 쿼리를 사용하거나, 앞서 학습한 스프링 JdbcTemplate 을 사용하면 된다.

### AOP ( Aspect Oriented Programming )

1. AOP 가 필요한 상황

- 모든 메소드의 호출 시간을 측정하고 싶다면 ?
- 공통 관심사항(cross cutting concern) vs 핵심 관심 사항 (core concern) 분리하기
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면 ?

- 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
- 핵심 관심 사항을 깔끔하게 유지 가능!
- 변경이 필요하면 이 로직만 변경하면 된다.
- 원하는 적용 대상을 선택할 수 있다.

AOP 적용전

helloController -> memberService -> memberRepository

AOP 모든곳에 적용후
[ 프록시 helloController ] -> helloController -> [ 프록시 memberService ] with joinPoint.proceed() -> memberService -> [ 프록시 memberRepository ] -> memberRepository

( 프록시 주입 실험 memberService 가 인젝션 될때 .getClass 를 찍어보게 된다면 -> EnhancerBySpringCGLIB$$~ 이라고 뜬다. )

결국 DI 장점이라고 볼 수 있다.

#### ETC

@@@@ 배포하기 위해서는 ???

./gradlew build >> 빌드 폴더로 진입

java -jar "만들어진 jar 파일" // 서버 잘 열리는지 확인후에

저 jar 파일로 복사해서 서버에 넣어서 배포 하면 되지롱~~

@@@@ 만약 잘 안됬어?

./gradlew clean build >> build 폴더 사라짐

그리고 다시 돌아가도록 !
