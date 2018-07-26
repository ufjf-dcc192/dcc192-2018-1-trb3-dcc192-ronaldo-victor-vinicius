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

 Date: 26/07/2018 15:47:08
*/


-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS "public"."item";
CREATE TABLE "public"."item" (
  "titulo" varchar(100) COLLATE "pg_catalog"."pt_BR" NOT NULL,
  "descricao" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "data_hora_criacao" timestamp(0) NOT NULL,
  "data_hora_ultima_atualizacao" timestamp(0) NOT NULL,
  "id_usuario_proprietario" int4 NOT NULL,
  "id_item" int4 NOT NULL DEFAULT nextval('item_id_item_seq'::regclass)
)
;

-- ----------------------------
-- Primary Key structure for table item
-- ----------------------------
ALTER TABLE "public"."item" ADD CONSTRAINT "item_pkey" PRIMARY KEY ("id_item");

-- ----------------------------
-- Foreign Keys structure for table item
-- ----------------------------
ALTER TABLE "public"."item" ADD CONSTRAINT "id_usuario_proprietario" FOREIGN KEY ("id_usuario_proprietario") REFERENCES "public"."usuario" ("id_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
