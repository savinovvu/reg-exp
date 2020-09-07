{
  document.querySelector('.form-login').addEventListener('submit', function (evt) {
    evt.preventDefault();
    let login = document.querySelector('input[name=login]').value;
    let password = document.querySelector('input[name=password]').value;
    let body = {login: login, password: password};
    fetch('/v1/sign-in', {
      method: 'POST',
      body: JSON.stringify(body),
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      }
    })
      .then(response => {
        return response.json();
      }).then(json => {
      if (json.token) {
        localStorage.setItem("jwt_token", json.token);
        localStorage.setItem("id", json.id);
        localStorage.setItem("fullName", json.fullName);
        localStorage.setItem("roles", json.roles);
        window.location.href = "/regexp";
      } else {
        alert(json)
      }
    }).catch(error => {
        debugger;
        alert(error);
      }
    );
  });
}

