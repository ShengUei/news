$.ajax({
    url: "ArticleList",
    dataType: 'json',
    success: function (json) {
        let articleList = document.querySelector("#articleList");
        let docFrag = document.createDocumentFragment();
        let div;
        let div_body;
        let img;
        let a;
        let h5;
        let p;

        // console.log(json);
        $.each(json, function(index, article) {
            div = document.createElement("div");
            div.id = "article";
            div.className = "media d-flex position-relative";

            img = document.createElement("img");
            img.src = `./images/${article.pictureList[0].picturePath}`;
            img.className = "flex-shrink-0 me-3";
            img.height = "240";

            div.appendChild(img);

            div_body = document.createElement("div");
            div_body.className = "media-body";

            h5 = document.createElement("h5");
            h5.className = "mt-0";
            h5.innerHTML = `${article.title}`;
            
            div_body.appendChild(h5);
            
            p = document.createElement("p");
            p.className = "blog-post-meta";
            let postTime = new Date(article.postTime);
            p.innerHTML = `作者: ${article.author}<br>發文時間: ${postTime}`;
            
            div_body.appendChild(p);
            
            p = document.createElement("p");
            p.innerHTML = `${article.contentList[0].paragraph}`;
            
            div_body.appendChild(p);

            a = document.createElement("a");
            a.className = "stretched-link";
            a.innerHTML = "繼續閱讀"
            a.href = `Article?articleNo=${article.articleNo}`;

            div_body.appendChild(a);
            
            div.appendChild(div_body);

            docFrag.appendChild(div);
        })

        articleList.appendChild(docFrag);

    }
});