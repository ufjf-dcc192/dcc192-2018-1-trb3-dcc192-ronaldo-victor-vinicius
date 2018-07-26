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

 Date: 26/07/2018 15:47:50
*/


-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS "public"."usuario";
CREATE TABLE "public"."usuario" (
  "nome_completo" varchar(100) COLLATE "pg_catalog"."pt_BR" NOT NULL,
  "email" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "login" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "senha" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "id_usuario" int4 NOT NULL DEFAULT nextval('usuario_id_usuario_seq'::regclass)
)
;

-- ----------------------------
-- Primary Key structure for table usuario
-- ----------------------------
ALTER TABLE "public"."usuario" ADD CONSTRAINT "usuario_pkey" PRIMARY KEY ("id_usuario");
