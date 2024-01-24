const url_new = '/api/admin/create';
const roles_new = document.querySelector('#roles').selectedOptions;
const form_new = document.forms["formForCreatingNewUser"];

async function newUser() {
    form_new.addEventListener('submit', addNewUser)

    async function addNewUser(e) {
        e.preventDefault();

        let listOfRole = [];
        for (let i = 0; i < roles_new.length; i++) {
            listOfRole.push("ROLE_" + roles_new[i].value);
        }

        let method = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: form_new.username.value,
                surname: form_new.surname.value,
                age: form_new.age.value,
                email: form_new.email.value,
                password: form_new.password.value,
                roles: listOfRole
            })
        }

        await fetch(url_new, method).then(() => {
            form_new.reset();
            getAdminPage();

            $("#tabBtnAllUsers").click();

        });
        form_new.removeEventListener('submit', addNewUser);
    }


}