INSERT INTO CITY_RISK
(product_code, product_version,
 city_category, factor,
 effective_from, effective_to)
VALUES
('MOTOR_PC','V1','METRO',1.20, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 5 YEAR)),
('MOTOR_PC','V1','NON_METRO',1.00, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 5 YEAR));
