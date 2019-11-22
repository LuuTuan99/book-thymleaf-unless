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
    $('.fancybox-fast-view').on('click', function () {
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
        debugger;
        viewUnit();
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
function viewUnit() {
    $('.product-pop-up').empty();
    var vkUnit = "";
    vkUnit += `
<!--<div id="product-pop-up" style="display: none; width: 700px;">-->
  <div class="product-page product-pop-up">
        <div class="row">
      <div class="col-md-6 col-sm-6 col-xs-3">
        <div class="product-main-image">
          <img src="https://www.vinabook.com/images/thumbnails/product/240x/351680_p90386mnguoithay.jpg" alt="" class="img-responsive">
        </div>
        <div class="product-other-images">
          <a href="javascript:;" class="active">
            <img alt="Berry Lace Dress 1" src="@{/assets-client/pages/img/products/book2.jpg}">
          </a>
          <a href="javascript:;">
            <img alt="Berry Lace Dress 2" th:src="@{/assets-client/pages/img/products/book5.jpg}">
          </a>
          <a href="javascript:;">
            <img alt="Berry Lace Dress 3" th:src="@{/assets-client/pages/img/products/book1.jpg}">
          </a>
        </div>
      </div>
      <div class="col-md-6 col-sm-6 col-xs-9">
        <h2></h2>
        <div class="price-availability-block clearfix">
          <div class="price">
            <strong><span></span>price</strong>
            <em><span>price quantity</span></em>
          </div>
          <div class="availability">
            Availability: <strong>In Stock</strong>
          </div>
        </div>
        <div class="description">
          <p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed nonumy nibh sed euismod laoreet dolore magna
            aliquarm erat volutpat Nostrud duis molestie at dolore.</p>
        </div>
        <div class="product-page-cart">
          <div class="product-quantity">
            <input id="product-quantity" type="text" value="quantity" readonly name="product-quantity"
                   class="form-control input-sm">
          </div>
          <button class="btn btn-primary add2cart" type="submit">Add to cart</button>
          <a th:href="@{/shop-item}" class="btn btn-default">More details</a>
        </div>
      </div>

      <div class="sticker sticker-sale"></div>
    </div>
<!--  </div>-->
</div>`;
    $('.product-pop-up').append(vkUnit);
}
