var cart = [];
$(document).ready(function () {
    $('.add2cart').on('click', function () {
        var bId = $(this).attr('bookId');
        var bName = $('.book-'+bId).children().children
        ('#bookName').text();
        var imgUrl = $('.book-'+bId).find('img').attr('src');
        var bPrice = $('.book-'+bId).find('#price').text();
        var obj = {
            id: bId,
            bookName: bName,
            imageUrl: imgUrl,
            price: bPrice
        };
        //check san pham co trong gio hang hay chua
        var flag = false;
        for (var i=0; i<cart.length; i++ ){
            if (cart[i].id == obj.id){
                flag = true;
                break;
            }
        }
        if ((flag == false)){
            obj.quantity = 1;
            cart.push(obj);
        }else {//san pham da co trong gio hang
            cart[i].quantity += 1;
        }
        drawCheckout();
    });

});
function drawCheckout() {
    $('tbody').empty();
    var ckUnit = "";
    var totalMonney = 0;
    for (var i =0; i < cart.length; i++){
        totalMonney += cart[i].price * cart[i].quantity;
        ckUnit += `
            <tr>
                  <td class="goods-page-image">
                    <a href="javascript:;"><img src="${cart[i].imageUrl}" alt=""></a>
                  </td>
                  <td class="goods-page-description">
                    <h3><a href="javascript:;">${cart[i].bookName}</a></h3>
                  </td>
                  <td class="goods-page-ref-no">
                    javc2133
                  </td>
                  <td class="goods-page-quantity">
                    <div class="product-quantity" onchange="changeUnitQuantity(this, ${cart[i].id})">
                      <input id="product-quantity" type="text" value="${cart[i].quantity}" readonly class="form-control input-sm">
                    </div>
                  </td>
                  <td class="goods-page-price">
                    <strong><span>$</span>${cart[i].price}</strong>
                  </td>
                  <td class="goods-page-total">
                    <strong><span>$</span>${cart[i].price * cart[i].quantity}</strong>
                  </td>
                  <td class="del-goods-col">
                    <a class="del-goods" href="javascript:;" onclick="removeUnit(${cart[i].id})">&nbsp;</a>
                  </td>
                </tr>`
    }
    ckUnit += `
        <tr>
        <td colspan="6">Total Price</td>
        <td ><b>$${totalMonney}</b></td>
        </tr>
        `;
    $('tbody').append(ckUnit);
}
function removeUnit(id) {
    // Check san pham co trong gio hang hay chua
    for (var i = 0; i < cart.length; i++) {
        if(cart[i].id == id){
            cart.splice(i, 1);
            break;
        }
    }
    drawCheckout();
}
function changeUnitQuantity(e, id){
    var ipValue = e.value;
    if(ipValue > 0){
        for (var i = 0; i < cart.length; i++) {
            if(cart[i].id == id){
                cart[i].quantity = ipValue;
                break;
            }
        }
        drawCheckout();
    }else{
        removeUnit(id);
    }
}
function view(bookInfo) {
    var id = bookInfo.getAttribute("Bookid");
    var image = bookInfo.getAttribute("BookImg");
    var price = bookInfo.getAttribute("BookPrice");
    var description = bookInfo.getAttribute("BookDescription");
    document.getElementById("descr").innerHTML = description;
    var imageModal = document.getElementById("imgModal");
    imageModal.src = image;
    document.getElementById("priceModal").innerHTML = price;
}
