
WITH Numbers (Number) AS
(
	SELECT
		*
	FROM (
		VALUES
		(NULL),
		(1),
		(2),
		(3),
		(4),
		(5),
		(6),
		(7),
		(8),
		(9),
		(10),
		(20),
		(28),
		(30),
		(40),
		(50),
		(100),
		(496),
		(500),
		(1000),
		(8128),
		(10000)
	) Numbers (Number)
),
PerfectNumberCandidates AS
(
	SELECT 
		Number
		, 1 AS IndexNumber
		, 0 AS DivisorSum
		, 0 AS Divisor /*I added*/
		, CAST('' AS VARCHAR(1000)) AS Summation
	FROM Numbers

	UNION ALL

	SELECT 
		-- Student Code
		IndexNumber+1,
		Divisor+1,
		DivisorSum,
		Number,
		CASE
			WHEN Number/Divisor=0 AND Divisor<Number 
				THEN Summation+'+'+CONVERT(VARCHAR,(Divisor))
				WHEN Number/Divisor=0 AND Divisor<Number 
				THEN DivisorSum+Divisor
				WHEN DivisorSum+Divisor=Number
					THEN Summation+'='+Number
		END AS CandNumber
	FROM PerfectNumberCandidates
	-- Student Code
),
PerfectNumbers AS
(
	SELECT
		Number AS CandNumber, 
		Summation
	FROM PerfectNumberCandidates
	-- Student Code
)
SELECT
	*
FROM PerfectNumbers
ORDER BY CandNumber --Orijinal versiyonda Number yazýyor
OPTION (MAXRECURSION 10000)


