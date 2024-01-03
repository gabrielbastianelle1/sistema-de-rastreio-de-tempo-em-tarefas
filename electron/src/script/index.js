const { ipcRenderer } = window;

const inputUsername = document.getElementById("username");
const inputPassword = document.getElementById("password");
const buttonSubmit = document.getElementById("submit");

buttonSubmit.addEventListener("click", (event) => {
    event.preventDefault();

    let request = {
        username: inputUsername.value,
        password: inputPassword.value,
    };
    console.log(JSON.stringify(request));

    window.ipc.signup(JSON.stringify(request));
});
