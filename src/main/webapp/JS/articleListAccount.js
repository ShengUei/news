$.ajax({
    url: "QueryArticleByAccount",
    dataType: 'json',
    success: function (json) {
        let tbody = document.querySelector("tbody");
        let docFrag = document.createDocumentFragment();
        let tr;
        let td;
        let form;
        let input;
        let a;

        // console.log(json);
        $.each(json, function(index, article) {
            tr = document.createElement("tr");

            td = document.createElement("td");
            td.innerHTML = `${article.articleNo}`;
            tr.appendChild(td);

            let postTime = new Date(article.postTime);
            td = document.createElement("td");
            td.innerHTML = `${postTime}`;
            tr.appendChild(td);

            td = document.createElement("td");
            td.innerHTML = `${article.category}`;
            tr.appendChild(td);

            td = document.createElement("td");
            td.innerHTML = `${article.title}`;
            tr.appendChild(td);

            td = document.createElement("td");
            td.innerHTML = `${article.contentList[0].contentNo}`;
            tr.appendChild(td);

            td = document.createElement("td");
            td.innerHTML = `${article.pictureList[0].pictureNo}`;
            tr.appendChild(td);

            td = document.createElement("td");
            a = document.createElement("a");
            a.href = `Article?articleNo=${article.articleNo}`;
            a.innerHTML = "文章資訊";
            a.className = "btn btn-info";
            td.appendChild(a);
            tr.appendChild(td);

            td = document.createElement("td");
            form = document.createElement("form");
            form.method = "post";
            form.action = "#";
            input = document.createElement("input");
            input.value = "修改文章";
            input.type = "submit";
            input.className = "btn btn-warning";
            form.appendChild(input);
            td.appendChild(form);
            tr.appendChild(td);

            td = document.createElement("td");
            form = document.createElement("form");
            form.method = "post";
            form.action = "#";
            input = document.createElement("input");
            input.value = "刪除文章";
            input.type = "submit";
            input.className = "btn btn-danger";
            form.appendChild(input);
            td.appendChild(form);
            tr.appendChild(td);

            docFrag.appendChild(tr);

        })

        tbody.appendChild(docFrag);

    }
});