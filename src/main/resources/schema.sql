CREATE TABLE IF NOT EXISTS admin (
    admin_id VARCHAR(255) PRIMARY KEY,
    admin_account VARCHAR(255),
    admin_password VARCHAR(255),
    admin_name VARCHAR(255),
    admin_age VARCHAR(255),
    admin_sex VARCHAR(255),
    admin_telephone VARCHAR(255),
    admin_email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pet (
    pet_id VARCHAR(255) PRIMARY KEY,
    pet_name VARCHAR(255),
    pet_sex VARCHAR(255),
    pet_sub VARCHAR(255),
    pet_type VARCHAR(255),
    pet_bir VARCHAR(255),
    pet_detail VARCHAR(1000),
    pet_pic VARCHAR(255),
    pet_state VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user (
    user_id VARCHAR(255) PRIMARY KEY,
    user_account VARCHAR(255),
    user_password VARCHAR(255),
    user_name VARCHAR(255),
    user_age VARCHAR(255),
    user_sex VARCHAR(255),
    user_telephone VARCHAR(255),
    user_email VARCHAR(255),
    user_address VARCHAR(255),
    user_state VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS apply (
    apply_id VARCHAR(255) PRIMARY KEY,
    apply_user_name VARCHAR(255),
    apply_pet_name VARCHAR(255),
    apply_user_sex VARCHAR(255),
    apply_user_address VARCHAR(255),
    apply_user_telephone VARCHAR(255),
    apply_user_state VARCHAR(255),
    apply_time VARCHAR(255),
    apply_state VARCHAR(255),
    apply_user_id VARCHAR(255),
    apply_pet_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS file (
    file_id VARCHAR(255) PRIMARY KEY,
    file_name VARCHAR(255),
    file_url VARCHAR(500),
    file_time VARCHAR(255),
    file_uid VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sys_log (
    log_id VARCHAR(255) PRIMARY KEY,
    log_time VARCHAR(255),
    log_username VARCHAR(255),
    log_operation VARCHAR(255),
    log_method VARCHAR(500),
    log_params VARCHAR(1000),
    log_ip VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_log (
    log_id VARCHAR(255) PRIMARY KEY,
    log_time VARCHAR(255),
    log_username VARCHAR(255),
    log_operation VARCHAR(255),
    log_method VARCHAR(500),
    log_params VARCHAR(1000),
    log_ip VARCHAR(255)
);

INSERT INTO admin (admin_id, admin_account, admin_password, admin_name, admin_age, admin_sex, admin_telephone, admin_email) 
VALUES ('1', 'admin', 'admin', '管理员', '20', '男', '13800138000', 'admin@pet.com');
