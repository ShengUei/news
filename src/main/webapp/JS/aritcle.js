$.ajax({
    url: `QueryArticleByNumber`,
    dataType: 'json',
    success: function (json) {
        let article = document.querySelector("#article");
        let docFrag = document.createDocumentFragment();
        let div;
        let h2;
        let img;
        let p;
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

        for (let content of json.contentList) {
            p = document.createElement("p");
            p.innerHTML = `${content.paragraph}`;
            div.appendChild(p);
        }

        hr = document.createElement("hr");
        div.appendChild(hr);

        docFrag.appendChild(div);

        article.appendChild(docFrag);

    }
});