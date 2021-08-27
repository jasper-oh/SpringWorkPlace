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

Using Junit Framework // 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다, 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행이 어렵고, 여러 테스트를 한번에 실행하기 어렵다는 단점이 있기 떄문에.

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

> @Repository -> 데이터 객체를 다루는 어노테이션

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

#### ETC

@@@@ 배포하기 위해서는 ???

./gradlew build >> 빌드 폴더로 진입

java -jar "만들어진 jar 파일" // 서버 잘 열리는지 확인후에

저 jar 파일로 복사해서 서버에 넣어서 배포 하면 되지롱~~

@@@@ 만약 잘 안됬어?

./gradlew clean build >> build 폴더 사라짐

그리고 다시 돌아가도록 !
