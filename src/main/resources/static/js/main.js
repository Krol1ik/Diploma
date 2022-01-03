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

function searchCatalog(){
    var val = document.getElementById("search").value;
    var opts = document.getElementById('searchresults').childNodes;
    for (var i = 0; i < opts.length; i++) {
        if (opts[i].value === val) {
            location.href='http://localhost:8080/catalog/search?search=' + val;
            break;
        }
    }
    var search = document.querySelector('#search');
    var results = document.querySelector('#searchresults');
    var templateContent = document.querySelector('#resultstemplate').content;
    search.addEventListener('keyup', function handler(event) {
        while (results.children.length) results.removeChild(results.firstChild);
        var inputVal = new RegExp(search.value.trim(), 'i');
        var set = Array.prototype.reduce.call(templateContent.cloneNode(true).children, function searchFilter(frag, item, i) {
            if (inputVal.test(item.textContent) && frag.children.length < 6) frag.appendChild(item);
            return frag;
        }, document.createDocumentFragment());
        results.appendChild(set);
    });
}



