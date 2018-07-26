/*
 Navicat PostgreSQL Data Transfer

 Source Server         : trabalho3
 Source Server Type    : PostgreSQL
 Source Server Version : 100004
 Source Host           : ec2-54-235-193-34.compute-1.amazonaws.com:5432
 Source Catalog        : da9p15t0v65m7c
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100004
 File Encoding         : 65001

 Date: 26/07/2018 15:47:29
*/


-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS "public"."link";
CREATE TABLE "public"."link" (
  "id_link" int4 NOT NULL DEFAULT nextval('link_id_link_seq'::regclass),
  "link" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "id_item_relacionado" int4 NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table link
-- ----------------------------
ALTER TABLE "public"."link" ADD CONSTRAINT "link_pkey" PRIMARY KEY ("id_link");

-- ----------------------------
-- Foreign Keys structure for table link
-- ----------------------------
ALTER TABLE "public"."link" ADD CONSTRAINT "id_item_relacionado" FOREIGN KEY ("id_item_relacionado") REFERENCES "public"."item" ("id_item") ON DELETE NO ACTION ON UPDATE NO ACTION;
