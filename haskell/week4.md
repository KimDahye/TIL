data 키워드 이용
타입이름과 값생성자는 대문자로 시작하기!

data Bool = False | True
(Bool이 타입의 이름)
(= 이후가 값 생성자)

data Shape = Circle Float Float Float 
값 생성자는 다른 게 아니라 함수다!

값 생성자로 패턴매칭을 하는 것이다!

Record Syntax 

- getter를 자동적으로 만드는 거라고 생각하면 됨.
- 필드를 선택적으로 바꿀 때도 이걸 이용한다. 

let c = Circle (5,5) 3
let d = c {radius = 5}


<타입 매개변수>
리스트는 그 자체로 타입은 아니다.
타입을 하나 받아서 그 타입을 이용한 새로운 타입을 만든다.
data Maybe a = Nothing | Just a

<deriving>
자동으로 상식적인 기준에서 타입 상속하는 게 deriving 키워드 이용하는 것.
ex) deriving Ord, deriving Show



타입 클래스


ghci 에서 모듈 불러오기  :m [모듈이름]

http://jwvg0425.tistory.com/18



