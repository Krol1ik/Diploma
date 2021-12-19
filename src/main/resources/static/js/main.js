function sum(count, price, sum) {
    var count = document.getElementById(count).value;
    var price = document.getElementById(price).value;

    document.getElementById(sum).innerHTML = count * price;
}

    function myFunction(id) {
    var change = document.getElementById(id);
    if (change.innerHTML == "В корзину") {
    change.innerHTML = "в корзине";
    change.style.backgroundColor = "fff";
} else if (change.innerHTML == "Add to cart"){
    change.innerHTML = "in the cart";
    change.style.backgroundColor = "fff";
}
}





