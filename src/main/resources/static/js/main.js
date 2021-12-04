function sum(count, price, sum) {
    var count = document.getElementById(count).value;
    var price = document.getElementById(price).value;

    document.getElementById(sum).innerHTML = count * price;
}