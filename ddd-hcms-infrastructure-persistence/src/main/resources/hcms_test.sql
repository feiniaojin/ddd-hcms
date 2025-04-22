INSERT INTO hcms.hcms_content_type (type_id,display_name,status,deleted,created_by,created_time,modified_by,modified_time,version) VALUES
    ('1','article',0,0,NULL,'2025-04-22 17:04:56',NULL,'2025-04-22 17:04:56',1);

INSERT INTO hcms.hcms_content_type_field (field_id, type_id, field_name, field_data_type, status, deleted, created_by,
                                          created_time, modified_by, modified_time, version)
VALUES ('1', '1', 'id', 0, 0, 0, NULL, '2025-04-22 18:25:11', NULL, '2025-04-22 18:25:11', 1),
       ('2', '1', 'document_id', 0, 0, 0, NULL, '2025-04-22 18:25:37', NULL, '2025-04-22 18:25:37', 1),
       ('3', '1', 'title', 0, 0, 0, NULL, '2025-04-22 17:07:40', NULL, '2025-04-22 18:24:46', 1),
       ('4', '1', 'context', 1, 0, 0, NULL, '2025-04-22 17:07:40', NULL, '2025-04-22 18:40:38', 1),
       ('5', '1', 'author', 0, 0, 0, NULL, '2025-04-22 17:07:40', NULL, '2025-04-22 18:24:42', 1),
       ('6', '1', 'deleted', 0, 0, 0, NULL, '2025-04-22 18:26:19', NULL, '2025-04-22 18:26:19', 1),
       ('7', '1', 'created_by', 0, 0, 0, NULL, '2025-04-22 18:26:19', NULL, '2025-04-22 18:26:19', 1),
       ('8', '1', 'created_date', 0, 0, 0, NULL, '2025-04-22 18:27:00', NULL, '2025-04-22 18:27:00', 1),
       ('9', '1', 'last_modified_by', 0, 0, 0, NULL, '2025-04-22 18:27:18', NULL, '2025-04-22 18:27:18', 1),
       ('10', '1', 'last_modified_date', 0, 0, 0, NULL, '2025-04-22 18:27:31', NULL, '2025-04-22 18:27:31', 1);
INSERT INTO hcms.hcms_content_type_field (field_id, type_id, field_name, field_data_type, status, deleted, created_by,
                                          created_time, modified_by, modified_time, version)
VALUES ('11', '1', 'version', 0, 0, 0, NULL, '2025-04-22 18:27:47', NULL, '2025-04-22 18:27:47', 1);


INSERT INTO hcms.hcms_article (document_id, title, context, author, deleted, created_by, created_date, last_modified_by,
                               last_modified_date, version)
VALUES ('1', '标题1', '正文1', '作者1', 0, '1', '2025-04-22 17:03:29', '1', '2025-04-22 17:03:29', 1),
       ('2', '标题2', '正文2', '作者2', 0, '1', '2025-04-22 17:04:03', '1', '2025-04-22 17:04:03', 1),
       ('3', '标题3', '正文3', '作者3', 0, '1', '2025-04-22 17:04:03', '1', '2025-04-22 17:04:03', 1);