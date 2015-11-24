yesNo :: Bool -> String
yesNo b = if(b) then "Yes" else "No"

trueman:: (Num a) => [Bool] -> a
trueman [] = 0
trueman (x:xs) = if x then (1 + trueman xs) else trueman xs

listToPairs:: [a] -> [(a, a)] -- [a] -> [(b, c)]로 하면 안된다.
listToPairs [] = [] 
listToPairs [_] = [] -- [x] = [] 보다는  _ 쓰는 게 좋다. 
listToPairs (x:y:xs) = (x,y):listToPairs xs -- ++ 보다는 : 쓰는 게 좋다. 


-- 에러가 있는 코드긴 하지만, guard와 패턴 매칭을 함께할 수 있음
test n 
	| n > 200 = "big"
	| n > 100 = "normal"
test 0 = "zero"

maximum' :: (Ord a) => [a] -> a
maximum' [] = error "Illegal argument: Empty list"
maximum' [x] = x
maximum' (x:xs)
	| x > maximum' xs = x
	| otherwise = maximum' xs
-- where tailMax = maximum' xs 이걸 쓰면 계산을 한번만 한다!


take' :: (Integral a) => a -> [b] -> [b]
take' n [] = []
take' n (x:xs)
	| n <= 0 = [] 
	| otherwise = x:take' (n-1) xs

zip' :: [a] -> [b] -> [(a,b)]
zip' [] _ = []
zip' _ [] = []
zip' (x:xs) (y:ys) = (x,y):(zip' xs ys)

-- 하스켈의 모든 함수는 커링되어 있다.

inverse :: (Fractional a) => [a] -> [a]
inverse xs = map (\x -> 1 / x) xs -- (1/) 부분적용도 가능!

longList xxs = filter (\xs -> length xs >= 3) xxs

dot :: (Num a) => [(a,a)] -> [(a,a)] -> [a]
--dot [] _ = [] 안해도 됨
--dot _ [] = [] 안해도 됨
dot xs ys = zipWith (\(a,b) (c,d) -> a*c + b*d) xs ys

finalPosition :: (Num a) => (a, a) -> [(a, a)] -> (a, a)
finalPosition t xs = foldl (\acc x -> tupleSum acc x) t xs 
	where tupleSum (t1, t2) (t3, t4) = (t1 + t3, t2 + t4)

nineDigit :: (Num b) => Integer -> b
nineDigit x = foldl (\acc x -> if x == '9' then acc + 1 else acc) 0 str where str = show x

