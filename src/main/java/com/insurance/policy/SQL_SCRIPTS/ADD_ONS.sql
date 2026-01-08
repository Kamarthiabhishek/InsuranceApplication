INSERT INTO ADD_ONS
(product_code, product_version,
 addon_code, pricing_type, pricing_value,
 effective_from, effective_to)
VALUES
('MOTOR_PC','V1','ZERO_DEP','PERCENT',15,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 5 YEAR)),
 
 ('MOTOR_PC','V1','ZERO_DEP','FLAT',1500,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 5 YEAR)),

('MOTOR_PC','V1','RSA','PERCENT',12,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 5 YEAR)),
 
('MOTOR_PC','V1','RSA','FLAT',1200,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 5 YEAR));
