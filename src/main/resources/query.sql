CREATE TABLE `user` ( `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT, `uid` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_general_ci', `upw` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci', `nm` VARCHAR(30) NOT NULL COLLATE 'utf8mb4_general_ci', `created_at` DATETIME NOT NULL DEFAULT current_timestamp(), `updated_at` DATETIME NULL DEFAULT NULL ON UPDATE current_timestamp(), PRIMARY KEY (`user_id`) USING BTREE, UNIQUE INDEX `uid` (`uid`) USING BTREE ) COLLATE='utf8mb4_general_ci' ENGINE=INNODB;



CREATE TABLE `board` ( `board_id` BIGINT(20) NOT NULL AUTO_INCREMENT, `title` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci', `contents` VARCHAR(1000) NOT NULL COLLATE 'utf8mb4_general_ci', `writer_id` BIGINT(20) NOT NULL, `hits` INT(11) NOT NULL DEFAULT '0', `created_at` DATETIME NOT NULL DEFAULT current_timestamp(), `updated_at` DATETIME NULL DEFAULT NULL ON UPDATE current_timestamp(), PRIMARY KEY (`board_id`) USING BTREE, INDEX `writer_id` (`writer_id`) USING BTREE, CONSTRAINT `board_ibfk_1` FOREIGN KEY (`writer_id`) REFERENCES `user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT ) COLLATE='utf8mb4_general_ci' ENGINE=INNODB;



CREATE TABLE `comment` (
   comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
   contents VARCHAR(1000) NOT NULL,
   writer_id BIGINT NOT NULL,
   board_id BIGINT NOT NULL,
   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   update_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (writer_id) REFERENCES user(user_id),
   FOREIGN KEY (board_id) REFERENCES board(board_id)
);