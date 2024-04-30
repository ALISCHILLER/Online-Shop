-- ----------------------------
-- Table structure for CustomerShipToParty
-- ----------------------------
DROP TABLE IF EXISTS "main"."CustomerShipToParty";
CREATE TABLE "CustomerShipToParty" (
"UniqueId"  TEXT NOT NULL COLLATE NOCASE ,
"SoldToPartyUniqueId" TEXT,
"BackOfficeId"  TEXT,
"CustomerName"  TEXT,
"Address"  TEXT,
"Phone"  TEXT,
"StoreName"  TEXT,
"Mobile"  TEXT,
"Longitude"  REAL,
"Latitude"  REAL,
"PostCode"  TEXT,
"EconomicCode"  TEXT(2048),
"NationalCode"  TEXT(2048),
"IsActive"  INTEGER,
"IgnoreLocation" INTEGER,
PRIMARY KEY ("UniqueId" ASC)
);



-- ----------------------------
--  Alter CustomerCallInvoice related to issue: CustomerCallInvoice
-- ----------------------------
alter table CustomerCallInvoice add column ShipToPartyUniqueId TEXT;
alter table CustomerCallOrder add column ShipToPartyUniqueId TEXT;
alter table CustomerCallInvoice add column ShipToPartyCode TEXT;
alter table CustomerCallOrder add column ShipToPartyCode TEXT;


-- ----------------------------
--  Alter view CustomerCallReturn and CustomerCallReturnRequest related to issue: NGT-3419
-- ----------------------------
alter table CustomerCallReturn add column ShipToPartyUniqueId TEXT;

alter table CustomerCallReturn add column ShipToPartyCode TEXT;


-- ----------------------------
--  Alter view CustomerCallReturn and CustomerCallReturnRequest related to issue: NGT-3419
-- ----------------------------
alter table CustomerCallReturnRequest add column ShipToPartyUniqueId TEXT;
alter table CustomerCallReturnRequest add column ShipToPartyCode TEXT;