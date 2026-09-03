SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_no` varchar(10) NOT NULL COMMENT '管理员工号',
  `admin_password` varchar(255) NOT NULL COMMENT '密码',
  `admin_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `admin_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `admin_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `admin_role` varchar(20) DEFAULT 'admin' COMMENT '角色',
  `first_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_no_index` (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '00001', 'e10adc3949ba59abbe56e057f20f883e', 'Admin1', '13892774299', '915462313@qq.com', 'admin', '2020-04-08 01:38:56', '2020-04-08 01:38:56');
INSERT INTO `admin` VALUES ('2', '00002', 'e10adc3949ba59abbe56e057f20f883e', 'Admin2', '13892774299', '915462313@qq.com', 'admin', '2020-04-08 01:38:56', '2020-04-08 01:38:56');

-- ----------------------------
-- Table structure for choose
-- ----------------------------
DROP TABLE IF EXISTS `choose`;
CREATE TABLE `choose` (
  `choose_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` int(11) DEFAULT NULL COMMENT '外键-学生id',
  `course_id` int(11) DEFAULT NULL COMMENT '外键-课程id',
  `usual_grade` float DEFAULT NULL COMMENT '平时成绩',
  `exam_grade` float DEFAULT NULL COMMENT '考试成绩',
  `all_grade` float DEFAULT NULL COMMENT '总成绩',
  `my_term` varchar(20) DEFAULT NULL COMMENT '学期',
  `first_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`choose_id`),
  KEY `student_id_choose_index` (`student_id`),
  KEY `course_id_choose_index` (`course_id`),
  CONSTRAINT `course_id_choose_index` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_id_choose_index` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of choose
-- ----------------------------
INSERT INTO `choose` VALUES ('6', '2', '1', '90', '88', '88.6', '1-1', '2020-05-01 20:34:36', '2020-05-07 21:15:35');
INSERT INTO `choose` VALUES ('7', '2', '2', null, null, null, '1-1', '2020-05-01 20:34:37', '2020-05-01 20:34:37');
INSERT INTO `choose` VALUES ('8', '2', '3', null, null, null, '1-1', '2020-05-01 20:34:38', '2020-05-01 20:34:38');
INSERT INTO `choose` VALUES ('16', '1', '10', '100', '100', '100', '1-1', '2020-05-19 20:45:30', '2020-05-19 20:47:05');
INSERT INTO `choose` VALUES ('17', '1', '8', null, null, null, '1-1', '2020-05-19 20:45:33', '2020-05-19 20:45:33');
INSERT INTO `choose` VALUES ('18', '1', '12', null, null, null, '1-1', '2020-05-19 20:45:35', '2020-05-19 20:45:35');
INSERT INTO `choose` VALUES ('19', '4', '24', null, null, null, '1-1', '2020-05-29 16:44:48', '2020-05-29 16:44:48');
INSERT INTO `choose` VALUES ('20', '4', '10', null, null, null, '1-1', '2020-05-29 16:44:55', '2020-05-29 16:44:55');
INSERT INTO `choose` VALUES ('21', '5', '24', null, null, null, '1-1', '2020-05-29 17:41:14', '2020-05-29 17:41:14');
INSERT INTO `choose` VALUES ('22', '5', '10', null, null, null, '1-1', '2020-05-29 17:41:26', '2020-05-29 17:41:26');
INSERT INTO `choose` VALUES ('23', '6', '24', null, null, null, '1-1', '2020-05-29 17:42:52', '2020-05-29 17:42:52');
INSERT INTO `choose` VALUES ('24', '6', '10', null, null, null, '1-1', '2020-05-29 17:42:58', '2020-05-29 17:42:58');
INSERT INTO `choose` VALUES ('25', '6', '6', null, null, null, '1-1', '2020-05-29 17:43:10', '2020-05-29 17:43:10');
INSERT INTO `choose` VALUES ('26', '7', '24', null, null, null, '1-1', '2020-05-29 17:43:31', '2020-05-29 17:43:31');
INSERT INTO `choose` VALUES ('27', '7', '10', null, null, null, '1-1', '2020-05-29 17:43:36', '2020-05-29 17:43:36');
INSERT INTO `choose` VALUES ('28', '8', '24', null, null, null, '1-1', '2020-05-29 17:43:53', '2020-05-29 17:43:53');
INSERT INTO `choose` VALUES ('29', '8', '10', null, null, null, '1-1', '2020-05-29 17:44:01', '2020-05-29 17:44:01');
INSERT INTO `choose` VALUES ('30', '9', '24', null, null, null, '1-1', '2020-05-29 17:44:22', '2020-05-29 17:44:22');
INSERT INTO `choose` VALUES ('31', '9', '10', null, null, null, '1-1', '2020-05-29 17:44:27', '2020-05-29 17:44:27');
INSERT INTO `choose` VALUES ('32', '10', '24', null, null, null, '1-1', '2020-05-29 17:45:00', '2020-05-29 17:45:00');
INSERT INTO `choose` VALUES ('33', '10', '10', null, null, null, '1-1', '2020-05-29 17:45:09', '2020-05-29 17:45:09');
INSERT INTO `choose` VALUES ('34', '10', '13', null, null, null, '1-1', '2020-05-29 17:45:23', '2020-05-29 17:45:23');
INSERT INTO `choose` VALUES ('35', '11', '24', null, null, null, '1-1', '2020-05-29 17:45:45', '2020-05-29 17:45:45');
INSERT INTO `choose` VALUES ('36', '11', '10', null, null, null, '1-1', '2020-05-29 17:45:53', '2020-05-29 17:45:53');
INSERT INTO `choose` VALUES ('37', '11', '5', null, null, null, '1-1', '2020-05-29 17:45:58', '2020-05-29 17:45:58');
INSERT INTO `choose` VALUES ('38', '12', '24', null, null, null, '1-1', '2020-05-29 17:46:16', '2020-05-29 17:46:16');
INSERT INTO `choose` VALUES ('39', '12', '10', null, null, null, '1-1', '2020-05-29 17:46:22', '2020-05-29 17:46:22');
INSERT INTO `choose` VALUES ('40', '13', '24', null, null, null, '1-1', '2020-05-29 17:46:40', '2020-05-29 17:46:40');
INSERT INTO `choose` VALUES ('41', '13', '10', null, null, null, '1-1', '2020-05-29 17:46:45', '2020-05-29 17:46:45');
INSERT INTO `choose` VALUES ('42', '17', '24', null, null, null, '1-1', '2020-05-29 17:47:07', '2020-05-29 17:47:07');
INSERT INTO `choose` VALUES ('43', '17', '10', null, null, null, '1-1', '2020-05-29 17:47:13', '2020-05-29 17:47:13');
INSERT INTO `choose` VALUES ('44', '18', '24', null, null, null, '1-1', '2020-05-29 17:47:35', '2020-05-29 17:47:35');
INSERT INTO `choose` VALUES ('45', '18', '10', null, null, null, '1-1', '2020-05-29 17:47:41', '2020-05-29 17:47:41');
INSERT INTO `choose` VALUES ('46', '19', '24', null, null, null, '1-1', '2020-05-29 17:48:03', '2020-05-29 17:48:03');
INSERT INTO `choose` VALUES ('47', '19', '10', null, null, null, '1-1', '2020-05-29 17:48:08', '2020-05-29 17:48:08');
INSERT INTO `choose` VALUES ('48', '20', '24', null, null, null, '1-1', '2020-05-29 17:48:29', '2020-05-29 17:48:29');
INSERT INTO `choose` VALUES ('49', '20', '10', null, null, null, '1-1', '2020-05-29 17:48:35', '2020-05-29 17:48:35');
INSERT INTO `choose` VALUES ('50', '21', '24', null, null, null, '1-1', '2020-05-29 17:48:59', '2020-05-29 17:48:59');
INSERT INTO `choose` VALUES ('51', '21', '10', null, null, null, '1-1', '2020-05-29 17:49:06', '2020-05-29 17:49:06');
INSERT INTO `choose` VALUES ('52', '22', '24', null, null, null, '1-1', '2020-05-29 17:49:27', '2020-05-29 17:49:27');
INSERT INTO `choose` VALUES ('53', '22', '10', null, null, null, '1-1', '2020-05-29 17:49:31', '2020-05-29 17:49:31');
INSERT INTO `choose` VALUES ('54', '23', '24', null, null, null, '1-1', '2020-05-29 17:49:50', '2020-05-29 17:49:50');
INSERT INTO `choose` VALUES ('55', '23', '10', null, null, null, '1-1', '2020-05-29 17:49:55', '2020-05-29 17:49:55');
INSERT INTO `choose` VALUES ('56', '25', '24', null, null, null, '1-1', '2020-05-29 17:50:13', '2020-05-29 17:50:13');
INSERT INTO `choose` VALUES ('57', '25', '10', null, null, null, '1-1', '2020-05-29 17:50:17', '2020-05-29 17:50:17');
INSERT INTO `choose` VALUES ('58', '26', '24', null, null, null, '1-1', '2020-05-29 17:50:36', '2020-05-29 17:50:36');
INSERT INTO `choose` VALUES ('59', '26', '10', null, null, null, '1-1', '2020-05-29 17:50:41', '2020-05-29 17:50:41');
INSERT INTO `choose` VALUES ('60', '27', '24', null, null, null, '1-1', '2020-05-29 17:50:56', '2020-05-29 17:50:56');
INSERT INTO `choose` VALUES ('61', '27', '10', '90', '96', '94.2', '1-1', '2020-05-29 17:50:59', '2020-05-29 17:51:49');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_name` varchar(100) DEFAULT NULL COMMENT '课程名',
  `course_picture` varchar(255) DEFAULT NULL COMMENT '书的封面url',
  `major_id` int(11) DEFAULT NULL COMMENT '外键-课程所属专业',
  `teacher_id` int(11) DEFAULT NULL COMMENT '外键-教师id',
  `course_type` varchar(20) DEFAULT NULL COMMENT '课程类型',
  `course_score` float DEFAULT NULL COMMENT '课程学分',
  `stock` int(11) DEFAULT NULL COMMENT '课程人数',
  `address` varchar(50) DEFAULT NULL COMMENT '上课地点与时间',
  `term` varchar(20) DEFAULT NULL COMMENT '学期',
  `number` int(11) DEFAULT '0' COMMENT '已选人数',
  `description` varchar(500) DEFAULT NULL COMMENT '课程简介',
  `first_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`course_id`),
  KEY `major_id_course_index` (`major_id`),
  KEY `teacher_id_course_index` (`teacher_id`),
  CONSTRAINT `major_id_course_index` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `teacher_id_course_index` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '大学生创新创业指导', '#', '6', '2', '创新创业类', '4', '99', '星期三A101第7-8节{1-16周}', '1-1', '1', '陶冶情操', '2020-04-08 01:34:20', '2020-05-14 20:31:41');
INSERT INTO `course` VALUES ('2', '人体的奥秘', '#', '6', '19', '自然科学类', '2', '99', '星期四A102第5-6节{1-16周}', '1-1', '1', '陶冶情操', '2020-04-08 01:34:20', '2020-05-14 20:31:19');
INSERT INTO `course` VALUES ('3', '如何学好英语', '#', '6', '7', '公共艺术类', '1', '99', '星期一A103第7-8节{1-16周}', '1-1', '1', '陶冶情操', '2020-04-09 20:27:20', '2020-05-14 20:31:33');
INSERT INTO `course` VALUES ('4', '婚恋指导', '#', '6', '2', '公共艺术类', '2', '100', '星期二A104第3-4节{1-16周}', '1-1', '0', '陶冶情操', '2020-04-09 20:27:20', '2020-04-27 14:42:33');
INSERT INTO `course` VALUES ('5', '电影的艺术', '#', '6', '5', '限定艺术类', '1', '99', '星期三A105第7-8节{1-16周}', '1-1', '1', '陶冶情操', '2020-04-09 20:27:20', '2020-05-29 17:45:58');
INSERT INTO `course` VALUES ('6', '戏剧欣赏', '#', '7', '1', '限定艺术类', '2', '99', '星期四A106第3-4节{1-18周}', '1-1', '1', '陶冶情操', '2020-04-09 20:27:20', '2020-05-29 17:43:10');
INSERT INTO `course` VALUES ('7', '中药理学', '#', '7', '19', '公共艺术类', '2', '100', '星期五A107第7-8节{1-18周}', '1-1', '0', '陶冶情操', '2020-04-09 20:27:20', '2020-04-27 12:25:23');
INSERT INTO `course` VALUES ('8', '创业管理实战', '#', '7', '4', '创新创业类', '1', '99', '星期一A108第7-8节{1-10周}', '1-1', '1', '本课程主要介绍了创业的评估和风险，商业的模式、计划和展示，以及创业管理的技巧和方法，对大学生创业有很好的借鉴和指导作用。', '2020-04-27 12:54:32', '2020-05-19 20:45:33');
INSERT INTO `course` VALUES ('9', '信息论基础', '#', '6', '5', '自然科学类', '2', '100', '星期二B105第5-6节{1-16周}', '1-1', '0', '讲解信息的编码解码', '2020-04-27 13:06:35', '2020-04-27 13:06:38');
INSERT INTO `course` VALUES ('10', '安全协议', '#', '6', '3', '自然科学类', '2', '79', '星期五B114第7-8节{1-16周}', '1-1', '21', '讲解传输协议及算法', '2020-04-27 13:18:55', '2020-05-29 17:50:59');
INSERT INTO `course` VALUES ('11', '网络安全技术A', '#', '6', '6', '自然科学类', '4', '100', '星期二B132第5-6节{1-16周}', '1-1', '0', '讲解网络安全技术基础知识', '2020-04-27 13:23:34', '2020-04-27 14:43:11');
INSERT INTO `course` VALUES ('12', '计算机病毒检测技术', '#', '8', '7', '自然科学类', '2', '99', '星期三B210第5-6节{1-16周}', '1-1', '1', '讲解计算机病毒', '2020-04-27 13:26:11', '2020-05-19 20:45:35');
INSERT INTO `course` VALUES ('13', '数据库基础', '#', '9', '8', '自然科学类', '4', '99', '星期二B132第1-2节{1-16周}', '1-1', '1', '讲解MySQL数据库', '2020-04-27 13:28:31', '2020-05-29 17:45:23');
INSERT INTO `course` VALUES ('14', '数字信号处理C', '#', '10', '9', '自然科学类', '2', '100', '星期四B201第3-4节{1-16周}', '1-1', '0', '讲解数字信号', '2020-04-27 13:31:10', '2020-04-27 13:31:16');
INSERT INTO `course` VALUES ('15', '信息安全导论', '#', '6', '10', '自然科学类', '2', '100', '星期五B301第3-4节{1-16周}', '1-1', '0', '信息安全导论', '2020-04-27 13:33:14', '2020-05-19 16:51:20');
INSERT INTO `course` VALUES ('16', '创新思维训练', '#', '11', '17', '创新创业类', '2', '100', '星期一B301第3-4节{1-16周}', '1-1', '0', '创新思维培养', '2020-04-27 13:45:44', '2020-04-27 13:45:47');
INSERT INTO `course` VALUES ('17', '电影与幸福感', '#', '8', '18', '公共艺术类', '1', '100', '星期五B312第5-6节{1-10周}', '1-1', '0', '电影艺术鉴赏', '2020-04-27 13:54:38', '2020-04-27 13:54:41');
INSERT INTO `course` VALUES ('18', '网页设计', '#', '11', '15', '公共艺术类', '2', '100', '星期二A406第7-8节{3-14周}', '1-1', '0', '学习HTML/CSS基础', '2020-04-27 13:59:22', '2020-04-27 13:59:27');
INSERT INTO `course` VALUES ('19', '计算机网络导论', '#', '11', '16', '公共艺术类', '1', '100', '星期三A212第7-8节{3-14周}', '1-1', '0', '计算机网络基础', '2020-04-27 14:01:47', '2020-04-27 14:01:49');
INSERT INTO `course` VALUES ('20', '人工智能概论', '#', '11', '17', '公共艺术类', '2', '100', '星期四A613第7-8节{3-14周}', '1-1', '0', '人工智能入门', '2020-04-27 14:03:47', '2020-04-27 14:23:30');
INSERT INTO `course` VALUES ('21', '音乐鉴赏', '#', '8', '18', '公共艺术类', '1', '100', '星期一A112第5-6节{3-14周}', '1-1', '0', '音乐艺术鉴赏', '2020-04-27 14:12:03', '2020-04-27 14:12:06');
INSERT INTO `course` VALUES ('22', '药品解析导论', '#', '8', '19', '限定艺术类', '2', '100', '星期二A301第5-6节{3-14周}', '1-1', '0', '药品分析基础', '2020-04-27 14:14:06', '2020-04-27 14:14:09');
INSERT INTO `course` VALUES ('23', '生物化学', '#', '8', '19', '限定艺术类', '2', '100', '星期一A302第3-4节{3-14周}', '1-1', '0', '生物化学基础', '2020-04-27 14:16:09', '2020-04-27 14:16:12');
INSERT INTO `course` VALUES ('24', '就业指导基础', '#', '6', '3', '创新创业类', '2', '80', '星期三B213第5-6节{3-14周}', '1-1', '20', '就业指导与职业规划', '2020-04-27 14:18:16', '2020-05-29 17:50:56');
INSERT INTO `course` VALUES ('25', '求职面试概论', '#', '8', '16', '创新创业类', '2', '100', '星期一B101第7-8节{3-14周}', '1-1', '0', '面试技巧培训', '2020-04-27 14:21:49', '2020-04-27 14:21:53');
INSERT INTO `course` VALUES ('26', 'java从入门到精通', '#', '11', '6', '创新创业类', '4', '100', '星期二B304第5-6节{1-16周}', '1-1', '0', 'Java编程基础', '2020-05-01 16:55:09', '2020-05-01 16:55:11');
INSERT INTO `course` VALUES ('27', '形式与政策Ⅰ', '#', '6', '3', '公共艺术类', '4', '100', '星期一B101第5-6节{1-12周}', '1-2', '0', '时事政策分析', '2020-05-29 17:13:04', '2020-05-29 17:13:06');
INSERT INTO `course` VALUES ('28', '密码学基础', '#', '6', '3', '公共艺术类', '4', '100', '星期二B201第3-4节{1-16周}', '1-2', '0', '密码学基础理论', '2020-05-29 17:20:56', '2020-05-29 17:20:59');
INSERT INTO `course` VALUES ('29', '计算机基础', '#', '6', '1', '自然科学类', '2', '100', '星期一B103第3-6节{1-12周}', '1-2', '0', '计算机基础知识', '2020-05-29 17:24:30', '2020-05-29 17:24:32');
INSERT INTO `course` VALUES ('30', '大学英语Ⅱ', '#', '6', '1', '限定艺术类', '2', '100', '星期二B201第1-2节{1-16周}', '1-2', '0', '英语听说读写训练', '2020-05-29 17:26:38', '2020-05-29 17:26:42');
INSERT INTO `course` VALUES ('31', '程序设计基础C', '#', '6', '3', '限定艺术类', '2', '100', '星期三B301第5-6节{1-16周}', '1-2', '0', 'C语言编程基础', '2020-05-29 17:28:32', '2020-05-29 17:28:34');
INSERT INTO `course` VALUES ('32', '大学英语Ⅲ', '#', '6', '3', '限定艺术类', '2', '100', '星期二B201第3-4节{1-16周}', '2-1', '0', '进阶英语学习', '2020-05-29 17:31:43', '2020-05-29 17:31:45');
INSERT INTO `course` VALUES ('33', '大学英语Ⅳ', '#', '6', '3', '限定艺术类', '2', '100', '星期三B201第5-6节{1-16周}', '2-2', '0', '高级英语学习', '2020-05-29 17:32:49', '2020-05-29 17:32:51');
INSERT INTO `course` VALUES ('34', '大学英语Ⅴ', '#', '6', '3', '限定艺术类', '2', '100', '星期三B301第1-2节{1-16周}', '3-1', '0', '英语应用能力培养', '2020-05-29 17:34:04', '2020-05-29 17:34:07');
INSERT INTO `course` VALUES ('35', '大学英语Ⅵ', '#', '6', '3', '限定艺术类', '2', '100', '星期四B301第3-4节{1-16周}', '3-2', '0', '英语专业技能培养', '2020-05-29 17:35:14', '2020-05-29 17:35:16');
INSERT INTO `course` VALUES ('36', '大学英语Ⅶ', '#', '6', '3', '限定艺术类', '2', '100', '星期四B302第5-6节{1-16周}', '4-1', '0', '英语专业拓展', '2020-05-29 17:36:39', '2020-05-29 17:36:41');
INSERT INTO `course` VALUES ('37', '大学英语Ⅷ', '#', '6', '3', '限定艺术类', '2', '100', '星期五B302第1-2节{1-16周}', '4-2', '0', '英语综合能力提升', '2020-05-29 17:37:28', '2020-05-29 17:37:31');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `major_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id主键自增',
  `major_name` varchar(20) DEFAULT NULL COMMENT '专业名称',
  `first_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('6', '信息安全', '2020-04-08 01:13:13', '2020-04-08 01:13:13');
INSERT INTO `major` VALUES ('7', '信息对抗', '2020-04-08 01:13:13', '2020-04-08 01:13:13');
INSERT INTO `major` VALUES ('8', '通信工程', '2020-04-08 01:13:13', '2020-04-08 01:13:13');
INSERT INTO `major` VALUES ('9', '物联网', '2020-04-08 01:13:13', '2020-04-08 01:13:13');
INSERT INTO `major` VALUES ('10', '电子信息工程', '2020-04-08 01:13:13', '2020-04-08 01:13:13');
INSERT INTO `major` VALUES ('11', '计算机科学与技术', '2020-04-08 01:13:13', '2020-04-08 01:13:13');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_no` varchar(10) NOT NULL COMMENT '学号',
  `student_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `student_sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `student_password` varchar(255) NOT NULL COMMENT '密码',
  `student_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `student_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `major_id` int(11) DEFAULT NULL COMMENT '外键major_id',
  `student_role` varchar(20) DEFAULT 'student' COMMENT '角色',
  `first_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `student_no_index` (`student_no`),
  KEY `major_id_student_index` (`major_id`),
  CONSTRAINT `major_id_student_index` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '03163079', '景飞', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '13892774299', '915462313@qq.com', '6', 'student', '2020-04-08 01:13:49', '2020-05-14 17:15:30');
INSERT INTO `student` VALUES ('2', '03163078', '胡兵', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '18149498916', '849805319@qq.com', '6', 'student', '2020-04-08 01:15:08', '2020-04-27 00:33:39');
INSERT INTO `student` VALUES ('3', '03163077', '张丹杨', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '18740774435', '1849104569@qq.com', '6', 'student', '2020-04-27 12:31:03', '2020-04-27 14:47:02');
INSERT INTO `student` VALUES ('4', '03163000', '学生账号', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', '6', 'student', '2020-04-27 12:33:39', '2020-04-27 12:33:42');
INSERT INTO `student` VALUES ('5', '03163080', '学生80', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '6', 'student', '2020-05-29 16:51:16', '2020-05-29 16:51:20');
INSERT INTO `student` VALUES ('6', '03163081', '学生81', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '6', 'student', '2020-05-29 16:51:57', '2020-05-29 16:52:01');
INSERT INTO `student` VALUES ('7', '03163082', '学生82', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '6', 'student', '2020-05-29 16:52:34', '2020-05-29 16:52:40');
INSERT INTO `student` VALUES ('8', '03163083', '学生83', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '6', 'student', '2020-05-29 16:53:16', '2020-05-29 16:53:19');
INSERT INTO `student` VALUES ('9', '03163084', '学生84', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '6', 'student', '2020-05-29 16:53:57', '2020-05-29 16:53:59');
INSERT INTO `student` VALUES ('10', '03163085', '学生85', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '6', 'student', '2020-05-29 16:54:32', '2020-05-29 16:54:35');
INSERT INTO `student` VALUES ('11', '03163086', '学生86', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '7', 'student', '2020-05-29 16:55:21', '2020-05-29 16:55:23');
INSERT INTO `student` VALUES ('12', '03163087', '学生87', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '7', 'student', '2020-05-29 16:55:54', '2020-05-29 16:55:57');
INSERT INTO `student` VALUES ('13', '03163088', '学生88', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '7', 'student', '2020-05-29 16:56:24', '2020-05-29 16:56:27');
INSERT INTO `student` VALUES ('17', '03163089', '学生89', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '7', 'student', '2020-05-29 16:57:07', '2020-05-29 16:57:13');
INSERT INTO `student` VALUES ('18', '03163090', '学生90', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '7', 'student', '2020-05-29 16:57:55', '2020-05-29 16:57:58');
INSERT INTO `student` VALUES ('19', '03163091', '学生91', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '8', 'student', '2020-05-29 16:58:30', '2020-05-29 16:58:32');
INSERT INTO `student` VALUES ('20', '03163092', '学生92', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '8', 'student', '2020-05-29 16:59:02', '2020-05-29 16:59:04');
INSERT INTO `student` VALUES ('21', '03163093', '学生93', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '8', 'student', '2020-05-29 16:59:31', '2020-05-29 16:59:33');
INSERT INTO `student` VALUES ('22', '03163094', '学生94', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '9', 'student', '2020-05-29 17:00:52', '2020-05-29 17:00:55');
INSERT INTO `student` VALUES ('23', '03163095', '学生95', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', null, '9', 'student', '2020-05-29 17:00:50', '2020-05-29 17:00:57');
INSERT INTO `student` VALUES ('24', '03163096', '学生96', '男', 'a1f06b5f413ff1b6b35753cf3576c965', null, null, '10', 'student', '2020-05-29 17:02:24', '2020-05-29 17:02:26');
INSERT INTO `student` VALUES ('25', '03163097', '学生97', '男', 'a1f06b5f413ff1b6b35753cf3576c965', null, null, '10', 'student', '2020-05-29 17:02:58', '2020-05-29 17:03:00');
INSERT INTO `student` VALUES ('26', '03163098', '学生98', '男', 'a1f06b5f413ff1b6b35753cf3576c965', null, null, '10', 'student', '2020-05-29 17:03:31', '2020-05-29 17:03:33');
INSERT INTO `student` VALUES ('27', '03163099', '学生99', '男', 'a1f06b5f413ff1b6b35753cf3576c965', null, null, '11', 'student', '2020-05-29 17:03:55', '2020-05-29 17:03:58');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_no` varchar(10) NOT NULL COMMENT '老师工号',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `teacher_sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `teacher_password` varchar(255) NOT NULL COMMENT '密码',
  `teacher_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `teacher_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `teacher_role` varchar(20) DEFAULT 'teacher' COMMENT '角色',
  `major_id` int(11) DEFAULT NULL COMMENT '外键major_id',
  `first_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `teacher_no_index` (`teacher_no`),
  KEY `major_id_teacher_index` (`major_id`),
  CONSTRAINT `major_id_teacher_index` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '90001', '老师账号', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '13892774299', '915462313@qq.com', 'teacher', '6', '2020-04-08 01:23:40', '2020-04-27 00:35:54');
INSERT INTO `teacher` VALUES ('2', '90002', '杨宝华', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '13892774299', '915462313@qq.com', 'teacher', '6', '2020-04-08 01:23:40', '2020-04-08 01:23:40');
INSERT INTO `teacher` VALUES ('3', '90000', '侯红霞', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '23086487@qq.com', 'teacher', '6', '2020-04-27 01:44:56', '2020-04-27 01:45:02');
INSERT INTO `teacher` VALUES ('4', '90003', '李肖鸣', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '7', '2020-04-27 12:51:10', '2020-04-27 12:51:14');
INSERT INTO `teacher` VALUES ('5', '90004', '刘双根', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '6', '2020-04-27 13:00:18', '2020-04-27 13:00:21');
INSERT INTO `teacher` VALUES ('6', '90005', '董晓丽', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '6', '2020-04-27 13:01:42', '2020-04-27 13:01:46');
INSERT INTO `teacher` VALUES ('7', '90006', '李珅', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '8', '2020-04-27 13:10:55', '2020-04-27 13:10:57');
INSERT INTO `teacher` VALUES ('8', '90007', '赵锋', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '9', '2020-04-27 13:12:01', '2020-04-27 13:12:04');
INSERT INTO `teacher` VALUES ('9', '90008', '包志强', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '10', '2020-04-27 13:12:48', '2020-04-27 13:12:52');
INSERT INTO `teacher` VALUES ('10', '90009', '张雪锋', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '11', '2020-04-27 13:15:18', '2020-04-27 13:15:21');
INSERT INTO `teacher` VALUES ('11', '90010', '关苹苹', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '11', '2020-04-27 13:34:58', '2020-04-27 13:35:00');
INSERT INTO `teacher` VALUES ('12', '90011', '魏炳堂', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '10', '2020-04-27 13:35:47', '2020-04-27 13:35:49');
INSERT INTO `teacher` VALUES ('13', '90012', '苏荣和', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '9', '2020-04-27 13:36:25', '2020-04-27 13:36:30');
INSERT INTO `teacher` VALUES ('14', '90013', '张蓉蓉', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '8', '2020-04-27 13:37:32', '2020-04-27 13:37:36');
INSERT INTO `teacher` VALUES ('15', '90014', '陈英', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '11', '2020-04-27 13:40:54', '2020-04-27 13:40:56');
INSERT INTO `teacher` VALUES ('16', '90015', '李嵩林', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '6', '2020-04-27 13:41:51', '2020-04-27 13:41:54');
INSERT INTO `teacher` VALUES ('17', '90016', '王竹山', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '7', '2020-04-27 13:43:08', '2020-04-27 13:43:12');
INSERT INTO `teacher` VALUES ('18', '90017', '候隆龙', '男', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '10', '2020-04-27 13:47:05', '2020-04-27 13:47:07');
INSERT INTO `teacher` VALUES ('19', '90018', '张文雯', '女', 'a1f06b5f413ff1b6b35753cf3576c965', '', '', 'teacher', '10', '2020-04-27 13:50:28', '2020-04-27 13:50:30');

SET FOREIGN_KEY_CHECKS=1;