{
  document.querySelector('.form-sign-up').addEventListener('submit', function (evt) {
    evt.preventDefault();
    let firstName = document.querySelector('input[name=firstName]').value;
    let lastName = document.querySelector('input[name=lastName]').value;
    let login = document.querySelector('input[name=login]').value;
    let email = document.querySelector('input[name=email]').value;
    let sex = document.querySelector('input[name=sex]:checked').value;
    let birthDate = document.querySelector('input[name=birthDate]').value;
    let password = document.querySelector('input[name=password]').value;
    let repeatPassword = document.querySelector('input[name=repeatPassword]').value;


    let body = {
      firstName: firstName,
      lastName: lastName,
      login: login,
      email: email,
      sex: sex,
      birthDate: birthDate,
      password: password,
      repeatPassword: repeatPassword,
    };
    fetch('/v1/sign-up', {
      method: 'POST',
      body: JSON.stringify(body),
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      }
    })
      .then(response => {
        return response.json();
      })
      .then(json => {
        if (Array.isArray(json)) {
          alert(json);
          return;
        }
        window.location.href = "/login"
      });
  });
}
