# Daily Codewars #1
## Question
[codewars link](http://www.codewars.com/kata/55a7de09273f6652b200002e)

Lucas numbers are numbers in a sequence defined like this:

```
L(n) = 2 if n = 0
L(n) = 1 if n = 1
otherwise 
L(n) = L(n - 1) + L(n - 2)
```
Your mission is to define a function ```lucasnum(n)``` that returns the nth term of this sequence.
Note: It should work for negative numbers as well (how you do this is you flip the equation around, so for negative numbers: ```L(n) = L(n + 2) - L(n + 1)).```


## My Solution
```java
public class Lucas {
  public static int lucasnum (int n){
    if(n == 0) return 2; //if it is not here, then line 12 will emit boundary exception.

    //make an array of int. [0 .. absolute(n)].
    int[] lucas = new int[(n > 0) ? (n + 1) : (-n + 1)]; 
    
    // base step. when n < 0, L(n) = lucas[-n];
    lucas[0] = 2; 
    lucas[1] = (n > 0) ? 1 : -1;
    
    // iteratively set lucas[i]
    for(int i = 2; i < lucas.length; i++) {
      lucas[i] = lucas[i-2] + lucas[1]*lucas[i-1]; //multiplying lucas[1] is adjusting the sign of lucas[i-1]
    }
    return lucas[lucas.length-1];
  }
}
```

## @NaMe613's Solution
```java
public class Lucas {
  public static int lucasnum (int n){
    int a = 2;
    int b = 1;
    int abs = n;
    if (n < 0){abs *= -1;}
    for (int i = 0; i < abs; i++){
      int q = a;
      a = b;
      b = q + b;
    }
    if (n < 0 && abs % 2 == 1){
      return a * -1;
    }
    return a;
  }
}
```
> 이전에 dynamic programming을 연습할 때 결과를 저장하려고 array를 사용했었다. 그 틀에 갖혀 이번에도 array를 사용하도록 짠 것 같다.
> 하지만 이 경우엔 subproblem으로 T(i-1), T(i-2)만 필요하므로, 배열 전체가 아닌 변수 2개만 임시적으로 이용하면서 짤 수가 있다.
> 또한 음수의 경우 양수랑 절대값은 비슷하게 증가하나, 번갈아 음수가 되는 규칙을 발견했다면 for문 안에서는 음수 고려하지 않고 덧셈을 하고, 마지막에만 부호를 설정해주면 되었다. 문제를 좀 더 분석할 필요가 있겠다! 