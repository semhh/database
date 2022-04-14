SELECT
	Color, 
	COUNT(*) AS ColorCount,
	SUM(SafetyStockLevel) AS TotalSafetyStockLevel, 
	MAX(H.StandardCost)-MIN(H.StandardCost) AS StandardCostChange
FROM Production.Product AS P INNER JOIN Production.ProductCostHistory AS H
ON P.ProductID = H.ProductID
WHERE ReorderPoint < 500
GROUP BY Color
HAVING COUNT(*) > 10
