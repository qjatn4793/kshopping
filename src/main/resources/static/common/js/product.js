window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

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

            for (var i = 0; Object.keys(data).length > i; i++) {

                var productSeq = data[Object.keys(data)[i]].productSeq;
                var productName = data[Object.keys(data)[i]].productName;
                var productLikes = data[Object.keys(data)[i]].productLikes;
                var productViews = data[Object.keys(data)[i]].productViews;
                var regDate = data[Object.keys(data)[i]].regDate;
                var modDate = data[Object.keys(data)[i]].modDate;
                var productThumbImg = data[Object.keys(data)[i]].productThumbImg;

                if(productThumbImg == "" || productThumbImg == null || productThumbImg == 0){
                    productThumbImg = "https://dummyimage.com/450x300/dee2e6/6c757d.jpg";
                }else {
                    productThumbImg = productThumbImg.replace("\\", "/");
                    productThumbImg = "common/img" + productThumbImg;
                }

                str += "<div class='col mb-5'>" +
                    "<div class='card h-100'>" +
                    "<img class='card-img-top' src='"+productThumbImg+"' alt='...' />" +
                    "<div class='card-body p-4'>" +
                    "<div class='text-center'>" +
                    "<h5 class='fw-bolder'>" + productName + "</h5>" +
                    "조회 수 : " + productViews +
                    "</div>" +
                    "</div>" +
                    "<div class='card-footer p-4 pt-0 border-top-0 bg-transparent'>" +
                    "<div class='text-center'><a class='btn btn-outline-dark mt-auto' href='/detailProduct/" + productSeq + "'>View options</a></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>";

                $("div.main-product").html(str);
            }
        }
    });

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }

});
