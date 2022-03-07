$.ajax({
    url: "SignInCheck",
    dataType: 'json',
    success: function (memberJson) {
        let memberDiv = document.querySelector("#member");
        let docFrag = document.createDocumentFragment();
        let div;
        let span;
        let p;
        let a;
        let ul;
        let li;
        let hr;

        div = document.createElement("div");
        div.className = "dropdown text-end";

        ul = document.createElement("ul");
        ul.className = "dropdown-menu text-small";
        ul.setAttribute("aria-labelledby", "dropdownUser1");

        if (memberJson != null) {

            let username = `${memberJson.username}`;
            span = document.createElement("span");
            if (username != null) {
                span.innerHTML = `${username} 您好`;
            } else {
                span.innerHTML = `${memberJson.account} 您好`;
            }

            a = document.createElement("a");
            a.className = "d-block link-dark text-decoration-none dropdown-toggle";
            a.id = "dropdownUser1";
            a.setAttribute("data-bs-toggle", "dropdown");
            a.setAttribute("aria-expanded", "false");

            a.appendChild(span);
            div.appendChild(a);

            li = document.createElement("li");
            a = document.createElement("a");
            a.className = "dropdown-item";
            a.href = "CreateNewArticle";
            a.innerHTML = "新增文章";
            
            li.appendChild(a);
            ul.appendChild(li);
            
            li = document.createElement("li");
            a = document.createElement("a");
            a.className = "dropdown-item";
            a.href = "ArticleManagement";
            a.innerHTML = "文章管理";
            
            li.appendChild(a);
            ul.appendChild(li);

            li = document.createElement("li");
            hr = document.createElement("hr");
            hr.className = "dropdown-divider";

            li.appendChild(hr);
            ul.appendChild(li);
            
            li = document.createElement("li");
            a = document.createElement("a");
            a.className = "dropdown-item";
            a.href = "SignOut";
            a.innerHTML = "Sign Out";

            li.appendChild(a);
            ul.appendChild(li);
            
            div.appendChild(ul);
            docFrag.appendChild(div);

        } else {
            a = document.createElement("a");
            a.href = "SignIn";
            a.className = "btn btn-outline-primary me-2";
            a.innerHTML = "Sign In";
            docFrag.appendChild(a);

            a = document.createElement("a");
            a.href = "SignUp";
            a.className = "btn btn-primary";
            a.innerHTML = "Sign Up";
            docFrag.appendChild(a);
        }

        memberDiv.appendChild(docFrag);

    }
});