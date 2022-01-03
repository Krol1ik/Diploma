## SportLine

Интернет-магазин спортивных товаров "Sport Line"

Cтартовая страница->http://localhost:8080/

О проекте: 
1. Можно фильтровать каталог магазина по кнопкам фильтра (турники, железо и т.д.), также можно искать в поиске товар по буквам (используется "Живой поиск");
2. В веб-системе у неавторизованного пользователя есть возможность заказать товар в магазине, путем добавления его в корзину, после оформления заказа, ему придет сообщение по адресу электронной почты с информацией о заказе. p.s. товар привязан к кол-ву на складе, нельзя заказать кол-ва, которого нет; 
3. После регистрации нового пользователя, ему приходит ссылка на адрес эл. почты, которую он указал, если не перейти по данной ссылке, то в аккаунт зайти будет невозможно;
4. В базе данных все пароли хранятся в кодированном виде;
5. Работает функционал "Забыли пароль?", вводится e-mail, на который был зарегистрирован аккаунт и на данный адрес электронной почты приходит ссылка для восстановления пароля;
6. У авторизованного пользователя также есть возможность делать заказ, а также открывается доступ к его личным данным, которые подтягиваются при оформление заказа и есть история заказа, где видна сумма заказа, кол-во и дата/время заказа. 
7. У администратора есть своя роль "ADMIN". У него появляется доп. кнопка на товарах в каталоге "Удалить товар" и еще 3 раздела: 
- Список пользователей (в нем все зарегистрированные пользователи и информация в сумме всех его покупок и кол-во купленных товаров за все время);
- Добавить товар (форма добавления товара), после добавления товара он появляется в каталоге;
- Список товаров (весь список товаров с возможностью удалить их или обновить);

В проекте были использованы технологии: Spring Boot, Spring Data, Spring Security, Spring Validation, FlyWay, база данных H2, Devtools. Views - JSTL, CSS, Bootstrap, JavaScript.

Пользователи по умолчанию: админ-логин "admin" пароль "admin" пользователь-логин "user" пароль "user"