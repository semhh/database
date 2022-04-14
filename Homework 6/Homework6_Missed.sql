WITH CitedPapers AS
(
	SELECT
		ROW_NUMBER() OVER (PARTITION BY AcademicianIdentityNumber ORDER BY CitationCount DESC) AS RowNumber,
		*
	FROM Papers
	WHERE CitationCount IS NOT NULL 
),

HirschValue AS
(
	SELECT
		CitedPapers.AcademicianIdentityNumber AS AcadID,
		COUNT(*) AS HID
	FROM CitedPapers
	WHERE RowNumber <= CitationCount 
	GROUP BY AcademicianIdentityNumber
),

HirschIndex AS
(
	SELECT
		AcadID,
		COUNT(PublicationYear) AS PublicYear,
		HID,
		CitationCount,
		PublicationYear,
		Title,
		ImpactFactor,
		CASE
		WHEN CitationCount>= 10 THEN 1
		ELSE 0
		END AS I10
	FROM HirschValue JOIN Papers
	ON HirschValue.AcadID=Papers.AcademicianIdentityNumber
	GROUP BY PublicationYear,AcadID,HID,CitationCount,Title,ImpactFactor
	
),

AcademicianSummary AS
(
	SELECT
		FirstName +' '+ LastSurname AS AcademicianName,
		HID AS HIndex,
		SUM(I10) AS I10Index,
		SUM(CitationCount) AS TotalCitation,
		MAX(CitationCount) AS MaxCitation,
		SUM(ISNULL(CitationCount,1))-SUM(CitationCount) AS PaperWithNoCitation,
		COUNT(Title) AS PublicationCount,
		CAST(CONVERT(float,SUM(CitationCount))/COUNT(Title) AS decimal(15,2)) AS CitationPerPaper,
		MIN(PublicationYear) AS FirstPublicationYear,
		MAX(PublicationYear) AS LastPublicationYear,
		COUNT(DISTINCT PublicationYear) AS PublishedYearCount,
		COUNT(ImpactFactor) AS SCIPaperCount,
		MIN(ImpactFactor) AS MinImpactFactor,
		MAX(ImpactFactor) AS MaxImpactFactor
	FROM Academician AS A JOIN HirschIndex AS P 
	ON A.IdentityNumber = P.AcadID
	GROUP BY HID,LastSurname,FirstName
)
	SELECT
		*
	FROM AcademicianSummary
	ORDER BY HIndex DESC

-- See  https://en.wikipedia.org/wiki/H-index
-- See  https://guides.erau.edu/bibliometrics/author-level/i10-index
-- See  https://scholar.google.com.tr/citations?user=LQg7qAEAAAAJ&hl=en
