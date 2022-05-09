window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    var productSelect = $("#product-select option:selected").val(); // 한 페이지에 나타낼 글 수
    var currentPage = 1; // 선택한 페이지

    product();

    $("#product-select").change(function(){
        productSelect = $("#product-select option:selected").val(); // 한 페이지에 나타낼 글 수
        product();
    });

    function product(){
        $.ajax({
            type: "GET",
            url: "/productView",
            dataType: "JSON",
            async: false,
            error: function () {
                alert('통신실패!!');
            },
            success: function (data) {
                let str = "";
                let paging = "";

                let totalProduct = Object.keys(data).length;
                let endPage = Math.ceil(totalProduct / productSelect);

                for (let j = 1; endPage + 1> j; j++) {
                    if(j == currentPage) {
                        paging += '<li class="active"><a href="#" data-page="'+ j +'">'+ j +'</a></li>';
                    }else {
                        paging += '<li class=""><a href="#" data-page="'+ j +'">'+ j +'</a></li>';
                    }
                }

                $(".dataTable-pagination-list").html(paging);

                var endNum = productSelect * currentPage; // 현재 페이지 상품 끝
                var prevNum = endNum - productSelect; // 현재 페이지 상품 시작

                for (let i = prevNum; endNum > i; i++) {
                /*for (var i = 0; Object.keys(data).length > i; i++) {*/
                    if (Object.keys(data).length > i) {
                        let productSeq = data[Object.keys(data)[i]].productSeq;
                        let productName = data[Object.keys(data)[i]].productName;
                        let productLikes = data[Object.keys(data)[i]].productLikes;
                        let productViews = data[Object.keys(data)[i]].productViews;
                        let regDate = data[Object.keys(data)[i]].regDate;
                        let modDate = data[Object.keys(data)[i]].modDate;
                        let productThumbImg = data[Object.keys(data)[i]].productThumbImg;

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

                        $(".dataTable-info").text("Showing " + (prevNum + 1) + " to " + endNum + " of"+ " entries");

                    }
                }
            }
        });
    }

    $(".dataTable-pagination-list li").click(function(){

        $(".dataTable-pagination-list li.active").removeClass("active");
        $(this).addClass("active");

        currentPage = $(".dataTable-pagination-list li.active a").text();
        product();
    });

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }

});
