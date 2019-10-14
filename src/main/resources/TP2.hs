-- on generalise (autant que possible) le type des fonctions du bloc1

--cons a b = \a -> \b -> \f -> ((f a) b)

myHead :: [a] -> a
--myHead xs = (\c -> (c (\ a b -> a))) xs
--myHead _ = undefined
myHead (x:_) = x

myTail :: [a] -> [a]
myTail (_:xs) = xs

myAppend :: [a] -> [a] -> [a]
myAppend xs ys = myAppend' xs 
    where --myAppend' :: [a] -> [a]
          myAppend' (x:xs) = x:myAppend' xs
          myAppend' [] = ys

myInit :: [a] -> [a]
myInit xs = 
    if length xs <= 1 
        then []
    else (head xs):(myInit (tail xs))

myLast :: [a] -> a
myLast [x] = x
myLast (_:xs) = myLast xs

myNull :: [a] -> Bool
myNull [] = True
myNull _ = False

myLength :: [a] -> Int
myLength list =
    if myNull list
        then 0
    else 1 + myLength (myTail list)

myReverse :: [a] -> [a]
myReverse list = 
    if myNull list
        then list
    else myAppend (myReverse (myTail list)) [myHead list]

myConcat :: [[a]] -> [a]
myConcat (xs:xss) = xs ++ myConcat xss
myConcat [] = []

myTake :: Int -> [b] -> [b]
myTake index list =
    if index <= 0
        then []
    else if myNull list
        then []
    else myHead list : myTake (index-1) (myTail list)

myDrop :: Int -> [element] -> [element]
myDrop index list =
    if index <= 0
        then list
    else if myNull list
        then []
    else myDrop (index-1) (myTail list)

myBangBang :: [a] -> Int -> a
myBangBang list index = 
    if index <= 0
        then myHead list 
    else myBangBang (myTail list) (index-1)

myInsert :: Ord a => a -> [a] -> [a]
myInsert element list =
    if myNull list
        then [element]
    else if element > myHead list
        then myHead list : myInsert element (myTail list)
    else element : myHead list : myTail list

mySort :: Ord a => [a] -> [a]
mySort list =
    if myNull list
        then []
    else myInsert (myHead list) (mySort (myTail list))

-- NEW STUFF
-- ordre superieur

myTakeWhile :: (a -> Bool) -> [a] -> [a]
myTakeWhile predicat (x:xs) | predicat x = x:myTakeWhile predicat xs
                            | otherwise  = []
myTakeWhile predicat [] = []

-- donner le type de la fonction, notation infixe versus prefixe
myCompose :: (b -> c) -> (a -> b) -> a -> c
myCompose f g x = f (g x)

myMap :: (a -> b) -> [a] -> [b]
myMap f list =
    if myNull list
        then []
    else f (myHead list) : myMap f (myTail list)

test1 = myMap odd [1..10]

-- calcul des sous liste en utilisant map

sousListes :: [a] -> [[a]]
sousListes list =
    if myNull list
        then [[]]
    else s ++ map ((myHead list):) s where s = sousListes (myTail list)

-- une fonction plus generale: foldr
-- inferer le type de foldr
-- forme graphique de la liste en peigne

myFoldr :: (a -> b -> b) -> b -> [a] -> b
myFoldr f k list =
    if myNull list
        then k
    else f (myHead list) (myFoldr f k (myTail list))

myAnd' :: [Bool] -> Bool
myAnd' = myFoldr (&&) True 

-- definir reverse avec foldr
myReverse' :: [a] -> [a]
myReverse' = undefined

-- une parenthese sur les lambda anonymes

add' :: Int -> Int -> Int
add' x y = x+y

add'' :: Int -> Int -> Int
add'' = \x -> \y -> x+y

-- eta reduction
myReverse''' :: [a] -> [a]
myReverse''' = myFoldr (\ x fdx -> fdx ++ [x]) []

-- un "nouveau type" String

myString :: String
myString = "rrr"

-- un nouveau type tuples

pair1 :: (Int,Int)
pair1 = (1,2)

pair2 :: (Int,Bool)
pair2 = (1,True)

tuple3 :: (Int,Int,String,Bool)
tuple3 = (1,1,"re",False)

myFst :: (a,b) -> a
myFst (x,_) = x

-- TODO: definir recursivement

myDropWhile :: (a -> Bool) -> [a] -> [a]
myDropWhile f list =
    if myNull list
        then []
    else if f (myHead list)
        then myDropWhile f (myTail list)
    else (myHead list : myTail list)

myElem :: Eq a => a -> [a] -> Bool
myElem x (y:ys) | x == y    = True
                | otherwise = myElem x ys
myElem x []                 = False

myNotElem :: Eq a => a -> [a] -> Bool
myNotElem x xs = not (myElem x xs)

myFilter :: (a -> Bool) -> [a] -> [a]
myFilter f (x:xs) | f x       = x : myFilter f xs
                  | otherwise = myFilter f xs
myFilter f []                 = []

mySplitAt :: Int -> [a] -> ([a],[a])
mySplitAt x xs = (myTake x xs, myDrop x xs)

myZip :: [a] -> [b] -> [(a,b)] 
myZip (x:xs) (y:ys) = [(x,y)] ++ myZip xs ys
myZip _ _           = []

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c] 
myZipWith f (x:xs) (y:ys) = f x y : myZipWith f xs ys
myZipWith f _ _           = []

myCurry :: ((a,b) -> c) -> a -> b -> c
myCurry f a b = f (a,b)

myUnCurry :: (a -> b -> c) -> (a,b) -> c
myUnCurry f (a,b) = f a b

myZipWith' :: (a -> b -> c) -> [a] -> [b] -> [c] 
myZipWith' f (x:xs) (y:ys) = myUnCurry f (x,y) : myZipWith' f xs ys
myZipWith' f _ _           = []

myUnZip :: [(a,b)] -> ([a],[b])
myUnZip (x:xs) = (fst x : first,snd x : second)
                 where (first,second) = myUnZip xs
myUnZip []     = ([],[])

-- TODO: redefinir en utilisant foldr

myConcat' :: [[a]] -> [a]
myConcat' = myFoldr (++) []

myMap' ::  (a -> b) -> [a] -> [b]
myMap' func xs = myFoldr gFunc [] xs
                 where gFunc a b = func a : b

myOr' ::  [Bool] -> Bool
myOr' = myFoldr (||) False

myAny :: (a -> Bool) -> [a] -> Bool
myAny func xs = myFoldr gFunc False xs
                where gFunc a bool = func a || bool

myAll :: (a -> Bool) -> [a] -> Bool
myAll func xs = myFoldr gFunc True xs
                where gFunc a bool = func a && bool

myProduct :: [Int] -> Int
myProduct = myFoldr (*) 1

-- TODO: calculuer les 50 plus petits nombres premiers 2, 3, 5, 7, 11...

premiers :: [Int]
premiers = step [2..]

step :: [Int] -> [Int]
step (x:xs) = x : step (filter (\n -> n `mod` x /= 0) xs)

test2 = take 50 premiers
