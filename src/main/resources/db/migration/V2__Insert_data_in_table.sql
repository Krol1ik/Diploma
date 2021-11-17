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


insert into Type_product (Type_name)
values ('Гантели');
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
values ('Железо');
insert into Category_product (Category_name)
values ('Спортивная площадка');
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
values (1, 5, 3, 'это новый профессиональный турник, для настоящих энтузиастов спорта и воркаута на базе усиленного разборного турника.',
        219.99, 0, '/static/img/турник1.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (1, 5, 3, 'это новый профессиональный турник, для настоящих энтузиастов спорта и воркаута на базе усиленного разборного турника.',
        219.99, 0, '/static/img/турник1.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (1, 5, 3, 'это новый профессиональный турник, для настоящих энтузиастов спорта и воркаута на базе усиленного разборного турника.',
        219.99, 0, '/static/img/турник1.jpg');
insert into Product (Category_product_id, Brand_product_id, Model_product_id, Description_product, Price, Discount, filename)
values (1, 5, 3, 'это новый профессиональный турник, для настоящих энтузиастов спорта и воркаута на базе усиленного разборного турника.',
        219.99, 0, '/static/img/турник1.jpg');

