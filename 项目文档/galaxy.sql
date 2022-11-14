-- 数据库：galaxy

-- 用户表：创建数据表
drop table if exists ums_user;
create table ums_user
(
    id           bigint unsigned auto_increment,
    username     varchar(50)      default null comment '用户名',
    password     char(64)         default null comment '密码',
    nick         varchar(50)      default null comment '昵称',
    sex          tinyint unsigned default 0 comment '性别,0为男，1为女',
    avatar       varchar(255)     default null comment '头像URL',
    email        varchar(50)      default null comment '电子邮箱',
    phone        varchar(50)      default null comment '手机号码',
    enable       tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    last_login_ip  varchar(50)    default null comment '最后登录ip地址',
    login_count  int unsigned     default 0 comment '累计登录次数',
    gmt_last_login  datetime      default null comment '最后登录时间',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '用户' charset utf8mb4;

-- 角色表：创建数据表
drop table if exists ums_role;
create table ums_role
(
    id           bigint unsigned auto_increment,
    name         varchar(50)       default null comment '名称',
    gmt_create   datetime          default null comment '数据创建时间',
    gmt_modified datetime          default null comment '数据最后修改时间',
    primary key (id)
) comment '角色' charset utf8mb4;

-- 权限表：创建数据表
drop table if exists ums_permission;
create table ums_permission
(
    id                     bigint unsigned auto_increment,
    description            varchar(255)    default null comment '描述',
    value                  varchar(50)     default null comment '值',
    gmt_create             datetime        default null comment '数据创建时间',
    gmt_modified           datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '权限' charset utf8mb4;

-- 角色权限表：创建数据表
drop table if exists ums_role_permission;
create table ums_role_permission
(
    id           bigint unsigned auto_increment,
    role_id      bigint unsigned  default null comment '角色id',
    permission_id bigint unsigned default null comment '权限id',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '角色权限' charset utf8mb4;

-- 用户角色表：创建数据表
drop table if exists ums_user_role;
create table ums_user_role
(
    id           bigint unsigned auto_increment,
    user_id      bigint unsigned default null comment '用户id',
    role_id      bigint unsigned default null comment '角色id',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '用户角色' charset utf8mb4;

-- 电影表：创建数据表
drop table if exists fms_film;
create table fms_film
(
    id                 bigint unsigned auto_increment,
    name               varchar(50)      default null comment '电影名',
    actor              varchar(100)     default null comment '电影演职人员',
    director           varchar(100)     default null comment '电影导演',
    detail             varchar(350)     default null comment '电影详情',
    duration           varchar(10)      default null comment '电影时长',
    type               varchar(20)      default null comment '电影类型',
    hot                bigint unsigned  default null comment '热度（电影票房、电影评分）',
    gmt_release        datetime         default null comment '电影上映时间',
    region             varchar(50)      default null comment '电影制片地区',
    picture            varchar(255)     default null comment '电影封面URL',
    state              tinyint unsigned default 0 comment '电影状态 默认为1  1：在线 0：下架',
    gmt_create         datetime         default null comment '数据创建时间',
    gmt_modified       datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '电影' charset utf8mb4;

INSERT INTO `fms_film` VALUES (1, '毒液：致命守护者', '汤姆·哈迪:埃迪·布洛克/毒液,米歇尔·威廉姆斯:安妮·韦英', '鲁本·弗雷斯彻', '身为记者的埃迪·布洛克（汤姆·哈迪饰）在调查生命基金会老板卡尔顿·德雷克（里兹·阿迈德饰）的过程中，事业遭受重创，与未婚妻安妮·韦英（米歇尔·威廉姆斯饰）的关系岌岌可危，并意外被外星共生体控制，他历经挣扎对抗，最终成为拥有强大超能力，无人可挡的“毒液“', '107分钟', '动作，科幻', 7.2, '2019-06-24', '美国', 'http://localhost:9060/upload/movies/Venom.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (2, '神奇动物：格林德沃之罪', '埃迪·雷德梅恩:纽特·斯卡曼德,凯瑟琳·沃特斯顿:蒂娜‧戈德斯坦,约翰尼·德普:盖勒·格林德沃', '大卫·叶茨', '在《神奇动物在那里》第一部的结尾，纽特·斯卡曼德（埃迪·雷德梅恩 饰）协助美国魔法国会，将强大的黑巫师盖勒特·格林德沃（约翰尼·德普 饰）抓捕归案。但格林德沃不久便兑现狂言成功越狱，并开始纠集信徒，着手实现他们的邪恶目的：让纯血统的巫师成为统治阶层，镇压一切非魔法生物。为挫败格林德沃的阴谋，阿不思·邓布利多（裘德·洛 饰）向昔日的学生纽特·斯卡曼德寻求帮助。纽特欣然允诺，却没有意识到，他将踏上的会是一段充满艰险的未来征途。此时的魔法世界面临空前的分裂乱局，阶层鸿沟日益加深，爱与忠诚备受考验，至亲好友也可能反目成仇……', '134分钟', '奇幻，冒险', 7.7, '2019-06-25', '美国', 'http://localhost:9060/upload/movies/Fantastic Beasts.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (3, '无名之辈',  '陈建斌:马先勇,任素汐:马嘉旗,潘斌龙:李海根', '饶晓志', '在一座山间小城中，一对低配劫匪、一个落魄的泼皮保安、一个身体残疾却性格彪悍的残毒舌女以及一系列生活在社会不同轨迹上的小人物，在一个貌似平常的日子里，因为一把丢失的老枪和一桩当天发生在城中的乌龙劫案，从而被阴差阳错地拧到一起，发生的一幕幕令人啼笑皆非的荒诞喜剧', '108分钟', '荒诞，喜剧', 9.2, '2019-07-05', '中国大陆', 'http://localhost:9060/upload/movies/A Cool Fish.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (4, '名侦探柯南：零的执行人', '高山南:江户川柯南,山崎和佳奈:毛利兰,林原惠美:灰原哀', '立川让', '5月1日，东京湾边的新建筑“海洋边缘”将举办首脑云集的东京峰会。然而，峰会开办前一周，会场发生超大规模的爆炸事件，并出现了安室透的身影。疑似恐怖袭击的事件引起了警察部门的严肃调查。在警察局大型搜查会议上，公安部门提交证物，却发现疑犯指纹与毛利小五郎（小山力也 配音）指纹吻合。作为律师的妃英理努力收集证据证明丈夫的无辜，却无力阻止毛利小五郎被收监。看到毛利兰（山崎和佳奈 配音）绝望哭泣的样子，柯南（高山南 配音）决定调查事件真相，还毛利小五郎清白。另一方面，少年侦探团的孩子们正紧密关注着无人探测器“天鹅”的回航任务。行踪诡异的安室透、惨遭陷害的毛利小五郎、错综复杂的警察部门、即将着陆的无人探测器；随着“机密任务”进入倒计时，关乎整个东京的可怕计划拉开帷幕…', '111分钟', '悬疑，冒险，动画', 8.5, '2019-07-13', '日本', 'http://localhost:9060/upload/movies/名探偵.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (5, '你好，之华', '周迅:袁之华,秦昊:尹川,杜江:周文涛', '岩井俊二', '有人慌张得见面，有人简单地告别。姐姐袁之南离世的那个清晨，只匆匆留下一封信和一张同学会邀请函。妹妹之华(周迅 饰)代替姐姐参加，却意外遇见年少时的倾慕对象尹川（秦昊 饰）。往日的记忆在苏醒，但再次相见，已物是人非', '114分钟', '爱情', 7.9, '2019-07-20', '中国大陆、日本', 'http://localhost:9060/upload/movies/Last Letter.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (6, '恐龙王', '吕佩玉:钢妈,王衡:斑大师,孙晔:八百度', '施雅雅', '陆地霸主特暴龙“斑大师”和自己的小儿子“小疙瘩”生活在一起。“小疙瘩”自幼失去了母亲，生性懦弱，严厉的“斑大师”虽然心底十分疼爱自己的孩子，但是急于让“小疙瘩”成长为新的陆地霸主，常常忍不住责骂“小疙瘩”，父子俩虽然相依为命，却始终有一些隔阂。 一天“小疙瘩”被几只邪恶的恐爪龙抓走，“斑大师”踏上漫漫的寻子之路，路途中他结识了有高度近视眼的美甲龙“八百度”，两人穿过“巨蝎峡”、走出“长颈龙绿洲”、踏上火山峡谷，经历了重重难关。而身处险境的“小疙瘩”也结识了一些新的朋友，并且开始和邪恶的恐爪龙斗智斗勇。最终父子两人终于相见，但是却不得不一起面对一个更加凶恶的史前怪物……', '95分钟', '喜剧，动画，冒险', 8.9, '2018-12-10', '中国大陆', 'http://localhost:9060/upload/movies/DINO KING.jpg', 0,null,null);
INSERT INTO `fms_film` VALUES (7, '冰封侠：时空行者', '甄子丹:贺英,黄圣依:小美,王宝强:萨獒', '叶伟民', '明朝大将军贺英（甄子丹 饰）利用时空金球终于重返明朝，与锦衣卫兄弟萨獒（王宝强 饰）获悉了倭寇和朝廷奸党之间足以倾覆皇权的密谋，绵延400年的惊天危机一触即发。贺英也在红颜知己小美（黄圣依 饰）的帮助下开始了抗倭锄奸和保护族人的战斗', '87分钟', '剧情，动作', 4.1, '2018-09-02', '中国大陆', 'http://localhost:9060/upload/movies/Bing Feng Xia II.jpg', 0,null,null);
INSERT INTO `fms_film` VALUES (8, '梦境之源', '陈志朋:徐朗,颜丹晨:李雪,方中信:梁文道', '柳珂', '货车司机李昂由于童年时代内向懦弱，颓废度日，频频做噩梦，严重影响了正常的生活，并因此不得不接受心理医生曹井润的催眠治疗，却在梦境中意外卷入一场凶杀案。睿智破案的警探成为凶手，而真正的幕后黑手仍在逍遥法外……', '90分钟', '悬疑，推理', 5.9, '2018-11-16', '中国大陆', 'http://localhost:9060/upload/movies/Source of Dreams.jpg', 0,null,null);
INSERT INTO `fms_film` VALUES (9, '摘金奇缘',  '吴恬敏:朱瑞秋,亨利·戈尔丁:杨尼克,杨紫琼:杨爱莉', '朱浩伟', '新加坡富二代王子杨尼克（亨利·戈尔丁饰）自豪地带着美丽大方、学识傲人的女友朱瑞秋（吴恬敏饰）回家见亲友。而这个巨富大家族对朱瑞秋的态度，与她的想像相差十万八千里远，朱瑞秋一开始以为只是跟深爱的男人轻松浪漫地度假，不料却面对排山倒海般的压力，她必须坚强应对一群有心机的情敌和反对者，但更难搞的竟然是她的准婆婆杨爱莉（杨紫琼饰），因为埃莉诺认为朱瑞秋这个现代美国女孩永远都高攀不上她们家', '120分钟', '喜剧，爱情', 6.1, '2018-11-30', '美国', 'http://localhost:9060/upload/movies/Crazy Rich Asians.jpg', 0, null, null);
INSERT INTO `fms_film` VALUES (10, '海王', '杰森·莫玛:海王/亚瑟·库瑞,艾梅柏·希尔德:海后/湄拉,威廉·达福:努迪斯·维科', '温子仁', '在一场狂风暴雨的海边灯塔看守人汤姆·库瑞（特穆拉·莫里森饰）救了受伤的亚特兰蒂斯女王亚特兰娜（妮可·基德曼饰）之后，他们相爱了，生下了拥有半人类、半亚特兰蒂斯人的血统亚瑟·库瑞（杰森·莫玛饰）。为了救自己的爱人和儿子亚特兰娜选择了离开。\r\n几年之后，亚特兰娜被迫回到海底国家缔结政治婚姻，生下儿子奥姆（帕特里克·威尔森饰）。奥姆长大后当上国王对陆地人类充满憎恨，开始吞并海底中发展中的国家的兵力，一举消灭陆地人。奥姆的未婚妻海底王国泽贝尔公主湄拉（艾梅柏·希尔德饰）打算阻止这场战争，她到陆地找回亚瑟，让他以亚特兰娜女王长子身份回亚特兰蒂斯把王位争回来，而且湄拉要协助亚瑟找回能统治大海的失落的三叉戟', '143分钟', '动作，科幻', 7.6,  '2018-12-07', '美国、澳大利亚', 'http://localhost:9060upload/movies/Aquaman.jpg', 0,null,null);
INSERT INTO `fms_film` VALUES (15, '素人特工',  '王大陆:赵风,张榕容:淼淼', '袁锦麟', '极限运动达人赵风（王大陆 饰），误打误撞闯入了一场国际犯罪交易，不得不跟随国际特工（米拉·乔沃维奇 饰）一起前往布达佩斯。在这里他与废柴刑警淼淼（张榕容 饰）、民间科学家丁山（许魏洲 饰）与待业医生LV（刘美彤 饰）组成一支素人特工小队。这四个特工小白和高级国际特攻米拉一起，与恐怖分子开启了一场又惊又喜的斗争。', '113分钟', '喜剧,动作,冒险', 7.2,  '2019-07-12', '中国大陆', 'http://localhost:9060/upload/movies/素人特工.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (16, '追龙Ⅱ',  '梁家辉:龙志强,古天乐:何天', '王晶', '悍匪龙志强（梁家辉 饰），在香港回归前，趁香港英政府不作为，而屡犯巨案，先后绑架富豪利家及雷家之长子，勒索超过二十亿元，事主怕被报复, 交赎款后都不敢报警。中国公安部极为关注，与香港警方合力，派香港警员何天（古天乐 饰）卧底潜入龙志强犯罪团伙，发现他正策划绑架澳门富豪贺不凡，最终陆港警察合力勇擒龙志强，救出贺不凡', '103分钟', '犯罪,剧情,动作', 7.9,  '2019-06-06', '中国大陆、中国香港', 'http://localhost:9060/upload/movies/追龙Ⅱ.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (17, '玩具总动员4',  '汤姆·汉克斯:胡迪,蒂姆·艾伦:巴斯光年', '乔什·库雷', '当邦妮将所有玩具带上房车家庭旅行时，胡迪（汤姆·汉克斯 配音）与伙伴们将共同踏上全新的冒险之旅，领略房间外面的世界有多广阔，甚至偶遇老朋友牧羊女（安妮·波茨 配音）。在多年的独自闯荡中，牧羊女已经变得热爱冒险，不再只是一个精致的洋娃娃。正当胡迪和牧羊女发现彼此对玩具的使命的意义大相径庭时，他们很快意识到更大的威胁即将到来。', '100分钟', '喜剧,动画,奇幻', 9.1,  '2019-06-21', '美国', 'http://localhost:9060/upload/movies/玩具总动员4.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (18, '碟仙',  '黄奕:梦瑶,范逸臣:项天', '廉涛', '以网络直播为业的单亲妈妈梦瑶（黄奕 饰）带着上幼儿园的女儿雯雯住进了一栋便宜的学区房，然而，屋里的诡异氛围，与不时散发的奇怪恶臭，令梦瑶感到不安。某夜，雯雯竟然在梦游中，玩了前租客遗留的“碟仙”游戏。传说只要玩过的人，七日内必会被碟仙夺命！紧接着，屋内接连发生令人毛骨悚然的怪事。七日大限将至，眼看爱女危在旦夕，为了解开碟仙诅咒，夺回女儿，绝望的妈妈不惜做出了惊人的举动……', '83分钟', '恐怖,惊悚', 7.9, '2019-06-21', '中国大陆', 'http://localhost:9060/upload/movies/碟仙.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (19, '扫毒',  '古天乐:苏建秋,刘青云:马昊天', '陈木胜', '以马昊天（刘青云 饰）为首的毒品调查科，与手下张子伟（张家辉 饰）和卧底苏建秋（古天乐 饰）在执行一次跨国的大型扫毒行动中，被毒犯巨头八面佛（卢海鹏 饰）暗中揭发反埋伏，最终全军覆没。面对生死关头，三位主角为求活存，被迫命运扭转，展开一场残酷的人生战役。', '134分钟', '剧情,犯罪', 8.9,  '2018-11-21', '中国大陆、中国香港', 'http://localhost:9060/upload/movies/扫毒.jpg', 0,null,null);
INSERT INTO `fms_film` VALUES (20, '流浪地球',  '吴京:刘培强,屈楚萧:刘启', '郭帆', '近未来，科学家们发现太阳急速衰老膨胀，短时间内包括地球在内的整个太阳系都将被太阳所吞没。为了自救，人类提出一个名为“流浪地球”的大胆计划，即倾全球之力在地球表面建造上万座发动机和转向发动机，推动地球离开太阳系，用2500年的时间奔往另外一个栖息之地。中国航天员刘培强在儿子刘启四岁那年前往国际空间站，和国际同侪肩负起领航者的重任。转眼刘启长大，他带着妹妹朵朵偷偷跑到地表，偷开外公韩子昂的运输车，结果不仅遭到逮捕，还遭遇了全球发动机停摆的事件。为了修好发动机，阻止地球坠入木星，全球开始展开饱和式营救，连刘启他们的车也被强征加入。在与时间赛跑的过程中，无数的人前仆后继，奋不顾身，只为延续百代子孙生存的希望…… 本片根据刘慈欣的同名小说改编。', '125分钟', '剧情,冒险,科幻', 9.2, '2019-07-05', '中国大陆、中国香港', 'http://localhost:9060/upload/movies/流浪地球.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (21, '九龙不败',  '张晋:九龙', '陈果', '警探九龙（张晋 饰），查案方式奇异狠辣却屡建奇功，是叱咤香港警界的精英干探, 但他处事独断爆裂，又被警队视为“偏执狂人”。九龙奉命调查一桩妙龄女警连环被凶杀 案，用尽手段后不仅毫无线索, 他的警花未婚妻方宁（邓丽欣 饰）竟然也在这场抓捕行动中意外失踪。在爱与痛的边缘挣扎的九龙，突然发现女警连环遇害只是第一步，凶手还有着更加血腥的阴谋，自己和未婚妻竟然也被算计其中。此时澳门再度发生女警被杀案，这次的作案手法更加令人发指，但凶手好像故意留下了线索，是危险陷阱还是复仇曙光？九龙义无反顾出发，在好友王梦奇（刘心悠 饰）及国际拳王冼力山（安德森·席尔瓦 饰）的协助下, 与澳门警司曹志德（郑嘉颖 饰）联手展开调查。魔高一丈，道高几何！血债血偿之前，就算流干最后一滴血，也要誓不罢休。', '100分钟', '剧情,动作,犯罪', 5.7, '2019-07-02', '中国大陆、中国香港', 'http://localhost:9060/upload/movies/九龙不败.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (22, '阿丽塔：战斗天使',  '罗莎·萨拉查:阿丽塔,克里斯托弗·沃尔兹:戴森·艾德博士', '罗伯特·罗德里格兹', '未来26世纪，科技发展，人类与机械改造人共存，弱肉强食是钢铁城唯一的生存法则。依德（克里斯托夫·沃尔兹 饰）是钢铁城著名的改造人医生，他在垃圾场发现了一个半机械少女残躯，依德医生将其拯救后为她取名阿丽塔（罗莎·萨拉扎尔 饰）。阿丽塔虽然重获生命却失去了记忆，如一个新生儿一样对这个世界充满新鲜感。在依德医生与好友雨果（基恩·约翰逊 饰）的帮助下，她逐步适应着新生活和街头险恶。一次偶然的机会，阿丽塔发现自己竟有着惊人的战斗天赋。 一次次猎杀激发着她的觉醒，阿丽塔逐渐明白自己注定为战斗而生，为正义而战。一场揭开自己身世之谜，并打破宇宙旧秩序的史诗级冒险之旅就这样展开。', '122分钟', '动作,冒险,科幻', 9.0, '2019-07-11', '美国', 'http://localhost:9060/upload/movies/阿丽塔：战斗天使.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (23, 'X战警：黑凤凰',  '苏菲·特纳:琴·格雷/黑凤凰,詹姆斯·麦卡沃伊:查尔斯·泽维尔/X教授', '西蒙·金伯格', '在一次危及生命的太空营救行动中，琴·葛蕾（苏菲·特纳 饰）被神秘的宇宙力量击中，成为最强大的变种人。此后琴不仅要设法掌控日益增长、极不稳定的力量，更要与自己内心的恶魔抗争，她的失控让整个X战警大家庭分崩离析，也让整个星球陷入毁灭的威胁之中……', '114分钟', '动作,冒险,科幻', 7.8,  '2019-06-06', '美国', 'http://localhost:9060/upload/movies/X战警：黑凤凰.jpg', 0,null,null);
INSERT INTO `fms_film` VALUES (24, '疯狂的外星人',  '黄渤:耿浩,沈腾:大飞,马修·莫里森:扎克,汤姆·派福瑞:约翰', '宁浩', '耿浩（黄渤 饰）与一心想发大财的好兄弟大飞（沈腾 饰），经营着各自惨淡的“事业”，然而“天外来客”（徐峥 饰）的意外降临，打破了二人平静又拮据的生活。神秘的西方力量也派出“哼哈二将”在全球搜查外星人行踪。啼笑皆非的跨物种对决，别开生面的“星战”，在中国某海边城市激情上演。', '116分钟', '剧情,喜剧,科幻', 8.5, '2019-07-10', '中国大陆、美国', 'http://localhost:9060/upload/movies/疯狂的外星人.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (25, '八子',  '刘端端:满崽,邵兵:大牛', '高希希', '上世纪30年代的赣南地区，在这个被称为中国革命“红色摇篮”的地方，曾经有这样一位母亲，她将八个儿子先后送入红军，奔赴战场前线。但战火无情，兄弟中的六人陆续牺牲，只剩下大哥杨大牛和最小的孩子满崽。满崽找到了大牛的部队，成了哥哥麾下的普通一兵，经过一场场战役的淬炼，新兵满崽迅速成长为一个真正的战士。最后的战斗打响了，为了掩护大部队安全撤离，杨大牛带领弟弟满崽和全体战友浴血肉搏，直至弹尽粮绝…… 英雄的身前，是枪林弹雨的沙场，而在英雄的身后，家乡的村庄依然宁静安详，微风吹过金黄的稻浪簌簌作响，一位年迈的母亲正在村头的小路旁孤独的守望……', '121分钟', '战争,历史,动作', 8.4,  '2019-06-30', '中国大陆', 'http://localhost:9060/upload/movies/bazi.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (26, '我的青春都是你',  '宋威龙:方予可,宋芸桦:周林林,林妍柔:茹婷,黄俊捷:谢端西,金士杰:畜牧系老师', '周彤', '周林林（宋芸桦 饰）高考发挥超常进入东方大学，与同校理科状元方予可（宋威龙 饰）一同进入了最高学府。郎有情妾无意，方予可其实从幼儿园时期就心系周林林，人生若只如初见，儿时的初遇相见便立下了日久的暗恋情愫！但万人瞩目的帅哥方予可身边有天之骄女茹庭（林妍柔 饰），从小暗恋状元对周林林看不顺眼，周林林则对方予可身边的同为校园风云人物的小西学长（黄俊捷 饰）心存爱慕，修习大学恋爱秘籍，苦练恋爱通关技巧，十八般武艺七十二变法轮番上阵！四人之间情感纠葛，在校园里上演了一幕青春爱情喜剧！', '92分钟', '爱情,青春', 7.3, '2019-06-21', '中国大陆、中国台湾', 'http://localhost:9060/upload/movies/我的青春都是你.jpg', 1,null,null);
INSERT INTO `fms_film` VALUES (27, '银河补习班',  '邓超:马皓文,白宇:成年马飞', '俞白眉', '浩瀚太空，航天员意外失联，生命最大的绝境中，他回忆起自己那个最了不起的爸爸。一对父子跨越漫长的时光，守护爱和亲情，故事充满了欢乐、温暖、泪水与奇观。', '147分钟', '剧情,家庭', 9.1, '2019-07-18', '中国大陆', 'http://localhost:9060/upload/movies/银河补习班.jpg', 1,null,null);

-- 电影海报表：创建数据表
drop table if exists fms_poster;
create table fms_poster
(
    id bigint unsigned auto_increment,
    title varchar(255) NOT NULL,
    url varchar(255) NOT NULL,
    status tinyint(4) NOT NULL,
    create_at varchar(255) NOT NULL,
    primary key (id)
) comment '电影海报' charset utf8mb4;
INSERT INTO `fms_poster` VALUES ('1', '哆啦A梦：大雄的宇宙小战争', 'http://localhost:9060/upload/poster/poster1.jpg', '1', '2022-09-31 17:06:52');
INSERT INTO `fms_poster` VALUES ('2', '暗恋·橘生淮南', 'http://localhost:9060/upload/poster/poster2.jpg', '1', '2022-09-31 16:58:36');
INSERT INTO `fms_poster` VALUES ('3', '反贪风暴', 'http://localhost:9060/upload/poster/poster3.jpg', '1', '2022-09-31 17:02:18');
INSERT INTO `fms_poster` VALUES ('4', '猫眼专访', 'http://localhost:9060/upload/poster/poster4.jpg', '1', '2022-09-31 16:55:00');

-- 影院表：创建数据表
drop table if exists fms_cinema;
create table fms_cinema
(
    id           bigint unsigned auto_increment comment '影院编号',
    name         varchar(50)      default null comment '影院名称',
    location     varchar(120)     default null comment '影院地址',
    brand        varchar(50)      default null comment '品牌',
    is_refuse    tinyint unsigned default 0 comment '是否可退票，1=可退票，0不可退票',
    is_endorse   tinyint unsigned default 0 comment '是否可改签，1=可改签，0不可改签',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '影院' charset utf8mb4;

-- 影厅表：创建数据表
drop table if exists fms_screen;
create table fms_screen
(
    id                    bigint unsigned auto_increment comment '影厅编号',
    name                  varchar(50)     default null comment '影厅名称',
    type                  varchar(50)      default null comment '影厅类型',
    ro                    tinyint unsigned default null comment '总行数',
    co                    tinyint unsigned default null comment '总列数',
    gmt_create            datetime        default null comment '数据创建时间',
    gmt_modified          datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '影厅' charset utf8mb4;

-- 影厅座位表：创建数据表
drop table if exists fms_screen_seat;
create table fms_screen_seat
(
    id                     bigint unsigned  auto_increment,
    screen_id              bigint unsigned  default null comment '影厅id',
    ro                   tinyint unsigned default null comment '行数',
    co                   tinyint unsigned default null comment '列数',
    gmt_create             datetime         default null comment '数据创建时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '影厅座位' charset utf8mb4;

-- 场次表：创建数据表
drop table if exists fms_arrangement;
create table fms_arrangement
(
    id           bigint unsigned auto_increment,
    film_id      bigint unsigned default null comment '电影id',
    film_name    varchar(50)     default null comment '电影名',
    cinema_name  varchar(50)     default null comment '影院名',
    screen_name  varchar(50)     default null comment '影厅名',
    price        decimal(5,2)    default null comment '价格',
    remaining    int(10)         default null comment '剩余座位数',
    gmt_start    datetime        default null comment '开始时间',
    gmt_end      datetime        default null comment '结束时间',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '场次' charset utf8mb4;

-- 订单表：创建数据表
drop table if exists fms_order;
create table fms_order
(
    id                     bigint unsigned  default 0,
    arrangement_id         bigint unsigned  default null comment '场次id',
    film_name              varchar(50)      default null comment '电影名（冗余）',
    cinema_name            varchar(50)      default null comment '影院名（冗余）',
    screen_name            varchar(50)      default null comment '影厅名（冗余）',
    screen_type            varchar(50)      default null comment '影厅类型（冗余）',
    picture                varchar(255)     default null comment '封面（冗余）',
    price                  decimal(5,2)     default null comment '价格（冗余）',
    user_id                bigint unsigned  default null comment '用户id',
    username               varchar(50)      default null comment '用户名（冗余）',
    phone                  varchar(50)      default null comment '手机号码（冗余）',
    ro                   tinyint unsigned default null comment '行数',
    co                   tinyint unsigned default null comment '列数',
    state                  tinyint unsigned default 0 comment '状态，1=已支付，2=已取消，0=待支付',
    gmt_start              datetime         default null comment '开始时间',
    gmt_end                datetime         default null comment '结束时间',
    gmt_order              datetime         default null comment '下单时间',
    gmt_pay                datetime         default null comment '支付时间',
    gmt_create             datetime         default null comment '数据创建时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '订单' charset utf8mb4;

-- 评论表：创建数据表
drop table if exists fms_comment;
create table fms_comment
(
    id              bigint unsigned auto_increment,
    user_id         bigint unsigned  default null comment '用户id',
    username        varchar(50)      default null comment '用户名（冗余）',
    nick            varchar(50)      default null comment '昵称（冗余）',
    avatar          varchar(255)     default null comment '头像（冗余）',
    content         varchar(255)     default null comment '内容',
    film_id         bigint unsigned  default null comment '电影id',
    film_name       varchar(50)      default null comment '电影名',
    like_count      int unsigned     default 0 comment '点赞数',
    comment_count   int unsigned     default 0 comment '评论数',
    depth           tinyint unsigned default 1 comment '深度，父级评论为1，子级评论为2',
    parent_id       bigint unsigned  default null comment '父级评论id',
    gmt_comment     datetime         default null comment '评论时间',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '评论' charset utf8mb4;

-- 评分表：创建数据表
drop table if exists fms_score;
create table fms_score
(
    id              bigint unsigned auto_increment,
    user_id         bigint unsigned  default null comment '用户id',
    film_id         bigint unsigned  default null comment '电影id',
    score           decimal(2,1)     default null comment '分数（1-5）',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '评分' charset utf8mb4;

# -- 电影类型：创建数据表
drop table if exists fms_ftype;
create table fms_ftype
(
    id              bigint unsigned auto_increment,
    name            varchar(50)      default null comment '类型名',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '电影类型' charset utf8mb4;
INSERT INTO fms_ftype VALUES (1,'全部',null,null);
INSERT INTO fms_ftype VALUES (2,'家庭',null,null);
INSERT INTO fms_ftype VALUES (3,'惊悚',null,null);
INSERT INTO fms_ftype VALUES (4,'科幻',null,null);
INSERT INTO fms_ftype VALUES (5,'爱情',null,null);
INSERT INTO fms_ftype VALUES (6,'动作',null,null);
INSERT INTO fms_ftype VALUES (7,'喜剧',null,null);
INSERT INTO fms_ftype VALUES (8,'恐怖',null,null);
INSERT INTO fms_ftype VALUES (9,'悬疑',null,null);
INSERT INTO fms_ftype VALUES (10,'犯罪',null,null);
INSERT INTO fms_ftype VALUES (11,'冒险',null,null);
INSERT INTO fms_ftype VALUES (12,'战争',null,null);
INSERT INTO fms_ftype VALUES (13,'历史',null,null);
INSERT INTO fms_ftype VALUES (14,'武侠',null,null);
INSERT INTO fms_ftype VALUES (15,'传记',null,null);
INSERT INTO fms_ftype VALUES (16,'古装',null,null);
INSERT INTO fms_ftype VALUES (17,'其他',null,null);

-- 电影类型关联表：创建数据表
drop table if exists fms_film_type;
create table fms_film_type
(
    id              bigint unsigned auto_increment,
    film_id         bigint unsigned  default null comment '电影id',
    film_type_id    bigint unsigned  default null comment '电影类型id',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '电影类型关联' charset utf8mb4;
INSERT INTO fms_film_type VALUES (1,1,6,null,null);
INSERT INTO fms_film_type VALUES (2,1,4,null,null);
INSERT INTO fms_film_type VALUES (3,2,10,null,null);
INSERT INTO fms_film_type VALUES (4,3,7,null,null);
INSERT INTO fms_film_type VALUES (5,4,9,null,null);
INSERT INTO fms_film_type VALUES (6,4,11,null,null);
INSERT INTO fms_film_type VALUES (7,5,5,null,null);
INSERT INTO fms_film_type VALUES (8,6,7,null,null);
INSERT INTO fms_film_type VALUES (9,6,11,null,null);
INSERT INTO fms_film_type VALUES (10,7,6,null,null);
INSERT INTO fms_film_type VALUES (11,8,9,null,null);

-- 电影地区表：创建数据表
# drop table if exists fms_region;
# create table fms_region
# (
#     id              bigint unsigned auto_increment,
#     name            varchar(50)      default null comment '地区名',
#     gmt_create      datetime         default null comment '数据创建时间',
#     gmt_modified    datetime         default null comment '数据最后修改时间',
#     primary key (id)
# ) comment '电影地区' charset utf8mb4;

# -- 影院品牌表：创建数据表
# drop table if exists fms_cinema_brand;
# create table fms_cinema_brand
# (
#     id              bigint unsigned auto_increment,
#     name            varchar(50)      default null comment '品牌名',
#     gmt_create      datetime         default null comment '数据创建时间',
#     gmt_modified    datetime         default null comment '数据最后修改时间',
#     primary key (id)
# ) comment '影院品牌' charset utf8mb4;

-- 影厅类型表：创建数据表
# drop table if exists fms_screen_type;
# create table fms_screen_type
# (
#     id              bigint unsigned auto_increment,
#     name            varchar(50)      default null comment '类型名',
#     gmt_create      datetime         default null comment '数据创建时间',
#     gmt_modified    datetime         default null comment '数据最后修改时间',
#     primary key (id)
# ) comment '影厅类型' charset utf8mb4;

-- 演员表：创建数据表
drop table if exists fms_actor;
create table fms_actor
(
    id              bigint unsigned auto_increment,
    name            varchar(50)      default null comment '演员名',
    photo           varchar(255)     default null comment '照片URL',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '演员' charset utf8mb4;

-- 电影演员表：创建数据表
drop table if exists fms_film_actor;
create table fms_film_actor
(
    id              bigint unsigned auto_increment,
    film_id         bigint unsigned  default null comment '电影id',
    actor_id        bigint unsigned  default null comment '演员id',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '电影演员' charset utf8mb4;

-- Seata使用的undo_log表
DROP TABLE IF EXISTS undo_log;
CREATE TABLE undo_log (
                            id bigint unsigned not null,
                            branch_id bigint NOT NULL,
                            xid varchar(100) NOT NULL,
                            context varchar(128) NOT NULL,
                            rollback_info longblob NOT NULL,
                            log_status int NOT NULL,
                            log_created datetime NOT NULL,
                            log_modified datetime NOT NULL,
                            PRIMARY KEY (id),
                            UNIQUE KEY ux_undo_log (xid,branch_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------- --
-- 以下是插入测试数据及一些测试访问 --
-- -------------------------- --

-- 用户表：插入测试数据
insert into ums_user (username, password, nick, sex, email ,phone ,enable)
values ('张三', '$2a$10$h23zgN3QZ.TzLFikHSI0OOvYUBiIeflecKQI0xhBiH.57cq0TWwWe', 'zs', 0, '12@45', '1212',1),
       ('李四', '$2a$10$h23zgN3QZ.TzLFikHSI0OOvYUBiIeflecKQI0xhBiH.57cq0TWwWe', 'ls', 1, '12@46', '1313',1),
       ('王五', '$2a$10$h23zgN3QZ.TzLFikHSI0OOvYUBiIeflecKQI0xhBiH.57cq0TWwWe', 'ww', 1, '12@78', '1353',1);

-- 角色表：插入测试数据
insert into ums_role(name)
values ('管理员'),
       ('用户');

-- 权限表：插入测试数据
insert into ums_permission(description, value)
values ('查看管理员列表', '/adminList'),
       ('查看用户列表', 'userList'),
       ('修改密码', '/updatePassword');

-- 角色权限表：插入测试数据
insert into ums_role_permission(role_id, permission_id)
values (1,1),
       (1,2),
       (1,3),
       (2,3);

-- 用户角色表：插入测试数据
insert into ums_user_role(user_id, role_id)
values (1,1),
       (2,2),
       (3,2);

-- 电影表：插入测试数据
# insert into fms_film(name, region, state)
# values ('电影1', '中国',1),
#        ('电影2', '美国',1);
# INSERT INTO `fms_film` VALUES (1, '毒液：致命守护者', '汤姆·哈迪:埃迪·布洛克/毒液,米歇尔·威廉姆斯:安妮·韦英', '鲁本·弗雷斯彻', '身为记者的埃迪·布洛克（汤姆·哈迪饰）在调查生命基金会老板卡尔顿·德雷克（里兹·阿迈德饰）的过程中，事业遭受重创，与未婚妻安妮·韦英（米歇尔·威廉姆斯饰）的关系岌岌可危，并意外被外星共生体控制，他历经挣扎对抗，最终成为拥有强大超能力，无人可挡的“毒液“', '107分钟', '动作，科幻', 7.2, 6, '2019-06-24', '美国', 'upload/movies/Venom.jpg', 1);

-- 影院表：插入测试数据
insert into fms_cinema(name, brand, location, is_refuse, is_endorse)
values ('大学城店', '春天国际影城', '闽侯县', 1, 1),
       ('高新IMAX店', '万达影城', '仓山区', 0, 0);

-- 影厅表：插入测试数据
insert into fms_screen(name, type, ro, co)
values ('1号影厅','IMAX厅',15,15),
       ('2号影厅','3D厅',14,14),
       ('3号影厅','4D厅',13,13);

-- 影厅座位表：插入测试数据
insert into fms_screen_seat(screen_id, ro, co)
values (1,5,5),
       (2,4,5),
       (2,4,6);

-- 场次表：插入测试数据
insert into fms_arrangement(film_id,film_name,cinema_name,screen_name,price,remaining,gmt_start,gmt_end)
values (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (1,'毒液：致命守护者','大学城店','1号影厅',45.00,60,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (2,'神奇动物：格林德沃之罪','大学城店','2号影厅',50.00,60,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (3,'无名之辈','大学城店','3号影厅',54.00,60,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (4,'名侦探柯南：零的执行人','大学城店','1号影厅',50.00,225,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (5,'你好,之华','大学城店','2号影厅',54.00,196,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (6,'恐龙王','大学城店','3号影厅',59.00,168,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (7,'冰封侠：时空行者','大学城店','1号影厅',50.00,225,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (8,'梦境之源','大学城店','2号影厅',52.00,196,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (1,'毒液：致命守护者','高新IMAX店','1号影厅',45.00,225,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (2,'神奇动物：格林德沃之罪','高新IMAX店','2号影厅',50.00,196,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (3,'无名之辈','高新IMAX店','3号影厅',52.00,169,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (4,'名侦探柯南：零的执行人','高新IMAX店','1号影厅',54.00,225,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (5,'你好,之华','高新IMAX店','2号影厅',50.00,196,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (6,'恐龙王','高新IMAX店','3号影厅',58.00,169,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',50.00,225,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-25 16:10:10','2022-10-24 18:10:10'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-25 18:20:20','2022-10-24 20:20:20'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-25 20:30:30','2022-10-24 22:30:30'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-26 16:10:10','2022-10-24 18:10:10'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-26 18:20:20','2022-10-24 20:20:20'),
       (7,'冰封侠：时空行者','高新IMAX店','1号影厅',53.00,225,'2022-10-26 20:30:30','2022-10-24 22:30:30'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 16:10:10','2022-10-24 18:10:10'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 18:20:20','2022-10-24 20:20:20'),
       (8,'梦境之源','高新IMAX店','2号影厅',50.00,196,'2022-10-24 20:30:30','2022-10-24 22:30:30');

-- 订单表：插入测试数据
insert into fms_order(id,arrangement_id, film_name, cinema_name, screen_name, screen_type, price, user_id, username, phone, ro, co, state,picture)
values (2121022,1, '名侦探柯南','大学城店','1号影厅','IMAX厅',45.00,2,'李四',1111,5,5,1,'http://localhost:9060/upload/movies/名探偵.jpg'),
       (2132132,2, '无名之辈','大学城店','2号影厅','4D厅',50.00,2,'李四',1313,4,5,0,'http://localhost:9060/upload/movies/A Cool Fish.jpg');

-- 评论表：插入测试数据
insert into fms_comment(user_id, username, nick, content, film_id, film_name)
values (2,'李四','ls',
        '看电影名以为是俗套的爱情片，本来已经做好了被割韭菜的准备，没想到意外的很好看，很感人，很有深度，电影要表达的东西很多',1,'电影1'),
       (2,'李四','ls',
        '看电影名以为是俗套的爱情片，本来已经做好了被割韭菜的准备，没想到意外的很好看，很感人，很有深度，电影要表达的东西很多',2,'电影2');

-- 评分表：插入测试数据
insert into fms_score(user_id, film_id, score)
values (2,1,3),
       (2,2,4);

-- 电影类型表：插入测试数据
# insert into fms_film_type(name)
# values ('动作'),
#        ('喜剧');
#
# -- 电影地区表：插入测试数据
# insert into fms_region(name)
# values ('中国'),
#        ('美国');

-- 影院品牌表：插入测试数据
# insert into fms_cinema_brand(name)
# values ('春天国际影城'),
#        ('万达影城');

-- 影厅类型表：插入测试数据
# insert into fms_screen_type(name)
# values ('IMAX厅'),
#        ('4D厅');

-- 演员表：插入测试数据
insert into fms_actor(name)
values ('kunkun'),
       ('坤坤');

-- 电影演员表：插入测试数据
insert into fms_film_actor(film_id, actor_id)
values (1,1),
       (1,2),
       (2,2);
