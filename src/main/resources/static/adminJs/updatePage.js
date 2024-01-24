const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('id_ed');
const username_ed = document.getElementById('username_ed');
const surname_ed = document.getElementById('surname_ed');
const age_ed = document.getElementById('age_ed');
const email_ed = document.getElementById('email_ed');
const password_ed = document.getElementById('password_ed');

async function editModalData(id) {
    const urlDataEd = '/api/admin/getUser/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(async user => {
                id_ed.value = `${user.id}`;
                username_ed.value = `${user.username}`;
                surname_ed.value = `${user.surname}`;
                age_ed.value = `${user.age}`;
                email_ed.value = `${user.email}`;
                password_ed.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}

class Role {
    constructor(id, role) {
        this.id = id;
        this.role = role;
    }
}

async function editUser() {
    let urlEdit = '/api/admin/getUser/' + id_ed.value
    let listOfRole = [];

    for (let i = 0; i < form_ed.rolesForEditing.options.length; i++) {
        if (form_ed.rolesForEditing.options[i].selected) {
            listOfRole.push("ROLE_" + form_ed.rolesForEditing.options[i].value);
        }
    }


    let method = {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: document.getElementById("id_ed").value,
            username: document.getElementById("username_ed").value,
            surname: document.getElementById("surname_ed").value,
            age: document.getElementById("age_ed").value,
            email: document.getElementById("email_ed").value,
            password: document.getElementById("password_ed").value,
            roles: listOfRole
        })
    }

    await fetch(urlEdit, method).then(() => {
        $('#editCloseBtn').click();
        getAdminPage();
    })
}


