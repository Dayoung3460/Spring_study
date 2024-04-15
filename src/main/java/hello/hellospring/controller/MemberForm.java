package hello.hellospring.controller;

public class MemberForm {
    // input의 name이 setName 통해서(스프링이 setName 호출) 값으로 들어감
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
