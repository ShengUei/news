$.ajax({
    url: "SignInCheck",
    dataType: 'json',
    success: function (memberJson) {
        let memberDiv = document.querySelector("#member");
        let docFrag = document.createDocumentFragment();
        let p;
        let a;

        if (memberJson != null) {
            let username = `${memberJson.username}`;
            p = document.createElement("p");
            if (username != null) {
                p.innerHTML = `${username} 您好`;
            } else {
                p.innerHTML = `${memberJson.account} 您好`;
            }
            docFrag.appendChild(p);

        } else {
            a = document.createElement("a");
            a.href = "signIn.html";
            a.className = "btn btn-outline-primary me-2";
            a.innerHTML = "Sign In"
            docFrag.appendChild(a);

            a = document.createElement("a");
            a.href = "signUpMember.html";
            a.className = "btn btn-primary";
            a.innerHTML = "Sign Up"
            docFrag.appendChild(a);
        }

        memberDiv.appendChild(docFrag);

    }
});