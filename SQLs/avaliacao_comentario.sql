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

 Date: 26/07/2018 15:44:25
*/


-- ----------------------------
-- Table structure for avaliacao_comentario
-- ----------------------------
DROP TABLE IF EXISTS "public"."avaliacao_comentario";
CREATE TABLE "public"."avaliacao_comentario" (
  "id_avaliacao_comentario" int4 NOT NULL DEFAULT nextval('avaliacao_comentario_id_avaliacao_comentario_seq'::regclass),
  "avaliacao" int2 NOT NULL,
  "id_comentario_avaliado" int4 NOT NULL,
  "id_usuario_proprietario" int4 NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table avaliacao_comentario
-- ----------------------------
ALTER TABLE "public"."avaliacao_comentario" ADD CONSTRAINT "avaliacao_comentario_pkey" PRIMARY KEY ("id_avaliacao_comentario");

-- ----------------------------
-- Foreign Keys structure for table avaliacao_comentario
-- ----------------------------
ALTER TABLE "public"."avaliacao_comentario" ADD CONSTRAINT "id_comentario_avaliado" FOREIGN KEY ("id_comentario_avaliado") REFERENCES "public"."comentario" ("id_comentario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."avaliacao_comentario" ADD CONSTRAINT "id_usuario_proprietario" FOREIGN KEY ("id_usuario_proprietario") REFERENCES "public"."usuario" ("id_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
