import Data.List
import System.Random
import Control.Monad

main = do
	gen <- getStdGen
	play $ makeAnswer gen



makeAnswer :: StdGen -> Int

