insert into country (Country_name)
values ('Беларусь');


insert into city (City_name, Country_id)
values ('Минск', 1);
insert into city (City_name, Country_id)
values ('Брест', 1);
insert into city (City_name, Country_id)
values ('Витебск', 1);
insert into city (City_name, Country_id)
values ('Гомель', 1);
insert into city (City_name, Country_id)
values ('Могилев', 1);
insert into city (City_name, Country_id)
values ('Гродно', 1);


insert into address (country_id, city_id, street, number_house)
values ( 1, 1, 'Сталина', 22 );
insert into user (username, password, email, First_name, Last_name, Phone_number,address_id, active)
values ( 'user', 'user', 'user@user.ru', 'Name', 'User', 80299998877, 1, true );
insert into user_role (user_id, role_users)
values (1, 'USER');

insert into address (country_id, city_id, street, number_house)
values ( 1, 3, 'Центральная', 13 );
insert into user (username, password, email, First_name, Last_name, Phone_number, address_id, active)
values ( 'admin', 'admin', 'admin@admin.ru', 'Name', 'Admin', 80291112233, 2, true );
insert into user_role (user_id, role_users)
values (2, 'USER'), (2, 'ADMIN');



insert into Type_product (Type_name)
values ('Гантель');
insert into Type_product (Type_name)
values ('Гриф');
insert into Type_product (Type_name)
values ('Беговая дорожка');
insert into Type_product (Type_name)
values ('Эллиптический тренажер');

insert into Category_product (Category_name)
values ('Турник');
insert into Category_product (Category_name)
values ('Шведская стенка');
insert into Category_product (Category_name)
values ('Железо для спорта');
insert into Category_product (Category_name)
values ('Тренажер');

insert into Brand_product (Brand_name)
values ('Adidas');
insert into Brand_product (Brand_name)
values ('Fora Sport');
insert into Brand_product (Brand_name)
values ('Miron Fit');
insert into Brand_product (Brand_name)
values ('VSport');
insert into Brand_product (Brand_name)
values ('Vertex');


insert into Model_product (Model_name)
values ('Power Tower G606');
insert into Model_product (Model_name)
values ('Professional');
insert into Model_product (Model_name)
values ('Champion CLS');
insert into Model_product (Model_name)
values ('Record R-2');
insert into Model_product (Model_name)
values ('Hybrid');
insert into Model_product (Model_name)
values ('OWT24');
insert into Model_product (Model_name)
values ('BSK13');

insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (2, 4, 7, 'Уникальные стеновые кронштейны обеспечивают максимальную монолитность конструкции, Вы можете нагружать стенку весом до 400 кг и она не будет колебаться. Абсолютная вертикальная жесткость обеспечивается благодаря уникальным креплениям кронштейнов Рекорд Х .',
        269.00, 0, '/static/img/стенка1.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (1, 5, 3, 'это новый профессиональный турник, для настоящих энтузиастов спорта и воркаута на базе усиленного разборного турника.',
        219.99, 0, '/static/img/турник1.jpg');
insert into Product (Category_product_id, type_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (4, 3, 4, 3, 'На удобном монохромном дисплее беговой дорожки отображаются преодоленная вами дистанция, пульс, расход калорий, а также скорость движения и время тренировки. Язык интерфейса дисплея - английский.',
        210.00, 0, '/static/img/дорожка1.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (1, 1, 2, 'это отличный вариант делать комплекс упражнений на разные группы мышц.',
        179.00, 0, '/static/img/турник3.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (2, 2, 6, 'Наше решение с большим раскосом спереди обеспечивает монолитность конструкции и сводит к минимуму возможные колебания при занятиях. Преимущества данной стенки в том, что ее можно передвинуть или переместить. ',
        199.99, 0, '/static/img/стенка2.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (2, 2, 4, 'Шведская стенка – это базовая основа, для которой можно использовать разнообразное навесное оборудование. При выборе стоит уделить внимание тому, какие именно дополнительные элементы нужны и для выполнения каких упражнений.',
        239.00, 0, '/static/img/стенка3.jpg');
insert into Product (Category_product_id, type_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (4, 4, 2, 5, 'Кардио-тренировка – это одна из главных функций данного тренажера эллиптического типа. ',
        659.40, 0, '/static/img/эллиптический1.jpg');
insert into Product (Category_product_id,type_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (3, 2, 2, 3, 'Гриф для штанги Титан хромированный великолепно подходит для занятия физическими упражнениями.',
        130.00, 0, '/static/img/гриф1.jpg');
insert into Product (Category_product_id,type_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (3, 1, 1, 1, 'Гантель 61 кг черная неразборная. Предназначена для выполнения различных силовых упражнений, использования в качестве отягощения. Подходят для использования дома и в залах.',
        129.30, 0, '/static/img/гантель1.jpg');
insert into Product (Category_product_id, type_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (3, 1, 1, 2, 'Гантель гексагональная обрезиненная ZSO, 10кг. С обрезиненными шестиугольными грузами. Анатомическая стальная ручка, покрытие зеркальный хром. Удобная форма грузов предохраняет от катания гантели.',
        99.00, 0, '/static/img/гантель2.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (1, 2, 1, 'Прочный стальной тренажер для дома, который подойдет новичкам, и профессиональным спортсменам, для поддержания тела в форме.',
        150.00, 0, '/static/img/турник2.jpg');
insert into Product (Category_product_id, type_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (4, 3, 4, 6, 'Тренажёр оборудован компьютером, который позволяет отслеживать пульс, время тренировки, скорость, пройдённую дистанцию и потраченные калории.',
        389.99, 0, '/static/img/дорожка2.jpg');


