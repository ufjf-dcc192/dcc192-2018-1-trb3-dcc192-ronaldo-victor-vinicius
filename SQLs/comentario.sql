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

 Date: 26/07/2018 15:46:39
*/


-- ----------------------------
-- Table structure for comentario
-- ----------------------------
DROP TABLE IF EXISTS "public"."comentario";
CREATE TABLE "public"."comentario" (
  "id_comentario" int4 NOT NULL DEFAULT nextval('comentario_id_comentario_seq'::regclass),
  "texto" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "data_hora_criacao" timestamp(0) NOT NULL,
  "data_hora_ultima_atualizacao" timestamp(0) NOT NULL,
  "id_usuario_proprietario" int4 NOT NULL,
  "id_item_comentado" int4 NOT NULL,
  "titulo" varchar(100) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table comentario
-- ----------------------------
ALTER TABLE "public"."comentario" ADD CONSTRAINT "comentario_pkey" PRIMARY KEY ("id_comentario");

-- ----------------------------
-- Foreign Keys structure for table comentario
-- ----------------------------
ALTER TABLE "public"."comentario" ADD CONSTRAINT "id_item_comentado" FOREIGN KEY ("id_item_comentado") REFERENCES "public"."item" ("id_item") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."comentario" ADD CONSTRAINT "id_usuario_proprietario" FOREIGN KEY ("id_usuario_proprietario") REFERENCES "public"."usuario" ("id_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
