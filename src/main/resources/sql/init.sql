CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    content text,
    metadata json,
    embedding vector(1536)
    );

CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);


-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_user";
CREATE TABLE "public"."tb_user" (
                                    "id" "pg_catalog"."int4" NOT NULL,
                                    "name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
                                    "user_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
                                    "password" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
                                    "phone" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
                                    "sex" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
                                    "id_number" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
                                    "status" "pg_catalog"."int4" NOT NULL DEFAULT 1,
                                    "create_time" "pg_catalog"."date",
                                    "update_time" "pg_catalog"."date",
                                    "create_user" "pg_catalog"."int8",
                                    "update_user" "pg_catalog"."int8"
)
;
COMMENT ON COLUMN "public"."tb_user"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_user"."name" IS '姓名';
COMMENT ON COLUMN "public"."tb_user"."user_name" IS '用户名';
COMMENT ON COLUMN "public"."tb_user"."password" IS '密码';
COMMENT ON COLUMN "public"."tb_user"."phone" IS '手机号';
COMMENT ON COLUMN "public"."tb_user"."sex" IS '性别';
COMMENT ON COLUMN "public"."tb_user"."id_number" IS '身份证号';
COMMENT ON COLUMN "public"."tb_user"."status" IS '状态 0：禁用 1：启用';
COMMENT ON COLUMN "public"."tb_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."tb_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."tb_user"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."tb_user"."update_user" IS '修改人';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO "public"."tb_user" VALUES (666497, '管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '13800138000', '男', '11010519491231002X', 1, '2025-03-03', '2025-03-03', NULL, NULL);

-- ----------------------------
-- Primary Key structure for table tb_user
-- ----------------------------
ALTER TABLE "public"."tb_user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("id");



-- ----------------------------
-- Table structure for ali_oss_file
-- ----------------------------
DROP TABLE IF EXISTS "public"."ali_oss_file";
CREATE TABLE "public"."ali_oss_file" (
                                         "id" "pg_catalog"."int8" NOT NULL,
                                         "file_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                         "url" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                         "vector_id" "pg_catalog"."text" COLLATE "pg_catalog"."default",
                                         "create_time" "pg_catalog"."timestamp",
                                         "update_time" "pg_catalog"."timestamp"
)
;
COMMENT ON COLUMN "public"."ali_oss_file"."id" IS '主键id';
COMMENT ON COLUMN "public"."ali_oss_file"."file_name" IS '文件名';
COMMENT ON COLUMN "public"."ali_oss_file"."url" IS '链接地址';
COMMENT ON COLUMN "public"."ali_oss_file"."vector_id" IS '该文件分割出的多段向量文本ID';
COMMENT ON COLUMN "public"."ali_oss_file"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."ali_oss_file"."update_time" IS '更新时间';

-- ----------------------------
-- Primary Key structure for table ali_oss_file
-- ----------------------------
ALTER TABLE "public"."ali_oss_file" ADD CONSTRAINT "ali_oss_file_pkey" PRIMARY KEY ("id");


-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."log_info";
CREATE TABLE "public"."log_info" (
                                     "id" "pg_catalog"."int8" NOT NULL,
                                     "method_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                     "class_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                     "request_time" "pg_catalog"."date",
                                     "request_params" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                     "response" "pg_catalog"."varchar" COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."log_info"."id" IS 'id';
COMMENT ON COLUMN "public"."log_info"."method_name" IS '方法名';
COMMENT ON COLUMN "public"."log_info"."class_name" IS '类目';
COMMENT ON COLUMN "public"."log_info"."request_time" IS '请求时间戳';
COMMENT ON COLUMN "public"."log_info"."request_params" IS '请求参数';
COMMENT ON COLUMN "public"."log_info"."response" IS '响应结果';

-- ----------------------------
-- Primary Key structure for table log_info
-- ----------------------------
ALTER TABLE "public"."log_info" ADD CONSTRAINT "log_info_pkey" PRIMARY KEY ("id");



-- ----------------------------
-- Table structure for sensitive_word
-- ----------------------------
DROP TABLE IF EXISTS "public"."sensitive_word";
CREATE TABLE "public"."sensitive_word" (
                                           "id" "pg_catalog"."int4" NOT NULL,
                                           "word" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                           "category" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                           "status" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                           "created_at" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                           "updated_at" "pg_catalog"."varchar" COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sensitive_word"."id" IS 'id';
COMMENT ON COLUMN "public"."sensitive_word"."word" IS '敏感词内容';
COMMENT ON COLUMN "public"."sensitive_word"."category" IS '敏感词类别';
COMMENT ON COLUMN "public"."sensitive_word"."status" IS '敏感词状态';
COMMENT ON COLUMN "public"."sensitive_word"."created_at" IS '创建时间戳';
COMMENT ON COLUMN "public"."sensitive_word"."updated_at" IS '更新时间戳';

-- ----------------------------
-- Primary Key structure for table sensitive_word
-- ----------------------------
ALTER TABLE "public"."sensitive_word" ADD CONSTRAINT "sensitive_word_pkey" PRIMARY KEY ("id");




-- ----------------------------
-- Table structure for word_frequency
-- ----------------------------
DROP TABLE IF EXISTS "public"."word_frequency";
CREATE TABLE "public"."word_frequency" (
                                           "id" "pg_catalog"."int4" NOT NULL,
                                           "word" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                           "count_num" "pg_catalog"."int4",
                                           "business_type" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                           "create_time" "pg_catalog"."date",
                                           "update_time" "pg_catalog"."date"
)
;
COMMENT ON COLUMN "public"."word_frequency"."id" IS 'id';
COMMENT ON COLUMN "public"."word_frequency"."word" IS '分词';
COMMENT ON COLUMN "public"."word_frequency"."count_num" IS '出现频次';
COMMENT ON COLUMN "public"."word_frequency"."business_type" IS '业务类型';
COMMENT ON COLUMN "public"."word_frequency"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."word_frequency"."update_time" IS '更新时间';

-- ----------------------------
-- Primary Key structure for table word_frequency
-- ----------------------------
ALTER TABLE "public"."word_frequency" ADD CONSTRAINT "word_frequency_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Table structure for sensitive_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."sensitive_category";
CREATE TABLE "public"."sensitive_category" (
                                               "id" "pg_catalog"."int4" NOT NULL,
                                               "category_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
                                               "created_time" "pg_catalog"."date",
                                               "update_time" "pg_catalog"."date",
                                               "status" "pg_catalog"."varchar" COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sensitive_category"."id" IS '主键ID';
COMMENT ON COLUMN "public"."sensitive_category"."category_name" IS '分类名';
COMMENT ON COLUMN "public"."sensitive_category"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."sensitive_category"."update_time" IS '更新时间';

-- ----------------------------
-- Primary Key structure for table sensitive_category
-- ----------------------------
ALTER TABLE "public"."sensitive_category" ADD CONSTRAINT "sensitive_category_pkey" PRIMARY KEY ("id");