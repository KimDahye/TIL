data BST a = EmptyBST 
			| Node a (BST a) (BST a) 
			deriving (Show, Read)

singleton :: (Ord a) => a -> BST a
singleton x = Node x (EmptyBST) (EmptyBST)

insert :: (Ord a) => BST a -> a -> BST a
insert EmptyBST e = singleton e
insert (Node x l r) e  
	| x > e = Node x (insert l e) r 
	| x < e = Node x l (insert r e)
	| otherwise = Node x l r

contains :: (Ord a) => BST a -> a -> Bool
contains EmptyBST _ = False
contains (Node x l r) e 
	| x == e = True 
	| otherwise = (contains l e ) || (contains r e)

instance (Eq a) => Eq (BST a) where
	EmptyBST == EmptyBST = True
	Node x l r == Node x' l' r' = (x == x') && (l == l') && (r == r') 
	_ == _ = Falseb 

