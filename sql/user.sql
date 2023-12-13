CREATE TABLE IF NOT EXISTS `user` (
    `id` int(11) NOT NULL,
    `username` varchar(30) CHARACTER SET utf8mb4 NOT NULL COMMENT '⽤户名',
    `password` varchar(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
    `name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '姓名',
    `gender` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '性别',
    `phone` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '电话',
    `email` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '邮箱'
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `username_UNIQUE` (`username`) USING BTREE,
    ADD KEY `username_INDEX` (`username`);
ALTER TABLE `user`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;