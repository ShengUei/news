$.ajax({
    url: "ArticleList",
    dataType: 'json',
    success: function (json) {
        let carousel_imgs = document.querySelectorAll(".carousel-item > img");
        let carousel_as = document.querySelectorAll(".carousel-item > a");
        let carousel_ps = document.querySelectorAll(".carousel-caption > p");

        $.each(json, function(index, article) {
            carousel_imgs[index].src = `./images/${article.pictureList[0].picturePath}`;
            carousel_imgs[index].width = "800";
            carousel_imgs[index].height = "400";

            carousel_ps[index].innerHTML = `${article.title}`;
            carousel_as[index].href = `Article?articleNo=${article.articleNo}`;

            if (index >= 2) {
                return false;
            }
        })

    }
});