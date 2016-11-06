INSERT INTO
    `hibernate_sequence` (`next_val`)
VALUES
    (10);

INSERT INTO
        dealer (city, country, line, post_code, zone, associate, email, name, phone, id)
VALUES
        ('Browndale', 'Dale Country', '88 Skiddy Street', 'RBB 9AP', 'Blue', 'Thimma', 'arial@clean.com', 'Arial',
        '341013523253', 1),
        ('Blight', 'Bleak Country', '66 Valley Street', 'PDT 9SN', 'Green', 'Ivan', 'bog@softbogs.com', 'Bog',
         '234234123412', 2),
        ('Bangalore', 'India', '66 Rajajinagar', 'PDT 9SN', 'Green', 'Ivan', 'carcare@bosch.com', 'Bosch Car Care',
        '234234123412', 3),
        ('Bangalore', 'India', '88 Jeevan Nagar', 'PDT 9SN', 'Green', 'Ivan', 'junction@gmail.com', 'Junction',
        '3143135123', 4),
        ('Bangalore', 'India', '78 Basavanagudi', 'PDT 9SN', 'Green', 'Thimma', 'underground@yahoo.com', 'Underground',
        '5642343423', 5),
        ('Bangalore', 'India', '85 J P Nagar', 'PDT 9SN', 'Green', 'Manja', 'evo@gmail.com', 'Evo',
        '8563412233', 6),
        ('Bangalore', 'India', '64 Tataguni', 'PDT 9SN', 'Green', 'Boda', 'cardecor@decor.com', 'Car Decor',
        '1231241324', 7),
        ('Bangalore', 'India', '31 Thyag Nagar', 'PDT 9SN', 'Green', 'Thimma', 'sai@gmail.com', 'Om Sai Motors',
        '5511343462', 8),
        ('Bangalore', 'India', '64 Tataguni', 'PDT 9SN', 'Red', 'Boda', 'autoshop@decor.com', 'Auto Shop',
        '5211899434', 9);



INSERT INTO
`product` (`id`, `name`,`type`)
VALUES
(1, 'SoftBogRoll','Wipes'),
(2, 'Dash','Cool');


INSERT INTO
`dealer_product` (`dealer_id`, `product_id`)
VALUES
(2, 1);

INSERT INTO
`note` (`id`, `time`,`text`, `dealer_id`)
VALUES
(1, '15:6, Fri Nov 04 2016','Follow up failed, dealer not interested', 1),
(2, '15:8, Fri Nov 04 2016','Dealer changed mind. Spoke of a ghost. He is ready to invest', 1);

