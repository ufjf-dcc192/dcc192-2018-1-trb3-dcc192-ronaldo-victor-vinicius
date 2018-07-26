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

 Date: 26/07/2018 15:46:13
*/


-- ----------------------------
-- Table structure for avaliacao_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."avaliacao_item";
CREATE TABLE "public"."avaliacao_item" (
  "id_avaliacao_item" int4 NOT NULL DEFAULT nextval('avaliacao_item_id_avaliacao_item_seq'::regclass),
  "avaliacao" int2 NOT NULL,
  "id_item_avaliado" int2 NOT NULL,
  "id_usuario_proprietario" int2 NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table avaliacao_item
-- ----------------------------
ALTER TABLE "public"."avaliacao_item" ADD CONSTRAINT "avaliacao_item_pkey" PRIMARY KEY ("id_avaliacao_item");

-- ----------------------------
-- Foreign Keys structure for table avaliacao_item
-- ----------------------------
ALTER TABLE "public"."avaliacao_item" ADD CONSTRAINT "id_item_avaliado" FOREIGN KEY ("id_item_avaliado") REFERENCES "public"."item" ("id_item") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."avaliacao_item" ADD CONSTRAINT "id_usuario_proprietario" FOREIGN KEY ("id_usuario_proprietario") REFERENCES "public"."usuario" ("id_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
