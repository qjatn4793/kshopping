window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    var productSelect = $("#product-select option:selected").val(); // 한 페이지에 나타낼 글 수
    var currentPage = $(".dataTable-pagination-list li.active a").text(); // 선택한 페이지

    product(productSelect, currentPage);

    $("#product-select").change(function(){
        productSelect = $("#product-select option:selected").val(); // 한 페이지에 나타낼 글 수
        product(productSelect, currentPage);
    });

    $(".dataTable-pagination-list li").click(function(){
        $(".dataTable-pagination-list li.active").removeClass("active");
        $(this).addClass("active");

        currentPage = $(".dataTable-pagination-list li.active a").text();
        product(productSelect, currentPage);
    });

    function product(productSelect, currentPage){
        $.ajax({
            type: "GET",
            url: "/product",
            dataType: "JSON",
            async: false,
            error: function () {
                alert('통신실패!!');
            },
            success: function (data) {
                var str = "";

                for (var i = 0; productSelect > i; i++) {
                /*for (var i = 0; Object.keys(data).length > i; i++) {*/

                    var productSeq = data[Object.keys(data)[i]].productSeq;
                    var productName = data[Object.keys(data)[i]].productName;
                    var productLikes = data[Object.keys(data)[i]].productLikes;
                    var productViews = data[Object.keys(data)[i]].productViews;
                    var regDate = data[Object.keys(data)[i]].regDate;
                    var modDate = data[Object.keys(data)[i]].modDate;
                    var productThumbImg = data[Object.keys(data)[i]].productThumbImg;

                    if (productThumbImg == "" || productThumbImg == null || productThumbImg == 0) {
                        productThumbImg = "https://dummyimage.com/450x300/dee2e6/6c757d.jpg";
                    } else {
                        productThumbImg = productThumbImg.replace("\\", "/");
                        productThumbImg = "common/img" + productThumbImg;
                    }

                    str += "<div class='col mb-5'>" +
                        "<div class='card h-100'>" +
                        "<a class='btn mt-auto' href='/detailProduct/" + productSeq + "'>" +
                        "<img class='card-img-top' src='" + productThumbImg + "' alt='...' />" +
                        "</a>" +
                        "<div class='card-body p-4'>" +
                        "<div class='text-center'>" +
                        "<a class='btn mt-auto' href='/detailProduct/" + productSeq + "'>" +
                        "<h5 class='fw-bolder'>" + productName + "</h5>" +
                        "</a><br>" +
                        "조회 수 : " + productViews +
                        "</div>" +
                        "</div>" +
                        "<div class='card-footer p-4 pt-0 border-top-0 bg-transparent'>" +
                        "<div class='text-center'><a class='btn btn-outline-dark mt-auto' href='/detailProduct/" + productSeq + "'>View options</a></div>" +
                        "</div>" +
                        "</div>" +
                        "</div>";

                    $("div.main-product").html(str);

                    $(".dataTable-info").text("Showing " + currentPage + " to " + productSelect * currentPage + " of"+ " entries");

                }
            }
        });
    }

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }

});
