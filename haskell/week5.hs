import Data.Monoid

newtype MyAny = MyAny {getMyAny :: Bool} deriving (Eq, Ord, Show)
newtype MyAll = MyAll {getMyAll :: Bool} deriving (Eq, Ord, Show)

instance Monoid MyAny where 
	mempty = MyAny False
	MyAny x `mappend` MyAny y = MyAny (x || y)

instance Monoid MyAll where
	mempty = MyAll True
	MyAll x `mappend` MyAll y = MyAll (x && y)