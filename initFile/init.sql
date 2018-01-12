use ltsh;
drop table if exists message_info;

drop table if exists message_info_file;

drop table if exists user_friend;

drop table if exists user_friend_group;

drop table if exists user_friend_group_rel;

drop table if exists user_group;

drop table if exists user_group_rel;

drop table if exists user_info;



/*==============================================================*/
/* Table: message_info                                          */
/*==============================================================*/
create table message_info
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   msg_context          blob comment '消息内容',
   template_id          integer comment '模板id',
   msg_type             integer comment '消息类型,0:文本消息,1:富文本消息.',
   to_user              integer comment '接收用户',
   read_time            datetime comment '读取时间',
   send_type            integer comment '发送类型,0:私信,1:订阅消息,2:系统推送',
   status               varchar(10) comment '状态,0::未读,1:已读,2:已删',
   source_id            varchar(50) comment '来源id',
   source_type          varchar(50) comment '来源类型',
   primary key (id)
);

alter table message_info comment '消息记录表';

/*==============================================================*/
/* Table: message_info_file                                     */
/*==============================================================*/
create table message_info_file
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   message_id           integer comment '消息id',
   file_path            varchar(150) comment '文件路径',
   local_path           varchar(150) comment '本机文件路径',
   file_type            varchar(50) comment '文件类型',
   original_filename    varchar(150) comment '原文件名称',
   primary key (id)
);

alter table message_info_file comment '消息文件记录表';

/*==============================================================*/
/* Table: user_friend                                           */
/*==============================================================*/
create table user_friend
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   name                 varchar(50) comment '好友名称',
   type                 integer comment '好友类型',
   status               varchar(10) comment '好友状态',
   remarks              varchar(500) comment '好友备注',
   friend_user_id       integer comment '好友用户id',
   sort                 integer comment '排序',
   primary key (id)
);

alter table user_friend comment '用户好友';

/*==============================================================*/
/* Table: user_friend_group                                     */
/*==============================================================*/
create table user_friend_group
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   name                 varchar(50) comment '名称',
   sort                 integer comment '排序',
   status               varchar(10) comment '状态',
   type                 int comment '类型',
   primary key (id)
);

alter table user_friend_group comment '好友分组';

/*==============================================================*/
/* Table: user_friend_group_rel                                 */
/*==============================================================*/
create table user_friend_group_rel
(
   id                   integer not null auto_increment,
   group_id             integer comment '分组id',
   friend_id            integer comment '好友id',
   sort                 int comment '排序',
   primary key (id)
);

alter table user_friend_group_rel comment '分组好友关系表';

/*==============================================================*/
/* Table: user_group                                            */
/*==============================================================*/
create table user_group
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   name                 varchar(200) comment '名称',
   type                 integer comment '类型',
   status               varchar(10) comment '状态',
   owner                int comment '所有者',
   validity             datetime comment '有效期',
   level_type           int comment '级别类型',
   primary key (id)
);

alter table user_group comment '群组';

/*==============================================================*/
/* Table: user_group_rel                                        */
/*==============================================================*/
create table user_group_rel
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   nick_name            varchar(50) comment '昵称',
   role                 int comment '角色',
   level                varchar(50) comment '级别',
   group_id             int comment '群组id',
   user_id              int comment '用户id',
   primary key (id)
);

alter table user_group_rel comment '群组成员';

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   id                   integer not null auto_increment comment '主键',
   create_by            integer comment '创建用户',
   create_time          datetime comment '创建时间',
   update_by            integer comment '修改用户',
   update_time          datetime comment '修改时间',
   login_name           varchar(50) comment '登录名',
   password             varchar(128) comment '密码',
   name                 varchar(50) comment '用户名称',
   nick_name            varchar(50) comment '昵称',
   tel                  varchar(11) comment '电话号码',
   phone                varchar(11) comment '手机号码',
   address              varchar(200) comment '地址',
   email                varchar(100) comment '电子邮箱',
   idcard               varchar(18) comment '身份证',
   zip                  varchar(10) comment '邮政编码',
   status               varchar(10) comment '状态',
   sex                  integer comment '性别',
   birth                date comment '出生年月',
   remarks              varchar(500) comment '备注',
   last_login_time      datetime comment '最后登录时间',
   login_count          integer comment '登录次数',
   primary key (id)
);

alter table user_info comment '用户信息表';
