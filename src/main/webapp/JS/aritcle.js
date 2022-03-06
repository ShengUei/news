$.ajax({
    url: `QueryArticleByNumber`,
    dataType: 'json',
    success: function (json) {
        let article = document.querySelector("#article");
        let docFrag = document.createDocumentFragment();
        let div;
        let div_img;
        let h2;
        let img;
        let p;
        let br;
        let hr;

        div = document.createElement("div");

        h2 = document.createElement("h2");
        h2.className = "blog-post-title";
        h2.innerHTML = `${json.title}`;

        div.appendChild(h2);

        p = document.createElement("p");
        p.className = "blog-post-meta";
        let postTime = new Date(json.postTime);
        p.innerHTML = `作者: ${json.author}<br>發文時間: ${postTime}`;

        div.appendChild(p);

        div_img = document.createElement("div");
        div_img.className = "d-flex justify-content-center";
        for (let picture of json.pictureList) {
            img = document.createElement("img");
            img.src = `./images/${picture.picturePath}`;
            div_img.appendChild(img);
        }
        div.appendChild(div_img);

        br = document.createElement("br");
        hr = document.createElement("hr");
        div.appendChild(br);
        div.appendChild(hr);

        for (let content of json.contentList) {
            p = document.createElement("p");
            p.innerHTML = `${content.paragraph}`;
            div.appendChild(p);
        }


        docFrag.appendChild(div);

        article.appendChild(docFrag);

    }
});