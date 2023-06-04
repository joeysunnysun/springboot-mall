CREATE TABLE IF NOT EXISTS product
(
    productId         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    productName       VARCHAR(128)  NOT NULL,
    category           VARCHAR(32)  NOT NULL,
    imageUrl          VARCHAR(256) NOT NULL,
    price              INT          NOT NULL,
    stock              INT          NOT NULL,
    description        VARCHAR(1024),
    createdDate       TIMESTAMP    NOT NULL,
    lastModifiedDate TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS `user`
(
    userId            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email              VARCHAR(256) NOT NULL UNIQUE,
    password           VARCHAR(256) NOT NULL,
    createdDate       TIMESTAMP    NOT NULL,
    lastModifiedDate TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS `order`
(
    orderId           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId            INT       NOT NULL,
    totalAmount       INT       NOT NULL,
    createdDate       TIMESTAMP NOT NULL,
    lastModifiedDate TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS orderItem
(
    orderItemId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    orderId      INT NOT NULL,
    productId    INT NOT NULL,
    quantity      INT NOT NULL,
    amount        INT NOT NULL
);