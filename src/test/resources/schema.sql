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