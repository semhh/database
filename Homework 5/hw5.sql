WITH Emp AS
(
	SELECT
		PersonType
		, Title
		, FirstName
		, MiddleName
		, LastName
		, JobTitle
		, BirthDate
		, MaritalStatus
		, Gender
		, HireDate
	FROM HumanResources.Employee AS E INNER JOIN Person.Person AS P
	ON E.BusinessEntityID = P.BusinessEntityID
)
SELECT 
	PersonType,
	Title,
	FirstName+' '+MiddleName+' '+LastName AS FullName,
	JobTitle,
	(Case when MaritalStatus = 'M' then 'Married' when MaritalStatus = 'S' then 'Single' else 'Other' end)+' '+
	(Case when Gender = 'M' then 'Male' when Gender = 'F' then 'Female' else 'Other' end) AS SocialStatus,
	YEAR(GETDATE())-YEAR(BirthDate) AS Age,
	RIGHT(CONVERT(VARCHAR(10), HireDate, 103), 7) AS HireMonthYear
FROM Emp
WHERE Title != 'NULL'

