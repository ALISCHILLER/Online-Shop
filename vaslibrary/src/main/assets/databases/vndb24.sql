/*
Date: 2018-05-22 12:27:54
*/
-- ----------------------------
-- Add StartTime and EndTime to order and return
-- ----------------------------
alter table CustomerCallOrder add column StartTime Integer;
alter table CustomerCallOrder add column EndTime Integer;
alter table CustomerCallReturn add column StartTime Integer;
alter table CustomerCallReturn add column EndTime Integer;

-- ----------------------------
--  CustomerCallOrderPreview
-- ----------------------------
DROP VIEW IF EXISTS "main"."CustomerCallOrderPreview";
CREATE VIEW CustomerCallOrderPreview AS
SELECT
	OrderUniqueId AS UniqueId,
	CustomerUniqueId,
	SUM(TotalPrice) as TotalPrice,
	SUM(TotalQty) as TotalQty,
	LocalPaperNo,
	Comment
FROM
	(
	SELECT
		CustomerCallOrderLines.OrderUniqueId AS OrderUniqueId,
		CustomerCallOrder.CustomerUniqueId AS CustomerUniqueId,
		CustomerCallOrderLines.RequestBulkQty AS RequestBulkQty,
		CustomerCallOrderLines.RequestBulkQtyUnitUniqueId AS RequestBulkQtyUnitUniqueId,
		CustomerPrice.Price AS UnitPrice,
		CASE
		WHEN CustomerCallOrderLines.RequestBulkQtyUnitUniqueId IS NULL THEN
			sum(Qty * ConvertFactor)
		ELSE
			CustomerCallOrderLines.RequestBulkQty
		END AS TotalQty,
		CASE
		WHEN RequestBulkQtyUnitUniqueId IS NULL THEN
		(
			Qty * ConvertFactor * CustomerPrice.Price
		)
		ELSE
		(
			RequestBulkQty * CustomerPrice.Price
		)
		END AS TotalPrice,
		CustomerCallOrder.LocalPaperNo AS LocalPaperNo,
		CustomerCallOrder.Comment AS Comment
	FROM
		CustomerCallOrderLines,
		ProductUnit ON CustomerCallOrderLinesOrderQtyDetail.ProductUnitId = ProductUnit.UniqueId,
		CustomerCallOrderLinesOrderQtyDetail ON CustomerCallOrderLinesOrderQtyDetail.OrderLineUniqueId = CustomerCallOrderLines.UniqueId,
		Product ON Product.UniqueId = CustomerCallOrderLines.ProductUniqueId,
		CustomerCallOrder ON CustomerCallOrder.UniqueId = CustomerCallOrderLines.OrderUniqueId
	LEFT JOIN CustomerPrice ON (CustomerPrice.CustomerUniqueId = CustomerCallOrder.CustomerUniqueId)
	AND (CustomerPrice.ProductUniqueId = CustomerCallOrderLines.ProductUniqueId)
	GROUP BY OrderLineUniqueId
	)
GROUP BY
	OrderUniqueId;