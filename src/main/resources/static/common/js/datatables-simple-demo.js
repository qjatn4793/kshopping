window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    $.ajax({
        type : "GET",
        url : "/product",
        dataType : "JSON",
        async : false,
        error : function(){
            alert('통신실패!!');
        },
        success : function(data){
            var str = "";

            for(var i = 0; Object.keys(data).length > i; i++){

                var productSeq = data[Object.keys(data)[i]].productSeq;
                var productName = data[Object.keys(data)[i]].productName;
                var productLikes = data[Object.keys(data)[i]].productLikes;
                var productViews = data[Object.keys(data)[i]].productViews;
                var regDate = data[Object.keys(data)[i]].regDate;
                var modDate = data[Object.keys(data)[i]].modDate;

                str += "<tr>" +
                    "<td>" + productSeq + "</td>" +
                    "<td>" + productName + "</td>" +
                    "<td>" + productLikes + "</td>" +
                    "<td>" + productViews + "</td>" +
                    "<td>" + regDate + "</td>" +
                    "<td>" + modDate + "</td>" +
                    "</tr>";

                $("tbody.product").html(str);
            }
        }
    });

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }

});
