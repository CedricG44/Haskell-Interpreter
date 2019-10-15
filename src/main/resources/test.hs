consL a b = \a -> \b -> \f -> ((f a) b)

headL :: [a] -> a
headL xs = (\c -> (c (\a -> \b -> a))) xs
