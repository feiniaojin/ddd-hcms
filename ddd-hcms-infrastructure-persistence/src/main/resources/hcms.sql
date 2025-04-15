create table hcms_content_entry
(
    id            bigint auto_increment comment '自增主键' primary key,
    entry_id      varchar(64) not null comment '业务唯一标识',
    type_id       varchar(64) not null comment 'hcms_content_type表的唯一标识',
    status        int      default 0 null comment '状态：0草稿，1已发布，2撤回',
    deleted       int      default 0 null comment '逻辑删除标记[0-正常；1-已删除]',
    created_by    varchar(100) null comment '创建人',
    created_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    modified_by   varchar(100) null comment '更新人',
    modified_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    version       bigint   default 1 null comment '乐观锁'
) comment '内容实例表' collate = utf8mb4_bin;

create index idx_TypeId_EntryId_Deleted
    on hcms_content_entry (type_id, deleted, entry_id);

create table hcms_content_entry_field
(
    id              bigint auto_increment comment '自增主键' primary key,
    entry_field_id  varchar(64) not null comment '业务唯一标识',
    entry_id        varchar(64) not null comment 'entry的唯一标识',
    type_field_id   varchar(64) not null comment '属性定义的唯一标识',
    field_name      varchar(64) not null comment '属性定义的字段名称',
    field_data_type int      default 0 null comment '字段数据类型',
    field_value     varchar(64) not null comment '属性定义的字段值',
    status          int      default 0 null comment '状态：0草稿，1已发布，2撤回',
    deleted         int      default 0 null comment '逻辑删除标记[0-正常；1-已删除]',
    created_by      varchar(100) null comment '创建人',
    created_time    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    modified_by     varchar(100) null comment '更新人',
    modified_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    version         bigint   default 1 null comment '乐观锁'
) comment '内容实例字段表' collate = utf8mb4_bin;

create index idx_biz_id
    on hcms_content_entry_field (entry_field_id);

create index idx_entry_id
    on hcms_content_entry_field (entry_id);

create index q
    on hcms_content_entry_field (field_value, field_name, entry_id);

create table hcms_content_type
(
    id            bigint auto_increment comment '自增主键' primary key,
    type_id       varchar(64) not null comment '业务唯一标识',
    display_name  varchar(64) not null comment '名称',
    status        int      default 0 null comment '状态：0草稿，1已发布，2撤回',
    deleted       int      default 0 null comment '逻辑删除标记[0-正常；1-已删除]',
    created_by    varchar(100) null comment '创建人',
    created_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    modified_by   varchar(100) null comment '更新人',
    modified_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    version       bigint   default 1 null comment '乐观锁'
) comment 'Content Type表' collate = utf8mb4_bin;

create index idx_BizId
    on hcms_content_type (type_id);

create table hcms_content_type_field
(
    id              bigint auto_increment comment '自增主键' primary key,
    field_id        varchar(64)        not null comment '业务唯一标识',
    type_id         varchar(64)        not null comment '业务唯一标识',
    field_name      varchar(64)        not null comment '属性名称',
    field_data_type int      default 0 not null comment '属性的数据类型',
    status          int      default 0 null comment '状态：0草稿，1已发布，2撤回',
    deleted         int      default 0 null comment '逻辑删除标记[0-正常；1-已删除]',
    created_by      varchar(100) null comment '创建人',
    created_time    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    modified_by     varchar(100) null comment '更新人',
    modified_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    version         bigint   default 1 null comment '乐观锁'
) comment '内容属性定义表' collate = utf8mb4_bin;

create index idx_biz
    on hcms_content_type_field (type_id, field_id);

create table hcms_files
(
    id               bigint auto_increment comment '自增主键' primary key,
    document_id      varchar(64)  not null comment '业务唯一标识',
    name             varchar(64)  not null comment '文件名称',
    alternative_text varchar(64)  not null comment '备用文本',
    title            varchar(64)  not null comment '标题',
    ext              varchar(64)  not null comment '扩展名',
    folder_path      varchar(64)  not null comment '文件夹路径',
    url              varchar(128) not null comment '文件路径',
    deleted          int      default 0 null comment '逻辑删除标记[0-正常；1-已删除]',
    created_by      varchar(100) null comment '创建人',
    created_date     datetime default CURRENT_TIMESTAMP null comment '创建时间',
    last_modified_by      varchar(100) null comment '更新人',
    last_modified_date    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    version          bigint   default 1 null comment '乐观锁'
) comment '文件表' collate = utf8mb4_bin;

